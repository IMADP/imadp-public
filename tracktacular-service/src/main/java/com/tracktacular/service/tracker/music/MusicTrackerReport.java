package com.tracktacular.service.tracker.music;

import java.util.List;

import com.google.common.collect.Lists;
import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * MusicTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class MusicTrackerReport extends AbstractTrackerReport {
	private final Songs songs;
	private final List<Song> unratedSongs;
	private final List<Song> targetDateSongs;
	private final Albums albums;
	private final List<Album> unratedAlbums;
	private final List<Album> targetDateAlbums;

	// constructor
	public MusicTrackerReport(MusicTrackerPreferences preferences, Songs songs, Albums albums) {
		this.songs = songs;
		this.unratedSongs = songs.getUnratedSongs();
		this.targetDateSongs = Lists.newArrayList();
		this.albums = albums;
		this.unratedAlbums = albums.getUnratedAlbums();
		this.targetDateAlbums = Lists.newArrayList();

    	if(preferences.isAlertOnTargetDates())
    	{
    	 	for(SongCategory songCategory : songs.getSongCategories())
	    		for(Song song : songCategory.getItems())
	    			if(isCurrentDate(song.getTargetDate()))
	    				targetDateSongs.add(song);

    	 	for(AlbumCategory albumCategory : albums.getAlbumCategories())
	    		for(Album album : albumCategory.getItems())
	    			if(isCurrentDate(album.getTargetDate()))
	    				targetDateAlbums.add(album);
    	}
	}

	@Override
	public boolean isEnabled() {
		return getSongCount() + getAlbumCount() > 0;
	}

    @Override
    public int getAlertCount() {
    	return targetDateSongs.size() + targetDateAlbums.size();
    }

    /**
	 * Returns the count of songs.
	 *
	 * @return int
	 */
	public int getSongCount() {
		return songs.getSongCount();
	}

	/**
	 * Returns the count of unrated songs.
	 *
	 * @return int
	 */
	public int getUnratedSongCount() {
		return unratedSongs.size();
	}

	/**
	 * Returns the count of albums.
	 *
	 * @return int
	 */
	public int getAlbumCount() {
		return albums.getAlbumCount();
	}

	/**
	 * Returns the count of unrated albums.
	 *
	 * @return int
	 */
	public int getUnratedAlbumCount() {
		return unratedAlbums.size();
	}

	// getters
	public List<Song> getUnratedSongs() {
		return unratedSongs;
	}

	public List<Song> getTargetDateSongs() {
		return targetDateSongs;
	}

	public List<Album> getUnratedAlbums() {
		return unratedAlbums;
	}

	public List<Album> getTargetDateAlbums() {
		return targetDateAlbums;
	}

}