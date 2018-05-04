package com.imadp.service.account.credentials;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.validation.ValidationResult;
import com.imadp.service.test.IMADPServiceTestCase;
import com.imadp.service.user.User;

/**
 * CredentialsPasswordChangeValidatorTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CredentialsPasswordChangeValidatorTest extends IMADPServiceTestCase {
	User user;
	
	@Override
	public void before() throws Exception {
		super.before();

		user = new User();
		
		Credentials credentials = new Credentials();
		credentials.setUser(user);
		credentials.setUsername("username");
		credentials.setEmail("email@email.com");
		credentials.setPassword(digestor.digest("password"));
		credentials.setFacebookId(System.currentTimeMillis());
		
		userService.save(user);		
		credentialsService.save(credentials);
	}
	
	@Test 
	public void testValid() throws Exception {
		CredentialsPassword credentialsPassword = new CredentialsPassword();
		credentialsPassword.setPassword("password");
		credentialsPassword.setConfirmPassword("password");
		
		CredentialsPasswordChange credentialsPasswordChange = new CredentialsPasswordChange();
		credentialsPasswordChange.setOldPassword("password");
		credentialsPasswordChange.setUser(user);
		credentialsPasswordChange.setCredentialsPassword(credentialsPassword);
		
		CredentialsPasswordChangeValidator validator = new CredentialsPasswordChangeValidator(
				"credentialsPasswordChange", credentialsPasswordChange, digestor, credentialsService);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
	}
	
	@Test 
	public void testNull() throws Exception {
		CredentialsPasswordChangeValidator validator = new CredentialsPasswordChangeValidator(
				"credentialsPasswordChange", null, digestor, credentialsService);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(3, result.getFailureCount());
		assertTrue(result.hasFailure("credentialsPasswordChange.oldPassword.invalidNull"));
		assertTrue(result.hasFailure("credentialsPasswordChange.credentialsPassword.password.invalidNull"));
		assertTrue(result.hasFailure("credentialsPasswordChange.credentialsPassword.confirmPassword.invalidNull"));
	}

	@Test 
	public void testInvalidOldPassword() throws Exception {
		CredentialsPassword credentialsPassword = new CredentialsPassword();
		credentialsPassword.setPassword("password");
		credentialsPassword.setConfirmPassword("password");
		
		CredentialsPasswordChange credentialsPasswordChange = new CredentialsPasswordChange();
		credentialsPasswordChange.setOldPassword("invalidOldPassword");
		credentialsPasswordChange.setUser(user);
		credentialsPasswordChange.setCredentialsPassword(credentialsPassword);
		
		CredentialsPasswordChangeValidator validator = new CredentialsPasswordChangeValidator(
				"credentialsPasswordChange", credentialsPasswordChange, digestor, credentialsService);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
		assertTrue(result.hasFailure("credentialsPasswordChange.oldPassword.invalidOldPassword"));		
	}
	
	@Test 
	public void testInvalidConfirmPassword() throws Exception {
		CredentialsPassword credentialsPassword = new CredentialsPassword();
		credentialsPassword.setPassword("password1");
		credentialsPassword.setConfirmPassword("password2");
		
		CredentialsPasswordChange credentialsPasswordChange = new CredentialsPasswordChange();
		credentialsPasswordChange.setOldPassword("password");
		credentialsPasswordChange.setUser(user);
		credentialsPasswordChange.setCredentialsPassword(credentialsPassword);
		
		CredentialsPasswordChangeValidator validator = new CredentialsPasswordChangeValidator(
				"credentialsPasswordChange", credentialsPasswordChange, digestor, credentialsService);
		
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(1, result.getFailureCount());
		assertTrue(result.hasFailure("credentialsPasswordChange.credentialsPassword.confirmPassword.invalidConfirmPassword"));		
	}
	
}