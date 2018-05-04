package com.tracktacular.service;

import com.google.common.base.CaseFormat;

/**
 * TracktacularEmail
 *
 * Enumerated values of a email codes.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public enum TracktacularEmail {
	ACCOUNT_VERIFICATION,
	ACCOUNT_NOTIFICATION,
	BLOG_ENTRY_NOTIFICATION,
	RESET_PASSWORD,
	STATUS_REPORT,
	TRACKTACULAR_REPORT,
	SUBSCRIPTION_ERROR;

	/**
	 * Returns the email template name.
	 *
	 * @return String
	 */
	public String getTemplate() {
		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, toString());
	}

}