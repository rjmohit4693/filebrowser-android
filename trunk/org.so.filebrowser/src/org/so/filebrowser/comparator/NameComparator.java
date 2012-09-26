package org.so.filebrowser.comparator;

import java.util.Comparator;

import org.so.filebrowser.FileData;

/**
 * Sorts FileData by name.
 * 
 * @author strangeoptics
 *
 */
public class NameComparator implements Comparator<FileData> {

	@Override
	public int compare(FileData lhs, FileData rhs) {
		return lhs.name.compareToIgnoreCase(rhs.name);
	}

}
