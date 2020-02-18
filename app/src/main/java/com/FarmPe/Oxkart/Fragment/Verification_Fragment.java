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
import com.FarmPe.Oxkart.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;


public class Verification_Fragment extends Fragment {


    Fragment selectedFragment;
  LinearLayout back_feed;
    public static JSONObject lngObject;
  TextView voter_id_back,voter_id_front,select_location,selfie_verify,face_verify_selfy_text,sel_loc_text,voter_front_text,voter_back_text,continue_btn;
  String status_1,status_2;
  SessionManager sessionManager;




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
        face_verify_selfy_text = view.findViewById(R.id.face_verify_selfy_text);
        sel_loc_text = view.findViewById(R.id.sel_loc_text);
        voter_back_text = view.findViewById(R.id.voter_back_text);
        voter_front_text = view.findViewById(R.id.voter_front_text);
        continue_btn = view.findViewById(R.id.continue_btn);
        back_feed = view.findViewById(R.id.back_feed);


        sessionManager = new SessionManager(getActivity());
     //   linearLayout = view.findViewById(R.id.main_layout);


//           status_1 = getArguments().getString("verification_status");


        try {


            lngObject = new JSONObject(sessionManager.getRegId("language"));

            System.out.println("llllllllllllkkkkkkkkkkkkkkk" + lngObject.getString("EnterPhoneNo"));

            select_location.setText(lngObject.getString("Select"));
            voter_id_front.setText(lngObject.getString("Upload"));
            voter_id_back.setText(lngObject.getString("Upload"));
            selfie_verify.setText(lngObject.getString("Click"));

            face_verify_selfy_text.setText(lngObject.getString("FaceVerificationSelfie").replace("\n",""));
            sel_loc_text.setText(lngObject.getString("SelectLocation").replace("\n",""));
            voter_front_text.setText(lngObject.getString("VoterIDFront").replace("\n",""));
            voter_back_text.setText(lngObject.getString("VoterIDBack").replace("\n",""));


            continue_btn.setText(lngObject.getString("PROCEED").replace("\n",""));



            //  pass.setHint(lngObject.getString("Password"));
            //  remember_me.setText(lngObject.getString("RememberMe"));





        } catch (JSONException e) {
            e.printStackTrace();
        }



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

