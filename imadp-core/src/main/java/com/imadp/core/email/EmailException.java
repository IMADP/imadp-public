package com.imadp.core.email;


/**
 * EmailException
 *
 * The exception thrown in the case of being unable to send an Email.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class EmailException extends RuntimeException {

	// constructor
	public EmailException(String message) {
		super(message);
	}
	
	// constructor
	public EmailException(String message, Exception exception) {
		super(message, exception);
	}
	
}
