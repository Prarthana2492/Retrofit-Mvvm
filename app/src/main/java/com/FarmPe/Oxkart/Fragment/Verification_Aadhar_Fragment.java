package com.FarmPe.Oxkart.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.FarmPe.Oxkart.Activity.New_Login_Activity2;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.Login_post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;



public class Verification_Aadhar_Fragment extends Fragment {

    LinearLayout sele_loc, selfie_img, main_layout;
    TextView submit,toolbar_title,title_details, verify_btn, verify_aadhar_st;
    EditText aadhar_no, pan_name, pan_num;
    public static String status;
    SessionManager sessionManager;
    public static JSONObject lngObject;
    JSONArray get_location_array,vote_list_array,vote_bk_list_array,imagelist_array;
    Fragment selectedFragment = null;
    boolean doubleBackToExitPressedOnce = false;


    public static Verification_Aadhar_Fragment newInstance() {
        Verification_Aadhar_Fragment fragment = new Verification_Aadhar_Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.verify_aadhar, container, false);

        //  Status_bar_change_singleton.getInstance().color_change(getActivity());


        submit = view.findViewById(R.id.submit);

        pan_name = view.findViewById(R.id.pan_name);
        pan_num = view.findViewById(R.id.pan_num);

        main_layout = view.findViewById(R.id.main_layout);
        toolbar_title = view.findViewById(R.id.setting_tittle);
        title_details = view.findViewById(R.id.title_details);
        // VerifyAadhar.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        sessionManager = new SessionManager(getActivity());

        setupUI(main_layout);



        try {


            lngObject = new JSONObject(sessionManager.getRegId("language"));

            System.out.println("llllllllllllkkkkkkkkkkkkkkk" + lngObject.getString("EnterPhoneNo"));

            toolbar_title.setText(lngObject.getString("PANDetailsoptional").replace("\n",""));
            title_details.setText(lngObject.getString("PANCardisrequiredtoavailFinancialServiceonFarmPe"));
            pan_name.setHint(lngObject.getString("EnternameasperPANCard").replace("\n",""));
            submit.setText(lngObject.getString("PROCEED").replace("\n",""));



            //  pass.setHint(lngObject.getString("Password"));
            //  remember_me.setText(lngObject.getString("RememberMe"));





        } catch (JSONException e) {
            e.printStackTrace();
        }




        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        // this.finishAffinity();

                        if (doubleBackToExitPressedOnce) {

                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                            startActivity(intent);
                            getActivity().finish();
                            System.exit(0);
                        }


                        doubleBackToExitPressedOnce = true;


                        // Toast.makeText(getActivity().getApplicationContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//                        int duration = 1000;
//
//                        Snackbar snackbar = Snackbar
//                                .make(main_layout,"Please Click Back To Exit", duration);
//                        View snackbarView = snackbar.getView();
//                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                        tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
//                        tv.setTextColor(Color.WHITE);
//
//
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                        } else {
//                            tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                        }
//                        snackbar.show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                doubleBackToExitPressedOnce = false;
                            }
                        }, 3000);
                    }

                    return true;
                }
                return false;
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                if (pan_name.getText().toString().equals("")) {
//
//
//                    Toast toast = Toast.makeText(getActivity(), "Please Enter PAN Name", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
//                    TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
//                    toastMessage.setTextColor(Color.WHITE);
//                    toast.getView().setBackgroundResource(R.drawable.black_curve_background);
//                    toast.show();
//
//
//                }  else if(pan_num.getText().toString().equals("")){
//
//
//                   Toast toast = Toast.makeText(getActivity(), "Please Enter PAN Number", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
//                    TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
//                    toastMessage.setTextColor(Color.WHITE);
//                    toast.getView().setBackgroundResource(R.drawable.black_curve_background);
//                    toast.show();
//
//
//                }


//                    int duration = 1000;
//                    Snackbar snackbar = Snackbar
//                            .make(main_layout, "Enter 12 digit Aadhar Number.", duration);
//                    View snackbarView = snackbar.getView();
//                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
//                    tv.setTextColor(Color.WHITE);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                    } else {
//                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                    }
//                    snackbar.show();

//                else {

                SubmitDetails();


//                Intent intent = new Intent(VerifyAadhar.this, VerificationMain.class);
//                startActivity(intent);
//
//                if (aadhar_no.getText().toString().equals("")) {
//                    int duration = 1000;
//                    Snackbar snackbar = Snackbar
//                            .make(main_layout, "Enter 12 digit Aadhar Number.", duration);
//                    View snackbarView = snackbar.getView();
//                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                    tv.setBackgroundColor(ContextCompat.getColor(VerifyAadhar.this, R.color.orange));
//                    tv.setTextColor(Color.WHITE);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                    } else {
//                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                    }
//                    snackbar.show();
//                }else if (pan_name.getText().toString().equals("")){
//                    int duration = 1000;
//                    Snackbar snackbar = Snackbar
//                            .make(main_layout, "Enter name as per PAN card", duration);
//                    View snackbarView = snackbar.getView();
//                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                    tv.setBackgroundColor(ContextCompat.getColor(VerifyAadhar.this, R.color.orange));
//                    tv.setTextColor(Color.WHITE);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                    } else {
//                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                    }
//                    snackbar.show();
//                }else if (pan_num.getText().toString().equals("")){
//                    int duration = 1000;
//                    Snackbar snackbar = Snackbar
//                            .make(main_layout, "Enter PAN Number.", duration);
//                    View snackbarView = snackbar.getView();
//                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                    tv.setBackgroundColor(ContextCompat.getColor(VerifyAadhar.this, R.color.orange));
//                    tv.setTextColor(Color.WHITE);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                    } else {
//                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                    }
//                    snackbar.show();
//                }else if (!(Pattern.compile("[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}").matcher(pan_num.getText().toString()).matches())) {
//                    int duration = 1000;
//                    Snackbar snackbar = Snackbar
//                            .make(main_layout, "Enter Valid Pan Card Number.", duration);
//                    View snackbarView = snackbar.getView();
//                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                    tv.setBackgroundColor(ContextCompat.getColor(VerifyAadhar.this, R.color.orange));
//                    tv.setTextColor(Color.WHITE);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                    } else {
//                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                    }
//                    snackbar.show();
//                }else{
                //  SubmitDetails();
                //  }
                /*Intent intent=new Intent(VerifyAadhar.this,VerificationMain.class);
                startActivity(intent);*/
            }
        });

