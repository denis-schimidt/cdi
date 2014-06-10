package com.schimidtsolutions.ejb;

import java.util.concurrent.atomic.AtomicInteger;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import com.schimidtsolutions.model.Contact;
import com.schimidtsolutions.model.User;

@Local( UserService.class ) 
@Stateful
@SessionScoped
public class UserBeanService implements UserService {
	private static final AtomicInteger ID_GENERATOR = new AtomicInteger();
	private User user;

	@Override
	public Integer addUser( User user ) {
		user.setId( ID_GENERATOR.incrementAndGet() );
		this.user = user;

		return user.getId();
	}

	@Override
	public void addContact( Contact contact ) {
		user.setContact( contact );
	}

	@Override
	public User getUser() {
		return user;
	}
}
