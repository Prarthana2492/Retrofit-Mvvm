package com.FarmPe.Oxkart.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.Oxkart.Fragment.AskNandi;
import com.FarmPe.Oxkart.Fragment.Home_Menu_Fragment;
import com.FarmPe.Oxkart.Fragment.LookingForFragment;
import com.FarmPe.Oxkart.Fragment.NotificationFragment;
import com.FarmPe.Oxkart.Fragment.New_Profile_Setting_Fragment;
import com.FarmPe.Oxkart.R;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomePage_With_Bottom_Navigation extends AppCompatActivity {


      public static LinearLayout toolbar_header;
      Fragment selectedFragment = null;
      public static AHBottomNavigation bottomNavigation;
      boolean doubleBackToExitPressedOnce = false;
      String nav_switch = "HOME";
      CircleImageView prod_imgg;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_home_page);


        selectedFragment = Home_Menu_Fragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_menu, selectedFragment);
        transaction.commit();






    }


}




