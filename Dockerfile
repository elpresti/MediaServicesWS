FROM tomcat:8-alpine
COPY target/mediaservices.war /usr/local/tomcat/webapps/mediaservices.war