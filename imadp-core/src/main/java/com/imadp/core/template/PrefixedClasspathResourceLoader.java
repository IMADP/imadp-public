package com.imadp.core.template;

import java.io.InputStream;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * PrefixedClasspathResourceLoader
 *
 * Classpath loader that allows prefixed root template directories.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public class PrefixedClasspathResourceLoader extends ClasspathResourceLoader {
    private String prefix = "";

    @Override
    public void init(ExtendedProperties configuration) {
        prefix = configuration.getString("prefix","");
    }

    @Override
    public InputStream getResourceStream(String name) throws ResourceNotFoundException {
        return super.getResourceStream(prefix+name);
    }

}