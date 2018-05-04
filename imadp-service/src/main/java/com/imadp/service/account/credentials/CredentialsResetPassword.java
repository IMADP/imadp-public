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
public class CredentialsResetPassword extends AbstractSerializable {
	private String email;

	// constructor
	public CredentialsResetPassword() {

	}

	// getters and setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}