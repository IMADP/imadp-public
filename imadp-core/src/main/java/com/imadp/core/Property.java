package com.imadp.core;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;



/**
 * Property
 *
 * A type-safe class representing a property.
 *
 * @note This class is immutable and thread-safe.
 *
 * @param <T>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public final class Property<T, V> implements Serializable {

	/**
	 * Factory method for creating new Property instances.
	 *
	 * @param <T> the type of the persistable object
	 * @param <V> the type of the property value
	 * @param name
	 * @return Property<T, V>
	 */
	public static <T, V> Property<T, V> of(String name) {
		return new Property<>(name);
	}

	/**
	 * Factory method for creating new component Property instances.
	 * A component property consists of the componentName joined by a period to the nested property name.
	 *
	 * @param <T> the type of the persistable object
	 * @param <V> the type of the property value
	 * @param componentName
	 * @param property
	 * @return Property<T, V>
	 */
	public static <T, V> Property<T, V> of(String componentName, Property<?, V> property) {
		return Property.of(componentName + "." + property.name);
	}

	// properties
	private final String name;

	/*
	 * Constructors are private to allow the potential caching of instantiated objects,
	 * and enhance code readability through the use of generic inference. Please use the
	 * factory methods to instantiate all objects.
	 *
	 */

	// constructor
	private Property(String name) {
		this.name = name;
	}

	/**
	 * Constructs a PropertyValue with the given value.
	 *
	 * @param value
	 * @return PropertyValue<T, V>
	 */
	public PropertyValue<T, V> withValue(V value) {
		return new PropertyValue<>(this, value);
	}

	// getters and setters
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Property<?, ?> other = (Property<?, ?>) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * Returns a List<Property<T, V>> from the static Properties of a given class.
	 *
	 * @param <T>
	 * @param entityClass
	 * @return List<Property<T, V>>
	 */
	public static <T> List<Property<T, ?>> getStaticProperties(Class<?> entityClass) {
		List<Property<T, ?>> properties = new ArrayList<>();
		getStaticClassProperties(properties, entityClass);
		getStaticInterfaceProperties(properties, entityClass);
		return properties;
	}

	/**
	 * Recursively adds the properties of a class to a List of Properties.
	 *
	 * @param <T>
	 * @param properties
	 * @param entityClass
	 */
	private static <T> void getStaticClassProperties(List<Property<T, ?>> properties, Class<?> entityClass) {
		if(entityClass == null)
			return;

		getStaticProperties(properties, entityClass);
		getStaticClassProperties(properties, entityClass.getSuperclass());
	}

	/**
	 * Adds the properties of a class interfaces to a List of Properties.
	 *
	 * @param <T>
	 * @param properties
	 * @param entityClass
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static <T> void getStaticInterfaceProperties(List<Property<T, ?>> properties, Class entityClass) {
		Class<T>[] classes = entityClass.getInterfaces();

		for(int i = 0; i < classes.length; i++)
			getStaticProperties(properties, classes[i]);
	}

	/**
	 * Adds the properties of a class to a List of Properties.
	 *
	 * @param <T>
	 * @param properties
	 * @param entityClass
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static <T> void getStaticProperties(List<Property<T, ?>> properties, Class entityClass) {

		for(Field field : entityClass.getDeclaredFields()) {
			field.setAccessible(true);

			try
			{
				Object object = field.get(null);

				if(object instanceof Property)
					properties.add((Property<T, ?>) object);
			}
			catch(Exception e)
			{

			}
		}

	}

}
