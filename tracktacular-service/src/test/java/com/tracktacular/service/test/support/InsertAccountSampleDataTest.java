package com.tracktacular.service.test.support;

import java.util.Locale;

import org.junit.Test;

import com.imadp.service.account.Account;
import com.imadp.service.account.credentials.Credentials;
import com.imadp.service.account.credentials.CredentialsEmail;
import com.imadp.service.account.credentials.CredentialsPassword;
import com.imadp.service.account.credentials.CredentialsUsername;



/**
 * InsertAccountSampleDataTest
 *
 * Inserts sample data into the database.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class InsertAccountSampleDataTest extends TracktacularServiceSupportTestCase {

	@Override
	public void before() throws Exception {
		super.before();

		loadUser();
	}

	@Test
	public void insertAccount() {

		CredentialsEmail credentialsEmail = new CredentialsEmail();
		credentialsEmail.setEmail("fatefree@gmail.com");

		CredentialsUsername credentialsUsername = new CredentialsUsername();
		credentialsUsername.setUsername("fatefree");

		CredentialsPassword credentialsPassword = new CredentialsPassword();
		credentialsPassword.setPassword("password");
		credentialsPassword.setConfirmPassword("password");

		Account account = new Account();
		account.setUser(user);
		account.setCredentialsEmail(credentialsEmail);
		account.setCredentialsUsername(credentialsUsername);
		account.setCredentialsPassword(credentialsPassword);

		Credentials credentials = accountFacade.createUserAccount(account, Locale.getDefault());
		accountFacade.verifyEmail(user, credentials.getVerificationId());
	}

}
