package com.tracktacular.service.tracker.birthday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;

import com.imadp.core.AbstractSerializable;


/**
 * Birthdays
 *
 * A collection of a birthdays.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Birthdays extends AbstractSerializable {

	// all birthdays count
	private final int allBirthdayCount;

	// currentMonth
	private final int currentMonth;

	// list of birthdays by month
	private final List<Month> birthdayMonths;

	// constructor
	public Birthdays(List<Birthday> allBirthdays) {
		DateTime date = new DateTime();
		this.birthdayMonths = new ArrayList<>(12);
		this.currentMonth = date.getMonthOfYear();
		this.allBirthdayCount = allBirthdays.size();

		Collections.sort(allBirthdays);

		// populate the list with all 12 months
		for(int i=1; i<=12; i++)
		{
			Month month = new Month(i);
			birthdayMonths.add(month);
		}

		// populate birthdays
		for(Birthday birthday : allBirthdays)
			birthdayMonths.get(birthday.getMonth()-1).birthdays.add(birthday);
	}

	/**
	 * Returns the current month.
	 *
	 * @return int
	 */
	public int getCurrentMonth() {
		return currentMonth;
	}

	/**
	 * Returns the count of all birthdays.
	 *
	 * @return int
	 */
	public int getAllBirthdayCount() {
		return allBirthdayCount;
	}

	// getters
	public List<Month> getBirthdayMonths() {
		return birthdayMonths;
	}

	/**
	 * Month
	 *
	 * A collection of a birthdays for a given month.
	 *
	 * @version 1.0
	 * @author Anthony DePalma
	 */
	public class Month extends AbstractSerializable {
		private final List<Birthday> birthdays;
		private final int month;

		// constructor
		public Month(int month) {
			this.month = month;
			this.birthdays = new ArrayList<>();
		}

		/**
		 * Returns true if the birthday month is the current month, false otherwise.
		 *
		 * @return boolean
		 */
		public boolean isCurrentMonth() {
			DateTime currentMonth = new DateTime();
			return getMonthAsDate().getMonthOfYear() == currentMonth.getMonthOfYear();
		}

		/**
		 * Returns true if the birthday month is the next month, false otherwise.
		 *
		 * @return boolean
		 */
		public boolean isNextMonth() {
			DateTime nextMonth = new DateTime().plusMonths(1);
			return getMonthAsDate().getMonthOfYear() == nextMonth.getMonthOfYear();
		}

		/**
		 * Returns the count of all birthdays for this month.
		 *
		 * @return int
		 */
		public int getBirthdayCount() {
			return birthdays.size();
		}

		/**
		 * Returns a date with the month set.
		 *
		 * @return DateTime
		 */
		public DateTime getMonthAsDate() {
			return new DateTime().withMonthOfYear(month);
		}

		// getters
		public List<Birthday> getBirthdays() {
			return birthdays;
		}

		public int getMonth() {
			return month;
		}

	}

}