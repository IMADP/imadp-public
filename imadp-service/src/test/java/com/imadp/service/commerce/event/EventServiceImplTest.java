package com.imadp.service.commerce.event;

import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.test.IMADPServiceTestCase;


/**
 * EventServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class EventServiceImplTest extends IMADPServiceTestCase {
	Event event;

	@Override
	public void before() throws Exception {
		super.before();

		event = new Event();
		event.setEventId("eventId");
		event.setEventType("eventType");
		event.setEventData("eventData");
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(event, eventService);
	}

}