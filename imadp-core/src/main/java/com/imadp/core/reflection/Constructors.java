package com.imadp.core.reflection;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Constructors
 *
 * Provides constructor level reflection using bytecode generation for enhanced performance.
 *
 * @param <T>
 * @author ADP
 * @version 1.0
 */
public final class Constructors<T> {

	// constructors cache
	private static final LoadingCache<String, ConstructorAccess<?>> CONSTRUCTORS = CacheBuilder.newBuilder().build(
			new CacheLoader<String, ConstructorAccess<?>>() {
				@Override
				public ConstructorAccess<?> load(String className) throws Exception {
					return ConstructorAccess.get(Class.forName(className));
				}
			});

	/**
	 * Returns a new instance of an object with the given class.
	 *
	 * @param objectClass
	 * @return T
	 */
	public static <T> T newInstance(Class<T> objectClass) {
		return newInstance(objectClass.getName());
	}

	/**
	 * Returns a new instance of an object with the given class name.
	 *
	 * @param className
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String className) {
		return (T) CONSTRUCTORS.getUnchecked(className).newInstance();
	}

}
