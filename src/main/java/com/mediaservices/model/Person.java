package com.mediaservices.model;


import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name="PERSONS")
public class Person {
    @Id @GeneratedValue
    private int personId;
    private String name;
    private String surname;
    private String nickname;
    private Date dateOfBirth;
    private String imgUrl;
    private Role role;

    @ElementCollection
    @JoinTable(name="PERSON_ADDRESS",
          joinColumns=@JoinColumn(name="PERSON_ID")
      )
    @GenericGenerator(name="hilo-gen", strategy="hilo")
    @CollectionId(columns = { @Column(name="ADDRESS_ID") }, generator = "hilo-gen", type = @Type(type="long"))
    private Collection<Address> listOfAddress = new ArrayList();
/*
    @ManyToMany (cascade=CascadeType.ALL)
    private Collection<Vehicle> vehicles = new ArrayList<Vehicle>();
*/
    
   public String getName(){
      return name;
   }

   public void setName(String name){
      this.name = name;
   }

   public Collection<Address> getListOfAddress() {
      return listOfAddress;
   }
   
   public void setListOfAddress(Collection<Address> listOfAddress) {
      this.listOfAddress = listOfAddress;
   }

   public int getPersonId(){
      return personId;
   }

   public void setPersonId(int personId){
      this.personId = personId;
   }
   
/*   
   public Collection<Vehicle> getVehicles()
   {
      return vehicles;
   }

   public void setVehicles(Collection<Vehicle> vehicles)
   {
      this.vehicles = vehicles;
   }
*/

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String showInfo() {
		String info="No Info";
		if (getPersonId()>=0){
			info="El ID de esta persona es "+getPersonId()+"<br>";
		}
		if (getName() != null && getName().length()>0){
			info +="El NOMBRE de esta persona es "+getName().toUpperCase()+"<br>";
		}
		for (Address address : getListOfAddress()) {
			info+="<br>Ciudad: "+address.getCity()+"<br>PinCode: "+address.getPinCode()+"<br>Street: "+address.getStreet()+"<br>----<br>";
		}
		return info;
	}

	
}
