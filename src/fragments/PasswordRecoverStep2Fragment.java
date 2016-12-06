package fragments;

import com.example.helloworld.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import inputcells.SimpleTextInputCellFragment;

public class PasswordRecoverStep2Fragment extends Fragment {

	View view;
	SimpleTextInputCellFragment fragInputCellPassword;
	SimpleTextInputCellFragment fragInputCellPasswordRepeat;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	fragInputCellPassword=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.forgot_password);
	fragInputCellPasswordRepeat=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password_repeat);
		if(view==null){
			view=inflater.inflate(R.layout.fragment_password_recover_step2,null);
		}
		return view;
	}
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		fragInputCellPassword.setLabelText("新密码");
		fragInputCellPassword.setHintText("请输入新密码");
		fragInputCellPassword.setIsPassword(true);
		
		fragInputCellPasswordRepeat.setLabelText("新密码");
		fragInputCellPasswordRepeat.setHintText("请再次输入新密码");
		fragInputCellPasswordRepeat.setIsPassword(true);
		
		
		
		
	}
}
