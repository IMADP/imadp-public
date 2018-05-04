	package com.tracktacular.web;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

import com.imadp.core.reflection.Methods;
import com.imadp.core.validation.ValidationException;
import com.imadp.web.stripes.AbstractAction;
import com.tracktacular.web.page.tracker.TrackerActionBean;



/**
 * ImportAction
 *
 * The action for importing data.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ImportAction extends AbstractAction<TrackerActionBean<?,?>> {

	@Override
	protected final void doAction(TrackerActionBean<?,?> actionBean) {
		try
		{
			for(Object object : actionBean.getContext().getSession().getImportedItems())
			{
				ImportedItem<?> importedItem = (ImportedItem<?>)object;

				if(importedItem.isValid())
				{
					String importClassName = actionBean.getImportClass().getSimpleName();
					Methods.invokeMethod(actionBean.getTrackerFacade(), "save" + importClassName, importedItem.getItem());
				}

			}
		}
		catch(ValidationException validationException)
		{

		}

		actionBean.getContext().getSession().getImportedItems().clear();
	}

	@Override
	protected final Resolution getResolution(TrackerActionBean<?,?> actionBean) {
		actionBean.getContext().addSuccessMessage("tracktacular.importData.success");

		return new RedirectResolution(actionBean.getDefaultActionBean())
			.addParameter(TrackerActionBean.TRACKER_USER_USERNAME_PARAM, actionBean.getTrackerUserUsername());
	}

	@Override
	protected boolean isSessionTokenRequired() {
		return true;
	}

}