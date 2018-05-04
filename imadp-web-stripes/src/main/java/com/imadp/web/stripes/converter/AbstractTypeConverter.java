package com.imadp.web.stripes.converter;

import java.util.Locale;

import net.sourceforge.stripes.format.Formatter;
import net.sourceforge.stripes.validation.TypeConverter;

/**
 * AbstractTypeConverter
 *
 * Abstract class for type converters and formatters.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractTypeConverter<T> implements Formatter<T>, TypeConverter<T> {

	@Override
    public void init() {

    }

	@Override
    public void setFormatPattern(String formatPattern) {

    }

    @Override
    public void setFormatType(String formatType) {

    }

    @Override
    public void setLocale(Locale locale) {

    }

}