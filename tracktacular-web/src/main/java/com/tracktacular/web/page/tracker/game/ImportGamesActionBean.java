	package com.tracktacular.web.page.tracker.game;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.core.Property;
import com.imadp.core.validation.ValidationResult;
import com.imadp.service.user.AbstractPersistableUser;
import com.tracktacular.service.tracker.game.Game;
import com.tracktacular.service.tracker.game.GameValidator;
import com.tracktacular.service.tracker.game.Games;
import com.tracktacular.web.TracktacularActionBean;


/**
 * ImportGamesActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-game/import-games")
public class ImportGamesActionBean extends GameTrackerActionBean {

	@Override
	public String getTrackerPageTitle() {
		return "Import Games";
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
		return Game.class;
	}

	@Override
	public Property<?,?>[] getImportProperties() {
		return new Property[] {Game.PLATFORM, Game.TITLE, Game.TAG, Game.RATING, Game.NOTES};
	}

	@Override
	protected ValidationResult getImportValidationResult(AbstractPersistableUser item) {
		Games games = getTrackerFacade().findActiveGames(getTrackerUser(), Game.TITLE.getName());
		return new GameValidator("game", (Game) item, games).getValidationResult();
	}

}