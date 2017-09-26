env.DOCKERHUB_USERNAME = 'elpresti'
env.PROJECT_NAME = 'MediaServicesWS'

  node("docker-test") {
    checkout scm

    stage("Unit Test") {
      sh "docker run --rm -v ${WORKSPACE}:/go/src/${PROJECT_NAME} golang go test ${PROJECT_NAME} -v --run Unit"
    }
    stage("Integration Test") {
      try {
        sh "docker build -t ${PROJECT_NAME} ."
        sh "docker rm -f ${PROJECT_NAME} || true"
        sh "docker run -d -p 8080:8080 --name=${PROJECT_NAME} ${PROJECT_NAME}"
        // env variable is used to set the server where go test will connect to run the test
        sh "docker run --rm -v ${WORKSPACE}:/go/src/${PROJECT_NAME} --link=${PROJECT_NAME} -e SERVER=${PROJECT_NAME} golang go test ${PROJECT_NAME} -v --run Integration"
      }
      catch(e) {
        error "Integration Test failed"
      }finally {
        sh "docker rm -f ${PROJECT_NAME} || true"
        sh "docker ps -aq | xargs docker rm || true"
        sh "docker images -aq -f dangling=true | xargs docker rmi || true"
      }
    }
    stage("Build") {
      sh "docker build -t ${DOCKERHUB_USERNAME}/${PROJECT_NAME}:${BUILD_NUMBER} ."
    }
    stage("Publish") {
      withDockerRegistry([credentialsId: 'DockerHub']) {
        sh "docker push ${DOCKERHUB_USERNAME}/${PROJECT_NAME}:${BUILD_NUMBER}"
      }
    }
  }

  node("docker-stage") {
    checkout scm

    stage("Staging") {
      try {
        sh "docker rm -f ${PROJECT_NAME} || true"
        sh "docker run -d -p 8080:8080 --name=${PROJECT_NAME} ${DOCKERHUB_USERNAME}/${PROJECT_NAME}:${BUILD_NUMBER}"
        sh "docker run --rm -v ${WORKSPACE}:/go/src/${PROJECT_NAME} --link=${PROJECT_NAME} -e SERVER=${PROJECT_NAME} golang go test ${PROJECT_NAME} -v"

      } catch(e) {
        error "Staging failed"
      } finally {
        sh "docker rm -f ${PROJECT_NAME} || true"
        sh "docker ps -aq | xargs docker rm || true"
        sh "docker images -aq -f dangling=true | xargs docker rmi || true"
      }
    }
  }

  node("docker-prod") {
    stage("Production") {
      try {
        // Create the service if it doesn't exist otherwise just update the image
        sh '''
          SERVICES=$(docker service ls --filter name=${PROJECT_NAME} --quiet | wc -l)
          if [[ "$SERVICES" -eq 0 ]]; then
            docker network rm ${PROJECT_NAME} || true
            docker network create --driver overlay --attachable ${PROJECT_NAME}
            docker service create --replicas 3 --network ${PROJECT_NAME} --name ${PROJECT_NAME} -p 8080:8080 ${DOCKERHUB_USERNAME}/${PROJECT_NAME}:${BUILD_NUMBER}
          else
            docker service update --image ${DOCKERHUB_USERNAME}/${PROJECT_NAME}:${BUILD_NUMBER} ${PROJECT_NAME}
          fi
          '''
        // run some final tests in production
        checkout scm
        sh '''
          sleep 60s 
          for i in `seq 1 20`;
          do
            STATUS=$(docker service inspect --format '{{ .UpdateStatus.State }}' ${PROJECT_NAME})
            if [[ "$STATUS" != "updating" ]]; then
              docker run --rm -v ${WORKSPACE}:/go/src/${PROJECT_NAME} --network ${PROJECT_NAME} -e SERVER=${PROJECT_NAME} golang go test ${PROJECT_NAME} -v --run Integration
              break
            fi
            sleep 10s
          done
          
        '''
      }catch(e) {
        sh "docker service update --rollback  ${PROJECT_NAME}"
        error "Service update failed in production"
      }finally {
        sh "docker ps -aq | xargs docker rm || true"
      }
    }
  }
