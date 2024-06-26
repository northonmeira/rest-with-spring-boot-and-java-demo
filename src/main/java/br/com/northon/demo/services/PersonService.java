package br.com.northon.demo.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import br.com.northon.demo.controller.PersonController;
import br.com.northon.demo.data.vo.v1.PersonVO;
import br.com.northon.demo.exception.RequiredObjectIsNullException;
import br.com.northon.demo.exceptions.ResourceNotFoundException;
import br.com.northon.demo.mapper.DozerMapper;
import br.com.northon.demo.model.Person;
import br.com.northon.demo.repositorys.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PagedResourcesAssembler<PersonVO> assembler;
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public PersonVO findById(Long id) throws Exception {
		
		logger.info("finding one person");

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));
		
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		
		return vo;
	}
	
	public PagedModel<EntityModel<PersonVO>> findAll(Pageable pageable) {
		
		logger.info("finding all people");
		
		var personPage = personRepository.findAll(pageable);

		var personVosPage = personPage.map(p -> DozerMapper.parseObject(p, PersonVO.class));
		
		personVosPage.stream().forEach(
				p -> {
					try {
						p.add(linkTo(methodOn(PersonController.class)
								.findById(p.getKey())).withSelfRel());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
		
		Link link = linkTo(
				methodOn(PersonController.class).findAll(
						pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
		
		return assembler.toModel(personVosPage, link);
	}

	public PagedModel<EntityModel<PersonVO>> findPersonsByName(String firstName, Pageable pageable) {
		
		logger.info("finding people by name");
		
		var personPage = personRepository.findPersonsByName(firstName, pageable);

		var personVosPage = personPage.map(p -> DozerMapper.parseObject(p, PersonVO.class));
		
		personVosPage.stream().forEach(
				p -> {
					try {
						p.add(linkTo(methodOn(PersonController.class)
								.findById(p.getKey())).withSelfRel());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
		
		Link link = linkTo(
				methodOn(PersonController.class).findAll(
						pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
		
		return assembler.toModel(personVosPage, link);
	}
	
	public PersonVO createPerson(PersonVO personVO) {
		
		if(personVO == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one person!");
		
		var entity = DozerMapper.parseObject(personVO, Person.class);
		
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).createPerson(personVO)).withSelfRel());
		
		return vo;
	}
	
	public PersonVO updatePerson(PersonVO personVO) {

		if(personVO == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating one person!");
		
		Person entity = personRepository.findById(personVO.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!")); 
		
		entity.setFirstName(personVO.getFirstName());
		entity.setLastName(personVO.getLastName());
		entity.setAddress(personVO.getAddress());
		entity.setGender(personVO.getGender());
		
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).updatePerson(personVO)).withSelfRel());
		
		return vo;
	}
	
	@Transactional
	public PersonVO disablePerson(Long id) throws Exception {
		
		logger.info("disabling one person");
		
		personRepository.disablePerson(id);

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));
		
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
	
		return vo;
	}

	public void deletePerson(Long id) {
		
		logger.info("Deleting one person!");
		
		Person entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!")); 
		
		personRepository.delete(entity);
		
	}
}
