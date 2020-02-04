package com.FarmPe.Oxkart.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.Oxkart.R;


public class Verification_Fragment extends Fragment {


    Fragment selectedFragment;
  LinearLayout back_feed;
  TextView voter_id_back,voter_id_front,select_location,selfie_verify;




    public static Verification_Fragment newInstance() {
        Verification_Fragment fragment = new Verification_Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.verification_layout, container, false);

        //  Status_bar_change_singleton.getInstance().color_change(getActivity());

        voter_id_back = view.findViewById(R.id.voter_id_back);
        voter_id_front = view.findViewById(R.id.voter_id_front);
        select_location = view.findViewById(R.id.select_loc);
        selfie_verify = view.findViewById(R.id.selfie_verify);
        back_feed = view.findViewById(R.id.back_feed);
     //   linearLayout = view.findViewById(R.id.main_layout);



        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("verify", FragmentManager.POP_BACK_STACK_INCLUSIVE);


//
//                selectedFragment = Verification_Aadhar_Fragment.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout1, selectedFragment);
//                transaction.commit();

            }
        });



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("verify", FragmentManager.POP_BACK_STACK_INCLUSIVE);



//                    HomeMenuFragment.onBack_status = "farms";

//

//                    selectedFragment = Verification_Aadhar_Fragment.newInstance();
//                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame_layout1, selectedFragment);
//                    transaction.commit();

                    return true;
                }
                return false;
            }
        });


        select_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("Edit_Fragment","shop_location");
                selectedFragment = Shop_Current_Location_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("shop_locatn");
                transaction.commit();

            }
        });


        voter_id_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bundle bundle = new Bundle();
                bundle.putString("VoterFront_Fragment","voter_front");
                selectedFragment = Voter_Id_Front_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("verify_voter");
                selectedFragment.setArguments(bundle);
                transaction.commit();


            }
        });


        voter_id_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("VoterBack_Fragment","voter_back");
                selectedFragment = Voter_Id_Back_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("verify_voter");
                transaction.commit();


            }
        });


        selfie_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("Selfie_Edit","verify_selfie");
                selectedFragment = Selfie_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("verify_selfie");
                selectedFragment.setArguments(bundle);
                transaction.commit();

            }
        });







        return view;
    }
}

