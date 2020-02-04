package com.FarmPe.Oxkart.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.FarmPe.Oxkart.Bean.ListBean2;
import com.FarmPe.Oxkart.R;

import java.util.ArrayList;


public class SliderPagerAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    Activity activity;
    ArrayList<ListBean2> slider_text;
    RecyclerView recyclerView;

    public static int morecount;


    public SliderPagerAdapter(Activity activity, ArrayList<ListBean2> slider_text) {
        this.activity = activity;
        this.slider_text = slider_text;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = null;

        switch (position){

            case 0: view=  layoutInflater.inflate(R.layout.sliderlogofirst, container, false);
            break;

            case 1: view=  layoutInflater.inflate(R.layout.how_it_works_slider, container, false);
            break;

            case 2: view=  layoutInflater.inflate(R.layout.whatwesell_slider, container, false);

              CartSliderAdapter madapter = new CartSliderAdapter(activity, slider_text);
                recyclerView=view.findViewById(R.id.rv);
                System.out.println("size "+slider_text.size());
                recyclerView.setHasFixedSize(true);
                GridLayoutManager mLayoutManager5 = new GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(mLayoutManager5);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                System.out.println("sizeeeeeee"+slider_text.size());
                madapter = new CartSliderAdapter(activity, slider_text);
                recyclerView.setAdapter(madapter);

        }

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return 3 ;

    }


    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

}
