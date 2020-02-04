package com.FarmPe.Oxkart.Activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.FarmPe.Oxkart.Fragment.Verification_Aadhar_Fragment;
import com.FarmPe.Oxkart.Fragment.Verification_Fragment;
import com.FarmPe.Oxkart.R;


public class Verification_Activity extends AppCompatActivity  {

    Fragment selectedFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firm);


        selectedFragment = Verification_Aadhar_Fragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout1, selectedFragment);
        transaction.commit();
    }
}
