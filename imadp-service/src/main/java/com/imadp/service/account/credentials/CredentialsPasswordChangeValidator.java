package com.imadp.service.account.credentials;

import java.util.List;

import com.imadp.core.encryption.Digestor;
import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.SimpleValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * CredentialsPasswordChangeValidator
 * 
 * Ensures that the password change is valid for a given credentials object.
 * 
 * @note ValidationFailures are identified via the following failure keys:
 * 		 {objectName}.oldPassword.invalidNull
 *       {objectName}.oldPassword.invalidOldPassword
 *       {objectName}.credentialsPassword.password.invalidNull
 *       {objectName}.credentialsPassword.password.invalidMinLength
 *       {objectName}.credentialsPassword.password.invalidMaxLength  
 * 		 {objectName}.credentialsPassword.confirmPassword.invalidNull       
 *       {objectName}.credentialsPassword.confirmPassword.invalidConfirmPassword
 *       
 * @version 1.0
 * @author Anthony DePalma
 */
public class CredentialsPasswordChangeValidator extends AbstractValidator<CredentialsPasswordChange> {
	
	// keys
	private static final String INVALID_OLD_PASSWORD = "invalidOldPassword";
	
	// properties
	private Digestor digestor;
	private CredentialsService credentialsService;
	
	// constructor
	public CredentialsPasswordChangeValidator(String objectName, CredentialsPasswordChange credentialsPasswordChange, 
			Digestor digestor, CredentialsService credentialsService) {
		super(objectName, credentialsPasswordChange);
		this.digestor = digestor;
		this.credentialsService = credentialsService;
	}
	
	@Override
	protected void validate(String objectName, CredentialsPasswordChange credentialsPasswordChange, List<ValidationFailure> failures) {
		String oldPasswordName = join(objectName, "oldPassword");
		String credentialsPasswordName = join(objectName, "credentialsPassword");
		
		if(credentialsPasswordChange == null)
		{
			addValidationResult(NotNullValidator.invalidNull(oldPasswordName));
			addValidationResult(new CredentialsPasswordValidator(credentialsPasswordName, null));
			return;
		}
		
		// validate the old password
		boolean oldPasswordNotNull = addValidationResult(
				new NotNullValidator(oldPasswordName, credentialsPasswordChange.getOldPassword()));
		
		if(oldPasswordNotNull)
		{
			String undigestedPassword = credentialsPasswordChange.getOldPassword();
			String digestedPassword = credentialsService.findFirstByUser(credentialsPasswordChange.getUser()).getPassword();
			boolean isOldPasswordValid = digestor.isEqualDigest(undigestedPassword, digestedPassword);
			
			addValidationResult(new SimpleValidator(isOldPasswordValid, 
					join(oldPasswordName, INVALID_OLD_PASSWORD), oldPasswordName));
		}
		
		// validate the confirm password
		addValidationResult(new CredentialsPasswordValidator(
				credentialsPasswordName, credentialsPasswordChange.getCredentialsPassword()));
	}
	
}