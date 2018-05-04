package com.imadp.service.commerce.subscription;

import java.util.List;

import org.joda.time.Period;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.commerce.CommerceFacade;
import com.imadp.service.commerce.subscription.plan.SubscriptionPlan;
import com.imadp.service.user.User;
import com.stripe.exception.CardException;

/**
 * SubscriptionFacade
 *
 * This facade coordinates subscription services.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface SubscriptionFacade extends CommerceFacade {

	/**
	 * Gets a subscriptionPlan by subscriptionId.
	 *
	 * @param subscriptionId
	 * @return SubscriptionPlan
	 */
	public SubscriptionPlan getSubscriptionPlan(String subscriptionId);

	/**
	 * Gets a subscription by uid and user.
	 *
	 * @param user
	 * @return Subscription
	 */
	public Subscription getSubscription(User user);

	/**
	 * Returns a list of subscription referrals for a given user.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Subscription>
	 */
	public List<Subscription> getReferrals(User user, CriteriaParams<Subscription> criteriaParams);

	/**
	 * Returns a list of subscription referrals for a given user.
	 *
	 * @param user
	 * @return long
	 */
	public long getReferralCount(User user);

	/**
	 * Creates a trial Subscription with the given trial period.
	 *
	 * @param user
	 * @param trialPeriod
	 */
	public void createSubscription(User user, Period trialPeriod);

	/**
	 * Creates a trial Subscription with the given trial period and a referrer.
	 *
	 * @param user
	 * @param trialPeriod
	 * @param referrer
	 */
	public void createSubscription(User user, Period trialPeriod, User referrer);

	/**
	 * Updates a subscription with a given subscriptionPlan and customerId.
	 *
	 * @param subscription
	 * @param subscriptionPlan
	 * @param customerId
	 * @param email
	 * @param coupon
	 * @throws CardException
	 * @throws SubscriptionException
	 */
	public void updateSubscription(Subscription subscription, SubscriptionPlan subscriptionPlan, String customerId, String email, String coupon)
			throws CardException, SubscriptionException;

	/**
	 * Cancels a Subscription.
	 *
	 * @param subscription
	 * @throws SubscriptionException
	 */
	public void cancelSubscription(Subscription subscription) throws SubscriptionException;

}