package com.imadp.service.commerce;

import java.io.IOException;
import java.io.Reader;

/**
 * CommerceFacade
 *
 * Provides common functionality such as event handling for commerce facades.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface CommerceFacade {

	/**
	 * Processes an event from a json reader.
	 *
	 * @param reader
	 * @throws IOException
	 */
	public void processEvent(Reader reader) throws IOException;

}