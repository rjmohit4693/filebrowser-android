package de.so.filebrowser;

import java.io.File;

import org.so.filebrowser.FileData;
import org.so.filebrowser.FileListView;
import org.so.filebrowser.FileSystemAdapter;
import org.so.filebrowser.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class CustomFilebrowserActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.custom_filebrowser);
		
		FileListView fileList = (FileListView) findViewById(R.id.fileListView);
		fileList.setOnDirectoryOrFileClickListener(new FileListView.OnDirectoryOrFileClickListener() {
			public void onDirectoryOrFileClick(File file) {
				System.out.println(file);
			}
		});
		fileList.setRowView(R.layout.filemanager_row_icon_size);
		fileList.setOnGetView(new FileSystemAdapter.OnGetView() {
			@Override
			public void onGetView(int position, View convertView, ViewGroup paren, FileData item) {
				if(convertView != null) {
					TextView textViewSize = (TextView) convertView.findViewById(R.id.textSize);
					if(!item.directory) {
						textViewSize.setText(Long.toString(item.file.length()));
					} else {
						textViewSize.setText("");
					}
				} 
			}
		});
		
		fileList.getAdapter().addExtention("folder", R.drawable.folder);
		fileList.getAdapter().addExtention("file", R.drawable.file);
		fileList.getAdapter().addExtention("jpg", R.drawable.jpg);
		fileList.getAdapter().addExtention("zip", R.drawable.zip);
		
		
		TextView textViewDirectory = (TextView) findViewById(R.id.textViewDirectory);
		fileList.setTextViewDirectory(textViewDirectory);

		TextView textViewFile = (TextView) findViewById(R.id.textViewFile);
		fileList.setTextViewFile(textViewFile);

		EditText editTextFile = (EditText) findViewById(R.id.editTextFile);
		fileList.setEditTextFile(editTextFile);

//		File externalFilesDir = getExternalFilesDir(null);
		fileList.init();
	}
}
