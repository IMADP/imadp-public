package com.imadp.dao.criteria.search;

import com.imadp.dao.Persistable;

/**
 * ISearchCriteriaBuilder
 *
 * Provides dynamic SearchCriteria instances.
 *
 * @note Implementations of this class are mutable by necessity, therefore instances
 * 		 should not be shared among multiple threads, or used as cache keys.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public interface SearchCriteriaBuilder<T extends Persistable> {

	/**
	 * Builds and returns a SearchCriteria based on the supplied criteria.
	 *
	 * @return SearchCriteria<T>
	 */
	public SearchCriteria<T> build();

}