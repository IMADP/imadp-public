package com.tracktacular.service.tracker.gift;

import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * GiftTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class GiftTrackerReport extends AbstractTrackerReport {
	private final int givenGiftCount;
	private final int receivedGiftCount;

    // constructor
    public GiftTrackerReport(Gifts giftsGiven, Gifts giftsReceived) {
    	this.givenGiftCount = giftsGiven.getGiftCount();
    	this.receivedGiftCount = giftsReceived.getGiftCount();
    }

    @Override
	public boolean isEnabled() {
    	return givenGiftCount + receivedGiftCount > 0;
    }

    // getters
	public int getGivenGiftCount() {
		return givenGiftCount;
	}

	public int getReceivedGiftCount() {
		return receivedGiftCount;
	}

}