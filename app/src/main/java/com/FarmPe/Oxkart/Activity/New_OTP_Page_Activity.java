package com.FarmPe.Oxkart.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.ReadSms;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.SmsListener;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.Login_post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;
import com.chaos.view.PinView;

import org.json.JSONException;
import org.json.JSONObject;




public class New_OTP_Page_Activity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{


    String toast_internet,toast_nointernet;
    JSONObject lngObject,verify_status;
    SessionManager sessionManager;
    LinearLayout main_layout,back_feed,regiter_backgrd;
    TextView register_submit,timer,mobile_number_text,otpsenttxt,otp_sent_to,enter_otp_here,otp_receive;
    String sessionId,toast_enter_otp,toast_invalid_otp;
    EditText edit_tOTP;
    ProgressBar otp_sent;
    ImageView otpsentimg;
    BroadcastReceiver receiver;
    PinView pinView;
    Vibrator vibe;
    public static boolean connectivity_check;
    ConnectivityReceiver connectivityReceiver;



    @Override
    protected void onStop()
    {
        unregisterReceiver(connectivityReceiver);
        super.onStop();
    }


    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }


    private void showSnack(boolean isConnected) {
        String message = null;
        int color=0;
        if (isConnected) {
            if(connectivity_check) {


                Toast toast = Toast.makeText(New_OTP_Page_Activity.this,toast_internet, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                toast.show();




//                message = "Good! Connected to Internet";
//                color = Color.WHITE;
//                int duration=1000;
//                Snackbar snackbar = Snackbar.make(main_layout,toast_internet, duration);
//                View sbView = snackbar.getView();
//                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
//                textView.setBackgroundColor(ContextCompat.getColor(New_OTP_Page_Activity.this,R.color.orange));
//                textView.setTextColor(Color.WHITE);
//                snackbar.show();


                connectivity_check=false;
            }

        } else {
            message = "No Internet Connection";
            color = Color.RED;

            int duration=1000;
            connectivity_check=true;


            Toast toast = Toast.makeText(New_OTP_Page_Activity.this,toast_nointernet, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
            toast.show();





            //  Snackbar.make(findViewById(android.R.id.content),toast_nointernet, duration).show();
        }
    }


    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, new IntentFilter("otpnumber"));
        super.onResume();

        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        connectivityReceiver = new ConnectivityReceiver();
        registerReceiver(connectivityReceiver, intentFilter);

        MyApplication.getInstance().setConnectivityListener(this);

    }


