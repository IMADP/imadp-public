package com.imadp.core.template;


/**
 * TemplateException
 *
 * The exception thrown in the case of being unable construct a template.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TemplateException extends RuntimeException {

	// constructor
	public TemplateException(String message) {
		super(message);
	}
	
	// constructor
	// constructor
	public TemplateException(Exception exception) {
		super(exception);
	}
	
	// constructor
	public TemplateException(String message, Exception exception) {
		super(message, exception);
	}
	
}
