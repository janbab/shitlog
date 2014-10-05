package com.example.jen.shitlogapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    int[] flag;
    String[] description;
    LayoutInflater inflater;

    Fragment fragment;

    ImageView[] flagImages = new ImageView[7];

    public ViewPagerAdapter(Context context, int[] flag, String[] description) {
        this.context = context;
        this.flag = flag;
        this.description = description;
    }

    @Override
    public int getCount() {
        return flag.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgflag;


        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,false);

//        itemView.setTag(position);
//        System.out.println(itemView.getTag());
        System.out.println("itemView pos = " + position);

        imgflag = (ImageView) itemView.findViewById(R.id.flag);
        imgflag.setImageResource(flag[position]);

        flagImages[position] = imgflag;

        TextView text_bristol = (TextView)itemView.findViewById(R.id.text_bristol);
        text_bristol.setText(description[position]);

        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }

    public int getItem(int position) {
        return flag[position];
    }


    public ImageView getImageView(int position) {
        return flagImages[position];
    }
}