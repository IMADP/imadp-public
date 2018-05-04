package com.imadp.service.commerce.subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * SubscriptionStatus
 *
 * Enumerated values of a subscription status codes.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public enum SubscriptionStatus {
	UNKNOWN("unknown"),
	TRIALING("trialing"),
	ACTIVE("active"),
	PAST_DUE("past_due"),
	UNPAID("unpaid"),
	CANCELLED("canceled");

	// logger
	private final static Logger LOGGER = LoggerFactory.getLogger(SubscriptionStatus.class);

	/**
	 * Returns a SubscriptionStatus from a status.
	 *
	 * @param status
	 * @return SubscriptionStatus
	 */
	public static SubscriptionStatus fromStatus(String status) {
		for(SubscriptionStatus subscriptionStatus : SubscriptionStatus.values())
			if(subscriptionStatus.getStatus().equalsIgnoreCase(status))
				return subscriptionStatus;

		LOGGER.warn("Unknown SubscriptionStatus [{}]", status);
		return UNKNOWN;
	}

	// properties
	private String status;

	// constructor
	SubscriptionStatus(String status) {
		this.status = status;
	}

	// getters
	public String getStatus() {
		return status;
	}

}