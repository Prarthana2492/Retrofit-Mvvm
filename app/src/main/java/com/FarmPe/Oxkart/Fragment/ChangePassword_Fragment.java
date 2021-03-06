package com.FarmPe.Oxkart.Fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.FarmPe.Oxkart.DB.DatabaseHelper;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;






public class ChangePassword_Fragment extends Fragment {
    EditText old_password,new_password;
    String status;
    LinearLayout back_feed,linearLayout,verfiybtn,main_layout;
    Fragment selectedFragment;
    SessionManager sessionManager;
    DatabaseHelper myDb;



    public static ChangePassword_Fragment newInstance() {
        ChangePassword_Fragment fragment = new ChangePassword_Fragment();
        return fragment;
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.changepassword_layout, container, false);


        Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.dark_green));


        back_feed = view.findViewById(R.id.back_feed);
        verfiybtn=view.findViewById(R.id.verfiybtn);
        new_password=view.findViewById(R.id.new_password);
        main_layout=view.findViewById(R.id.main_layout);
        old_password = view.findViewById(R.id.old_password);

        setupUI(main_layout);



         sessionManager = new SessionManager(getActivity());
         myDb = new DatabaseHelper(getActivity());



        final InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                String filtered = "";
                for (int i = start; i < end; i++) {
                    char character = source.charAt(i);
                    if (!Character.isWhitespace(character)) {
                        filtered += character;
                    }
                }

                return filtered;
            }
        };



        new_password.setFilters(new InputFilter[] {filter,new InputFilter.LengthFilter(12) });
        old_password.setFilters(new InputFilter[] {filter,new InputFilter.LengthFilter(12) });



        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("settingg", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("settingg", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    return true;
                }

                return false;
            }
        });



        verfiybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (old_password.getText().toString().equals("")) {


                    int duration = 1000;

                    Snackbar snackbar = Snackbar
                            .make(main_layout, "Enter Old Password", duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }

                    snackbar.show();


                } else if (new_password.getText().toString().equals("")) {


                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(main_layout, "Enter New Password", duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }

                    snackbar.show();


                } else {


                    save_password();


                }
            }
        });



        old_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                linear_layout_selection(old_password,new_password);
                return false;

            }
        });



        new_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                linear_layout_selection(new_password,old_password);
                return false;

            }
        });

        return view;
    }


    private void save_password() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.Update_Profile_Details,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("lhhhhhhhhhhhhhhhhhhhhhhhhplllllllll"+response);

                        if(new_password.getText().toString().length()<=12 && new_password.getText().toString().length()>=6){
                            if(myDb.isEmailExists(sessionManager.getRegId("phone").substring(3))) {
                                System.out.println("lhhhhhhhhhhhhhhhhhhhhhhhhp"+sessionManager.getRegId("phone").substring(3));
                                //    System.out.println("lhhhhhhhhhhhhhhhhhhhhhhhhp"+profile_phone.getText().toString());
                                // AddData(profile_phone.getText().toString(), profile_passwrd.getText().toString());
                                myDb.updateContact(sessionManager.getRegId("phone").substring(3),new_password.getText().toString());


                                int duration = 1000;
                                Snackbar snackbar = Snackbar
                                        .make(main_layout, "Password Updated Successfully", duration);
                                View snackbarView = snackbar.getView();
                                TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                                tv.setTextColor(Color.WHITE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                                } else {

                                    tv.setGravity(Gravity.CENTER_HORIZONTAL);
                                }

                                snackbar.show();


                                selectedFragment = New_Profile_Setting_Fragment.newInstance();
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.frame_menu, selectedFragment);
                                transaction.commit();
                            }
                        }

                        else {

                            //   Toast.makeText(getActivity(), "Password Updated Successfully", Toast.LENGTH_LONG).show();
                            int duration = 1000;
                            Snackbar snackbar = Snackbar
                                    .make(main_layout, "Password Updated Successfully", duration);
                            View snackbarView = snackbar.getView();
                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                            tv.setTextColor(Color.WHITE);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            } else {
                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                            }

                            snackbar.show();
                            selectedFragment = New_Profile_Setting_Fragment.newInstance();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_menu, selectedFragment);
                            transaction.commit();

                        }
                    }
                },


                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                //  String stredtphone=AaProfileFragment.profile_phone.getText().toString();
                //   String stredtname=AaProfileFragment.profname.getText().toString();
                params.put("UserId",sessionManager.getRegId("userId"));
                params.put("FullName",sessionManager.getRegId("name"));
                params.put("PhoneNo",sessionManager.getRegId("phone"));
                params.put("Password",new_password.getText().toString());
                Log.e(TAG,"jdfhjsfhj" + params);

                return params;
            }
        };

        Volley.newRequestQueue(getActivity()).add(stringRequest);


    }

    public void linear_layout_selection(EditText selectdl1, EditText l2){
        selectdl1.setBackgroundResource(R.drawable.border_1_layout);
        l2.setBackgroundResource(R.drawable.request_price_white_border);

    }


    public void setupUI(View view) {


        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }

            });
        }


        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }


    public static void hideSoftKeyboard(Activity activity) {


        InputMethodManager inputManager = (InputMethodManager)
                activity.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();

        if (focusedView != null) {

            try{
                assert inputManager != null;
                inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }catch(AssertionError e){
                e.printStackTrace();
            }
        }
    }

}
