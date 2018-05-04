package com.imadp.dao.criteria.search;

import java.util.List;

import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.AbstractCriteria;
import com.imadp.dao.criteria.Criterion;

/**
 * SearchCriteria
 *
 * Holds the criteria used to search for the results or row count of a dynamic query.
 *
 * @note This class is immutable and thread-safe.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public final class SearchCriteria<T extends Persistable> extends AbstractCriteria<T> {

	// constructor
	public SearchCriteria(List<Criterion<T, ?>> criteria) {
		super(criteria);
	}

}