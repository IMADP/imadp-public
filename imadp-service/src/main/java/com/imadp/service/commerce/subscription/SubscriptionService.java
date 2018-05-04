package com.imadp.service.commerce.subscription;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.PersistableUserService;
import com.imadp.service.user.User;


/**
 * SubscriptionService
 *
 * Provides common retrieval operations for Subscription objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface SubscriptionService extends PersistableUserService<Subscription> {

	/**
	 * Finds the first subscription by customerId, or null if none was found.
	 *
	 * @param customerId
	 * @return Subscription
	 */
	public Subscription findFirstByCustomerId(String customerId);

    /**
     * Returns a list of subscriptions by referrer and criteriaParams.
     *
     * @param referrer
     * @param criteriaParams
     * @return Subscription
     */
	public List<Subscription> findByReferrer(User referrer, CriteriaParams<Subscription> criteriaParams);

	/**
	 * Finds the count of subscriptions by referrer.
	 *
	 * @param referrer
	 * @return long
	 */
	public long findCountByReferrer(User referrer);

	/**
	 * Finds the count of subscriptions by referrer and subscriptionStatus.
	 *
	 * @param referrer
	 * @param subscriptionStatus
	 * @return long
	 */
	public long findCountByReferrer(User referrer, SubscriptionStatus subscriptionStatus);

}