package org.so.filebrowser;

import java.io.File;

/**
 * 
 * @author strangeoptics
 *
 */
public class FileData {
	
	public File file;
	public String name;
	public boolean directory = false;

	public FileData(File file, String name, boolean directory) {
		this.file = file;
		this.name = name;
		this.directory = directory;
	}

}
