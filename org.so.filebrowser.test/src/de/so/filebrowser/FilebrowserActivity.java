package de.so.filebrowser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.so.filebrowser.FileListView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class FilebrowserActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		setContentView(R.layout.activity_main);

		FileListView fileList = (FileListView) findViewById(R.id.fileListView);
		fileList.setOnDirectoryOrFileClickListener(new FileListView.OnDirectoryOrFileClickListener() {
			public void onDirectoryOrFileClick(File file) {
				System.out.println(file);
			}
		});

		TextView textViewDirectory = (TextView) findViewById(R.id.textViewDirectory);
		fileList.setTextViewDirectory(textViewDirectory);

		TextView textViewFile = (TextView) findViewById(R.id.textViewFile);
		fileList.setTextViewFile(textViewFile);

		EditText editTextFile = (EditText) findViewById(R.id.editTextFile);
		fileList.setEditTextFile(editTextFile);

//		File externalFilesDir = getExternalFilesDir(null);
		fileList.init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void readStream(InputStream in) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
