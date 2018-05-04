package com.tracktacular.service.account;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.joda.time.Period;

import com.imadp.core.email.Email;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.account.Account;
import com.imadp.service.account.AccountFacadeImpl;
import com.imadp.service.account.authority.AuthorityType;
import com.imadp.service.account.credentials.Credentials;
import com.imadp.service.commerce.subscription.Subscription;
import com.imadp.service.commerce.subscription.SubscriptionFacade;
import com.imadp.service.commerce.subscription.plan.SubscriptionPlan;
import com.imadp.service.user.User;
import com.stripe.exception.CardException;
import com.tracktacular.service.TracktacularEmail;


/**
 * TracktacularAccountFacadeImpl
 *
 * The standard implementation of the TracktacularAccountFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class TracktacularAccountFacadeImpl extends AccountFacadeImpl<Account>
	implements TracktacularAccountFacade {

	// properties
	private String demoUserUid;
	private String emailAccountNotificationEmailAddress;
	private String referredCouponId;
	private String subscriptionPlanId;
	private PreferencesService preferencesService;
	private SubscriptionFacade subscriptionFacade;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(demoUserUid);
		Validate.notNull(preferencesService);
		Validate.notNull(referredCouponId);
		Validate.notNull(subscriptionPlanId);
		Validate.notNull(subscriptionFacade);
		Validate.notNull(emailAccountNotificationEmailAddress);
	}

	@Override
	public User getDemoUser() {
		return userService.get(demoUserUid);
	}

	@Override
	public Preferences getPreferences(User user) {
		Preferences preferences = user == null ? null : preferencesService.findFirstByUser(user);
		return preferences != null ? preferences : new Preferences(user);
	}

	@Override
	public void savePreferences(Preferences preferences) {
		preferencesService.save(preferences);
	}

	@Override
	public Credentials createUserAccount(Account account, Locale locale) {
		Credentials credentials = createAccount(account, authorityService.findFirstByType(AuthorityType.USER));
		sendAccountVerificationEmail(account.getUser(), locale);
		sendAccountNotificationEmail(account, locale);
		return credentials;
	}

	@Override
	protected void onCreateAccount(Account account) {
		User user = account.getUser();
		User referrer = account.hasReferrer() ? userService.get(account.getReferrerId()) : null;
		preferencesService.save(new Preferences(user));
		subscriptionFacade.createSubscription(user, Period.months(1), referrer);
	}

	/**
	 * Sends a notification email for new accounts.
	 *
	 * @param account
	 * @param locale
	 */
	private void sendAccountNotificationEmail(Account account, Locale locale) {
		Map<String, Account> model = new HashMap<>();
		model.put("account", account);

		// build and send the email
		Email email = persistableEmailService.buildEmail(TracktacularEmail.ACCOUNT_NOTIFICATION.getTemplate(), locale, model);
		email.setTo(emailAccountNotificationEmailAddress);

		persistableEmailService.send(email);
	}

	@Override
	public Credentials createAdminAccount(Account account, Locale locale) {
		return createAccount(account, authorityService.findFirstByType(AuthorityType.ADMIN));
	}

	@Override
	public Subscription getSubscription(User user) {
		return subscriptionFacade.getSubscription(user);
	}

	@Override
	public List<Subscription> getReferrals(User user) {
		return subscriptionFacade.getReferrals(user, CriteriaParams.<Subscription>of(
				Results.ALL, Order.<Subscription>asc(Subscription.TIME_CREATED)));
	}

	@Override
	public void updateSubscription(Subscription subscription, String customerId) throws CardException {
		SubscriptionPlan subscriptionPlan = subscriptionFacade.getSubscriptionPlan(subscriptionPlanId);
		Credentials credentials = getCredentials(subscription.getUser());
		String coupon = subscription.getReferrer() == null ? null : referredCouponId;
		subscriptionFacade.updateSubscription(subscription, subscriptionPlan, customerId, credentials.getEmail(), coupon);
	}

	@Override
	public void cancelSubscription(Subscription subscription) {
		subscriptionFacade.cancelSubscription(subscription);
	}

	@Override
	public void processSubscriptionEvent(Reader reader) throws IOException {
		subscriptionFacade.processEvent(reader);
	}

	@Override
	public void unsubscribeEmailAlerts(String preferencesUid) {
		Preferences preferences = preferencesService.get(preferencesUid);
		preferences.setEmailAlerts(false);
		preferencesService.save(preferences);
	}

	@Override
	public void unsubscribeBlogNotification(String preferencesUid) {
		Preferences preferences = preferencesService.get(preferencesUid);
		preferences.setBlogNotification(false);
		preferencesService.save(preferences);
	}

	// getters and setters
	public PreferencesService getPreferencesService() {
		return preferencesService;
	}

	public String getDemoUserUid() {
		return demoUserUid;
	}

	public void setDemoUserUid(String demoUserUid) {
		this.demoUserUid = demoUserUid;
	}

	public void setPreferencesService(PreferencesService preferencesService) {
		this.preferencesService = preferencesService;
	}

	public String getEmailAccountNotificationEmailAddress() {
		return emailAccountNotificationEmailAddress;
	}

	public void setEmailAccountNotificationEmailAddress(String emailAccountNotificationEmailAddress) {
		this.emailAccountNotificationEmailAddress = emailAccountNotificationEmailAddress;
	}

	public SubscriptionFacade getSubscriptionFacade() {
		return subscriptionFacade;
	}

	public void setSubscriptionFacade(SubscriptionFacade subscriptionFacade) {
		this.subscriptionFacade = subscriptionFacade;
	}

	public String getSubscriptionPlanId() {
		return subscriptionPlanId;
	}

	public void setSubscriptionPlanId(String subscriptionPlanId) {
		this.subscriptionPlanId = subscriptionPlanId;
	}

	public String getReferredCouponId() {
		return referredCouponId;
	}

	public void setReferredCouponId(String referredCouponId) {
		this.referredCouponId = referredCouponId;
	}

}