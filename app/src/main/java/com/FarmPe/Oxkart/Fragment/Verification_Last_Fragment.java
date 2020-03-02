package com.FarmPe.Oxkart.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.Oxkart.Activity.HomePage_With_Bottom_Navigation;
import com.FarmPe.Oxkart.Activity.New_Login_Activity2;
import com.FarmPe.Oxkart.Activity.New_OTP_Page_Activity;
import com.FarmPe.Oxkart.Activity.Verification_Activity;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;




public class Verification_Last_Fragment extends Fragment {
    Boolean user_uploaded;
    Fragment selectedFragment;
    public static  LinearLayout linear_layout,cont_btn;
    boolean doubleBackToExitPressedOnce = false;
    JSONObject verify_status;
    TextView toolbar_title,mobile_no,proceed_txt,in_progress_details,success_details;
    public static  TextView user_status,ph_no,user_status_text;
    ImageView in_progress_image,success_image;
    SessionManager sessionManager;
    public static JSONObject lngObject;



    public static Verification_Last_Fragment newInstance() {
        Verification_Last_Fragment fragment = new Verification_Last_Fragment();
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.verify_last_layout, container, false);

        //   Status_bar_change_singleton.getInstance().color_change(getActivity());

        linear_layout = view.findViewById(R.id.linear_layout);
        user_status = view.findViewById(R.id.user_status);
        ph_no = view.findViewById(R.id.ph_no);
        cont_btn = view.findViewById(R.id.cont_btn);
        toolbar_title = view.findViewById(R.id.setting_tittle);
        mobile_no = view.findViewById(R.id.mobile_no);
        user_status_text = view.findViewById(R.id.user_status_text);
        proceed_txt = view.findViewById(R.id.proceed_txt);
        in_progress_details = view.findViewById(R.id.in_progress_details);
        success_details = view.findViewById(R.id.success_details);
        in_progress_image = view.findViewById(R.id.in_progress_image);
        success_image = view.findViewById(R.id.success_image);

        sessionManager = new SessionManager(getActivity());

        ph_no.setText(sessionManager.getRegId("phone"));
        System.out.println("dhfgfjh" + sessionManager.getRegId("phone"));




        try {


            lngObject = new JSONObject(sessionManager.getRegId("language"));

            System.out.println("llllllllllllkkkkkkkkkkkkkkk" + lngObject.getString("EnterPhoneNo"));

            toolbar_title.setText(lngObject.getString("Verification"));
            mobile_no.setText(lngObject.getString("PhoneNumber"));
            user_status_text.setText(lngObject.getString("Status"));

            in_progress_details.setText(lngObject.getString("OuragentwillcallyousoontoverifyDetailsPleasebeavailableovercall").replace("\n",""));
            success_details.setText(lngObject.getString("YourverificationissuccessfulClickproceedtostartexploring"));


            proceed_txt.setText(lngObject.getString("PROCEED").replace("\n",""));



            //  pass.setHint(lngObject.getString("Password"));
            //  remember_me.setText(lngObject.getString("RememberMe"));





        } catch (JSONException e) {
            e.printStackTrace();
        }



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        // this.finishAffinity();

                        if (doubleBackToExitPressedOnce) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                            startActivity(intent);
                            getActivity().finish();
                            System.exit(0);
                        }

                        doubleBackToExitPressedOnce = true;
                        // Toast.makeText(getActivity().getApplicationContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//                       int duration = 1000;
//
//                       Snackbar snackbar = Snackbar
//                               .make(linear_layout,"Please Click Back To Exit", duration);
//                       View snackbarView = snackbar.getView();
//                       TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                       tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
//                       tv.setTextColor(Color.WHITE);
//
//
//                       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                           tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                       } else {
//                           tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                       }
//                       snackbar.show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                doubleBackToExitPressedOnce = false;
                            }
                        }, 3000);
                    }

                    return true;
                }
                return false;
            }
        });


        cont_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_uploaded.equals(false)){
                    Intent intent = new Intent(getActivity(), Verification_Activity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getActivity(),HomePage_With_Bottom_Navigation.class);
                    startActivity(intent);
                }



            }
        });


        try{


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));
            System.out.println("deyuiirwe" + sessionManager.getRegId("userId"));

            Crop_Post.crop_posting(getActivity(), Urls.Get_Verification_Status, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("ghdgfd" + result);

                    try{

                        verify_status = result.getJSONObject("VerificationStatus");

                        user_uploaded = verify_status.getBoolean("IsUserUploaded");

                        if(user_uploaded.equals(false)){

                            user_status.setText("In Progress");
                            in_progress_details.setVisibility(View.VISIBLE);

                            user_status.setText(lngObject.getString("InProgress").replace("\n",""));
                            in_progress_image.setVisibility(View.VISIBLE);



                        }else{

                            user_status.setText("Successfull");

                            user_status.setText(lngObject.getString("Successful"));

                            success_details.setVisibility(View.VISIBLE);
                            success_image.setVisibility(View.VISIBLE);



                        }


                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }


//        Continue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                selectedFragment = VerificationRejectedFragment.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout1, selectedFragment);
//                transaction.addToBackStack("verification");
//                transaction.commit();
//
//            }
//        });







        return view;
    }
}

