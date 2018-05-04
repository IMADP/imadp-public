package com.tracktacular.service.account;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.Validate;

import com.google.common.collect.Maps;
import com.imadp.core.email.Email;
import com.imadp.service.commerce.subscription.Subscription;
import com.imadp.service.commerce.subscription.SubscriptionException;
import com.imadp.service.commerce.subscription.SubscriptionFacadeImpl;
import com.imadp.service.commerce.subscription.SubscriptionStatus;
import com.imadp.service.email.PersistableEmailService;
import com.imadp.service.user.User;
import com.stripe.model.Customer;
import com.stripe.model.Discount;
import com.tracktacular.service.TracktacularEmail;


/**
 * TracktacularSubscriptionFacadeImpl
 *
 * The Tracktacular implementation of the subscription facade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class TracktacularSubscriptionFacadeImpl extends SubscriptionFacadeImpl {
	private PersistableEmailService persistableEmailService;
	private String subscriptionErrorEmailAddress;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(persistableEmailService);
		Validate.notNull(subscriptionErrorEmailAddress);
	}

	@Override
	protected void onSubscriptionSaved(Subscription subscription) throws Exception {

		// update discounts for the user who was updated
		updateDiscounts(subscription.getUser());

		// update discounts for the referrering user
		updateDiscounts(subscription.getReferrer());
	}

	/**
	 * Updates the discounts for a given user.
	 * Ensures that the number of active referrals matches the percentage discount associated with the user's subscription.
	 *
	 * @param user
	 * @throws Exception
	 */
	private void updateDiscounts(User user) throws Exception {

		// if there is no user, return
		if(user == null)
			return;

		// retrieve the user's subscription
		Subscription subscription = subscriptionService.findFirstByUser(user);

		// if the user subscription does not have a customer yet, return
		if(subscription.getCustomerId() == null)
			return;

		// check the user's discount status
		Customer customer = Customer.retrieve(subscription.getCustomerId());
		long activeReferrals = subscriptionService.findCountByReferrer(user, SubscriptionStatus.ACTIVE);

		// if no referrals are active, ensure that no discounts are active
		if(activeReferrals == 0)
		{
			if(customer.getDiscount() == null)
				return;

			// delete any existing discount
			logger.info("Deleting discount for customer [{}]", subscription.getCustomerId());
			customer.deleteDiscount();
			return;
		}

		// check to make sure the discount applied matches the expected discount
		String couponId = activeReferrals > 4 ? "REFERRAL_5" : "REFERRAL_" + activeReferrals;
		Discount discount = customer.getDiscount();

		// update the discount if its incorrect
		if(discount == null || !discount.getCoupon().getId().equalsIgnoreCase(couponId))
		{
			logger.info("Updating discount for customer [{}] to coupon [{}]", subscription.getCustomerId(), couponId);
			Map<String, Object> updateParams = Maps.newHashMap();
			updateParams.put("coupon", couponId);
			customer.update(updateParams);
		}
	}

	@Override
	protected void onSubscriptionException(SubscriptionException subscriptionException) {
		logger.error("SubscriptionException", subscriptionException);

		Map<String, Object> model = new HashMap<>();
		model.put("subscriptionException", subscriptionException);

		// build and send the email
		Email email = persistableEmailService.buildEmail(TracktacularEmail.SUBSCRIPTION_ERROR.getTemplate(), Locale.ENGLISH, model);
		email.setTo(subscriptionErrorEmailAddress);

		persistableEmailService.send(email);

		// rethrow exception
		throw subscriptionException;
	}

	// getters and setters
	public String getSubscriptionErrorEmailAddress() {
		return subscriptionErrorEmailAddress;
	}

	public void setSubscriptionErrorEmailAddress(String subscriptionErrorEmailAddress) {
		this.subscriptionErrorEmailAddress = subscriptionErrorEmailAddress;
	}

	public PersistableEmailService getPersistableEmailService() {
		return persistableEmailService;
	}

	public void setPersistableEmailService(PersistableEmailService persistableEmailService) {
		this.persistableEmailService = persistableEmailService;
	}

}