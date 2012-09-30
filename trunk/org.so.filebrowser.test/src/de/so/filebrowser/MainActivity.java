package de.so.filebrowser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		Button buttonStandard = (Button) findViewById(R.id.button_standard);
		buttonStandard.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, FilebrowserActivity.class);
				startActivity(intent);
			}
		});
		
		Button buttonCustom = (Button) findViewById(R.id.button_custom);
		buttonCustom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, CustomFilebrowserActivity.class);
				startActivity(intent);
			}
		});
	}

}
