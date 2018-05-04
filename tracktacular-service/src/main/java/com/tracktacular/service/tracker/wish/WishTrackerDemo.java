package com.tracktacular.service.tracker.wish;

import com.imadp.core.money.Money;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;



/**
 * WishTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class WishTrackerDemo extends AbstractTrackerDemo {
	private WishTrackerFacade trackerFacade;

	// constructor
	public WishTrackerDemo(WishTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		Item item;

		item = addItem(user, "Astronaut Ice Cream", "Neopolitan flavor! (Chocolate/Vanilla/Strawberry)", new Money("15.99"), null);
		item.setNotes("I will never have enough of these in my cabinet.");
		trackerFacade.saveItem(item);

		addItem(user, "Kiehls Ultimate Man Soap", "I cannot go back to regular soap", new Money("26.95"),
				"http://www.amazon.com/Kiehls-Ultimate-Body-Scrub-Soap/dp/B002Z0LKJS/ref=sr_1_cc_1?s=aps&ie=UTF8&qid=1354593773&sr=1-1-catcorr&keywords=khiels+soap");

		addItem(user, "Planet Earth: The Complete BBC Series", "Bluray Edition", new Money("31.95"),
				"http://www.amazon.com/Planet-Earth-Complete-Series-Blu-ray/dp/B000MRAAJM/ref=sr_1_cc_1?s=aps&ie=UTF8&qid=1354593832&sr=1-1-catcorr&keywords=planet+earth+bluray");

		addItem(user, "Foundation by Isaac Asimov", null, new Money("10.20"),
				"http://www.amazon.com/Foundation-Novels-Isaac-Asimov/dp/0553382578/ref=sr_1_6?s=books&ie=UTF8&qid=1354594007&sr=1-6&keywords=isaac+asimov+book");

		addItem(user, "Rant by Chuck Palahniuk", null, new Money("10.20"),
				"http://www.amazon.com/Rant-Oral-Biography-Buster-Casey/dp/0307275833");

		addItem(user, "Dark Knight Trilogy", null, new Money("29.99"),
				"http://www.amazon.com/Knight-Trilogy-Batman-Begins-Blu-ray/dp/B009JBZH54/ref=cm_gift_gg_B009JBZH54?pf_rd_p=381702801&pf_rd_s=center-1&pf_rd_t=1001&pf_rd_i=gift-guides-search&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=1V79SNFQFF9CE6Y0VHVP");

		// completed
		item = addItem(user, "Cat's Cradle by Kurt Vonnegut", null, new Money("10.20"),
				"http://www.amazon.com/Cats-Cradle-Novel-Kurt-Vonnegut/dp/038533348X/ref=sr_1_1?s=books&ie=UTF8&qid=1354594064&sr=1-1&keywords=cats+cradle+book");
		item.setPersistableState(PersistableState.ARCHIVED);
		trackerFacade.saveItem(item);
	}

	/**
	 * Adds a item.
	 *
	 * @param user
	 * @param name
	 * @param description
	 * @param completedDate
	 * @return Item
	 */
	private Item addItem(User user, String name, String description, Money price, String url) {
		Item item = new Item(user);
		item.setName(name);
		item.setDescription(description);
		item.setPrice(price);
		item.setUrl(url);

		trackerFacade.saveItem(item);

		return item;
	}

}
