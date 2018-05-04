package com.imadp.core.email;

import com.imadp.core.CoreComponent;



/**
 * EmailSenderMockSuccessfulImpl
 * 
 * A mock implementation of the EmailSender which will never send emails or throw an EmailException.
 * 
 * @note This component is provided for testing purposes only. It should NOT be used in production.
 * @note This component is declared final as it was not designed with inheritance in mind.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public final class EmailSenderMockSuccessfulImpl extends CoreComponent implements EmailSender {
	
	@Override
	protected void onInit() {
		super.onInit();
	
		logger.warn("This is a mock implementation: NO EMAILS WILL BE SENT");
	}
	
	@Override
	public void send(Email email) throws EmailException {
		
	}
	
}
