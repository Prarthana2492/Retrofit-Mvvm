//package com.FarmPe.Oxkart.Activity;
//
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.design.widget.Snackbar;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.view.Gravity;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//
//import com.FarmPe.Oxkart.R;
//import com.FarmPe.Oxkart.SessionManager;
//import com.FarmPe.Oxkart.Urls;
//import com.FarmPe.Oxkart.Volly_class.Login_post;
//import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.regex.Pattern;
//
//
//public class VerifyAadhar extends AppCompatActivity{
//
//    LinearLayout sele_loc,selfie_img,main_layout;
//    TextView submit,verify_btn,verify_aadhar_st;
//    EditText aadhar_no,pan_name,pan_num;
//    String status;
//    SessionManager sessionManager;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.verify_aadhar);
//        submit=findViewById(R.id.submit);
//        aadhar_no=findViewById(R.id.aadhar_no);
//        verify_btn=findViewById(R.id.verify_btn);
//        verify_aadhar_st=findViewById(R.id.verify_aadhar_st);
//        pan_name=findViewById(R.id.pan_name);
//        pan_num=findViewById(R.id.pan_num);
//        main_layout=findViewById(R.id.main_layout);
//       // VerifyAadhar.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//
//        sessionManager=new SessionManager(this);
//
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Intent intent=new Intent(VerifyAadhar.this,VerificationMain.class);
//                          startActivity(intent);
////
////                if (aadhar_no.getText().toString().equals("")) {
////                    int duration = 1000;
////                    Snackbar snackbar = Snackbar
////                            .make(main_layout, "Enter 12 digit Aadhar Number.", duration);
////                    View snackbarView = snackbar.getView();
////                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
////                    tv.setBackgroundColor(ContextCompat.getColor(VerifyAadhar.this, R.color.orange));
////                    tv.setTextColor(Color.WHITE);
////                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
////                    } else {
////                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
////                    }
////                    snackbar.show();
////                }else if (pan_name.getText().toString().equals("")){
////                    int duration = 1000;
////                    Snackbar snackbar = Snackbar
////                            .make(main_layout, "Enter name as per PAN card", duration);
////                    View snackbarView = snackbar.getView();
////                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
////                    tv.setBackgroundColor(ContextCompat.getColor(VerifyAadhar.this, R.color.orange));
////                    tv.setTextColor(Color.WHITE);
////                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
////                    } else {
////                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
////                    }
////                    snackbar.show();
////                }else if (pan_num.getText().toString().equals("")){
////                    int duration = 1000;
////                    Snackbar snackbar = Snackbar
////                            .make(main_layout, "Enter PAN Number.", duration);
////                    View snackbarView = snackbar.getView();
////                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
////                    tv.setBackgroundColor(ContextCompat.getColor(VerifyAadhar.this, R.color.orange));
////                    tv.setTextColor(Color.WHITE);
////                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
////                    } else {
////                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
////                    }
////                    snackbar.show();
////                }else if (!(Pattern.compile("[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}").matcher(pan_num.getText().toString()).matches())) {
////                    int duration = 1000;
////                    Snackbar snackbar = Snackbar
////                            .make(main_layout, "Enter Valid Pan Card Number.", duration);
////                    View snackbarView = snackbar.getView();
////                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
////                    tv.setBackgroundColor(ContextCompat.getColor(VerifyAadhar.this, R.color.orange));
////                    tv.setTextColor(Color.WHITE);
////                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
////                    } else {
////                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
////                    }
////                    snackbar.show();
////                }else{
//                //  SubmitDetails();
//              //  }
//                /*Intent intent=new Intent(VerifyAadhar.this,VerificationMain.class);
//                startActivity(intent);*/
//            }
//        });
//        verify_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (aadhar_no.getText().toString().equals("")){
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
//                }else if (!(Pattern.compile("[0-9]{12}").matcher(aadhar_no.getText().toString()).matches())) {
//                    verify_aadhar_st.setVisibility(View.VISIBLE);
//                    verify_aadhar_st.setText("Invalid Aadhar Number");
//                }else{
//                    verify_aadhar_st.setVisibility(View.VISIBLE);
//                    verify_aadhar_st.setText("Aadhar Verification Completed!");
//
//                }
//            }
//        });
//
//    }
//    @Override
//    public void onBackPressed() {
//
//        Intent intent=new Intent(VerifyAadhar.this,LoginActivity_new.class);
//        startActivity(intent);
//    }
//
//    private void SubmitDetails() {
//
////        try {
////
////            JSONObject jsonObject = new JSONObject();
////
////            // jsonObject.put("Id", sessionManager.getRegId("userId"));
////            jsonObject.put("PANCardName", pan_name.getText().toString());
////            jsonObject.put("PANNo", pan_num.getText().toString());
////            jsonObject.put("CreatedBy",sessionManager.getRegId("userId") );
////            jsonObject.put("UserId", sessionManager.getRegId("userId"));
////
////            System.out.println("poooooossttiing_parameters"+jsonObject);
////
////
////            Login_post.login_posting(this, Urls.AddUpdateAadhaarDetails, jsonObject, new VoleyJsonObjectCallback() {
////                @Override
////                public void onSuccessResponse(JSONObject result) {
////                    System.out.println("111111user" + result);
////                    try {
////                        status = result.getString("Status");
////                        // LandingPageActivity.applicationId=status;
////                        System.out.println("ennnnnnppp");
////
////                        if (!(status.equals("0"))){
////                            System.out.println("ennnnnn"+status);
////                            Intent intent=new Intent(VerifyAadhar.this,VerificationMain.class);
////                            startActivity(intent);
////                        }
////
////                    } catch (JSONException e) {
////                        e.printStackTrace();
////                    }
////
////                }
////            });
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//    }
//
//}
