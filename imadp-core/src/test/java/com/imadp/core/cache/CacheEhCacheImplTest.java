package com.imadp.core.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.springframework.test.annotation.ExpectedException;

import com.imadp.core.cache.CreateValueException;
import com.imadp.core.cache.Cache;
import com.imadp.core.cache.CreateValue;
import com.imadp.core.test.IMADPCoreTestCase;

/**
 * CacheEhCacheImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CacheEhCacheImplTest extends IMADPCoreTestCase {
	Long key;
	Boolean value;
	Cache<Long, Boolean> cache;
	String cacheName;
	AtomicInteger count;
	
	@Override
	public void before() throws Exception {
		super.before();

		count = new AtomicInteger(0);
		
		cacheName = "cacheName";
		cacheManagerEhCache.addCache(cacheName);
		cache = cacheManagerEhCache.getCache(cacheName);	
		
		key = 1L;
		value = Boolean.TRUE;		
	}
	
	@Override
	public void after() throws Exception {
		super.after();
		
		cacheManagerEhCache.removeCache(cacheName);
	}

	@Test
	public void getAndRemove() {
		assertNull(cache.get(key));

		cache.put(key, value);

		assertNotNull(cache.get(key));
		assertEquals(value, cache.get(key));

		cache.remove(key);

		assertNull(cache.get(key));		
	}

	@Test
	public void getKeys() {
		List<Long> keys = cache.getKeys();
		assertEquals(0, keys.size());

		Long keyOne = 1L;
		Long keyTwo = 2L;
		Long keyThree = 3L;

		assertNull(cache.get(keyOne));
		assertNull(cache.get(keyTwo));
		assertNull(cache.get(keyThree));

		cache.put(keyOne, value);
		cache.put(keyTwo, value);
		cache.put(keyThree, value);

		assertEquals(3, cache.getSize());
		assertNotNull(cache.get(keyOne));
		assertNotNull(cache.get(keyTwo));
		assertNotNull(cache.get(keyThree));

		keys = cache.getKeys();

		assertEquals(3, keys.size());
		assertTrue(keys.contains(keyOne));
		assertTrue(keys.contains(keyTwo));
		assertTrue(keys.contains(keyThree));

		cache.remove(keyOne);
		cache.remove(keyTwo);
		cache.remove(keyThree);

		assertNull(cache.get(keyOne));
		assertNull(cache.get(keyTwo));
		assertNull(cache.get(keyThree));

		keys = cache.getKeys();
		assertEquals(0, keys.size());
	}

	@Test
	public void putNull() {
		assertNull(cache.get(key));
		
		cache.put(key, null);
		
		assertTrue(cache.isKeyInCache(key));
		assertNull(cache.get(key));
	}
	
	@Test
	public void getOrPut() {
		assertNull(cache.get(key));

		cache.getOrPut(key, new CreateValue<Long, Boolean>() {
			@Override
			public Boolean create(Long key) {
				return value;
			}
		});

		assertNotNull(cache.get(key));
		assertEquals(value, cache.get(key));

		cache.getOrPut(key, new CreateValue<Long, Boolean>() {
			@Override
			public Boolean create(Long key) {
				return value;
			}
		});

		assertNotNull(cache.get(key));
		assertEquals(value, cache.get(key));

		cache.remove(key);

		assertNull(cache.get(key));		
	}
	
	@Test
	public void putAndGetOrPut() {
		assertNull(cache.get(key));

		cache.put(key, true);
		
		cache.getOrPut(key, new CreateValue<Long, Boolean>() {
			@Override
			public Boolean create(Long key) {
				return value;
			}
		});
		
		cache.remove(key);

		assertNull(cache.get(key));		
	}
	
	@Test
	@ExpectedException(CreateValueException.class)
	public void getOrPutException() {
		assertNull(cache.get(key));

		// createValue that will error
		CreateValue<Long, Boolean> createValue = new CreateValue<Long, Boolean>() {
			@Override
			public Boolean create(Long key) {
				throw new IllegalArgumentException("not initialized");			
			}
		};
		
		cache.getOrPut(key, createValue);
	}
	
	@Test
	public void getOrPutExceptionCacheRemove() {
		assertNull(cache.get(key));

		// createValue that will error
		CreateValue<Long, Boolean> createValue = new CreateValue<Long, Boolean>() {
			@Override
			public Boolean create(Long key) {
				throw new IllegalArgumentException("not initialized");			
			}
		};
		
		try
		{
			cache.getOrPut(key, createValue);
		}
		catch(CreateValueException e)
		{
			
		}
		
		assertFalse(cache.isKeyInCache(key));
	}
						
	@Test
	public void concurrentGetOrPut() throws InterruptedException {
		assertEquals(0, count.get());
		assertEquals(0, cache.getSize());
		
		Thread thread1 = getConcurrentGetOrPutThread();
		Thread thread2 = getConcurrentGetOrPutThread();
		Thread thread3 = getConcurrentGetOrPutThread();
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		// wait for threads to complete
		Thread.sleep(1000);
		
		assertEquals(1, count.get());
		assertEquals(1, cache.getSize());
	}

	/**
	 * Returns a thread which puts a slow to process value in cache.
	 * A null value is returned from the ICreateValue to ensure null values are cacheable.
	 * 
	 * @return Thread
	 */
	private Thread getConcurrentGetOrPutThread() {
		return new Thread() {
			
			@Override
			public void run() {
				CreateValue<Long, Boolean> createValue = new CreateValue<Long, Boolean>() {					
					@Override
					public Boolean create(Long key) {
						count.incrementAndGet();
						try
						{
							Thread.sleep(500);
						}
						catch (InterruptedException e)
						{
							
						}	
						return null;
					}
					
				};

				cache.getOrPut(key, createValue);
			}
			
		};
	}

}
