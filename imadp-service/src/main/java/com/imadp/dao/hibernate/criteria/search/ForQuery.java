package com.imadp.dao.hibernate.criteria.search;

import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.AbstractCriterion;


/**
 * ForQuery
 * 
 * A criterion that specifies the query to search for.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
final class ForQuery<T extends Persistable> 
		extends AbstractCriterion<T, SearchCriteriaExecutor<T>> {	
	
	// properties
	private String query;

	// constructor
	ForQuery(String query) {
		this.query = query;
	}
	
	@Override
	public void applyCriterion(SearchCriteriaExecutor<T> searchCriteriaExecutor) {
		searchCriteriaExecutor.setQuery(query);
	}
			
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((query == null) ? 0 : query.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ForQuery<?> other = (ForQuery<?>) obj;
		if (query == null) {
			if (other.query != null)
				return false;
		} else if (!query.equals(other.query))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder()
		.append("ForQuery(")
		.append(query)
		.append(')')
		.toString();
	}
	
}
