package br.com.northon.demo.integrationtests.vo.wrappers;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.northon.demo.integrationtests.vo.PersonVO;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PersonEmbeddedVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("personVOList")
	private List<PersonVO> persons;

	public PersonEmbeddedVO() {
		super();
	}

	public List<PersonVO> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonVO> persons) {
		this.persons = persons;
	}

	@Override
	public int hashCode() {
		return Objects.hash(persons);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonEmbeddedVO other = (PersonEmbeddedVO) obj;
		return Objects.equals(persons, other.persons);
	}

	
}
