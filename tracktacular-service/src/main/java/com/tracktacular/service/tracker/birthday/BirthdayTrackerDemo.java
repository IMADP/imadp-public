package com.tracktacular.service.tracker.birthday;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;



/**
 * BirthdayTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BirthdayTrackerDemo extends AbstractTrackerDemo {

	// static properties
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("MM/dd/yyyy");

	// properties
	private BirthdayTrackerFacade trackerFacade;

	// constructor
	public BirthdayTrackerDemo(BirthdayTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		// january
		addBirthday(user, "Mel", "Gibson", "01/03/1956");
		addBirthday(user, "Nicolas", "Cage", "01/07/1964");
		addBirthday(user, "Christian", "Bale", "01/30/1974");

		// february
		addBirthday(user, "Ashton", "Kutcher", "02/07/1978");
		addBirthday(user, "Cindy", "Crawford", "02/20/1966");

		// march
		addBirthday(user, "Jessica", "Biel", "03/03/1982");
		addBirthday(user, "Dane", "Cook", "03/18/1972");

		// april
		addBirthday(user, "Robert", "Downey Jr.", "04/04/1965");
		addBirthday(user, "Jack", "Nicholson", "04/22/1937");

		// may
		addBirthday(user, "Dwayne", "Johnson", "05/02/1972");
		addBirthday(user, "Megan", "Fox", "05/16/1986");

		// june
		addBirthday(user, "Morgan", "Freeman", "06/01/1937");
		addBirthday(user, "Dana", "Carvey", "06/02/1955");
		addBirthday(user, "Paula", "Abdul", "06/19/1962");

		// july
		addBirthday(user, "Lindsay", "Lohan", "07/02/1986");
		addBirthday(user, "Alex", "Trebek", "07/22/1940");

		// august
		addBirthday(user, "Mila", "Kunis", "08/14/1983");
		addBirthday(user, "Cameron", "Diaz", "08/30/1972");

		// september
		addBirthday(user, "Keanu", "Reeves", "09/02/1964");
		addBirthday(user, "Fiona", "Apple", "09/13/1977");
		addBirthday(user, "Adam", "West", "09/19/1928");

		// october
		addBirthday(user, "Kate", "Winslet", "10/05/1975");
		addBirthday(user, "Hugh", "Jackman", "10/12/1968");

		// november
		addBirthday(user, "Demi", "Moore", "11/11/1962");
		addBirthday(user, "Ryan", "Gosling", "11/12/1980");
		addBirthday(user, "Ben", "Stiller", "11/30/1965");

		// december
		addBirthday(user, "Steve", "Buscemi", "12/13/1957");
		addBirthday(user, "Brad", "Pitt", "12/18/1963");
	}

	/**
	 * Adds a birthday.
	 *
	 * @param user
	 * @param firstName
	 * @param lastName
	 * @param date
	 */
	private void addBirthday(User user, String firstName, String lastName, String date) {
		Birthday birthday = new Birthday(user);
		birthday.setFirstName(firstName);
		birthday.setLastName(lastName);
		birthday.setDate(DATE_FORMATTER.parseDateTime(date));

		trackerFacade.saveBirthday(birthday);
	}

}
