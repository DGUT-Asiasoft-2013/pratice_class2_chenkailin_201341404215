package page;

import java.util.Random;

import com.example.helloworld.FeedContentActivity;
import com.example.helloworld.R;

import android.R.string;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FeedListFragment extends Fragment {

	View view;
	ListView listView;
	
String  date[];
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view==null){
			view = inflater.inflate(R.layout.fragment_page_feed_list, null);
			
			listView = (ListView) view.findViewById(R.id.list);
			listView.setAdapter(listAdapter);
		}
		
		
		Random random=new Random();
		date=new String[10+random.nextInt()%5];
		for(int i=0;i<date.length;i++)
		{
		date[i]=random.nextInt()+"";
		}
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				onItemClicked(position);
			}
		});
		
		
		

		return view;
	}
	
	
	
	BaseAdapter listAdapter = new BaseAdapter() {
		
		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			
			if(convertView==null){
				LayoutInflater inflater = LayoutInflater.from(parent.getContext());
				view = inflater.inflate(R.layout.my_list_items, null);	
			}else{
				view = convertView;
			}
			
			TextView text1 = (TextView) view.findViewById(R.id.my_list_feeds);
			text1.setText(date[position]);
			
			return view;
		}
		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return  date==null?0:date.length;
		}
	};
	
	public void onItemClicked(int position){
		
		String text=date[position];
		
		Intent intent=new Intent(getActivity(),FeedContentActivity.class);
		
		intent.putExtra("text", text);
	
		startActivity(intent);
		
	}
}
