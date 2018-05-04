package com.imadp.core.encryption;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * DecryptionException
 *
 * The exception thrown in the case of being unable to decrypt a value.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class DecryptionException extends RuntimeException {
	private String encryptedString;

	// constructor
	public DecryptionException(String message, Exception exception, String encryptedString) {
		super(message, exception);
		this.encryptedString = encryptedString;
	}

	// getters and setters
	public String getEncryptedString() {
		return encryptedString;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).
		appendSuper(super.toString()).
		append("encryptedString", encryptedString).
		toString();
	}
	
}
