package com.tracktacular.web.page.tracker.dream;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.imadp.web.stripes.link.Link;
import com.tracktacular.service.tracker.dream.Dream;


/**
 * DreamsActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-dream/dreams/{dreamType=all}/sort-{sort=desc}/page-{itemsPageProvider.pageNumber=1}")
public class DreamsActionBean extends DreamTrackerActionBean {
	private String sort;
	private String dreamType;

	/**
	 * Returns the count of all dreams.
	 *
	 * @return long
	 */
	public long getDreamCount() {
		return getTrackerFacade().findDreamCount(getTrackerUser());
	}

	/**
	 * Returns the count of all lucid dreams.
	 *
	 * @return long
	 */
	public long getLucidDreamCount() {
		return getTrackerFacade().findLucidDreamCount(getTrackerUser());
	}

	/**
	 * Returns the count of all favorite dreams.
	 *
	 * @return long
	 */
	public long getFavoriteDreamCount() {
		return getTrackerFacade().findFavoriteDreamCount(getTrackerUser());
	}

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		if("lucid".equalsIgnoreCase(dreamType))
			return getTrackerFacade().findLucidDreamCount(user);

		if("favorite".equalsIgnoreCase(dreamType))
			return getTrackerFacade().findFavoriteDreamCount(user);

		return getTrackerFacade().findDreamCount(user);
	}

	@Override
	protected List<Dream> findItems(Results results, User user) {
		Order<Dream> order = "desc".equalsIgnoreCase(getSort()) ? Order.desc(Dream.DATE) : Order.asc(Dream.DATE);

		if("lucid".equalsIgnoreCase(dreamType))
			return getTrackerFacade().findLucidDreams(user, CriteriaParams.<Dream>of(results, order));

		if("favorite".equalsIgnoreCase(dreamType))
			return getTrackerFacade().findFavoriteDreams(user, CriteriaParams.<Dream>of(results, order));

		return getTrackerFacade().findDreams(user, CriteriaParams.<Dream>of(results, order));
	}

	@Override
	protected void addLinkParameters(Link link) {
		link.addParameter("dreamType", getDreamType())
			.addParameter("sort", getSort());
	}

	// getter and setters
	public String getDreamType() {
		return dreamType;
	}

	public void setDreamType(String dreamType) {
		this.dreamType = dreamType;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}