package com.imadp.service.email;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.imadp.core.email.Email;
import com.imadp.core.email.EmailException;
import com.imadp.core.email.EmailSender;
import com.imadp.core.template.TemplateDocument;
import com.imadp.core.template.TemplateEngine;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.PersistableServiceImpl;
import com.imadp.service.email.PersistableEmail.PersistableEmailStatus;

/**
 * AbstractPersistableEmailServiceImpl
 *
 * Provides common functionality for persistable email service implementations.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractPersistableEmailServiceImpl extends PersistableServiceImpl<PersistableEmail>
implements PersistableEmailService {

	// static property names
	private static final String FROM = "from";
	private static final String FROM_ALIAS = "fromAlias";
	private static final String REPLY_TO = "replyTo";
	private static final String REPLY_TO_ALIAS = "replyToAlias";
	private static final String SUBJECT = "subject";

	// properties
	private EmailSender emailSender;
	private TemplateEngine templateEngine;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(emailSender);
		Validate.notNull(templateEngine);
	}

	@Override
	public final Email buildEmail(String template, Locale locale) {
		return buildEmail(template, locale, null);
	}

	@Override
	public final Email buildEmail(String template, Locale locale, Map<String, ?> model) {
		TemplateDocument document = templateEngine.createDocument(template + ".vm", locale, model);

		Email email = new Email();
		email.setHtml(true);
		email.setTemplate(template);
		email.setText(document.getContent());
		email.setSubject(document.getProperty(SUBJECT));
		email.setFrom(document.getProperty(FROM));
		email.setFromAlias(document.getProperty(FROM_ALIAS));
		email.setReplyTo(document.getProperty(REPLY_TO));
		email.setReplyToAlias(document.getProperty(REPLY_TO_ALIAS));
		return email;
	}

	@Override
	public final Long send(Email email) {
		return onSend(new PersistableEmail(email)).getId();
	}

	/**
	 * Sends a email, saving it with status information.
	 *
	 * @param persistableEmail
	 */
	protected final void sendAndSave(PersistableEmail persistableEmail) {
		try
		{
			emailSender.send(persistableEmail.getEmail());
			persistableEmail.setStatus(PersistableEmailStatus.SENT);
		}
		catch(EmailException emailException)
		{
			Throwable rootCause = ExceptionUtils.getRootCause(emailException);
			persistableEmail.setErrorMessage(rootCause != null ? rootCause.getMessage() : emailException.getMessage());
			persistableEmail.setStatus(PersistableEmailStatus.ERROR);

			logger.warn("Unable to send email with uid [{}] because of errorMessage [{}]",
					persistableEmail.getUid(), persistableEmail.getErrorMessage());
		}

		// save the email
		save(persistableEmail);
	}

	@Override
	public List<PersistableEmail> findUnsent(CriteriaParams<PersistableEmail> criteriaParams) {
		return findBy(PersistableEmail.STATUS, PersistableEmailStatus.UNSENT, criteriaParams);
	}

	@Override
	public long findUnsentCount(){
		return findCountBy(PersistableEmail.STATUS, PersistableEmailStatus.UNSENT);
	}

	@Override
	public boolean isFoundBy(String to, String template) {
		FindCriteria<PersistableEmail> findCriteria = findCriteriaBuilder(CriteriaParams.<PersistableEmail>of(Results.ONE))
			.whereEqualTo(PersistableEmail.TO, to)
			.whereEqualTo(PersistableEmail.TEMPLATE, template)
			.build();

		return findCountBy(findCriteria) > 0;
	}

	/**
	 * Hook called when a persistable email should be sent.
	 *
	 * @param persistableEmail
	 * @return PersistableEmail
	 */
	protected abstract PersistableEmail onSend(PersistableEmail persistableEmail);

	// getters and setters
	public EmailSender getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(EmailSender emailSender) {
		this.emailSender = emailSender;
	}

	public TemplateEngine getTemplateEngine() {
		return templateEngine;
	}

	public void setTemplateEngine(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

}