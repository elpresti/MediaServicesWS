package com.mediaservices.hibernate;

import org.hibernate.Session;

import com.mediaservices.model.RadioStation;

public class HibernateMediums {

    public static String out="<br>HibernateMediums LOG:<br>";

    private static HibernateMediums uniqueInstance;
    
    private HibernateMediums() {
      
    }
    
    public static HibernateMediums getInstance(){
      if (uniqueInstance == null){
        uniqueInstance = new HibernateMediums();
      }
      return uniqueInstance;
    }


  public int saveRadioStation (RadioStation station){
    int canDoIt = 0;
    try{
      Session session = HibernateUtil.getOpenedSession();
      session.beginTransaction();
      session.persist(station);
      session.getTransaction().commit();
      session.close();
      canDoIt = station.getMediumId();
    } catch (Exception e){
      //System.out.println(e.toString());
      out += "<br>"+e.toString();
    }
    return canDoIt;
  }

  public RadioStation getRadioStation(int stationId){
    RadioStation station = null;
    try{
      Session session = HibernateUtil.getOpenedSession();
      session.beginTransaction();
      station = (RadioStation) session.get(RadioStation.class,stationId);
      session.close();
    } catch (Exception e){
      System.out.println(e.toString());
    }
    return station;
  }

}