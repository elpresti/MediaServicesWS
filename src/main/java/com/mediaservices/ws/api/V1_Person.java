package com.mediaservices.ws.api;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.mediaservices.clientlocator.IpTrackerLocator;
import com.mediaservices.model.Person;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;

@Path("/v1/person/")
public class V1_Person {
	
	private String defaultValue="UNKNOWN";
	
	@POST
	@Path("/savePerson")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String savePerson(
				@FormParam("name") String name,
				@FormParam("surname") String surname,
				@FormParam("nickname") String nickname,
				@FormParam("dateOfBirth") Date dateOfBirth,
				@FormParam("imgUrl") String imgUrl,
				@FormParam("roleName") String roleName,
				@Context HttpServletResponse servletResponse) throws IOException {
		String output="No Data";
		try {
			InitialContext ctx = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			output= e.toString();
		}
	    
	    if (!( (name != null && name.length()>1)  && (surname != null && surname.length()>1) )) {
	  	    output = "ERROR: NOMBRE Y/O APELLIDO INVÁLIDOS";
	    }else{
	    	String result = HibernatePerson.getInstance().savePerson(name, surname, nickname, dateOfBirth, imgUrl, roleName);
	    	if (result != "done"){
		    	output = "NO SE PUDO GUARDAR LA PERSONA<br>Error:<br>"+result;	      
		    }else{
		    	output = "LA PERSONA SE GUARDO CORRECTAMENTE";
		    }
	    }
	    return output;
	}

}
