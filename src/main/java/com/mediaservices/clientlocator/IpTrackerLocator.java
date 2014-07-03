package com.mediaservices.clientlocator;


import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class IpTrackerLocator extends Client{
  private String trackerName="ip-tracker.org";
  private String baseUrl="http://www.ip-tracker.org/locator/ip-lookup.php?ip=";
  
  public IpTrackerLocator(){
    setTracker(getTrackerName());
  }    
  

  public String getBaseUrl(){
     return baseUrl;
  }
  
  public void setBaseUrl(String baseUrl){
     this.baseUrl = baseUrl;
  }
  
  public String getTrackerName(){
     return trackerName;
  }
   
  public void setTrackerName(String trackerName){
     this.trackerName = trackerName;
  }

  public boolean collectData(){
    boolean canDoIt = false;
    try{
      String tmpStr = "";
      int tmpInt = 0;
      Document doc = Jsoup.connect(getBaseUrl()+getIp()).get();
      
      Element htmlElements = doc.select("#maincontent table").get(1); //gets all the tables inside the maincontent div
      htmlElements = htmlElements.getElementsByTag("tr").get(3); 
      Element mainTable = htmlElements.getElementsByTag("td").get(0); //get the important table
      
      //get Region
      htmlElements = mainTable.getElementsByTag("tr").get(8);
      htmlElements = htmlElements.getElementsByTag("td").get(0); 
      setRegion(htmlElements.text());
      
      //get City
      htmlElements = mainTable.getElementsByTag("tr").get(9);
      htmlElements = htmlElements.getElementsByTag("td").get(0); 
      tmpStr = htmlElements.text().trim();
      if (tmpStr.length()>0){
        setCity(tmpStr);
      }
      
      //get CountryCode
      htmlElements = mainTable.getElementsByTag("tr").get(6);
      htmlElements = htmlElements.getElementsByTag("td").get(0); 
      tmpStr = htmlElements.text().trim();
      tmpInt = tmpStr.indexOf("(");
      if (tmpInt != -1){
        tmpStr = tmpStr.substring(tmpInt+1, tmpInt+3).trim();
        setCountryCode(tmpStr);
      }
      
      //return info
      //setCountryName(htmlElements.toString());
      canDoIt=true;
    }catch(Exception e){
      setRegion(e.toString());
      e.printStackTrace();
    }
    return canDoIt;
  }

}