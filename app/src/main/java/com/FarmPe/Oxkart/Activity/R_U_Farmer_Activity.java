package com.FarmPe.Oxkart.Activity;



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
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.FarmPe.Oxkart.Bean.FarmsImageBean;
import com.FarmPe.Oxkart.Fragment.PrivacyPolicyFragment;
import com.FarmPe.Oxkart.Fragment.Verification_Last_Fragment;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
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

                    }
                }


            }
        });


        if(radio_group_farmer.getCheckedRadioButtonId() == -1){


            continuebtn.setBackgroundResource(R.drawable.grey_curved_border);

        } else{

            continuebtn.setBackgroundResource(R.drawable.border_filled_red_not_curved);

        }





        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(R_U_Farmer_Activity.this,Verification_Activity.class);
                startActivity(intent);
                sessionManager.createRegisterSession(sessionManager.getRegId("phone"));


            }
        });



    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);

    }
}