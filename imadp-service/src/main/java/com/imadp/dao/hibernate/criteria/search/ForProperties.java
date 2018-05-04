package com.imadp.dao.hibernate.criteria.search;

import java.util.List;

import com.imadp.core.Property;
import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.AbstractCriterion;


/**
 * ForProperties
 * 
 * A criterion that adds a Property to search on.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
final class ForProperties<T extends Persistable> 
		extends AbstractCriterion<T, SearchCriteriaExecutor<T>> {	
	
	// properties
	private List<Property<T, String>> properties;

	// constructor
	ForProperties(List<Property<T, String>> properties) {
		this.properties = properties;
	}
	
	@Override
	public void applyCriterion(SearchCriteriaExecutor<T> searchCriteriaExecutor) {
		searchCriteriaExecutor.setProperties(properties);
	}		
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((properties == null) ? 0 : properties.hashCode());
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
		ForProperties<?> other = (ForProperties<?>) obj;
		if (properties == null) {
			if (other.properties != null)
				return false;
		} else if (!properties.equals(other.properties))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder()
		.append("ForProperties(")
		.append(properties)
		.append(")")
		.toString();
	}

}
