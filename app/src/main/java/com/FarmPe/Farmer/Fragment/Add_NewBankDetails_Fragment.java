package com.FarmPe.Farmer.Fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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


public class Add_NewBankDetails_Fragment extends Fragment {
    RecyclerView recycler_brand;
    LinearLayout back_feed,loan_lay,main_layout,continuebtn;
    TextView back_text,next;
    Fragment selectedFragment;
    EditText do_u_have_ifsc,state,district,bankname,bank_branchname,saving_ac_nu,confirm_saving_ac;
    Calendar myCalendar;
    public static EditText state_txt;
    public static DrawerLayout drawer;
    RecyclerView recyclerView;
    String account_validate,ifsc_validate,status;
    EditText account_no,ifsc_code;

    public static Add_NewBankDetails_Fragment newInstance() {
        Add_NewBankDetails_Fragment fragment = new Add_NewBankDetails_Fragment();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addnewbankdetailslayout, container, false);


        getActivity().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


         Window window = getActivity().getWindow();
         window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
         back_feed=view.findViewById(R.id.back_feed);
          do_u_have_ifsc=view.findViewById(R.id.fullname);
          state=view.findViewById(R.id.relationship);
          district=view.findViewById(R.id.relativename);
          bankname=view.findViewById(R.id.age);
          bank_branchname=view.findViewById(R.id.castecategory);
          saving_ac_nu=view.findViewById(R.id.farmertype);
          confirm_saving_ac=view.findViewById(R.id.farmercategory);
          continuebtn=view.findViewById(R.id.continuebtn);


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("bankaccount", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = Addnewbankdetailswithifsc.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("add_new");
                transaction.commit();
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack ("bankaccount", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }

                return false;
            }
        });

        do_u_have_ifsc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(do_u_have_ifsc,state,district,bankname,bank_branchname,saving_ac_nu,confirm_saving_ac);
                return false;
            }
        });

        state.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(state,do_u_have_ifsc,district,bankname,bank_branchname,saving_ac_nu,confirm_saving_ac);
                return false;
            }
        });

        district.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(district,state,do_u_have_ifsc,bankname,bank_branchname,saving_ac_nu,confirm_saving_ac);
                return false;
            }
        });

        bankname.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(bankname,do_u_have_ifsc,state,district,bank_branchname,saving_ac_nu,confirm_saving_ac);
                return false;
            }
        });

        bank_branchname.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(bank_branchname,state,district,bankname,saving_ac_nu,confirm_saving_ac,do_u_have_ifsc);
                return false;
            }
        });

        saving_ac_nu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(saving_ac_nu,state,district,bankname,bank_branchname,confirm_saving_ac,do_u_have_ifsc);
                return false;
            }
        });

        confirm_saving_ac.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(confirm_saving_ac,state,district,bankname,bank_branchname,saving_ac_nu,do_u_have_ifsc);
                return false;
            }
        });


        return view;
    }
  public void linear_layout_selection(EditText selectdl1,EditText l2,EditText l3,EditText l4, EditText l5 ,EditText l6 , EditText l7){
      selectdl1.setBackgroundResource(R.drawable.border_1_layout);
      l2.setBackgroundResource(R.drawable.request_price_white_border2);
      l3.setBackgroundResource(R.drawable.request_price_white_border2);
      l4.setBackgroundResource(R.drawable.request_price_white_border2);
      l5.setBackgroundResource(R.drawable.request_price_white_border2);
      l6.setBackgroundResource(R.drawable.request_price_white_border2);
      l7.setBackgroundResource(R.drawable.request_price_white_border2);

  }
}
