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

/**
 * GreetingsController
 */
@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@GetMapping("/{id}")
	public PersonVO findById(@PathVariable(value="id") Long id) throws Exception {
		
		return personService.findById(id);
		
	}
	
	@GetMapping
	public List<PersonVO> findAll() {
		
		return personService.findAll();
		
	}
	
	@PostMapping()
	public PersonVO createPerson(@RequestBody PersonVO person) {
		
		return personService.createPerson(person);
		
	}

	@PutMapping()
	public PersonVO updatePerson(@RequestBody PersonVO person) {
		
		return personService.updatePerson(person);
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable Long id) {
		
		personService.deletePerson(id);
		
		return ResponseEntity.noContent().build();
	}
}