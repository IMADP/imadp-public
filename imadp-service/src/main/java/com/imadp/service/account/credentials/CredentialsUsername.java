package com.imadp.service.account.credentials;

import com.imadp.core.AbstractSerializable;
import com.imadp.service.user.User;

/**
 * CredentialsPassword
 * 
 * Object holding the fields required for a credentials username.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CredentialsUsername extends AbstractSerializable {
	private String username;
	private User user;
	
	// constructor
	public CredentialsUsername() {
		
	}

	// getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}