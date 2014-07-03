package com.mediaservices.ws.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.mediaservices.clientlocator.IpTrackerLocator;
import com.mediaservices.hibernate.HibernatePerson;
import com.mediaservices.model.Person;

import java.security.Principal;

@Path("/v1/client/")
public class V1_client {
	
	@Context private HttpServletRequest requestContext;
	@Context private SecurityContext context;
	@PathParam("ip") String ipParam;
	private String defaultValue="UNKNOWN";
	
	@GET
	@Path("/getLocation")
	@Produces(MediaType.TEXT_HTML)
	public String getLocation(){
	    String output="No Data";
	    String ip = getIp();
	    IpTrackerLocator ipTracker = new IpTrackerLocator();
	    ipTracker.setIp(ip);
	    if (!ipTracker.collectData()){
	      
	    }
	    output = ipTracker.getJSONclientInfo();
	    return output;
	}

	
	
	@GET
	@Path("/getIp")
	@Produces(MediaType.TEXT_HTML)
	//public String getIp(HttpServletRequest requestContext, SecurityContext context){
	public String getIp(){
	    String output=getDefaultValue();
	    String ipAddress = "";
	    if (!(getIpParam() != null  &&  getIpParam().length()>0)){
	      ipAddress = requestContext.getHeader("X-FORWARDED-FOR");
	      if (ipAddress == null) {
	         ipAddress = requestContext.getRemoteAddr();
	      }
	    }else{
	      ipAddress = getIpParam();
	    }
	    if (ipAddress.length()>0){
	      output = ipAddress;
		}
		return output;
	}
	
	@GET
	@Path("/savePerson")
	@Produces(MediaType.TEXT_HTML)
	public String savePerson(){
		String output="NOT SAVED";
		if (HibernatePerson.getInstance().createPersonOnDb("seba-30062014-2110", "calle-30062014-2110", "ciudad-30062014-2110", "pinCode-30062014-2110")){
			output= "done!";    	
		}else{
			output= "failed to save!";
		}
		return output;
	}
	
	
	@GET
	@Path("/getClientInfo")
	@Produces(MediaType.TEXT_HTML)
	public String getClientInfo(){
	    ApplicationContext context = new FileSystemXmlApplicationContext( "classpath:applicationContext.xml");
	    Person person = (Person) context.getBean("defaultPersonInfo");
	    return person.showInfo();
	}
	
	    
	private SecurityContext getContext() {
	     return context;
	}

	private void setContext(SecurityContext context) {
	     this.context = context;
	}

	private HttpServletRequest getRequestContext() {
	     return requestContext;
	}

	private void setRequestContext(HttpServletRequest requestContext) {
	     this.requestContext = requestContext;
	}
		  
	public String getDefaultValue() {
	     return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
	     this.defaultValue = defaultValue;
	}

	public String getIpParam() {
	     return ipParam;
	}

	public void setIpParam(String ipParam) {
	     this.ipParam = ipParam;
	}
	
}