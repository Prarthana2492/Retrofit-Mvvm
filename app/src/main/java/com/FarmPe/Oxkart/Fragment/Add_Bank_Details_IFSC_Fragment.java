package com.FarmPe.Oxkart.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import com.FarmPe.Oxkart.R;
import org.json.JSONObject;



public class Add_Bank_Details_IFSC_Fragment extends Fragment {

    TextView toolbar_title;
    LinearLayout back_feed,continuebtn;
    Fragment selectedFragment;
    JSONObject lngObject;
    String ifsc_yes;
    EditText ifsc,ifsc2,sav,sav2;
    public static String Ifsc_text;



    public static Add_Bank_Details_IFSC_Fragment newInstance() {
        Add_Bank_Details_IFSC_Fragment fragment = new Add_Bank_Details_IFSC_Fragment();
        return fragment;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addnewbank_layout, container, false);


        getActivity().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        back_feed=view.findViewById(R.id.back_feed);
        ifsc=view.findViewById(R.id.ifsc_code);
        ifsc2=view.findViewById(R.id.enterifsc);
        sav=view.findViewById(R.id.savingsbank);
        sav2=view.findViewById(R.id.confirmsavings);
        continuebtn=view.findViewById(R.id.continuebtn);

        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.dark_green));



        ifsc.setText(getArguments().getString("status_ifsc"));



        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("add_bank", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("add_bank", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }
                return false;
            }
        });



        ifsc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(ifsc,ifsc2);
                return false;
            }
        });




        ifsc2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(ifsc2,ifsc);
                return false;
            }
        });




        sav.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(sav,sav2);
                return false;
            }
        });



        sav2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try {
                    linear_layout_selection(sav2,sav);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });



        ifsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(getActivity(),ifsc);
                popupMenu.getMenu().add("Yes");
                popupMenu.getMenu().add("No");


             popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                 @Override
                 public boolean onMenuItemClick(MenuItem item) {

                     ifsc.setText(item.getTitle());



                     if(item.getTitle().equals("No")){

                         Bundle bundle = new Bundle();
                         bundle.putString("bank_status","bank_test");
                         bundle.putString("bank_status_text",ifsc.getText().toString());
                         selectedFragment = Add_New_Bank_Account_Details_Fragment.newInstance();
                         FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                         transaction.replace(R.id.frame_menu, selectedFragment);
                         selectedFragment.setArguments(bundle);
                         transaction.commit();

                     }


                     return false;
                 }
             });

             popupMenu.show();

            }
        });



        continuebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("add_bank", FragmentManager.POP_BACK_STACK_INCLUSIVE);

//                Bundle bundle = new Bundle();
//                bundle.putString("bank_status","Profile_Add_Bank_Details");
//                selectedFragment = Add_New_Bank_Account_Details_Fragment.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_menu, selectedFragment);
//                selectedFragment.setArguments(bundle);
//                transaction.commit();


            }
        });

        return view;

    }

    public void linear_layout_selection(EditText selectdl1, EditText l2){

        selectdl1.setBackgroundResource(R.drawable.border_1_layout);
        l2.setBackgroundResource(R.drawable.request_price_white_border);

    }

}
