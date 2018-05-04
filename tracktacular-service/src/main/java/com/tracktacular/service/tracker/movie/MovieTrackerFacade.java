package com.tracktacular.service.tracker.movie;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * MovieTrackerFacade
 *
 * Provides functionality for movie tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface MovieTrackerFacade extends TrackerFacade {

	/**
	 * Gets a movie by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Movie
	 */
	public Movie getMovie(User user, String uid);

	/**
	 * Saves a movie.
	 *
	 * @param movie
	 */
	public void saveMovie(Movie movie);

	/**
	 * Deletes a movie.
	 *
	 * @param movie
	 */
	public void deleteMovie(Movie movie);

	/**
	 * Finds a List of active Movies for a User.
	 *
	 * @param user
	 * @param sortProperty
	 * @return Movies
	 */
	public Movies findActiveMovies(User user, String sortProperty);

	/**
	 * Finds a List of deleted Movies for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Movie>
	 */
	public List<Movie> findDeletedMovies(User user, CriteriaParams<Movie> criteriaParams);

	/**
	 * Finds the count of all deleted Movies for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedMovieCount(User user);

}