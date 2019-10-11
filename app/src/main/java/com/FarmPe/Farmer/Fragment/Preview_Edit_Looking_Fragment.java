package com.FarmPe.Farmer.Fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.Farmer.Activity.HomePage_With_Bottom_Navigation;
import com.FarmPe.Farmer.Activity.Status_bar_change_singleton;
import com.FarmPe.Farmer.Bean.FarmsImageBean;
import com.FarmPe.Farmer.R;

import java.util.ArrayList;
import java.util.List;




public class Preview_Edit_Looking_Fragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    LinearLayout back_feed;
    Fragment selectedFragment = null;
    ImageView imageView,image_looking,image_loofking,image_lgooking,image_loovking;
    TextView textView,toolbar_title,preview;



    public static Preview_Edit_Looking_Fragment newInstance() {
        Preview_Edit_Looking_Fragment fragment = new Preview_Edit_Looking_Fragment();
        return fragment;
    }

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.edit_request_layout, container, false);
        // getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

         Status_bar_change_singleton.getInstance().color_change(getActivity());
         HomePage_With_Bottom_Navigation.linear_bottom.setVisibility(View.GONE);


         imageView = view.findViewById(R.id.editimg);
         image_looking = view.findViewById(R.id.image_looking);
         image_loofking = view.findViewById(R.id.image_loofking);
         image_lgooking = view.findViewById(R.id.image_lgooking);
         image_loovking = view.findViewById(R.id.image_loovking);



         imageView.setVisibility(View.GONE);
         image_looking.setVisibility(View.GONE);
         image_loofking.setVisibility(View.GONE);
         image_lgooking.setVisibility(View.GONE);
         image_loovking.setVisibility(View.GONE);


         preview = view.findViewById(R.id.preview);
         preview.setVisibility(View.GONE);
         back_feed = view.findViewById(R.id.back_feed);
         toolbar_title = view.findViewById(R.id.toolbar_title);
         toolbar_title.setText("Preview");


         view.setFocusableInTouchMode(true);
         view.requestFocus();
         view.setOnKeyListener(new View.OnKeyListener() {


             @Override
             public boolean onKey(View v, int keyCode, KeyEvent event) {
                 if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                     if(getArguments().getString("status").equals("lookng_for")){

                         FragmentManager fm = getActivity().getSupportFragmentManager();
                         fm.popBackStack("looking_for_edit", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                     }else if(getArguments().getString("status").equals("edit_for")) {


                         FragmentManager fm = getActivity().getSupportFragmentManager();
                         fm.popBackStack("editpage", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                     }

                     return true;
                 }
                 return false;
             }
         });


         back_feed.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(getArguments().getString("status").equals("lookng_for")){

                     FragmentManager fm = getActivity().getSupportFragmentManager();
                     fm.popBackStack("looking_for_edit", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                 }else if(getArguments().getString("status").equals("edit_for")) {


                     FragmentManager fm = getActivity().getSupportFragmentManager();
                     fm.popBackStack("editpage", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                 }


             }
         });



        return view;

    }

}









