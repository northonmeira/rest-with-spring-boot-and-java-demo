package br.com.northon.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.northon.demo.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
