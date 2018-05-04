package com.imadp.core.test;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import com.imadp.core.io.ParseUtil;

/**
 *
 * A Suite extention to provide package level tests through the SuitePackages annotation.
 * This eliminates the error prone method of declaring new unit tests manually.
 *
 * @note This suite will add tests found in the specified package locations (in the SuitePackages
 *       annotation) only if the classes found have methods annotated with @Test.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class PackageSuite extends Suite {

	/**
	 * The <code>SuitePackages</code> annotation specifies the classes to be run when a class
	 * annotated with <code>@RunWith(Suite.class)</code> is run.
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface SuitePackages {
		public String[] value();
	}

	/**
	 * The <code>ExcludeFromPackageSuite</code> annotation specifies the class to be excluded from
	 * the tests run from the SuitePackages annotation.
	 *
	 */
	@Inherited
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface ExcludeFromPackageSuite {

	}

	/**
	 * Called reflectively on classes annotated with <code>@RunWith(Suite.class)</code>
	 *
	 * @param klass the root class
	 * @param builder builds runners for classes in the suite
	 * @throws InitializationError
	 */
	public PackageSuite(Class<?> klass, RunnerBuilder builder) throws InitializationError {
		super(builder, klass, getAnnotatedClasses(klass));
	}

	/**
	 * Call this when there is no single root class (for example, multiple class names
	 * passed on the command line to {@link org.junit.runner.JUnitCore}
	 *
	 * @param builder builds runners for classes in the suite
	 * @param classes the classes in the suite
	 * @throws InitializationError
	 */
	public PackageSuite(RunnerBuilder builder, Class<?>[] classes) throws InitializationError {
		super(null, builder.runners(null, classes));
	}

	private static Class<?>[] getAnnotatedClasses(Class<?> klass) throws InitializationError {
		SuitePackages annotation= klass.getAnnotation(SuitePackages.class);

		if (annotation == null)
			throw new InitializationError(String.format(
					"class '%s' must have a SuitePackages annotation", klass.getName()));

		List<Class<?>> classes = new ArrayList<>();

		// add classes with the @Test annotation on any method
		for(int i=0; i<annotation.value().length; i++)
		{
			try
			{
				for(Class<?> c : ParseUtil.parseClasses(annotation.value()[i]))
					if(hasTestAnnotation(c.getMethods()) && !hasExcludeTestAnnotation(c))
						classes.add(c);
			}
			catch (Exception exception)
			{
				throw new InitializationError(
						"Could not parse classes from package ["+exception.getMessage()+"]");
			}
		}

		Class<?>[] classArray = new Class[classes.size()];

		for(int i = 0; i < classArray.length; i++)
			classArray[i] = classes.get(i);

		return classArray;
	}

	/**
 	 * Returns true if a method has a Test annotation, false otherwise.
	 *
	 * @param methods
	 * @return boolean
	 */
	private static boolean hasTestAnnotation(Method[] methods) {
		for(int i=0; i<methods.length; i++)
			if(methods[i].isAnnotationPresent(org.junit.Test.class))
				return true;

		return false;
	}

	/**
 	 * Returns true if a class has an ExcludeFromPackageSuite annotation, false otherwise.
	 *
	 * @param className
	 * @return boolean
	 */
	private static boolean hasExcludeTestAnnotation(Class<?> className) {
		for(Annotation a : className.getAnnotations())
			if(a instanceof ExcludeFromPackageSuite)
				return true;

		return false;
	}

}
