package com.imadp.core.email;

import org.junit.Test;

import com.imadp.core.email.Email;
import com.imadp.core.test.IMADPCoreTestCase;


/**
 * EmailSenderMockSuccessfulImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class EmailSenderMockSuccessfulImplTest extends IMADPCoreTestCase {
		
	@Test 
	public void send() throws Exception {		
		emailSenderMockSuccessful.send(new Email());
	}	

}
