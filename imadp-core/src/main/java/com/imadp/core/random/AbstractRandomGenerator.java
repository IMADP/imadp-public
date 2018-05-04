package com.imadp.core.random;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Random;

import com.imadp.core.CoreComponent;

/**
 * AbstractRandomGenerator
 *
 * Provides common functionality for IRandomGenerator implementations.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractRandomGenerator extends CoreComponent implements RandomGenerator {
	
	@Override
	public long randomLong() {
		return forcePositive(getRandom().nextLong());
	}
	
	@Override
	public int randomInt() {
		return getRandom().nextInt();
	}
	
	@Override
	public int randomInt(int max) {
		return getRandom().nextInt(max);
	}
	@Override
	public long randomLong(long max) {
		if(max < 0)
			throw new IllegalArgumentException("Max must be greater than 0");
		
		long value = new BigInteger(BigInteger.valueOf(max).bitLength(), getRandom()).longValue();

		forcePositive(value);
				
		return value < max ? value : randomLong(max);
	}
	
	@Override
	public void setSeed(long seed) {
		getRandom().setSeed(seed);
	}

	@Override
	public void reSeed() {
		setSeed(getRandomSeed());
	}
	
	/**
	 * Checks the value of a long and reverse its sign if it is negative.
	 * 
	 * @return long
	 */
	private long forcePositive(long value) {
		return value < 0 ? value * -1 : value;
	}
	
	/**
	 * Generates and returns a high quality random seed.
	 * 
	 * @return long
	 */
	protected long getRandomSeed() {
		return ByteBuffer.wrap(new SecureRandom().generateSeed(8)).getLong();
	}
	
	/**
	 * Return the Random implemenation used by the random generator service.
	 * 
	 * @return Random
	 */
	public abstract Random getRandom();
	
}
