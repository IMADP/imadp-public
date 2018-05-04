package com.tracktacular.service.account;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.PersistableUserService;

/**
 * PreferencesService
 *
 * Provides common retrieval operations for TracktacularPreferences objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface PreferencesService extends PersistableUserService<Preferences> {

	/**
	 * Finds all preferences by emailAlerts.
	 *
	 * @param criteriaParams
	 * @param emailAlerts
	 * @return List<Preferences>
	 */
	public List<Preferences> findByEmailAlerts(boolean emailAlerts, CriteriaParams<Preferences> criteriaParams);

	/**
	 * Finds all preferences by blog notification.
	 *
	 * @param criteriaParams
	 * @param blogNotification
	 * @return List<Preferences>
	 */
	public List<Preferences> findByBlogNotification(boolean blogNotification, CriteriaParams<Preferences> criteriaParams);

}