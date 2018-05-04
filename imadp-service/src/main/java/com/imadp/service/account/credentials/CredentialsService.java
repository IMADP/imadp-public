package com.imadp.service.account.credentials;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.imadp.service.user.PersistableUserService;
import com.imadp.service.user.User;

/**
 * ICredentialsService
 *
 * Provides common retrieval operations for Credentials objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface CredentialsService extends UserDetailsService, PersistableUserService<Credentials> {

	/**
	 * Returns a set of restricted usernames.
	 *
	 * @return Set<String>
	 */
	public Set<String> getRestrictedUsernames();

	/**
	 * Returns true if a username is currently in use by a user other than the one given, false otherwise.
	 *
	 * @note If the supplied user is null, the method will return true if the username is in use by anyone,
	 *
	 * @param username
	 * @param user
	 * @return boolean
	 */
	public boolean isUsernameInUse(String username, User user);

	/**
	 * Returns true if an email is currently in use other than the one given, false otherwise.
	 *
	 * @note If the supplied user is null, the method will return true if the email is in use by anyone.
	 *
	 * @param email
	 * @param user
	 * @return boolean
	 */
	public boolean isEmailInUse(String email, User user);

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
	 * Finds Credentials by facebookId.
	 *
	 * @param facebookId
	 * @return Credentials or <code>null</code> if no result was found
	 */
	public Credentials findBy(Long facebookId);

	/**
	 * Finds credentials by email, or null if no credentials could be found.
	 *
	 * @param email
	 * @return Credentials
	 */
	public Credentials findByEmail(String email);

	/**
	 * Finds credentials by username, or null if no credentials could be found.
	 *
	 * @param username
	 * @return Credentials
	 */
	public Credentials findByUsername(String username);

}