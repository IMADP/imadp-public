package com.tracktacular.service.tracker.tv;

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
 * Show
 *
 * A representation of a show.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Show extends AbstractPersistableUser {

	// static Properties
	public static final Property<Show, String> TITLE = Property.of("title");
	public static final Property<Show, String> TAG = Property.of("tag");
	public static final Property<Show, String> NOTES = Property.of("notes");
	public static final Property<Show, Integer> RATING = Property.of("rating");
	public static final Property<Show, DateTime> TARGET_DATE = Property.of("targetDate");

	// properties
	private String title;
	private String tag;
	private String notes;
	private Integer rating;
	private DateTime targetDate;
	private Set<Episode> episodes;

	// constructor
	public Show() {
		this(null);
	}

	// constructor
	public Show(User user) {
		super(user);
		setPersistableState(PersistableState.ACTIVE);
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

	/**
	 * Returns the count of episodes for this show.
	 *
	 * @return int
	 */
	public int getEpisodeCount() {
		return episodes == null ? 0 : episodes.size();
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

	public Set<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(Set<Episode> episodes) {
		this.episodes = episodes;
	}

	// title comparator
	public static final Comparator<Show> TITLE_COMPARATOR = new Comparator<Show>() {
		@Override
		public int compare(Show b1, Show b2) {
			return b1.getTitle().toLowerCase().compareTo(b2.getTitle().toLowerCase());
		}
	};

	// rating comparator
	public static final Comparator<Show> RATING_COMPARATOR = new Comparator<Show>() {
		@Override
		public int compare(Show b1, Show b2) {

			if(b1.isCompleted() && !b2.isCompleted())
				return 1;

			if(b2.isCompleted() && !b1.isCompleted())
				return -1;

			if(!Objects.equal(b1.getRating(), b2.getRating()))
				return Integer.compare(b2.getRating(), b1.getRating());

			return TITLE_COMPARATOR.compare(b1, b2);
		}
	};

}