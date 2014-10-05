package com.example.jen.shitlogapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {
	MyPageAdapter pageAdapter;
	NonSwipeableViewPager pager;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		pager = new NonSwipeableViewPager(this);
		pager.setId(R.id.viewpager);
		setContentView(pager);

		List<Fragment> fragments = getFragments();

		pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
		pager.setAdapter(pageAdapter);
	}

	private List<Fragment> getFragments(){
		List<Fragment> fList = new ArrayList<Fragment>();

		fList.add(Fragment_Main.newInstance());
		fList.add(Fragment_Time.newInstance());
		fList.add(Fragment_Appearance.newInstance());
		fList.add(Fragment_Sick.newInstance());
		fList.add(Fragment_Confirm.newInstance());

		return fList;
	}

	private class MyPageAdapter extends FragmentPagerAdapter {
		private List<Fragment> fragments;

		public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}
		@Override
		public Fragment getItem(int position) {
			return this.fragments.get(position);
		}

		@Override
		public int getCount() {
			return this.fragments.size();
		}
	}
	
	
	@Override
	public void onBackPressed() {
		if (pager.getCurrentItem() > 1) {
			pager.setCurrentItem(pager.getCurrentItem()-1);
		} else {
			new AlertDialog.Builder(this)
	        .setTitle("Really Exit?")
	        .setMessage("Are you sure you want to exit?")
	        .setNegativeButton(android.R.string.no, null)
	        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        	@Override
	            public void onClick(DialogInterface arg0, int arg1) {
	            }
	        }).create().show();
		}
	}
}