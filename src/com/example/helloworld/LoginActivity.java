package com.example.helloworld;

import java.io.IOException;

import com.example.entity.Server;
import com.example.entity.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import inputcells.SimpleTextInputCellFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends Activity {

	
	SimpleTextInputCellFragment fragInputCellAccount;
	SimpleTextInputCellFragment fragInputCellPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		fragInputCellAccount=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_account);
		fragInputCellPassword=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password);
		
		Button btn_register;
		btn_register=(Button) findViewById(R.id.btn_register);
		
		Button btn_login;
		btn_login=(Button) findViewById(R.id.btn_login);
		
		btn_register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goRegister();
			}
		});
		
		btn_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goLogin();
			}
		});
		
		
		
		findViewById(R.id.forgot_password).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goRecoverPassword();
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		fragInputCellAccount.setLabelText("µ«¬º√˚");
		fragInputCellAccount.setHintText("«Î ‰»Îµ«¬º√˚");
		
		fragInputCellPassword.setLabelText("√‹¬Î");
		fragInputCellPassword.setHintText("«Î ‰»Î√‹¬Î");
		fragInputCellPassword.setIsPassword(true);
		
		
		
		
		
	}
	
	public void goRegister(){
		Intent itnt=new Intent(LoginActivity.this,RegisterActivity.class);
		startActivity(itnt);
	}
	
	public void goLogin(){
		String account =fragInputCellAccount.getEditText();
	String password=fragInputCellPassword.getEditText();
	
	password=MD5.getMD5(password);
	
	MultipartBody.Builder requestBody=new MultipartBody.Builder()
			.addFormDataPart("account", account)
			.addFormDataPart("passwordHash", password);
			
//	OkHttpClient client=new OkHttpClient();
//	
//	Request request=new Request.Builder()
//			.url("http://172.27.0.40:8080/membercenter/api/login")
//			.method("post",null)
//			.post(requestBody.build())
//			.build();
	
	
		OkHttpClient client=Server.getShareClient();
		
		Request request=Server.requestBuilderWithApi("login")
				.method("post",null)
				.post(requestBody.build())
				.build();
		
	client.newCall(request).enqueue(new Callback() {
		
		@Override
		public void onResponse(final Call arg0, final Response arg1) throws IOException {
			// TODO Auto-generated method stub
			
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						ObjectMapper oMapper=new ObjectMapper();
						final User user=oMapper.readValue(arg1.body().string(), User.class);
						final String hello="Hello "+user.getName();
						LoginActivity.this.onResponse(arg0,hello);
					} catch (final Exception e) {
						// TODO Auto-generated catch block
								LoginActivity.this.onFailure(arg0,e);
					}
				}
			});
		}
		
		@Override
		public void onFailure(final Call arg0, final IOException arg1) {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					LoginActivity.this.onFailure(arg0,arg1);

				}
			});
		}
	});
		
	
	}
	
	void onFailure(Call arg0, Exception arg1) {
		// TODO Auto-generated method stub

		new AlertDialog.Builder(this)
		.setTitle("µ«¬º ß∞‹")
		.setMessage(arg1.getLocalizedMessage())
		.setPositiveButton("∫√",null)
		.show();
	}

	 void onResponse(Call arg0, String response) {
		// TODO Auto-generated method stub

		new AlertDialog.Builder(this)
		.setTitle("µ«¬º≥…π¶")
		.setMessage(response)
		//.setMessage(userName)
		.setPositiveButton("∫√",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent itnt=new Intent(LoginActivity.this,HelloWorldActivity.class);
				startActivity(itnt);
				finish();
			}
		}).show();
				
	}

	public void goRecoverPassword(){
		Intent itnt=new Intent(this,PasswordRecoverActivity.class);
		startActivity(itnt);
	}
	
	
}

