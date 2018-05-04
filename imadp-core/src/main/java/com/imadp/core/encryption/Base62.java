package com.imadp.core.encryption;

import java.math.BigInteger;


/**
 * Base62
 *
 * A utility for converting long numbers into corresponding Base62 equivalents,
 * with a dictionary of numbers, lowercase letters, and uppercase letters.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Base62 {

	// the base value
	private static final BigInteger BASE = BigInteger.valueOf(62);

	// the dictionary
	private static final String DICTIONARY = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	/**
	 * Encodes a number using Base62 encoding.
	 *
	 * @param value
	 * @return String
	 * @throws IllegalArgumentException if <code>value</code> is a negative integer
	 */
	public static String encode(Long value) {
		if(value == null)
			return null;

		BigInteger number = BigInteger.valueOf(value);

		// if the number is less than 0, throw an exception
		if (number.compareTo(BigInteger.ZERO) == -1)
			throw new IllegalArgumentException("number must not be negative");

		StringBuilder result = new StringBuilder();

		// loop while the number is greater than 0
		while (number.compareTo(BigInteger.ZERO) == 1)
		{
			BigInteger[] divmod = number.divideAndRemainder(BASE);
			number = divmod[0];
			int digit = divmod[1].intValue();
			result.insert(0, DICTIONARY.charAt(digit));
		}

		return (result.length() == 0) ? DICTIONARY.substring(0, 1) : result.toString();
	}

	/**
	 * Decodes a string using Base62 encoding.
	 *
	 * @param string
	 * @return long
	 */
	public static Long decode(String string) {
		if(string == null)
			return null;

		BigInteger result = BigInteger.ZERO;
		int digits = string.length();

		// loop until all digits are found
		for (int index = 0; index < digits; index++)
		{
			int digit = DICTIONARY.indexOf(string.charAt(digits - index - 1));
			result = result.add(BigInteger.valueOf(digit).multiply(BASE.pow(index)));
		}

		return result.longValue();
	}

}
