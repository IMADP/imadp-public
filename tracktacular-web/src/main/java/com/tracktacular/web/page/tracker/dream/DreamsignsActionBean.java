package com.tracktacular.web.page.tracker.dream;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.tag.TagCloud;
import com.imadp.service.user.User;
import com.imadp.web.stripes.link.Link;
import com.tracktacular.service.tracker.dream.Dreamsign;


/**
 * DreamsignsActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-dream/dreamsigns/{dreamsign=all}/page-{itemsPageProvider.pageNumber=1}")
public class DreamsignsActionBean extends DreamTrackerActionBean {

	// default dreamsign param
	private static final String DEFAULT_DREAMSIGN_PARAM = "all";

	// properties
	private String dreamsign;

	/**
	 * Returns the dreamsign tag cloud for a user.
	 *
	 * @return TagCloud
	 */
	public TagCloud getTagCloud() {
		return getTrackerFacade().findDreamsignTagCloud(getTrackerUser(),
				TAG_CLOUD_COUNT, TAG_CLOUD_MIN_WEIGHT, TAG_CLOUD_MAX_WEIGHT);
	}

	/**
	 * Returns true if a dreamsign is selected, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isDreamsignSelected() {
		return dreamsign != null;
	}

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDreamsignCount(user, dreamsign);
	}

	@Override
	protected List<Dreamsign> findItems(Results results, User user) {
		CriteriaParams<Dreamsign> params = CriteriaParams.<Dreamsign>of(
				results, Order.<Dreamsign>desc(Dreamsign.PERSISTABLE_STATE_DATE));

		return getTrackerFacade().findDreamsigns(user, dreamsign, params);
	}

	@Override
	protected void addLinkParameters(Link link) {
		link.addParameter("dreamsign", dreamsign);
	}

	// getter and setters
	public String getDreamsign() {
		return dreamsign;
	}

	public void setDreamsign(String dreamsign) {
		if(DEFAULT_DREAMSIGN_PARAM.equalsIgnoreCase(dreamsign))
			return;

		this.dreamsign = dreamsign;
	}

}