package com.imadp.core.email;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.Validate;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.imadp.core.CoreComponent;


/**
 * EmailSenderJavaMailImpl
 * 
 * The JavaMail implementation of the EmailSender.
 * 
 * @note This component is declared final as it was not designed with inheritance in mind.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public final class EmailSenderJavaMailImpl extends CoreComponent implements EmailSender {
	private JavaMailSender mailSender;
	
	@Override
	protected void onInit() {
		super.onInit();
		
		Validate.notNull(mailSender);
	}
	
	@Override
	public void send(Email email) throws EmailException {
		try
		{
			mailSender.send(getMimeMessagePreparator(email));
		}
		catch(MailException mailException)
		{
			throw new EmailException("Unable to send email", mailException);
		}
	}
	
	/**
	 * Returns a MimeMessagePreparator built from an Email object.
	 * 
	 * @param email
	 * @return MimeMessagePreparator
	 */
	private static final MimeMessagePreparator getMimeMessagePreparator(final Email email) {
		return new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				
				if(email.hasTo())
					message.setTo(email.getTo()); 
				
				if(email.hasBcc())
					message.setBcc(email.getBcc()); 
				
				if(email.hasCc())
					message.setCc(email.getCc());
				
				if(email.hasSubject())
					message.setSubject(email.getSubject()); 
				
				if(email.hasText())
					message.setText(email.getText(), email.isHtml());
				
				if(email.hasFrom())
					message.setFrom(email.getFrom(), email.hasFromAlias() ?
							email.getFromAlias() : email.getFrom()); 
				
				if(email.hasReplyTo())
					message.setReplyTo(email.getReplyTo(), email.hasReplyToAlias() ?
							email.getReplyToAlias() : email.getReplyTo());
	      	}
		};
	}
	
	// getters and setters
	public JavaMailSender getMailSender() {
		return mailSender;
	}
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}		
	
}
