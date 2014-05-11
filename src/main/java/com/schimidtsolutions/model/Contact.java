package com.schimidtsolutions.model;

import java.io.Serializable;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@FormParam("email")
	private String email;
	
	@FormParam("ddd")
	private Short ddd;
	
	@FormParam("phone")
	private Long phone;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Short getDdd() {
		return ddd;
	}
	public void setDdd(Short ddd) {
		this.ddd = ddd;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return String.format("Contact [email=%s, ddd=%s, phone=%s]",
				email, ddd, phone);
	}
}
