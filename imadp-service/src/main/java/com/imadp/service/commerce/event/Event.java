package com.imadp.service.commerce.event;

import com.imadp.core.Property;
import com.imadp.dao.AbstractPersistable;


/**
 * Event
 *
 * An event notification.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Event extends AbstractPersistable {

	// static properties
	public static final Property<Event, String> EVENT_ID = Property.of("eventId");
	public static final Property<Event, String> EVENT_TYPE = Property.of("eventType");
	public static final Property<Event, String> EVENT_DATA = Property.of("eventData");

	// properties
	private String eventId;
	private String eventType;
	private String eventData;

	// constructor
	public Event() {

	}

	// constructor
	public Event(String eventId, String eventType, String eventData) {
		this.eventId = eventId;
		this.eventType = eventType;
		this.eventData = eventData;
	}

	// getters and setters
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventData() {
		return eventData;
	}

	public void setEventData(String eventData) {
		this.eventData = eventData;
	}

}
