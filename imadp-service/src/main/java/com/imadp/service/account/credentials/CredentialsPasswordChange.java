package com.imadp.service.account.credentials;

import com.imadp.core.AbstractSerializable;
import com.imadp.core.encryption.Digestor;
import com.imadp.service.user.User;

/**
 * CredentialsPasswordChange
 * 
 * Object holding the fields necessary to change a user's password.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CredentialsPasswordChange extends AbstractSerializable {
	private User user;
	private String oldPassword;
	private CredentialsPassword credentialsPassword;
	
	// constructor
	public CredentialsPasswordChange() {
		
	}
	
	/**
	 * Returns true if the old password is valid, false otherwise.
	 * 
	 * @param digestor
	 * @param credentials
	 * @return boolean
	 */
	boolean isOldPasswordValid(Digestor digestor, Credentials credentials) {
		return digestor.isEqualDigest(oldPassword, credentials.getPassword());
	}

	// getters and setters
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public CredentialsPassword getCredentialsPassword() {
		return credentialsPassword;
	}

	public void setCredentialsPassword(CredentialsPassword credentialsPassword) {
		this.credentialsPassword = credentialsPassword;
	}
	
}