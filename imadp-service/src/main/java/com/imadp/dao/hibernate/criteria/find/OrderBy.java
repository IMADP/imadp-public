package com.imadp.dao.hibernate.criteria.find;

import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.AbstractCriterion;
import com.imadp.dao.criteria.Order;


/**
 * OrderBy
 * 
 * A criterion that orders results.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
final class OrderBy<T extends Persistable> 
		extends AbstractCriterion<T, FindCriteriaExecutor<T>> {	
	
	// properties
	private Order<? super T> order;

	// constructor
	OrderBy(Order<? super T> order) {
		this.order = order;
	}
	
	@Override
	public void applyCriterion(FindCriteriaExecutor<T> findCriteriaExecutor) {
		findCriteriaExecutor.getCriteria().addOrder(order.isDescending() ? 
				org.hibernate.criterion.Order.desc(order.getPropertyName()) : 
					org.hibernate.criterion.Order.asc(order.getPropertyName()));
	}
			
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
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
		OrderBy<?> other = (OrderBy<?>) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder()
		.append("OrderBy(")
		.append(order)
		.append(')')
		.toString();
	}
}
