package com.FarmPe.Farmer.Activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.FarmPe.Farmer.Bean.AddPhotoBean;
import com.FarmPe.Farmer.Fragment.HomeMenuFragment;

import com.FarmPe.Farmer.G_Vision_Controller;
import com.FarmPe.Farmer.GpsService;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Volly_class.PackageManagerUtils;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequest;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.api.services.vision.v1.model.SafeSearchAnnotation;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;


public class LandingPageActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{
    public static TextView name,price;
    Fragment selectedFragment = null;
    public static ImageView cart_img;
    View Profile,feedback_view,invite_view;
    JSONObject lngObject;
    String toast_internet,toast_nointernet;
    CoordinatorLayout coordinate_layout;
    SessionManager sessionManager;
    public static Bitmap selectedImage;
    public static EditText editname;

    public  static Activity activity;
    public static String toast_click_back;
    boolean doubleBackToExitPressedOnce = false;
    private static final int MAX_DIMENSION = 200;
    public static BottomSheetBehavior mBottomSheetBehavior6,mBottomSheetBehavior5,mBottomSheetBehavior4;
    public static TextView name_hint,cancel,save,logout,cancel_feed,cancel_invite,prof_name,buy_now;

    private static final String TAG = LandingPageActivity.class.getSimpleName();


    public static boolean connectivity_check;
    ConnectivityReceiver connectivityReceiver;

    SafeSearchAnnotation annotation;

    G_Vision_Controller g_vision_controller;
    Bitmap compressedbitmap;
    private static final String CLOUD_VISION_API_KEY = "AIzaSyASLfdH5Tr931zKrsdH2alWHPxMg6NzD-A";
    public static final String FILE_NAME = "temp.jpg";
    private static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";
    private static final int MAX_LABEL_RESULTS = 10;
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
                Snackbar snackbar = Snackbar.make(coordinate_layout,toast_internet, duration);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setBackgroundColor(ContextCompat.getColor(LandingPageActivity.this,R.color.orange));
                textView.setTextColor(Color.WHITE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                } else {
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                }
                snackbar.show();


                connectivity_check=false;
            }

        } else {
            message = "No Internet Connection";
            color = Color.RED;

            connectivity_check=true;

            int duration=1000;
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), toast_nointernet, duration);
            View sb = snackbar.getView();
            TextView textView = (TextView) sb.findViewById(android.support.design.R.id.snackbar_text);
            textView.setBackgroundColor(ContextCompat.getColor(LandingPageActivity.this, R.color.orange));
            textView.setTextColor(Color.WHITE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            } else {
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
            }


            snackbar.show();


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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("priyanka"+requestCode);
        System.out.println("priyanka"+resultCode);
        g_vision_controller = G_Vision_Controller.getInstance( );



        if (requestCode == 100&& resultCode == RESULT_OK && data != null) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream =getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
           AddPhotoBean img1=new AddPhotoBean( selectedImage);
                if (!(selectedImage==null)){
                    g_vision_controller.callCloudVision(selectedImage,this,"farm");




                }else {
                    Toast.makeText(getApplicationContext(), "You haven't picked Image",Toast.LENGTH_LONG).show();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();

                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        checkConnection();

        coordinate_layout=findViewById(R.id.coordinator);
        name_hint=findViewById(R.id.enter_name_text);
        coordinate_layout=findViewById(R.id.coordinator);
        editname=findViewById(R.id.enter_name);
        cancel=findViewById(R.id.cancel);
        cancel_feed=findViewById(R.id.cancel_feed);
        cancel_invite=findViewById(R.id.cancel_invite);
        save=findViewById(R.id.save);
        logout=findViewById(R.id.logout);

        sessionManager = new SessionManager(this);
        activity= this;

        System.out.println("landiiiiiing");


        Window window = activity.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(activity,R.color.colorPrimaryDark));
      //  LandingPageActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);




        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));

            toast_click_back = lngObject.getString("PleaseclickBACKagaintoexit");
            toast_internet = lngObject.getString("GoodConnectedtoInternet");
            toast_nointernet = lngObject.getString("NoInternetConnection");



        } catch (JSONException e) {
            e.printStackTrace();
        }



        System.out.println("landiiiiiing");

        selectedFragment = HomeMenuFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();




        Profile = findViewById(R.id.profile_view);
        feedback_view = findViewById(R.id.feedback_view);
        invite_view = findViewById(R.id.invite_view);

        mBottomSheetBehavior6 = BottomSheetBehavior.from(Profile);
        mBottomSheetBehavior6.setPeekHeight(0);

        mBottomSheetBehavior5 = BottomSheetBehavior.from(feedback_view);
        mBottomSheetBehavior5.setPeekHeight(0);

        mBottomSheetBehavior4 = BottomSheetBehavior.from(invite_view);
        mBottomSheetBehavior4.setPeekHeight(0);

        mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior5.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior4.setState(BottomSheetBehavior.STATE_COLLAPSED);

        mBottomSheetBehavior6.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {

                }
                else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                }
                else if (newState == BottomSheetBehavior.STATE_HIDDEN) {

                }
            }


            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }

        });


        mBottomSheetBehavior5.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {

                }
                else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                }
                else if (newState == BottomSheetBehavior.STATE_HIDDEN) {

                }
            }


            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }

        });

        mBottomSheetBehavior4.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {

                }
                else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                }
                else if (newState == BottomSheetBehavior.STATE_HIDDEN) {

                }
            }


            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }

        });
    }
















    @Override
    public void onBackPressed() {
       super.onBackPressed();

//
//            HomeMenuFragment.onBack_status = "no_request";
//            selectedFragment = HomeMenuFragment.newInstance();
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.frame_layout, selectedFragment);
//            transaction.commit();

    }



    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);

    }



















}
