package com.imadp.core.validation;

import java.util.List;

import org.apache.commons.validator.GenericValidator;


/**
 * ExpressionValidator
 * 
 * Ensures that the object is validated against a regular expression.
 * 
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.invalidExpression
 *       
 * @version 1.0
 * @author Anthony DePalma
 */
public class ExpressionValidator extends AbstractValidator<String> {
	
	// key 
	private static final String INVALID_EXPRESSION = "invalidExpression";
		
	// properties
	private String expression;
	
	// constructor
	public ExpressionValidator(String objectName, String object, String expression) {
		super(objectName, object);
		this.expression = expression;
	}

	@Override
	protected void validate(String objectName, String object, List<ValidationFailure> failures) {
		if(!GenericValidator.matchRegexp(object, expression))
			failures.add(new ValidationFailure(join(objectName, INVALID_EXPRESSION), objectName));
	}
	
}