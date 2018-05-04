package com.tracktacular.service.tracker.tv;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.service.user.PersistableUserServiceImpl;


/**
 * ShowServiceImpl
 *
 * The standard implementation of the ShowService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class ShowServiceImpl extends PersistableUserServiceImpl<Show> implements ShowService {
	private EpisodeService episodeService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(episodeService);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveEpisodes(List<Episode> episodes) {
		for(Episode episode : episodes)
			saveEpisode(episode);
	}

	@Override
	protected void onBeforeDelete(Show show) {
		super.onBeforeDelete(show);

		episodeService.delete(show.getEpisodes());
	}

	@Override
	public void saveEpisode(Episode episode) {
		episodeService.save(episode);

		clearUserCaches(episode.getUser());
	}

	@Override
	public void deleteEpisode(Episode episode) {
		episodeService.delete(episode);

		clearUserCaches(episode.getUser());
	}

	// getters and setters
	public EpisodeService getEpisodeService() {
		return episodeService;
	}

	public void setEpisodeService(EpisodeService episodeService) {
		this.episodeService = episodeService;
	}

}