package com.imadp.core.validation;


/**
 * Validator
 *
 * Performs an act of validation on an object and returns the result of the validation.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface Validator {

	/**
	 * Executes an act of validation and returns the ValidationResult.
	 *
	 * @return ValidationResult
	 */
	public ValidationResult getValidationResult();

	/**
	 * Executes an act of validation and throws a ValidationException in the event of an invalid result.
	 *
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException;

}