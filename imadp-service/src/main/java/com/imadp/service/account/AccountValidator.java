package com.imadp.service.account;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.SimpleValidator;
import com.imadp.core.validation.ValidationFailure;
import com.imadp.service.account.credentials.CredentialsEmailValidator;
import com.imadp.service.account.credentials.CredentialsPasswordValidator;
import com.imadp.service.account.credentials.CredentialsService;
import com.imadp.service.account.credentials.CredentialsUsernameValidator;


/**
 * AccountValidator
 *
 * Ensures that the account is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.acceptTerms.invalid
 *       {objectName}.verification.invalid
 *       {objectName}.credentialsEmail.email.invalidNull
 *       {objectName}.credentialsEmail.email.invalidEmail
 *       {objectName}.credentialsEmail.email.invalidEmailInUse
 *       {objectName}.credentialsUsername.username.invalidNull
 *       {objectName}.credentialsUsername.username.invalidMinLength
 *       {objectName}.credentialsUsername.username.invalidMaxLength
 *       {objectName}.credentialsUsername.username.invalidExpression
 *       {objectName}.credentialsUsername.username.invalidUsernameInUse
 *       {objectName}.credentialsPassword.password.invalidNull
 *       {objectName}.credentialsPassword.password.invalidMinLength
 *       {objectName}.credentialsPassword.password.invalidMaxLength
 * 		 {objectName}.credentialsPassword.confirmPassword.invalidNull
 *       {objectName}.credentialsPassword.confirmPassword.invalidConfirmPassword
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public class AccountValidator<T extends Account> extends AbstractValidator<T> {
	private CredentialsService credentialsService;

	// constructor
	public AccountValidator(String objectName, T account, CredentialsService credentialsService) {
		super(objectName, account);
		this.credentialsService = credentialsService;
	}

	@Override
	protected final void validate(String objectName, T account, List<ValidationFailure> failures) {
		// validate a null account
		if(account == null)
		{
			validateNullAccount(objectName, account, failures);
			return;
		}

		// validate a non null account
		validateNonNullAccount(objectName, account, failures);
	}

	/**
	 * Validates a null account.
	 *
	 * @param objectName
	 * @param account
	 * @param failures
	 */
	protected void validateNullAccount(String objectName, Account account, List<ValidationFailure> failures) {
		String acceptTermsName = join(objectName, "acceptTerms");
		String credentialsEmailName = join(objectName, "credentialsEmail");
		String credentialsUsernameName = join(objectName, "credentialsUsername");
		String credentialsPasswordName = join(objectName, "credentialsPassword");

		addValidationResult(new SimpleValidator(false, join(acceptTermsName, "invalid"), acceptTermsName));
		addValidationResult(new CredentialsEmailValidator(credentialsEmailName, null, credentialsService));
		addValidationResult(new CredentialsUsernameValidator(credentialsUsernameName, null, credentialsService));
		addValidationResult(new CredentialsPasswordValidator(credentialsPasswordName, null));
	}

	/**
	 * Validates a non-null account.
	 *
	 * @param objectName
	 * @param account
	 * @param failures
	 */
	protected void validateNonNullAccount(String objectName, Account account, List<ValidationFailure> failures) {
		String acceptTermsName = join(objectName, "acceptTerms");
		String credentialsEmailName = join(objectName, "credentialsEmail");
		String credentialsUsernameName = join(objectName, "credentialsUsername");
		String credentialsPasswordName = join(objectName, "credentialsPassword");
		String verificationName = join(objectName, "verification");

		addValidationResult(new SimpleValidator(account.isAcceptTerms(),
				join(acceptTermsName, "invalid"), acceptTermsName));

		addValidationResult(new SimpleValidator(StringUtils.isBlank(account.getVerification()),
				join(verificationName, "invalid"), verificationName));

		addValidationResult(new CredentialsEmailValidator(credentialsEmailName,
				account.getCredentialsEmail(), credentialsService));

		addValidationResult(new CredentialsUsernameValidator(credentialsUsernameName,
				account.getCredentialsUsername(), credentialsService));

		addValidationResult(new CredentialsPasswordValidator(credentialsPasswordName, account.getCredentialsPassword()));
	}

}