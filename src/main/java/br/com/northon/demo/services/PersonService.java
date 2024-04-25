package br.com.northon.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.northon.demo.model.Person;

@Service
public class PersonService {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public Person findById(String id) {
		
		logger.info("finding one person");
		
		Person person = new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirstName("Northon");
		person.setLastName("mor");
		person.setAddress("Osasco");
		person.setGender("male");
		
		return person;
	}
	
	public List<Person> findAll() {
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;
	}

	private Person mockPerson(int i) {
		Person person = new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person Name" + i);
		person.setLastName("Last name" + i);
		person.setAddress("Osasco" + i);
		person.setGender("male");
		
		return person;
	}
	
	public Person createPerson(Person person) {
		logger.info("Creating one person!");
		
		return person;
	}
	
	public Person updatePerson(Person person) {
		logger.info("Updating one person!");
		
		return person;
	}

	public void deletePerson(String id) {
		// TODO Auto-generated method stub
		logger.info("Deleting one person!");
	}
}
