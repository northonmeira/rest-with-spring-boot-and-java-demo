package br.com.northon.demo.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.northon.demo.data.vo.v1.PersonVO;
import br.com.northon.demo.exception.ResourceNotFoundException;
import br.com.northon.demo.mapper.DozerMapper;
import br.com.northon.demo.model.Person;
import br.com.northon.demo.repositorys.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public PersonVO findById(Long id) throws Exception {
		
		logger.info("finding one person");

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public List<PersonVO> findAll() {

		return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
	}

	
	public PersonVO createPerson(PersonVO personVO) {
		logger.info("Creating one person!");
		
		var entity = DozerMapper.parseObject(personVO, Person.class);
		
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		
		return vo;
	}
	
	public PersonVO updatePerson(PersonVO personVO) {
		logger.info("Updating one person!");
		Person entity = personRepository.findById(personVO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!")); 
		
		entity.setFirstName(personVO.getFirstName());
		entity.setLastName(personVO.getLastName());
		entity.setAddress(personVO.getAddress());
		entity.setGender(personVO.getGender());
		
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		
		return vo;
	}

	public void deletePerson(Long id) {
		logger.info("Deleting one person!");
		
		Person entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!")); 
		
		personRepository.delete(entity);
		
	}
}
