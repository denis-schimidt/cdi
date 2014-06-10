package com.schimidtsolutions.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="PERSON")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Person{

	@Id
	@Column(name="PERSON_ID", nullable=false, updatable=false, insertable=true)
	@SequenceGenerator(name = "personSeq", sequenceName = "PERSON_SEQ", initialValue=1, allocationSize=2147483647)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="personSeq")
	private Integer id;
	
	@Pattern(regexp="[a-zA-Z]{5,}", message="Nome inválido!")
	@Column(name="PERSON_NAME", nullable=false, updatable=true, insertable=true)
	private String name;
	
	@Min(value=1) @Max(value=130)
	@Column(name="AGE", nullable=false, updatable=true, insertable=true)
	private Short age;
	
	// Uso apenas JPA.
	public Person() {}
	
	public Person(Integer id) {
		this.id = id;
	}

	public Person(String name, Short age) {
		this.name = name;
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Short getAge() {
		return age;
	}
}
