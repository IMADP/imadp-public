package com.imadp.core.email;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;



/**
 * EmailValidator
 * 
 * Ensures that the string is a valid email.
 * 
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.invalidNull
 *       {objectName}.invalidEmail
 *       
 * @version 1.0
 * @author Anthony DePalma
 */
public class EmailValidator extends AbstractValidator<String> {
	
	// key
	private static final String INVALID_EMAIL = "invalidEmail";
	
	// constructor
	public EmailValidator(String objectName, String email) {
		super(objectName, email);
	}

	@Override
	protected void validate(String objectName, String email, List<ValidationFailure> failures) {
		boolean notNull = addValidationResult(new NotNullValidator(objectName, email));
		
		if(notNull && !org.apache.commons.validator.EmailValidator.getInstance().isValid(email))
			failures.add(new ValidationFailure(join(objectName, INVALID_EMAIL), objectName));
	}
	
}