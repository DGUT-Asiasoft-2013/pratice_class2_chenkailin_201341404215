package page;

import java.io.IOException;

import com.example.entity.Server;
import com.example.entity.User;
import com.example.helloworld.R;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyProfileFragment extends Fragment {
	View view;
	TextView textView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(view==null){

			view=inflater.inflate(R.layout.fragment_page_my_profile,null);
		}
textView=(TextView) view.findViewById(R.id.me);
		return view;
	}
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		OkHttpClient client=Server.getShareClient();
		
		Request request=Server.requestBuilderWithApi("me")
				.method("get",null)
				.build();
		
		
		client.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(final Call arg0, Response arg1) throws IOException {
				// TODO Auto-generated method stub
				
			final User user=new ObjectMapper().readValue(arg1.body().string(), User.class);
				
			getActivity().runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					MyProfileFragment.this.onResponse(arg0,user.getName());
				
				}
			});
			}
			
			@Override
			public void onFailure(Call arg0, IOException arg1) {
				// TODO Auto-generated method stub
				
			}
		});
	}


	 void onResponse(Call arg0, String name) {
		// TODO Auto-generated method stub
		textView.setText(name);
	}
}
