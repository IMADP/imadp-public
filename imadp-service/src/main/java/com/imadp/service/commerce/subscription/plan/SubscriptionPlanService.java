package com.imadp.service.commerce.subscription.plan;

import com.imadp.service.PersistableService;


/**
 * SubscriptionPlanService
 *
 * Provides common retrieval operations for SubscriptionPlan objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface SubscriptionPlanService extends PersistableService<SubscriptionPlan> {

	/**
	 * Finds a SubscriptionPlan by id, or null if none was found.
	 *
	 * @param subscriptionId
	 * @return SubscriptionPlan
	 */
	public SubscriptionPlan findFirstBySubscriptionId(String subscriptionId);

}