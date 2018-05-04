package com.tracktacular.web.page;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.imadp.service.feedback.Feedback;
import com.tracktacular.service.support.TracktacularSupportFacade;
import com.tracktacular.web.TracktacularActionBean;


/**
 * FeedbackActionBean
 *
 * The ActionBean for the feedback page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/feedback")
public class FeedbackActionBean extends TracktacularActionBean {

	// views
	private static final String FEEDBACK_PAGE_VIEW = "/WEB-INF/jsp/page/feedbackPage.jsp";

	@SpringBean
	private TracktacularSupportFacade supportFacade;

	// properties
	private Feedback feedback;

	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution(FEEDBACK_PAGE_VIEW);
	}

	/**
	 * Saves feedback.
	 *
	 * @return Resolution
	 */
	public final Resolution saveFeedback() {
		Feedback feedback = getFeedback();
		populatePersistableUserOrNull(feedback);
		supportFacade.saveFeedback(feedback);
		getContext().addSuccessMessage("support.saveFeedback.success");
		return getAjaxSourceResolution();
	}

	// getters and setters
	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

}