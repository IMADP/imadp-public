package com.tracktacular.service.tracker;

import java.util.Random;

import com.imadp.core.AbstractSerializable;



/**
 * AbstractTrackerDemo
 *
 * Base class for tracker demo data.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractTrackerDemo extends AbstractSerializable implements TrackerDemo {
	protected Random random = new Random();

}