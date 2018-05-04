package com.tracktacular.service.tracker.movie;

import java.util.List;

import com.google.common.collect.Lists;
import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * MovieTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class MovieTrackerReport extends AbstractTrackerReport {
	private final Movies movies;
	private final List<Movie> unratedMovies;
	private final List<Movie> targetDateMovies;

    // constructor
    public MovieTrackerReport(MovieTrackerPreferences preferences, Movies movies) {
    	this.movies = movies;
    	this.unratedMovies = movies.getUnratedMovies();
    	this.targetDateMovies = Lists.newArrayList();

    	if(preferences.isAlertOnTargetDates())
    	  	for(MovieCategory movieCategory : movies.getMovieCategories())
	    		for(Movie movie : movieCategory.getItems())
	    			if(isCurrentDate(movie.getTargetDate()))
	    				targetDateMovies.add(movie);
    }

    @Override
	public boolean isEnabled() {
    	return getMovieCount() > 0;
    }

	@Override
	public int getAlertCount() {
		return targetDateMovies.size();
	}

    /**
     * Returns the count of movies.
     *
     * @return int
     */
    public int getMovieCount() {
		return movies.getMovieCount();
	}

    /**
     * Returns the count of unrated movies.
    *
    * @return int
    */
    public int getUnratedMovieCount() {
		return unratedMovies.size();
	}

    // getters
	public List<Movie> getUnratedMovies() {
		return unratedMovies;
	}

	public List<Movie> getTargetDateMovies() {
		return targetDateMovies;
	}

}