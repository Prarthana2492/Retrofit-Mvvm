package com.FarmPe.Farmer.Fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.FarmPe.Farmer.Activity.LandingPageActivity;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;

import org.json.JSONObject;

import static com.FarmPe.Farmer.Activity.LandingPageActivity.mBottomSheetBehavior4;


public class AaSettingFragment extends Fragment {



    BottomSheetDialog mBottomSheetDialog;
    View sheetView;
    String packageName;
    Fragment selectedFragment;
    LinearLayout backfeed,acc_info_lay,not_lay,lang_lay,help_lay,invi_lay,main_layout,request_lay;
    TextView sub_lang,notificatn,change_language,your_addresss,acc_info1,refer_ern,feedbk,help_1,abt_frmpe,polic_1,logot,setting_tittle;
    SessionManager sessionManager;
    JSONObject lngObject;
    boolean newState=false;
    public static AaSettingFragment newInstance() {
        AaSettingFragment fragment = new AaSettingFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_s_setting_layout, container, false);

        backfeed=view.findViewById(R.id.back_feed);
        acc_info_lay=view.findViewById(R.id.acc_info_lay);
        not_lay=view.findViewById(R.id.not_lay);
        lang_lay=view.findViewById(R.id.lang_lay);
        help_lay=view.findViewById(R.id.help_lay);
        invi_lay=view.findViewById(R.id.invi_lay);
        main_layout=view.findViewById(R.id.main_layout);
        request_lay=view.findViewById(R.id.setting_request);
        sub_lang=view.findViewById(R.id.sub_lang);


        sessionManager = new SessionManager(getActivity());


        sub_lang.setText(sessionManager.getRegId("language_name"));

        Resources resources = getResources();
        PackageManager pm = getActivity().getPackageManager();
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        packageName = pm.queryIntentActivities(sendIntent, 0).toString();



        backfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = Home_Menu_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.commit();
            }
        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                //    getFragmentManager().popBackStack("home_menu", android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    selectedFragment = Home_Menu_Fragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_menu, selectedFragment);
                    transaction.commit();

                    return  true;
                    /*FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("home_m", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;*/
                }
                return false;
            }
        });

        acc_info_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    selectedFragment = AaAccountFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_menu, selectedFragment);
                    transaction.addToBackStack("setting");
                    transaction.commit();

            }
        });


        not_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    selectedFragment = AaNotificationSetting.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_menu, selectedFragment);
                    transaction.addToBackStack("setting");
                    transaction.commit();

            }
        });


        lang_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedFragment = ChangeLanguageFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("setting");
                transaction.commit();

            }
        });


        request_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedFragment = Setting_Request_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("request_profile");
                transaction.commit();

            }
        });



        help_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    selectedFragment = AaHelpFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_menu, selectedFragment);
                    transaction.addToBackStack("setting");
                    transaction.commit();

            }
        });


        invi_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mBottomSheetDialog = new BottomSheetDialog(getActivity());
                sheetView = getActivity().getLayoutInflater().inflate(R.layout.invite_people_bottom_sheet, null);
                TextView cancel = sheetView.findViewById(R.id.cancel_invite);
                LinearLayout whatsapp = sheetView.findViewById(R.id.whatsapp);
                LinearLayout facebook = sheetView.findViewById(R.id.facebook);
                LinearLayout instagram = sheetView.findViewById(R.id.instagram);
                LinearLayout twitter = sheetView.findViewById(R.id.twitter);
                LinearLayout messenger = sheetView.findViewById(R.id.messenger);
                LinearLayout message = sheetView.findViewById(R.id.message);



                whatsapp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (packageName.contains("com.whatsapp")) {
                            Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                            whatsappIntent.setType("text/plain");
                            whatsappIntent.setPackage("com.whatsapp");
                            //whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Text");
                            whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Hey , you found one app \"FarmPeFarmer\" Tap https://play.google.com/store/apps/details?id=com.FarmPe.Farmer to download the app!");
                            try {
                                startActivity(whatsappIntent);
                            } catch (android.content.ActivityNotFoundException ex) {



                                Toast.makeText(getActivity(), "Whatsapp is not installed on this device.", Toast.LENGTH_SHORT);
                            }

                        }else {
                            Toast.makeText(getActivity(), "Whatsapp is not installed on this device.", Toast.LENGTH_SHORT);

                        }

                    }
                });


                facebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if (packageName.contains("com.facebook.katana")) {
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey , you found one app \"FarmPeFarmer\" Tap https://play.google.com/store/apps/details?id=com.FarmPe.Farmer to download the app");
                            sendIntent.setType("text/plain");
                            sendIntent.setPackage("com.facebook.katana");
                            try {
                                startActivity(sendIntent);
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(getActivity(), "Facebook is not installed on this device ", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Toast.makeText(getActivity(), "Facebook is not installed on this device ", Toast.LENGTH_LONG).show();
                        }

                    }
                });


                instagram.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (packageName.contains("com.instagram")) {
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey , you found one app \"FarmPeFarmer\" Tap https://play.google.com/store/apps/details?id=com.FarmPe.Farmer to download the app");

                            sendIntent.setType("text/plain");
                            sendIntent.setPackage("com.instagram.android");
                            try {
                                startActivity(sendIntent);
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(getActivity(), "Instagram is not installed on this device  ", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Toast.makeText(getActivity(), "Instagram is not installed on this device ", Toast.LENGTH_LONG).show();

                        }

                    }
                });

                twitter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try
                        {
                            // Check if the Twitter app is installed on the phone.
                            getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setClassName("com.twitter.android", "com.twitter.android.composer.ComposerActivity");
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, "Hey , you found one app \"FarmPeFarmer\" Tap https://play.google.com/store/apps/details?id=com.FarmPe.Farmer to download the app!");

                            startActivity(intent);

                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getActivity(),"Twitter is not installed on this device",Toast.LENGTH_LONG).show();

                        }

                    }
                });


                messenger.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (packageName.contains("com.facebook.orca")) {
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey , you found one app \"FarmPeFarmer\" Tap https://play.google.com/store/apps/details?id=com.FarmPe.Farmer to download the app");
                            sendIntent.setType("text/plain");
                            sendIntent.setPackage("com.facebook.orca");
                            try {
                                startActivity(sendIntent);
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(getActivity(), "Messanger is not installed on this device ", Toast.LENGTH_LONG).show();
                            }

                        }else {
                            Toast.makeText(getActivity(), "Messanger is not installed on this device ", Toast.LENGTH_LONG).show();
                        }
                    }
                });



                message.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if (packageName.contains("com.android.mms")) {
                            Intent messageIntent = new Intent(Intent.ACTION_SEND);
                            messageIntent.setType("text/plain");
                            messageIntent.setPackage("com.android.mms");
                            //whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Text");
                            messageIntent.putExtra(Intent.EXTRA_TEXT, "Hey , you found one app \"FarmPeFarmer\" Tap https://play.google.com/store/apps/details?id=com.FarmPe.Farmer to download the app");
                            try {
                                startActivity(messageIntent);
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(getActivity(), "Message is not installed on this device .", Toast.LENGTH_SHORT);
                            }

                        }else {
                            Toast.makeText(getActivity(), "Message is not installed on this device.", Toast.LENGTH_SHORT);

                        }

                    }
                });



                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mBottomSheetDialog.dismiss();
                    }
                });

                mBottomSheetDialog.setContentView(sheetView);
                mBottomSheetDialog.show();


            }
        });



        return view;
    }



}
