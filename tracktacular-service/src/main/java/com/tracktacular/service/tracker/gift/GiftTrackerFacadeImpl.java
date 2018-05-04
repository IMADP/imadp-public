package com.tracktacular.service.tracker.gift;

import java.util.List;

import org.apache.commons.lang.Validate;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.AbstractTrackerFacade;
import com.tracktacular.service.tracker.TrackerDemo;


/**
 * GiftTrackerFacadeImpl
 *
 * The standard implementation of the GiftTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class GiftTrackerFacadeImpl extends AbstractTrackerFacade
	implements GiftTrackerFacade {

	private GiftService giftService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(giftService);
	}

	@Override
	public Gift getGift(User user, String uid) {
		return giftService.findFirstByUser(user, uid);
	}

	@Override
	public void saveGift(Gift gift) {
		new GiftValidator("gift", gift).validate();
		giftService.save(gift);
	}

	@Override
	public void deleteGift(Gift gift) {
		giftService.delete(gift);
	}

	@Override
	public Gifts findGivenGifts(User user, String sortProperty) {
		List<Gift> activeGifts = giftService.findByUser(
				user, PersistableState.ACTIVE, CriteriaParams.<Gift>of(Results.ALL));

		return new Gifts(activeGifts, false, sortProperty);
	}

	@Override
	public Gifts findReceivedGifts(User user, String sortProperty) {
		List<Gift> activeGifts = giftService.findByUser(
				user, PersistableState.ACTIVE, CriteriaParams.<Gift>of(Results.ALL));

		return new Gifts(activeGifts, true, sortProperty);
	}

	@Override
	public List<Gift> findDeletedGifts(User user, CriteriaParams<Gift> criteriaParams) {
		return giftService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedGiftCount(User user) {
		return giftService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<Gift> gifts = giftService.findByUser(user, CriteriaParams.<Gift>of(Results.ALL));

		for(Gift gift : gifts)
			deleteGift(gift);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new GiftTrackerDemo(this);
	}

	@Override
	public GiftTrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		Gifts giftsGiven = findGivenGifts(user, Gift.OCCASION.getName());
		Gifts giftsReceived = findReceivedGifts(user, Gift.OCCASION.getName());
		return new GiftTrackerReport(giftsGiven, giftsReceived);
	}

	// getters and setters
	public GiftService getGiftService() {
		return giftService;
	}

	public void setGiftService(GiftService giftService) {
		this.giftService = giftService;
	}

}