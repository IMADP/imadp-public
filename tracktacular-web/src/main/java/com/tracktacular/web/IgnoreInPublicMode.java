package com.tracktacular.web;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * IgnoreInPublicMode
 *
 * Annotation to interrupt the resolution handling of a request in public mode.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface IgnoreInPublicMode {

}