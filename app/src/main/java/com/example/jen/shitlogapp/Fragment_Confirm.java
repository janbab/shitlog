package com.example.jen.shitlogapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment_Confirm extends Fragment {
	public static NonSwipeableViewPager mViewPager;
	public Button next;
	public static int count=0;

	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

	public static final Fragment_Confirm newInstance() {		
		Fragment_Confirm f = new Fragment_Confirm();
		return f;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mViewPager = (NonSwipeableViewPager) getActivity().findViewById(R.id.viewpager);
		
		View v = inflater.inflate(R.layout.fragment_confirm, container, false);
		
		Button btn_next = (Button)v.findViewById(R.id.btn_next);

		btn_next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
                ((MainActivity) Fragment_Confirm.this.getActivity()).publishShit();

				mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1, true);
			}
		});

		return v;
	}

	private int getItem(int i) {
		return mViewPager.getCurrentItem() + i;
	}
}