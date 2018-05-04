package com.imadp.dao.hibernate.criteria.find;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.Results;

/**
 * FindCriteriaExecutor
 *
 * The object designed to execute a find query after all criterion have been applied.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public class FindCriteriaExecutor<T extends Persistable> {
	private Session session;
	private DetachedCriteria criteria;
	private Results results;

	// constructor
	public FindCriteriaExecutor(String entityName, Session session) {
		this.criteria = DetachedCriteria.forEntityName(entityName);
		this.session = session;
	}

	/**
	 * Executes the find query.
	 *
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> find() {
		Criteria executableCriteria = criteria.getExecutableCriteria(session);

		if (results.getFirstResult() >= 0)
			executableCriteria.setFirstResult((int) results.getFirstResult());

		if (results.getMaxResults() > 0)
			executableCriteria.setMaxResults((int) results.getMaxResults());

		return executableCriteria.list();
	}

	/**
	 * Executes the find count query.
	 *
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public long findCount() {
		criteria.setProjection(Projections.rowCount());
		Criteria executableCriteria = criteria.getExecutableCriteria(session);
		List<Long> results = executableCriteria.list();
		return results.isEmpty() ? 0 : results.get(0);
	}

	// getters and setters
	public DetachedCriteria getCriteria() {
		return criteria;
	}

	public void setResults(Results results) {
		this.results = results;
	}

}
