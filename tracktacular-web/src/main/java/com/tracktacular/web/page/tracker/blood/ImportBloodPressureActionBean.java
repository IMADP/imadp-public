	package com.tracktacular.web.page.tracker.blood;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.core.Property;
import com.imadp.core.validation.ValidationResult;
import com.imadp.service.user.AbstractPersistableUser;
import com.tracktacular.service.tracker.blood.BloodPressure;
import com.tracktacular.service.tracker.blood.BloodPressureValidator;
import com.tracktacular.web.TracktacularActionBean;


/**
 * ImportBloodPressureActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-blood/import-blood-entries")
public class ImportBloodPressureActionBean extends BloodTrackerActionBean {

	@Override
	public String getTrackerPageTitle() {
		return "Import Blood Pressure Entries";
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
		return BloodPressure.class;
	}

	@Override
	public Property<?,?>[] getImportProperties() {
		return new Property[] {BloodPressure.DATE, BloodPressure.DIASTOLIC, BloodPressure.SYSTOLIC, BloodPressure.NOTES};
	}

	@Override
	protected ValidationResult getImportValidationResult(AbstractPersistableUser item) {
		return new BloodPressureValidator("bloodPressure", (BloodPressure) item).getValidationResult();
	}

}