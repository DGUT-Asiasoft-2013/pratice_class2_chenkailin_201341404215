package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import inputcells.SimpleTextInputCellFragment;

public class RegisterActivity extends Activity {

	SimpleTextInputCellFragment fragInputCellAccount;
	SimpleTextInputCellFragment fragInputCellPassword;
	SimpleTextInputCellFragment fragInputCellPasswordRepeat;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		fragInputCellAccount=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_account);
		fragInputCellPassword=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password);
		fragInputCellPasswordRepeat=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password_repeat);


	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		fragInputCellAccount.setLabelText("µÇÂ¼Ãû");
		fragInputCellAccount.setHintText("ÇëÊäÈëµÇÂ¼Ãû");
		fragInputCellPassword.setLabelText("ÃÜÂë");
		fragInputCellPassword.setHintText("ÇëÊäÈëÃÜÂë");
		fragInputCellPasswordRepeat.setLabelText("ÖØ¸´ÃÜÂë");
		fragInputCellPasswordRepeat.setHintText("ÇëÔÙ´ÎÊäÈëÃÜÂë");
	}
}
