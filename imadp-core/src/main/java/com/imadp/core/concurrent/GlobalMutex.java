package com.imadp.core.concurrent;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * GlobalMutex
 *
 * Provides a global string mutex that is safe to synchronize on.
 * Any string that matches another according to its equals method will return the same instance.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class GlobalMutex {

	// mutex cache to provide string references
	final static LoadingCache<String, String> MUTEX_CACHE = CacheBuilder.newBuilder()
			.weakValues()
			.build(new CacheLoader<String, String>() {
				@Override
				public String load(String key) throws Exception {
					return new String(key);
				}
			});

	/**
	 * Returns a global mutex constructed out of the calling class, a domain, and an int identifier.
	 *
	 * @param callingClass
	 * @param domain
	 * @param id
	 * @return String
	 */
	public static String getMutex(Class<?> callingClass, String domain, int id) {
		return getMutex(callingClass, domain, String.valueOf(id));
	}

	/**
	 * Returns a global mutex constructed out of the calling class, a domain, and a long identifier.
	 *
	 * @param callingClass
	 * @param domain
	 * @param id
	 * @return String
	 */
	public static String getMutex(Class<?> callingClass, String domain, long id) {
		return getMutex(callingClass, domain, String.valueOf(id));
	}

	/**
	 * Returns a global mutex constructed out of the calling class, a domain, and a string identifier.
	 *
	 * @param callingClass
	 * @param domain
	 * @param id
	 * @return String
	 */
	public static String getMutex(Class<?> callingClass, String domain, String id) {
		return MUTEX_CACHE.getUnchecked(callingClass.getName() + "." + domain + "." + id);
	}

}