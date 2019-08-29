package com.FarmPe.Farmer.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Farmer.Activity.LandingPageActivity;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;

import org.json.JSONObject;

import static com.FarmPe.Farmer.Activity.LandingPageActivity.mBottomSheetBehavior4;


public class AaSettingFragment extends Fragment {
    Fragment selectedFragment;
    LinearLayout backfeed,acc_info_lay,not_lay,lang_lay,help_lay,invi_lay,main_layout,requ_lay;
    TextView notificatn,change_language,your_addresss,acc_info1,refer_ern,feedbk,help_1,abt_frmpe,polic_1,logot,setting_tittle;
    SessionManager sessionManager;
    JSONObject lngObject;
    boolean newState=false;
    public static AaSettingFragment newInstance() {
        AaSettingFragment fragment = new AaSettingFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_s_setting_layout, container, false);

        backfeed=view.findViewById(R.id.back_feed);
        acc_info_lay=view.findViewById(R.id.acc_info_lay);
        not_lay=view.findViewById(R.id.not_lay);
        lang_lay=view.findViewById(R.id.lang_lay);
        help_lay=view.findViewById(R.id.help_lay);
        invi_lay=view.findViewById(R.id.invi_lay);
        main_layout=view.findViewById(R.id.main_layout);
        requ_lay=view.findViewById(R.id.requ_lay);


        backfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = HomeMenuFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
            }
        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                //    getFragmentManager().popBackStack("home_menu", android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    selectedFragment = HomeMenuFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();

                    return  true;
                    /*FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("home_m", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;*/
                }
                return false;
            }
        });


        acc_info_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newState==false){
                    selectedFragment = AaAccountFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.addToBackStack("setting");
                    transaction.commit();

                }else{
                    mBottomSheetBehavior4.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    main_layout.setBackgroundColor(Color.parseColor("#f5f5f5"));
                   newState=false;
                }

            }
        });

        not_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newState==false){
                    selectedFragment = AaNotificationSetting.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.addToBackStack("setting");
                    transaction.commit();

                }else{
                    mBottomSheetBehavior4.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    main_layout.setBackgroundColor(Color.parseColor("#f5f5f5"));
                    newState=false;
                }

            }
        });
        lang_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (newState==false){
                    selectedFragment = ChangeLanguageFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.addToBackStack("setting");
                    transaction.commit();

                }else{
                    mBottomSheetBehavior4.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    main_layout.setBackgroundColor(Color.parseColor("#f5f5f5"));
                    newState=false;
                }

            }
        });

        help_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newState==false){
                    selectedFragment = AaHelpFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.addToBackStack("setting");
                    transaction.commit();

                }else{
                    mBottomSheetBehavior4.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    main_layout.setBackgroundColor(Color.parseColor("#f5f5f5"));
                    newState=false;
                }

            }
        });


        invi_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newState=true;
                main_layout.setBackgroundColor(Color.parseColor("#666666"));
                mBottomSheetBehavior4.setState(BottomSheetBehavior.STATE_EXPANDED);

                main_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mBottomSheetBehavior4.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        main_layout.setBackgroundColor(Color.parseColor("#f5f5f5"));
                        newState=false;

                    }
                });

                LandingPageActivity.cancel_invite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mBottomSheetBehavior4.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        main_layout.setBackgroundColor(Color.parseColor("#f5f5f5"));
                        newState=false;

                    }
                });
            }
        });

        requ_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (newState==false){
//                    selectedFragment = AaStatusFragment.newInstance();
//                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame_layout, selectedFragment);
//                    transaction.addToBackStack("setting");
//                    transaction.commit();
//
//                }else{
//                    mBottomSheetBehavior4.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                    main_layout.setBackgroundColor(Color.parseColor("#f5f5f5"));
//                    newState=false;
//                }
            }
        });


        return view;
    }



}
