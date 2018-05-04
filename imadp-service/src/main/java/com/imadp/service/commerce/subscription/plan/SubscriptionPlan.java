package com.imadp.service.commerce.subscription.plan;

import com.imadp.core.Property;
import com.imadp.dao.AbstractPersistable;


/**
 * SubscriptionPlan
 *
 * A representation of an external subscription plan.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class SubscriptionPlan extends AbstractPersistable {

	// static properties
	public static final Property<SubscriptionPlan, String> SUBSCRIPTION_ID = Property.of("subscriptionId");

	// properties
	private String subscriptionId;

	// getters and setters
	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

}
