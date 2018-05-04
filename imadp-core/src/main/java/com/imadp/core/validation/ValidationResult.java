package com.imadp.core.validation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.imadp.core.AbstractSerializable;


/**
 * ValidationResult
 * 
 * Holds the result of a validation performed by a validator.
 * 
 * @note This class is immutable and thread-safe.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class ValidationResult extends AbstractSerializable {
	
	// static valid result
	public static final ValidationResult VALID = new ValidationResult();
	
	// validation failures
	private final List<ValidationFailure> failures;	

	// constructor
	private ValidationResult() {
		this.failures = Collections.emptyList();
	}

	// constructor
	public ValidationResult(String key, String... objectNames) {
		this(Arrays.asList(new ValidationFailure(key, objectNames)));
	}
	
	// constructor
	public ValidationResult(String key, Object[] parameters, String... objectNames) {
		this(Arrays.asList(new ValidationFailure(key, parameters, objectNames)));
	}
	
	// constructor
	public ValidationResult(List<ValidationFailure> failures) {
		this.failures = Collections.unmodifiableList(failures);
	}
	
	/**
	 * Returns true if the validation result is successful and contains no failures, false otherwise.
	 * 
	 * @return boolean
	 */
	public boolean isValid() {
		return failures.isEmpty();
	}

	/**
	 * Returns the count of the number of validation failures for this validation result.
	 * 
	 * @return int
	 */
	public int getFailureCount() {
		return failures.size();
	}
	
	/**
	 * Returns true if the validation result contains the failure matching the given key, false otherwise.
	 *  
	 * @param key
	 * @return boolean
	 */
	public boolean hasFailure(String key) {
		for(ValidationFailure failure : failures)
			if(failure.getKey().equals(key))
				return true;
		
		return false;
	}
	
	// getters
	public List<ValidationFailure> getFailures() {
		return failures;
	}

}