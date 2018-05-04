package com.imadp.dao.criteria.find;

import java.util.List;

import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.AbstractCriteria;
import com.imadp.dao.criteria.Criterion;

/**
 * FindCriteria
 *
 * Holds the criteria used to find the results or row count of a dynamic query.
 *
 * @note This class is immutable and thread-safe.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public final class FindCriteria<T extends Persistable> extends AbstractCriteria<T> {

	// constructor
	public FindCriteria(List<Criterion<T, ?>> criteria) {
		super(criteria);
	}

}