package com.imadp.service.account.credentials;

import com.imadp.core.AbstractSerializable;
import com.imadp.service.user.User;

/**
 * CredentialsEmail
 * 
 * Object holding the fields required for a credentials email.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CredentialsEmail extends AbstractSerializable {
	private String email;
	private User user;
	
	// constructor
	public CredentialsEmail() {
		
	}

	// getters and setters	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}