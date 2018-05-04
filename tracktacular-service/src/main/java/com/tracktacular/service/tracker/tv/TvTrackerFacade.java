package com.tracktacular.service.tracker.tv;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * TvTrackerFacade
 *
 * Provides functionality for show tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface TvTrackerFacade extends TrackerFacade {

	/**
	 * Gets a show by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Show
	 */
	public Show getShow(User user, String uid);

	/**
	 * Saves a show.
	 *
	 * @param show
	 */
	public void saveShow(Show show);

	/**
	 * Deletes a show.
	 *
	 * @param show
	 */
	public void deleteShow(Show show);

	/**
	 * Gets a episode by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Episode
	 */
	public Episode getEpisode(User user, String uid);

	/**
	 * Saves a episode.
	 *
	 * @param episode
	 */
	public void saveEpisode(Episode episode);

	/**
	 * Saves a list of episodes.
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

	/**
	 * Finds a List of active Shows for a User.
	 *
	 * @param user
	 * @param sortProperty
	 * @return Shows
	 */
	public Shows findActiveShows(User user, String sortProperty);

	/**
	 * Finds a List of deleted Shows for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Show>
	 */
	public List<Show> findDeletedShows(User user, CriteriaParams<Show> criteriaParams);

	/**
	 * Finds the count of all deleted Shows for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedShowCount(User user);

}