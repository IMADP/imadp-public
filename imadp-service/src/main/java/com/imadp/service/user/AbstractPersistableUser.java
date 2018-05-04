package com.imadp.service.user;

import com.imadp.core.Property;
import com.imadp.dao.AbstractPersistable;

/**
 * AbstractPersistableUser
 *
 * An extention of the AbstractPersistable class which provides a User reference
 * and all of its delegated methods.	
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractPersistableUser extends AbstractPersistable {
	
	// static Properties
	public static final Property<AbstractPersistableUser, User> USER = Property.of("user");
	
	/*
	 * The User reference, which may be null.
	 * 
	 */
	protected User user;
	
	// constructor
	public AbstractPersistableUser() {
		
	}
	
	// constructor
	public AbstractPersistableUser(User user) {
		this.user = user;
	}
	
	/**
	 * Returns true if user is present, false otherwise.
	 * 
	 * @return boolean
	 */
	public boolean hasUser() {
		return user != null;
	}
	
	/**
	 * Returns the id of the user, or <code>null</code> if none is present.
	 * 
	 * @return Long
	 */
	public Long getUserId() {
		return hasUser() ? user.getId() : null;
	}	

	// getters and setters
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}			
	
}