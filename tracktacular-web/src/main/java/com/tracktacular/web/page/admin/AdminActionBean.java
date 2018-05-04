package com.tracktacular.web.page.admin;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;


/**
 * AdminActionBean
 *
 * The ActionBean for the admin page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/admin")
public class AdminActionBean extends AbstractAdminActionBean {

	// views
	private static final String ADMIN_PAGE_VIEW = "/WEB-INF/jsp/page.admin/adminPage.jsp";

	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution(ADMIN_PAGE_VIEW);
	}

	@Override
	public boolean isMobile() {
		return false;
	}

	/**
	 * Inserts all demo data.
	 *
	 * @return Resolution
	 */
	public Resolution clearAllCaches() {
		adminFacade.clearAllCaches();
		getContext().addSuccessMessage("admin.clearAllCaches.success");
		return new RedirectResolution(AdminActionBean.class);
	}

	/**
	 * Inserts all demo data.
	 *
	 * @return Resolution
	 */
	public Resolution generateAndSendStatusReport() {
		adminFacade.generateAndSendStatusReport();
		getContext().addSuccessMessage("admin.generateAndSendStatusReport.success");
		return new RedirectResolution(AdminActionBean.class);
	}

	/**
	 * Inserts all demo data.
	 *
	 * @return Resolution
	 */
	public Resolution insertTrackerDemoData() {
		adminFacade.insertTrackerDemoData();
		getContext().addSuccessMessage("admin.insertTrackerDemoData.success");
		return new RedirectResolution(AdminActionBean.class);
	}

}