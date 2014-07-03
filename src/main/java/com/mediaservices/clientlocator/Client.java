package com.mediaservices.clientlocator;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;


public abstract class Client {
  private String ip;
  private String countryName;
  private String countryCode;
  private String language;
  private String region;
  private String city;
  private double latitude;
  private double longitude;
  private String tracker;
  private String defaultValue="UNKNOWN";

  public  Client(){
    setIp(getDefaultValue());
    setCountryName(getDefaultValue());
    setRegion(getDefaultValue());
    setLanguage(getDefaultValue());
    setCountryCode(getDefaultValue());
    setCity(getDefaultValue());
    setLatitude(0);
    setLongitude(0);
    setTracker(getDefaultValue());
  }

  public abstract boolean collectData();

  public String getCity(){
     return city;
  }

  public void setCity(String city){
     this.city = city;
  }

  public String getCountryCode(){
     return countryCode;
  }

  public void setCountryCode(String countryCode){
     this.countryCode = countryCode;
  }

  public String getCountryName(){
     return countryName;
  }

  public void setCountryName(String countryName){
     this.countryName = countryName;
  }

  public String getIp(){
     return ip;
  }

  public void setIp(String ip){
     this.ip = ip;
  }

  public String getLanguage(){
     return language;
  }

  public void setLanguage(String language){
     this.language = language;
  }

  public double getLatitude(){
     return latitude;
  }

  public void setLatitude(double latitude){
     this.latitude = latitude;
  }

  public double getLongitude(){
   return longitude;
  }

  public void setLongitude(double longitude){
   this.longitude = longitude;
  }
  
  public String getRegion(){
   return region;
  }

  public void setRegion(String region){
    this.region = region;
  }

  public String getDefaultValue(){
     return defaultValue;
  }

  public void setDefaultValue(String defaultValue){
     this.defaultValue = defaultValue;
  }
  
  public String getTracker(){
     return tracker;
  }

  public void setTracker(String tracker){
     this.tracker = tracker;
  }

  public String getJSONclientInfo(){
    String output="No Data";
    try{
      JSONObject clientInfo = new JSONObject();
      clientInfo.put("ip", getIp());
      clientInfo.put("tracker", getTracker());
      clientInfo.put("countryName", getCountryName());
      clientInfo.put("countryCode", getCountryCode());
      clientInfo.put("language", getLanguage());
      clientInfo.put("region", getRegion());
      clientInfo.put("city", getCity());
      clientInfo.put("longitude", getLongitude());
      clientInfo.put("latitude", getLatitude());
      output = clientInfo.toString();
    }catch(Exception e){
      System.out.println(e.toString());
    }
    return output;
  }

}