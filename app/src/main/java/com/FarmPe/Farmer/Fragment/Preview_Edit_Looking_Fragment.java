package com.FarmPe.Farmer.Fragment;



import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.FarmPe.Farmer.Bean.FarmsImageBean;
import com.FarmPe.Farmer.R;
import java.util.ArrayList;
import java.util.List;


public class Preview_Edit_Looking_Fragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    LinearLayout back_feed;
    Fragment selectedFragment = null;



    public static Preview_Edit_Looking_Fragment newInstance() {
        Preview_Edit_Looking_Fragment fragment = new Preview_Edit_Looking_Fragment();
        return fragment;
    }


     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.edit_request_layout, container, false);
        // getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);




        back_feed = view.findViewById(R.id.back_feed);


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeMenuFragment.onBack_status = "looking_frg";

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("edit_looking", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });




        return view;

    }

}









