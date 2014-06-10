package com.schimidtsolutions.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="person")
public class PersonDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@XmlAttribute(name="id")
	private Integer id;
	private String name;
	private Short age;
	
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
