	package com.tracktacular.web.page.tracker.cholesterol;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.core.Property;
import com.imadp.core.validation.ValidationResult;
import com.imadp.service.user.AbstractPersistableUser;
import com.tracktacular.service.tracker.cholesterol.Cholesterol;
import com.tracktacular.service.tracker.cholesterol.CholesterolValidator;
import com.tracktacular.web.TracktacularActionBean;


/**
 * ImportCholesterolActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-cholesterol/import-cholesterol-entries")
public class ImportCholesterolActionBean extends CholesterolTrackerActionBean {

	@Override
	public String getTrackerPageTitle() {
		return "Import Cholesterol Entries";
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
		return Cholesterol.class;
	}

	@Override
	public Property<?,?>[] getImportProperties() {
		return new Property[] {Cholesterol.DATE, Cholesterol.HDL_CHOLESTEROL, Cholesterol.LDL_CHOLESTEROL, Cholesterol.TOTAL_CHOLESTEROL, Cholesterol.TRIGLYCERIDES, Cholesterol.NOTES};
	}

	@Override
	protected ValidationResult getImportValidationResult(AbstractPersistableUser item) {
		return new CholesterolValidator("cholesterol", (Cholesterol) item).getValidationResult();
	}

}