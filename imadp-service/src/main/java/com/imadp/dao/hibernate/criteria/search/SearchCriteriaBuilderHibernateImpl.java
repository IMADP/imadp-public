package com.imadp.dao.hibernate.criteria.search;

import java.util.List;

import com.imadp.core.Property;
import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.AbstractCriteriaBuilder;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.search.SearchCriteria;
import com.imadp.dao.criteria.search.SearchCriteriaBuilder;

/**
 * SearchCriteriaBuilderHibernateImpl
 *
 * A factory class for creating SearchCriteria instances.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public class SearchCriteriaBuilderHibernateImpl<T extends Persistable>
	extends AbstractCriteriaBuilder<T> implements SearchCriteriaBuilder<T> {

	// constructor
	public SearchCriteriaBuilderHibernateImpl(String query, List<Property<T, String>> properties, Results results) {
		add(new ForResults<T>(results));
		add(new ForProperties<>(properties));
		add(new ForQuery<T>(query));
	}

	@Override
	public SearchCriteria<T> build() {
		return new SearchCriteria<>(criteria);
	}

}