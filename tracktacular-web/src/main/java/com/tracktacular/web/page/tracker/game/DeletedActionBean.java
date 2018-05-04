package com.tracktacular.web.page.tracker.game;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.game.Game;


/**
 * DeletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-game/deleted/page-{itemsPageProvider.pageNumber=1}")
public class DeletedActionBean extends GameTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDeletedGameCount(user);
	}

	@Override
	protected List<Game> findItems(Results results, User user) {
		CriteriaParams<Game> params = CriteriaParams.<Game>of(results, Order.<Game>desc(Game.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findDeletedGames(user, params);
	}

}