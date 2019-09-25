package com.FarmPe.Farmer.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.FarmPe.Farmer.Fragment.AaSettingFragment;
import com.FarmPe.Farmer.Fragment.Home_Menu_Fragment;
import com.FarmPe.Farmer.Fragment.LookingForFragment;
import com.FarmPe.Farmer.Fragment.NotificationFragment;
import com.FarmPe.Farmer.R;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;



public class HomePage_With_Bottom_Navigation extends AppCompatActivity {


    Fragment selectedFragment = null;
    public static AHBottomNavigation bottomNavigation;
    boolean doubleBackToExitPressedOnce = false;
    String nav_switch = "HOME";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_withbottom_navigation);
        Intent intent = getIntent();


        selectedFragment = Home_Menu_Fragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_menu, selectedFragment);
        transaction.commit();



        System.out.println("aaaaaaaaaaHome"+nav_switch);

        bottomNavigation = findViewById(R.id.bottom_navigation_land);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home",R.drawable.ic_home);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("MailBox", R.drawable.ic_mailbox);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Ask Nandi", R.drawable.logo);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Notification", R.drawable.ic_notification_home);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("Profile", R.drawable.ic_user_home);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);



        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#ffffff"));
        // bottomNavigation.setBehaviorTranslationEnabled(true);
        bottomNavigation.setAccentColor(Color.parseColor("#18a360"));
        bottomNavigation.setInactiveColor(Color.parseColor("#8f8f8f"));
        bottomNavigation.setForceTint(true);
        //  bottomNavigation.setColored(true);#8f8f8f
        bottomNavigation.setTranslucentNavigationEnabled(false);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);



// Enable the translation of the FloatingActionButton
        //  bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);


        //  bottomNavigation.hideBottomNavigation(true);


        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                switch (position) {
                    case 0:
                        selectedFragment = Home_Menu_Fragment.newInstance();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_menu, selectedFragment);
                        transaction.addToBackStack("bussiness");
                        transaction.commit();

                        break;


                    case 1:
                        selectedFragment = LookingForFragment.newInstance();
                        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                        transaction1.replace(R.id.frame_menu, selectedFragment);
                        transaction1.addToBackStack("bussiness");
                        transaction1.commit();

                        break;



                    case 3:

                        selectedFragment = NotificationFragment.newInstance();
                        FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                        transaction3.replace(R.id.frame_menu, selectedFragment);
                        transaction3.addToBackStack("home");
                        transaction3.commit();
                        break;



                    case 4:
                        selectedFragment = AaSettingFragment.newInstance();
                        FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                        transaction4.replace(R.id.frame_menu, selectedFragment);
                        transaction4.addToBackStack("home");
                        transaction4.commit();
                        break;

                }
                return true;
            }
        });







    }

}




