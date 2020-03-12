package com.FarmPe.Oxkart.Activity;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.FarmPe.Oxkart.Bean.FarmsImageBean;
import com.FarmPe.Oxkart.Fragment.PrivacyPolicyFragment;
import com.FarmPe.Oxkart.Fragment.Verification_Last_Fragment;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class R_U_Farmer_Activity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{


    TextView toolbar_title;
    Fragment selectedFragment;
    String toast_internet,toast_nointernet;
    EditText user_name;
    RadioGroup radio_group_farmer;
    JSONObject lngObject;
    SessionManager sessionManager;
    String stat="2";
    String status,message;
    LinearLayout back_feed,main_layout,continuebtn;



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
                message = "Good! Connected to Internet";
                color = Color.WHITE;
                int duration= 1000;

                Snackbar.make(findViewById(android.R.id.content),toast_nointernet, duration).show();

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
        setContentView(R.layout.r_u_farmer_layout);
        checkConnection();
        sessionManager = new SessionManager(this);

        back_feed=findViewById(R.id.back_feed);
        main_layout=findViewById(R.id.main_layout);
        toolbar_title=findViewById(R.id.toolbar_title);
        continuebtn=findViewById(R.id.continuebtn);
        radio_group_farmer=findViewById(R.id.radio_group_farmer);
        user_name=findViewById(R.id.user_name);


         setupUI(main_layout);

        //  profile_view.setVisibility(View.GONE);



        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));



            toast_internet = lngObject.getString("GoodConnectedtoInternet");
            toast_nointernet = lngObject.getString("NoInternetConnection");



        } catch (JSONException e) {
            e.printStackTrace();
        }




        radio_group_farmer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (user_name.getText().toString().equals("")) {

                    continuebtn.setBackgroundResource(R.drawable.grey_curved_border);


                }else {

                    continuebtn.setBackgroundResource(R.drawable.border_filled_red_not_curved);

                }
                RadioButton radioButton = group.findViewById(checkedId);


                if (radioButton.getTag().toString().equals("1")) {
                    stat = "1";


                } else if (radioButton.getTag().toString().equals("2")) {
                    stat = "2";



                }else{


                }

            }

        });



        user_name.addTextChangedListener(new TextWatcher() {
            @Override

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (radio_group_farmer.getCheckedRadioButtonId() != -1)
                {
                    // no radio buttons are checked
                    if (user_name.getText().toString().equals("")) {

                        continuebtn.setBackgroundResource(R.drawable.grey_curved_border);


                    } else {

                        continuebtn.setBackgroundResource(R.drawable.border_filled_red_not_curved);


                        continuebtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                AddUpdateUserDetails();


                                Intent intent = new Intent(R_U_Farmer_Activity.this, Verification_Activity.class);
                                startActivity(intent);
                                sessionManager.createRegisterSession(sessionManager.getRegId("phone"));


                            }
                        });

                    }
                }


            }
        });





//        if(radio_group_farmer.getCheckedRadioButtonId() == -1){
//
//
//            continuebtn.setBackgroundResource(R.drawable.grey_curved_border);
//
//        } else{
//
//            continuebtn.setBackgroundResource(R.drawable.border_filled_red_not_curved);
//
//            cont_btn();
//
//        }
//


    }


    private void AddUpdateUserDetails() {


        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserName", user_name.getText().toString());
            jsonObject.put("UserTypeId", stat);
            jsonObject.put("UserId", sessionManager.getRegId("userId"));
            jsonObject.put("CreatedBy",sessionManager.getRegId("userId"));

            System.out.println("nnnnnnnnnnnnnnnaaaaaaaaannnnnnnnnnnnnnnaaaaaaaaa" + jsonObject);


            Crop_Post.crop_posting(R_U_Farmer_Activity.this, Urls.R_U_Farmer_Details, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("AddFeedbackkkkkkkkkkkkkkkkkkkkkkk" + result);

                    try {

                        status = result.getString("Status");
                        message = result.getString("Message");


                        if (status.equals("1")) {

                            Toast.makeText(R_U_Farmer_Activity.this, message, Toast.LENGTH_SHORT).show();


                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setupUI(View view) {


        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(R_U_Farmer_Activity.this);
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