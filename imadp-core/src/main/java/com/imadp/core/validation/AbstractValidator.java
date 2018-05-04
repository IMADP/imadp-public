package com.imadp.core.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.imadp.core.Property;


/**
 * AbstractValidator
 *
 * Holds common functionality for Validator implementations.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractValidator<T> implements Validator {
	private T object;
	private String objectName;
	private List<ValidationFailure> failures;

	// constructor
	public AbstractValidator(T object) {
		this(null, object);
	}

	// constructor
	public AbstractValidator(String objectName, T object) {
		this.object = object;
		this.objectName = objectName;
	}

	@Override
	public final ValidationResult getValidationResult() {
		this.failures = new ArrayList<>();
		validate(objectName, object, failures);
		return failures.isEmpty() ? ValidationResult.VALID : new ValidationResult(failures);
	}

	@Override
	public final void validate() {
		ValidationResult validationResult = getValidationResult();

		if(!validationResult.isValid())
			throw new ValidationException(validationResult);
	}

	/**
	 * Validates an object.
	 *
	 * @param object
	 * @param objectName
	 * @param validationFailures
	 */
	protected abstract void validate(String objectName, T object, List<ValidationFailure> validationFailures);

	/**
	 * Adds a validation by executing the given validator and adding any failures to the list of ValidationFailures.
	 * The method returns true if the validation was valid, false otherwise.
	 *
	 * @param validator
	 * @return boolean
	 */
	protected final boolean addValidationResult(Validator validator) {
		return addValidationResult(validator.getValidationResult());
	}

	/**
	 * Adds a validation result by adding any failures to the list of ValidationFailures.
	 * The method returns true if the validation was valid, false otherwise.
	 *
	 * @param validationResult
	 * @return boolean
	 */
	protected final boolean addValidationResult(ValidationResult validationResult) {
		if(!validationResult.isValid())
			failures.addAll(validationResult.getFailures());

		return validationResult.isValid();
	}

	/**
	 * Joins a string together with a property name, delimited by a period.
	 *
	 * @param strings
	 * @return String
	 */
	protected final String join(String string, Property<?, ?> property) {
		return join(string, property.getName());
	}

	/**
	 * Joins multiple strings together, delimited by a period.
	 *
	 * @param strings
	 * @return String
	 */
	protected final String join(String...strings) {
		return StringUtils.join(strings, '.');
	}

}