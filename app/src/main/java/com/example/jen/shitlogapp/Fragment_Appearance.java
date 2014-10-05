package com.example.jen.shitlogapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Fragment_Appearance extends Fragment
{
    public static NonSwipeableViewPager mViewPager;
    public static ViewPager viewPager;
    public Button next;
    public static int count = 0;
    public String number = "1";
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final int[] flag = {
            R.drawable.bristol1gray, R.drawable.bristol2gray, R.drawable.bristol3gray,
            R.drawable.bristol4gray, R.drawable.bristol5gray, R.drawable.bristol6gray, R.drawable.bristol7gray
    };

    public static final String[] description = {
            "TYPE 1\nSeparate hard lumps that are difficult to pass",
            "TYPE 2\nSausage-shaped and lumpy",
            "TYPE 3\nLike a sausage but with a cracked surface",
            "TYPE 4\nSmooth, soft and sausage-like",
            "TYPE 5\nSoft blobs with clean edges",
            "TYPE 6\nFluffy, mushy pieces with ragged edges",
            "TYPE 7\nEntirely liquid"
    };

    public static final String[] colors = {
            "#6C5035", //dark brown
            "#E0A366", //light brown
            "#A3C266", //green
            "#140A00", //black
            "#FFE066", //yellow
            "#CC0000", //red
    };

    public static final Fragment_Appearance newInstance()
    {
        Fragment_Appearance f = new Fragment_Appearance();
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_appearance, container, false);

        mViewPager = (NonSwipeableViewPager)getActivity().findViewById(R.id.viewpager);

        viewPager = (ViewPager)v.findViewById(R.id.pager);

        final ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getApplicationContext(), flag, description);

        viewPager.setAdapter(adapter);

        RadioGroup everyColorButton = (RadioGroup)v.findViewById(R.id.colorButtons);
        for (int i = 0; i < everyColorButton.getChildCount(); i++) {
            View o = everyColorButton.getChildAt(i);
            o.getBackground().setAlpha(160);
        }

        Button btn_next = (Button)v.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Appearance.mViewPager.setCurrentItem(Fragment_Appearance.mViewPager.getCurrentItem() + 1, true);
            }
        });

        RadioButton color1 = (RadioButton)v.findViewById(R.id.color1);
        color1.setChecked(true);

        RadioButton size1 = (RadioButton)v.findViewById(R.id.size1);
        size1.setChecked(true);


//        ImageView currentImage = adapter.getImageView(viewPager.getCurrentItem());

        int originalHeight = this.getResources().getDrawable(R.drawable.bristol1gray).getIntrinsicHeight();
        int originalWidth = this.getResources().getDrawable(R.drawable.bristol1gray).getIntrinsicWidth();

        Resources r = getResources();
        float tenDP = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, r.getDisplayMetrics());

        final int[] height = new int[] {
                originalHeight-((int)tenDP*4), originalHeight-((int)tenDP*2), originalHeight, originalHeight+((int)tenDP*3)
        };
        final int[] width = new int[] {
                originalWidth-((int)tenDP*3), originalWidth-((int)tenDP*2), originalWidth, originalWidth+(int)tenDP
        };


//        currentImage.setColorFilter(Color.parseColor(colors[1]), PorterDuff.Mode.MULTIPLY);
//
//        currentImage.requestLayout();
//        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(width[1], height[1]);
//        layoutParams.gravity = Gravity.CENTER;
//        layoutParams.width = width[1];
//        layoutParams.height = height[1];
//        currentImage.setLayoutParams(layoutParams);


        Drawable d = this.getResources().getDrawable(R.drawable.bristol1gray);
        d.getIntrinsicHeight();

//      ImageView startingImage = adapter.getImageView(R.drawable.bristol1gray);




        RadioGroup colorButtons = (RadioGroup)v.findViewById(R.id.colorButtons);
        colorButtons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                for (int i = 0; i < group.getChildCount(); i++) {
                    View o = group.getChildAt(i);
                    o.getBackground().setAlpha(160);
                }

                checkedRadioButton.getBackground().setAlpha(255);

                number = getResources().getResourceEntryName(checkedId).split("color")[1];
                for (int i=0; i < flag.length; i++) {
                    ImageView bbb = adapter.getImageView(viewPager.getCurrentItem());
                    bbb.setColorFilter(Color.parseColor(colors[java.lang.Integer.parseInt(number)]), PorterDuff.Mode.MULTIPLY);
                }
            }
        });

//        Typeface font = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "flaticon.ttf" );
//        Button button = (Button)v.findViewById( R.id.like );
//        button.setTypeface(font);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) { }

            @Override
            public void onPageSelected(int i) {
                ImageView bbb = adapter.getImageView(viewPager.getCurrentItem());
                bbb.setColorFilter(Color.parseColor(colors[java.lang.Integer.parseInt(number)]), PorterDuff.Mode.MULTIPLY);

            }

            @Override
            public void onPageScrollStateChanged(int i) {  }
        });


        RadioGroup sizeButtons = (RadioGroup)v.findViewById(R.id.sizeButtons);
        sizeButtons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String number = getResources().getResourceEntryName(checkedId).split("size")[1];
                int num = java.lang.Integer.parseInt(number);
                ImageView bbb = adapter.getImageView(Fragment_Appearance.viewPager.getCurrentItem());

                bbb.requestLayout();
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(width[num], height[num]);
                layoutParams.gravity = Gravity.CENTER;
                layoutParams.width = width[num];
                layoutParams.height = height[num];
                bbb.setLayoutParams(layoutParams);
            }
        });

        return v;
    }
}