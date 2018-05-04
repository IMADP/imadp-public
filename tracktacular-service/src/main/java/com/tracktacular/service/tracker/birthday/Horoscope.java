package com.tracktacular.service.tracker.birthday;

import org.joda.time.DateTime;

/**
 * Enumerated values of horoscopes.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public enum Horoscope {
	ARIES,
	TAURUS,
	GEMINI,
	CANCER,
	LEO,
	VIRGO,
	LIBRA,
	SCORPIO,
	SAGITTARIUS,
	CAPRICORN,
	AQUARIUS,
	PISCES;

	/**
	 * Returns a lowercase representation of the horoscope.
	 *
	 * @return String
	 */
	public String getLowerCase() {
		return this.name().toLowerCase();
	}

	/**
	 * Returns a horoscope for a given date.
	 *
	 * @param date
	 * @return Horoscope
	 */
	public static Horoscope getHoroscope(DateTime date) {
		if(date == null)
			return null;

		int month = date.getMonthOfYear();
		int day = date.getDayOfMonth();

		// aries: 3/21 - 4/19
		if((month == 3 && day >= 21) || (month == 4 && day <= 19))
			return Horoscope.ARIES;

		// taurus: 4/20 - 5/20
		if((month == 4 && day >= 20) || (month == 5 && day <= 20))
			return Horoscope.TAURUS;

		// gemini: 5/21 - 6/20
		if((month == 5 && day >= 21) || (month == 6 && day <= 20))
			return Horoscope.GEMINI;

		// cancer: 6/21 - 7/22
		if((month == 6 && day >= 21) || (month == 7 && day <= 22))
			return Horoscope.CANCER;

		// leo: 7/23 - 8/22
		if((month == 7 && day >= 23) || (month == 8 && day <= 22))
			return Horoscope.LEO;

		// virgo: 8/23 - 9/22
		if((month == 8 && day >= 23) || (month == 9 && day <= 22))
			return Horoscope.VIRGO;

		// libra: 9/23 - 10/22
		if((month == 9 && day >= 23) || (month == 10 && day <= 22))
			return Horoscope.LIBRA;

		// scorpio: 10/23 - 11/21
		if((month == 10 && day >= 23) || (month == 11 && day <= 21))
			return Horoscope.SCORPIO;

		// sagittarius: 11/22 - 12/21
		if((month == 11 && day >= 22) || (month == 12 && day <= 21))
			return Horoscope.SAGITTARIUS;

		// capricorn: 12/22 - 1/19
		if((month == 12 && day >= 22) || (month == 1 && day <= 19))
			return Horoscope.CAPRICORN;

		// acquarius: 1/20 - 2/18
		if((month == 1 && day >= 20) || (month == 2 && day <= 18))
			return Horoscope.AQUARIUS;

		// pisces: 2/19 - 3/20
		if((month == 2 && day >= 19) || (month == 3 && day <= 20))
			return Horoscope.PISCES;

		throw new AssertionError("Horoscope is missing a date ["+date+"]");
	}

}