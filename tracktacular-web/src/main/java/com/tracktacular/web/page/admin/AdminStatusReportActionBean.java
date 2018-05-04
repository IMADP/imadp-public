package com.tracktacular.web.page.admin;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.service.admin.report.StatusReport;


/**
 * AdminStatusReportActionBean
 *
 * The ActionBean for the admin page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/admin/status-report")
public class AdminStatusReportActionBean extends AbstractAdminActionBean {

	// views
	private static final String ADMIN_STATUS_REPORT_PAGE_VIEW = "/WEB-INF/jsp/page.admin/adminStatusReportPage.jsp";

	// properties
	private StatusReport statusReport;

	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution(ADMIN_STATUS_REPORT_PAGE_VIEW);
	}

	/**
	 * Returns the current status report.
	 *
	 * @return StatusReport
	 */
	public StatusReport getStatusReport() {
		if(statusReport == null)
			statusReport = adminFacade.generateStatusReport();

		return statusReport;
	}

}