package org.so.filebrowser;

/**
 * 
 * @author strangeoptics
 *
 */
public class FileData {
	
	public String name;
	public boolean directory = false;

	public FileData(String name, boolean directory) {
		this.name = name;
		this.directory = directory;
	}

}
