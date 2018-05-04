package com.imadp.service.account.credentials;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.ExpressionValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.MinLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.SimpleValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * CredentialsUsernameValidator
 *
 * Ensures that the username is valid for a given credentials object.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.username.invalidNull
 *       {objectName}.username.invalidMinLength
 *       {objectName}.username.invalidMaxLength
 *       {objectName}.username.invalidExpression
 *       {objectName}.username.invalidRestricted
 *       {objectName}.username.invalidUsernameInUse
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CredentialsUsernameValidator extends AbstractValidator<CredentialsUsername> {

	// lengths
	private static final int MIN_USERNAME_LENGTH = 3;
	private static final int MAX_USERNAME_LENGTH = 15;

	// expressions
	private static final String USERNAME_EXPRESSION = "^[a-zA-Z0-9_-]+$";

	// keys
	private static final String INVALID_USERNAME_IN_USE = "invalidUsernameInUse";
	private static final String INVALID_RESTRICTED = "invalidRestricted";

	// properties
	private CredentialsService credentialsService;

	// constructor
	public CredentialsUsernameValidator(String objectName, CredentialsUsername credentialsUsername,
			CredentialsService credentialsService) {
		super(objectName, credentialsUsername);
		this.credentialsService = credentialsService;
	}

	@Override
	protected void validate(String objectName, CredentialsUsername credentialsUsername, List<ValidationFailure> failures) {
		String usernameName = join(objectName, "username");

		// validate null credentialsUsername
		if(credentialsUsername == null)
		{
			addValidationResult(NotNullValidator.invalidNull(usernameName));
			return;
		}

		String username = credentialsUsername.getUsername();
		boolean usernameNotNull = addValidationResult(new NotNullValidator(usernameName, username));

		if(usernameNotNull)
		{
			boolean validMin = addValidationResult(new MinLengthValidator(usernameName, username, MIN_USERNAME_LENGTH));
			boolean validMax = addValidationResult(new MaxLengthValidator(usernameName, username, MAX_USERNAME_LENGTH));
			boolean validExp = addValidationResult(new ExpressionValidator(usernameName, username, USERNAME_EXPRESSION));

			boolean isUsernameRestricted = credentialsService.getRestrictedUsernames().contains(username);
			boolean validRestricted = addValidationResult(new SimpleValidator(
					!isUsernameRestricted, join(usernameName, INVALID_RESTRICTED), usernameName));

			if(validMin && validMax && validExp && validRestricted)
			{
				boolean isUsernameInUse = credentialsService.isUsernameInUse(username, credentialsUsername.getUser());
				addValidationResult(new SimpleValidator(!isUsernameInUse, join(usernameName, INVALID_USERNAME_IN_USE), usernameName));
			}

		}

	}

}