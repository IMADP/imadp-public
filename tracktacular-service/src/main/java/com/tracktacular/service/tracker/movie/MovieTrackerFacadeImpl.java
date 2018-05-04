package com.tracktacular.service.tracker.movie;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.joda.time.Interval;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.AbstractTrackerFacade;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TrackerDemo;
import com.tracktacular.service.tracker.calendar.CalendarEntry;


/**
 * MovieTrackerFacadeImpl
 *
 * The standard implementation of the MovieTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class MovieTrackerFacadeImpl extends AbstractTrackerFacade
	implements MovieTrackerFacade {

	private MovieService movieService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(movieService);
	}

	@Override
	public Movie getMovie(User user, String uid) {
		return movieService.findFirstByUser(user, uid);
	}

	@Override
	public void saveMovie(Movie movie) {
		new MovieValidator("movie", movie, findActiveMovies(movie.getUser(), Movie.TITLE.getName())).validate();
		movieService.save(movie);
	}

	@Override
	public void deleteMovie(Movie movie) {
		movieService.delete(movie);
	}

	@Override
	public Movies findActiveMovies(User user, String sortProperty) {
		List<Movie> activeMovies = movieService.findByUser(
				user, PersistableState.ACTIVE, CriteriaParams.<Movie>of(Results.ALL));

		return new Movies(activeMovies, sortProperty);
	}

	@Override
	public List<Movie> findDeletedMovies(User user, CriteriaParams<Movie> criteriaParams) {
		return movieService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedMovieCount(User user) {
		return movieService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<Movie> movies = movieService.findByUser(user, CriteriaParams.<Movie>of(Results.ALL));

		for(Movie movie : movies)
			deleteMovie(movie);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new MovieTrackerDemo(this);
	}

	@Override
	public MovieTrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		Movies movies = findActiveMovies(user, Movie.TITLE.getName());
		return new MovieTrackerReport(preferences.getTrackers().getMovieTrackerPreferences(), movies);
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();

		// active movies
		Movies movies = findActiveMovies(user, Movie.TITLE.getName());

		for(MovieCategory category : movies.getMovieCategories())
			for(Movie movie : category.getItems())
			{
				if(movie.getTargetDate() != null)
				{
					CalendarEntry calendarEntry = new CalendarEntry(user, movie.getTargetDate());
					calendarEntry.setTracker(Tracker.MOVIE);
					calendarEntry.setName(String.format("Movie: %s", movie.getTitle()));
					calendarEntries.add(calendarEntry);
				}
			}

		return calendarEntries;
	}

	// getters and setters
	public MovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}

}