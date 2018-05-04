package com.imadp.core.reflection;

import com.esotericsoftware.reflectasm.FieldAccess;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Fields
 *
 * Provides field level reflection using bytecode generation for enhanced performance.
 *
 * @author ADP
 * @version 1.0
 */
public final class Fields {

	// fields cache
	private static final LoadingCache<Class<?>, Fields> FIELDS = CacheBuilder.newBuilder().build(
			new CacheLoader<Class<?>, Fields>() {
				@Override
				public Fields load(Class<?> objectClass) throws Exception {
					return new Fields(objectClass);
				}
			});

	/**
	 * Gets the value of a public field for a given object using field access.
	 *
	 * @param object
	 * @param field
	 * @return T
	 */
	public static <T> T getField(Object object, String field) {
		return FIELDS.getUnchecked(object.getClass()).get(object, field);
	}

	/**
	 * Sets the value of a public field for a given object using field access.
	 *
	 * @param object
	 * @param field
	 * @param value
	 */
	public static void setField(Object object, String field, Object value) {
		FIELDS.getUnchecked(object.getClass()).set(object, field, value);
	}

	// properties
	private FieldAccess fieldAccess;

	// field index cache
	private LoadingCache<String, Integer> fieldIndexes = CacheBuilder.newBuilder().build(
			new CacheLoader<String, Integer>() {
				@Override
				public Integer load(String field) throws Exception {
					return fieldAccess.getIndex(field);
				}
			});

	// constructor
	private Fields(Class<?> objectClass) {
		this.fieldAccess = FieldAccess.get(objectClass);
	}

	/**
	 * Gets the value of a field for a given object using field access.
	 *
	 * @param object
	 * @param field
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	private <T> T get(Object object, String field) {
		return (T) fieldAccess.get(object, fieldIndexes.getUnchecked(field));
	}

	/**
	 * Sets the value of a field for a given object using field access.
	 *
	 * @param object
	 * @param field
	 * @param value
	 */
	private void set(Object object, String field, Object value) {
		fieldAccess.set(object, fieldIndexes.getUnchecked(field), value);
	}

}
