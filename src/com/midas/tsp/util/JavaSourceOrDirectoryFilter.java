/**
 * 
 */
package com.midas.tsp.util;

import java.io.File;
import java.io.FileFilter;

/**
 * @author German Dario Camacho
 * @date 06/03/2011
 * Filtro para buscar directorios o archivos .java
 *
 */
public class JavaSourceOrDirectoryFilter implements FileFilter {

	/* (non-Javadoc)
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	@Override
	public boolean accept(File arg0) {

		if(arg0.isDirectory()|| arg0.getName().endsWith(".java")  ){
			return true;
		}
		return false;
	}

}
