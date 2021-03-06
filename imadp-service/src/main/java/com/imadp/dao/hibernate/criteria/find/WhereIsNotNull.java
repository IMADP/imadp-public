package com.imadp.dao.hibernate.criteria.find;

import org.hibernate.criterion.Restrictions;

import com.imadp.core.Property;
import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.AbstractCriterion;


/**
 * WhereIsNotNull
 * 
 * A criterion that restricts on a non null Property value.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
final class WhereIsNotNull<T extends Persistable> 
		extends AbstractCriterion<T, FindCriteriaExecutor<T>> {	
	
	// properties
	private Property<? super T, ?> property;

	// constructor
	WhereIsNotNull(Property<? super T, ?> property) {
		this.property = property;
	}
	
	@Override
	public void applyCriterion(FindCriteriaExecutor<T> findCriteriaExecutor) {
		findCriteriaExecutor.getCriteria().add(Restrictions.isNotNull(property.getName()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((property == null) ? 0 : property.hashCode());
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
		WhereIsNotNull<?> other = (WhereIsNotNull<?>) obj;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder()
		.append("WhereIsNotNull(")
		.append(property)
		.append("')")
		.toString();
	}

}