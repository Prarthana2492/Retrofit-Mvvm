package com.FarmPe.Farmer.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Farmer.R;

import java.util.Calendar;


public class Verify_KYC_Fragment extends Fragment {
    RecyclerView recycler_brand;
    LinearLayout back_feed,loan_lay,main_layout,verify;
    TextView back_text,next;
    Fragment selectedFragment;
    EditText select_document_type,enter_id_nu,enter_name;
    Calendar myCalendar;
    public static EditText state_txt;
    public static DrawerLayout drawer;
    RecyclerView recyclerView;
    String account_validate,ifsc_validate,status;
    EditText account_no,ifsc_code;
    public static Verify_KYC_Fragment newInstance() {
        Verify_KYC_Fragment fragment = new Verify_KYC_Fragment();
        return fragment;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.verify_kyc_layout, container, false);

        getActivity().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        back_feed=view.findViewById(R.id.back_feed);
        select_document_type=view.findViewById(R.id.fullname);
        enter_id_nu=view.findViewById(R.id.relationship);
        enter_name=view.findViewById(R.id.relativename);
        verify=view.findViewById(R.id.continuebtn);
        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("settingg", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
    /*verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = Addnewbankdetailswithifsc.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("add_new");
                transaction.commit();
            }
        });*/
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack ("settingg", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }

                return false;
            }
        });
        select_document_type.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(select_document_type,enter_id_nu,enter_name);
                return false;
            }
        });
        enter_id_nu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(enter_id_nu,select_document_type,enter_name);
                return false;
            }
        });
        enter_name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(enter_name,select_document_type,enter_id_nu);
                return false;
            }
        });
        return view;
    }
  public void linear_layout_selection(EditText selectdl1,EditText l2,EditText l3){
      selectdl1.setBackgroundResource(R.drawable.border_1_layout);
      l2.setBackgroundResource(R.drawable.request_price_white_border2);
      l3.setBackgroundResource(R.drawable.request_price_white_border2);


  }
}
