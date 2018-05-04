package com.imadp.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

/**
 * ParseUtil
 *
 * Utility methods for various object parsing.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class ParseUtil {

	/**
	 * Scans and returns a List of all files accessible from the context class loader
     * which belong to the given package.
	 *
	 * @param packageName
	 * @return List<File>
	 * @throws IOException
	 */
	public static List<File> parseFiles(String packageName) throws IOException {

		List<File> files = new ArrayList<>();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(packageName.replace('.', '/'));

		while(resources.hasMoreElements())
        {
            URL resource = resources.nextElement();
            String fileName = resource.getFile();
            String fileNameDecoded = URLDecoder.decode(fileName, "UTF-8");
            files.add(new File(fileNameDecoded));
        }

		return files;
	}

	/**
     * Scans and parses all files accessible from the context class loader
     * which belong to the given package and subpackages.
     *
     * @param packageName
     * @return List<Class>
     * @throws ClassNotFoundException
     * @throws IOException
     */
	public static List<Class<?>> parseClasses(String packageName) throws ClassNotFoundException, IOException {
        List<File> directories = parseFiles(packageName);
        ArrayList<Class<?>> classes = new ArrayList<>();

        for(File directory : directories)
            classes.addAll(parseClasses(directory, packageName));

        return classes;
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory
     * @param packageName
     * @return List<Class>
     * @throws ClassNotFoundException
     */
	private static List<Class<?>> parseClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();

        if (!directory.exists())
            return classes;

        File[] files = directory.listFiles();

        for (File file : files)
        {
        	String fileName = file.getName();

            if (file.isDirectory())
            {
                classes.addAll(parseClasses(file, packageName + "." + fileName));
            }
            else if (fileName.endsWith(".class"))
            {
            	Class<?> _class;

				try
				{
					_class = Class.forName(packageName + '.' + fileName.substring(
							0, fileName.length() - 6));
				}
				catch(ExceptionInInitializerError e)
				{
					// happens if a class dependency is not fulfilled
					_class = Class.forName(packageName + '.' + fileName.substring(
							0, fileName.length() - 6),
							false, Thread.currentThread().getContextClassLoader());
				}
				classes.add(_class);
            }
        }

        return classes;
    }

	/**
	 * Parses a List<String> from a classpath resource, delimited by new lines.
	 *
	 * @param resource
	 * @return List
	 */
	public static List<String> parseResourceAsList(String resource) {
		return ParseUtil.parseResourceAsList(resource, System.getProperty("line.separator"));
	}

	/**
	 * Parses a List<String> from a classpath resource, delimited by the supplied delimiter.
	 *
	 * @param resource
	 * @param delimiter
	 * @return List<String>
	 */
	public static List<String> parseResourceAsList(String resource, String delimiter) {
		List<String> list = new ArrayList<>();

		InputStream inputStream = ParseUtil.class.getClassLoader().getResourceAsStream(resource);

		if(inputStream == null)
			throw new IllegalArgumentException("Could not parse resource ["+resource+"]");

		Scanner scanner = new Scanner(inputStream);
		scanner.useDelimiter(delimiter);

		while (scanner.hasNext())
			list.add(scanner.next());

		return list;
	}

}