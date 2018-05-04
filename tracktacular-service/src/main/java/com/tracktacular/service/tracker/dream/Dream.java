package com.tracktacular.service.tracker.dream;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Dream
 *
 * A representation of a single dream.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Dream extends AbstractPersistableUser {

	// static Properties
	public static final Property<Dream, String> TITLE = Property.of("title");
	public static final Property<Dream, String> CONTENT = Property.of("content");
	public static final Property<Dream, String> ANALYSIS = Property.of("analysis");
	public static final Property<Dream, DateTime> DATE = Property.of("date");
	public static final Property<Dream, Boolean> LUCID = Property.of("lucid");
	public static final Property<Dream, Boolean> FAVORITE = Property.of("favorite");

	// properties
	private String title;
	private String analysis;
	private String content;
	private DateTime date;
	private Set<Dreamsign> dreamsigns;
	private boolean lucid;
	private boolean favorite;

	// constructor
	public Dream() {
		this(null);
	}

	// constructor
	public Dream(User user) {
		super(user);
		setPersistableState(PersistableState.ACTIVE);
	}

	/**
	 * Adds a dreamsign to the dream.
	 *
	 * @param name
	 */
	public void addDreamsign(String name) {
		if(dreamsigns == null)
			dreamsigns = new HashSet<>();

		dreamsigns.add(new Dreamsign(user, this, name, getPersistableState()));
	}

	/**
	 * Returns dreamsigns as a comma separated list of values.
	 *
	 * @return String
	 */
	public String getDreamsignsAsString() {
		return Dreamsign.getTagNamesAsString(dreamsigns);
	}

	/**
	 * Sets the dreamsigns from a comma separated list of names.
	 *
	 * @param dreamsignsAsString
	 */
	public void setDreamsignsAsString(String dreamsignsAsString) {
		setDreamsigns(null);

		for(String name : Dreamsign.getTagsNamesAsSet(dreamsignsAsString))
			addDreamsign(name);
	}

	/**
	 * Returns the count of dreamsigns for a given dream.
	 *
	 * @return int
	 */
	public int getDreamsignCount() {
		return getDreamsigns() == null ? 0 : getDreamsigns().size();
	}

	// getters and setters
	public String getDateTimeString() {
		return toDateTimeString(date);
	}

	public void setDateTimeString(String dateTimeString) {
		this.date = fromDateTimeString(dateTimeString);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isLucid() {
		return lucid;
	}

	public void setLucid(boolean lucid) {
		this.lucid = lucid;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public Set<Dreamsign> getDreamsigns() {
		return dreamsigns;
	}

	public void setDreamsigns(Set<Dreamsign> dreamsigns) {
		this.dreamsigns = dreamsigns;
	}

	public String getTitleSlug() {
		return toSlug(getTitle());
	}

}
