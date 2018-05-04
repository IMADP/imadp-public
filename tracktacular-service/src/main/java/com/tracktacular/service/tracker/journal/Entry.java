package com.tracktacular.service.tracker.journal;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Entry
 *
 * An entry in a journal.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Entry extends AbstractPersistableUser {

	// static Properties
	public static final Property<Entry, Journal> JOURNAL = Property.of("journal");
	public static final Property<Entry, String> TITLE = Property.of("title");
	public static final Property<Entry, String> NOTES = Property.of("notes");
	public static final Property<Entry, String> CONTENT = Property.of("content");
	public static final Property<Entry, DateTime> DATE = Property.of("date");

	// properties
	private Journal journal;
	private String title;
	private String notes;
	private String content;
	private DateTime date;

	// constructor
	public Entry() {
		this(null, null);
	}

	// constructor
	public Entry(User user, Journal journal) {
		super(user);
		this.journal = journal;
		this.date = new DateTime();
	}

	// getters and setters
	public String getDateTimeString() {
		return toDateTimeString(date);
	}

	public void setDateTimeString(String dateTimeString) {
		this.date = fromDateTimeString(dateTimeString);
	}

	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getTitleSlug() {
		return toSlug(getTitle());
	}

}
