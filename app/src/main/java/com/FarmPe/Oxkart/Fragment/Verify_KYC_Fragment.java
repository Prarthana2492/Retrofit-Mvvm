package com.FarmPe.Oxkart.Fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;


import com.FarmPe.Oxkart.R;


public class Verify_KYC_Fragment extends Fragment {

    LinearLayout back_feed,linear_layout,verify;

    Fragment selectedFragment;
    EditText select_document_type,enter_id_nu,enter_name;
    public static DrawerLayout drawer;
    RecyclerView recyclerView;
    String status;


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
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.dark_green));
        back_feed=view.findViewById(R.id.back_feed);
        select_document_type=view.findViewById(R.id.select_doc);
        enter_id_nu=view.findViewById(R.id.relationship);
        enter_name=view.findViewById(R.id.relativename);
        verify=view.findViewById(R.id.continuebtn);
        linear_layout = view.findViewById(R.id.linear_layout);


             setupUI(linear_layout);


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



        select_document_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(getActivity(),select_document_type);
                popupMenu.getMenu().add("PAN Card");
                popupMenu.getMenu().add("Aadhar Card");
                popupMenu.getMenu().add("Voter Id");
                popupMenu.getMenu().add("Driving License");



                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem items) {


                      select_document_type.setText(items.getTitle());


                        return false;
                    }
                });

               popupMenu.show();

            }
        });



        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(select_document_type.getText().toString().equals("")){


                    Snackbar snackbar = Snackbar
                            .make(linear_layout,"Please Select Document Type", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                    tv.setTextColor(Color.WHITE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }

                    snackbar.show();


                } else if(enter_id_nu.getText().toString().equals("")){



                    Snackbar snackbar = Snackbar
                            .make(linear_layout,"Please Enter ID Number", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                    tv.setTextColor(Color.WHITE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    } else {

                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }

                    snackbar.show();


                } else if(enter_name.getText().toString().equals("")){


                    Snackbar snackbar = Snackbar
                            .make(linear_layout,"Please Enter Name", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                    tv.setTextColor(Color.WHITE);


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    } else {

                        tv.setGravity(Gravity.CENTER_HORIZONTAL);

                    }

                    snackbar.show();

                }

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


  public void linear_layout_selection(EditText selectdl1,EditText l2,EditText l3){
      selectdl1.setBackgroundResource(R.drawable.border_1_layout);
      l2.setBackgroundResource(R.drawable.request_price_white_border2);
      l3.setBackgroundResource(R.drawable.request_price_white_border2);

  }
}
