package com.tracktacular.web.page.tracker.movie;

import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.service.tracker.movie.Movies;


/**
 * MoviesActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-movie/movies/by-{sortProperty=title}")
public class MoviesActionBean extends MovieTrackerActionBean {

	// properties
	private String sortProperty;
	private Movies movies;

	/**
	 * Returns a list of movies.
	 *
	 * @return Movies
	 */
	public Movies getMovies() {
		if(movies == null)
			movies = getTrackerFacade().findActiveMovies(getTrackerUser(), sortProperty);

		return movies;
	}

	// getter and setters
	public String getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}

}