package com.imadp.core.encryption;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * DigestionException
 *
 * The exception thrown in the case of being unable to encrypt a value.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class DigestionException extends RuntimeException {
	private String string;
	
	// constructor
	public DigestionException(String message, Exception exception, String string) {
		super(message, exception);
		this.string = string;
	}

	// getters and setters
	public String getUnencryptedString() {
		return string;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).
		appendSuper(super.toString()).
		append("string", string).
		toString();
	}
	
}
