package com.FarmPe.Farmer.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Farmer.Activity.HomePage_With_Bottom_Navigation;
import com.FarmPe.Farmer.Activity.LoginActivity;
import com.FarmPe.Farmer.Activity.Status_bar_change_singleton;
import com.FarmPe.Farmer.Adapter.Address_Adapter;
import com.FarmPe.Farmer.Bean.Add_New_Address_Bean;
import com.FarmPe.Farmer.R;

import org.json.JSONArray;

import java.util.ArrayList;

public class NewAddressDetails_Fragment extends Fragment {

    public static ArrayList<Add_New_Address_Bean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static Address_Adapter livestock_types_adapter;
    Fragment selectedFragment = null;
    TextView toolbar_title,Continue_txt;
    public static String livestock_status;
    LinearLayout back_feed,linearLayout,Continue;
    JSONArray get_categorylist_array;

    public static NewAddressDetails_Fragment newInstance() {
        NewAddressDetails_Fragment fragment = new NewAddressDetails_Fragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.soil_details_recy_layout, container, false);
        Status_bar_change_singleton.getInstance().color_change(getActivity());

        HomePage_With_Bottom_Navigation.linear_bottom.setVisibility(View.GONE);



        recyclerView=view.findViewById(R.id.recycler_what_looking);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        toolbar_title.setText("My Addresses");
        back_feed=view.findViewById(R.id.back_feed);
        linearLayout = view.findViewById(R.id.main_layout);
        Continue = view.findViewById(R.id.continuebtn);
        Continue_txt = view.findViewById(R.id.apply_loan);
        Continue_txt.setText("ADD NEW ADDRESS");

     //   setupUI(linearLayout);

      Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFragment = NewAddressFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("newaddressfragment");
                transaction.commit();
            }
        });

 back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("settingg", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("settingg", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });
        newOrderBeansList.clear();
        // livestock_types_adapter = new Livestock_Types_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        Add_New_Address_Bean img1=new Add_New_Address_Bean("Jagdish","102","RR Nagar","","Bengaluru","560098","","","Karnataka",
                "","","","","", true,"","","","");
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);

        livestock_types_adapter=new Address_Adapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(livestock_types_adapter);


        return view;
    }








}
