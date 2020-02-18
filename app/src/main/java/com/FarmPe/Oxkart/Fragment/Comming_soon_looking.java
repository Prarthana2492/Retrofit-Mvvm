package com.FarmPe.Oxkart.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.FarmPe.Oxkart.Activity.HomePage_With_Bottom_Navigation;
import com.FarmPe.Oxkart.Activity.Status_bar_change_singleton;
import com.FarmPe.Oxkart.R;

public class Comming_soon_looking extends Fragment {
    Fragment selectedFragment;
    LinearLayout backfeed;



    public static Comming_soon_looking newInstance() {
        Comming_soon_looking fragment = new Comming_soon_looking();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comming_layout, container, false);
        backfeed= view.findViewById(R.id.back_feed1);

        Status_bar_change_singleton.getInstance().color_change(getActivity());




        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {


                    selectedFragment = Home_Menu_Fragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_menu, selectedFragment);
                    transaction.commit();


                    return true;
                }
                return false;
            }
        });


        backfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                selectedFragment = Home_Menu_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.commit();


            }
        });


        return view;
    }
}

