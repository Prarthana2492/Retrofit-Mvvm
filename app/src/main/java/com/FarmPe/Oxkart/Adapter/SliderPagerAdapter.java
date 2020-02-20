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
import android.widget.TextView;


import com.FarmPe.Oxkart.Bean.ListBean2;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SliderPagerAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    Activity activity;
    ArrayList<ListBean2> slider_text;
    RecyclerView recyclerView;
    TextView farmer_text,farmer_descr,how_it_wrks,what_we_sell,register,register_details;
    public static JSONObject lngObject;
    SessionManager sessionManager;

    public static int morecount;


    public SliderPagerAdapter(Activity activity, ArrayList<ListBean2> slider_text) {
        this.activity = activity;
        this.slider_text = slider_text;



        sessionManager = new SessionManager(activity);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = null;

        switch (position){

            case 0: view=  layoutInflater.inflate(R.layout.sliderlogofirst, container, false);

                farmer_text = view.findViewById(R.id.farmer_text);
                farmer_descr = view.findViewById(R.id.farmer_descr);


                try {


                    lngObject = new JSONObject(sessionManager.getRegId("language"));

                    System.out.println("llllllllllllkkkkkkkkkkkkkkk" + lngObject.getString("EnterPhoneNo"));

                    farmer_text.setText(lngObject.getString("MadeforFarmingCommunity"));
                    farmer_descr.setText(lngObject.getString("Theconfluenceoffarmersandfairtrade"));

                    //  pass.setHint(lngObject.getString("Password"));
                    //  remember_me.setText(lngObject.getString("RememberMe"));





                } catch (JSONException e) {
                    e.printStackTrace();
                }



            break;

            case 1: view=  layoutInflater.inflate(R.layout.how_it_works_slider, container, false);

                how_it_wrks = view.findViewById(R.id.how_it_wrks);
                register = view.findViewById(R.id.register);
                register_details = view.findViewById(R.id.register_details);


                try {

                    lngObject = new JSONObject(sessionManager.getRegId("language"));

                    System.out.println("llllllllllllkkkkkkkkkkkkkkk" + lngObject.getString("EnterPhoneNo"));

                    how_it_wrks.setText(lngObject.getString("HowitWorks"));
                    register.setText("1." + lngObject.getString("Register"));
                    register_details.setText(lngObject.getString("Registerbyenteringtherequireddetails"));

                   // farmer_descr.setText(lngObject.getString("Theconfluenceoffarmersandfairtrade"));

                    //  pass.setHint(lngObject.getString("Password"));
                    //  remember_me.setText(lngObject.getString("RememberMe"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            break;

            case 2: view=  layoutInflater.inflate(R.layout.whatwesell_slider, container, false);

                what_we_sell = view.findViewById(R.id.what_we_sell);



                try {


                    lngObject = new JSONObject(sessionManager.getRegId("language"));

                    System.out.println("llllllllllllkkkkkkkkkkkkkkk" + lngObject.getString("EnterPhoneNo"));

                    farmer_text.setText(lngObject.getString("MadeforFarmingCommunity"));
                    farmer_descr.setText(lngObject.getString("Theconfluenceoffarmersandfairtrade"));

                    //  pass.setHint(lngObject.getString("Password"));
                    //  remember_me.setText(lngObject.getString("RememberMe"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }



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
