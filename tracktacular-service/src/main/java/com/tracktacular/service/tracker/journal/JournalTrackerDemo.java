package com.tracktacular.service.tracker.journal;

import org.joda.time.DateTime;

import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;


/**
 * JournalTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class JournalTrackerDemo extends AbstractTrackerDemo {
	private JournalTrackerFacade trackerFacade;

	// constructor
	public JournalTrackerDemo(JournalTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		// captains journal
		Journal journal = new Journal();
		journal.setUser(user);
		journal.setName("Captain's Journal");
		journal.setDescription("Daily thoughts and the like");

		trackerFacade.saveJournal(journal);

		Entry entry = journal.getNewEntry();
		entry.setTitle("Captains Log #1");
		entry.setContent("I've crashed landed on a barren planet just outside the Crab Nebula. The ship is in stable condition but the crew is severly demoralized. I feel partly responsible for the crash, but at the same time no one on board told me my phone's GPS wouldn't function in space. \n\n I will conduct a thorough damage assessment of the ship and the crew's health after my coffee break. Thankfully the expresso machine was not damaged in the landing. These creamer packets on the other hand leave no excuse for their awful taste. After the oxygen levels stabilize I'll ask the engineering team to create a suitable replacement.");
		entry.setDate(new DateTime().minusDays(1));

		trackerFacade.saveEntry(entry);

		entry = journal.getNewEntry();
		entry.setTitle("Captains Log #2");
		entry.setContent("Damage assessment was worse than I could have imagined. Life sustainment systems are intact, the hull is not compromised, but the entire engineering team was sucked out into space in a freak escape-pod accident. So much for my coffee creamers.");
		entry.setDate(new DateTime().minusDays(2));

		trackerFacade.saveEntry(entry);

		entry = journal.getNewEntry();
		entry.setTitle("Captains Log #3");
		entry.setContent("My alarm clock was damaged and I appear to have overslept. To compensate I took a tour of the ship, trying to salvage any spare machine parts that could be converted into a makeshift alarm. There were several damaged oxygen tanks that seem to explode at regular intervals. I placed one beside my bed - only time will tell whether it is effective. \n\nThe crew is becoming desperate. I cannot say that I blame them. Being stranded on this ship, in the middle of nowhere, with low quality coffee creamers - its just a matter of time before we lose the fight to preserve our sanity. I need a plan.");
		entry.setDate(new DateTime().minusDays(3));

		trackerFacade.saveEntry(entry);

		entry = journal.getNewEntry();
		entry.setTitle("Captains Log #4");
		entry.setContent("I awoke from my peaceful slumber to a deafening explosion beside my bed. It appears my makeshift alarm was successful! My ears have been ringing for over 2 hours, but my spirits are higher than ever. \n\n I've decided to organize a search party. What we are searching for, I cannot yet say. Milk would surely help. Exotic coffee beans even more so. The medical team insisted that we are dangerously low on water, or perhaps watermelons... I couldn't hear them very well. What strange wonders await us outside? Only time will tell! I'll report back tomorrow if the mission is a success.");
		entry.setDate(new DateTime().minusDays(4));

		trackerFacade.saveEntry(entry);
	}

}
