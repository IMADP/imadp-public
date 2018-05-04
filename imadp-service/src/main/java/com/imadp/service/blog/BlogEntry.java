package com.imadp.service.blog;

import java.util.Comparator;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.service.Sortable;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * BlogEntry
 *
 * A representation of a blog entry.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BlogEntry extends AbstractPersistableUser implements Sortable {

	// static Properties
	public static final Property<BlogEntry, String> TITLE = Property.of("name");
	public static final Property<BlogEntry, String> DESCRIPTION = Property.of("description");
	public static final Property<BlogEntry, String> KEYWORDS = Property.of("keywords");
	public static final Property<BlogEntry, String> CONTENT = Property.of("content");
	public static final Property<BlogEntry, BlogCategory> CATEGORY = Property.of("category");
	public static final Property<BlogEntry, DateTime> DATE = Property.of("date");
	public static final Property<BlogEntry, Integer> SORT = Property.of("sort");

	// properties
	private String title;
	private String description;
	private String keywords;
	private String content;
	private BlogCategory category;
	private DateTime date;
	private int sort;

	// constructor
	public BlogEntry() {
		this(null);
	}

	// constructor
	public BlogEntry(User user) {
		super(user);
		setDate(new DateTime());
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
	public String getDateString() {
		return toDateString(date);
	}

	public void setDateString(String dateString) {
		this.date = fromDateString(dateString);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BlogCategory getCategory() {
		return category;
	}

	public void setCategory(BlogCategory category) {
		this.category = category;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	@Override
	public int getSort() {
		return sort;
	}

	@Override
	public void setSort(int sort) {
		this.sort = sort;
	}

	// date comparator
	public static final Comparator<BlogEntry> DATE_COMPARATOR = new Comparator<BlogEntry>() {
		@Override
		public int compare(BlogEntry o1, BlogEntry o2) {
			return o2.getDate().compareTo(o1.getDate());
		}
	};

}