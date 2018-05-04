package com.imadp.service.email;


/**
 * PersistableEmailServiceSynchronousImpl
 *
 * An implementation of the PersistableEmailService which synchronously sends and persists an email.
 * 
 * @note This service is declared final as it was not designed with inheritance in mind.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public final class PersistableEmailServiceSynchronousImpl extends AbstractPersistableEmailServiceImpl {	

	@Override
	protected PersistableEmail onSend(PersistableEmail persistableEmail) {
		// send and save the email
		sendAndSave(persistableEmail);
		
		return persistableEmail;
	}
	
}