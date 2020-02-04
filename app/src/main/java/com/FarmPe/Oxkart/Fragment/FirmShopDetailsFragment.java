package com.FarmPe.Oxkart.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.FarmPe.Oxkart.Activity.Status_bar_change_singleton;
import com.FarmPe.Oxkart.R;


public class FirmShopDetailsFragment extends Fragment {
    Fragment selectedFragment;
    LinearLayout Continue;

    public static FirmShopDetailsFragment newInstance() {
        FirmShopDetailsFragment fragment = new FirmShopDetailsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firmshopdetails_layout, container, false);

        Status_bar_change_singleton.getInstance().color_change(getActivity());

        Continue = view.findViewById(R.id.continuebtn);


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                  //  HomeMenuFragment.onBack_status = "farms";

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("list_address_firm", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    return true;
                }
                return false;
            }
        });


        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFragment = Shop_Location_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("list_address_firm");
                transaction.commit();

            }
        });









        return view;
    }
}

