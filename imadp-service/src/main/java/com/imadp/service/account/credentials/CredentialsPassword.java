package com.imadp.service.account.credentials;

import com.imadp.core.AbstractSerializable;

/**
 * CredentialsPassword
 * 
 * Object holding the fields required for a credentials password.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CredentialsPassword extends AbstractSerializable {
	private String password;
	private String confirmPassword;
	
	// constructor
	public CredentialsPassword() {
		
	}

	/**
	 * Returns true if the password is confirmed.
	 * 
	 * @return boolean
	 */
	public boolean isPasswordConfirmed() {
		return password.equals(confirmPassword);
	}
	
	// getters and setters
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}	
	
}