package com.tracktacular.service.tracker.dream;

import com.imadp.dao.PersistableState;
import com.imadp.service.tag.AbstractTag;
import com.imadp.service.user.User;


/**
 * Dreamsign
 *
 * Represents a dreamsign, or notable theme in a dream.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Dreamsign extends AbstractTag<Dream> {

	// constructor
	public Dreamsign() {

	}

	// constructor
	public Dreamsign(User user, Dream dream, String name, PersistableState persistableState) {
		super(user, dream, name, persistableState);
	}

}
