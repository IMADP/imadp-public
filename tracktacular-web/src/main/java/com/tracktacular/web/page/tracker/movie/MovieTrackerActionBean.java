package com.tracktacular.web.page.tracker.movie;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.movie.Movie;
import com.tracktacular.service.tracker.movie.MovieTrackerFacade;
import com.tracktacular.service.tracker.movie.MovieTrackerPreferences;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * MovieTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class MovieTrackerActionBean extends TrackerActionBean<MovieTrackerFacade, MovieTrackerPreferences> {
	private Movie movie;

	@Override
	public Tracker getTracker() {
		return Tracker.MOVIE;
	}

	/**
	 * Save or updates a Movie.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveMovie() {
		Movie movie = getMovie();
		populatePersistableUser(movie);
		getTrackerFacade().saveMovie(movie);

		if(movie.isActiveState())
			getContext().addSuccessMessage("movie.saveMovie.success", movie.getTitle());

		else if(movie.isDeletedState())
			getContext().addSuccessMessage("movie.deleteMovie.success", movie.getTitle());

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Movie.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteMovie() {
		Movie movie = getMovie();
		getTrackerFacade().deleteMovie(movie);
		getContext().addSuccessMessage("movie.deleteMovie.success", movie.getTitle());
		return getAjaxSourceResolution();
	}

	// getters and setters
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Movie getMovie() {
		return movie;
	}

}