package com.renewin.FarmPe.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.renewin.FarmPe.Activity.LoginActivity;
import com.renewin.FarmPe.Activity.SignUpActivity;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.ReadSms;
import com.renewin.FarmPe.SessionManager;
import com.renewin.FarmPe.SmsListener;
import com.renewin.FarmPe.Urls;
import com.renewin.FarmPe.Volly_class.Login_post;
import com.renewin.FarmPe.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;


public class EnterOTP extends AppCompatActivity {
    TextView submit,otp_title,otp_text;
    LinearLayout close;
    String otp;
    EditText otpedittext;
    public  static String sessionId,otp_get_text;
    public static String contact;
    BroadcastReceiver receiver;
    Vibrator vibe;
    TextView resendotp;
    JSONObject lngObject;
    LinearLayout linearLayout;
    private Context mContext=EnterOTP.this;
    private static final int REQUEST=1;
    Fragment selectedFragment;
    SessionManager sessionManager;
    LinearLayout left_arrow;



    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(receiver);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_layout);
        vibe = (Vibrator) getApplication().getSystemService(Context.VIBRATOR_SERVICE);
        // name_back=findViewById(R.id.name_back);
        submit=findViewById(R.id.otp_submit);
        otp_title=findViewById(R.id.thank);
        otpedittext=findViewById(R.id.otp);
        otp_text=findViewById(R.id.thanktu);
        linearLayout=findViewById(R.id.main_layout);
       // close=findViewById(R.id.arrow_thank_u);
        left_arrow=findViewById(R.id.left_arow);




        sessionId= getIntent().getStringExtra("otpnumber");


        resendotp=findViewById(R.id.resend);

        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    JSONObject postjsonObject = new JSONObject();
                    postjsonObject.put("UserName", SignUpActivity.contact );
                    System.out.println("rrrrrrrrrrrrrrrrrrrr" + postjsonObject);

                    Login_post.login_posting(EnterOTP.this, Urls.ResendOTP, postjsonObject, new VoleyJsonObjectCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject result) {

                            System.out.println("kkkkkkkkkkkkkkkkkkkkkkkk" + result.toString());
                            try{

                                String  Otp = result.getString("OTP");
                                sessionId=Otp;
                                String  Message = result.getString("Message");
                                int  status= result.getInt("Status");

                                if (status==2){
                                    Snackbar snackbar = Snackbar
                                            .make(linearLayout, result.getString("Message"), Snackbar.LENGTH_LONG);
                                    //snackbar.setActionTextColor(R.color.colorAccent);
                                    View snackbarView = snackbar.getView();
                                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                    tv.setTextColor(Color.RED);
                                    snackbar.show();
                                }
                                else {
                                    Toast.makeText(EnterOTP.this, Message, Toast.LENGTH_LONG).show();
                                }
                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });



        setupUI(linearLayout);
        left_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EnterOTP.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        sessionManager = new SessionManager(this);
        // sessionManager.getRegId("lng_object");
        // System.out.println("llllllllllll" + sessionManager.getRegId("lng_object"));

        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));

            submit.setText(lngObject.getString("SendOTP"));
            otp_title.setText(lngObject.getString("OTP"));
            otp_text.setText(lngObject.getString("EnterOTP"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (checkAndRequestPermissions()) {
            // carry on the normal flow, as the case of  permissions  granted.
        }

        System.out.println("qwertyuio"+sessionId);
      /*  close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               *//* Intent intent=new Intent(EnterOTP.this,SignUp.class);
                startActivity(intent);*//*
            }
        });*/

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });


        ReadSms.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                System.out.println("llllllllllllllllllllllllllllllllllllllllllllotp"+messageText);

                otpedittext.setText(messageText);
            }
        });

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equalsIgnoreCase("otp")) {
                    final String message = intent.getStringExtra("message");
                }
            }
        };

//System.out.println("qwertyuisdfgh"+SignUp.contact);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp_get_text=otpedittext.getText().toString();
                System.out.println("entered_otp"+otp_get_text);
               /* Intent intent=new Intent(EnterOTP.this,Login.class);
                startActivity(intent);*/


                if (otp_get_text.equals("")){
                   // otp_get_text.requestFocus();
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter OTP", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();
                }

               else{

                    Intent intent=new Intent(EnterOTP.this, LoginActivity.class);
                    startActivity(intent);
                 //   Toast.makeText(EnterOTP.this,"Incorrect OTP",Toast.LENGTH_SHORT).show();

                }
            }

        });
    }

    @Override
    public void onBackPressed() {
        //System.exit(0);

        Intent intent=new Intent(EnterOTP.this,SignUpActivity.class);
        startActivity(intent);
    }

    private  boolean checkAndRequestPermissions() {

        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.READ_SMS};
            if (!hasPermissions(mContext, PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST);
            } else {
                //do here
            }
        } else {
            //do here
        }
        return true;
    }
    private static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(EnterOTP.this);
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
 /*InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);*/

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
