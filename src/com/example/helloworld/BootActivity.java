package com.example.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.sax.StartElementListener;
import android.view.Menu;
import android.view.MenuItem;

public class BootActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_boot);
		
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	Handler handler=new Handler();
	handler.postDelayed(new Runnable(){
		
		public void run(){
			startLoginActivity();
		}
	},1000);
	
	
	
	}
	
	
	void startLoginActivity(){
		Intent itnt=new Intent(this,LoginActivity.class);
		//Intent itnt=new Intent(this,RegisterActivity.class);
		startActivity(itnt);
		//finish();
	}
}
