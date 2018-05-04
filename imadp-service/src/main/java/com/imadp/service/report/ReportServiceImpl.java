package com.imadp.service.report;

import java.util.List;

import org.joda.time.DateTime;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.user.PersistableUserServiceImpl;

/**
 * ReportServiceImpl
 *
 * The standard implementation of the ReportService.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public class ReportServiceImpl<T extends Report> extends PersistableUserServiceImpl<T> implements ReportService<T> {

	@Override
	public List<T> findBy(String title, CriteriaParams<T> criteriaParams) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(criteriaParams)
			.whereEqualTo(Report.TITLE, title).build();

		return findBy(findCriteria);
	}

	@Override
	public long findCountBy(String title) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(CriteriaParams.<T>of(Results.ALL))
			.whereEqualTo(Report.TITLE, title).build();

		return findCountBy(findCriteria);
	}

	@Override
	public List<T> findBy(DateTime startDate, DateTime endDate, CriteriaParams<T> criteriaParams) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(criteriaParams)
			.whereAfterOrOnDate(Report.DATE, startDate)
			.whereBeforeOrOnDate(Report.DATE, endDate).build();

		return findBy(findCriteria);
	}

	@Override
	public long findCountBy(DateTime startDate, DateTime endDate) {
		FindCriteria<T> findCriteria = findCriteriaBuilder(CriteriaParams.<T>of(Results.ALL))
			.whereAfterOrOnDate(Report.DATE, startDate)
			.whereBeforeOrOnDate(Report.DATE, endDate).build();

		return findCountBy(findCriteria);
	}

}