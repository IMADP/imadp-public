package com.tracktacular.service.tracker.restaurant;

import java.util.Comparator;

import org.apache.commons.lang.WordUtils;
import org.joda.time.DateTime;

import com.google.common.base.Objects;
import com.imadp.core.Property;
import com.imadp.service.Sortable;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Meal
 *
 * A meal for a restaurant.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Meal extends AbstractPersistableUser implements Sortable {

	// static Properties
	public static final Property<Meal, Restaurant> RESTAURANT = Property.of("restaurant");
	public static final Property<Meal, String> NAME = Property.of("name");
	public static final Property<Meal, String> NOTES = Property.of("notes");
	public static final Property<Meal, String> TAG = Property.of("tag");
	public static final Property<Meal, Integer> RATING = Property.of("rating");
	public static final Property<Meal, DateTime> TARGET_DATE = Property.of("targetDate");
	public static final Property<Meal, Integer> SORT = Property.of("sort");

	// properties
	private Restaurant restaurant;
	private String name;
	private String notes;
	private String tag;
	private Integer rating;
	private DateTime targetDate;
	private int sort;

	// constructor
	public Meal() {
		this(null, null);
	}

	// constructor
	public Meal(User user, Restaurant restaurant) {
		super(user);
		this.restaurant = restaurant;
	}

	/**
	 * Returns true if the meal is completed as determined by whether a rating exists or not.
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

	// getters and setters
	public String getTargetDateString() {
		return toDateString(targetDate);
	}

	public void setTargetDateString(String targetDateString) {
		this.targetDate = fromDateString(targetDateString);
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getName() {
		return WordUtils.capitalize(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public int getSort() {
		return sort;
	}

	@Override
	public void setSort(int sort) {
		this.sort = sort;
	}

	// name comparator
	public static final Comparator<Meal> NAME_COMPARATOR = new Comparator<Meal>() {
		@Override
		public int compare(Meal b1, Meal b2) {
			return b1.getName().toLowerCase().compareToIgnoreCase(b2.getName().toLowerCase());
		}
	};

	// rating comparator
	public static final Comparator<Meal> RATING_COMPARATOR = new Comparator<Meal>() {
		@Override
		public int compare(Meal b1, Meal b2) {

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
	public static final Comparator<Meal> RESTAURANT_COMPARATOR = new Comparator<Meal>() {
		@Override
		public int compare(Meal b1, Meal b2) {

			if(!b1.getRestaurant().getName().equalsIgnoreCase(b2.getRestaurant().getName()))
				return b1.getRestaurant().getName().toLowerCase().compareToIgnoreCase(b2.getRestaurant().getName().toLowerCase());

			return NAME_COMPARATOR.compare(b1, b2);
		}
	};

}
