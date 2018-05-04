package com.imadp.service.account;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.core.email.Email;
import com.imadp.core.encryption.Digestor;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.FacadeComponent;
import com.imadp.service.account.authority.Authority;
import com.imadp.service.account.authority.AuthorityService;
import com.imadp.service.account.credentials.Credentials;
import com.imadp.service.account.credentials.CredentialsEmail;
import com.imadp.service.account.credentials.CredentialsEmailValidator;
import com.imadp.service.account.credentials.CredentialsPassword;
import com.imadp.service.account.credentials.CredentialsPasswordChange;
import com.imadp.service.account.credentials.CredentialsPasswordChangeValidator;
import com.imadp.service.account.credentials.CredentialsResetPassword;
import com.imadp.service.account.credentials.CredentialsResetPasswordValidator;
import com.imadp.service.account.credentials.CredentialsService;
import com.imadp.service.account.credentials.CredentialsUsername;
import com.imadp.service.account.credentials.CredentialsUsernameValidator;
import com.imadp.service.email.PersistableEmailService;
import com.imadp.service.user.User;
import com.imadp.service.user.UserService;

/**
 * AccountFacadeImpl
 *
 * The standard AccountFacade implementation, capable of creating accounts transactionally.
 * Delegates the various save and delete operations to the appropriate services.
 *
 * Alone the implementation is not suitable for creating accounts, but can serve as a functional authentication
 * facade for administrative purposes. Subclasses should override and provide methods to create more specific accounts.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public class AccountFacadeImpl<T extends Account> extends FacadeComponent implements AccountFacade<T> {
	protected CredentialsService credentialsService;
	protected UserService userService;
	protected AuthorityService authorityService;
	protected PersistableEmailService persistableEmailService;
	protected Digestor digestor;
	protected AuthenticationManager authenticationManager;
	protected String emailAccountVerificationTemplate;
	protected String emailResetPasswordTemplate;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(credentialsService);
		Validate.notNull(authorityService);
		Validate.notNull(userService);
		Validate.notNull(digestor);
		Validate.notNull(authenticationManager);
	}

	@Override
	public final Authentication authenticate(Authentication authentication) {
		return authenticationManager.authenticate(authentication);
	}

	@Override
	public final User getUser(Long id) {
		return userService.get(id);
	}

	@Override
	public final User getUser(String uid) {
		return userService.get(uid);
	}

	@Override
	public final User getUserByUsername(String username) {
		Credentials credentials = credentialsService.findByUsername(username);
		return credentials == null ? null : credentials.getUser();
	}

	@Override
	public final void saveUser(User user) {
		userService.save(user);
	}

	@Override
	public final Credentials getCredentials(User user) {
		return credentialsService.findFirstByUser(user);
	}

	@Override
	public void updateLastLoginDate(User user) {
		Credentials credentials = getCredentials(user);
		credentials.setLastLoginDate(new DateTime());
		credentialsService.save(credentials);
	}

	/**
	 * Creates a new account with the given authority.
	 *
	 * @param account
	 * @param authority
	 * @param locale
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	protected final Credentials createAccount(T account, Authority authority) {
		validateAccount(account);

		// set a new user if none was provided
		if(account.getUser() == null)
			account.setUser(new User());

		// prepare credentials
		Credentials credentials = new Credentials();
		credentials.setUser(account.getUser());
		credentials.setEmail(account.getCredentialsEmail());
		credentials.setUsername(account.getCredentialsUsername());
		credentials.setPassword(account.getCredentialsPassword());

		// digest password
		credentials.setPassword(digestor.digest(credentials.getPassword()));

		credentials.setAuthority(authority);
		credentials.enableCredentials();

		// save the account
		userService.save(account.getUser());
		credentialsService.save(credentials);

		onCreateAccount(account);

		return credentials;
	}

	/**
	 * Hook into the account creation process. This method is called after the user and credentials have been
	 * extracted and saved from the account object.  Override this method to control what domain objects
	 * are saved with the account or if a verification email should be generated.
	 *
	 * @param account
	 */
	protected void onCreateAccount(T account) {

	}

	@Override
	public final boolean verifyEmail(User user, String verificationId) {
		return credentialsService.verifyEmail(user, verificationId);
	}

	@Override
	public final void sendAccountVerificationEmail(User user, Locale locale) {
		sendAccountVerificationEmail(getCredentials(user), locale);
	}

	/**
	 * Sends a verification email.
	 *
	 * @param credentials
	 * @param locale
	 */
	private void sendAccountVerificationEmail(Credentials credentials, Locale locale) {
		Validate.notNull(persistableEmailService);
		Validate.notNull(emailAccountVerificationTemplate);

		Map<String, Credentials> model = new HashMap<>();
		model.put("credentials", credentials);

		// build and send the email
		Email email = persistableEmailService.buildEmail(emailAccountVerificationTemplate, locale, model);
		email.setTo(credentials.getEmail());

		persistableEmailService.send(email);
	}

	/**
	 * Disables an account without permanently deleting it.
	 *
	 * @param user
	 * @throws IllegalArgumentException if no account was found for the given user
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public final void disableAccount(User user) {
		Credentials credentials = credentialsService.findFirstByUser(user);

		if(credentials == null)
			throw new IllegalArgumentException("No account found by user=["+user == null ? "null" : user.getId()+"]");

		credentials.setEnabled(false);
		credentialsService.save(credentials);
	}

	/**
	 * Enables an account without permanently deleting it.
	 *
	 * @param user
	 * @throws IllegalArgumentException if no account was found for the given user
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public final void enableAccount(User user) {
		Credentials credentials = credentialsService.findFirstByUser(user);

		if(credentials == null)
			throw new IllegalArgumentException("No account found by user=["+user == null ? "null" : user.getId()+"]");

		credentials.setEnabled(true);
		credentialsService.save(credentials);
	}

	/**
	 * Deletes the account domain objects, including user and credentials objects.
	 * Override this method to control what domain objects are deleted.
	 *
	 * @param user
	 * @throws IllegalArgumentException if no account was found for the given user
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public final void deleteAccount(User user) {
		List<Credentials> credentials = credentialsService.findByUser(user, CriteriaParams.<Credentials>of(Results.ALL));

		// delete the domain objects
		credentialsService.delete(credentials);
		userService.delete(credentials.get(0).getUser());
	}

	@Override
	public final void changeCredentialsUsername(CredentialsUsername credentialsUsername) {
		validateCredentialsUsername(credentialsUsername);
		Credentials credentials = getCredentials(credentialsUsername.getUser());
		credentials.setUsername(credentialsUsername);
		credentialsService.save(credentials);
	}

	@Override
	public final void changeCredentialsPassword(CredentialsPasswordChange credentialsPasswordChange) {
		validateCredentialsPasswordChange(credentialsPasswordChange);
		changeCredentialsPasswordAfterValidation(credentialsPasswordChange);
	}

	/**
	 * Changes the credentials password after successful validation.
	 *
	 * @param credentialsPasswordChange
	 */
	private void changeCredentialsPasswordAfterValidation(CredentialsPasswordChange credentialsPasswordChange) {
		Credentials credentials = getCredentials(credentialsPasswordChange.getUser());
		credentials.setPassword(digestor.digest(credentialsPasswordChange.getCredentialsPassword().getPassword()));
		credentialsService.save(credentials);
	}

	@Override
	public final void changeCredentialsEmail(CredentialsEmail credentialsEmail, Locale locale) {
		validateCredentialsEmail(credentialsEmail);
		Credentials credentials = getCredentials(credentialsEmail.getUser());
		credentials.setEmail(credentialsEmail);
		credentialsService.save(credentials);

		if(!credentials.isEmailVerified())
			sendAccountVerificationEmail(credentials, locale);
	}

	@Override
	public final void resetPassword(CredentialsResetPassword credentialsResetPassword, Locale locale) {
		validateResetPassword(credentialsResetPassword);

		Credentials credentials = credentialsService.findByEmail(credentialsResetPassword.getEmail());
		String resetPassword = RandomStringUtils.randomAlphanumeric(8);

		CredentialsPasswordChange credentialsPasswordChange = new CredentialsPasswordChange();
		CredentialsPassword credentialsPassword = new CredentialsPassword();
		credentialsPassword.setPassword(resetPassword);
		credentialsPassword.setConfirmPassword(resetPassword);
		credentialsPasswordChange.setCredentialsPassword(credentialsPassword);
		credentialsPasswordChange.setUser(credentials.getUser());
		changeCredentialsPasswordAfterValidation(credentialsPasswordChange);

		sendResetPasswordEmail(resetPassword, credentials, locale);
	}

	/**
	 * Sends a reset password email.
	 *
	 * @param resetPassword
	 * @param credentials
	 * @param locale
	 */
	private void sendResetPasswordEmail(String resetPassword, Credentials credentials, Locale locale) {
		Validate.notNull(persistableEmailService);
		Validate.notNull(emailResetPasswordTemplate);

		Map<String, Object> model = new HashMap<>();
		model.put("credentials", credentials);
		model.put("resetPassword", resetPassword);

		// build and send the email
		Email email = persistableEmailService.buildEmail(emailResetPasswordTemplate, locale, model);
		email.setTo(credentials.getEmail());

		persistableEmailService.send(email);
	}

	/**
	 * Validates an account.
	 *
	 * @param objectName
	 * @param account
	 */
	protected void validateAccount(T account) {
		new AccountValidator<>("account", account, credentialsService).validate();
	}

	/**
	 * Validates the credentials username.
	 *
	 * @param credentialsUsername
	 */
	protected void validateCredentialsUsername(CredentialsUsername credentialsUsername) {
		new CredentialsUsernameValidator("credentialsUsername", credentialsUsername,
				credentialsService).validate();
	}

	/**
	 * Validates the credentials email.
	 *
	 * @param credentialsEmail
	 */
	protected void validateCredentialsEmail(CredentialsEmail credentialsEmail) {
		new CredentialsEmailValidator("credentialsEmail", credentialsEmail, credentialsService)
			.validate();
	}

	/**
	 * Validates the reset password.
	 *
	 * @param credentialsResetPassword
	 */
	protected void validateResetPassword(CredentialsResetPassword credentialsResetPassword) {
		new CredentialsResetPasswordValidator("credentialsResetPassword",
				credentialsResetPassword, credentialsService).validate();
	}

	/**
	 * Validates the credentials password change.
	 *
	  * @param credentialsPasswordChange
	 */
	protected void validateCredentialsPasswordChange(CredentialsPasswordChange credentialsPasswordChange) {
		new CredentialsPasswordChangeValidator("credentialsPasswordChange",
				credentialsPasswordChange, digestor, credentialsService).validate();
	}

	// getters and setters
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}

	public void setCredentialsService(CredentialsService credentialsService) {
		this.credentialsService = credentialsService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Digestor getDigestor() {
		return digestor;
	}

	public void setDigestor(Digestor digestor) {
		this.digestor = digestor;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public PersistableEmailService getPersistableEmailService() {
		return persistableEmailService;
	}

	public void setPersistableEmailService(PersistableEmailService persistableEmailService) {
		this.persistableEmailService = persistableEmailService;
	}

	public String getEmailAccountVerificationTemplate() {
		return emailAccountVerificationTemplate;
	}

	public void setEmailAccountVerificationTemplate(String emailAccountVerificationTemplate) {
		this.emailAccountVerificationTemplate = emailAccountVerificationTemplate;
	}

	public String getEmailResetPasswordTemplate() {
		return emailResetPasswordTemplate;
	}

	public void setEmailResetPasswordTemplate(String emailResetPasswordTemplate) {
		this.emailResetPasswordTemplate = emailResetPasswordTemplate;
	}

}