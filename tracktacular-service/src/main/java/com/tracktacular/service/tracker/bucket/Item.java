package com.tracktacular.service.tracker.bucket;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Item
 *
 * A representation of a item.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Item extends AbstractPersistableUser implements Comparable<Item> {

	// static Properties
	public static final Property<Item, String> NAME = Property.of("name");
	public static final Property<Item, String> DESCRIPTION = Property.of("description");
	public static final Property<Item, String> NOTES = Property.of("notes");
	public static final Property<Item, DateTime> COMPLETED_DATE = Property.of("completedDate");

	// properties
	private String name;
	private String description;
	private String notes;
	private DateTime completedDate;

	// constructor
	public Item() {
		this(null);
	}

	// constructor
	public Item(User user) {
		super(user);
		setPersistableState(PersistableState.ACTIVE);
	}

	/**
	 * Returns true if the item is completed as determined by whether a rating exists or not.
	 *
	 * @return boolean
	 */
	public boolean isCompleted() {
		return completedDate != null;
	}

	/**
	 * Returns true if the item was completed within the given interval, false otherwise.
	 *
	 * @param interval
	 * @return boolean
	 */
	public boolean isCompletedWithinInterval(Interval interval) {
		return isCompleted() && interval.contains(completedDate);
	}

	/**
	 * Returns a slug from the name property.
	 *
	 * @return String
	 */
	public String getNameSlug() {
		return toSlug(name);
	}

	@Override
	public int compareTo(Item o) {
		if(isCompleted() && !o.isCompleted())
			return 1;

		if(!isCompleted() && o.isCompleted())
			return -1;

		if(isCompleted() && o.isCompleted())
			return o.getCompletedDate().compareTo(getCompletedDate());

		return name.compareToIgnoreCase(o.getName());
	}

	// getters and setters
	public String getCompletedDateString() {
		return toDateString(completedDate);
	}

	public void setCompletedDateString(String completedDateString) {
		this.completedDate = fromDateString(completedDateString);
	}

	public DateTime getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(DateTime completedDate) {
		this.completedDate = completedDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}