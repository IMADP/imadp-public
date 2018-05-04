package com.imadp.dao.criteria;

import java.io.Serializable;

import com.imadp.core.Property;


/**
 * Order
 *
 * Encapsulates the parameters used to determine the order of returned results.
 *
 * @note This class is immutable and thread-safe.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public final class Order<T> implements Serializable {

	/**
	 * Factory method for creating new ascending Orders.
	 *
	 * @param <T>
	 * @param property
	 * @return FindCriteria<T>
	 */
	public static <T> Order<T> asc(Property<? super T, ?> property) {
		return new Order<>(property, false);
	}

	/**
	 * Factory method for creating new descending Orders.
	 *
	 * @param <T>
	 * @param property
	 * @return FindCriteria<T>
	 */
	public static <T> Order<T> desc(Property<? super T, ?> property) {
		return new Order<>(property, true);
	}

	// properties
	private final Property<? super T, ?> property;
	private final boolean descending;

	// constructor
	private Order(Property<? super T, ?> property, boolean descending) {
		this.property = property;
		this.descending = descending;
	}

	/**
	 * Returns the property name.
	 *
	 * @return String
	 */
	public String getPropertyName() {
		return property.getName();
	}

	// getters and setters
	public Property<? super T, ?> getProperty() {
		return property;
	}

	public boolean isDescending() {
		return descending;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (descending ? 1231 : 1237);
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
		Order<?> other = (Order<?>) obj;
		if (descending != other.descending)
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
		.append(property)
		.append(',')
		.append(descending ? "desc" : "asc")
		.toString();
	}

}
