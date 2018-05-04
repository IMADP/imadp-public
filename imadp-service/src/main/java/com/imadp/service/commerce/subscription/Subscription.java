package com.imadp.service.commerce.subscription;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imadp.core.Property;
import com.imadp.service.commerce.subscription.plan.SubscriptionPlan;
import com.imadp.service.log.PersistableLog;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Subscription
 *
 * A representation of a subscription.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Subscription extends AbstractPersistableUser {

	// logger
	private final static Logger LOGGER = LoggerFactory.getLogger(PersistableLog.class);

	// static properties
	public static final Property<Subscription, String> CUSTOMER_ID = Property.of("customerId");
	public static final Property<Subscription, String> SUBSCRIPTION_LOG_JSON = Property.of("subscriptionLogJson");
	public static final Property<Subscription, DateTime> SUBSCRIPTION_END = Property.of("subscriptionEnd");
	public static final Property<Subscription, DateTime> SUBSCRIPTION_START = Property.of("subscriptionStart");
	public static final Property<Subscription, DateTime> SUBSCRIPTION_PERIOD_END = Property.of("subscriptionPeriodEnd");
	public static final Property<Subscription, DateTime> SUBSCRIPTION_PERIOD_START = Property.of("subscriptionPeriodStart");
	public static final Property<Subscription, SubscriptionPlan> SUBSCRIPTION_PLAN = Property.of("subscriptionPlan");
	public static final Property<Subscription, SubscriptionStatus> SUBSCRIPTION_STATUS = Property.of("subscriptionStatus");
	public static final Property<Subscription, User> REFERRER = Property.of("referrer");

	// properties
	private String customerId;
	private DateTime subscriptionEnd;
	private DateTime subscriptionStart;
	private DateTime subscriptionPeriodEnd;
	private DateTime subscriptionPeriodStart;
	private SubscriptionPlan subscriptionPlan;
	private SubscriptionStatus subscriptionStatus;
	private User referrer;
	private PersistableLog subscriptionLog = new PersistableLog();

	// constructor
	public Subscription() {

	}

	// constructor
	public Subscription(User user) {
		super(user);
	}

	// constructor
	public Subscription(User user, User referrer) {
		super(user);
		this.referrer = referrer;
	}

	/**
	 * Creates a subscription with the given trial period.
	 *
	 * @param trialPeriod
	 */
	public void createSubscription(Period trialPeriod) {
		DateTime date = new DateTime();
		this.subscriptionStatus = SubscriptionStatus.TRIALING;
		this.subscriptionStart = date;
		this.subscriptionPeriodStart = date;
		this.subscriptionPeriodEnd = date.plus(trialPeriod);
	}

	/**
	 * Updates a subscription.
	 *
	 * @param subscription
	 * @param subscriptionPlan
	 */
	public void updateSubscription(com.stripe.model.Subscription subscription, SubscriptionPlan subscriptionPlan) {
		this.subscriptionPlan = subscriptionPlan;
		this.subscriptionStatus = SubscriptionStatus.fromStatus(subscription.getStatus());
		this.subscriptionStart = new DateTime(subscription.getStart() * 1000L);
		this.subscriptionPeriodStart  = new DateTime(subscription.getCurrentPeriodStart() * 1000L);
		this.subscriptionPeriodEnd = new DateTime(subscription.getCurrentPeriodEnd() * 1000L);
	}

	/**
	 * Cancels a subscription.
	 *
	 */
	public void cancelSubscription() {
		this.subscriptionStatus = SubscriptionStatus.CANCELLED;
		this.subscriptionEnd = new DateTime();
	}

	/**
	 * Logs a subscription message with optional object parameters.
	 *
	 * @param message
	 * @param objects
	 */
	public void log(String message, Object... objects) {
		LOGGER.info(message, objects);
		subscriptionLog.log(message, objects);
	}

	/**
	 * Returns the days remaining for the current subscription period.
	 *
	 * @return int
	 */
	public int getSubscriptionPeriodDaysRemaining() {
		if(subscriptionPeriodEnd == null)
			return 0;

		return Days.daysBetween(new DateTime(), subscriptionPeriodEnd).getDays();
	}

	/**
	 * Returns true if the subscription is expired, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isExpired() {
		return !isActive() && new DateTime().isAfter(subscriptionPeriodEnd);
	}

	/**
	 * Returns true if the subscription is trialing, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isTrialing() {
		return SubscriptionStatus.TRIALING == subscriptionStatus;
	}

	/**
	 * Returns true if the subscription is active, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isActive() {
		return SubscriptionStatus.ACTIVE == subscriptionStatus;
	}

	/**
	 * Returns true if the subscription is cancelled, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isCancelled() {
		return SubscriptionStatus.CANCELLED == subscriptionStatus ;
	}

	/**
	 * Returns true if the subscription is past due, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isPastDue() {
		return SubscriptionStatus.PAST_DUE == subscriptionStatus;
	}

	/**
	 * Returns true if the subscription is unpaid, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isUnpaid() {
		return SubscriptionStatus.UNPAID == subscriptionStatus;
	}

	// getters and setters
	public String getSubscriptionLogJson() {
		return subscriptionLog.toJson();
	}

	public void setSubscriptionLogJson(String subscriptionLogJson) {
		this.subscriptionLog = PersistableLog.fromJson(subscriptionLogJson);
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public SubscriptionStatus getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	public SubscriptionPlan getSubscriptionPlan() {
		return subscriptionPlan;
	}

	public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
		this.subscriptionPlan = subscriptionPlan;
	}

	public DateTime getSubscriptionStart() {
		return subscriptionStart;
	}

	public void setSubscriptionStart(DateTime subscriptionStart) {
		this.subscriptionStart = subscriptionStart;
	}

	public DateTime getSubscriptionPeriodStart() {
		return subscriptionPeriodStart;
	}

	public void setSubscriptionPeriodStart(DateTime subscriptionPeriodStart) {
		this.subscriptionPeriodStart = subscriptionPeriodStart;
	}

	public DateTime getSubscriptionPeriodEnd() {
		return subscriptionPeriodEnd;
	}

	public void setSubscriptionPeriodEnd(DateTime subscriptionPeriodEnd) {
		this.subscriptionPeriodEnd = subscriptionPeriodEnd;
	}

	public DateTime getSubscriptionEnd() {
		return subscriptionEnd;
	}

	public void setSubscriptionEnd(DateTime subscriptionEnd) {
		this.subscriptionEnd = subscriptionEnd;
	}

	public User getReferrer() {
		return referrer;
	}

	public void setReferrer(User referrer) {
		this.referrer = referrer;
	}

}
