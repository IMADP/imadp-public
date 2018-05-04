package com.imadp.core.validation;

import java.util.List;


/**
 * RangeValidator
 *
 * Ensures that an object has a valid minimum length and valid maximum length.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.invalidRange
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RangeValidator extends AbstractValidator<Boolean> {

	// key
	private static final String INVALID_RANGE = "invalidRange";

	private Object[] parameters;

	// constructor
	public RangeValidator(String objectName, String object, int minLength, int maxLength) {
		super(objectName, object == null ? false : object.length() >= minLength && object.length() <= maxLength);
		this.parameters = new Object[] {minLength, maxLength};
	}

	// constructor
	public RangeValidator(String objectName, int object, int minLength, int maxLength) {
		super(objectName, object >= minLength && object <= maxLength);
		this.parameters = new Object[] {minLength, maxLength};
	}

	// constructor
	public RangeValidator(String objectName, long object, long minLength, long maxLength) {
		super(objectName, object >= minLength && object <= maxLength);
		this.parameters = new Object[] {minLength, maxLength};
	}

	// constructor
	public RangeValidator(String objectName, double object, double minLength, double maxLength) {
		super(objectName, object >= minLength && object <= maxLength);
		this.parameters = new Object[] {minLength, maxLength};
	}

	@Override
	protected void validate(String objectName, Boolean valid, List<ValidationFailure> failures) {
		if(!valid)
			failures.add(new ValidationFailure(join(objectName, INVALID_RANGE), parameters, objectName));
	}

}