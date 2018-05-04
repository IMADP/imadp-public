package com.imadp.core.email;


/**
 * IEmailSender
 *
 * Provides the ability to send an Email.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface EmailSender {

	/**
	 * Sends an email.
	 *
	 * @param email
	 * @throws EmailException if unable to send the email.
	 */
	public void send(Email email) throws EmailException;

}
