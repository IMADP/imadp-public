package com.imadp.service.email;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.joda.time.DateTime;
import org.junit.Test;

import com.imadp.core.email.Email;
import com.imadp.service.ServiceTestUtil;
import com.imadp.service.email.PersistableEmail.PersistableEmailStatus;
import com.imadp.service.test.IMADPServiceTestCase;


/**
 * PersistableEmailServiceAsynchronousImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class PersistableEmailServiceAsynchronousImplTest extends IMADPServiceTestCase {
	PersistableEmail persistableEmail;

	@Override
	public void before() throws Exception {
		super.before();

		persistableEmail = new PersistableEmail();
		persistableEmail.setBcc("bcc");
		persistableEmail.setCc("cc");
		persistableEmail.setFrom("from");
		persistableEmail.setFromAlias("fromAlias");
		persistableEmail.setReplyTo("replyTo");
		persistableEmail.setSubject("subject");
		persistableEmail.setText("text");
		persistableEmail.setTo("test@test.test");
		persistableEmail.setTemplate("template");
		persistableEmail.setErrorMessage("errorMessage");
		persistableEmail.setDate(new DateTime());
		persistableEmail.setStatus(PersistableEmailStatus.UNSENT);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(persistableEmail, persistableEmailServiceAsynchronous);
	}

	@Test
	public void commonPersistableOperationsJoin() {
		Email email = persistableEmail.getEmail();
		email.setBcc("bcc1", "bcc2", "bcc3");
		email.setCc("cc1", "cc2", "cc3");
		email.setTo("to1", "to2", "to3");

		persistableEmailServiceSynchronous.save(persistableEmail);

		assertEquals("bcc1;bcc2;bcc3", persistableEmail.getBcc());
		assertEquals("cc1;cc2;cc3", persistableEmail.getCc());
		assertEquals("to1;to2;to3", persistableEmail.getTo());

		ServiceTestUtil.assertPersistable(persistableEmail, persistableEmailServiceSynchronous);
	}

	@Test
	public void commonPersistableOperationsSplit() {
		persistableEmail.setBcc("bcc1;bcc2;bcc3");
		persistableEmail.setCc("cc1;cc2;cc3");
		persistableEmail.setTo("to1;to2;to3");

		persistableEmailServiceSynchronous.save(persistableEmail);

		assertEquals("bcc1", persistableEmail.getEmail().getBcc()[0]);
		assertEquals("bcc2", persistableEmail.getEmail().getBcc()[1]);
		assertEquals("bcc3", persistableEmail.getEmail().getBcc()[2]);
		assertEquals("cc1", persistableEmail.getEmail().getCc()[0]);
		assertEquals("cc2", persistableEmail.getEmail().getCc()[1]);
		assertEquals("cc3", persistableEmail.getEmail().getCc()[2]);
		assertEquals("to1", persistableEmail.getEmail().getTo()[0]);
		assertEquals("to2", persistableEmail.getEmail().getTo()[1]);
		assertEquals("to3", persistableEmail.getEmail().getTo()[2]);

		ServiceTestUtil.assertPersistable(persistableEmail, persistableEmailServiceSynchronous);
	}

	@Test
	public void sendEmail() {
		Email email = new Email();
		email.setBcc("bcc");
		email.setCc("cc");
		email.setFrom("from");
		email.setFromAlias("fromAlias");
		email.setReplyTo("replyTo");
		email.setSubject("subject");
		email.setText("text");
		email.setTo("test@test.test");

		assertEquals(0, persistableEmailServiceAsynchronous.findCount());
		assertEquals(0, persistableEmailServiceAsynchronous.findUnsentCount());

		Long id = persistableEmailServiceAsynchronous.send(email);
		PersistableEmail persistableEmail = persistableEmailServiceAsynchronous.get(id);

		assertEquals(1, persistableEmailServiceAsynchronous.findCount());
		assertEquals(1, persistableEmailServiceAsynchronous.findUnsentCount());

		assertEquals(email.getTo()[0], persistableEmail.getTo());
		assertEquals(email.getFrom(), persistableEmail.getFrom());
		assertEquals(email.getFromAlias(), persistableEmail.getFromAlias());
		assertEquals(email.getSubject(), persistableEmail.getSubject());
		assertEquals(email.getText(), persistableEmail.getText());
		assertEquals(email.getTemplate(), persistableEmail.getTemplate());
		assertEquals(PersistableEmailStatus.UNSENT, persistableEmail.getStatus());
		assertNotNull(persistableEmail.getDate());
		assertNull(persistableEmail.getErrorMessage());

		persistableEmailServiceAsynchronous.sendUnsentEmails();

		assertEquals(1, persistableEmailServiceAsynchronous.findCount());
		assertEquals(0, persistableEmailServiceAsynchronous.findUnsentCount());

		persistableEmail = persistableEmailServiceAsynchronous.get(id);

		assertEquals(PersistableEmailStatus.SENT, persistableEmail.getStatus());
	}

}