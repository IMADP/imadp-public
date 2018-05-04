package com.tracktacular.service.account;

import com.imadp.core.Property;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Preferences
 *
 * General preferences for a Tracktacular user.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Preferences extends AbstractPersistableUser {

	// static properties
	public static final Property<Preferences, Boolean> EMAIL_ALERTS  = Property.of("emailAlerts");
	public static final Property<Preferences, Boolean> BLOG_NOTIFICATION  = Property.of("blogNotification");

	// static tracker properties
	public static final Property<Preferences, String> TRACKERS_BIRTHDAY_JSON  = Property.of("trackers.birthdayJson");
	public static final Property<Preferences, String> TRACKERS_BODY_JSON  = Property.of("trackers.bodyJson");
	public static final Property<Preferences, String> TRACKERS_BOOK_JSON  = Property.of("trackers.bookJson");
	public static final Property<Preferences, String> TRACKERS_BLOOD_JSON  = Property.of("trackers.bloodJson");
	public static final Property<Preferences, String> TRACKERS_BUCKET_JSON = Property.of("trackers.bucketJson");
	public static final Property<Preferences, String> TRACKERS_BUDGET_JSON = Property.of("trackers.budgetJson");
	public static final Property<Preferences, String> TRACKERS_CALENDAR_JSON = Property.of("trackers.calendarJson");
	public static final Property<Preferences, String> TRACKERS_CHOLESTEROL_JSON = Property.of("trackers.cholesterolJson");
	public static final Property<Preferences, String> TRACKERS_DREAM_JSON = Property.of("trackers.dreamJson");
	public static final Property<Preferences, String> TRACKERS_EXERCISE_JSON = Property.of("trackers.exerciseJson");
	public static final Property<Preferences, String> TRACKERS_GAME_JSON = Property.of("trackers.gameJson");
	public static final Property<Preferences, String> TRACKERS_GOAL_JSON = Property.of("trackers.goalJson");
	public static final Property<Preferences, String> TRACKERS_GIFT_JSON = Property.of("trackers.giftJson");
	public static final Property<Preferences, String> TRACKERS_JOURNAL_JSON = Property.of("trackers.journalJson");
	public static final Property<Preferences, String> TRACKERS_MOVIE_JSON  = Property.of("trackers.movieJson");
	public static final Property<Preferences, String> TRACKERS_MUSIC_JSON  = Property.of("trackers.musicJson");
	public static final Property<Preferences, String> TRACKERS_RECIPE_JSON = Property.of("trackers.recipeJson");
	public static final Property<Preferences, String> TRACKERS_RESTAURANT_JSON = Property.of("trackers.restaurantJson");
	public static final Property<Preferences, String> TRACKERS_TASK_JSON = Property.of("trackers.taskJson");
	public static final Property<Preferences, String> TRACKERS_TV_JSON = Property.of("trackers.tvJson");
	public static final Property<Preferences, String> TRACKERS_WISH_JSON = Property.of("trackers.wishJson");

    // preferences
	private boolean emailAlerts = true;
	private boolean blogNotification = true;
	private PreferencesTrackers trackers;

	// constructor
	public Preferences() {
		this(null);
	}

	// constructor
	public Preferences(User user) {
		super(user);
	}

	// getters and setters
	public boolean isEmailAlerts() {
		return emailAlerts;
	}

	public void setEmailAlerts(boolean emailAlerts) {
		this.emailAlerts = emailAlerts;
	}

	public PreferencesTrackers getTrackers() {
		if(trackers == null)
			trackers = new PreferencesTrackers();

		return trackers;
	}

	public void setTrackers(PreferencesTrackers trackers) {
		this.trackers = trackers;
	}

	public boolean isBlogNotification() {
		return blogNotification;
	}

	public void setBlogNotification(boolean blogNotification) {
		this.blogNotification = blogNotification;
	}

}