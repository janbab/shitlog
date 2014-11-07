package com.example.jen.shitlogapp;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Fragment_Time extends Fragment {
    public static NonSwipeableViewPager mViewPager;
    public Button next;
    public static int count=0;

    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";


    public static final Fragment_Time newInstance() {
        Fragment_Time f = new Fragment_Time();
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_time, container, false);
        mViewPager = (NonSwipeableViewPager) getActivity().findViewById(R.id.viewpager);

        Button btn_next = (Button)v.findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final TimePicker timePicker = (TimePicker) Fragment_Time.this.getView().findViewById(R.id.timePicker1);
                Calendar c = Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                c.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                ((MainActivity) Fragment_Time.this.getActivity()).getCurrentShit().setDateTime(c);
                System.out.println(((MainActivity) Fragment_Time.this.getActivity()).getCurrentShit().toString());

                mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1, true);
            }
        });


        final TimePicker timePicker = (TimePicker)v.findViewById(R.id.timePicker1);
        final Calendar c = Calendar.getInstance();
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (hourOfDay >= c.get(Calendar.HOUR_OF_DAY) && minute >= c.get(Calendar.MINUTE)) {
                    timePicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
                    timePicker.setCurrentMinute(c.get(Calendar.MINUTE));
                }
            }
        });


        return v;
    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }
}