package com.imadp.core.email;

import org.junit.Test;
import org.springframework.test.annotation.ExpectedException;

import com.imadp.core.email.Email;
import com.imadp.core.email.EmailException;
import com.imadp.core.test.IMADPCoreTestCase;


/**
 * EmailSenderJavaMailImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class EmailSenderJavaMailImplTest extends IMADPCoreTestCase {
		
	@Test 
	@ExpectedException(EmailException.class)
	public void send() throws Exception {		
		emailSenderJavaMail.send(new Email());
	}	

}
