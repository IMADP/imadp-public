package com.imadp.core.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imadp.core.cache.Cache;
import com.imadp.core.test.IMADPCoreTestCase;

/**
 * CacheManagerConcurrentImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CacheManagerConcurrentImplTest extends IMADPCoreTestCase {
		
	@Test
	public void cacheManagerOperations() {
		String cacheName = "cacheName";
		
		assertFalse(cacheManagerConcurrent.hasCache(cacheName));
		
		cacheManagerConcurrent.addCache(cacheName);
		
		assertTrue(cacheManagerConcurrent.hasCache(cacheName));
		
		Cache<String, String> cache = cacheManagerConcurrent.getCache(cacheName);
		
		assertNotNull(cache);
		
		cache.put("key", "value");
		
		assertTrue(cache.isKeyInCache("key"));
		assertEquals("value", cache.get("key"));
		
		cacheManagerConcurrent.clearAll();
		
		assertFalse(cache.isKeyInCache("key"));
		
		cacheManagerConcurrent.removeCache(cacheName);
		
		assertFalse(cacheManagerConcurrent.hasCache(cacheName));
	}	

}
