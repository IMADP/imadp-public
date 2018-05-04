package com.tracktacular.service.account;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.PersistableUserServiceImpl;


/**
 * PreferencesServiceImpl
 *
 * The standard implementation of the PreferencesService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class PreferencesServiceImpl extends PersistableUserServiceImpl<Preferences> implements PreferencesService {

	@Override
	public List<Preferences> findByEmailAlerts(boolean emailAlerts, CriteriaParams<Preferences> criteriaParams) {
		return findBy(Preferences.EMAIL_ALERTS, emailAlerts, criteriaParams);
	}

	@Override
	public List<Preferences> findByBlogNotification(boolean blogNotification, CriteriaParams<Preferences> criteriaParams) {
		return findBy(Preferences.BLOG_NOTIFICATION, blogNotification, criteriaParams);
	}

}