package com.mediaservices.hibernate;

import com.mediaservices.model.Person;
import java.util.logging.Logger;
import com.mediaservices.hibernate.HibernatePerson;

public class HibernatePerson {

	private static HibernatePerson uniqueInstance;
	  
	private HibernatePerson(){ }
	  
	public static HibernatePerson getInstance(){
	   if (uniqueInstance == null ) {
	      uniqueInstance = new HibernatePerson();
	   }
	   return uniqueInstance;
	}
	
	public Person getPersonById(int id){
	     return (Person) HibernateUtil.getOpenedSession().get(Person.class, id);
	}
	   
	public boolean createPersonOnDb( String name, String street, String city, String pinCode){
	     boolean done = false;
	     Logger logger = Logger.getLogger("LoggerTest");
	     try{
	       Person person = new Person();
	       person.setName(name);
/*	       Address address = new Address();
	       address.setStreet(street);
	       address.setPinCode(pinCode);
	       address.setCity(city);

	       person.getListOfAddress().add(address);

	       
	       Address address2 = new Address();
	       address.setStreet("calle2-456");
	       address.setPinCode("pinCode2-456");
	       address.setCity("city2-456");
	       person.getListOfAddress().add(address2);
	       
	       com.mediaservices.ws.model.Vehicle vehicle1 = new com.mediaservices.ws.model.Vehicle();
	       vehicle1.setName("Car-456");
	       vehicle1.getPeople().add(person);
	       person.getVehicles().add(vehicle1);
	       
	       com.mediaservices.ws.model.TwoWheeler vehicle2 = new com.mediaservices.ws.model.TwoWheeler();
	       vehicle2.setName("Bike-456");
	       vehicle2.setSteeringHandle("No se que steeringHandle");
	       
	       com.mediaservices.ws.model.FourWheeler vehicle3 = new com.mediaservices.ws.model.FourWheeler();
	       vehicle3.setName("Porsche-456");
	       vehicle3.setSteeringWheel("No se que steeringWheel");
	       
*/	       
	       //vehicle2.getPeople().add(person);
	       
	       
	       HibernateUtil.getOpenedSession().beginTransaction();
	       
	       
	       //getOpenedSession().save(person);
	       HibernateUtil.getOpenedSession().persist(person);
	       //inheritance=single table strategy 
	       //getOpenedSession().save(vehicle1);
	       //getOpenedSession().save(vehicle2);
	       //getOpenedSession().save(vehicle1);
	       //getOpenedSession().save(vehicle2);
	       //getOpenedSession().save(vehicle3);
	       
	       HibernateUtil.getOpenedSession().getTransaction().commit();
	       HibernateUtil.getOpenedSession().close();
	       done=true;
	       logger.info("person "+person.getName()+" saved!");
	     }catch(Exception e){
	       System.out.println(e.toString());
	       logger.warning(e.toString());
	     }
	     return done;
	}
	
}
