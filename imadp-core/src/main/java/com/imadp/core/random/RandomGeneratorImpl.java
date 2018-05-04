package com.imadp.core.random;

import java.util.Random;



/**
 * RandomGeneratorImpl
 * 
 * The standard implementation of the RandomGeneratorService.
 * 
 * @note This service is declared final as it was not designed with inheritance in mind.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public final class RandomGeneratorImpl extends AbstractRandomGenerator {
	private Random random;
	
	@Override
	protected void onInit() {
		super.onInit();
		
		random = new Random(getRandomSeed());
	}

	@Override
	public Random getRandom() {
		return random;
	}
		
}
 