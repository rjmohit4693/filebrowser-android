package org.so.filebrowser;


import java.io.File;

import org.so.filebrowser.comparator.ComparatorChain;
import org.so.filebrowser.comparator.DirectoryFileComparator;
import org.so.filebrowser.comparator.NameComparator;

import android.content.Context;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * TODO: folders on top, sorting, filtering, set root, ...<br>
 * 
 * Supports multiple Comparators by a ComparatorChain see getComparator(). <br>
 * 
 * root: /mnt/sdcard
 * 
 * @author strangeoptics
 *
 */
public class FileListView extends ListView implements AdapterView.OnItemClickListener {

	private FileSystemAdapter adapter;
	private OnDirectoryOrFileClickListener onDirectoryOrFileClickListener;
	private TextView textViewDirectory;
	private TextView textViewFile;
	private EditText editTextFile;
	private ComparatorChain<FileData> comparator;
	
	public FileListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		adapter = new FileSystemAdapter(context, R.layout.filemanager_row_icon);
		setAdapter(adapter);
		
		comparator = new ComparatorChain<FileData>();
		comparator.addComparator(new DirectoryFileComparator());
		comparator.addComparator(new NameComparator());
		
		setOnItemClickListener(this);
	}
	
	/**
	 * Initializes the FileListView <br>
	 * - sets the root path. If null it will be the root of the sdcard <br>
	 * - sets the default extentions if no customs are present <br>
	 * - updates the UI <br>
	 * <br>
	 * Call this method at the end of the onCreate()-Methode.
	 * 
	 * @param path root directory
	 */
	public void init(File path) {
		
		if(path != null) {
			adapter.setPath(path);
		} else {
			adapter.setPath(Environment.getExternalStorageDirectory());
		}
		
		if(!adapter.hasExtentions()) {
			setDefaultFileExtentions();
		}
		
		adapter.showFileSystem();
		
		updateUI(adapter.getPath());
	}
	
	/**
	 * Initializes the FileListView <br>
	 * - sets the root path to the root of the sdcard <br>
	 * - sets the default extentions if no customs are present <br>
	 * - updates the UI <br>
	 * <br>
	 * Call this method at the end of the onCreate()-Methode.
	 * 
	 * @param path root directory
	 */
	public void init() {
		init(null);
	}

	public FileSystemAdapter getAdapter() {
		return adapter;
	}
	
	public void setDefaultFileExtentions() {
		adapter.addExtention("folder", R.drawable.folder);
		adapter.addExtention("file", R.drawable.file);
		adapter.addExtention("jpg", R.drawable.image);
		adapter.addExtention("zip", R.drawable.packed);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		FileData item = (FileData) adapter.getItem(position);
		File file = adapter.move(item.name);
		
		if (file.isDirectory()) {
			if (file.canRead()) {
				adapter.showFileSystem();
				if(comparator != null) {
					adapter.sort(comparator);
				}
			} 
		}		
		updateUI(file);
	}
	
	private void updateUI(File file) {
		if(onDirectoryOrFileClickListener != null) {
			onDirectoryOrFileClickListener.onDirectoryOrFileClick(file);
		}
		
		if(textViewDirectory != null) {
			if(file.isDirectory()) {
				textViewDirectory.setText(file.getPath());
			} else {
				textViewDirectory.setText(file.getParent());
			}
		}
		if(textViewFile != null) {
			if(file.isFile()) {
				textViewFile.setText(file.getName());
			} else {
				textViewFile.setText("");
			}
		}
		if(editTextFile != null) {
			if(file.isFile()) {
				editTextFile.setText(file.getName());
			} else {
				editTextFile.setText("");
			}
		}
	}
	
	public void setTextViewDirectory(TextView textView) {
		this.textViewDirectory = textView;
	}
	
	public void setTextViewFile(TextView textView) {
		this.textViewFile = textView;
	}
	
	public void setEditTextFile(EditText editText) {
		this.editTextFile = editText;
	}
	
	public void setOnDirectoryOrFileClickListener(OnDirectoryOrFileClickListener listener) {
		onDirectoryOrFileClickListener = listener;
	}
	
	/**
     * Interface definition for a callback to be invoked when an directory or file has been clicked.
     */
    public interface OnDirectoryOrFileClickListener {

        /**
         * Callback method to be invoked when an directory or file has been clicked.
         */
        void onDirectoryOrFileClick(File file);
    }

    /**
     * Gives back a ComparatorChain that is preset with the following two Comparators: <br>
     * 1) DirectoryFileComparator <br>
     * 2) NameComparator <br><br>
     * To use different Comparators just call comparator.clear() and comparator.addComparator(yourcomparator)
     * 
     * @return
     */
	public ComparatorChain<FileData> getComparator() {
		return comparator;
	}

	public void setComparator(ComparatorChain<FileData> comparator) {
		this.comparator = comparator;
	}
	
}

