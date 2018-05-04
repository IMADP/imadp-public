package com.imadp.service.account.credentials;

import java.util.Collections;
import java.util.Set;

import org.apache.commons.validator.EmailValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.user.PersistableUserServiceImpl;
import com.imadp.service.user.User;

/**
 * CredentialsServiceImpl
 *
 * The standard implementation of the CredentialsService.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CredentialsServiceImpl extends PersistableUserServiceImpl<Credentials> implements CredentialsService {
	private Set<String> restrictedUsernames;

	@Override
	public boolean isUsernameInUse(String username, User user) {
		FindCriteria<Credentials> findCriteria = findCriteriaBuilder(CriteriaParams.<Credentials>of(Results.ONE))
			.whereEqualTo(Credentials.USERNAME, username)
			.whereNotEqualTo(Credentials.USER, user).build();

		return findFirstBy(findCriteria) != null;
	}

	@Override
	public boolean isEmailInUse(String email, User user) {
		FindCriteria<Credentials> findCriteria = findCriteriaBuilder(CriteriaParams.<Credentials>of(Results.ONE))
			.whereEqualTo(Credentials.EMAIL, email)
			.whereNotEqualTo(Credentials.USER, user).build();

		return findFirstBy(findCriteria) != null;
	}

	@Override
	public boolean verifyEmail(User user, String verificationId) {
		Credentials credentials = findFirstByUser(user);

		if(credentials == null)
			throw new IllegalArgumentException("No credentials were found for the user=["+user.getId()+"]");

		// if the verification was unsuccessful, return false
		if(!credentials.verifyEmail(verificationId))
			return false;

		// save the verified credentials
		save(credentials);

		return true;
	}

	@Override
	public Credentials findBy(Long facebookId) {
		return findFirstBy(Credentials.FACEBOOK_ID, facebookId);
	}

	@Override
	public Credentials findByEmail(String email) {
		return findFirstBy(Credentials.EMAIL, email);
	}

	@Override
	public Credentials findByUsername(String username) {
		return findFirstBy(Credentials.USERNAME, username);
	}

	/**
	 * The UserDetails implementation method. Attempts to load the user by the primary login,
	 * and alternatively by the secondary login.
	 *
	 * @param login
	 * @throws UsernameNotFoundException
	 * @return UserDetails
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		// if the login is a valid email address, attempt to find credentials by email address
		if(EmailValidator.getInstance().isValid(login))
		{
			Credentials credentials = findFirstBy(Credentials.EMAIL, login);

			if(credentials != null)
				return credentials;
		}
		else
		{
			// attempt to login via username
			Credentials credentials = findFirstBy(Credentials.USERNAME, login);

			if(credentials != null)
				return credentials;
		}

		// otherwise throw username not found exception
		throw new UsernameNotFoundException("Login [" + login + "] was not found in the database.");
	}

	// getters and setters

	@Override
	public Set<String> getRestrictedUsernames() {
		if(restrictedUsernames == null)
			restrictedUsernames = Collections.emptySet();

		return restrictedUsernames;
	}

	public void setRestrictedUsernames(Set<String> restrictedUsernames) {
		this.restrictedUsernames = restrictedUsernames;
	}

}