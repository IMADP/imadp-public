package com.imadp.service.log;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.joda.time.DateTime;
import org.junit.Test;

import ch.qos.logback.classic.Level;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.ServiceTestUtil;
import com.imadp.service.test.IMADPServiceTestCase;


/**
 * LogEntryServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class LogEntryServiceImplTest extends IMADPServiceTestCase {
	LogEntry logEntry;

	@Override
	public void before() throws Exception {
		super.before();

		logEntry = new LogEntry();
		logEntry.setThrowableCauseClass("throwableCauseClass");
		logEntry.setThrowableCauseMessage("throwableCauseMessage");
		logEntry.setThrowableCauseSource("throwableCauseSource");
		logEntry.setLevel("level");
		logEntry.setLogger("logger");
		logEntry.setMessage("message");
		logEntry.setThread("thread");
		logEntry.setTimestamp(new DateTime());
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(logEntry, logEntryService);
	}

	@Test
	public void appenderOperationsNoThrowableCause() {
		assertEquals(0, logEntryService.findCount());

		String message = "Test message";

		logger.error(message);

		assertEquals(1, logEntryService.findCount());

		LogEntry logEntry = logEntryService.findBy(CriteriaParams.<LogEntry>of(Results.ALL)).get(0);

		assertEquals(message, logEntry.getMessage());
		assertEquals(Level.ERROR.toString(), logEntry.getLevel());
		assertEquals(LogEntryServiceImplTest.class.getName(), logEntry.getLogger());
		assertNull(logEntry.getThrowableCauseClass());
		assertNull(logEntry.getThrowableCauseMessage());
		assertNull(logEntry.getThrowableCauseSource());
	}

	@Test
	public void appenderOperationsSingleThrowableCause() {
		assertEquals(0, logEntryService.findCount());

		String message = "Test message";

		Exception exceptionOne = new Exception("Test exception one");

		logger.error(message, exceptionOne);

		assertEquals(1, logEntryService.findCount());

		LogEntry logEntry = logEntryService.findBy(CriteriaParams.<LogEntry>of(Results.ALL)).get(0);

		assertEquals(message, logEntry.getMessage());
		assertEquals(Level.ERROR.toString(), logEntry.getLevel());
		assertEquals(LogEntryServiceImplTest.class.getName(), logEntry.getLogger());
		assertEquals(Exception.class.getName(), logEntry.getThrowableCauseClass());
		assertEquals(exceptionOne.getMessage(), logEntry.getThrowableCauseMessage());
		assertEquals("com.imadp.service.log.LogEntryServiceImplTest." +
				"appenderOperationsSingleThrowableCause(LogEntryServiceImplTest.java:74)",
				logEntry.getThrowableCauseSource());
	}

	@Test
	public void appenderOperationsMultipleThrowableCauses() {
		assertEquals(0, logEntryService.findCount());

		String message = "Test message";

		Exception exceptionOne = new IllegalArgumentException("Test exception one");
		Exception exceptionTwo = new IllegalStateException("Test exception two", exceptionOne);
		Exception exceptionThree = new Exception("Test exception three", exceptionTwo);

		logger.error(message, exceptionThree);

		assertEquals(1, logEntryService.findCount());

		LogEntry logEntry = logEntryService.findBy(CriteriaParams.<LogEntry>of(Results.ALL)).get(0);

		assertEquals(message, logEntry.getMessage());
		assertEquals(Level.ERROR.toString(), logEntry.getLevel());
		assertEquals(LogEntryServiceImplTest.class.getName(), logEntry.getLogger());
		assertEquals(IllegalArgumentException.class.getName(), logEntry.getThrowableCauseClass());
		assertEquals(exceptionOne.getMessage(), logEntry.getThrowableCauseMessage());
		assertEquals("com.imadp.service.log.LogEntryServiceImplTest." +
				"appenderOperationsMultipleThrowableCauses(LogEntryServiceImplTest.java:98)",
				logEntry.getThrowableCauseSource());
	}

}