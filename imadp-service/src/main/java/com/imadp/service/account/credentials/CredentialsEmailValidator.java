package com.imadp.service.account.credentials;

import java.util.List;

import com.imadp.core.email.EmailValidator;
import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.SimpleValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * CredentialsEmailValidator
 * 
 * Ensures that the email is valid for a given credentials object.
 * 
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.email.invalidNull
 *       {objectName}.email.invalidEmail
 *       {objectName}.email.invalidEmailInUse
 *       
 * @version 1.0
 * @author Anthony DePalma
 */
public class CredentialsEmailValidator extends AbstractValidator<CredentialsEmail> {

	// keys
	private static final String INVALID_EMAIL_IN_USE = "invalidEmailInUse";
	
	// properties
	private CredentialsService credentialsService;
	
	// constructor
	public CredentialsEmailValidator(String objectName, CredentialsEmail credentialsEmail,
			CredentialsService credentialsService) {
		super(objectName, credentialsEmail);
		this.credentialsService = credentialsService;
	}
	
	@Override
	protected void validate(String objectName, CredentialsEmail credentialsEmail, List<ValidationFailure> failures) {
		String emailName = join(objectName, "email");
		 
		// validate null credentialsEmail
		if(credentialsEmail == null)
		{
			addValidationResult(NotNullValidator.invalidNull(emailName));
			return;
		}
		
		// validate the email
		String email = credentialsEmail.getEmail();
		boolean isEmailValid = addValidationResult(new EmailValidator(emailName, email));
		
		if(isEmailValid)
		{
			boolean isEmailInUse = credentialsService.isEmailInUse(email, credentialsEmail.getUser());			
			addValidationResult(new SimpleValidator(!isEmailInUse, join(emailName, INVALID_EMAIL_IN_USE), emailName));
		}
		
	}
	
}