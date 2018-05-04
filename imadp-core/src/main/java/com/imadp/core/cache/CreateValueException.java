package com.imadp.core.cache;


/**
 * CreateValueException
 *
 * The exception thrown when unable to create a value via the ICreateValue implementation.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CreateValueException extends RuntimeException {

	// constructor
	public CreateValueException(Exception exception) {
		super(exception);
	}
	
}
