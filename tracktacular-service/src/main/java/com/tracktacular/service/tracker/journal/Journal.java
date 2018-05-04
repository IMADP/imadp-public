package com.tracktacular.service.tracker.journal;

import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;
import com.tracktacular.service.Recurrence;


/**
 * Journal
 *
 * A representation of a journal.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Journal extends AbstractPersistableUser {

	// static Properties
	public static final Property<Journal, String> NAME = Property.of("name");
	public static final Property<Journal, String> NOTES = Property.of("notes");
	public static final Property<Journal, String> DESCRIPTION = Property.of("description");
	public static final Property<Journal, Recurrence> ALERT_RECURRENCE = Property.of("alertRecurrence");

	// properties
	private String name;
	private String notes;
	private String description;
	private Recurrence alertRecurrence;

	// constructor
	public Journal() {
		this(null, null);
	}

	// constructor
	public Journal(String name, User user) {
		this.name = name;
		this.user = user;
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

	/**
	 * Returns a new entry for this journal.
	 *
	 * @return Entry
	 */
	public Entry getNewEntry() {
		return new Entry(user, this);
	}

	// getters and setters
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

	public String getNameSlug() {
		return toSlug(getName());
	}

	@SuppressWarnings("unused")
	private void setNameSlug(String nameSlug) {

	}

	public Recurrence getAlertRecurrence() {
		return alertRecurrence;
	}

	public void setAlertRecurrence(Recurrence alertRecurrence) {
		this.alertRecurrence = alertRecurrence;
	}

}