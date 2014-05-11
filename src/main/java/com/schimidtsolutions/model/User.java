package com.schimidtsolutions.model;

import java.io.Serializable;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@FormParam("name")
	private String name;
	
	@FormParam("idade")
	private int idade;
	
	private Contact contact;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, idade=%s, contact=%s]", id,
				name, idade, contact);
	}
}
