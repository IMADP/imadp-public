package com.tracktacular.service.tracker.blood;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * BloodPressure
 *
 * A representation of blood pressure levels in the blood.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BloodPressure extends AbstractPersistableUser {

	// static properties
	public static final Property<BloodPressure, String> NOTES = Property.of("notes");
	public static final Property<BloodPressure, Integer> SYSTOLIC = Property.of("systolic");
	public static final Property<BloodPressure, Integer> DIASTOLIC = Property.of("diastolic");
	public static final Property<BloodPressure, DateTime> DATE = Property.of("date");

	// properties
	private String notes;
	private Integer systolic;
	private Integer diastolic;
	private DateTime date;

	// constructor
	public BloodPressure() {
		this(null);
	}

	// constructor
	public BloodPressure(User user) {
		super(user);
		setPersistableState(PersistableState.ACTIVE);
	}

	// getters and setters
	public String getDateString() {
		return toDateString(date);
	}

	public void setDateString(String dateString) {
		this.date = fromDateString(dateString);
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getSystolic() {
		return systolic;
	}

	public void setSystolic(Integer systolic) {
		this.systolic = systolic;
	}

	public Integer getDiastolic() {
		return diastolic;
	}

	public void setDiastolic(Integer diastolic) {
		this.diastolic = diastolic;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

}