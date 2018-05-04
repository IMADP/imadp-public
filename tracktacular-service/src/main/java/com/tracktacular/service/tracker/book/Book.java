package com.tracktacular.service.tracker.book;

import java.util.Comparator;
import java.util.Set;

import org.apache.commons.lang.WordUtils;
import org.joda.time.DateTime;

import com.google.common.base.Objects;
import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Book
 *
 * A representation of a book.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Book extends AbstractPersistableUser {

	// static Properties
	public static final Property<Book, String> TITLE = Property.of("title");
	public static final Property<Book, String> AUTHOR = Property.of("author");
	public static final Property<Book, String> TAG = Property.of("tag");
	public static final Property<Book, String> NOTES = Property.of("notes");
	public static final Property<Book, Integer> RATING = Property.of("rating");
	public static final Property<Book, DateTime> TARGET_DATE = Property.of("targetDate");

	// properties
	private String title;
	private String author;
	private String tag;
	private String notes;
	private Integer rating;
	private DateTime targetDate;
	private Set<Chapter> chapters;

	// constructor
	public Book() {
		this(null);
	}

	// constructor
	public Book(User user) {
		super(user);
		setPersistableState(PersistableState.ACTIVE);
	}

	/**
	 * Returns true if the book is completed as determined by whether a rating exists or not.
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

	/**
	 * Returns the count of chapters for this book.
	 *
	 * @return int
	 */
	public int getChapterCount() {
		return chapters == null ? 0 : chapters.size();
	}

	// getters and setters
	public String getTargetDateString() {
		return toDateString(targetDate);
	}

	public void setTargetDateString(String targetDateString) {
		this.targetDate = fromDateString(targetDateString);
	}

	public String getTitle() {
		return WordUtils.capitalize(title);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return WordUtils.capitalize(author);
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTag() {
		return WordUtils.capitalize(tag);
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Set<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(Set<Chapter> chapters) {
		this.chapters = chapters;
	}

	// title comparator
	public static final Comparator<Book> TITLE_COMPARATOR = new Comparator<Book>() {
		@Override
		public int compare(Book b1, Book b2) {
			return b1.getTitle().toLowerCase().compareToIgnoreCase(b2.getTitle().toLowerCase());
		}
	};

	// rating comparator
	public static final Comparator<Book> RATING_COMPARATOR = new Comparator<Book>() {
		@Override
		public int compare(Book b1, Book b2) {

			if(b1.isCompleted() && !b2.isCompleted())
				return 1;

			if(b2.isCompleted() && !b1.isCompleted())
				return -1;

			if(!Objects.equal(b1.getRating(), b2.getRating()))
				return Integer.compare(b2.getRating(), b1.getRating());

			return TITLE_COMPARATOR.compare(b1, b2);
		}
	};

	// author comparator
	public static final Comparator<Book> AUTHOR_COMPARATOR = new Comparator<Book>() {
		@Override
		public int compare(Book b1, Book b2) {

			if(!b1.getAuthor().equals(b2.getAuthor()))
				return b1.getAuthor().toLowerCase().compareToIgnoreCase(b2.getAuthor().toLowerCase());

			return TITLE_COMPARATOR.compare(b1, b2);
		}
	};

}