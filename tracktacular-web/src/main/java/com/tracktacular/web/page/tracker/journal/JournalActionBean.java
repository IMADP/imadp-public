package com.tracktacular.web.page.tracker.journal;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.imadp.web.stripes.link.Link;
import com.tracktacular.service.tracker.journal.Entry;
import com.tracktacular.web.TracktacularActionBean;
import com.tracktacular.web.page.PageNotFoundActionBean;


/**
 * JournalActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-journal/journal-{journal=0}/{nameSlug=unknown}/sort-{sort=desc}/page-{itemsPageProvider.pageNumber=1}")
public class JournalActionBean extends JournalTrackerActionBean {
	private String sort;

	@Override
	public Resolution index() {
		if(getJournal() == null)
			return new ForwardResolution(PageNotFoundActionBean.class);

		return super.index();
	}

	@Override
	public String getTrackerPageTitle() {
		return getJournal().getName();
	}

	@Override
	protected Class<? extends TracktacularActionBean> getUserToggleClass() {
		return getDefaultActionBean();
	}

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findEntriesCount(user, getJournal());
	}

	@Override
	protected List<Entry> findItems(Results results, User user) {
		Order<Entry> order = "desc".equalsIgnoreCase(getSort()) ? Order.desc(Entry.DATE) : Order.asc(Entry.DATE);
		return getTrackerFacade().findEntries(user, getJournal(), CriteriaParams.<Entry>of(results, order));
	}

	@Override
	protected void addLinkParameters(Link link) {
		link.addParameter("nameSlug", getJournal().getNameSlug())
			.addParameter("sort", getSort())
			.addParameter("journal", getJournal().getUid());
	}

	// getters and setters
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}