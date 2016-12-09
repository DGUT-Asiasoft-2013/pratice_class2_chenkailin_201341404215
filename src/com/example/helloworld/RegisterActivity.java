package com.example.helloworld;

import java.io.IOException;
import java.security.PublicKey;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage.SubmitPdu;
import android.util.Log;
import android.view.View;
import inputcells.PictureInputCellFragment;
import inputcells.SimpleTextInputCellFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends Activity {

	SimpleTextInputCellFragment fragInputCellAccount;
	SimpleTextInputCellFragment fragInputCellName;
	SimpleTextInputCellFragment fragInputEmailAdress;
	SimpleTextInputCellFragment fragInputCellPassword;
	SimpleTextInputCellFragment fragInputCellPasswordRepeat;
	PictureInputCellFragment fragInputAvatar;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub


		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		fragInputCellAccount=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_account);
		fragInputCellName=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_name);
		fragInputEmailAdress=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_email);
		fragInputCellPassword=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password);
		fragInputCellPasswordRepeat=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password_repeat);

		fragInputAvatar=(PictureInputCellFragment) getFragmentManager().findFragmentById(R.id.imagei);
		findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				submit();
				//	Log.d("a", "aaaaaaaa");
			}
		});

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		fragInputCellAccount.setLabelText("µ«¬º√˚");
		fragInputCellAccount.setHintText("«Î ‰»Îµ«¬º√˚");

		fragInputCellName.setLabelText("Í«≥∆");
		fragInputCellName.setHintText("«Î ‰»ÎÍ«≥∆");

		fragInputEmailAdress.setLabelText("” œ‰");
		fragInputEmailAdress.setHintText("«Î ‰»Î” œ‰");

		fragInputCellPassword.setLabelText("√‹¬Î");
		fragInputCellPassword.setHintText("«Î ‰»Î√‹¬Î");
		fragInputCellPassword.setIsPassword(true);

		fragInputCellPasswordRepeat.setLabelText("÷ÿ∏¥√‹¬Î");
		fragInputCellPasswordRepeat.setHintText("«Î‘Ÿ¥Œ ‰»Î√‹¬Î");
		fragInputCellPasswordRepeat.setIsPassword(true);
	}


	public void submit(){
		String password= fragInputCellPassword.getEditText();
		String passwordReoeat= fragInputCellPasswordRepeat.getEditText();

		if(!password.equals(passwordReoeat)){


			return;
		}
		
		password=MD5.getMD5(password);
		
		String account= fragInputCellAccount.getEditText();
		String name= fragInputCellName.getEditText();
		String email= fragInputEmailAdress.getEditText();

		


		MultipartBody.Builder requestBody=new MultipartBody.Builder()
				.addFormDataPart("account",account)
				.addFormDataPart("name", name)
				.addFormDataPart("passwordHash", password)
				.addFormDataPart("email", email);

if(fragInputAvatar.getPngData()!=null){
	requestBody.addFormDataPart("avatar","avatar",
			RequestBody.create(MediaType.parse("image/png"),
					fragInputAvatar.getPngData()));
		}
		//		MultipartBody requestBodyBulider=new MultipartBody.Builder()
		//				.setType(MultipartBody.FORM)
		//				.addFormDataPart("account", account)
		//				.addFormDataPart("name", name)
		//				.addFormDataPart("email", email)
		//				.addFormDataPart("passwordHash", password)
		//				.build();
		OkHttpClient client=new OkHttpClient();


		//Request request=new Request.Builder().url("http://172.27.0.40:8080/membercenter/api/hello").method("GET", null).build();		
		Request request=new Request.Builder()
				.url("http://172.27.0.40:8080/membercenter/api/register")
				.method("post",null).post(requestBody.build())
				.build();

		//		Request request=new Request.Builder()
		//				.url("http://172.27.0.40:8080/membercenter/api/register")
		//				.method("post", null)
		//				.post(requestBodyBulider)
		//				.build();

		final ProgressDialog progressDialog=new ProgressDialog(RegisterActivity.this);
		progressDialog.setMessage("«Î…‘∫Ú");
		progressDialog.setCancelable(true);
		progressDialog.setCanceledOnTouchOutside(false);


		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(final Call arg0, final Response arg1) throws IOException {
				// TODO Auto-generated method stub
				runOnUiThread( new Runnable() {
					public void run() {
						try {
							progressDialog.dismiss();
							RegisterActivity.this.onResponse(arg0, arg1.body().string());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
						progressDialog.dismiss();
						RegisterActivity.this.onFailure(arg0,arg1);

					}
				});
			}
		});




	}

	void onFailure(Call arg0, IOException arg1) {
		// TODO Auto-generated method stub

		new AlertDialog.Builder(this)
		.setTitle("«Î«Û ß∞‹")
		.setMessage(arg1.getLocalizedMessage())
		.setPositiveButton("∫√",null)
		.show();

	}

	void onResponse(Call arg0, String response) {
		new AlertDialog.Builder(this)
		.setTitle("◊¢≤·≥…π¶")
		.setMessage(response)
		.setPositiveButton("∫√", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
			}
		})
		.show();
	}

}
