package com.imadp.service.account.authority;

import com.imadp.service.PersistableServiceImpl;

/**
 * AuthorityServiceImpl
 *
 * The standard implementation of the AuthorityService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class AuthorityServiceImpl extends PersistableServiceImpl<Authority> implements AuthorityService {

	@Override
	public Authority findFirstByName(String name) {
		return findFirstBy(Authority.NAME, name);
	}

	@Override
	public Authority findFirstByType(AuthorityType type) {
		return findFirstBy(Authority.NAME, type.toString());
	}

}
