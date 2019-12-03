package com.FarmPe.Farmer.Activity;

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
import com.FarmPe.Farmer.Fragment.AaSettingFragment;
import com.FarmPe.Farmer.Fragment.AskNandi;
import com.FarmPe.Farmer.Fragment.Home_Menu_Fragment;
import com.FarmPe.Farmer.Fragment.LookingForFragment;
import com.FarmPe.Farmer.Fragment.NotificationFragment;
import com.FarmPe.Farmer.Fragment.SellersettingFragment;
import com.FarmPe.Farmer.R;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;




public class HomePage_With_Bottom_Navigation extends AppCompatActivity {


     Fragment selectedFragment = null;
     public static AHBottomNavigation bottomNavigation;
     boolean doubleBackToExitPressedOnce = false;
     String nav_switch = "HOME";


     public static LinearLayout linear_bottom;
     LinearLayout linear_home,linear_mailbox,noti_layout,profile_layout,ask_nandi_layout;
     public static TextView mail_text,text_home,noti_text,profile_text,nandi_text;
     public static ImageView mail_icon,home_icon,noti_icon,profile_icon,nandi_icon;




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


         linear_home = findViewById(R.id.linear_home);
         linear_bottom = findViewById(R.id.linear_bottom);
         linear_mailbox = findViewById(R.id.linear_mailbox);
         ask_nandi_layout = findViewById(R.id.ask_nandi_layout);
         noti_layout = findViewById(R.id.noti_layout);

         profile_layout = findViewById(R.id.profile_layout);
         mail_text = findViewById(R.id.mail_text);
         mail_icon = findViewById(R.id.mail_icon);
         home_icon = findViewById(R.id.home_icon);
         text_home = findViewById(R.id.text_home);
         noti_icon = findViewById(R.id.noti_icon);
         noti_text = findViewById(R.id.noti_text);
         profile_text = findViewById(R.id.profile_text);
         profile_icon = findViewById(R.id.profile_icon);
        nandi_text = findViewById(R.id.nandi_text);
        nandi_icon = findViewById(R.id.nandi_icon);


        home_icon.setImageResource(R.drawable.ic_home_green);
        text_home.setTextColor(Color.parseColor("#18a360"));



            linear_bottom.setVisibility(View.VISIBLE);


          linear_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                text_home.setTextColor(Color.parseColor("#18a360"));
                mail_text.setTextColor(Color.parseColor("#595959"));
                noti_text.setTextColor(Color.parseColor("#595959"));
                profile_text.setTextColor(Color.parseColor("#595959"));
                nandi_text.setTextColor(Color.parseColor("#595959"));


                home_icon.setImageResource(R.drawable.ic_home_green);
                mail_icon.setImageResource(R.drawable.ic_mailbox);
                noti_icon.setImageResource(R.drawable.ic_notification_home);
                profile_icon.setImageResource(R.drawable.ic_user_home);
                nandi_icon.setImageResource(R.drawable.ic_agronomy);

