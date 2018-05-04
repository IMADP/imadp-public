package com.tracktacular.service.tracker;

import com.google.common.base.CaseFormat;

/**
 * Tracker
 *
 * Enumeration containing the values of all trackers.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public enum Tracker {
	BIRTHDAY,
	BLOOD,
	BODY,
	BOOK,
	BUCKET,
	BUDGET,
	CALENDAR,
	CHOLESTEROL,
	DREAM,
	GAME,
	GIFT,
	GOAL,
	EXERCISE,
	JOURNAL,
	MOVIE,
	MUSIC,
	RECIPE,
	RESTAURANT,
	TASK,
	TV,
	WISH;

	/**
	 * Returns the uppercase camel form of the tracker name.
	 *
	 * @return String
	 */
	public String getCapitalizedName() {
		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, toString());
	}

	/**
	 * Returns the lowercase camel form of the tracker name.
	 *
	 * @return String
	 */
	public String getName() {
		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, toString());
	}

}