package com.tracktacular.service.tracker.music;

import java.util.Comparator;

import org.apache.commons.lang.WordUtils;
import org.joda.time.DateTime;

import com.google.common.base.Objects;
import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Song
 *
 * A song to track.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Song extends AbstractPersistableUser {

	// static Properties
	public static final Property<Song, String> ALBUM = Property.of("album");
	public static final Property<Song, String> ARTIST = Property.of("artist");
	public static final Property<Song, String> TITLE = Property.of("title");
	public static final Property<Song, String> NOTES = Property.of("notes");
	public static final Property<Song, String> TAG = Property.of("tag");
	public static final Property<Song, String> INSTRUMENT = Property.of("instrument");
	public static final Property<Song, Integer> RATING = Property.of("rating");
	public static final Property<Song, Integer> PROGRESS = Property.of("progress");
	public static final Property<Song, DateTime> TARGET_DATE = Property.of("targetDate");

	// properties
	private String artist;
	private String album;
	private String title;
	private String instrument;
	private String notes;
	private String tag;
	private Integer rating;
	private DateTime targetDate;
	private int progress;

	// constructor
	public Song() {
		this(null);
	}

	// constructor
	public Song(User user) {
		super(user);
		setPersistableState(PersistableState.ACTIVE);
	}

	/**
	 * Returns true if the album is completed as determined by whether a rating exists or not.
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

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
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

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	// title comparator
	public static final Comparator<Song> TITLE_COMPARATOR = new Comparator<Song>() {
		@Override
		public int compare(Song b1, Song b2) {
			return b1.getTitle().toLowerCase().compareToIgnoreCase(b2.getTitle().toLowerCase());
		}
	};

	// rating comparator
	public static final Comparator<Song> RATING_COMPARATOR = new Comparator<Song>() {
		@Override
		public int compare(Song b1, Song b2) {

			if(b1.isCompleted() && !b2.isCompleted())
				return 1;

			if(b2.isCompleted() && !b1.isCompleted())
				return -1;

			if(!Objects.equal(b1.getRating(), b2.getRating()))
				return Integer.compare(b2.getRating(), b1.getRating());

			return TITLE_COMPARATOR.compare(b1, b2);
		}
	};

	// artist comparator
	public static final Comparator<Song> ARTIST_COMPARATOR = new Comparator<Song>() {
		@Override
		public int compare(Song b1, Song b2) {
			if(!b1.getArtist().equalsIgnoreCase(b2.getArtist()))
				return b1.getArtist().toLowerCase().compareToIgnoreCase(b2.getArtist().toLowerCase());

			return ALBUM_COMPARATOR.compare(b1, b2);
		}
	};

	// instrument comparator
	public static final Comparator<Song> INSTRUMENT_COMPARATOR = new Comparator<Song>() {
		@Override
		public int compare(Song b1, Song b2) {

			if (b1.getInstrument() == null)
				return 1;

			if (b2.getInstrument() == null)
				return -1;

			if(!b1.getInstrument().equalsIgnoreCase(b2.getInstrument()))
				return b1.getInstrument().toLowerCase().compareToIgnoreCase(b2.getInstrument().toLowerCase());

			return TITLE_COMPARATOR.compare(b1, b2);
		}
	};

	// album comparator
	public static final Comparator<Song> ALBUM_COMPARATOR = new Comparator<Song>() {
		@Override
		public int compare(Song b1, Song b2) {

			if (b1.getAlbum() == null)
				return 1;

			if (b2.getAlbum() == null)
				return -1;

			if(!b1.getAlbum().equalsIgnoreCase(b2.getAlbum()))
				return b1.getAlbum().toLowerCase().compareToIgnoreCase(b2.getAlbum().toLowerCase());

			return TITLE_COMPARATOR.compare(b1, b2);
		}
	};

}