//    @Override
//    public void onPause() {
//        super.onPause();
//
//        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(receiver);
//    }
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_otp);

        timer = findViewById(R.id.timer);
        register_submit = findViewById(R.id.register_submit);
        otp_sent = findViewById(R.id.otp_sent);
        back_feed = findViewById(R.id.back_feed);
        pinView = findViewById(R.id.pinView);
        main_layout = findViewById(R.id.main_layout);
        mobile_number_text = findViewById(R.id.mobile_number_text);
        otp_sent = findViewById(R.id.otp_sent);
        otpsentimg = findViewById(R.id.otpsent);
        otpsenttxt = findViewById(R.id.otpsenttxt);
        otp_sent_to = findViewById(R.id.otp_sent_to);
        enter_otp_here = findViewById(R.id.enter_otp_here);
        otp_receive = findViewById(R.id.otp_receive);
        regiter_backgrd = findViewById(R.id.regiter_backgrd);

        sessionManager = new SessionManager(this);

        checkConnection();



        try {


            lngObject = new JSONObject(sessionManager.getRegId("language"));

            System.out.println("llllllllllllkkkkkkkkkkkkkkk" + lngObject.getString("EnterPhoneNo"));

            otp_sent_to.setText(lngObject.getString("OTPSentto"));
            enter_otp_here.setText(lngObject.getString("EnterOTPhere"));
            otp_receive.setText(lngObject.getString("DidntreceiveOTP"));
            otpsenttxt.setText(lngObject.getString("OTPSent").replace("\n",""));
            toast_internet = lngObject.getString("GoodConnectedtoInternet");
            toast_nointernet = lngObject.getString("NoInternetConnection");
            toast_enter_otp = lngObject.getString("EntertheOTP");
            toast_invalid_otp = lngObject.getString("InvalidOTP");



            //  pass.setHint(lngObject.getString("Password"));
            //  remember_me.setText(lngObject.getString("RememberMe"));





        } catch (JSONException e) {
            e.printStackTrace();
        }





        if(getIntent().getExtras().getString("register_status").equals("login_btn")){


            register_submit.setText("LOGIN");




        }else if(getIntent().getExtras().getString("register_status").equals("register_btn")){


            register_submit.setText("REGISTER");

        }


        setupUI(main_layout);

        mobile_number_text.setText(sessionManager.getRegId("phone"));


       // System.out.println("djhfjhvxcv" + sessionManager.getRegId("phone"));


        vibe = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

        sessionId = getIntent().getStringExtra("otpnumber");



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                otpsentimg.setVisibility(View.VISIBLE);
                otpsenttxt.setVisibility(View.VISIBLE);
                otp_sent.setVisibility(View.GONE);


            }
        }, 8000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                otpsentimg.setVisibility(View.GONE);
                otpsenttxt.setVisibility(View.GONE);
                otp_sent.setVisibility(View.GONE);


            }
        }, 9000);





        new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {

                timer.setText("00 :" + millisUntilFinished / 1000);
            }

            public void onFinish() {

                timer.setText("RESEND");
                timer.setBackgroundResource(R.drawable.border_curved_red);


                timer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                      resend_otp();

                    }
                });

                //timer.setText("done!");
            }

        }.start();



        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(New_OTP_Page_Activity.this,New_Login_Activity2.class);
                startActivity(intent);

            }
        });



        pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                if(pinView.getText().toString().equals("")){


                    otp_sent.setVisibility(View.GONE);
                    regiter_backgrd.setBackgroundResource(R.drawable.grey_curved_border);


                }else if(!(pinView.getText().toString().equals(sessionId))){

                    otp_sent.setVisibility(View.GONE);
                    regiter_backgrd.setBackgroundResource(R.drawable.grey_curved_border);

                } else {

                    regiter_backgrd.setBackgroundResource(R.drawable.border_filled_red_not_curved);

                   // otp_sent.setVisibility(View.VISIBLE);
                }

            }
        });


        ReadSms.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                System.out.println("llllllllllllllllllllllllllllllllllllllllllllotp"+messageText);

              //  pinView.setText(messageText);


            }
        });



        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction().equalsIgnoreCase("otpnumber")) {

                    final String message = intent.getStringExtra("message");
                }
            }
        };




        register_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pinView.getText().toString().equals("")) {



                    Toast toast = Toast.makeText(New_OTP_Page_Activity.this,toast_enter_otp, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                    toast.show();



                } else if (pinView.getText().toString().equals(sessionId)){

                    if(getIntent().getExtras().getString("register_status").equals("login_btn")){


                        verification_status();


                    }else if(getIntent().getExtras().getString("register_status").equals("register_btn")){


                        Intent intent = new Intent(New_OTP_Page_Activity.this,Verification_Activity.class);
                        startActivity(intent);
                        sessionManager.createRegisterSession(New_Login_Activity2.contact_no);


                    }


//                    Intent intent = new Intent(New_OTP_Page_Activity.this,Verification_Activity.class);
//                    startActivity(intent);
//                    sessionManager.createRegisterSession(New_Login_Activity2.contact_no);


                }else{


                    Toast toast = Toast.makeText(New_OTP_Page_Activity.this,toast_invalid_otp, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                    toast.show();


                }

            }
        });

    }

    private void verification_status() {


        try{

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));
            System.out.println("deyuiirwe" + sessionManager.getRegId("userId"));



            Crop_Post.crop_posting(this, Urls.Get_Verification_Status, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("ghdgfd" + result);

                    try{

                        verify_status = result.getJSONObject("VerificationStatus");

                        Boolean user_uploaded = verify_status.getBoolean("IsUserUploaded");


                        if(user_uploaded.equals(false)){


                             Intent intent = new Intent(New_OTP_Page_Activity.this,Privacy_Activity.class);
                             startActivity(intent);
                            sessionManager.createRegisterSession(sessionManager.getRegId("phone"));



                             }else{


                            Intent intent= new Intent(New_OTP_Page_Activity.this,Privacy_Activity.class);
                            startActivity(intent);
                            sessionManager.createRegisterSession(sessionManager.getRegId("phone"));


                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private void resend_otp() {


        try{

            JSONObject postjsonObject = new JSONObject();
            postjsonObject.put("PhoneNo", sessionManager.getRegId("phone") );
            System.out.println("sdfsfhusdff" + sessionManager.getRegId("phone"));


            Login_post.login_posting(New_OTP_Page_Activity.this, Urls.ResendOTP, postjsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("sdfsfhusdff" + result);


                    try{

                        String  Otp = result.getString("OTP");
                        sessionId = Otp;

                        String  message = result.getString("Message");
                        int  status= result.getInt("Status");


                        if (status==1){


                            Toast toast = Toast.makeText(New_OTP_Page_Activity.this,message, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                            toast.show();



                        } else if (status==2){


                            Toast toast = Toast.makeText(New_OTP_Page_Activity.this,message, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                            toast.show();



                        }

                        else {



                            Toast toast = Toast.makeText(New_OTP_Page_Activity.this,message, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                            toast.show();

                            //  Toast.makeText(New_OTP_Page_Activity.this, message, Toast.LENGTH_LONG).show();
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


    public void setupUI(View view) {


        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(New_OTP_Page_Activity.this);
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

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);

    }
}