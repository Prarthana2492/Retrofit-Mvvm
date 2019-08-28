package com.FarmPe.Farmer.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Farmer.Activity.LandingPageActivity;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;

import org.json.JSONObject;

import static com.FarmPe.Farmer.Activity.LandingPageActivity.mBottomSheetBehavior6;


public class AaProfileFragment extends Fragment {
    Fragment selectedFragment;
    LinearLayout backfeed,acc_info_lay,main_layout,about_lay;
    TextView notificatn,change_language,your_addresss,acc_info1,refer_ern,feedbk,help_1,abt_frmpe,polic_1,logot,setting_tittle;
    SessionManager sessionManager;
    JSONObject lngObject;
    public static AaProfileFragment newInstance() {
        AaProfileFragment fragment = new AaProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_a_profile_layout, container, false);

        backfeed=view.findViewById(R.id.back_feed);
        acc_info_lay=view.findViewById(R.id.acc_info_lay);
        main_layout=view.findViewById(R.id.main_layout);
        about_lay=view.findViewById(R.id.about_lay);

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        backfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                //    getFragmentManager().popBackStack("home_menu", android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });
        main_layout.setBackgroundColor(Color.parseColor("#f5f5f5"));
        mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_COLLAPSED);

        acc_info_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_layout.setBackgroundColor(Color.parseColor("#666666"));
                mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_EXPANDED);
                LandingPageActivity.name_hint.setText("Enter your name");
                LandingPageActivity.editname.setVisibility(View.VISIBLE);
                LandingPageActivity.logout.setVisibility(View.GONE);
                LandingPageActivity.cancel.setText("Cancel");
                LandingPageActivity.save.setText("Save");
                main_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        main_layout.setBackgroundColor(Color.parseColor("#f5f5f5"));

                    }
                });
                LandingPageActivity.cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        main_layout.setBackgroundColor(Color.parseColor("#f5f5f5"));

                    }
                });

            }
        });

        about_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_layout.setBackgroundColor(Color.parseColor("#666666"));
                mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_EXPANDED);
                LandingPageActivity.name_hint.setText("Add About");
                LandingPageActivity.editname.setVisibility(View.VISIBLE);
                LandingPageActivity.logout.setVisibility(View.GONE);
                LandingPageActivity.cancel.setText("Cancel");
                LandingPageActivity.save.setText("Save");
                main_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        main_layout.setBackgroundColor(Color.parseColor("#f5f5f5"));
                    }
                });
                LandingPageActivity.cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        main_layout.setBackgroundColor(Color.parseColor("#f5f5f5"));

                    }
                });
            }
        });

        return view;
    }



}
