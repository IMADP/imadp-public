	package com.tracktacular.web.page.tracker.music;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.core.Property;
import com.imadp.core.validation.ValidationResult;
import com.imadp.service.user.AbstractPersistableUser;
import com.tracktacular.service.tracker.music.Album;
import com.tracktacular.service.tracker.music.AlbumValidator;
import com.tracktacular.service.tracker.music.Albums;
import com.tracktacular.web.TracktacularActionBean;


/**
 * ImportAlbumsActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-music/import-albums")
public class ImportAlbumsActionBean extends MusicTrackerActionBean {

	@Override
	public String getTrackerPageTitle() {
		return "Import Albums";
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
		return Album.class;
	}

	@Override
	public Property<?,?>[] getImportProperties() {
		return new Property[] {Album.TITLE, Album.ARTIST, Album.TAG, Album.RATING, Album.NOTES};
	}

	@Override
	protected ValidationResult getImportValidationResult(AbstractPersistableUser item) {
		Albums albums = getTrackerFacade().findActiveAlbums(getTrackerUser(), Album.TITLE.getName());
		return new AlbumValidator("album", (Album) item, albums).getValidationResult();
	}

}