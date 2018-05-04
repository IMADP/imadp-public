package com.imadp.core.random;

import java.util.Random;




/**
 * RandomGeneratorNumericalRecipiesImpl
 * 
 * The NumericalRecipies implementation of the RandomGeneratorService.
 * 
 * @note This service is declared final as it was not designed with inheritance in mind.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public final class RandomGeneratorNumericalRecipiesImpl extends AbstractRandomGenerator {
	private NumericalRecipiesRandom random;
	
	@Override
	protected void onInit() {
		super.onInit();
		
		random = new NumericalRecipiesRandom(getRandomSeed());
	}

	@Override
	public Random getRandom() {
		return random;
	}
		
}
 