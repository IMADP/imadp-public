package com.imadp.service.commerce.subscription.plan;

import com.imadp.service.PersistableServiceImpl;

/**
 * SubscriptionPlanServiceImpl
 *
 * The standard implementation of the SubscriptionPlanService.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class SubscriptionPlanServiceImpl extends PersistableServiceImpl<SubscriptionPlan> implements SubscriptionPlanService {

	@Override
	public SubscriptionPlan findFirstBySubscriptionId(String subscriptionId) {
		return findFirstBy(SubscriptionPlan.SUBSCRIPTION_ID, subscriptionId);
	}

}