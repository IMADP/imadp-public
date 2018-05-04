package com.imadp.service.account.authority;

import com.imadp.service.PersistableService;


/**
 * AuthorityService
 *
 * Provides a means of granting and revoking Authority levels to Users.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface AuthorityService extends PersistableService<Authority> {

	/**
	 * Finds the first Authority by name.
	 *
	 * @param name
	 * @return Authority
	 */
	public Authority findFirstByName(String name);

	/**
	 * Finds the first Authority by type.
	 *
	 * @param type
	 * @return Authority
	 */
	public Authority findFirstByType(AuthorityType type);

}
