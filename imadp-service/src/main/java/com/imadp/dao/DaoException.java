package com.imadp.dao;



/**
 * DaoException
 *
 * The exception thrown in the case of a data access exception.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class DaoException extends RuntimeException {
	
	// constructor
	public DaoException(String message, Exception exception) {
		super(message, exception);
	}
	
}
