package com.mediaservices.ws.api;


import com.mediaservices.mchartcollector.*;
import com.mediaservices.model.RadioStation;

import com.mediaservices.hibernate.HibernateMediums;
import com.mediaservices.hibernate.HibernateUtil;

import javax.ws.rs.*;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Context;

import java.security.Principal;

@Path("/v1/chartfollower/")
public class V1_ChartFollower{

  @GET
  @Path("/printDocuments")
  @Produces(MediaType.TEXT_HTML)
  public String printDocuments(){
    String output = "NO DATA";
/*    
    OtherClass gdocs = new OtherClass();
    try{
      output= gdocs.printDocuments();      
    }catch(Exception e){
      output= "<br>Exception: "+e.toString();
    }
*/
    return output;
  }

  @GET
  @Path("/addAndGetRadioStation")
  @Produces(MediaType.TEXT_HTML)
  public String addAndGetRadioStation(){
    String output = "NO DATA";
    try{
      RadioStation radio = new RadioStation();
      radio.setName("Radio Sarasa!");
      radio.setCountry("Eslovenia");
      int stationSavedId = HibernateMediums.getInstance().saveRadioStation(radio);
      if (stationSavedId != 0){
        output = "<br>Se pudo guardar correctamente la estación de Radio<br>";
        RadioStation savedRadio = HibernateMediums.getInstance().getRadioStation(stationSavedId);
        if (savedRadio != null){
          output += "Los datos de la radio guardada son:<br>Nombre: "+savedRadio.getName()+"<br>Pais: "+savedRadio.getCountry();
        }else{
          output += "No se pudo leer la estación guardada"+HibernateMediums.out+HibernateUtil.out;
        }
      }else{
        output = "FAILED TO SAVE STATION!"+HibernateMediums.out+HibernateUtil.out;
      }
    }catch(Exception e){
      output = "<br>addAndGetRadioStation LOG: <br>"+e.toString()+HibernateMediums.out+HibernateUtil.out;
    }
    
    
    return output;
  }
  
  @GET
  @Path("/get40ppalesAr")
  @Produces(MediaType.TEXT_HTML)
  public String get40ppalesAr(){
    String output = "NO DATA";
    Ar40ppalesCollector chart = new Ar40ppalesCollector();
    if (chart.getChart()){
      output = chart.out;
    }else{
      output = "FAILED TO GET CHART!";
    }
    
    return output;
  }
  
  
}