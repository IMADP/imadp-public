package com.imadp.service.commerce.subscription.plan;

import org.junit.Test;

import com.imadp.service.ServiceTestUtil;
import com.imadp.service.test.IMADPServiceTestCase;
import com.imadp.service.user.User;


/**
 * SubscriptionPlanServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class SubscriptionPlanServiceImplTest extends IMADPServiceTestCase {
	SubscriptionPlan subscriptionPlan;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		subscriptionPlan = new SubscriptionPlan();
		subscriptionPlan.setSubscriptionId("subscriptionId");

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(subscriptionPlan, subscriptionPlanService);
	}

}