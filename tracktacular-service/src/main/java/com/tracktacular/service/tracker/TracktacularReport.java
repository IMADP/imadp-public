package com.tracktacular.service.tracker;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import com.google.common.collect.Lists;
import com.imadp.service.report.Report;


/**
 * TracktacularReport
 *
 * A summary report for all trackers.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TracktacularReport extends Report {
	private List<TrackerReport> trackerReports;
	private String username;
	private String trackerAlertsBreakdown;
	private int alertCount;
	private int trackersWithAlerts;

	// constructor
	public TracktacularReport(String username, List<TrackerReport> trackerReports) {
		setDate(new DateTime());
		setUsername(username);
		setTrackerReports(trackerReports);

		List<String> trackerReportBreakdown = Lists.newArrayList();

		for(TrackerReport trackerReport : trackerReports)
		{
			if(trackerReport.isEnabled())
			{
				int trackerReportAlertCount = trackerReport.getAlertCount();

				if(trackerReportAlertCount > 0)
				{
					alertCount += trackerReportAlertCount;
					trackersWithAlerts += 1;
					trackerReportBreakdown.add(trackerReport.getTracker().getCapitalizedName() + "["+trackerReportAlertCount+"]");
				}
			}
		}

		trackerAlertsBreakdown = StringUtils.join(trackerReportBreakdown, ", ");
	}

	/**
	 * Returns true if there are trackers present in the report, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isTrackersFound() {
		return !trackerReports.isEmpty();
	}

	/**
	 * Returns true if any alerts are found, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isAlerts() {
		return getAlertCount() > 0;
	}

	/**
	 * Returns the number of alerts for all tracker reports.
	 *
	 * @return int
	 */
	public int getAlertCount() {
		return this.alertCount;
	}

	/**
	 * Returns the number of trackers with alerts for all tracker reports.
	 *
	 * @return int
	 */
	public int getTrackersWithAlerts() {
		return this.trackersWithAlerts;
	}

	// getters and setters
	public List<TrackerReport> getTrackerReports() {
		return trackerReports;
	}

	public void setTrackerReports(List<TrackerReport> trackerReports) {
		this.trackerReports = trackerReports;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTrackerAlertsBreakdown() {
		return trackerAlertsBreakdown;
	}

}