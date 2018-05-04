package com.imadp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CoreComponent
 *
 * CoreComponent provides a base class for all custom components or service implementations.
 * A logger is provided, as well as two lifecycle method hooks onInit() and onDestroy() to handle
 * any custom initialization or cleanup logic.
 *
 * A variety of simple readability methods are provided to enhance code clarity.
 *
 * Finally, a reflection based toString() implementation is included to provide a common String
 * representation, using the CoreComponentToStringBuilder.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class CoreComponent {

	// logger
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	// these booleans ensure that the init and destroy hooks call super if they are overridden
	private boolean onInitHook;
	private boolean onDestroyHook;

	/**
	 * Init method for any initialization logic.
	 *
	 */
	public final void init() {
		onInit();

		// ensure overriding method called super
		if(!onInitHook)
			throw new UnsupportedOperationException("Methods overriding onInit must call super()");

		logger.info("Initialized: {}", this);
	}

	/**
	 * Hook into the component initialization lifecycle.
	 *
	 */
	protected void onInit() {
		onInitHook = true;
	}

	/**
	 * Destroy method for any cleanup logic.
	 *
	 */
	public final void destroy() {
		onDestroy();

		// ensure overriding method called super
		if(!onDestroyHook)
			throw new UnsupportedOperationException("Methods overriding onDestroy must call super()");

		logger.info("Destroyed: {}", this.getClass().getName());
	}

	/**
	 * Hook into the component destruction lifecycle.
	 *
	 */
	protected void onDestroy() {
		onDestroyHook = true;
	}

	/**
	 * Returns a simple toString identifier, to avoid printing large object graphs from nested core components.
	 *
	 * @return String
	 */
	public String getSimpleToString() {
		return getClass().getName();
	}

	@Override
	public String toString() {
		return CoreComponentToStringBuilder.reflectionToString(this);
	}

}