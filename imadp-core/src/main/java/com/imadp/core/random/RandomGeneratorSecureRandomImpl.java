package com.imadp.core.random;

import java.security.SecureRandom;
import java.util.Random;




/**
 * RandomGeneratorSecureRandomImpl
 * 
 * The SecureRandom implementation of the RandomGeneratorService.
 * 
 * @note This service is declared final as it was not designed with inheritance in mind.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public final class RandomGeneratorSecureRandomImpl extends AbstractRandomGenerator {
	private SecureRandom random;
	
	@Override
	protected void onInit() {
		super.onInit();
		
		random = new SecureRandom();		
	}

	@Override
	public Random getRandom() {
		return random;
	}
	
}
 