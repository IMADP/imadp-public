package com.tracktacular.service.tracker.gift;

import org.joda.time.DateTime;

import com.imadp.core.money.Money;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;



/**
 * GiftTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class GiftTrackerDemo extends AbstractTrackerDemo {

	private GiftTrackerFacade trackerFacade;

	// constructor
	public GiftTrackerDemo(GiftTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		// received

		// birthday
		addGift(user, "Barnes and Noble Gift Card", "Mom", null, "Birthday", new DateTime().minusMonths(1), new Money("100"));
		addGift(user, "Barnes and Noble Gift Card", "Mom", null, "Birthday", new DateTime().minusMonths(1).minusYears(1), new Money("100"));
		addGift(user, "Bluray Player", "Dad", null, "Birthday", new DateTime().minusMonths(1), new Money("99.99"));
		addGift(user, "Steak and Seafood Dinner", "Girlfriend", null, "Birthday", new DateTime().minusMonths(1), null);
		addGift(user, "Steak and Seafood Dinner", "Girlfriend", null, "Birthday", new DateTime().minusMonths(1).minusYears(1), null);

		Gift gift = addGift(user, "High Fidelity Surround Speakers", "Dad", null, "Birthday", new DateTime().minusMonths(1).minusYears(1), null);
		gift.setFavorite(true);
		trackerFacade.saveGift(gift);

		// christmas
		addGift(user, "Assorted candy and toys", "Mom", null, "Christmas", new DateTime().minusYears(1).withMonthOfYear(12).withDayOfMonth(25), null);
		addGift(user, "Assorted candy and toys", "Mom", null, "Christmas", new DateTime().minusYears(2).withMonthOfYear(12).withDayOfMonth(25), null);
		addGift(user, "Amazon Gift Card", "Dad", null, "Christmas", new DateTime().minusYears(1).withMonthOfYear(12).withDayOfMonth(25), new Money("100"));
		addGift(user, "Amazon Gift Card", "Dad", null, "Christmas", new DateTime().minusYears(2).withMonthOfYear(12).withDayOfMonth(25), new Money("100"));
		addGift(user, "Digital Camera", "Girlfriend", null, "Christmas", new DateTime().minusYears(1).withMonthOfYear(12).withDayOfMonth(25), null);
		addGift(user, "Xbox 360", "Girlfriend", null, "Christmas", new DateTime().minusYears(2).withMonthOfYear(12).withDayOfMonth(25), null);

		// given

		// birthday
		addGift(user, "Heated Throw Blanket", null, "Mom", "Birthday", new DateTime().minusMonths(3).minusDays(2).minusYears(1), new Money("69.99"));
		addGift(user, "Decorative Candle Set", null, "Mom", "Birthday", new DateTime().minusMonths(3).minusDays(2).minusYears(2), new Money("49.99"));
		addGift(user, "Dewalt Cordless Power Drill", null, "Dad", "Birthday", new DateTime().minusMonths(5).minusDays(2).minusYears(1), new Money("99.99"));
		addGift(user, "14 Piece Deluxe Grill Set", null, "Dad", "Birthday", new DateTime().minusMonths(5).minusDays(2).minusYears(2), new Money("68.99"));
		addGift(user, "Swarovski Pendant", null, "Girlfriend", "Birthday", new DateTime().minusMonths(7).minusDays(4).minusYears(1), new Money("99.99"));
		addGift(user, "Spa Full Day Pass", null, "Girlfriend", "Birthday", new DateTime().minusMonths(7).minusDays(4).minusYears(2), new Money("89.99"));

		// christmas
		addGift(user, "Bath and Body Soap Kit", null, "Mom", "Christmas", new DateTime().minusYears(1).withMonthOfYear(12).withDayOfMonth(25), new Money("49.99"));
		addGift(user, "Automatic Back Massager", null, "Mom", "Christmas", new DateTime().minusYears(2).withMonthOfYear(12).withDayOfMonth(25), new Money("59.99"));
		addGift(user, "Bose Noise Cancelling Headphones", null, "Dad", "Christmas", new DateTime().minusYears(1).withMonthOfYear(12).withDayOfMonth(25), new Money("125.99"));
		addGift(user, "Logitech Universal Remote", null, "Dad", "Christmas", new DateTime().minusYears(2).withMonthOfYear(12).withDayOfMonth(25), new Money("115.99"));
		addGift(user, "Hi-tech Blow Dryer", null, "Girlfriend", "Christmas", new DateTime().minusYears(1).withMonthOfYear(12).withDayOfMonth(25), new Money("49.99"));
		addGift(user, "Vanity Desk", null, "Girlfriend", "Christmas", new DateTime().minusYears(2).withMonthOfYear(12).withDayOfMonth(25), new Money("149.99"));
	}

	/**
	 * Adds a gift.
	 *
	 * @param user
	 * @param name
	 * @param sender
	 * @param receiver
	 * @param occasion
	 * @param date
	 * @param price
	 * @return gift
	 */
	private Gift addGift(User user, String name, String sender, String receiver, String occasion, DateTime date, Money price) {
		Gift gift = new Gift(user);
		gift.setName(name);
		gift.setSender(sender);
		gift.setReceiver(receiver);
		gift.setOccasion(occasion);
		gift.setDate(date);
		gift.setPrice(price);

		trackerFacade.saveGift(gift);

		return gift;
	}

}
