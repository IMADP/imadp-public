package com.tracktacular.web.page.tracker.music;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.music.Album;
import com.tracktacular.service.tracker.music.MusicTrackerFacade;
import com.tracktacular.service.tracker.music.MusicTrackerPreferences;
import com.tracktacular.service.tracker.music.Song;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * MusicTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class MusicTrackerActionBean extends TrackerActionBean<MusicTrackerFacade, MusicTrackerPreferences> {
	private Album album;
	private Song song;

	@Override
	public Tracker getTracker() {
		return Tracker.MUSIC;
	}

	/**
	 * Save or updates a Song.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveSong() {
		Song song = getSong();
		populatePersistableUser(song);
		getTrackerFacade().saveSong(song);

		if(song.isActiveState())
			getContext().addSuccessMessage("music.saveSong.success", song.getTitle());

		else if(song.isDeletedState())
			getContext().addSuccessMessage("music.deleteSong.success", song.getTitle());

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Song.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteSong() {
		Song song = getSong();
		getTrackerFacade().deleteSong(song);
		getContext().addSuccessMessage("music.deleteSong.success", song.getTitle());
		return getAjaxSourceResolution();
	}

	/**
	 * Save or updates a Album.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveAlbum() {
		Album album = getAlbum();
		populatePersistableUser(album);
		getTrackerFacade().saveAlbum(album);

		if(album.isActiveState())
			getContext().addSuccessMessage("music.saveAlbum.success", album.getTitle());

		else if(album.isDeletedState())
			getContext().addSuccessMessage("music.deleteAlbum.success", album.getTitle());

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Album.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteAlbum() {
		Album album = getAlbum();
		getTrackerFacade().deleteAlbum(album);
		getContext().addSuccessMessage("music.deleteAlbum.success", album.getTitle());
		return getAjaxSourceResolution();
	}

	// getters and setters
	public void setSong(Song song) {
		this.song = song;
	}

	public Song getSong() {
		return song;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

}