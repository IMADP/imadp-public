package com.imadp.core.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.cache.Cache;
import com.imadp.core.test.IMADPCoreTestCase;

/**
 * CacheManagerEhCacheImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CacheManagerEhCacheImplTest extends IMADPCoreTestCase {
		
	@Test
	public void cacheManagerOperations() {
		String cacheName = "cacheName";
		
		assertFalse(cacheManagerEhCache.hasCache(cacheName));
		
		cacheManagerEhCache.addCache(cacheName);
		
		assertTrue(cacheManagerEhCache.hasCache(cacheName));
		
		Cache<String, String> cache = cacheManagerEhCache.getCache(cacheName);
		
		assertNotNull(cache);
		
		cache.put("key", "value");
		
		assertTrue(cache.isKeyInCache("key"));
		assertEquals("value", cache.get("key"));
		
		cacheManagerEhCache.clearAll();
		
		assertFalse(cache.isKeyInCache("key"));
		
		cacheManagerEhCache.removeCache(cacheName);
		
		assertFalse(cacheManagerEhCache.hasCache(cacheName));
	}	

}
