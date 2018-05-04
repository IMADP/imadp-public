package com.tracktacular.web.page.tracker.music;

import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.service.tracker.music.Songs;


/**
 * SongsActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-music/songs/by-{sortProperty=artist}")
public class SongsActionBean extends MusicTrackerActionBean {

	// properties
	private String sortProperty;
	private Songs songs;

	/**
	 * Returns a list of songs.
	 *
	 * @return Songs
	 */
	public Songs getSongs() {
		if(songs == null)
			songs = getTrackerFacade().findActiveSongs(getTrackerUser(), sortProperty);

		return songs;
	}

	// getter and setters
	public String getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}

}