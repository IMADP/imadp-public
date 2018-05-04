package com.tracktacular.service.tracker.tv;

import org.apache.commons.lang.WordUtils;
import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.service.Sortable;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Episode
 *
 * A show for a show.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Episode extends AbstractPersistableUser implements Sortable {

	// static Properties
	public static final Property<Episode, Show> SHOW = Property.of("show");
	public static final Property<Episode, String> TITLE = Property.of("title");
	public static final Property<Episode, String> NOTES = Property.of("notes");
	public static final Property<Episode, Integer> RATING = Property.of("rating");
	public static final Property<Episode, DateTime> TARGET_DATE = Property.of("targetDate");
	public static final Property<Episode, Integer> SORT = Property.of("sort");

	// properties
	private Show show;
	private String title;
	private String notes;
	private Integer rating;
	private DateTime targetDate;
	private int sort;

	// constructor
	public Episode() {
		this(null, null);
	}

	// constructor
	public Episode(User user, Show show) {
		super(user);
		this.show = show;
	}

	/**
	 * Returns true if the show is completed as determined by whether a rating exists or not.
	 *
	 * @return boolean
	 */
	public boolean isCompleted() {
		return rating != null;
	}

	/**
	 * Returns a slug from the title property.
	 *
	 * @return String
	 */
	public String getTitleSlug() {
		return toSlug(title);
	}

	// getters and setters
	public String getTargetDateString() {
		return toDateString(targetDate);
	}

	public void setTargetDateString(String targetDateString) {
		this.targetDate = fromDateString(targetDateString);
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public String getTitle() {
		return WordUtils.capitalize(title);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public DateTime getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(DateTime targetDate) {
		this.targetDate = targetDate;
	}

	@Override
	public int getSort() {
		return sort;
	}

	@Override
	public void setSort(int sort) {
		this.sort = sort;
	}

}