//        verify_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (aadhar_no.getText().toString().equals("")) {
//
//                    Toast toast = Toast.makeText(getActivity(), "Enter 12 digit Aadhar Number", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
//                    toast.show();
//
//
//                    //int duration = 1000;
////
////                    Snackbar snackbar = Snackbar
////                            .make(main_layout, "Enter 12 digit Aadhar Number.", duration);
////                    View snackbarView = snackbar.getView();
////                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
////                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
////                    tv.setTextColor(Color.WHITE);
////                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
////                    } else {
////                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
////                    }
////                    snackbar.show();
//
//
//                } else if (!(Pattern.compile("[0-9]{12}").matcher(aadhar_no.getText().toString()).matches())) {
//                    verify_aadhar_st.setVisibility(View.VISIBLE);
//                    verify_aadhar_st.setText("Invalid Aadhar Number");
//
//
//                } else {
//
//                    verify_aadhar_st.setVisibility(View.VISIBLE);
//                    verify_aadhar_st.setText("Aadhar Verification Completed!");
//
//                }
//            }
//        });



        //Get shop location
        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId", sessionManager.getRegId("userId"));


            Crop_Post.crop_posting(getActivity(), Urls.Get_Shop_Location, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("dhfjfjd" + result);


                    try {

                        get_location_array = result.getJSONArray("clocationList");


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


        //Get voter front details

        try{

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));


            Crop_Post.crop_posting(getActivity(), Urls.Get_Front_Voter_ID_Details, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dhfjfjd" + result);


                    try{
                        vote_list_array = result.getJSONArray("voterIdfrontLists");

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });

        }catch(Exception e){
            e.printStackTrace();
        }



  //Get voterback id details

        try{

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));



            Crop_Post.crop_posting(getActivity(), Urls.Get_Back_Voter_ID_Details, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dhfjfjd" + result);


                    try{

                        vote_bk_list_array = result.getJSONArray("voterIdbackLists");


                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });


        }catch(Exception e){
            e.printStackTrace();
        }


//Get Selfie Details

        try{

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));



            Crop_Post.crop_posting(getActivity(), Urls.Get_Image_Details, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dhfjfjd" + result);

                    try{

                        imagelist_array = result.getJSONArray("captureImagelist");


                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });


        }catch(Exception e){
            e.printStackTrace();
        }






        return view;

    }


    private void SubmitDetails() {

        try {

            JSONObject jsonObject = new JSONObject();

            // jsonObject.put("Id", sessionManager.getRegId("userId"));
            jsonObject.put("PANCardName", pan_name.getText().toString());
            jsonObject.put("PANNo", pan_num.getText().toString());
            jsonObject.put("CreatedBy", sessionManager.getRegId("userId"));
            jsonObject.put("UserId", sessionManager.getRegId("userId"));


            System.out.println("poooooossttiing_parameters" + jsonObject);


            Login_post.login_posting(getActivity(), Urls.Add_Update_Aadhar_details, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("111111user" + result);

                    try {

                        status = result.getString("Status");
                        // LandingPageActivity.applicationId=status;
                        System.out.println("ennnnnnppp");


                        if (status.equals("1")) {


                            System.out.println("ennnnnn" + status);
//
                             verification_details();
//                            selectedFragment = Verification_Fragment.newInstance();
//                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                            transaction.replace(R.id.frame_layout1, selectedFragment);
//                            transaction.commit();

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void verification_details() {

        if (get_location_array.length() == 0 && vote_list_array.length() == 0 && vote_bk_list_array.length()== 0 && imagelist_array.length() == 0) {

                Bundle bundle = new Bundle();
                bundle.putString("verification_status","Verify_Page");
                status="Verify_Page";
                selectedFragment = Verification_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("verify");
                selectedFragment.setArguments(bundle);
                transaction.commit();


            } else {
                   Bundle bundle = new Bundle();
                   bundle.putString("verification_status","Edit_Page");
                   status="Edit_Page";

                   selectedFragment = Edit_Verification_Fragment.newInstance();
                   FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                   transaction.replace(R.id.frame_layout1, selectedFragment);
                   transaction.addToBackStack("verify");
                   transaction.commit();

            }

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
    }







