package com.tracktacular.service.admin.report;


/**
 * StatusReportService
 *
 * Provides a way of generating status reports.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface StatusReportService {

	/**
	 * Generates a status report.
	 *
	 * @return StatusReport
	 */
	public StatusReport generateStatusReport();

}
