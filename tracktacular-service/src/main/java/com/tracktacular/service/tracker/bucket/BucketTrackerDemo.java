package com.tracktacular.service.tracker.bucket;

import org.joda.time.DateTime;

import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;



/**
 * BucketTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BucketTrackerDemo extends AbstractTrackerDemo {
	private BucketTrackerFacade trackerFacade;

	// constructor
	public BucketTrackerDemo(BucketTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		Item item;

		// completed
		item = addItem(user, "Run a marathon", "And don't come in last place", new DateTime().minusYears(1));
		item.setNotes("Ran a local marathon in my town after six months of light training. Finished as 150/325.");
		trackerFacade.saveItem(item);

		item = addItem(user, "Jump out of an airplane", "Preferrably with a parachute", new DateTime().minusDays(6));
		item.setNotes("I went tandem skydiving at 12,000 feet without any complications. There was a short training session although there was very little I needed to learn for tandem jumping. \n\nThe plane did not inspire confidence - it barely fit 5 people including the pilot. But the actual jump was amazing. The freefall in actuality probably lasted less than a minute but it felt like much longer than that. I couldn't hear anything over the sound of the wind so there was little to do but scream and enjoy it. \n\nWhen the parachute opened, everything fell silent. I was able to talk to the instructor with ease and admire the view. We landed without a problem, and I had an adrenaline rush for the rest of the day. I'd highly recommend it to anyone else.");
		trackerFacade.saveItem(item);

		// uncompleted
		addItem(user, "See the Northern Lights", "Alaska, Denmark, Canada, and Finland are possible locations", null);
		addItem(user, "Step foot on all seven continents", "(5/7) Antartica and Australia still to go", null);
		addItem(user, "Place a single large bet at a casino", "Minimum $1000 bet, and it must be done with style", null);
		addItem(user, "See a concert at the Sydney Opera House", "Preferrably a classical rendition", null);
		addItem(user, "Surf in the dark", "Best bet is to wait for a full moon", null);
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
	private Item addItem(User user, String name, String description, DateTime completedDate) {
		Item item = new Item(user);
		item.setName(name);
		item.setDescription(description);
		item.setCompletedDate(completedDate);

		trackerFacade.saveItem(item);

		return item;
	}

}
