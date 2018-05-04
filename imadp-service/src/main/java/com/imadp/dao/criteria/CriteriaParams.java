package com.imadp.dao.criteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * CriteriaParams
 *
 * Encapsulates the dynamic parameters of a query.
 *
 * @note This class is immutable and thread-safe.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public final class CriteriaParams<T> implements Serializable {

	/**
	 * Factory method for creating new CriteriaParams instances.
	 *
	 * @param <T>
	 * @param results
	 * @return CriteriaParams<T>
	 */
	public static <T> CriteriaParams<T> of(Results results) {
		return new CriteriaParams<>(results);
	}

	/**
	 * Factory method for creating new CriteriaParams instances.
	 *
	 * @param <T>
	 * @param results
	 * @param order
	 * @return CriteriaParams<T>
	 */
	public static <T> CriteriaParams<T> of(Results results, Order<? super T> order) {
		return new CriteriaParams<>(results, order);
	}

	/**
	 * Factory method for creating new CriteriaParams instances.
	 *
	 * @param <T>
	 * @param results
	 * @param orderOne
	 * @param orderTwo
	 * @return CriteriaParams<T>
	 */
	public static <T> CriteriaParams<T> of(Results results, Order<? super T> orderOne, Order<? super T> orderTwo) {
		List<Order<? super T>> orders = new ArrayList<>();
		orders.add(orderOne);
		orders.add(orderTwo);
		return new CriteriaParams<>(results, orders);
	}

	/**
	 * Factory method for creating new CriteriaParams instances.
	 *
	 * @param <T>
	 * @param results
	 * @param orderList
	 * @return CriteriaParams<T>
	 */
	public static <T> CriteriaParams<T> of(Results results, List<Order<? super T>> orderList) {
		return new CriteriaParams<>(results, orderList);
	}

	// properties
	private final Results results;
	private final List<Order<? super T>> orders;

	// constructor
	private CriteriaParams(Results results) {
		this(results, Collections.<Order<? super T>> emptyList());
	}

	// constructor
	@SuppressWarnings("unchecked")
	private CriteriaParams(Results results, Order<? super T> order) {
		this(results, order == null ? Collections.EMPTY_LIST :
			Arrays.<Order<? super T>>asList(order));
	}

	// constructor
	private CriteriaParams(Results results, List<Order<? super T>> orders) {
		this.results = results;
		this.orders = Collections.unmodifiableList(orders);
	}

	/**
	 * Returns true if orders are present, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean hasOrders() {
		return orders != null && !orders.isEmpty();
	}

	// getters and setters
	public Results getResults() {
		return results;
	}

	public List<Order<? super T>> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
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
		CriteriaParams<?> other = (CriteriaParams<?>) obj;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
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
		.append("CriteriaParams[Results(")
		.append(results)
		.append(") OrderList[")
		.append(orders)
		.append("]")
		.toString();
	}

}