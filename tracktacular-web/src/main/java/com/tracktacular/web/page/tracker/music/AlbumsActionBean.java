package com.tracktacular.web.page.tracker.music;

import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.service.tracker.music.Albums;


/**
 * AlbumsActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-music/albums/by-{sortProperty=artist}")
public class AlbumsActionBean extends MusicTrackerActionBean {

	// properties
	private String sortProperty;
	private Albums albums;

	/**
	 * Returns a list of albums.
	 *
	 * @return Albums
	 */
	public Albums getAlbums() {
		if(albums == null)
			albums = getTrackerFacade().findActiveAlbums(getTrackerUser(), sortProperty);

		return albums;
	}

	// getter and setters
	public String getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}

}