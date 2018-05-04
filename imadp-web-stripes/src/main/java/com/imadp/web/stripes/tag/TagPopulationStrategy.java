package com.imadp.web.stripes.tag;

import net.sourceforge.stripes.exception.StripesJspException;
import net.sourceforge.stripes.tag.DefaultPopulationStrategy;
import net.sourceforge.stripes.tag.InputTagSupport;

/**
 * TagPopulationStrategy
 *
 * A custom population strategy that favors the tag initially, followed by ActionBean, followed by request.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TagPopulationStrategy extends DefaultPopulationStrategy {

    @Override
    public Object getValue(InputTagSupport tag) throws StripesJspException {

    	// if form has validation errors, grab the values from the request
    	if (isFormInError(tag))
    		return getValuesFromRequest(tag);

    	// try getting from the tag
    	return getValueFromTag(tag);
    }
    
}