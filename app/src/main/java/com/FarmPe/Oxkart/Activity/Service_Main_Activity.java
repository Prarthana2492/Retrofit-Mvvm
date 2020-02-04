package com.FarmPe.Oxkart.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.FarmPe.Oxkart.R;

public class Service_Main_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
    }


    // Start the service
    public void startService(View view) {
        startService(new Intent(this, Service_Activity.class));
    }


    // Stop the service
    public void stopService(View view) {
        stopService(new Intent(this, Service_Activity.class));

    }
}