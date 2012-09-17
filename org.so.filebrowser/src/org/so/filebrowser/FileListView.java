package org.so.filebrowser;


import java.io.File;

import org.so.filebrowser.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class FileListView extends ListView implements AdapterView.OnItemClickListener {

	private FileSystemAdapter adapter;
	private OnDirectoryOrFileClickListener onDirectoryOrFileClickListener;
	private TextView textViewDirectory;
	private TextView textViewFile;
	private EditText editTextFile;
	
	public FileListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		adapter = new FileSystemAdapter(context, R.layout.filemanager_row_icon);
		setAdapter(adapter);
		
		setOnItemClickListener(this);
	}
	
	public void init() {
		if(!adapter.hasExtentions()) {
			setDefaultFileExtentions();
		}
		
		adapter.showFileSystem();
		
		updateUI(adapter.getPath());
	}

	public FileSystemAdapter getAdapter() {
		return adapter;
	}
	
	public void setDefaultFileExtentions() {
		adapter.addExtention("jpg", R.drawable.iconimage);
		adapter.addExtention("zip", R.drawable.archive);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		FileData item = (FileData) adapter.getItem(position);
		File file = adapter.move(item.name);
		
		if (file.isDirectory()) {
			if (file.canRead()) {
				adapter.showFileSystem();
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
	
}

