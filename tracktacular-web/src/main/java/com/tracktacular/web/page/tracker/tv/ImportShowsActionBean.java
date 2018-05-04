	package com.tracktacular.web.page.tracker.tv;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.core.Property;
import com.imadp.core.validation.ValidationResult;
import com.imadp.service.user.AbstractPersistableUser;
import com.tracktacular.service.tracker.tv.Show;
import com.tracktacular.service.tracker.tv.ShowValidator;
import com.tracktacular.service.tracker.tv.Shows;
import com.tracktacular.web.TracktacularActionBean;


/**
 * ImportShowsActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-tv/import-shows")
public class ImportShowsActionBean extends TvTrackerActionBean {

	@Override
	public String getTrackerPageTitle() {
		return "Import Shows";
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
		return Show.class;
	}

	@Override
	public Property<?,?>[] getImportProperties() {
		return new Property[] {Show.TITLE, Show.TAG, Show.RATING, Show.NOTES};
	}

	@Override
	protected ValidationResult getImportValidationResult(AbstractPersistableUser item) {
		Shows shows = getTrackerFacade().findActiveShows(getTrackerUser(), Show.TITLE.getName());
		return new ShowValidator("show", (Show) item, shows).getValidationResult();
	}

}