package com.tracktacular.service.tracker.music;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * MusicTrackerFacade
 *
 * Provides functionality for album tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface MusicTrackerFacade extends TrackerFacade {

	/**
	 * Gets a song by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Song
	 */
	public Song getSong(User user, String uid);

	/**
	 * Saves a song.
	 *
	 * @param song
	 */
	public void saveSong(Song song);

	/**
	 * Deletes a song.
	 *
	 * @param song
	 */
	public void deleteSong(Song song);

	/**
	 * Finds a List of active songs for a User.
	 *
	 * @param user
	 * @param sortProperty
	 * @return Songs
	 */
	public Songs findActiveSongs(User user, String sortProperty);

	/**
	 * Finds a List of deleted Songs for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Song>
	 */
	public List<Song> findDeletedSongs(User user, CriteriaParams<Song> criteriaParams);

	/**
	 * Finds the count of all deleted Songs for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedSongCount(User user);

	/**
	 * Gets a album by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Album
	 */
	public Album getAlbum(User user, String uid);

	/**
	 * Saves a album.
	 *
	 * @param album
	 */
	public void saveAlbum(Album album);

	/**
	 * Deletes a album.
	 *
	 * @param album
	 */
	public void deleteAlbum(Album album);

	/**
	 * Finds a List of active albums for a User.
	 *
	 * @param user
	 * @param sortProperty
	 * @return Albums
	 */
	public Albums findActiveAlbums(User user, String sortProperty);

	/**
	 * Finds a List of deleted Albums for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Album>
	 */
	public List<Album> findDeletedAlbums(User user, CriteriaParams<Album> criteriaParams);

	/**
	 * Finds the count of all deleted Albums for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedAlbumCount(User user);

}