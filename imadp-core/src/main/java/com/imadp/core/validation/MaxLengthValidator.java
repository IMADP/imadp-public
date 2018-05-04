package com.imadp.core.validation;

import java.util.List;


/**
 * MaxLengthValidator
 *
 * Ensures that an object has a valid maximum length.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class MaxLengthValidator extends AbstractValidator<Boolean> {

	// key
	private static final String INVALID_MAX_LENGTH = "invalidMaxLength";

	private Object[] parameters;

	// constructor
	public MaxLengthValidator(String objectName, String object, int maxLength) {
		super(objectName, object == null ? true : object.length() <= maxLength);
		this.parameters = new Object[] {maxLength};
	}

	// constructor
	public MaxLengthValidator(String objectName, int object, int maxLength) {
		super(objectName, object <= maxLength);
		this.parameters = new Object[] {maxLength};
	}

	// constructor
	public MaxLengthValidator(String objectName, long object, long maxLength) {
		super(objectName, object <= maxLength);
		this.parameters = new Object[] {maxLength};
	}

	// constructor
	public MaxLengthValidator(String objectName, double object, double maxLength) {
		super(objectName, object <= maxLength);
		this.parameters = new Object[] {maxLength};
	}

	@Override
	protected void validate(String objectName, Boolean valid, List<ValidationFailure> failures) {
		if(!valid)
			failures.add(new ValidationFailure(join(objectName, INVALID_MAX_LENGTH), parameters, objectName));
	}

}