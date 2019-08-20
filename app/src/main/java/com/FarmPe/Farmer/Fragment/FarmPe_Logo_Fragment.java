package com.FarmPe.Farmer.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;



import com.FarmPe.Farmer.Adapter.HomePage_Adapter;
import com.FarmPe.Farmer.Bean.AddTractorBean1;
import com.FarmPe.Farmer.Bean.AddTractorBean2;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Volly_class.Crop_Post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FarmPe_Logo_Fragment extends Fragment {
    Fragment selectedFragment;
    public static LinearLayout backfeed;

LinearLayout linearLayout,no_request,no_farms,requests_made;
    JSONObject lngObject;
    public static TextView reqst_count,nameee;
    SessionManager sessionManager;
    public static String toast_click_back;
    boolean doubleBackToExitPressedOnce = false;
    JSONArray count_images_array,rfq_images_array;
    HomePage_Adapter homePage_adapter;
    TextView Add_make_request,no_make_request,no_list_farm,seeall_request;


    RecyclerView recyclerView;
    public static List<AddTractorBean1> newOrderBeansList = new ArrayList<>();
    public static List<AddTractorBean2> newOrderBeansList2 = new ArrayList<>();


    public static FarmPe_Logo_Fragment newInstance() {
        FarmPe_Logo_Fragment fragment = new FarmPe_Logo_Fragment();
        return fragment;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.farmepe_logo_layout, container, false);
     //   backfeed= view.findViewById(R.id.back_feed1);

        linearLayout= view.findViewById(R.id.layout);
       // no_farms= view.findViewById(R.id.no_farms);
        no_request= view.findViewById(R.id.no_requests);

        requests_made= view.findViewById(R.id.request_made);
        nameee= view.findViewById(R.id.nameee);
        sessionManager = new SessionManager(getActivity());
        recyclerView= view.findViewById(R.id.recylr_2);


        reqst_count= view.findViewById(R.id.request_count);

        Add_make_request= view.findViewById(R.id.add_request);
       // no_list_farm= view.findViewById(R.id.list_farmmmmm);
        no_make_request= view.findViewById(R.id.make_requesttttt);

        seeall_request= view.findViewById(R.id.request_sell_all);


        no_request.setVisibility(View.GONE);


        nameee.setText(sessionManager.getRegId("name"));

        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));

            toast_click_back = lngObject.getString("PleaseclickBACKagaintoexit");



        } catch (JSONException e) {
            e.printStackTrace();
        }


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        // this.finishAffinity();
                        if (doubleBackToExitPressedOnce) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                            startActivity(intent);
                            getActivity().finish();
                            System.exit(0);
                        }

                        doubleBackToExitPressedOnce = true;
                        // Toast.makeText(getActivity().getApplicationContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
                        int duration = 1000;
                        Snackbar snackbar = Snackbar
                                .make(linearLayout, toast_click_back, duration);
                        View snackbarView = snackbar.getView();
                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                        tv.setTextColor(Color.WHITE);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        } else {
                            tv.setGravity(Gravity.CENTER_HORIZONTAL);
                        }
                        snackbar.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                doubleBackToExitPressedOnce=false;
                            }
                        }, 3000);
                    }

                    return true;
                }
                return false;
            }
        });


        newOrderBeansList2.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
       // recyclerView.addItemDecoration(new ItemDecorator( -80));
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        homePage_adapter=new HomePage_Adapter(getActivity(),newOrderBeansList2);
        recyclerView.setAdapter(homePage_adapter);



        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm1 = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);



                Add_make_request.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectedFragment = AddFirstFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.addToBackStack("farmpe_logo");
                        transaction.commit();

                    }
                });

                no_make_request.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectedFragment = AddFirstFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.addToBackStack("farmpe_logo");
                        transaction.commit();
                    }
                });



                seeall_request.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectedFragment = LookingForFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.first_full_frame, selectedFragment);
                        transaction.addToBackStack("home");
                        transaction.commit();

                    }
                });



        try{
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("CreatedBy",sessionManager.getRegId("userId"));

            Crop_Post.crop_posting(getActivity(), Urls.Home_Page_Count, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("fjfhffjcount" + result);

                    try{

                        newOrderBeansList.clear();
                        newOrderBeansList2.clear();

                        String farm_count = String.valueOf(result.getInt("FarmsCount"));
                        String  request_count = String.valueOf(result.getInt("RFQCount"));
                        String  notificatn_count = String.valueOf(result.getInt("NotificationCount"));

                        count_images_array = result.getJSONArray("FarmImages");
                        rfq_images_array = result.getJSONArray("RFQImages");

                        if(request_count.equalsIgnoreCase("0")){
                            no_request.setVisibility(View.VISIBLE);
                            requests_made.setVisibility(View.GONE);
                           /* no_farms.setVisibility(View.VISIBLE);
                            farms_lists.setVisibility(View.GONE);*/
                        }else {

                            no_request.setVisibility(View.GONE);
                        }



                        for(int i = 0;i<count_images_array.length();i++){
                            AddTractorBean1 img1=new AddTractorBean1( count_images_array.getString(i)," ","");
                            newOrderBeansList.add(img1);

                         //   if (i <= 3) {


                         //   }

                        }

                        for(int i = 0;i<rfq_images_array.length();i++) {
                            AddTractorBean2 img2 = new AddTractorBean2(rfq_images_array.getString(i), " ", "");
                            newOrderBeansList2.add(img2);

                        }


                        reqst_count.setText(request_count);
                        HomeMenuFragment.request_count.setText(request_count);
                        HomeMenuFragment.notifictn_count.setText(notificatn_count);


                        homePage_adapter.notifyDataSetChanged();


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });


        }catch(Exception e){
            e.printStackTrace();
        }


        return view;
    }

    public class ItemDecorator extends RecyclerView.ItemDecoration{
        private  final int mSpace;

        public  ItemDecorator(int space) {
            this.mSpace = space;
        }
        @Override
        public  void  getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
            int position = parent.getChildAdapterPosition(view);
            if(position !=0)
                // outRect.top = mSpace;
                outRect.left = mSpace;
        }

    }

}

