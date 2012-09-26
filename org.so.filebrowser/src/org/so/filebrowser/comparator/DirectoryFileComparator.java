package org.so.filebrowser.comparator;

import java.util.Comparator;

import org.so.filebrowser.FileData;

/**
 * Sorting order for FileData: <br>
 * 1) directories <br>
 * 2) files
 * 
 * @author strangeoptics
 *
 */
public class DirectoryFileComparator implements Comparator<FileData> {

	@Override
	public int compare(FileData lhs, FileData rhs) {
		if(lhs.directory != rhs.directory) {
			if(lhs.directory) {
				return -1;
			}
			return 1;
		}
		return 0;
	}

}
