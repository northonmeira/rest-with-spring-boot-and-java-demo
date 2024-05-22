package br.com.northon.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.northon.demo.data.vo.v1.PersonVO;
import br.com.northon.demo.services.PersonService;
import br.com.northon.demo.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for managing people")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@CrossOrigin(origins = {"http://localhost:8080"})
	@GetMapping(value= "/{id}", produces = {MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds a Person", description = "Finds a Person",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(schema = @Schema(implementation = PersonVO.class))
					}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public PersonVO findById(@PathVariable(value="id") Long id) throws Exception {
		
		return personService.findById(id);
		
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all people", description = "Finds all People",
			tags = {"People"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200", 
							content = {
									@Content(
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
											)
							}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			})
	public List<PersonVO> findAll() {
		return personService.findAll();
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, consumes = {MediaType.APPLICATION_JSON, 
					MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Create a Person", description = "Create a Person",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(schema = @Schema(implementation = PersonVO.class))
					}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		})
	public PersonVO createPerson(@RequestBody PersonVO person) {
		
		return personService.createPerson(person);
		
	}
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Updates a Person", description = "Updates a Person",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(schema = @Schema(implementation = PersonVO.class))
					}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		})
	public PersonVO updatePerson(@RequestBody PersonVO person) {
		
		return personService.updatePerson(person);
		
	}
	
	
	@DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Deletes a Person", description = "Deletes a Person",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		})
	public ResponseEntity<?> deletePerson(@PathVariable Long id) {
		
		personService.deletePerson(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(value= "/{id}", produces = {MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Disable a Person by Id", description = "Disable a Person by Id",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(schema = @Schema(implementation = PersonVO.class))
					}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public PersonVO disablePerson(@PathVariable(value="id") Long id) throws Exception {
		
		return personService.disablePerson(id);
		
	}
	
}