package com.imadp.service.account.credentials;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.imadp.core.Property;
import com.imadp.service.account.authority.Authority;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Credentials
 *
 * Stores the credentials for a user in an application, implementing the UserDetails from Spring Security.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Credentials extends AbstractPersistableUser implements UserDetails {

	// static Properties
	public static final Property<Credentials, String> USERNAME = Property.of("username");
	public static final Property<Credentials, String> EMAIL = Property.of("email");
	public static final Property<Credentials, String> PASSWORD = Property.of("password");
	public static final Property<Credentials, String> VERIFICATION_ID = Property.of("verificationId");
	public static final Property<Credentials, String> VERIFICATION_EMAIL = Property.of("verificationEmail");
	public static final Property<Credentials, DateTime> LAST_LOGIN_DATE = Property.of("lastLoginDate");
	public static final Property<Credentials, Long> FACEBOOK_ID = Property.of("facebookId");
	public static final Property<Credentials, Boolean> ENABLED = Property.of("enabled");
	public static final Property<Credentials, Boolean> ACCOUNT_NON_EXPIRED = Property.of("accountNonExpired");
	public static final Property<Credentials, Boolean> ACCOUNT_NON_LOCKED = Property.of("accountNonLocked");

	// properties
	private Collection<GrantedAuthority> authorities;
	private String username;
	private String email;
	private String password;
	private String verificationId = UUID.randomUUID().toString();
	private String verificationEmail;
	private Long facebookId;
	private DateTime lastLoginDate;
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;

	// constructor
	public Credentials() {

	}

	// constructor
	public Credentials(User user) {
		super(user);
	}

	/**
	 * Returns true if the user has a username
	 *
	 * @return boolean
	 */
	public boolean hasUsername() {
		return username != null;
	}

	/**
	 * Returns the username in lowercase form
	 *
	 * @return String
	 */
	public String getUsernameLowerCase() {
		return hasUsername() ? username.toLowerCase() : null;
	}

	/**
	 * Returns true if an email address is present, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean hasEmail() {
		return email != null;
	}

	/**
	 * Returns true if a facebookId is present, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean hasFacebookId() {
		return facebookId != null;
	}

	/**
	 * Returns true if the email has been verified, false otherwise.
	 * This is determined by matching the verificationEmail against the actual email.
	 *
	 * @return boolean
	 */
	public boolean isEmailVerified() {
		return verificationEmail != null && verificationEmail.equals(email);
	}

	/**
	 * Gets a single authority.
	 *
	 * @return Authority
	 */
	public Authority getAuthority() {
		return (Authority) (authorities.size() < 1 ? null : authorities.iterator().next());
	}

	/**
	 * Sets a single authority.
	 *
	 * @param authority
	 */
	public void setAuthority(Authority authority) {
		if(authorities == null)
			authorities = new HashSet<>(1);

		authorities.clear();
		authorities.add(authority);
	}

	/**
	 * Set the username from the CredentialsUsername.
	 *
	 * @param credentialsUsername
	 */
	public void setUsername(CredentialsUsername credentialsUsername) {
		if(credentialsUsername == null)
			return;

		this.username = credentialsUsername.getUsername();
	}

	/**
	 * Set the email from the CredentialsEmail.
	 *
	 * @param credentialsEmail
	 */
	public void setEmail(CredentialsEmail credentialsEmail) {
		if(credentialsEmail == null)
			return;

		this.email = credentialsEmail.getEmail();
	}

	/**
	 * Set the password from the CredentialsPasswordChange.
	 *
	 * @param credentialsPasswordChange
	 */
	public void setPassword(CredentialsPasswordChange credentialsPasswordChange) {
		if(credentialsPasswordChange == null)
			return;

		setPassword(credentialsPasswordChange.getCredentialsPassword());
	}

	/**
	 * Set the email from the CredentialsPassword.
	 *
	 * @param credentialsPassword
	 */
	public void setPassword(CredentialsPassword credentialsPassword) {
		if(credentialsPassword == null)
			return;

		this.password = credentialsPassword.getPassword();
	}

	/**
	 * Enables this credentials object.
	 *
	 */
	public void enableCredentials() {
		setAccountNonExpired(true);
		setAccountNonLocked(true);
		setEnabled(true);
	}

	/**
	 * Disables this credentials object.
	 *
	 */
	public void disableCredentials() {
		setAccountNonExpired(true);
		setAccountNonLocked(true);
		setEnabled(true);
	}

	/**
	 * Verifies that the credentials email is valid and returns true if the verificationId
	 * matches the one in this object, or if the credentials were already previously verified, false otherwise.
	 *
	 * @param verificationId
	 * @return boolean
	 */
	boolean verifyEmail(String verificationId) {
		// return true if the email was already verified
		if(isEmailVerified())
			return true;

		// return false if the verificationId does not match the expected id
		if(!getVerificationId().equals(verificationId))
			return false;

		// set the verified email and resets the verification id in case the email changes in the future
		this.verificationEmail = this.email;
		this.verificationId = UUID.randomUUID().toString();
		return true;
	}

    @Override
	public String getUsername() {
		return username;
	}

	// getters and setters
	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public DateTime getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(DateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public String getVerificationId() {
		return verificationId;
	}

	public void setVerificationId(String verificationId) {
		this.verificationId = verificationId;
	}

	public Long getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(Long facebookId) {
		this.facebookId = facebookId;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVerificationEmail() {
		return verificationEmail;
	}

	public void setVerificationEmail(String verificationEmail) {
		this.verificationEmail = verificationEmail;
	}

}