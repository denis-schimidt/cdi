package com.schimidtsolutions.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="PERSON")
public class Person {

	@Id
	@Column(name="PERSON_ID", nullable=false, updatable=false, unique=true)
	@SequenceGenerator( name = "personSeq", sequenceName = "PERSON_SEQ")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="personSeq")
	private Integer id;
	
	@Pattern(regexp="[a-zA-Z]{5,}", message="Nome inv�lido!")
	@Column(name="PERSON_NAME", nullable=false, updatable=true, insertable=true)
	private String name;
	
	@Pattern(regexp="[0-130]", message="Idade inv�lida!")
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
