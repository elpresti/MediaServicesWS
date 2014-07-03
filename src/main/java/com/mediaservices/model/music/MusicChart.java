package com.mediaservices.model.music;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.mediaservices.model.RadioStation;

@Entity
@Table(name="MUSICCHARTS")
public class MusicChart {
  @Id @GeneratedValue
  private int mchartId;
  private String name;
  
  @ManyToMany (cascade=CascadeType.ALL)
  private java.util.Collection<Song> songs = new ArrayList<Song>();

  private RadioStation station;
  
  
  public int getMchartId(){
     return mchartId;
  }
  
  public void setMchartId(int mchartId){
     this.mchartId = mchartId;
  }
  
  public String getName(){
     return name;
  }
  
  public void setName(String name){
     this.name = name;
  }

  
}