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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.FarmPe.Farmer.Activity.LandingPageActivity;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;

import org.json.JSONObject;

import static com.FarmPe.Farmer.Activity.LandingPageActivity.mBottomSheetBehavior6;


public class AaProfileFragment extends Fragment {
    BottomSheetDialog mBottomSheetDialog;
    View sheetView;
    Fragment selectedFragment;
    LinearLayout backfeed,acc_info_lay,main_layout,about_lay;
    TextView notificatn,change_language,your_addresss,acc_info1,refer_ern,feedbk,help_1,abt_frmpe,polic_1,logot,setting_tittle,profname,aboutText;
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
        profname = view.findViewById(R.id.prof_name);
        aboutText = view.findViewById(R.id.about_text);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        backfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("aaAccount", FragmentManager.POP_BACK_STACK_INCLUSIVE);
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
                        fm.popBackStack("aaAccount", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }
                return false;
            }
        });

        main_layout.setBackgroundColor(Color.parseColor("#f5f5f5"));
      //  mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_COLLAPSED);

        acc_info_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*main_layout.setBackgroundColor(Color.parseColor("#666666"));
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
                });*/

                mBottomSheetDialog = new BottomSheetDialog(getActivity());
                sheetView = getActivity().getLayoutInflater().inflate(R.layout.general_layout, null);
                TextView positiveText = sheetView.findViewById(R.id.positive_text);
                TextView titleText = sheetView.findViewById(R.id.bottom_sheet_title);
                TextView descriptionText = sheetView.findViewById(R.id.bottom_sheet_description);
                EditText userInput = sheetView.findViewById(R.id.user_text);
                userInput.setVisibility(View.VISIBLE);
                userInput.setText(profname.getText().toString());
                descriptionText.setVisibility(View.GONE);
                titleText.setText("Enter your name");
                descriptionText.setText("Are you sure you want to exit?");
                positiveText.setText("Save");
                TextView negetiveText = sheetView.findViewById(R.id.negetive_text);
                negetiveText.setText("Cancel");
                positiveText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(),"Save was clicked",Toast.LENGTH_LONG).show();
                    }
                });
                negetiveText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mBottomSheetDialog.dismiss();
                    }
                });
                mBottomSheetDialog.setContentView(sheetView);
                mBottomSheetDialog.show();
            }
        });


        about_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*main_layout.setBackgroundColor(Color.parseColor("#666666"));
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
                });*/

                mBottomSheetDialog = new BottomSheetDialog(getActivity());
                sheetView = getActivity().getLayoutInflater().inflate(R.layout.general_layout, null);
                TextView positiveText = sheetView.findViewById(R.id.positive_text);
                TextView titleText = sheetView.findViewById(R.id.bottom_sheet_title);
                TextView descriptionText = sheetView.findViewById(R.id.bottom_sheet_description);
                EditText userInput = sheetView.findViewById(R.id.user_text);
                userInput.setVisibility(View.VISIBLE);
                userInput.setText(aboutText.getText().toString());
                descriptionText.setVisibility(View.GONE);
                titleText.setText("Add about");
                descriptionText.setText("Are you sure you want to exit?");
                positiveText.setText("Save");
                TextView negetiveText = sheetView.findViewById(R.id.negetive_text);
                negetiveText.setText("Cancel");


                positiveText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(),"Save was clicked",Toast.LENGTH_LONG).show();
                    }
                });


                negetiveText.setOnClickListener(new View.OnClickListener() {
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
