package com.example.jen.shitlogapp;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Sick extends Fragment {
	public static NonSwipeableViewPager mViewPager;
	public Button next;
	public static int count=0;

	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    public static String[] painColors = {"#808080", "#FF8400", "#E8C70C", "#E60000"};
    public static String[] sickColors = {"#808080", "#FF8400", "#E8C70C", "#E60000"};

    public static final Fragment_Sick newInstance() {
		Fragment_Sick f = new Fragment_Sick();
		return f;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mViewPager = (NonSwipeableViewPager) getActivity().findViewById(R.id.viewpager);

		View v = inflater.inflate(R.layout.fragment_sick, container, false);

		Button btn_next = (Button)v.findViewById(R.id.btn_next);

		btn_next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1, true);
			}
		});

        LinearLayout layout = (LinearLayout)v.findViewById(R.id.sickLayout);
        List<View> allPainButtons = findAllViewsWithTag(layout, "painButton");
        for (int i=0; i < allPainButtons.size(); i++) {
            final CheckBox painButton = (CheckBox)allPainButtons.get(i);
            final int index = i;

            painButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(painButton.isChecked()){
                        System.out.println("Checked " + painButton.getBackground());
                        painButton.getBackground().setColorFilter(Color.parseColor(painColors[index]), PorterDuff.Mode.SRC_IN);
                    } else {
                        System.out.println("Unchecked");
                        painButton.getBackground().setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_IN);
                    }
                }
            });
        }

        return v;
}



    /**
     * Get all the views which matches the given Tag recursively
     * @param root parent view. for e.g. Layouts
     * @param tag tag to look for
     * @return List of views
     */
    public static List<View> findAllViewsWithTag(ViewGroup root, Object tag){
        List<View> allViews = new ArrayList<View>();

        final int childCount = root.getChildCount();
        for(int i=0; i<childCount; i++){
            final View childView = root.getChildAt(i);

            if(childView instanceof ViewGroup){
                allViews.addAll(findAllViewsWithTag((ViewGroup)childView, tag));
            }
            else{
                final Object tagView = childView.getTag();
                if(tagView != null && tagView.equals(tag))
                    allViews.add(childView);
            }
        }

        return allViews;
    }





	private int getItem(int i) {
		return mViewPager.getCurrentItem() + i;
	}
}