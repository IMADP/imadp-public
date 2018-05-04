package com.tracktacular.service.tracker.movie;

import org.joda.time.DateTime;

import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;



/**
 * MovieTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class MovieTrackerDemo extends AbstractTrackerDemo {

	private MovieTrackerFacade trackerFacade;

	// constructor
	public MovieTrackerDemo(MovieTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		Movie movie;

		// target date
		addMovie(user, "American History X", "Crime, Drama", null, new DateTime().plusDays(4));

		// uncompleted
		addMovie(user, "A Clockwork Orange", "Crime, Drama, Science Fiction", null, null);

		// completed
		addMovie(user, "Office Space", "Comedy, Crime", 8, null);
		addMovie(user, "Shawshank Redemption", "Crime, Drama", 9, null);
		addMovie(user, "Pulp Fiction", "Crime, Thriller", 7, null);
		addMovie(user, "Fight Club", "Drama", 9, null);
		addMovie(user, "Casablanca",  "Drama, Romance, War", 10, null);
		addMovie(user, "The Matrix",  "Action, Adventure, Science Fiction", 8, null);
		addMovie(user, "Inception",  "Action, Adventure, Mystery", 7, null);
		addMovie(user, "Memento", "Mystery, Thriller", 5, null);
		addMovie(user, "The Departed", "Crime, Drama, Thriller", 8, null);
		addMovie(user, "Alien", "Horror, Science Fiction", 7, null);
		addMovie(user, "Aliens", "Horror, Science Fiction", 9, null);

		movie = addMovie(user, "Alien 3", "Horror, Science Fiction", 4, null);
		movie.setNotes("The beginning of this movie seriously cheapens the end of the previous one.");
		trackerFacade.saveMovie(movie);
	}

	/**
	 * Adds a movie.
	 *
	 * @param user
	 * @param title
	 * @param tag
	 * @param rating
	 * @param targetDate
	 * @return movie
	 */
	private Movie addMovie(User user, String title, String tag, Integer rating, DateTime targetDate) {
		Movie movie = new Movie(user);
		movie.setTitle(title);
		movie.setTag(tag);
		movie.setRating(rating);
		movie.setTargetDate(targetDate);

		trackerFacade.saveMovie(movie);

		return movie;
	}

}
