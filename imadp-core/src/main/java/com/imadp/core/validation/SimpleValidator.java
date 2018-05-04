package com.imadp.core.validation;

import java.util.List;


/**
 * SimpleValidator
 * 
 * The simple validator simply asserts that the given condition is true.
 * This is useful when chaining validators or using quick stand alone validations that don't need heavy processing.
 * 
 * Keep in mind however, that the condition will be executed before the validator is invoked. That means that the
 * SimpleValidator should NOT be used when an expensive validation operation needs to be executed towards the
 * end of a chain of validations. An anonymous subclass of the AbstractValidator should be used for that scenario.
 * 
 * @note ValidationFailures are identified via the supplied failure key.
 *       
 * @version 1.0
 * @author Anthony DePalma
 */
public class SimpleValidator extends AbstractValidator<Boolean> {
	private String key;
	private String[] objectNames;
	private Object[] parameters;
	
	// constructor
	public SimpleValidator(boolean valid, String key, String... objectNames) {
		this(valid, key, null, objectNames);
	}
	
	// constructor
	public SimpleValidator(boolean valid, String key, Object[] parameters, String... objectNames) {
		super(valid);
		this.key = key;
		this.parameters = parameters;
		this.objectNames = objectNames;
	} 
	
	@Override
	protected void validate(String objectName, Boolean valid, List<ValidationFailure> failures) {
		if(!valid)
			failures.add(new ValidationFailure(key, parameters, objectNames));
	}
	
}