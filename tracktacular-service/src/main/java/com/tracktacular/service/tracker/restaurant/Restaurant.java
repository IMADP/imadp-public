package com.tracktacular.service.tracker.restaurant;

import java.util.Comparator;
import java.util.Set;

import org.apache.commons.lang.WordUtils;
import org.joda.time.DateTime;

import com.google.common.base.Objects;
import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Restaurant
 *
 * A representation of a restaurant.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Restaurant extends AbstractPersistableUser {

	// static Properties
	public static final Property<Restaurant, String> NAME = Property.of("name");
	public static final Property<Restaurant, String> CITY = Property.of("city");
	public static final Property<Restaurant, String> STATE = Property.of("state");
	public static final Property<Restaurant, String> TAG = Property.of("tag");
	public static final Property<Restaurant, String> NOTES = Property.of("notes");
	public static final Property<Restaurant, Integer> RATING = Property.of("rating");
	public static final Property<Restaurant, DateTime> TARGET_DATE = Property.of("targetDate");

	// properties
	private String name;
	private String city;
	private String state;
	private String tag;
	private String notes;
	private Integer rating;
	private DateTime targetDate;
	private Set<Meal> meals;

	// constructor
	public Restaurant() {
		this(null);
	}

	// constructor
	public Restaurant(User user) {
		super(user);
		setPersistableState(PersistableState.ACTIVE);
	}

	/**
	 * Returns true if the restaurant is completed as determined by whether a rating exists or not.
	 *
	 * @return boolean
	 */
	public boolean isCompleted() {
		return rating != null;
	}

	/**
	 * Returns a slug from the name property.
	 *
	 * @return String
	 */
	public String getNameSlug() {
		return toSlug(name);
	}

	/**
	 * Returns the count of meals for this restaurant.
	 *
	 * @return int
	 */
	public int getMealCount() {
		return meals == null ? 0 : meals.size();
	}

	// getters and setters
	public String getTargetDateString() {
		return toDateString(targetDate);
	}

	public void setTargetDateString(String targetDateString) {
		this.targetDate = fromDateString(targetDateString);
	}

	public String getName() {
		return WordUtils.capitalize(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTag() {
		return WordUtils.capitalize(tag);
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public DateTime getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(DateTime targetDate) {
		this.targetDate = targetDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Set<Meal> getMeals() {
		return meals;
	}

	public void setMeals(Set<Meal> meals) {
		this.meals = meals;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	// name comparator
	public static final Comparator<Restaurant> NAME_COMPARATOR = new Comparator<Restaurant>() {
		@Override
		public int compare(Restaurant b1, Restaurant b2) {
			return b1.getName().toLowerCase().compareToIgnoreCase(b2.getName().toLowerCase());
		}
	};

	// rating comparator
	public static final Comparator<Restaurant> RATING_COMPARATOR = new Comparator<Restaurant>() {
		@Override
		public int compare(Restaurant b1, Restaurant b2) {

			if(b1.isCompleted() && !b2.isCompleted())
				return 1;

			if(b2.isCompleted() && !b1.isCompleted())
				return -1;

			if(!Objects.equal(b1.getRating(), b2.getRating()))
				return Integer.compare(b2.getRating(), b1.getRating());

			return NAME_COMPARATOR.compare(b1, b2);
		}
	};

	// state comparator
	public static final Comparator<Restaurant> STATE_COMPARATOR = new Comparator<Restaurant>() {
		@Override
		public int compare(Restaurant b1, Restaurant b2) {

			if(!b1.getState().equalsIgnoreCase(b2.getState()))
				return b1.getState().toLowerCase().compareToIgnoreCase(b2.getState().toLowerCase());

			return NAME_COMPARATOR.compare(b1, b2);
		}
	};

	// city comparator
	public static final Comparator<Restaurant> CITY_COMPARATOR = new Comparator<Restaurant>() {
		@Override
		public int compare(Restaurant b1, Restaurant b2) {

			if(!b1.getCity().equalsIgnoreCase(b2.getCity()))
				return b1.getCity().toLowerCase().compareToIgnoreCase(b2.getCity().toLowerCase());

			return NAME_COMPARATOR.compare(b1, b2);
		}
	};

}