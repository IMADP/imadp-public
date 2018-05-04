	package com.tracktacular.web.page.tracker.movie;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.core.Property;
import com.imadp.core.validation.ValidationResult;
import com.imadp.service.user.AbstractPersistableUser;
import com.tracktacular.service.tracker.movie.Movie;
import com.tracktacular.service.tracker.movie.MovieValidator;
import com.tracktacular.service.tracker.movie.Movies;
import com.tracktacular.web.TracktacularActionBean;


/**
 * ImportMoviesActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-movie/import-movies")
public class ImportMoviesActionBean extends MovieTrackerActionBean {

	@Override
	public String getTrackerPageTitle() {
		return "Import Movies";
	}

	@Override
	public Resolution index() {
		if(isPublicMode())
			return new RedirectResolution(getDefaultActionBean());

		return super.index();
	}

	@Override
	protected Class<? extends TracktacularActionBean> getUserToggleClass() {
		return getDefaultActionBean();
	}

	@Override
	public Class<? extends AbstractPersistableUser> getImportClass() {
		return Movie.class;
	}

	@Override
	public Property<?,?>[] getImportProperties() {
		return new Property[] {Movie.TITLE, Movie.TAG, Movie.RATING, Movie.NOTES};
	}

	@Override
	protected ValidationResult getImportValidationResult(AbstractPersistableUser item) {
		Movies movies = getTrackerFacade().findActiveMovies(getTrackerUser(), Movie.TITLE.getName());
		return new MovieValidator("movie", (Movie) item, movies).getValidationResult();
	}

}