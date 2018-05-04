package com.imadp.service.commerce.order.shipping;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;

import com.imadp.core.CoreComponent;
import com.imadp.core.email.Email;
import com.imadp.service.commerce.order.Order;
import com.imadp.service.commerce.order.shipping.ShippingTransaction.ShippingTransactionStatus;
import com.imadp.service.email.PersistableEmailService;



/**
 * ShippingServiceEmailImpl
 *
 * Implementation of the ShippingService that generates a shipping request email.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ShippingServiceEmailImpl extends CoreComponent implements ShippingService {
	private PersistableEmailService persistableEmailService;
	private String[] emailCc;
	private String[] emailBcc;
	private String emailTo;
	private String emailTemplate;
	private boolean emailHtml = true;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(persistableEmailService);
		Validate.notNull(emailTo);
		Validate.notNull(emailTemplate);
	}

	@Override
	public ShippingTransaction requestShipping(Order order) {

		// generate a shipping requested email
		sendShippingRequestedEmail(order);

		// return the shipping transaction
		ShippingTransaction shippingTransaction = new ShippingTransaction();
		shippingTransaction.setDate(new DateTime());
		shippingTransaction.setOrder(order);
		shippingTransaction.setTransactionId(order.getUid());
		shippingTransaction.setStatus(ShippingTransactionStatus.REQUESTED);
		return shippingTransaction;
	}

	/**
	 * Sends a shipping requested email.
	 *
	 * @param order
	 */
	private void sendShippingRequestedEmail(Order order) {
		Map<String, Object> model = new HashMap<>(1, 1);
		model.put("order", order);

		// build email
		Email email = persistableEmailService.buildEmail(emailTemplate, order.getLocale(), model);
		email.setTo(emailTo);
		email.setCc(emailCc);
		email.setBcc(emailBcc);
		email.setHtml(emailHtml);

		persistableEmailService.send(email);
	}

	// getters and setters
	public PersistableEmailService getPersistableEmailService() {
		return persistableEmailService;
	}

	public void setPersistableEmailService(PersistableEmailService persistableEmailService) {
		this.persistableEmailService = persistableEmailService;
	}

	public String getEmailTemplate() {
		return emailTemplate;
	}

	public void setEmailTemplate(String emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	public String[] getEmailBcc() {
		return emailBcc;
	}

	public void setEmailBcc(String[] emailBcc) {
		this.emailBcc = emailBcc;
	}

	public String[] getEmailCc() {
		return emailCc;
	}

	public void setEmailCc(String[] emailCc) {
		this.emailCc = emailCc;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public boolean isEmailHtml() {
		return emailHtml;
	}

	public void setEmailHtml(boolean emailHtml) {
		this.emailHtml = emailHtml;
	}

}