package com.imadp.service.tag;

import com.imadp.service.user.User;



/**
 * SampleTag
 *
 * A sample taggable persistable object.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class SampleTag extends AbstractTag<SampleTaggable> {

	// constructor
	public SampleTag() {

	}

	// constructor
	public SampleTag(User user) {
		this(user, null, null);
	}

	// constructor
	public SampleTag(User user, SampleTaggable taggable) {
		this(user, taggable, null);
	}

	// constructor
	public SampleTag(User user, SampleTaggable taggable, String name) {
		super(user, taggable, name);
	}

}