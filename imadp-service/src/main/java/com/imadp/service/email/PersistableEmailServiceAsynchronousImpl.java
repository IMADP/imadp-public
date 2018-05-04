package com.imadp.service.email;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;

/**
 * PersistableEmailServiceAsynchronousImpl
 *
 * An implementation of the PersistableEmailService which asynchronously sends and persists an email.
 * A scheduler is needed to routinely execute the sendUnsentEmails method.
 * 
 * @note This service is declared final as it was not designed with inheritance in mind.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public final class PersistableEmailServiceAsynchronousImpl extends AbstractPersistableEmailServiceImpl {	
	
	@Override
	protected PersistableEmail onSend(PersistableEmail persistableEmail) {
		// save the email for later delivery
		save(persistableEmail);
		
		return persistableEmail;
	}
	
	/**
	 * Sends all emails awaiting asynchronous sending.
	 * 
	 */
	public void sendUnsentEmails() {
		CriteriaParams<PersistableEmail> criteriaParams = CriteriaParams.<PersistableEmail>of(
				Results.ALL, Order.asc(PersistableEmail.TIME_CREATED));
		
		List<PersistableEmail> persistableEmails = findUnsent(criteriaParams);
		
		logger.info("Sending [{}] unsent emails", persistableEmails.size());
		
		for(PersistableEmail persistableEmail : persistableEmails)
			sendAndSave(persistableEmail);
		
		logger.info("Sent [{}] unsent emails", persistableEmails.size());
	}
		
}