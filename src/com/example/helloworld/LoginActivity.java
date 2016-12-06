package com.example.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import inputcells.SimpleTextInputCellFragment;

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
		Intent itnt=new Intent(LoginActivity.this,HelloWorldActivity.class);
		startActivity(itnt);
	}
	
	
}

