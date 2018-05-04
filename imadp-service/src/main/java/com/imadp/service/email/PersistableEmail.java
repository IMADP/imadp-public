package com.imadp.service.email;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.core.email.Email;
import com.imadp.dao.AbstractPersistable;


/**
 * PersistableEmail
 *
 * A representation of an electronic mail message.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class PersistableEmail extends AbstractPersistable {

	// static Email Properties
	public static final Property<PersistableEmail, String> BCC = Property.of("bcc");
	public static final Property<PersistableEmail, String> CC = Property.of("cc");
	public static final Property<PersistableEmail, String> FROM = Property.of("from");
	public static final Property<PersistableEmail, String> FROM_ALIAS = Property.of("fromAlias");
	public static final Property<PersistableEmail, String> REPLY_TO = Property.of("replyTo");
	public static final Property<PersistableEmail, String> SUBJECT = Property.of("subject");
	public static final Property<PersistableEmail, String> TEXT = Property.of("text");
	public static final Property<PersistableEmail, String> TEMPLATE = Property.of("template");
	public static final Property<PersistableEmail, String> TO = Property.of("to");
	public static final Property<PersistableEmail, Boolean> HTML = Property.of("html");

	// static status properties
	public static final Property<PersistableEmail, String> ERROR_MESSAGE = Property.of("errorMessage");
	public static final Property<PersistableEmail, DateTime> DATE = Property.of("date");
	public static final Property<PersistableEmail, PersistableEmailStatus> STATUS = Property.of("status");

	private static final char EMAIL_DELIMITER = ';';

	// email properties
	private Email email;

	// status properties
	private String errorMessage;
	private DateTime date;
	private PersistableEmailStatus status;

	// constructor
	public PersistableEmail() {
		this(new Email());
	}

	// constructor
	public PersistableEmail(Email email) {
		this.email = email;
		this.date = new DateTime();
		this.status = PersistableEmailStatus.UNSENT;
	}

	/**
	 * Returns true if the status of this email is SENT, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isSent() {
		return PersistableEmailStatus.SENT == status;
	}

	// delegate methods
	public String getBcc() {
		return StringUtils.join(email.getBcc(), EMAIL_DELIMITER);
	}

	public String getCc() {
		return StringUtils.join(email.getCc(), EMAIL_DELIMITER);
	}

	public String getFrom() {
		return email.getFrom();
	}

	public String getFromAlias() {
		return email.getFromAlias();
	}

	public String getReplyTo() {
		return email.getReplyTo();
	}

	public String getSubject() {
		return email.getSubject();
	}

	public String getText() {
		return email.getText();
	}

	public String getTemplate() {
		return email.getTemplate();
	}

	public String getTo() {
		return StringUtils.join(email.getTo(), EMAIL_DELIMITER);
	}

	public boolean isHtml() {
		return email.isHtml();
	}

	public void setBcc(String bcc) {
		email.setBcc(StringUtils.split(bcc, EMAIL_DELIMITER));
	}

	public void setCc(String cc) {
		email.setCc(StringUtils.split(cc, EMAIL_DELIMITER));
	}

	public void setFrom(String from) {
		email.setFrom(from);
	}

	public void setFromAlias(String fromAlias) {
		email.setFromAlias(fromAlias);
	}

	public void setReplyTo(String replyTo) {
		email.setReplyTo(replyTo);
	}

	public void setSubject(String subject) {
		email.setSubject(subject);
	}

	public void setText(String text) {
		email.setText(text);
	}

	public void setTemplate(String template) {
		email.setTemplate(template);
	}

	public void setTo(String to) {
		email.setTo(StringUtils.split(to, EMAIL_DELIMITER));
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public void setHtml(boolean html) {
		email.setHtml(html);
	}

	// getters and setters
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public PersistableEmailStatus getStatus() {
		return status;
	}

	public void setStatus(PersistableEmailStatus status) {
		this.status = status;
	}

	/**
	 * PersistableEmailStatus
	 *
	 * Enumerated values of a email statuses.
	 *
	 * @version 1.0
	 * @author Anthony DePalma
	 */
	public enum PersistableEmailStatus {
		UNSENT, SENT, ERROR
	}

}