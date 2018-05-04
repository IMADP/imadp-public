package com.imadp.core.encryption;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * EncryptionException
 *
 * The exception thrown in the case of being unable to encrypt a value.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class EncryptionException extends RuntimeException {
	private String unencryptedString;
	
	// constructor
	public EncryptionException(String message, Exception exception, String unencryptedString) {
		super(message, exception);
		this.unencryptedString = unencryptedString;
	}

	// getters and setters
	public String getUnencryptedString() {
		return unencryptedString;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).
		appendSuper(super.toString()).
		append("unencryptedString", unencryptedString).
		toString();
	}
	
}
