package com.imadp.core.validation;


/**
 * ValidationException
 *
 * The exception thrown when validation errors occur.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ValidationException extends RuntimeException {
	private final ValidationResult validationResult;

	// constructor
	public ValidationException(ValidationResult validationResult) {
		this.validationResult = validationResult;
	}

	// getters
	public ValidationResult getValidationResult() {
		return validationResult;
	}

}
