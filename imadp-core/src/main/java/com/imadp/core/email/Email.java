package com.imadp.core.email;

import com.imadp.core.AbstractSerializable;


/**
 * Email
 *
 * A representation of an electronic mail message.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Email extends AbstractSerializable {
    private String from;
    private String fromAlias;
    private String replyTo;
    private String replyToAlias;
    private String subject;
    private String text;
    private String template;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private boolean html;

    // constructor
    public Email() {

    }

    /**
     * Returns true if the email has a from field, false otherwise.
     *
     * @return boolean
     */
    public boolean hasFrom() {
        return from != null;
    }

    /**
     * Returns true if the email has a fromAlias field, false otherwise.
     *
     * @return boolean
     */
    public boolean hasFromAlias() {
        return fromAlias != null;
    }

    /**
     * Returns true if the email has a replyTo field, false otherwise.
     *
     * @return boolean
     */
    public boolean hasReplyTo() {
        return replyTo != null;
    }

    /**
     * Returns true if the email has a replyToAlias field, false otherwise.
     *
     * @return boolean
     */
    public boolean hasReplyToAlias() {
        return replyToAlias != null;
    }

    /**
     * Returns true if the email has a subject field, false otherwise.
     *
     * @return boolean
     */
    public boolean hasSubject() {
        return subject != null;
    }

    /**
     * Returns true if the email has a text field, false otherwise.
     *
     * @return boolean
     */
    public boolean hasText() {
        return text != null;
    }

    /**
     * Returns true if the email has a to field, false otherwise.
     *
     * @return boolean
     */
    public boolean hasTo() {
        return to != null;
    }

    /**
     * Returns true if the email has a bcc field, false otherwise.
     *
     * @return boolean
     */
    public boolean hasBcc() {
        return bcc != null;
    }

    /**
     * Returns true if the email has a cc field, false otherwise.
     *
     * @return boolean
     */
    public boolean hasCc() {
        return cc != null;
    }

    // getters and setters
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromAlias() {
        return fromAlias;
    }

    public void setFromAlias(String fromAlias) {
        this.fromAlias = fromAlias;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getReplyToAlias() {
        return replyToAlias;
    }

    public void setReplyToAlias(String replyToAlias) {
        this.replyToAlias = replyToAlias;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String... to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String... cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String... bcc) {
        this.bcc = bcc;
    }

	public boolean isHtml() {
		return html;
	}

	public void setHtml(boolean html) {
		this.html = html;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}