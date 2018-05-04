package com.tracktacular.service.tracker.tv;

import java.util.List;

import com.imadp.service.user.PersistableUserService;

/**
 * ShowService
 *
 * Provides common retrieval operations for Show objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface ShowService extends PersistableUserService<Show> {

	/**
	 * Saves an episode.
	 *
	 * @param episode
	 */
	public void saveEpisode(Episode episode);

	/**
	 * Saves a list of show shows.
	 *
	 * @param episodes
	 */
	public void saveEpisodes(List<Episode> episodes);

	/**
	 * Deletes a episode.
	 *
	 * @param episode
	 */
	public void deleteEpisode(Episode episode);

}