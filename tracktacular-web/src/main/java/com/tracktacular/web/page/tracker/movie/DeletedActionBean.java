package com.tracktacular.web.page.tracker.movie;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.movie.Movie;


/**
 * DeletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-movie/deleted/page-{itemsPageProvider.pageNumber=1}")
public class DeletedActionBean extends MovieTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDeletedMovieCount(user);
	}

	@Override
	protected List<Movie> findItems(Results results, User user) {
		CriteriaParams<Movie> params = CriteriaParams.<Movie>of(results, Order.<Movie>desc(Movie.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findDeletedMovies(user, params);
	}

}