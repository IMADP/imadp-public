package com.tracktacular.service.account;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Locale;

import com.imadp.service.account.Account;
import com.imadp.service.account.AccountFacade;
import com.imadp.service.account.credentials.Credentials;
import com.imadp.service.commerce.subscription.Subscription;
import com.imadp.service.user.User;
import com.stripe.exception.CardException;


/**
 * TracktacularAccountFacade
 *
 * Facade encapsulating the account operations for Tracktacular.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface TracktacularAccountFacade extends AccountFacade<Account> {

	/**
	 * Returns the demo user.
	 *
	 * @return User
	 */
	public User getDemoUser();

	/**
	 * Gets Preferences for a user.
	 *
	 * @param user
	 * @return Preferences or default preferences if none was found
	 */
	public Preferences getPreferences(User user);

	/**
	 * Saves a user's preferences.
	 *
	 * @param preferences
	 */
	public void savePreferences(Preferences preferences);

	/**
	 * Creates an account with the trial member authority.
	 *
	 * @param account
	 * @param locale
	 * @return Credentials
	 */
	public Credentials createUserAccount(Account account, Locale locale);

	/**
	 * Creates an account with the Admin authority.
	 *
	 * @param account
	 * @param locale
	 * @return Credentials
	 */
	public Credentials createAdminAccount(Account account, Locale locale);

	/**
	 * Gets a subscription by user.
	 *
	 * @param user
	 * @return Subscription
	 */
	public Subscription getSubscription(User user);

	/**
	 * Returns a list of subscription referrals for a given user.
	 *
	 * @param user
	 * @return List<Subscription>
	 */
	public List<Subscription> getReferrals(User user);

	/**
	 * Unsubscribes from blog notifications.
	 *
	 * @param preferencesUid
	 */
	public void unsubscribeBlogNotification(String preferencesUid);

	/**
	 * Unsubscribes from email alerts.
	 *
	 * @param preferencesUid
	 */
	public void unsubscribeEmailAlerts(String preferencesUid);

	/**
	 * Updates a subscription with a given subscriptionPlan and customerId.
	 *
	 * @param subscription
	 * @param customerId
	 * @throws CardException
	 */
	public void updateSubscription(Subscription subscription, String customerId) throws CardException;

	/**
	 * Cancels a Subscription.
	 *
	 * @param subscription
	 */
	public void cancelSubscription(Subscription subscription);

	/**
	 * Processes a subscriptionEvent from a json reader.
	 *
	 * @param reader
	 * @throws IOException
	 */
	public void processSubscriptionEvent(Reader reader) throws IOException;

}