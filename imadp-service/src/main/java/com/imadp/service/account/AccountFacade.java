package com.imadp.service.account;

import java.util.Locale;

import org.springframework.security.core.Authentication;

import com.imadp.service.account.credentials.Credentials;
import com.imadp.service.account.credentials.CredentialsEmail;
import com.imadp.service.account.credentials.CredentialsPasswordChange;
import com.imadp.service.account.credentials.CredentialsResetPassword;
import com.imadp.service.account.credentials.CredentialsUsername;
import com.imadp.service.user.User;

/**
 * AccountFacade
 *
 * This facade coordinates common account services. It is suggested that this facade be subclassed by all
 * applications to supply the account type and provide create account methods.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public interface AccountFacade<T extends Account> {

	/**
	 * Authenticates a request.
	 *
	 * @param authentication
	 * @return Authentication
	 */
	public Authentication authenticate(Authentication authentication);

	/**
	 * Returns a user by id, or null if none was found.
	 *
	 * @param id
	 * @return User
	 */
	public User getUser(Long id);

	/**
	 * Returns a user by uid, or null if none was found.
	 *
	 * @param uid
	 * @return User
	 */
	public User getUser(String uid);

	/**
	 * Returns a user by username, or null if none was found.
	 *
	 * @param username
	 * @return User
	 */
	public User getUserByUsername(String username);

	/**
	 * Saves a user.
	 *
	 * @param user
	 */
	public void saveUser(User user);

	/**
	 * Returns credentials by user, or null if none was found.
	 *
	 * @param user
	 * @return Credentials
	 */
	public Credentials getCredentials(User user);

	/**
	 * Updates the credentials with the date of the last successful login.
	 *
	 * @param user
	 */
	public void updateLastLoginDate(User user);

	/**
	 * Sends a verification email in order to verify the user's credentials.
	 *
	 * @param user
	 * @param locale
	 */
	public void sendAccountVerificationEmail(User user, Locale locale);

	/**
	 * Attempts to verify the current email with the given verificationId.
	 * Returns true if the verification was successful, false otherwise.
	 *
	 * @param user
	 * @param verificationId
	 * @return boolean
	 */
	public boolean verifyEmail(User user, String verificationId);

	/**
	 * Changes an existing user's username.
	 *
	 * @param credentialsUsername
	 */
	public void changeCredentialsUsername(CredentialsUsername credentialsUsername);

	/**
	 * Changes an existing user's email.
	 *
	 * @param credentialsEmail
	 * @param locale
	 */
	public void changeCredentialsEmail(CredentialsEmail credentialsEmail, Locale locale);

	/**
	 * Changes an existing user's password.
	 *
	 * @param credentialsPasswordChange
	 */
	public void changeCredentialsPassword(CredentialsPasswordChange credentialsPasswordChange);

	/**
	 * Resets the password associated with the given email address and sends an email with the temporary password.
	 *
	 * @param credentialsResetPassword
	 * @param locale
	 */
	public void resetPassword(CredentialsResetPassword credentialsResetPassword, Locale locale);

	/**
	 * Disables an account without permanently deleting it.
	 *
	 * @param user
	 */
	public void disableAccount(User user);

	/**
	 * Enables an account without permanently deleting it.
	 *
	 * @param user
	 */
	public void enableAccount(User user);

	/**
	 * Deletes the account permanently.
	 *
	 * @param user
	 */
	public void deleteAccount(User user);

}