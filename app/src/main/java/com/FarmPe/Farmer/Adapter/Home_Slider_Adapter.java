package com.FarmPe.Farmer.Adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.FarmPe.Farmer.R;

import java.util.List;

public class Home_Slider_Adapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        Activity activity;
       List<Integer> image_arraylist;
        private float x1,x2;
        static final int MIN_DISTANCE = 150;


        public Home_Slider_Adapter(Activity activity, List<Integer> image_arraylist) {
            this.activity = activity;
            this.image_arraylist = image_arraylist;
        }
        @SuppressLint("ClickableViewAccessibility")
        @Override



        public Object instantiateItem(ViewGroup container, final int position) {
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.sliding_layout, container, false);
            ImageView im_slider = (ImageView) view.findViewById(R.id.im_slider);
            im_slider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   // Toast.makeText(activity, "Clicked "+position, Toast.LENGTH_SHORT).show();
                }
            });
            //  System.out.println("imageeeeesizeee"+image_arraylist.get(2).getCat_id());

//            Glide.with(activity).load(URLs.MODULE_NAME_IMAGE +image_arraylist.get(position).getCat_id())
//                    .thumbnail(0.25f)
//                    .into(im_slider);
            container.addView(view);
            return view;
        }
        @Override
        public int getCount() {
            return image_arraylist.size();
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




