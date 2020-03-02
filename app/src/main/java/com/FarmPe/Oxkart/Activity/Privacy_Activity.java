package com.FarmPe.Oxkart.Activity;



import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.FarmPe.Oxkart.Bean.FarmsImageBean;
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


public class Privacy_Activity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{
    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    TextView toolbar_title;
    Fragment selectedFragment;
    String toast_internet,toast_nointernet;
    JSONObject lngObject;
    SessionManager sessionManager;
    LinearLayout back_feed,main_layout;
    JSONObject verify_status;
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
        int color=0;
        if (isConnected) {
            if(connectivity_check) {
                Toast toast = Toast.makeText(Privacy_Activity.this,toast_internet, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                toast.show();
                connectivity_check=false;
            }

        } else {
            connectivity_check=true;
            Toast toast = Toast.makeText(Privacy_Activity.this,toast_nointernet, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
            toast.show();
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
        setContentView(R.layout.test);
        checkConnection();
        sessionManager = new SessionManager(this);
        back_feed=findViewById(R.id.back_feed);
        main_layout=findViewById(R.id.main_layout);
        toolbar_title=findViewById(R.id.toolbar_title);
;       try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));
            toast_internet = lngObject.getString("GoodConnectedtoInternet");
            toast_nointernet = lngObject.getString("NoInternetConnection");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));
            Crop_Post.crop_posting(this, Urls.Get_Verification_Status, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    try{
                        verify_status = result.getJSONObject("VerificationStatus");
                        Boolean user_uploaded = verify_status.getBoolean("IsUserUploaded");
                        Bundle bundle=new Bundle();
                            selectedFragment = Verification_Last_Fragment.newInstance();
                            bundle.putBoolean("VER_SATTUS",user_uploaded);
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout, selectedFragment);
                            selectedFragment.setArguments(bundle);
                            transaction.commit();

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }
}