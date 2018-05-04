package com.imadp.core.random;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.test.annotation.ExpectedException;

import com.imadp.core.test.IMADPCoreTestCase;


/**
 * RandomGeneratorSecureRandomImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RandomGeneratorSecureRandomImplTest extends IMADPCoreTestCase {
	
	@Test 
	public void randomLongIsPositive() throws Exception {
		for(int i=0; i < 1000; i++)
			assertTrue(randomGeneratorSecureRandom.randomLong() >= 0);
	}
	
	@Test 
	public void randomLongIsWithinMaxRange() throws Exception {
		for(int i=0; i < 100000; i++)
		{
			long maxRandomLong = randomGeneratorSecureRandom.randomLong();		
			long randomLong = randomGeneratorSecureRandom.randomLong(maxRandomLong);
		
			assertTrue("randomLong ["+randomLong+"] should be less than maxRandomLong ["+maxRandomLong+"]",
				randomLong < maxRandomLong);
		}
	}
	
	@Test 	
	@ExpectedException(IllegalArgumentException.class)
	public void invalidRangeShouldThrowException() throws Exception {	
		randomGeneratorSecureRandom.randomLong(-1);	
	}	
		
}
