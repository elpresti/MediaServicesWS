package com.mediaservices.model;

import com.mediaservices.model.Medium;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="TVSTATIONS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class TvStation extends Medium {

  private ArrayList<String> videoStreams
;
  
  @ManyToMany (cascade=CascadeType.ALL)
   @JoinTable (name="PROGRAM_TVSTATION",
       joinColumns=@JoinColumn(name="PROGRAM_ID"), 
       inverseJoinColumns=@JoinColumn(name="TVSTATION_ID")
     )
   private Collection<Program> programs = new ArrayList<Program>();


  public Collection<Program> getPrograms() {
     return programs;
  }
  
  public void setPrograms(Collection<Program> programs){
     this.programs = programs;
  }
  
  public ArrayList<String> getVideoStreams(){
     return videoStreams;
  }
  
  public void setVideoStreams(ArrayList<String> videoStreams){
     this.videoStreams = videoStreams;
  }
    
  
}

