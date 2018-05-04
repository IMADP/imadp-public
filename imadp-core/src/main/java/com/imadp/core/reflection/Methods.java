package com.imadp.core.reflection;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.beans.BeanUtils;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Methods
 *
 * Provides method level reflection using bytecode generation for enhanced performance.
 *
 * @author ADP
 * @version 1.0
 */
public final class Methods {

	// methods cache
	private static final LoadingCache<Class<?>, Methods> METHODS = CacheBuilder.newBuilder().build(
			new CacheLoader<Class<?>, Methods>() {
				@Override
				public Methods load(Class<?> objectClass) throws Exception {
					return new Methods(objectClass);
				}
			});

	/**
	 * Invokes a public method for a given object and arguments.
	 *
	 * @param object
	 * @param method
	 * @param args
	 * @return T
	 */
	public static <T> T invokeMethod(Object object, String method, Object... args) {
		return METHODS.getUnchecked(object.getClass()).invoke(object, method, args);
	}

	/**
	 * Gets a value from an object with the corresponding public getter of given property name.
	 * Nested property names can be passed in, if a container object is null then the value returned will be null.
	 *
	 * @param object
	 * @param property
	 * @return T
	 */
	public static <T> T getProperty(Object object, String property) {
		String[] properties = property.split("\\.");
		return getProperty(object, properties);
	}

	/**
	 * Gets a property from an object, handling nested properties along the way.
	 *
	 * @param object
	 * @param properties
	 * @return T
	 */
	private static <T> T getProperty(Object object, String[] properties) {

		// if a nested object is null, return null
		if(object == null)
			return null;

		// if more than one property name is found, recursively retrieve nested properties
		if(properties.length > 1)
		{
			Methods methods = METHODS.getUnchecked(object.getClass());
			Object nestedObject = methods.get(object, properties[0]);
			properties = Arrays.copyOfRange(properties, 1, properties.length);
			return getProperty(nestedObject, properties);
		}

		// return the property for the remaining property name
		Methods methods = METHODS.getUnchecked(object.getClass());
		return methods.get(object, properties[0]);
	}

	/**
	 * Sets a value into an object by the corresponding public setter with the given property name.
	 * Nested property names can be passed in, if a container object is null it will be instantiated with the default constructor.
	 *
	 * @param object
	 * @param property
	 * @param value
	 */
	public static void setProperty(Object object, String property, Object value) {
		String[] properties = property.split("\\.");
		setProperty(object, properties, value);
	}

	/**
	 * Sets a value into an object, instantiating nested properties along the way.
	 *
	 * @param object
	 * @param properties
	 * @param value
	 */
	private static void setProperty(Object object, String[] properties, Object value) {

		// if more than one property name is found, recursively set nested properties
		if(properties.length > 1)
		{
			Class<?> objectClass = object.getClass();
			Methods methods = METHODS.getUnchecked(objectClass);
			Object nestedObject = methods.get(object, properties[0]);

			// if the nested object was null, instantiate it and set it into the parent object
			if(nestedObject == null)
			{
				PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(objectClass, properties[0]);
				nestedObject = Constructors.newInstance(propertyDescriptor.getPropertyType());
				methods.set(object, properties[0], nestedObject);
			}

			// recursively call this method to work with the nested object
			properties = Arrays.copyOfRange(properties, 1, properties.length);
			setProperty(nestedObject, properties, value);
			return;
		}

		// return the property for the remaining property name
		Methods methods = METHODS.getUnchecked(object.getClass());
		methods.set(object, properties[0], value);
	}

	// properties
	private	MethodAccess methodAccess;
	private	Class<?> objectClass;

	// method index cache
	private LoadingCache<String, Integer> methodIndexes = CacheBuilder.newBuilder().build(
			new CacheLoader<String, Integer>() {
				@Override
				public Integer load(String field) throws Exception {
					return methodAccess.getIndex(field);
				}
			});

	// get method index cache
	private LoadingCache<String, Integer> getMethodIndexes = CacheBuilder.newBuilder().build(
			new CacheLoader<String, Integer>() {
				@Override
				public Integer load(String property) throws Exception {
					PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(objectClass, property);

					if(propertyDescriptor == null)
						throw new IllegalArgumentException("No property descriptor on object ["+objectClass.getName()+"] for property ["+property+"]");

					Method method = propertyDescriptor.getReadMethod();

					if(method == null)
						throw new IllegalArgumentException("No getter on object ["+objectClass.getName()+"] for property ["+property+"]");

					String getMethod = method.getName();
					return methodAccess.getIndex(getMethod);
				}
			});

	// set method index cache
	private LoadingCache<String, Integer> setMethodIndexes = CacheBuilder.newBuilder().build(
			new CacheLoader<String, Integer>() {
				@Override
				public Integer load(String property) throws Exception {
					PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(objectClass, property);

					if(propertyDescriptor == null)
						throw new IllegalArgumentException("No property descriptor on object ["+objectClass.getName()+"] for property ["+property+"]");

					Method method = propertyDescriptor.getWriteMethod();

					if(method == null)
						throw new IllegalArgumentException("No setter on object ["+objectClass.getName()+"] for property ["+property+"]");

					String setMethod = method.getName();
					return methodAccess.getIndex(setMethod);
				}
			});

	// constructor
	private Methods(Class<?> objectClass) {
		this.objectClass = objectClass;
		this.methodAccess = MethodAccess.get(objectClass);
	}

	/**
	 * Gets the value of a method for a given object using method access.
	 *
	 * @param object
	 * @param method
	 * @param args
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	private <T> T invoke(Object object, String method, Object... args) {
		return (T) methodAccess.invoke(object, methodIndexes.getUnchecked(method), args);
	}

	/**
	 * Returns the value of a property for a given object using its getter method.
	 *
	 * @param object
	 * @param objectClass
	 * @param property
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	private <T> T get(Object object, String property) {
		return (T) methodAccess.invoke(object, getMethodIndexes.getUnchecked(property));
	}

	/**
	 * Sets the value of a property for a given object using its setter method.
	 *
	 * @param object
	 * @param objectClass
	 * @param property
	 */
	private void set(Object object, String property, Object value) {
		methodAccess.invoke(object, setMethodIndexes.getUnchecked(property), value);
	}

}
