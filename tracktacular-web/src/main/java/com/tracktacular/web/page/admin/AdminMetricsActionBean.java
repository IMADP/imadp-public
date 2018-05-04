package com.tracktacular.web.page.admin;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.service.metrics.MetricsSummaries;


/**
 * AdminMetricsActionBean
 *
 * The ActionBean for the admin page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/admin/metrics")
public class AdminMetricsActionBean extends AbstractAdminActionBean {

	// views
	private static final String ADMIN_METRICS_PAGE_VIEW = "/WEB-INF/jsp/page.admin/adminMetricsPage.jsp";

	// properties
	private MetricsSummaries metricsSummaries;

	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution(ADMIN_METRICS_PAGE_VIEW);
	}

	// getters and setters
	public MetricsSummaries getMetricsSummaries() {
		if(metricsSummaries == null)
			metricsSummaries = adminFacade.getRecentMetricsSummary();

		return metricsSummaries;
	}

}