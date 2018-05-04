package com.imadp.core;

import java.io.Serializable;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.gson.Gson;

/**
 * AbstractSerializable
 *
 * An abstract serializable class providing common operations such as
 * a reflective toString implementation provided by Apache Commons.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractSerializable implements Serializable {

	// date patterns
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm";
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String TIME_PATTERN = "HH:mm";

	// date formatters
	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern(DATE_TIME_PATTERN);
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern(DATE_PATTERN);
	public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern(TIME_PATTERN);

	// slug patterns
	private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

	// gson formatter
	protected static final Gson GSON = new Gson();

	/**
	 * Converts a date to a dateString.
	 *
	 * @param date
	 * @return String
	 */
	protected final String toDateString(DateTime date) {
		return date == null ? null : DATE_FORMATTER.print(date);
	}

	/**
	 * Converts a dateString to a DateTime.
	 *
	 * @param dateString
	 * @return DateTime
	 */
	protected final DateTime fromDateString(String dateString) {
		return dateString == null ? null : DATE_FORMATTER.parseDateTime(dateString);
	}

	/**
	 * Converts a dateTime to a dateTimeString.
	 *
	 * @param dateTime
	 * @return String
	 */
	protected final String toDateTimeString(DateTime dateTime) {
		return dateTime == null ? null : DATE_TIME_FORMATTER.print(dateTime);
	}

	/**
	 * Converts a dateTimeString to a DateTime.
	 *
	 * @param dateTimeString
	 * @return DateTime
	 */

	protected final DateTime fromDateTimeString(String dateTimeString) {
		return dateTimeString == null ? null : DATE_TIME_FORMATTER.parseDateTime(dateTimeString);
	}

	/**
	 * Converts a dateTime to a timeString.
	 *
	 * @param dateTime
	 * @return String
	 */
	protected final String toTimeString(DateTime time) {
		return time == null ? null : TIME_FORMATTER.print(time);
	}

	/**
	 * Converts a timeString to a DateTime.
	 *
	 * @param timeString
	 * @return DateTime
	 */

	protected final DateTime fromTimeString(String timeString) {
		return timeString == null ? null : TIME_FORMATTER.parseDateTime(timeString);
	}

	/**
	 * Returns a slug from the given input string.
	 *
	 * @param input
	 * @return String
	 */
	protected final String toSlug(String input) {
		if(input == null)
			return null;

		String removeWhitespace = WHITESPACE.matcher(input).replaceAll("-");
		String normalized = Normalizer.normalize(removeWhitespace, Form.NFD);
		String slug = NON_LATIN.matcher(normalized).replaceAll("");
		return slug.toLowerCase(Locale.ENGLISH);
	}

	/**
	 * This implementation uses Apache Commons's ToStringBuilder to generate values through
	 * reflection. Subclasses are therefore not required to override this method to add any
	 * additional fields. While convenient, this method is considerably slower than manually
	 * appending fields.
	 *
	 * @note This method will fail under a security manager, unless the appropriate permissions
	 *       are set up correctly.
	 *
	 * @note If an object is expected to use toString often, it is suggested
	 *       to override this method and manually append all fields, which will greatly
	 *       improve the performance of this method.
	 *
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}