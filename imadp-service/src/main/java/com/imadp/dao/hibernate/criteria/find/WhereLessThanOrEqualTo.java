package com.imadp.dao.hibernate.criteria.find;

import org.hibernate.criterion.Restrictions;

import com.imadp.core.Property;
import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.AbstractCriterion;


/**
 * WhereGreaterThan
 * 
 * A criterion that restricts on less than or equal to a Property value.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
final class WhereLessThanOrEqualTo<T extends Persistable, V extends Number> 
		extends AbstractCriterion<T, FindCriteriaExecutor<T>> {	
	
	// properties
	private Property<? super T, V> property;
	private V value;

	// constructor
	WhereLessThanOrEqualTo(Property<? super T, V> property, V value) {
		this.property = property;
		this.value = value;
	}
	
	@Override
	public void applyCriterion(FindCriteriaExecutor<T> findCriteriaExecutor) {
		findCriteriaExecutor.getCriteria().add(Restrictions.le(property.getName(), value));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((property == null) ? 0 : property.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		WhereLessThanOrEqualTo<?, ?> other = (WhereLessThanOrEqualTo<?, ?>) obj;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder()
		.append("WhereLessThanOrEqualTo(")
		.append(property)
		.append("<=")
		.append(value)
		.append(')')
		.toString();
	}

}
