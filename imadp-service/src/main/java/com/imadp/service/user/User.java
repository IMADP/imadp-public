package com.imadp.service.user;

import com.imadp.dao.AbstractPersistable;

/**
 * User
 * 
 * The User class represents any user of a system, and is often used to tie together 
 * and imply ownership of other domain objects.
 * 
 * @note This class is declared final as it should not need to be extended. Additional information about a user
 * 		 should be stored in an appropriate table, this object is intended to merely be a lightweight link.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public final class User extends AbstractPersistable {
	
	// constructor
	public User() { 
		
	}

}