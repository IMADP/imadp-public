	package com.tracktacular.web.page.tracker.body;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.core.Property;
import com.imadp.core.validation.ValidationResult;
import com.imadp.service.user.AbstractPersistableUser;
import com.tracktacular.service.tracker.body.Body;
import com.tracktacular.service.tracker.body.BodyValidator;
import com.tracktacular.web.TracktacularActionBean;


/**
 * ImportBodyActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-body/import-body-entries")
public class ImportBodyActionBean extends BodyTrackerActionBean {

	@Override
	public String getTrackerPageTitle() {
		return "Import Body Entries";
	}

	@Override
	public Resolution index() {
		if(isPublicMode())
			return new RedirectResolution(getDefaultActionBean());

		return super.index();
	}

	@Override
	protected Class<? extends TracktacularActionBean> getUserToggleClass() {
		return getDefaultActionBean();
	}

	@Override
	public Class<? extends AbstractPersistableUser> getImportClass() {
		return Body.class;
	}

	@Override
	public Property<?,?>[] getImportProperties() {
		return new Property[] {Body.DATE, Body.ANKLES, Body.BICEPS, Body.BODY_FAT, Body.CALVES, Body.CHEST, Body.FEET, Body.FOREARMS, Body.HEIGHT, Body.HIPS, Body.NECK, Body.THIGHS, Body.WAIST, Body.WEIGHT, Body.WRISTS, Body.NOTES};
	}

	@Override
	protected ValidationResult getImportValidationResult(AbstractPersistableUser item) {
		return new BodyValidator("body", (Body) item).getValidationResult();
	}

}