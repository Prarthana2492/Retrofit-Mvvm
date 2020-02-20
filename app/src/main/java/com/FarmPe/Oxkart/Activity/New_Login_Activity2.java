package com.FarmPe.Oxkart.Activity;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.graphics.Color;
import android.net.ConnectivityManager;

import android.os.Bundle;
import android.os.Handler;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;




import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Login_post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;

import com.google.android.gms.auth.api.Auth;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.annotations.Nullable;


import org.json.JSONException;
import org.json.JSONObject;


public class New_Login_Activity2 extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {


    TextView register_btn,login_btn,phone_number_visiblity,mob_no_details,farmer_desc1,farmer_text1;
    LinearLayout linear_layout;
    public static EditText mobile_no;
    SessionManager sessionManager;
    public static boolean connectivity_check;
    ConnectivityReceiver connectivityReceiver;
    public static  String toast_internet,toast_nointernet,userId;
    public static   JSONObject lngObject;
    String status_resp,status;
    private Handler mHandler = new Handler();
    private int nCounter = 0;
    public static String contact_no,localize;

    GoogleApiClient mGoogleApiClient;
    private int RESOLVE_HINT = 2;
    Credential credential;
    String s1;



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
                message = "Good! Connected to Internet";
                color = Color.WHITE;

                int duration=1000;
                Snackbar snackbar = Snackbar.make(linear_layout,toast_internet, duration);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setBackgroundColor(ContextCompat.getColor(New_Login_Activity2.this,R.color.orange));
                textView.setTextColor(Color.WHITE);
                snackbar.show();


