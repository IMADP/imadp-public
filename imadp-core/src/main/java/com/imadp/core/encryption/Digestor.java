package com.imadp.core.encryption;



/**
 * IDigestor
 *
 * Provides uni-directional String digestion services.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface Digestor {

	/**
	 * Digests a String and returns the digested value.
	 *
	 * @param string
	 * @throws DigestionException if unable to digest a string
	 * @return String
	 */
	public String digest(String string);

	/**
	 * Compares an undigested string against a digested one
	 * and returns true if they match, false otherwise.
	 *
	 * @param undigested
	 * @param digested
	 * @throws DigestionException if unable to digest a string
	 * @return boolean
	 */
	public boolean isEqualDigest(String undigested, String digested);

}