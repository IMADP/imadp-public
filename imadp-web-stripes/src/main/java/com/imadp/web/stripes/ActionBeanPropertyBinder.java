package com.imadp.web.stripes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.controller.DefaultActionBeanPropertyBinder;
import net.sourceforge.stripes.controller.ParameterName;
import net.sourceforge.stripes.util.CryptoUtil;
import net.sourceforge.stripes.validation.TypeConverter;
import net.sourceforge.stripes.validation.TypeConverterFactory;
import net.sourceforge.stripes.validation.ValidationError;
import net.sourceforge.stripes.validation.ValidationMetadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ActionBeanPropertyBinder
 *
 * Property builder supplying the actionBean to type converters.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ActionBeanPropertyBinder extends DefaultActionBeanPropertyBinder {

	// logger
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List<Object> convert(ActionBean bean, ParameterName propertyName,
			String[] values, Class<?> declaredType, Class<?> scalarType,
			ValidationMetadata validationInfo, List<ValidationError> errors) throws Exception {

		List<Object> returns = new ArrayList<>();
		Class returnType = null;

		/*
		 * Dig up the type converter.  This gets a bit tricky because we need to handle the following cases:
		 * 1. We need to simply find a converter for the declared type of a simple property
		 * 2. We need to find a converter for the element type in a list/array/map
		 * 3. We have a domain model object that implements List/Map and has a converter itself
		 */

		TypeConverterFactory factory = getConfiguration().getTypeConverterFactory();
		Locale locale = bean.getContext().getRequest().getLocale();
		TypeConverter<?> converter = factory.getTypeConverter(declaredType, locale);

		if (validationInfo != null && validationInfo.converter() != null)
		{
			// if a specific converter was requested and it's the same type as one we'd use
			// for the declared type, set the return type appropriately
			if (converter != null && validationInfo.converter().isAssignableFrom(converter.getClass()))
				returnType = declaredType;

			// otherwise assume that it's a converter for the scalar type inside a collection
			else
				returnType = scalarType;

			converter = factory.getInstance(validationInfo.converter(), locale);
		}

		// else, if we got a converter for the declared type (e.g. Foo implements List<Bar>)
		// then convert for the declared type
		else if (converter != null)
		{
			returnType = declaredType;
		}
		// else look for a converter for the scalar type (Bar in List<Bar>)
		else
		{
			converter  = factory.getTypeConverter(scalarType, locale);
			returnType = scalarType;
		}

		// convert the value
		for (String value : values)
		{
			if (validationInfo != null && validationInfo.encrypted())
				value = CryptoUtil.decrypt(value);

			if (!"".equals(value))
			{
				try
				{
					Object retval = null;

					if (converter != null)
						retval = converter.convert(value, returnType, errors);

					else
					{
						Method method = findConvertMethodForTargetTypeHierarchy(bean.getClass(), returnType);

						if (method != null)
						{
							if(method.getParameterTypes().length == 1)
								retval = method.invoke(bean, new Object[] {value});

							else if(method.getParameterTypes().length == 2)
								retval = method.invoke(bean, new Object[] {value, returnType});

							else if(method.getParameterTypes().length == 3)
								retval = method.invoke(bean, new Object[] {value, returnType, errors});
						}

						else
							logger.warn("Could not convert parameter=[{}] with value=[{}] to type=[{}]. No type converter " +
									"was registered or found in the action bean with signature: public {} convert{}(String input)",
									new Object[] {propertyName.getName(), value, returnType.getSimpleName(),
									returnType.getSimpleName(), returnType.getSimpleName()});
					}

					// if we managed to get a non-null converted value, add it to the return set
					if (retval != null)
						returns.add(retval);

					// set the field name and value on the error
					for (ValidationError error : errors)
					{
						error.setFieldName(propertyName.getStrippedName());
						error.setFieldValue(value);
					}

				}
				catch (Exception e)
				{
					logger.error("Type converter=["+converter+"] threw an exception for value=["+value+"]", e);
				}
			}
		}

		return returns;
	}

	/**
	 * Traverses the target type hierarchy to search for a suitable convert method.
	 * Initially searches the actionBean and all its parent classes for an exact targetType match.
	 * If no match was found, the targetType superclasses are traversed in the same fashion.
	 *
	 * @param actionBean
	 * @param targetType
	 * @return Method
	 */
	private Method findConvertMethodForTargetTypeHierarchy(Class<?> actionBean, Class<?> targetType) {
		Method method = findConvertMethodForActionBeanHierarchy(actionBean, targetType);

		if(method != null)
			return method;

		return targetType.getSuperclass() == null ? null :
			findConvertMethodForTargetTypeHierarchy(actionBean, targetType.getSuperclass());
	}

	/**
	 * Traverses the action bean hierarchy to search for a suitable convert method.
	 *
	 * @param actionBean
	 * @param targetType
	 * @return Method
	 */
	private Method findConvertMethodForActionBeanHierarchy(Class<?> actionBean, Class<?> targetType) {
		for(Method method : actionBean.getDeclaredMethods())
			if(method.getName().equalsIgnoreCase("convert"+targetType.getSimpleName()))
				return method;

		return actionBean.getSuperclass() == null ? null :
			findConvertMethodForActionBeanHierarchy(actionBean.getSuperclass(), targetType);
	}

}