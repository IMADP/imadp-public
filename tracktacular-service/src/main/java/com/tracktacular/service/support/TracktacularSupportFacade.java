package com.tracktacular.service.support;

import com.imadp.service.feedback.Feedback;


/**
 * TracktacularSupportFacade
 *
 * Facade encapsulating the support operations for Tracktacular.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface TracktacularSupportFacade {

	/**
	 * Saves user feedback.
	 *
	 * @param feedback
	 */
	public void saveFeedback(Feedback feedback);

}