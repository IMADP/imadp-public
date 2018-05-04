package com.tracktacular.service.tracker.book;

import org.apache.commons.lang.WordUtils;
import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.service.Sortable;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Chapter
 *
 * A chapter for a book.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Chapter extends AbstractPersistableUser implements Sortable {

	// static Properties
	public static final Property<Chapter, Book> BOOK = Property.of("book");
	public static final Property<Chapter, String> TITLE = Property.of("title");
	public static final Property<Chapter, String> NOTES = Property.of("notes");
	public static final Property<Chapter, Integer> RATING = Property.of("rating");
	public static final Property<Chapter, DateTime> TARGET_DATE = Property.of("targetDate");
	public static final Property<Chapter, Integer> SORT = Property.of("sort");

	// properties
	private Book book;
	private String title;
	private String notes;
	private Integer rating;
	private DateTime targetDate;
	private int sort;

	// constructor
	public Chapter() {
		this(null, null);
	}

	// constructor
	public Chapter(User user, Book book) {
		super(user);
		this.book = book;
	}

	/**
	 * Returns true if the chapter is completed as determined by whether a rating exists or not.
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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
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
