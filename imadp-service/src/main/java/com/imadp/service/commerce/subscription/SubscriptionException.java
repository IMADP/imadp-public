package com.imadp.service.commerce.subscription;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.imadp.service.user.User;



/**
 * SubscriptionException
 *
 * The exception thrown when unable to process a subscription through the subscription workflow.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class SubscriptionException extends RuntimeException {
	private User user;
	private String eventId;
	private Subscription subscription;

	// constructor
	public SubscriptionException(String message, Exception exception, User user) {
		super(message, exception);
		this.user = user;
	}

	// constructor
	public SubscriptionException(String message, Exception exception, String eventId) {
		super(message, exception);
		this.eventId = eventId;
	}

	// constructor
	public SubscriptionException(String message, Exception exception, Subscription subscription) {
		super(message, exception);
		this.subscription = subscription;
	}

	// getters and setters
	public Subscription getSubscription() {
		return subscription;
	}

	public User getUser() {
		return user;
	}

	public String getEventId() {
		return eventId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}