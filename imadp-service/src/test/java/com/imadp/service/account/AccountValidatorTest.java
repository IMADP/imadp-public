package com.imadp.service.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.validation.ValidationResult;
import com.imadp.service.account.credentials.CredentialsEmail;
import com.imadp.service.account.credentials.CredentialsPassword;
import com.imadp.service.account.credentials.CredentialsUsername;
import com.imadp.service.test.IMADPServiceTestCase;
import com.imadp.service.user.User;

/**
 * AccountValidatorTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class AccountValidatorTest extends IMADPServiceTestCase {
	Account account;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		CredentialsEmail credentialsEmail = new CredentialsEmail();
		credentialsEmail.setEmail("email@email.com");

		CredentialsUsername credentialsUsername = new CredentialsUsername();
		credentialsUsername.setUsername("username");

		CredentialsPassword credentialsPassword = new CredentialsPassword();
		credentialsPassword.setPassword("password");
		credentialsPassword.setConfirmPassword("password");

		account = new Account();
		account.setUser(user);
		account.setCredentialsEmail(credentialsEmail);
		account.setCredentialsUsername(credentialsUsername);
		account.setCredentialsPassword(credentialsPassword);
	}

	@Test
	public void testValid() throws Exception {
		AccountValidator<Account> validator = new AccountValidator<>("account", account, credentialsService);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertTrue(result.isValid());
	}

	@Test
	public void testNull() throws Exception {
		AccountValidator<Account> validator = new AccountValidator<>("account", null, credentialsService);
		ValidationResult result = validator.getValidationResult();

		assertNotNull(result);
		assertFalse(result.isValid());
		assertEquals(5, result.getFailureCount());
		assertTrue(result.hasFailure("account.credentialsEmail.email.invalidNull"));
		assertTrue(result.hasFailure("account.credentialsUsername.username.invalidNull"));
		assertTrue(result.hasFailure("account.credentialsPassword.password.invalidNull"));
		assertTrue(result.hasFailure("account.credentialsPassword.confirmPassword.invalidNull"));
	}

}