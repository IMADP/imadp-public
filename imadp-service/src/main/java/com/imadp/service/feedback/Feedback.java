package com.imadp.service.feedback;

import com.imadp.core.Property;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Feedback
 * 
 * Holds feedback information from Users.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class Feedback extends AbstractPersistableUser {
	
	// static Properties
	public static final Property<Feedback, String> CONTENT = Property.of("content");		
		
	// properties
	private String content;
	
	// constructor
	public Feedback() { 
		
	}
	
	// constructor
	public Feedback(User user) { 
		super(user);
	}

	// getters and setters	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}		

}