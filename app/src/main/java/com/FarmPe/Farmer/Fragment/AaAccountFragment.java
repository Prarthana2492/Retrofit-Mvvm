package com.FarmPe.Farmer.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.FarmPe.Farmer.DB.DatabaseHelper;
import com.FarmPe.Farmer.G_Vision_Controller;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.volleypost.VolleyMultipartRequest;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static com.android.volley.VolleyLog.TAG;


public class AaAccountFragment extends Fragment {
    BottomSheetDialog mBottomSheetDialog;
    View sheetView;
    Fragment selectedFragment;
    LinearLayout backfeed, acc_info_lay, change_pass_lay, main_layout, logout_lay,linearLayout;
    TextView notificatn, change_language, your_addresss, acc_info1, refer_ern, feedbk, help_1, abt_frmpe, polic_1, logot, setting_tittle;
    SessionManager sessionManager;
    JSONObject lngObject;
    DatabaseHelper myDb;
    EditText userInputedt;

    public static AaAccountFragment newInstance() {
        AaAccountFragment fragment = new AaAccountFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_a_acount_layout, container, false);

        backfeed = view.findViewById(R.id.back_feed);
        acc_info_lay = view.findViewById(R.id.acc_info_lay);
        change_pass_lay = view.findViewById(R.id.change_pass_lay);
        main_layout = view.findViewById(R.id.main_layout);
        logout_lay = view.findViewById(R.id.logout_lay);
        linearLayout = view.findViewById(R.id.main_layout);
        myDb = new DatabaseHelper(getActivity());
        // LandingPageActivity.editname.setVisibility(View.GONE);

      sessionManager = new SessionManager(getActivity());
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);



        backfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    //    getFragmentManager().popBackStack("home_menu", android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;

                }
                return false;
            }
        });

        acc_info_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("status", "ACC_IMG");
                selectedFragment = AaProfileFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.commit();
            }
        });

        change_pass_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog = new BottomSheetDialog(getActivity());
                sheetView = getActivity().getLayoutInflater().inflate(R.layout.general_layout, null);

                new KeyboardUtil(getActivity(), sheetView);
                TextView positiveText = sheetView.findViewById(R.id.positive_text);
                TextView titleText = sheetView.findViewById(R.id.bottom_sheet_title);
                TextView descriptionText = sheetView.findViewById(R.id.bottom_sheet_description);

                userInputedt= sheetView.findViewById(R.id.user_text);
                userInputedt.setFilters(new InputFilter[]{EMOJI_FILTER, new InputFilter.LengthFilter(12)});

                userInputedt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                userInputedt.setVisibility(View.VISIBLE);
                titleText.setText("Change password");
                Log.d("liugekuyhg",""+titleText.getText().toString());
                descriptionText.setText("Are you sure, you want to exit?");
                descriptionText.setVisibility(View.GONE);



                userInputedt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(userInputedt.hasFocus()){
                            //et1.setCursorVisible(true);
                            userInputedt.setActivated(true);
                            userInputedt.setPressed(true);
                        }
                    }
                });


                positiveText.setText("Save");
                TextView negetiveText = sheetView.findViewById(R.id.negetive_text);
                negetiveText.setText("Cancel");






                positiveText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if(userInputedt.getText().toString().equals("")) {


                            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            //Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_LONG).show();
                            int duration = 1000;
                            Snackbar snackbar = Snackbar
                                    .make(mBottomSheetDialog.getWindow().getDecorView(), "Please Enter Password", duration);
                            View snackbarView = snackbar.getView();
                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                            tv.setTextColor(Color.WHITE);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            } else {
                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            snackbar.show();
                            mBottomSheetDialog.show();



                        } else if(userInputedt.length()<6){

                            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            //Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_LONG).show();
                            int duration = 1000;
                            Snackbar snackbar = Snackbar
                                    .make(mBottomSheetDialog.getWindow().getDecorView(), "Password Should Be Minimum 6 Characters", duration);
                            View snackbarView = snackbar.getView();
                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                            tv.setTextColor(Color.WHITE);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            } else {
                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            snackbar.show();
                            mBottomSheetDialog.show();


                            //Toast.makeText(getActivity(), "Password Should Be Minimum 12 Characters", Toast.LENGTH_SHORT).show();



                        }else{

                            save_password();
                        }

                    }
                });

                negetiveText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mBottomSheetDialog.dismiss();
                    }
                });
                mBottomSheetDialog.setContentView(sheetView);
                mBottomSheetDialog.show();
            }
        });

        logout_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mBottomSheetDialog = new BottomSheetDialog(getActivity());
                sheetView = getActivity().getLayoutInflater().inflate(R.layout.general_layout, null);

                new KeyboardUtil(getActivity(), sheetView);
                TextView positiveText = sheetView.findViewById(R.id.positive_text);
                TextView titleText = sheetView.findViewById(R.id.bottom_sheet_title);
                TextView descriptionText = sheetView.findViewById(R.id.bottom_sheet_description);
                EditText userInput = sheetView.findViewById(R.id.user_text);
                userInput.setVisibility(View.GONE);
                titleText.setText("Logout");
                descriptionText.setText("Are you sure, you want to exit?");
                positiveText.setText("Yes");
                TextView negetiveText = sheetView.findViewById(R.id.negetive_text);
                negetiveText.setText("No");
                positiveText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sessionManager.logoutUser();
                        getActivity().finish();
                        mBottomSheetDialog.dismiss();
                    }
                });
                negetiveText.setOnClickListener(new View.OnClickListener() {
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


    public static InputFilter EMOJI_FILTER = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            boolean keepOriginal = true;
            StringBuilder sb = new StringBuilder(end - start);
            for (int index = start; index < end; index++) {
                int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                    return "";
                }
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        if (dstart == 0)
                            return "";
                    }
                }

          /*  char c = source.charAt(index);
            if (isCharAllowed(c))
                sb.append(c);
            else
                keepOriginal = false;*/

                String filtered = "";
                for (int i = start; i < end; i++) {
                    char character = source.charAt(i);
                    if (!Character.isWhitespace(character)) {
                        filtered += character;
                    }
                }
                return filtered;
            }



            if (keepOriginal)
                return null;
            else {
                if (source instanceof Spanned) {
                    SpannableString sp = new SpannableString(sb);
                    TextUtils.copySpansFrom((Spanned) source, start, sb.length(), null, sp, 0);
                    return sp;
                } else {
                    return sb;
                }
            }
        }
    };



    private void save_password() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.Update_Profile_Details,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("lhhhhhhhhhhhhhhhhhhhhhhhhplllllllll"+response);

                        if(userInputedt.getText().toString().length()<=12 && userInputedt.getText().toString().length()>=6){
                            if(myDb.isEmailExists(sessionManager.getRegId("phone").substring(3))) {
                                System.out.println("lhhhhhhhhhhhhhhhhhhhhhhhhp"+userInputedt.getText().toString());
                                //    System.out.println("lhhhhhhhhhhhhhhhhhhhhhhhhp"+profile_phone.getText().toString());
                                // AddData(profile_phone.getText().toString(), profile_passwrd.getText().toString());
                                myDb.updateContact(sessionManager.getRegId("phone").substring(3),userInputedt.getText().toString());
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
                            mBottomSheetDialog.dismiss();
                            selectedFragment = AaAccountFragment.newInstance();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout, selectedFragment);
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
                params.put("Password",userInputedt.getText().toString());
                Log.e(TAG,"jdfhjsfhj" + params);
                return params;
            }
        };

        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }





}



