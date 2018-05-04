package com.imadp.service.report;

import java.util.List;

import org.joda.time.DateTime;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.PersistableUserService;

/**
 * IReportService
 *
 * Provides common retrieval operations for Report objects.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public interface ReportService<T extends Report> extends PersistableUserService<T> {

	/**
	 * Finds reports by title and criteriaParams.
	 *
	 * @param title
	 * @param criteriaParams
	 * @return List<T>
	 */
	public List<T> findBy(String title, CriteriaParams<T> criteriaParams);

	/**
	 * Finds the count of reports by title.
	 *
	 * @param title
	 * @return long
	 */
	public long findCountBy(String title);

	/**
	 * Finds a list of reports within an inclusive date range and criteriaParams.
	 *
	 * @param startDate
	 * @param endDate
	 * @param criteriaParams
	 * @return List<T>
	 */
	public List<T> findBy(DateTime startDate, DateTime endDate, CriteriaParams<T> criteriaParams);

	/**
	 * Finds the count of reports within an inclusive date range.
	 *
	 * @param startDate
	 * @param endDate
	 * @return long
	 */
	public long findCountBy(DateTime startDate, DateTime endDate);

}