package com.example.jen.shitlogapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_Image extends Fragment {

    public static Fragment_Image newInstance(String title, int image) {
        Fragment_Image pageFragment = new Fragment_Image();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putInt("image", image);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.viewpager_item, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text_bristol);
        textView.setText("titleeeeeeeeeeee");
//        textView.setTextColor("#000000");
        return view;
    }
}
   