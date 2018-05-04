package com.imadp.dao.hibernate.criteria.find;

import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.AbstractCriterion;
import com.imadp.dao.criteria.Results;


/**
 * ForResults
 * 
 * A criterion that specifies the result set to return.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
final class ForResults<T extends Persistable> 
		extends AbstractCriterion<T, FindCriteriaExecutor<T>> {	
	
	// properties
	private Results results;

	// constructor
	ForResults(Results results) {
		this.results = results;
	}
	
	@Override
	public void applyCriterion(FindCriteriaExecutor<T> findCriteriaExecutor) {
		findCriteriaExecutor.setResults(results);
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((results == null) ? 0 : results.hashCode());
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
	ForResults<?> other = (ForResults<?>) obj;
		if (results == null) {
			if (other.results != null)
				return false;
		} else if (!results.equals(other.results))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder()
		.append("ForResults(")
		.append(results)
		.append(')')
		.toString();
	}
	
}
