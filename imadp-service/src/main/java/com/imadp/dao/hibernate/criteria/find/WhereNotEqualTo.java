package com.imadp.dao.hibernate.criteria.find;

import org.hibernate.criterion.Restrictions;

import com.imadp.core.Property;
import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.AbstractCriterion;


/**
 * WhereNotEqualTo
 * 
 * A criterion that restricts on a not equal Property value.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
final class WhereNotEqualTo<T extends Persistable, V> 
		extends AbstractCriterion<T, FindCriteriaExecutor<T>> {	
	
	// properties
	private Property<? super T, V> property;
	private V value;

	// constructor
	WhereNotEqualTo(Property<? super T, V> property, V value) {
		this.property = property;
		this.value = value;
	}
	
	@Override
	public void applyCriterion(FindCriteriaExecutor<T> findCriteriaExecutor) {
		if(value != null)
			findCriteriaExecutor.getCriteria().add(Restrictions.ne(property.getName(), value));
		else
			findCriteriaExecutor.getCriteria().add(Restrictions.isNotNull(property.getName()));			
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
		WhereNotEqualTo<?, ?> other = (WhereNotEqualTo<?, ?>) obj;
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
		.append("WhereNotEqualTo(")
		.append(property)
		.append("!='")
		.append(value instanceof Persistable ? ((Persistable)value).getId() : value )
		.append("')")
		.toString();
	}

}
