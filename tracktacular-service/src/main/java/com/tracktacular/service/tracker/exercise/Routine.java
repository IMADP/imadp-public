package com.tracktacular.service.tracker.exercise;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;
import com.tracktacular.service.Recurrence;


/**
 * Routine
 *
 * A representation of an exercise routine.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Routine extends AbstractPersistableUser {

	// static Properties
	public static final Property<Routine, String> NAME = Property.of("name");
	public static final Property<Routine, String> NOTES = Property.of("notes");
	public static final Property<Routine, String> DESCRIPTION = Property.of("description");
	public static final Property<Routine, Recurrence> ALERT_RECURRENCE = Property.of("alertRecurrence");
	public static final Property<Routine, DateTime> START_DATE = Property.of("startDate");

	// properties
	private String name;
	private String notes;
	private String description;
	private DateTime startDate;
	private Recurrence alertRecurrence;

	// constructor
	public Routine() {
		this(null);
	}

	// constructor
	public Routine(User user) {
		super(user);
		this.startDate = new DateTime();
		setPersistableState(PersistableState.ACTIVE);
	}

	/**
	 * Returns true if an alert recurrence was found, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isAlertRecurring() {
		return alertRecurrence != null && alertRecurrence.hasPeriod();
	}

	// getters and setters
	public String getStartDateString() {
		return toDateString(startDate);
	}

	public void setStartDateString(String startDateString) {
		this.startDate = fromDateString(startDateString);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameSlug() {
		return toSlug(getName());
	}

	@SuppressWarnings("unused")
	private void setNameSlug(String nameSlug) {

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Recurrence getAlertRecurrence() {
		return alertRecurrence;
	}

	public void setAlertRecurrence(Recurrence alertRecurrence) {
		this.alertRecurrence = alertRecurrence;
	}

}
