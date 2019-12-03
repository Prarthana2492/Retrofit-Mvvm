package com.FarmPe.Farmer.Fragment;

import android.app.Activity;
import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Farmer.R;

import java.util.Calendar;


public class NewAddressFragment extends Fragment {
    RecyclerView recycler_brand;
    LinearLayout back_feed,loan_lay,main_layout,continuebtn,linear_layout;
    TextView back_text,next;
    Fragment selectedFragment;
    EditText full_name,mobile_no,address,landmark,pincode,state,district,block,nyaypanchayat,grampanchayath,village ;
    Calendar myCalendar;
    public static EditText state_txt;
    public static DrawerLayout drawer;
    RecyclerView recyclerView;
    String account_validate,ifsc_validate,status;
    EditText account_no,ifsc_code;

    public static NewAddressFragment newInstance() {
        NewAddressFragment fragment = new NewAddressFragment();
        return fragment;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newaddresslayout, container, false);


        getActivity().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        back_feed=view.findViewById(R.id.back_feed);
        full_name=view.findViewById(R.id.fulname);
        mobile_no=view.findViewById(R.id.mobno);
        address=view.findViewById(R.id.addressss);
        landmark=view.findViewById(R.id.lnd_mr);
        pincode=view.findViewById(R.id.pincodeee);
        state=view.findViewById(R.id.st);
        district=view.findViewById(R.id.dt);
        block=view.findViewById(R.id.bk);
        nyaypanchayat=view.findViewById(R.id.nt);
        grampanchayath=view.findViewById(R.id.gt);
        village=view.findViewById(R.id.ve);
        linear_layout=view.findViewById(R.id.linear_layout);


        setupUI(linear_layout);

        continuebtn=view.findViewById(R.id.continuebtn);
        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("newaddressfragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
     /* continuebtn.setOnClickListener(new View.OnClickListener() {
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
                        fm.popBackStack ("newaddressfragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }

                return false;
            }
        });
        full_name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(full_name,mobile_no,address,landmark,pincode,state,district,block,nyaypanchayat,grampanchayath,village);
                return false;
            }
        });
        mobile_no.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(mobile_no,full_name,address,landmark,pincode,state,district,block,nyaypanchayat,grampanchayath,village);
                return false;
            }
        });
        address.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(address,full_name,mobile_no,landmark,pincode,state,district,block,nyaypanchayat,grampanchayath,village);
                return false;
            }
        });
        landmark.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(landmark,full_name,mobile_no,address,pincode,state,district,block,nyaypanchayat,grampanchayath,village);
                return false;
            }
        });
        pincode.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(pincode,full_name,mobile_no,address,landmark,state,district,block,nyaypanchayat,grampanchayath,village);
                return false;
            }
        });
        state.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(state,full_name,mobile_no,address,landmark,pincode,district,block,nyaypanchayat,grampanchayath,village);
                return false;
            }
        });
        district.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(district,full_name,mobile_no,address,landmark,pincode,state,block,nyaypanchayat,grampanchayath,village);
                return false;
            }
        });
        block.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(block,full_name,mobile_no,address,landmark,pincode,state,district,nyaypanchayat,grampanchayath,village);
                return false;
            }
        });
        nyaypanchayat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(nyaypanchayat,full_name,mobile_no,address,landmark,pincode,state,district,block,grampanchayath,village);
                return false;
            }
        });
        grampanchayath.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(grampanchayath,full_name,mobile_no,address,landmark,pincode,state,district,block,nyaypanchayat,village);
                return false;
            }
        });
        village.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(village,full_name,mobile_no,address,landmark,pincode,state,district,block,nyaypanchayat,grampanchayath);
                return false;
            }
        });


        return view;
    }





    public void setupUI(View view) {


        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }

            });
        }


        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {


        InputMethodManager inputManager = (InputMethodManager)
                activity.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();

        if (focusedView != null) {

            try{
                assert inputManager != null;
                inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }catch(AssertionError e){
                e.printStackTrace();
            }
        }
    }





    public void linear_layout_selection(EditText selectdl1,EditText l2,EditText l3,EditText l4, EditText l5 ,EditText l6 , EditText l7,EditText l8,EditText l9,EditText l10,EditText l11){
      selectdl1.setBackgroundResource(R.drawable.border_1_layout);
      l2.setBackgroundResource(R.drawable.request_price_white_border2);
      l3.setBackgroundResource(R.drawable.request_price_white_border2);
      l4.setBackgroundResource(R.drawable.request_price_white_border2);
      l5.setBackgroundResource(R.drawable.request_price_white_border2);
      l6.setBackgroundResource(R.drawable.request_price_white_border2);
      l7.setBackgroundResource(R.drawable.request_price_white_border2);
      l8.setBackgroundResource(R.drawable.request_price_white_border2);
      l9.setBackgroundResource(R.drawable.request_price_white_border2);
      l10.setBackgroundResource(R.drawable.request_price_white_border2);
      l11.setBackgroundResource(R.drawable.request_price_white_border2);

  }
}
