package com.mediaservices.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	   private String street;
	   private String city;
	   private String pinCode;
	   
	   
	   public String getCity()
	   {
	      return city;
	   }
	   public void setCity(String city)
	   {
	      this.city = city;
	   }
	   public String getPinCode()
	   {
	      return pinCode;
	   }
	   public void setPinCode(String pinCode)
	   {
	      this.pinCode = pinCode;
	   }
	   public String getStreet()
	   {
	      return street;
	   }
	   public void setStreet(String street)
	   {
	      this.street = street;
	   }
	   
}
