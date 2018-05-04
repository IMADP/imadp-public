package com.imadp.service.account.credentials;

import java.util.List;

import com.imadp.core.email.EmailValidator;
import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.SimpleValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * CredentialsResetPasswordValidator
 *
 * Ensures that the email is valid and associated with a credentials object.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.invalidNull
 *       {objectName}.invalidEmail
 *       {objectName}.invalidEmailNotInUse
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CredentialsResetPasswordValidator extends AbstractValidator<CredentialsResetPassword> {

	// keys
	private static final String INVALID_EMAIL_NOT_IN_USE = "invalidEmailNotInUse";

	// properties
	private CredentialsService credentialsService;

	// constructor
	public CredentialsResetPasswordValidator(String objectName, CredentialsResetPassword credentialsResetPassword,
			CredentialsService credentialsService) {
		super(objectName, credentialsResetPassword);
		this.credentialsService = credentialsService;
	}

	@Override
	protected void validate(String objectName, CredentialsResetPassword credentialsResetPassword,
			List<ValidationFailure> failures) {

		String emailName = join(objectName, "email");

		// validate null credentialsResetPassword
		if(credentialsResetPassword == null)
		{
			addValidationResult(NotNullValidator.invalidNull(emailName));
			return;
		}

		String email = credentialsResetPassword.getEmail();

		// validate the email
		boolean isEmailValid = addValidationResult(new EmailValidator(emailName, email));

		if(isEmailValid)
		{
			boolean isEmailInUse = credentialsService.isEmailInUse(email, null);
			addValidationResult(new SimpleValidator(isEmailInUse, join(emailName, INVALID_EMAIL_NOT_IN_USE), emailName));
		}

	}

}