package com.imadp.service.report;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.imadp.core.Property;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;



/**
 * Report
 *
 * Provides the foundation for any kind of generated report.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Report extends AbstractPersistableUser  {

	// static Properties
	public static final Property<Report, String> TITLE = Property.of("title");
	public static final Property<Report, String> SUMMARY = Property.of("summary");
	public static final Property<Report, DateTime> DATE = Property.of("date");

	// properties
	private String title;
	private String summary;
	private DateTime date;

	// constructor
	public Report() {

	}

	// constructor
	public Report(User user) {
		super(user);
	}

	/**
	 * Returns the date as a medium date time string.
	 *
	 * @return String
	 */
	public String getDateTimeStringLong() {
		return DateTimeFormat.longDateTime().print(date);
	}

	// getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

}