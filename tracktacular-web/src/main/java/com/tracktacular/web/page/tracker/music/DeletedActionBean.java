package com.tracktacular.web.page.tracker.music;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.music.Album;


/**
 * DeletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-music/deleted/page-{itemsPageProvider.pageNumber=1}")
public class DeletedActionBean extends MusicTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDeletedAlbumCount(user);
	}

	@Override
	protected List<Album> findItems(Results results, User user) {
		CriteriaParams<Album> params = CriteriaParams.<Album>of(results, Order.<Album>desc(Album.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findDeletedAlbums(user, params);
	}

}