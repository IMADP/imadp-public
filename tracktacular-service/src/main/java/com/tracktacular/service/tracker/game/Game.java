package com.tracktacular.service.tracker.game;

import java.util.Comparator;

import org.apache.commons.lang.WordUtils;
import org.joda.time.DateTime;

import com.google.common.base.Objects;
import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Game
 *
 * A representation of a game.
 *
 * @version 1.0
 * @platform Anthony DePalma
 */
public class Game extends AbstractPersistableUser {

	// static Properties
	public static final Property<Game, String> TITLE = Property.of("title");
	public static final Property<Game, String> PLATFORM = Property.of("platform");
	public static final Property<Game, String> TAG = Property.of("tag");
	public static final Property<Game, String> NOTES = Property.of("notes");
	public static final Property<Game, Integer> RATING = Property.of("rating");
	public static final Property<Game, DateTime> TARGET_DATE = Property.of("targetDate");

	// properties
	private String title;
	private String platform;
	private String tag;
	private String notes;
	private Integer rating;
	private DateTime targetDate;

	// constructor
	public Game() {
		this(null);
	}

	// constructor
	public Game(User user) {
		super(user);
		setPersistableState(PersistableState.ACTIVE);
	}

	/**
	 * Returns true if the game is completed as determined by whether a rating exists or not.
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

	public String getTitle() {
		return WordUtils.capitalize(title);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlatform() {
		return WordUtils.capitalize(platform);
	}

	public void setPlatform(String platform) {
		this.platform = platform;
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

	// title comparator
	public static final Comparator<Game> TITLE_COMPARATOR = new Comparator<Game>() {
		@Override
		public int compare(Game b1, Game b2) {
			return b1.getTitle().toLowerCase().compareToIgnoreCase(b2.getTitle().toLowerCase());
		}
	};

	// rating comparator
	public static final Comparator<Game> RATING_COMPARATOR = new Comparator<Game>() {
		@Override
		public int compare(Game b1, Game b2) {

			if(b1.isCompleted() && !b2.isCompleted())
				return 1;

			if(b2.isCompleted() && !b1.isCompleted())
				return -1;

			if(!Objects.equal(b1.getRating(), b2.getRating()))
				return Integer.compare(b2.getRating(), b1.getRating());

			return TITLE_COMPARATOR.compare(b1, b2);
		}
	};

	// platform comparator
	public static final Comparator<Game> PLATFORM_COMPARATOR = new Comparator<Game>() {
		@Override
		public int compare(Game b1, Game b2) {

			if(!b1.getPlatform().equalsIgnoreCase(b2.getPlatform()))
				return b1.getPlatform().toLowerCase().compareToIgnoreCase(b2.getPlatform().toLowerCase());

			return TITLE_COMPARATOR.compare(b1, b2);
		}
	};

}