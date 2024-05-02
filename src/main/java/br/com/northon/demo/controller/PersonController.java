package br.com.northon.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.northon.demo.data.vo.v1.PersonVO;
import br.com.northon.demo.services.PersonService;
import br.com.northon.demo.util.MediaType;

/**
 * GreetingsController
 */
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@GetMapping(value= "/{id}", produces = {MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public PersonVO findById(@PathVariable(value="id") Long id) throws Exception {
		
		return personService.findById(id);
		
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public List<PersonVO> findAll() {
		
		return personService.findAll();
		
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, consumes = {MediaType.APPLICATION_JSON, 
					MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public PersonVO createPerson(@RequestBody PersonVO person) {
		
		return personService.createPerson(person);
		
	}
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public PersonVO updatePerson(@RequestBody PersonVO person) {
		
		return personService.updatePerson(person);
		
	}
	
	
	@DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public ResponseEntity<?> deletePerson(@PathVariable Long id) {
		
		personService.deletePerson(id);
		
		return ResponseEntity.noContent().build();
	}
}