package com.imadp.service.account.credentials;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.MinLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.SimpleValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * CredentialsPasswordValidator
 * 
 * Ensures that the password is confirmed for a given credentials object.
 * 
 * @note ValidationFailures are identified via the following failure keys:
 * 		 {objectName}.password.invalidNull
 *       {objectName}.password.invalidMinLength
 *       {objectName}.password.invalidMaxLength  
 * 		 {objectName}.confirmPassword.invalidNull       
 *       {objectName}.confirmPassword.invalidConfirmPassword
 *       
 * @version 1.0
 * @author Anthony DePalma
 */
public class CredentialsPasswordValidator extends AbstractValidator<CredentialsPassword> {
	
	// lengths
	private static final int MIN_PASSWORD_LENGTH = 3;
	private static final int MAX_PASSWORD_LENGTH = 255;
	
	// keys
	private static final String INVALID_CONFIRM_PASSWORD = "invalidConfirmPassword";
	
	// constructor
	public CredentialsPasswordValidator(String objectName, CredentialsPassword credentialsPassword) {
		super(objectName, credentialsPassword);
	}
	
	@Override
	protected void validate(String objectName, CredentialsPassword credentialsPassword, List<ValidationFailure> failures) {
		String passwordName = join(objectName, "password");
		String confirmPasswordName = join(objectName, "confirmPassword");
		
		// validate null confirmPassword
		if(credentialsPassword == null) 
		{
			addValidationResult(NotNullValidator.invalidNull(passwordName));
			addValidationResult(NotNullValidator.invalidNull(confirmPasswordName));
			return;
		}
		
		// validate password		
		String password = credentialsPassword.getPassword();
		boolean passwordNotNull = addValidationResult(new NotNullValidator(passwordName, password));
		
		if(passwordNotNull)
		{
			addValidationResult(new MinLengthValidator(passwordName, password, MIN_PASSWORD_LENGTH));
			addValidationResult(new MaxLengthValidator(passwordName, password, MAX_PASSWORD_LENGTH));
		}
		
		// validate confirmPassword		
		boolean confirmPasswordNotNull = addValidationResult(new NotNullValidator(
				confirmPasswordName, credentialsPassword.getConfirmPassword()));
		
		if(passwordNotNull && confirmPasswordNotNull)
			addValidationResult(new SimpleValidator(credentialsPassword.isPasswordConfirmed(), 
					join(confirmPasswordName, INVALID_CONFIRM_PASSWORD), passwordName, confirmPasswordName));
	}
	
}