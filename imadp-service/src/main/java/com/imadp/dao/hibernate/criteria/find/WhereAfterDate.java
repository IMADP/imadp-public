package com.imadp.dao.hibernate.criteria.find;

import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.AbstractCriterion;


/**
 * WhereAfterDate
 * 
 * A criterion that restricts on after a Property date.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
final class WhereAfterDate<T extends Persistable> 
		extends AbstractCriterion<T, FindCriteriaExecutor<T>> {	
	
	// properties
	private Property<? super T, DateTime> property;
	private DateTime date;

	// constructor
	WhereAfterDate(Property<? super T, DateTime> property, DateTime date) {
		this.property = property;
		this.date = date;
	}
	
	@Override
	public void applyCriterion(FindCriteriaExecutor<T> findCriteriaExecutor) {
		findCriteriaExecutor.getCriteria().add(Restrictions.gt(property.getName(), date));
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((property == null) ? 0 : property.hashCode());
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
		WhereAfterDate<?> other = (WhereAfterDate<?>) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
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
		.append("WhereAfterDate(")
		.append(property)
		.append('>')
		.append(date)
		.append(')')
		.toString();
	}

}
