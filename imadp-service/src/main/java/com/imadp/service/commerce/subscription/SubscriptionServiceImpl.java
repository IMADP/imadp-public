package com.imadp.service.commerce.subscription;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.user.PersistableUserServiceImpl;
import com.imadp.service.user.User;

/**
 * SubscriptionServiceImpl
 *
 * The standard implementation of the SubscriptionService.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class SubscriptionServiceImpl extends PersistableUserServiceImpl<Subscription> implements SubscriptionService {

	@Override
	public Subscription findFirstByCustomerId(String customerId) {
		return findFirstBy(Subscription.CUSTOMER_ID, customerId);
	}

	@Override
	public List<Subscription> findByReferrer(User referrer, CriteriaParams<Subscription> criteriaParams) {
		return findBy(Subscription.REFERRER, referrer, criteriaParams);
	}

	@Override
	public long findCountByReferrer(User referrer) {
		return findCountBy(Subscription.REFERRER, referrer);
	}

	@Override
	public long findCountByReferrer(User referrer, SubscriptionStatus subscriptionStatus) {
		FindCriteria<Subscription> findCriteria = dao.findCriteriaBuilder(Results.ONE)
				.whereEqualTo(Subscription.REFERRER, referrer)
				.whereEqualTo(Subscription.SUBSCRIPTION_STATUS, subscriptionStatus).build();

		return findCountBy(findCriteria);
	}

}