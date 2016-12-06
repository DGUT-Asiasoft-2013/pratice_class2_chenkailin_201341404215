package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import inputcells.SimpleTextInputCellFragment;

public class RegisterActivity extends Activity {

	SimpleTextInputCellFragment fragInputCellAccount;
	SimpleTextInputCellFragment fragInputEmailAdress;
	SimpleTextInputCellFragment fragInputCellPassword;
	SimpleTextInputCellFragment fragInputCellPasswordRepeat;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		fragInputCellAccount=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_account);
		fragInputEmailAdress=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_email);
		
		fragInputCellPassword=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password);
		fragInputCellPasswordRepeat=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password_repeat);


	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		fragInputCellAccount.setLabelText("µ«¬º√˚");
		fragInputCellAccount.setHintText("«Î ‰»Îµ«¬º√˚");
		
		fragInputEmailAdress.setLabelText("” œ‰");
		fragInputEmailAdress.setHintText("«Î ‰»Î” œ‰");
		
		fragInputCellPassword.setLabelText("√‹¬Î");
		fragInputCellPassword.setHintText("«Î ‰»Î√‹¬Î");
		fragInputCellPassword.setIsPassword(true);
		
		fragInputCellPasswordRepeat.setLabelText("÷ÿ∏¥√‹¬Î");
		fragInputCellPasswordRepeat.setHintText("«Î‘Ÿ¥Œ ‰»Î√‹¬Î");
		fragInputCellPasswordRepeat.setIsPassword(true);
	}
}
