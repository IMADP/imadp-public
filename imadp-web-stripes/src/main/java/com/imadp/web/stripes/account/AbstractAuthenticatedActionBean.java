package com.imadp.web.stripes.account;

import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;
import com.imadp.web.stripes.AbstractActionBean;

/**
 * AbstractAuthenticatedActionBean
 *
 * An abstract class providing base functionality for authenticated ActionBean implementations.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractAuthenticatedActionBean<T extends AbstractAuthenticatedActionBeanContext<?>>
	extends AbstractActionBean<T> {

	/**
	 * Converts a User.
	 *
	 * @param uid
	 * @return User
	 */
	public User convertUser(String uid) {
		return getContext().convertUser(uid);
	}

	/**
	 * Populates a persistableUser object with the user from the context.
	 * Throws an unsupported operation if the user is not found in session.
	 *
	 * @param persistableUser
	 */
	protected final void populatePersistableUser(AbstractPersistableUser persistableUser) {
		if(persistableUser != null && !persistableUser.hasUser())
			persistableUser.setUser(getContext().getUser());
	}

	/**
	 * Populates a persistableUser object with the user from the context or null if no user was found.
	 *
	 * @param persistableUser
	 */
	protected final void populatePersistableUserOrNull(AbstractPersistableUser persistableUser) {
		if(persistableUser != null && !persistableUser.hasUser())
			persistableUser.setUser(getContext().getUserOrNull());
	}

	@Override
	protected boolean isHandleSessionExpirationOnPost() {
		// if the user has been auto-logged in, we don't need to consider this a session expiration
		return(!getContext().isLoggedIn());
	}

}