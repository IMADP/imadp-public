package com.imadp.service.account;

import com.imadp.core.AbstractSerializable;
import com.imadp.core.encryption.Base62;
import com.imadp.service.account.credentials.CredentialsEmail;
import com.imadp.service.account.credentials.CredentialsPassword;
import com.imadp.service.account.credentials.CredentialsUsername;
import com.imadp.service.user.User;

/**
 * Account
 *
 * Holds common objects necessary for creating and modifying accounts.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Account extends AbstractSerializable {
	private User user;
	private CredentialsEmail credentialsEmail;
	private CredentialsUsername credentialsUsername;
	private CredentialsPassword credentialsPassword;
	private String verification;
	private String referrerUserEid;
	private boolean acceptTerms = true;

	// constructor
	public Account() {

	}

	/**
	 * Returns true if a referral user is present, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean hasReferrer() {
		return referrerUserEid != null;
	}

	/**
	 * Returns the decoded id of the referring user.
	 *
	 * @return Long
	 */
	public Long getReferrerId() {
		return Base62.decode(getReferrerUserEid());
	}

	// getters and setters
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CredentialsEmail getCredentialsEmail() {
		return credentialsEmail;
	}

	public void setCredentialsEmail(CredentialsEmail credentialsEmail) {
		this.credentialsEmail = credentialsEmail;
	}

	public CredentialsUsername getCredentialsUsername() {
		return credentialsUsername;
	}

	public void setCredentialsUsername(CredentialsUsername credentialsUsername) {
		this.credentialsUsername = credentialsUsername;
	}

	public CredentialsPassword getCredentialsPassword() {
		return credentialsPassword;
	}

	public void setCredentialsPassword(CredentialsPassword credentialsPassword) {
		this.credentialsPassword = credentialsPassword;
	}

	public boolean isAcceptTerms() {
		return acceptTerms;
	}

	public void setAcceptTerms(boolean acceptTerms) {
		this.acceptTerms = acceptTerms;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public String getReferrerUserEid() {
		return referrerUserEid;
	}

	public void setReferrerUserEid(String referrerUserEid) {
		this.referrerUserEid = referrerUserEid;
	}

}