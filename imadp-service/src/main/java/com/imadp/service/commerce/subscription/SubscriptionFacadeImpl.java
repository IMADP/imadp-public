package com.imadp.service.commerce.subscription;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.joda.time.Period;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.commerce.CommerceFacadeImpl;
import com.imadp.service.commerce.subscription.plan.SubscriptionPlan;
import com.imadp.service.commerce.subscription.plan.SubscriptionPlanService;
import com.imadp.service.user.User;
import com.stripe.exception.CardException;
import com.stripe.model.Customer;

/**
 * SubscriptionFacadeImpl
 *
 * The standard SubscriptionFacade implementation.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class SubscriptionFacadeImpl extends CommerceFacadeImpl implements SubscriptionFacade {
	protected SubscriptionService subscriptionService;
	protected SubscriptionPlanService subscriptionPlanService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(subscriptionService);
		Validate.notNull(subscriptionPlanService);
	}

	@Override
	public SubscriptionPlan getSubscriptionPlan(String subscriptionId) {
		return subscriptionPlanService.findFirstBySubscriptionId(subscriptionId);
	}

	@Override
	public Subscription getSubscription(User user) {
		return subscriptionService.findFirstByUser(user);
	}

	@Override
	public List<Subscription> getReferrals(User user, CriteriaParams<Subscription> criteriaParams) {
		return subscriptionService.findByReferrer(user, criteriaParams);
	}

	@Override
	public long getReferralCount(User user) {
		return subscriptionService.findCountByReferrer(user);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void createSubscription(User user, Period trialPeriod) {
		createSubscription(user, trialPeriod, null);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void createSubscription(User user, Period trialPeriod, User referrer) {
		try
		{
			Subscription subscription = new Subscription(user, referrer);
			String id = subscription.getUid();

			subscription.log("[#{}]: Creating subscription for user [{}]", id, user.getUid());

			subscription.createSubscription(trialPeriod);

			subscription.log("[#{}]: Saving subscription", id);

			subscriptionService.save(subscription);
			onSubscriptionSaved(subscription);
		}
		catch(Exception exception)
		{
			onSubscriptionException(new SubscriptionException("CreateSubscription error", exception, user));
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void updateSubscription(Subscription subscription, SubscriptionPlan subscriptionPlan, String customerId, String email, String coupon)
			throws CardException, SubscriptionException {
		try
		{
			String id = subscription.getUid();
			String subscriptionPlanId = subscriptionPlan.getSubscriptionId();

			subscription.log("[#{}]: Updating subscription to SubscriptionPlan [{}]", id, subscriptionPlanId);
			subscription.log("[#{}]: Creating customer with id [{}]", id, customerId);

			Map<String, Object> customerParams = Maps.newHashMap();
			customerParams.put("description", subscription.getUid());
			customerParams.put("card", customerId);
			customerParams.put("email", email);
			com.stripe.model.Customer customer = Customer.create(customerParams);

			subscription.log("[#{}]: Updating subscription", id);

			Map<String, Object> subscriptionParams = Maps.newHashMap();
			subscriptionParams.put("plan", subscriptionPlan.getSubscriptionId());

			if(coupon != null)
				subscriptionParams.put("coupon", coupon);

			subscription.setCustomerId(customer.getId());
			subscription.updateSubscription(customer.updateSubscription(subscriptionParams), subscriptionPlan);

			subscription.log("[#{}]: Saving subscription", id);

			subscriptionService.save(subscription);
			onSubscriptionSaved(subscription);
		}
		catch(CardException cardException)
		{
			throw cardException;
		}
		catch(Exception exception)
		{
			onSubscriptionException(new SubscriptionException("UpdateSubscription error", exception, subscription));
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void cancelSubscription(Subscription subscription) throws SubscriptionException {
		try
		{
			String id = subscription.getUid();
			String subscriptionPlanId = subscription.getSubscriptionPlan().getSubscriptionId();

			subscription.log("[#{}]: Cancelling subscription with SubscriptionPlan [{}]", id, subscriptionPlanId);
			subscription.log("[#{}]: Retrieving customer with id [{}]", id, subscription.getCustomerId());

			com.stripe.model.Customer customer = Customer.retrieve(subscription.getCustomerId());

			subscription.log("[#{}]: Cancelling subscription", id);

			customer.cancelSubscription();
			subscription.cancelSubscription();

			subscription.log("[#{}]: Saving subscription", id);

			subscriptionService.save(subscription);
			onSubscriptionSaved(subscription);
		}
		catch(Exception exception)
		{
			onSubscriptionException(new SubscriptionException("CancelSubscription error", exception, subscription));
		}
	}

	@Override
	public void onCustomerSubscriptionUpdated(com.stripe.model.Event event, com.stripe.model.Subscription subscriptionObject) {
		try
		{
			Subscription subscription = subscriptionService.findFirstByCustomerId(subscriptionObject.getCustomer());
			String id = subscription.getUid();

			subscription.log("[#{}]: Updating customer subscription", id);

			SubscriptionPlan subscriptionPlan = subscriptionPlanService.findFirstBySubscriptionId(subscriptionObject.getPlan().getId());
			subscription.updateSubscription(subscriptionObject, subscriptionPlan);

			subscription.log("[#{}]: Saving subscription", id);

			subscriptionService.save(subscription);
			onSubscriptionSaved(subscription);
		}
		catch(Exception exception)
		{
			onSubscriptionException(new SubscriptionException("CustomerSubscriptionUpdated error", exception, event.getId()));
		}
	}

	/**
	 * Hook called whenever a subscription is saved.
	 *
	 * @param subscription
	 * @throws Exception
	 */
	protected void onSubscriptionSaved(Subscription subscription) throws Exception {

	}

	@Override
	protected final void onEventException(String eventId, Exception exception) {
		onSubscriptionException(new SubscriptionException("Error processing event", exception, eventId));
	}

	/**
	 * Called whenever a subscription exception occurs, by default the exception is simply rethrown.
	 * This method can be overridden to allow additional control over subscription errors.
	 *
	 * @param subscriptionException
	 */
	protected void onSubscriptionException(SubscriptionException subscriptionException) {
		throw subscriptionException;
	}

	// getters and setters
	public SubscriptionPlanService getSubscriptionPlanService() {
		return subscriptionPlanService;
	}

	public void setSubscriptionPlanService(SubscriptionPlanService subscriptionPlanService) {
		this.subscriptionPlanService = subscriptionPlanService;
	}

	public SubscriptionService getSubscriptionService() {
		return subscriptionService;
	}

	public void setSubscriptionService(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}

}