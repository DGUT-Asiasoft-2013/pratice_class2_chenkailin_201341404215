package com.example.helloworld;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import fragments.MainTabbarFragment;
import fragments.MainTabbarFragment.OnTabSelectedListener;
import page.FeedListFragment;
import page.MyProfileFragment;
import page.NoteListFragment;
import page.SearchPageFragment;

public class HelloWorldActivity extends Activity {

	FeedListFragment contentFeedList = new FeedListFragment();
	NoteListFragment contentNoteList = new NoteListFragment();
	SearchPageFragment contentSearchPage = new SearchPageFragment();
	MyProfileFragment contentMyProfile = new MyProfileFragment();

	MainTabbarFragment tabbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_helloworld);

		tabbar = (MainTabbarFragment) getFragmentManager().findFragmentById(R.id.frag_tabbar);
		tabbar.setOnTabSelectedListener(new OnTabSelectedListener() {

			@Override
			public void onTabSelected(int index) {
				changeContentFragment(index);
			}
		});
		
		Button btnNew=(Button) findViewById(R.id.btn_new);
		btnNew.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pushNewArtical();
				
			}
		});
		
		
		
	}

	@Override
	protected void onResume() {
		super.onResume();

		tabbar.setSelectedItem(0);
	}

	void changeContentFragment(int index){
		Fragment newFrag = null;

		switch (index) {
		case 0: newFrag = contentFeedList; break;
		case 1: newFrag = contentNoteList; break;
		case 2: newFrag = contentSearchPage; break;
		case 3: newFrag = contentMyProfile; break;

		default:break;
		}

		if(newFrag==null) return;

		getFragmentManager()
		.beginTransaction()
		.replace(R.id.content, newFrag)
		.commit();
	}
	
	public void pushNewArtical(){
		
		Intent intent=new Intent(this,NewArticalActivity.class);
		
		startActivity(intent);
		
		overridePendingTransition(R.anim.slide_in_buttom,0);
		
	}
	

}

