package org.sebapresti.mediaservices.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.xml.ws.spi.http.HttpHandler;

@Path("/injectdemo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InjectDemoResource {

	@GET
	@Path("anotations")
	public String getParamsUsingAnotations(@MatrixParam("param") String matrixParam,
								@HeaderParam("customHeaderValue") String headerValue,
								@CookieParam("name") String cookie
			){
		return "matrixParam="+matrixParam+". customHeaderValue="+headerValue
				+". Cookie="+cookie;
	}
	
	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo,
										@Context HttpHeaders httpHeaders
				){
		String path = uriInfo.getAbsolutePath().toString();
		String cookies = httpHeaders.getCookies().toString();
		return "Path: "+path+". Cookies: "+cookies;
	}
	
}
