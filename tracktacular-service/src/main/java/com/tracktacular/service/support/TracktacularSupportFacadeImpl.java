package com.tracktacular.service.support;

import org.apache.commons.lang.Validate;

import com.imadp.service.FacadeComponent;
import com.imadp.service.feedback.Feedback;
import com.imadp.service.feedback.FeedbackService;
import com.imadp.service.feedback.FeedbackValidator;


/**
 * TracktacularSupportFacadeImpl
 *
 * The standard implementation of the TracktacularSupportFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class TracktacularSupportFacadeImpl extends FacadeComponent implements TracktacularSupportFacade {
	private FeedbackService feedbackService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(feedbackService);
	}

	@Override
	public void saveFeedback(Feedback feedback) {
		new FeedbackValidator("feedback", feedback).validate();
		feedbackService.save(feedback);
	}

	// getters and setters
	public FeedbackService getFeedbackService() {
		return feedbackService;
	}

	public void setFeedbackService(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}

}