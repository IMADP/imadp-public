package com.tracktacular.service.tracker.birthday;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.person.Person;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;
import com.tracktacular.service.Recurrence;


/**
 * Birthday
 *
 * A representation of a birthday.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Birthday extends AbstractPersistableUser implements Comparable<Birthday> {

	// static Properties
	public static final Property<Birthday, String> FIRST_NAME = Property.of("firstName");
	public static final Property<Birthday, String> MIDDLE_NAME = Property.of("middleName");
	public static final Property<Birthday, String> LAST_NAME = Property.of("lastName");
	public static final Property<Birthday, String> NOTES = Property.of("notes");
	public static final Property<Birthday, DateTime> DATE = Property.of("date");
	public static final Property<Birthday, Recurrence> ALERT_RECURRENCE = Property.of("alertRecurrence");

	// properties
	private Person person;
	private String notes;
	private Recurrence alertRecurrence;

	// constructor
	public Birthday() {
		this(null, new Person());
	}

	// constructor
	public Birthday(User user) {
		this(user, new Person());
	}

	// constructor
	public Birthday(User user, Person person) {
		super(user);
		setPersistableState(PersistableState.ACTIVE);
		this.person = person;
	}

	/**
	 * Returns true if the birthday has an alert interval that matches the current day.
	 *
	 * @return boolean
	 */
	public boolean isAlertInterval() {
		if(alertRecurrence == null || !alertRecurrence.hasPeriod())
			return false;

		return new LocalDate().plus(alertRecurrence.getPeriod()).isEqual(getNextBirthday().toLocalDate());
	}

	/**
	 * Returns the birthdate that falls within the given interval,
	 * or null if the birthdate does not fall within the given interval.
	 *
	 * @param interval
	 * @return DateTime
	 */
	public DateTime getBirthdateWithinInterval(Interval interval) {
		DateTime birthdayWithStartYear = getDate().withYear(interval.getStart().getYear());

		if(interval.contains(birthdayWithStartYear))
			return birthdayWithStartYear;

		DateTime birthdayWithEndYear = getDate().withYear(interval.getEnd().getYear());

		if(interval.contains(birthdayWithEndYear))
			return birthdayWithEndYear;

		return null;
	}

	/**
	 * Returns the age ordinal of the given birthday.
	 *
	 * @return String
	 */
	public String getAgeOrdinal() {
		int age = getAge();
		int hundredRemainder = age % 100;
		int tenRemainder = age % 10;

		if(hundredRemainder - tenRemainder == 10)
			return age + "th";

		switch (tenRemainder)
		{
			case 1:  return age + "st";
			case 2:  return age + "nd";
			case 3:  return age + "rd";
			default: return age + "th";
		}
	}

	/**
	 * In the context of the current year, returns true if a birthday is the current day or false if its not.
	 *
	 * @return boolean
	 */
	public boolean isToday() {
		return new LocalDate().isEqual(getNextBirthday().toLocalDate());
	}

	/**
	 * In the context of the current year, returns true if a birthday has passed or false if it has not.
	 *
	 * @return boolean
	 */
	public boolean isPast() {
		return new LocalDate().isAfter(getNextBirthday().toLocalDate());
	}

	/**
	 * Returns the month of this birthday.
	 *
	 * @return int
	 */
	public int getMonth() {
		return getDate().getMonthOfYear();
	}

	/**
	 * Returns the day of this birthday.
	 *
	 * @return int
	 */
	public int getDay() {
		return getDate().getDayOfMonth();
	}

	/**
	 * Returns the current age determined from this birthday.
	 *
	 * @return int
	 */
	public int getAge() {
		return getAge(getNextBirthday());
	}

	/**
	 * Returns the age determined from the original birthday to the given nextBirthday.
	 *
	 * @param nextBirthday
	 * @return int
	 */
	public int getAge(DateTime nextBirthday) {
		return Years.yearsBetween(getDate(), nextBirthday).getYears();
	}

	/**
	 * Returns the next birthday.
	 *
	 * @return DateTime
	 */
	public DateTime getNextBirthday() {
		return getDate().withYear(new DateTime().getYear());
	}

	// getters and setters

	public String getDateString() {
		return toDateString(getDate());
	}

	public String getFullName() {
		return person.getFullName();
	}

	public void setDateString(String dateString) {
		setDate(fromDateString(dateString));
	}

	public String getFirstName() {
		return person.getFirstName();
	}

	public void setFirstName(String firstName) {
		person.setFirstName(firstName);
	}

	public String getMiddleName() {
		return person.getMiddleName();
	}

	public void setMiddleName(String middleName) {
		person.setMiddleName(middleName);
	}

	public String getLastName() {
		return person.getLastName();
	}

	public void setLastName(String lastName) {
		person.setLastName(lastName);
	}

	public DateTime getDate() {
		return person.getBirthdate();
	}

	public void setDate(DateTime date) {
		person.setBirthdate(date);
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

	public Horoscope getHoroscope() {
		return Horoscope.getHoroscope(person.getBirthdate());
	}

	@SuppressWarnings("unused")
	private void setHoroscope(Horoscope horoscope) {

	}

	@Override
	public int compareTo(Birthday birthday) {
		// sort by month
		if(this.getMonth() != birthday.getMonth())
			return this.getMonth() - birthday.getMonth();

		// sort by date
		if(this.getDay() != birthday.getDay())
			return this.getDay() - birthday.getDay();

		return 0;
	}

}