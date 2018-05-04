package com.imadp.web.stripes;

/**
 * ActionBeanAware
 *
 * An interface that allows any class to run logic at the appropriate time in the actionBean lifecycle.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public interface ActionBeanAware<T extends AbstractActionBean<? extends AbstractActionBeanContext<?>>> {

	/**
	 * Hook called after the actionBean is created to allow members of an actionBean to run any initialization logic.
	 *
	 * @param actionBean
	 */
	public void initialize(T actionBean);

}