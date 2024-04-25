package br.com.northon.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.northon.demo.model.Person;
import br.com.northon.demo.services.PersonService;

/**
 * GreetingsController
 */
@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@GetMapping("/{id}")
	public Person findById(@PathVariable(value="id") String id) {
		
		return personService.findById(id);
		
	}
	
	@GetMapping("/all")
	public List<Person> findAll() {
		
		return personService.findAll();
		
	}
	
	@PostMapping()
	public Person createPerson(@RequestBody Person person) {
		
		return personService.createPerson(person);
		
	}

	@PutMapping()
	public Person updatePerson(@RequestBody Person person) {
		
		return personService.updatePerson(person);
		
	}
	
	
	@DeleteMapping("/{id}")
	public void deletePerson(@PathVariable String id) {
		
		personService.deletePerson(id);
		
	}
}