                        selectedFragment = Home_Menu_Fragment.newInstance();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_menu, selectedFragment);
                        transaction.commit();

            }
        });


        linear_mailbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mail_text.setTextColor(Color.parseColor("#18a360"));
                text_home.setTextColor(Color.parseColor("#595959"));
                noti_text.setTextColor(Color.parseColor("#595959"));
                profile_text.setTextColor(Color.parseColor("#595959"));
                nandi_text.setTextColor(Color.parseColor("#595959"));

                mail_icon.setImageResource(R.drawable.ic_mailbox_green);
                home_icon.setImageResource(R.drawable.ic_home);
                noti_icon.setImageResource(R.drawable.ic_notification_home);
                profile_icon.setImageResource(R.drawable.ic_user_home);
                nandi_icon.setImageResource(R.drawable.ic_agronomy);


                Bundle bundle = new Bundle();
                bundle.putString("status","hme_request");
                selectedFragment = LookingForFragment.newInstance();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                selectedFragment.setArguments(bundle);
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.commit();

            }
        });



        noti_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                noti_text.setTextColor(Color.parseColor("#18a360"));
                mail_text.setTextColor(Color.parseColor("#595959"));
                text_home.setTextColor(Color.parseColor("#595959"));
                profile_text.setTextColor(Color.parseColor("#595959"));
                nandi_text.setTextColor(Color.parseColor("#595959"));

                mail_icon.setImageResource(R.drawable.ic_mailbox);
                home_icon.setImageResource(R.drawable.ic_home);
                noti_icon.setImageResource(R.drawable.ic_notification_green);
                profile_icon.setImageResource(R.drawable.ic_user_home);
                nandi_icon.setImageResource(R.drawable.ic_agronomy);

                selectedFragment = NotificationFragment.newInstance();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("");
                transaction.commit();


            }
        });



        profile_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                profile_text.setTextColor(Color.parseColor("#18a360"));
                mail_text.setTextColor(Color.parseColor("#595959"));
                text_home.setTextColor(Color.parseColor("#595959"));
                noti_text.setTextColor(Color.parseColor("#595959"));
                nandi_text.setTextColor(Color.parseColor("#595959"));

                profile_icon.setImageResource(R.drawable.ic_user_home_green);
                home_icon.setImageResource(R.drawable.ic_home);
                noti_icon.setImageResource(R.drawable.ic_notification_home);
                mail_icon.setImageResource(R.drawable.ic_mailbox);
                nandi_icon.setImageResource(R.drawable.ic_agronomy);


                selectedFragment = SellersettingFragment.newInstance();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("settingglist");
                transaction.commit();

            }
        });


        ask_nandi_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nandi_text.setTextColor(Color.parseColor("#18a360"));
                profile_text.setTextColor(Color.parseColor("#595959"));
                mail_text.setTextColor(Color.parseColor("#595959"));
                text_home.setTextColor(Color.parseColor("#595959"));
                noti_text.setTextColor(Color.parseColor("#595959"));

                nandi_icon.setImageResource(R.drawable.ic_agronomy_green);
                profile_icon.setImageResource(R.drawable.ic_user_home);
                home_icon.setImageResource(R.drawable.ic_home);
                noti_icon.setImageResource(R.drawable.ic_notification_home);
                mail_icon.setImageResource(R.drawable.ic_mailbox);

                selectedFragment = AskNandi.newInstance();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("ask_nandi");
                transaction.commit();

            }
        });


//        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home",R.drawable.ic_home);
//        AHBottomNavigationItem item2 = new AHBottomNavigationItem("MailBox", R.drawable.ic_mailbox);
//        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Ask Nandi", R.drawable.logo);
//        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Notification", R.drawable.ic_notification_home);
//        AHBottomNavigationItem item5 = new AHBottomNavigationItem("Profile", R.drawable.ic_user_home);
//
//        bottomNavigation.addItem(item1);
//        bottomNavigation.addItem(item2);
//        bottomNavigation.addItem(item3);
//        bottomNavigation.addItem(item4);
//        bottomNavigation.addItem(item5);

//        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#ffffff"));
//        // bottomNavigation.setBehaviorTranslationEnabled(true);
//        bottomNavigation.setAccentColor(Color.parseColor("#18a360"));
//        //bottomNavigation.setInactiveColor(Color.parseColor("#8f8f8f"));
//        bottomNavigation.setForceTint(true);
//        //  bottomNavigation.setColored(true);#8f8f8f
//        bottomNavigation.setTranslucentNavigationEnabled(false);
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

//#18a360

// Enable the translation of the FloatingActionButton
        //  bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);


        //  bottomNavigation.hideBottomNavigation(true);
//
//
//        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
//            @Override
//            public boolean onTabSelected(int position, boolean wasSelected) {
//
//                switch (position) {
//                    case 0:
//                        selectedFragment = Home_Menu_Fragment.newInstance();
//                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.frame_menu, selectedFragment);
//                        transaction.addToBackStack("bussiness");
//                        transaction.commit();
//
//                        break;
//
//
//                    case 1:
//                        selectedFragment = LookingForFragment.newInstance();
//                        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
//                        transaction1.replace(R.id.frame_menu, selectedFragment);
//                        transaction1.addToBackStack("bussiness");
//                        transaction1.commit();
//
//                        break;
//
//
//
//                    case 3:
//
//                        selectedFragment = NotificationFragment.newInstance();
//                        FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
//                        transaction3.replace(R.id.frame_menu, selectedFragment);
//                        transaction3.addToBackStack("home");
//                        transaction3.commit();
//                        break;
//
//
//
//                    case 4:
//                        selectedFragment = AaSettingFragment.newInstance();
//                        FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
//                        transaction4.replace(R.id.frame_menu, selectedFragment);
//                        transaction4.addToBackStack("home");
//                        transaction4.commit();
//                        break;
//
//                }
//                return true;
//            }
//        }




    }


}




