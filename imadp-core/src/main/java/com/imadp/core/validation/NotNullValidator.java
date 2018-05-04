package com.imadp.core.validation;

import java.util.List;

import org.apache.commons.lang.StringUtils;


/**
 * NotNullValidator
 *
 * Ensures that the validated object is not null.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.invalidNull
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class NotNullValidator extends AbstractValidator<Object> {

	// key
	private static final String INVALID_NULL = "invalidNull";

	/**
	 * Returns a ValidationResult with a single ValidationFailure for the supplied objectName.
	 * This is a convenience method meant to be used when a parent object is known to be null, yet validation
	 * errors need to be generated for individual objectNames.
	 *
	 * @param objectName
	 * @return ValidationResult
	 */
	public static final ValidationResult invalidNull(String objectName) {
		return new ValidationResult(StringUtils.join(new String[] {objectName, INVALID_NULL}, '.'), objectName);
	}

	// constructor
	public NotNullValidator(String objectName, Object object) {
		super(objectName, object);
	}

	@Override
	protected void validate(String objectName, Object object, List<ValidationFailure> failures) {
		if(object == null || (object instanceof String && StringUtils.isBlank((String)object)))
			failures.add(new ValidationFailure(join(objectName, INVALID_NULL), objectName));
	}

}