package com.imadp.core.random;



/**
 * IRandomGenerator
 * 
 * Provides random number generation and utility operations.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public interface RandomGenerator {
	
	/**
	 * Return a random long.
	 * 
	 * @return long
	 */
	public long randomLong();
	
	/**
	 * Return a random integer.
	 * 
	 * @return int
	 */
	public int randomInt();
	
	/**
	 * Return a random integer from 0 to max, exclusive.
	 * 
	 * @param max
	 * @return int
	 */
	public int randomInt(int max);
	
	/**
	 * Return a random long from 0 to max, exclusive.
	 * 
	 * @param max
	 * @return int
	 */
	public long randomLong(long max);
	
	/**
	 * Set a new seed.
	 * 
	 * @param seed
	 */
	public void setSeed(long seed);
	
	/**
	 * Generates a new seed automatically.
	 * 
	 */
	public void reSeed();
	
}
 