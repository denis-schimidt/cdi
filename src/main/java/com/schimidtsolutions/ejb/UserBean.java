package com.schimidtsolutions.ejb;

import javax.ejb.Local;

import com.schimidtsolutions.model.Contact;
import com.schimidtsolutions.model.User;

@Local
public interface UserBean {
	public Integer addUser( User user );
	
	public void addContact( Contact contact );
	
	public User getUser();
}
