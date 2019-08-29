package com.FarmPe.Farmer.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.FarmPe.Farmer.Activity.LandingPageActivity;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;

import org.json.JSONObject;

import static com.FarmPe.Farmer.Activity.LandingPageActivity.mBottomSheetBehavior5;


public class AaHelpFragment extends Fragment {
    BottomSheetDialog mBottomSheetDialog;
    View sheetView;
    Fragment selectedFragment;
    LinearLayout backfeed,feedback_lay,main_layout,about_lay;
    TextView notificatn,change_language,your_addresss,acc_info1,refer_ern,feedbk,help_1,abt_frmpe,polic_1,logot,setting_tittle;
    SessionManager sessionManager;
    JSONObject lngObject;
    public static AaHelpFragment newInstance() {
        AaHelpFragment fragment = new AaHelpFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_a_help_layout, container, false);

        backfeed=view.findViewById(R.id.back_feed);
        feedback_lay=view.findViewById(R.id.feedback_lay);
        main_layout=view.findViewById(R.id.main_layout);
       // about_lay=view.findViewById(R.id.about_lay);

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        backfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);
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
                    fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    return true;

                }
                return false;
            }
        });

        main_layout.setBackgroundColor(Color.parseColor("#f5f5f5"));
        mBottomSheetBehavior5.setState(BottomSheetBehavior.STATE_COLLAPSED);
        feedback_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*main_layout.setBackgroundColor(Color.parseColor("#666666"));
                mBottomSheetBehavior5.setState(BottomSheetBehavior.STATE_EXPANDED);
                main_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBottomSheetBehavior5.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        main_layout.setBackgroundColor(Color.parseColor("#f5f5f5"));

                    }
                });
                LandingPageActivity.cancel_feed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBottomSheetBehavior5.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        main_layout.setBackgroundColor(Color.parseColor("#f5f5f5"));

                    }
                });*/

                mBottomSheetDialog = new BottomSheetDialog(getActivity());
                sheetView = getActivity().getLayoutInflater().inflate(R.layout.feedback_bottom_sheet, null);
                TextView cancel = sheetView.findViewById(R.id.cancel_feedback);
                TextView save = sheetView.findViewById(R.id.save_feedback);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), "Save was clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mBottomSheetDialog.dismiss();
                    }
                });
                mBottomSheetDialog.setContentView(sheetView);
                mBottomSheetDialog.show();
            }
        });


        return view;
    }



}
