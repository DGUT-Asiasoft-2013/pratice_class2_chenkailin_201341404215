package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FeedContentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_feedcontent);
		
		String text=getIntent().getStringExtra("text");
		
		TextView showText;
		showText=(TextView) findViewById(R.id.show_text);
		showText.setText(text);
	
	}
}
