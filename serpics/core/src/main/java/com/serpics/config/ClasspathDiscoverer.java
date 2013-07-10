package com.serpics.config;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.impetus.annovention.Discoverer;
import com.impetus.annovention.Filter;
import com.impetus.annovention.FilterImpl;

public class ClasspathDiscoverer extends Discoverer {

	/** The filter. */
	private Filter filter;

	/**
	 * Instantiates a new classpath reader.
	 */
	public ClasspathDiscoverer() {
		filter = new FilterImpl();
	}

	/**
	 * Uses java.class.path system-property to fetch URLs
	 * 
	 * @return the URL[]
	 */
	@SuppressWarnings("deprecation")
	@Override
	public final URL[] findResources() {
		List<URL> list = new ArrayList<URL>();
		String classpath = System.getProperty("java.class.path");
		StringTokenizer tokenizer = new StringTokenizer(classpath, File.pathSeparator);

		while (tokenizer.hasMoreTokens()) {
			String path = tokenizer.nextToken();
			try {
				path = java.net.URLDecoder.decode(path, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
			}
			File fp = new File(path);
			if (!fp.exists())
				throw new RuntimeException("File in java.class.path does not exist: " + fp);
			try {
				list.add(fp.toURL());
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}
		}
		return list.toArray(new URL[list.size()]);
	}

	/* @see com.impetus.annovention.Discoverer#getFilter() */
	@Override
	public final Filter getFilter() {
		return filter;
	}

	/**
	 * @param filter
	 */
	public final void setFilter(Filter filter) {
		this.filter = filter;
	}
}
