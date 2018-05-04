package com.imadp.core.random;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.test.annotation.ExpectedException;

import com.imadp.core.test.IMADPCoreTestCase;


/**
 * RandomGeneratorNumericalRecipiesImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RandomGeneratorNumericalRecipiesImplTest extends IMADPCoreTestCase {
	
	@Test 
	public void randomLongIsPositive() throws Exception {
		for(int i=0; i < 1000; i++)
			assertTrue(randomGeneratorNumericalRecipies.randomLong() >= 0);
	}
	
	@Test 
	public void randomLongIsWithinMaxRange() throws Exception {
		for(int i=0; i < 100000; i++)
		{
			long maxRandomLong = randomGeneratorNumericalRecipies.randomLong();		
			long randomLong = randomGeneratorNumericalRecipies.randomLong(maxRandomLong);
		
			assertTrue("randomLong ["+randomLong+"] should be less than maxRandomLong ["+maxRandomLong+"]",
				randomLong < maxRandomLong);
		}
	}
	
	@Test 	
	@ExpectedException(IllegalArgumentException.class)
	public void invalidRangeShouldThrowException() throws Exception {	
		randomGeneratorNumericalRecipies.randomLong(-1);	
	}	
		
}