                connectivity_check=false;
            }


        } else {
            message = "No Internet Connection";
            color = Color.RED;

            int duration=1000;
            connectivity_check=true;

            Snackbar.make(findViewById(android.R.id.content),toast_nointernet, duration).show();


        }
    }

    @Override
    public void onResume() {
        super.onResume();

        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        connectivityReceiver = new ConnectivityReceiver();
        registerReceiver(connectivityReceiver, intentFilter);
        MyApplication.getInstance().setConnectivityListener(this);


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_login_page_screen);
        checkConnection();
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();



        login_btn =findViewById(R.id.login_btn);
        register_btn =findViewById(R.id.register_btn);
        linear_layout =findViewById(R.id.linear_layout);
        mobile_no =findViewById(R.id.mobile_no);
        mob_no_details =findViewById(R.id.mob_no_details);
        farmer_text1 =findViewById(R.id.farmer_text1);
        farmer_desc1 =findViewById(R.id.farmer_desc1);

        contact_no =  mobile_no.getText().toString();


        mGoogleApiClient = new GoogleApiClient.Builder(New_Login_Activity2.this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .addApi(Auth.CREDENTIALS_API)
                .build();


        setupUI(linear_layout);




        getHintPhoneNumber();


        try {


            lngObject = new JSONObject(sessionManager.getRegId("language"));

            System.out.println("llllllllllllkkkkkkkkkkkkkkk" + lngObject.getString("EnterPhoneNo"));

            mob_no_details.setText(lngObject.getString("Enteryourphonenumbertogetstarted").replace("\n",""));
            farmer_text1.setText(lngObject.getString("MadeforFarmingCommunity"));
            farmer_desc1.setText(lngObject.getString("Theconfluenceoffarmersandfairtrade"));



            //  pass.setHint(lngObject.getString("Password"));
            //  remember_me.setText(lngObject.getString("RememberMe"));





        } catch (JSONException e) {
            e.printStackTrace();
        }





        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                contact_no =  mobile_no.getText().toString();


                if(contact_no.equals("")){

                    Toast toast = Toast.makeText(New_Login_Activity2.this, "Please Enter Phone Number To Proceed", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                    TextView toastMessage=(TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage.setTextColor(Color.WHITE);
                    toast.getView().setBackgroundResource(R.drawable.black_curve_background);
                    toast.show();


                }else if(contact_no.length()<10){

                    Toast toast = Toast.makeText(New_Login_Activity2.this, "Please Enter 10 Digit Mobile Number", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                    TextView toastMessage1=(TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage1.setTextColor(Color.WHITE);
                    toast.getView().setBackgroundResource(R.drawable.black_curve_background);
                    toast.show();


                }else{

                    check_login_user();

                }

            }
        });


        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                contact_no =  mobile_no.getText().toString();

                System.out.println("uryuewyuwe" + contact_no);



                if(contact_no.equals("")) {

                    Toast toast = Toast.makeText(New_Login_Activity2.this, "Please Enter Phone Number To Proceed", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
                    TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage.setTextColor(Color.WHITE);
                    toast.getView().setBackgroundResource(R.drawable.black_curve_background);
                    toast.show();


                }else if(contact_no.length()<10){

//                    Toast toast = Toast.makeText(New_Login_Activity2,"Please Click Back Again To Exit", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
//                    toast.show();
////
//                    Toast toast = Toast.makeText(New_Login_Activity2.this, "Please Enter 10 Digit Mobile Number", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
//                    TextView toastMessage1=(TextView) toast.getView().findViewById(android.R.id.message);
//                    toastMessage1.setTextColor(Color.WHITE);
//                    toast.getView().setBackgroundResource(R.drawable.black_curve_background);
//                    toast.show();


//                    Toast toast = Toast.makeText(getApplicationContext(), "Please Enter Phone Number To Proceed", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);

                    //    toast.show();


                }else{

                    login_register();

                }
            }
        });
    }


    public void getHintPhoneNumber() {
        HintRequest hintRequest =
                new HintRequest.Builder()
                        .setPhoneNumberIdentifierSupported(true)
                        .build();
        PendingIntent mIntent = Auth.CredentialsApi.getHintPickerIntent(mGoogleApiClient, hintRequest);
        try {
            startIntentSenderForResult(mIntent.getIntentSender(), RESOLVE_HINT, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Result if we want hint number
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == Activity.RESULT_OK) {
                credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                System.out.println("fgd"+credential);

                 s1 = credential.getId().substring(3);

                //contact_no = mobile_no.getText().toString();
                //  System.out.println("uryuewyuwe" + contact_no);
                check_login_user2();
                // login_register();
            }
            // credential.getId();  <-- will need to process phone number string
            //  mobile_no.setText(credential.getId());
        }
    }


    private void check_login_user2() {



        try{

            JSONObject jsonObject = new JSONObject();
            JSONObject post_Object = new JSONObject();

            jsonObject.put("PhoneNo",s1);
            post_Object.put("UserRequest",jsonObject);
            System.out.println("postobjj"+post_Object);


            Login_post.login_posting(New_Login_Activity2.this, Urls.New_Login_Details,post_Object, new VoleyJsonObjectCallback()  {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("111111user" + result);

                    try{
                        JSONObject jsonObject;
                        JSONObject userObject;

                        jsonObject = result.getJSONObject("ResultObject");


                        if(!(jsonObject.isNull("user"))) {

                            userObject = jsonObject.getJSONObject("user");
                            status = jsonObject.getString("Status");
                            String status1 = jsonObject.getString("OTP");
                            userId = jsonObject.getString("UserId");
                            System.out.println("useridddduserId" + userId);
       /*                     sessionManager.save_name(jsonObject.getString("PhoneNo"));
                            //   sessionManager.save_name(userObject.getString("FullName"),userObject.getString("PhoneNo"),userObject.getString("ProfilePic"));
                            sessionManager.saveUserId(userId);

                            System.out.println("useridddd" + mobile_no.getText().toString());

*/
                            sessionManager.createLoginSession(contact_no);
                            sessionManager.save_name(userObject.getString("PhoneNo"));
                            System.out.println("useriddddsaveee" + sessionManager.getRegId("phone"));
                            //   sessionManager.save_name(userObject.getString("FullName"),userObject.getString("PhoneNo"),userObject.getString("ProfilePic"));
                            sessionManager.saveUserId(userId);
                            System.out.println("useriddddsaveee"+sessionManager);



                            if ((status.equals("1"))) {

                                System.out.println("jdhyusulogin" + status);
                                Intent intent = new Intent(New_Login_Activity2.this, New_OTP_Page_Activity.class);
                                intent.putExtra("otpnumber", status1);
                                intent.putExtra("register_status","login_btn");
                                startActivity(intent);

                                //    sessionManager.createRegisterSession(contact_no);
                            }

//                        }else{


//                            Toast toast = Toast.makeText(New_Login_Activity2.this, "User Not Registered", Toast.LENGTH_SHORT);
//                            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
//                            TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
//                            toastMessage.setTextColor(Color.WHITE);
//                            toast.getView().setBackgroundResource(R.drawable.black_curve_background);
//                            toast.show();

                        }


                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }




//        try{
//
//            JSONObject jsonObject = new JSONObject();
//            JSONObject post_Object = new JSONObject();
//
//            jsonObject.put("PhoneNo",credential.getId());
//            post_Object.put("UserRequest",jsonObject);
//            System.out.println("postobjj"+post_Object);
//
//
//            Login_post.login_posting(New_Login_Activity2.this, Urls.New_Login_Details,post_Object, new VoleyJsonObjectCallback()  {
//                @Override
//                public void onSuccessResponse(JSONObject result) {
//                    System.out.println("111111user" + result);
//
//                    try{
//                        JSONObject jsonObject;
//                        JSONObject userObject;
//
//                        jsonObject = result.getJSONObject("ResultObject");
//
//
//                        if(!(jsonObject.isNull("user"))) {
//
//                            userObject = jsonObject.getJSONObject("user");
//                            status = jsonObject.getString("Status");
//                            String status1 = jsonObject.getString("OTP");
//                            userId = jsonObject.getString("UserId");
//                            System.out.println("useridddduserId" + userId);
//       /*                     sessionManager.save_name(jsonObject.getString("PhoneNo"));
//                            //   sessionManager.save_name(userObject.getString("FullName"),userObject.getString("PhoneNo"),userObject.getString("ProfilePic"));
//                            sessionManager.saveUserId(userId);
//
//                            System.out.println("useridddd" + mobile_no.getText().toString());
//
//*/
//                            sessionManager.createLoginSession(contact_no);
//                            sessionManager.save_name(userObject.getString("PhoneNo"));
//                            //   sessionManager.save_name(userObject.getString("FullName"),userObject.getString("PhoneNo"),userObject.getString("ProfilePic"));
//                            sessionManager.saveUserId(userId);
//                            System.out.println("useriddddsaveee"+sessionManager);
//
//
//
//                            if ((status.equals("1"))) {
//
//                                System.out.println("jdhyusulogin" + status);
//                                Intent intent = new Intent(New_Login_Activity2.this, New_OTP_Page_Activity.class);
//                                intent.putExtra("otpnumber", status1);
//                                intent.putExtra("register_status","login_btn");
//                                startActivity(intent);
//
//                                //    sessionManager.createRegisterSession(contact_no);
//                            }
//
////                        }else{
//
//
////                            Toast toast = Toast.makeText(New_Login_Activity2.this, "User Not Registered", Toast.LENGTH_SHORT);
////                            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
////                            TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
////                            toastMessage.setTextColor(Color.WHITE);
////                            toast.getView().setBackgroundResource(R.drawable.black_curve_background);
////                            toast.show();
//
//                        }
//
//
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }

    private void check_login_user() {



        try{

            JSONObject jsonObject = new JSONObject();
            JSONObject post_Object = new JSONObject();

            jsonObject.put("PhoneNo",mobile_no.getText().toString());
            post_Object.put("UserRequest",jsonObject);
            System.out.println("postobjj"+post_Object);


            Login_post.login_posting(New_Login_Activity2.this, Urls.New_Login_Details,post_Object, new VoleyJsonObjectCallback()  {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("111111user" + result);

                    try{
                        JSONObject jsonObject;
                        JSONObject userObject;

                        jsonObject = result.getJSONObject("ResultObject");


                        if(!(jsonObject.isNull("user"))) {

                            userObject = jsonObject.getJSONObject("user");
                            status = jsonObject.getString("Status");
                            String status1 = jsonObject.getString("OTP");
                            userId = jsonObject.getString("UserId");
                            System.out.println("useridddduserId" + userId);
       /*                     sessionManager.save_name(jsonObject.getString("PhoneNo"));
                            //   sessionManager.save_name(userObject.getString("FullName"),userObject.getString("PhoneNo"),userObject.getString("ProfilePic"));
                            sessionManager.saveUserId(userId);

                            System.out.println("useridddd" + mobile_no.getText().toString());

*/
                            sessionManager.createLoginSession(contact_no);
                            sessionManager.save_name(userObject.getString("PhoneNo"));
                            System.out.println("useriddddsaveee" + sessionManager.getRegId("phone"));
                            //   sessionManager.save_name(userObject.getString("FullName"),userObject.getString("PhoneNo"),userObject.getString("ProfilePic"));
                            sessionManager.saveUserId(userId);
                            System.out.println("useriddddsaveee"+sessionManager);



                            if ((status.equals("1"))) {

                                System.out.println("jdhyusulogin" + status);
                                Intent intent = new Intent(New_Login_Activity2.this, New_OTP_Page_Activity.class);
                                intent.putExtra("otpnumber", status1);
                                intent.putExtra("register_status","login_btn");
                                startActivity(intent);

                                //    sessionManager.createRegisterSession(contact_no);
                            }

//                        }else{


//                            Toast toast = Toast.makeText(New_Login_Activity2.this, "User Not Registered", Toast.LENGTH_SHORT);
//                            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
//                            TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
//                            toastMessage.setTextColor(Color.WHITE);
//                            toast.getView().setBackgroundResource(R.drawable.black_curve_background);
//                            toast.show();

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

    private void login_register() {

        try {
            JSONObject userRequestjsonObject = new JSONObject();
            JSONObject postjsonObject = new JSONObject();

            userRequestjsonObject.put("PhoneNo", mobile_no.getText().toString());
            userRequestjsonObject.put("IsOTPVerified", 1);
            postjsonObject.putOpt("objUser", userRequestjsonObject);
            System.out.println("post_oobject" + postjsonObject);


            Login_post.login_posting(New_Login_Activity2.this, Urls.New_Register_Details, postjsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("statusssssslllll" + result);
                    JSONObject jsonObject = new JSONObject();
                    JSONObject jsonObject_resp = new JSONObject();


                    try {
                        if (result.isNull("user")) {
                            jsonObject_resp = result.getJSONObject("Response");
                            status_resp = jsonObject_resp.getString("Status");


                            Toast toast = Toast.makeText(New_Login_Activity2.this, "User Already Registered", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
                            TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                            toastMessage.setTextColor(Color.WHITE);
                            toast.getView().setBackgroundResource(R.drawable.black_curve_background);
                            toast.show();
                            // sessionManager.saveUserId(jsonObject_resp.getString("Id"));
                            //  sessionManager.save_name(jsonObject_resp.getString("FullName"),jsonObject_resp.getString("PhoneNo"),jsonObject_resp.getString("ProfilePic"));
//                            Intent intent = new Intent(New_Login_Activity2.this, FirmShopDetailsActivity.class);
//                            startActivity(intent);
                            // sessionManager.createRegistrSession(NewSignUpActivity.contact);


                        } else {
                            jsonObject = result.getJSONObject("user");
                            jsonObject_resp = result.getJSONObject("Response");
                            status_resp = jsonObject_resp.getString("Status");
                            status = jsonObject.getString("OTP");
                            String userid = jsonObject.getString("Id");
                            System.out.println("useerrrriidd" + status);
                            //  sessionManager.createRegisterSession(name_text,contact,password_text);

                            sessionManager.saveUserId(userid);
                            sessionManager.save_name(jsonObject.getString("PhoneNo"));

                            System.out.println("weyfhjxdbfv" + jsonObject.getString("PhoneNo"));
                            // sessionManager.save_name(jsonObject.getString("FullName"), jsonObject.getString("PhoneNo"),jsonObject.getString("ProfilePic"));

                            Intent intent = new Intent(New_Login_Activity2.this, New_OTP_Page_Activity.class);
                            intent.putExtra("otpnumber", status);
                            intent.putExtra("register_status","register_btn");
                            startActivity(intent);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

//
//        try {
//
//            // mobile=mob_no;
//            JSONObject userRequestjsonObject = new JSONObject();
//            JSONObject postjsonObject = new JSONObject();
//
//            userRequestjsonObject.put("PhoneNo",mobile_no.getText().toString());
//            userRequestjsonObject.put("IsOTPVerified","1");
//
//            postjsonObject.putOpt("objUser", userRequestjsonObject);
//
//            System.out.println("post_oobject" + postjsonObject);
//
//
//
//            Login_post.login_posting(New_Login_Activity2.this, Urls.New_Register_Details, postjsonObject, new VoleyJsonObjectCallback() {
//                @Override
//                public void onSuccessResponse(JSONObject result) {
//                    System.out.println("statussssss" + result);
//
//                    JSONObject jsonObject = new JSONObject();
//                    JSONObject jsonObject_resp = new JSONObject();
//
//
//                    try {
//                        if (result.isNull("user")) {
//
//                            jsonObject_resp = result.getJSONObject("Response");
//                            status_resp = jsonObject_resp.getString("Status");
//
//
//                            Toast.makeText(New_Login_Activity2.this, "User Already Registered", Toast.LENGTH_LONG).show();
//
//
//                        } else {
//
//                            jsonObject = result.getJSONObject("user");
//                            status = jsonObject.getString("OTP");
//                            String userid = jsonObject.getString("Id");
//                            System.out.println("useerrrriidd" + userid);
//                            jsonObject_resp = result.getJSONObject("Response");
//                            status_resp = jsonObject_resp.getString("Status");
//                            sessionManager.saveUserId(userid);
//                         //  sessionManager.save_name(jsonObject.getString("FullName"), jsonObject.getString("PhoneNo"),jsonObject.getString("ProfilePic"));
//                            sessionManager.save_name(jsonObject.getString("PhoneNo"));
//
//
//                            if(!(status_resp.equals("0"))){
//
//                                Intent intent = new Intent(New_Login_Activity2.this, New_OTP_Page_Activity.class);
//                                intent.putExtra("otpnumber", status);
//                                startActivity(intent);
//
//                            }
//
//
//                        }
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }



    public void setupUI(View view) {


        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(New_Login_Activity2.this);
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


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}