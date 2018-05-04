package com.imadp.core.validation;

import java.util.List;


/**
 * MinLengthValidator
 * 
 * Ensures that an object has a valid minimum length.
 * 
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.invalidMinLength
 *       
 * @version 1.0
 * @author Anthony DePalma
 */
public class MinLengthValidator extends AbstractValidator<Boolean> {
	
	// key
	private static final String INVALID_MIN_LENGTH = "invalidMinLength";
	
	private Object[] parameters;
	
	// constructor
	public MinLengthValidator(String objectName, String object, int minLength) {
		super(objectName, object == null ? false : object.length() >= minLength);
		this.parameters = new Object[] {minLength};
	}

	// constructor
	public MinLengthValidator(String objectName, int object, int minLength) {
		super(objectName, object >= minLength);
		this.parameters = new Object[] {minLength};
	}

	// constructor
	public MinLengthValidator(String objectName, long object, long minLength) {
		super(objectName, object >= minLength);
		this.parameters = new Object[] {minLength};
	}

	// constructor 
	public MinLengthValidator(String objectName, double object, double minLength) {
		super(objectName, object >= minLength);
		this.parameters = new Object[] {minLength};
	}

	@Override
	protected void validate(String objectName, Boolean valid, List<ValidationFailure> failures) {
		if(!valid)
			failures.add(new ValidationFailure(join(objectName, INVALID_MIN_LENGTH), parameters, objectName));
	}
	
} 