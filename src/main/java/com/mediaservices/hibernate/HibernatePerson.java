package com.mediaservices.hibernate;

import com.mediaservices.model.Person;
import com.mediaservices.model.Role;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;


public class HibernatePerson {

	private static HibernatePerson uniqueInstance;

	private HibernatePerson() {
	}

	public static HibernatePerson getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new HibernatePerson();
		}
		return uniqueInstance;
	}

	public Person getPersonById(int id) {
		return (Person)  HibernateUtil.getOpenedSession().get(Person.class, id);
	}

	public boolean createPersonOnDb(String name, String street, String city,
			String pinCode) {
		boolean done = false;
		Logger logger = Logger.getLogger("LoggerTest");
		try {
			Person person = new Person();
			person.setName(name);
			/*
			 * Address address = new Address(); address.setStreet(street);
			 * address.setPinCode(pinCode); address.setCity(city);
			 * 
			 * person.getListOfAddress().add(address);
			 * 
			 * 
			 * Address address2 = new Address();
			 * address.setStreet("calle2-456");
			 * address.setPinCode("pinCode2-456"); address.setCity("city2-456");
			 * person.getListOfAddress().add(address2);
			 * 
			 * com.mediaservices.ws.model.Vehicle vehicle1 = new
			 * com.mediaservices.ws.model.Vehicle();
			 * vehicle1.setName("Car-456"); vehicle1.getPeople().add(person);
			 * person.getVehicles().add(vehicle1);
			 * 
			 * com.mediaservices.ws.model.TwoWheeler vehicle2 = new
			 * com.mediaservices.ws.model.TwoWheeler();
			 * vehicle2.setName("Bike-456");
			 * vehicle2.setSteeringHandle("No se que steeringHandle");
			 * 
			 * com.mediaservices.ws.model.FourWheeler vehicle3 = new
			 * com.mediaservices.ws.model.FourWheeler();
			 * vehicle3.setName("Porsche-456");
			 * vehicle3.setSteeringWheel("No se que steeringWheel");
			 */

			// vehicle2.getPeople().add(person);

			HibernateUtil.getOpenedSession().beginTransaction();

			// getOpenedSession().save(person);
			HibernateUtil.getOpenedSession().persist(person);
			// inheritance=single table strategy
			// getOpenedSession().save(vehicle1);
			// getOpenedSession().save(vehicle2);
			// getOpenedSession().save(vehicle1);
			// getOpenedSession().save(vehicle2);
			// getOpenedSession().save(vehicle3);

			HibernateUtil.getOpenedSession().getTransaction().commit();
			HibernateUtil.getOpenedSession().close();
			done = true;
			logger.info("person " + person.getName() + " saved!");
		} catch (Exception e) {
			System.out.println(e.toString());
			logger.warning(e.toString());
		}
		return done;
	}

	public String savePerson(String name, String surname, String nickname,
			Date dateOfBirth, String imgUrl, String roleName) {
		String done = "not done";
		try {
			Person person = new Person();
			person.setName(name);
			person.setNickname(nickname);
			person.setSurname(surname);
			person.setDateOfBirth(dateOfBirth);
			person.setImgUrl(imgUrl);
			Role role = new Role();
			role.setName(roleName);
			person.getRoles().add(role);

			Session session = HibernateUtil.getOpenedSession();
			session.beginTransaction();
			session.save(person);
			session.getTransaction().commit();
			session.close();
			done = "done";
		} catch (Exception e) {
			done= e.toString();
			System.out.println(e.toString());
		}
		return done;
	}
	
/*
	public List<Person> getAllPersons(int pageNumber, int pageAmount) {
		List<Person> persons;
		Session session = HibernateUtil.getOpenedSession();
		session.beginTransaction();

		Query query = session.createQuery("from Persons");
		if ((pageAmount > 0) && (pageNumber > 0)) {
			query.setFirstResult(pageAmount * pageNumber);
			query.setMaxResults(pageAmount);
		}

		persons = (List<Person>) query.list();
		session.getTransaction().commit();
		session.close();
		return persons;
	}

	public List<String> getPersonsNames(int pageNumber, int pageAmount) {
		List<String> personsNames;
		Session session = HibernateUtil.getOpenedSession();
		session.beginTransaction();

		Query query = session.createQuery("select personName from Persons");
		if ((pageAmount > 0) && (pageNumber > 0)) {
			query.setFirstResult(pageAmount * pageNumber);
			query.setMaxResults(pageAmount);
		}

		personsNames = (List<String>) query.list();
		session.getTransaction().commit();
		session.close();
		return personsNames;
	}

	public List<Map<Integer, String>> getPersonsNamesAndIds(int pageNumber,
			int pageAmount) {
		List<Map<Integer, String>> personsInfo;
		Session session = HibernateUtil.getOpenedSession();
		session.beginTransaction();

		Query query = session
				.createQuery("select new map(personId, personName) from Persons");
		if ((pageAmount > 0) && (pageNumber > 0)) {
			query.setFirstResult(pageAmount * pageNumber);
			query.setMaxResults(pageAmount);
		}

		personsInfo = (List<Map<Integer, String>>) query.list();
		session.getTransaction().commit();
		session.close();
		return personsInfo;
	}

	public Person getLastPerson() {
		Person person;
		Session session = HibernateUtil.getOpenedSession();
		session.beginTransaction();

		Query query = session.createQuery("SELECT MAX(personId) from PERSONS");
		person = (Person) query.uniqueResult();

		session.getTransaction().commit();
		session.close();
		return person;
	}

	public List<Person> getPersonsWith(String minPersonId, String personName) {
		List<Person> person;
		Session session = HibernateUtil.getOpenedSession();
		session.beginTransaction();
*/
		// A BAD practice, APPENDING PARAMETERS (can cause SQL Injection):
		// Example
		// String minPersonId_withSQLinjection = "1 OR 1 = 1"; //example where
		// we bypass the where clause and got all the records
		// Query query = session.createQuery("from PERSONS where personId > "+
		// minPersonId_withSQLinjection);

		// A GOOD practice, using PARAMETER SUBSTITUTION: Example

		/* OPTION 1 of Using parameters on HQL querys :: Positional parameters */
		/*
		 * Query query = session.createQuery(
		 * "from PERSONS where personId > ? and userPersonName = ?");
		 * query.setInteger(0, Integer.parseInt(minPersonId));
		 * query.setString(1, personName);
		 */

		/* OPTION 2 of Using parameters on HQL querys :: Named parameters */
/*
		Query query = session
				.createQuery("from PERSONS where personId > :personId and personName = :personName");
		query.setInteger("personId", Integer.parseInt(minPersonId));
		query.setString("personName", personName);

		person = (List<Person>) query.list();
		session.getTransaction().commit();
		session.close();
		return person;
	}

*/

}
