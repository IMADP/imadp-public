package com.tracktacular.service.tracker.cholesterol;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Cholesterol
 *
 * A representation of cholesterol levels in the blood.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Cholesterol extends AbstractPersistableUser {

	// static properties
	public static final Property<Cholesterol, String> NOTES = Property.of("notes");
	public static final Property<Cholesterol, Integer> TOTAL_CHOLESTEROL = Property.of("totalCholesterol");
	public static final Property<Cholesterol, Integer> LDL_CHOLESTEROL = Property.of("ldlCholesterol");
	public static final Property<Cholesterol, Integer> HDL_CHOLESTEROL = Property.of("hdlCholesterol");
	public static final Property<Cholesterol, Integer> TRIGLYCERIDES = Property.of("triglycerides");
	public static final Property<Cholesterol, DateTime> DATE = Property.of("date");

	// properties
	private String notes;
	private Integer totalCholesterol;
	private Integer ldlCholesterol;
	private Integer hdlCholesterol;
	private Integer triglycerides;
	private DateTime date;

	// constructor
	public Cholesterol() {
		this(null);
	}

	// constructor
	public Cholesterol(User user) {
		super(user);
		setPersistableState(PersistableState.ACTIVE);
	}

	/**
	 * Returns true if any values have been entered, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean hasEntry() {
		return totalCholesterol != null || ldlCholesterol != null || hdlCholesterol != null || triglycerides != null;
	}

	// getters and setters
	public String getDateString() {
		return toDateString(date);
	}

	public void setDateString(String dateString) {
		this.date = fromDateString(dateString);
	}

	public Integer getTotalCholesterol() {
		return totalCholesterol;
	}

	public void setTotalCholesterol(Integer totalCholesterol) {
		this.totalCholesterol = totalCholesterol;
	}

	public Integer getLdlCholesterol() {
		return ldlCholesterol;
	}

	public void setLdlCholesterol(Integer ldlCholesterol) {
		this.ldlCholesterol = ldlCholesterol;
	}

	public Integer getHdlCholesterol() {
		return hdlCholesterol;
	}

	public void setHdlCholesterol(Integer hdlCholesterol) {
		this.hdlCholesterol = hdlCholesterol;
	}

	public Integer getTriglycerides() {
		return triglycerides;
	}

	public void setTriglycerides(Integer triglycerides) {
		this.triglycerides = triglycerides;
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

}