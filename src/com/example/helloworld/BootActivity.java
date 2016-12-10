package com.example.helloworld;

import java.io.IOException;

import com.example.entity.Server;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

		//	Handler handler=new Handler();
		//	handler.postDelayed(new Runnable(){
		//		
		//		public void run(){
		//			startLoginActivity();
		//		}
		//	},1000);
		//	


		//		OkHttpClient clinet=new OkHttpClient();
		//		Request request=new Request.Builder().url("http://172.27.0.40:8080/membercenter/api/hello").method("GET", null).build();	
		
		OkHttpClient client=Server.getShareClient();
		Request request=Server.requestBuilderWithApi("hello")
				.method("get", null)
				.build();


		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse( Call arg0, final Response arg1) throws IOException {
				// TODO Auto-generated method stub
				BootActivity.this.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Toast.makeText(BootActivity.this,arg1.body().string(),Toast.LENGTH_SHORT).show();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						startLoginActivity();
					}
				});
			}

			@Override
			public void onFailure(Call arg0, final IOException arg1) {
				// TODO Auto-generated method stub
				BootActivity.this.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Toast.makeText(BootActivity.this,arg1.getLocalizedMessage(),Toast.LENGTH_SHORT).show();;	
					}
				});
			}
		});


	}


	void startLoginActivity(){
		Intent itnt=new Intent(this,LoginActivity.class);
		//Intent itnt=new Intent(this,RegisterActivity.class);
		startActivity(itnt);
		finish();
	}
}
