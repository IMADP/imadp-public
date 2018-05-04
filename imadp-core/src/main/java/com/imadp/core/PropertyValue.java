package com.imadp.core;

import java.io.Serializable;



/**
 * PropertyValue
 *
 * A type-safe class representing a property and value pair.
 *
 * @note This class is immutable and thread-safe.
 *
 * @param <T>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public final class PropertyValue<T, V> implements Serializable {
	private final Property<T, V> property;
	private final V value;

	// constructor
	PropertyValue(Property<T, V> property, V value) {
		this.property = property;
		this.value = value;
	}

	// getters and setters
	public Property<? extends T, V> getProperty() {
		return property;
	}

	public V getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((property == null) ? 0 : property.hashCode());
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
		PropertyValue<?, ?> other = (PropertyValue<?, ?>) obj;
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
		return property + "=" + value;
	}

}
