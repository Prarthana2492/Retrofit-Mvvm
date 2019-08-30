package com.FarmPe.Farmer.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;

import org.json.JSONObject;




public class AaAccountFragment extends Fragment {
    BottomSheetDialog mBottomSheetDialog;
    View sheetView;
    Fragment selectedFragment;
    LinearLayout backfeed,acc_info_lay,change_pass_lay,main_layout,logout_lay;
    TextView notificatn,change_language,your_addresss,acc_info1,refer_ern,feedbk,help_1,abt_frmpe,polic_1,logot,setting_tittle;
    SessionManager sessionManager;
    JSONObject lngObject;

    public static AaAccountFragment newInstance() {
        AaAccountFragment fragment = new AaAccountFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_a_acount_layout, container, false);

        backfeed=view.findViewById(R.id.back_feed);
        acc_info_lay=view.findViewById(R.id.acc_info_lay);
        change_pass_lay=view.findViewById(R.id.change_pass_lay);
        main_layout=view.findViewById(R.id.main_layout);
        logout_lay=view.findViewById(R.id.logout_lay);

       // LandingPageActivity.editname.setVisibility(View.GONE);

        final SessionManager sessionManager=new SessionManager(getActivity());
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
                    return  true;

                }
                return false;
            }
        });

        acc_info_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = AaProfileFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("aaAccount");
                transaction.commit();
            }
        });

        change_pass_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*main_layout.setBackgroundColor(Color.parseColor("#666666"));
                mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_EXPANDED);
                LandingPageActivity.editname.setVisibility(View.VISIBLE);
                LandingPageActivity.logout.setVisibility(View.GONE);
                LandingPageActivity.name_hint.setText("Change Password");
                LandingPageActivity.cancel.setText("Cancel");
                LandingPageActivity.save.setText("Save");
                LandingPageActivity.editname.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                LandingPageActivity.editname.setFilters(new InputFilter[] { new InputFilter.LengthFilter(12)});
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
                userInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                userInput.setVisibility(View.VISIBLE);
                titleText.setText("Change password");
                Log.d("liugekuyhg",""+titleText.getText().toString());
                descriptionText.setText("Are you sure you want to exit?");
                descriptionText.setVisibility(View.GONE);
                positiveText.setText("Save");
                TextView negetiveText = sheetView.findViewById(R.id.negetive_text);
                negetiveText.setText("Cancel");
                positiveText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), "Change was clicked", Toast.LENGTH_SHORT).show();
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


        logout_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mBottomSheetDialog = new BottomSheetDialog(getActivity());
                sheetView = getActivity().getLayoutInflater().inflate(R.layout.general_layout, null);
                TextView positiveText = sheetView.findViewById(R.id.positive_text);
                TextView titleText = sheetView.findViewById(R.id.bottom_sheet_title);
                TextView descriptionText = sheetView.findViewById(R.id.bottom_sheet_description);
                EditText userInput = sheetView.findViewById(R.id.user_text);
                userInput.setVisibility(View.GONE);
                titleText.setText("Logout");
                descriptionText.setText("Are you sure you want to exit?");
                positiveText.setText("Yes");
                TextView negetiveText = sheetView.findViewById(R.id.negetive_text);
                negetiveText.setText("No");
                positiveText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sessionManager.logoutUser();
                        getActivity().finish();
                        mBottomSheetDialog.dismiss();
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
