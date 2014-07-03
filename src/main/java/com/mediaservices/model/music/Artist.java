package com.mediaservices.model.music;
import javax.persistence.Entity;

@Entity
public class Artist {
  private String name;
  private String surname;
  private String birthdate;
  
  
  public String getBirthdate(){
     return birthdate;
  }
  
  public void setBirthdate(String birthdate){
     this.birthdate = birthdate;
  }
  
  public String getName(){
     return name;
  }
  
  public void setName(String name){
     this.name = name;
  }
  
  public String getSurname(){
     return surname;
  }
  
  public void setSurname(String surname){
     this.surname = surname;
  }
  
}