package com.imadp.core.concurrent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.mutable.MutableInt;
import org.junit.Test;

import com.imadp.core.test.IMADPCoreTestCase;


/**
 * StringMutexTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class GlobalMutexTest extends IMADPCoreTestCase {
	MutableInt counter = new MutableInt(0);

	@Override
	public void before() throws Exception {
		super.before();

	}

	@Test
	public void concurrentExecute() throws InterruptedException {
		assertEquals(0, counter.intValue());

		for(int i=0; i<100; i++)
			new Thread() {
			@Override
			public void run() {
				synchronized(GlobalMutex.getMutex(getClass(), "test", new String("1")))
				{
					counter.increment();
					counter.increment();
				}
			}

		}.start();

		// wait for threads to complete
		Thread.sleep(500);

		assertEquals(200, counter.intValue());
        assertEquals(1, GlobalMutex.MUTEX_CACHE.size());

        try
        {
            final List<long[]> infiniteList = new LinkedList<>();

           for(long i = Long.MIN_VALUE; i < Long.MAX_VALUE; i++)
                infiniteList.add(new long[102400]);

            fail("An OutOfMemoryError should be thrown");
        }
        catch(OutOfMemoryError e)
        {
        	GlobalMutex.MUTEX_CACHE.cleanUp();
        }

		assertEquals(0, GlobalMutex.MUTEX_CACHE.size());
	}

}
