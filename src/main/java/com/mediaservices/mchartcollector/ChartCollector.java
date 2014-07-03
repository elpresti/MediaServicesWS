package com.mediaservices.mchartcollector;

import java.util.ArrayList;
import com.mediaservices.model.music.Song;
import com.mediaservices.model.Medium;

public abstract class ChartCollector {
  private Medium medium;
  private String baseUrl;
  
  private ArrayList<Song> songs;
  
  
  public ArrayList<Song> getSongs(){
      return songs;
  }

  public void setSongs(ArrayList<Song> songs){
     this.songs = songs;
  }
  
  public abstract boolean getChart();
  
  public Medium getMedium(){
     return medium;
  }
   
  public void setMedium(Medium station){
     this.medium = station;
  }

  public String getBaseUrl(){
     return baseUrl;
  }
  
  public void setBaseUrl(String baseUrl){
     this.baseUrl = baseUrl;
  }

}