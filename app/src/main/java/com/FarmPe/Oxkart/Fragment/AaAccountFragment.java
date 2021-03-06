package com.FarmPe.Oxkart.Fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.FarmPe.Oxkart.Activity.Status_bar_change_singleton;
import com.FarmPe.Oxkart.DB.DatabaseHelper;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;


public class AaAccountFragment extends Fragment {
    BottomSheetDialog mBottomSheetDialog;
    View sheetView;
    Fragment selectedFragment;
    LinearLayout backfeed, acc_info_lay, change_pass_lay, main_layout, logout_lay,linearLayout;
    TextView notificatn, change_language, your_addresss, acc_info1, refer_ern, feedbk, help_1, abt_frmpe, polic_1, logot, setting_tittle,your_addrss;
    SessionManager sessionManager;
    JSONObject lngObject;
    DatabaseHelper myDb;
    EditText userInputedt;
    String pickUPFrom;
    public static JSONArray get_address_array;



    public static AaAccountFragment newInstance() {
        AaAccountFragment fragment = new AaAccountFragment();
        return fragment;
    }


    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_a_acount_layout, container, false);


        Status_bar_change_singleton.getInstance().color_change(getActivity());


        backfeed = view.findViewById(R.id.back_feed);
        acc_info_lay = view.findViewById(R.id.acc_info_lay);
        change_pass_lay = view.findViewById(R.id.change_pass_lay);
        main_layout = view.findViewById(R.id.main_layout);
        logout_lay = view.findViewById(R.id.logout_lay);
        your_addrss = view.findViewById(R.id.your_addrss);
        linearLayout = view.findViewById(R.id.main_layout);
        myDb = new DatabaseHelper(getActivity());
        // LandingPageActivity.editname.setVisibility(View.GONE);



        sessionManager = new SessionManager(getActivity());



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
                transaction.replace(R.id.frame_menu, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.commit();
            }
        });



        your_addrss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    final JSONObject jsonObject = new JSONObject();
                    jsonObject.put("UserId",sessionManager.getRegId("userId"));
                    jsonObject.put("PickUpFrom",pickUPFrom);
                    System.out.println("aaaaaaaaaaaaadddd" + sessionManager.getRegId("userId"));
                    System.out.println("ggggggggggaaaaaaa"+jsonObject);



                    Crop_Post.crop_posting(getActivity(), Urls.Get_New_Address, jsonObject, new VoleyJsonObjectCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject result) {
                            System.out.println("ggggggggggaaaaaaa"+result);

                            try{

                                get_address_array = result.getJSONArray("UserAddressDetails");


                                if(get_address_array.length()== 0){
                                    Bundle bundle = new Bundle();
                                    bundle.putString("navigation_from","HOME_FRAGMENT");
                                    selectedFragment = Add_New_Address_Fragment.newInstance();
                                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                    selectedFragment.setArguments(bundle);
                                    transaction.addToBackStack("HOME_FRAGMENT");
                                    transaction.replace(R.id.frame_menu, selectedFragment);
                                    transaction.commit();


                                }else {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("navigation_from","HOME_FRAGMENT");
                                    selectedFragment = You_Address_Fragment.newInstance();
                                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_menu, selectedFragment);
                                    selectedFragment.setArguments(bundle);
                                    transaction.commit();

                                }

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    });


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });





        change_pass_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 mBottomSheetDialog = new BottomSheetDialog(getActivity());
                 sheetView = getActivity().getLayoutInflater().inflate(R.layout.change_password_layout, null);

                  new KeyboardUtil(getActivity(),sheetView);
                  TextView positiveText = sheetView.findViewById(R.id.positive_text);
                  TextView titleText = sheetView.findViewById(R.id.bottom_sheet_title);
                  TextView descriptionText = sheetView.findViewById(R.id.bottom_sheet_description);

                  userInputedt= sheetView.findViewById(R.id.user_text);
                  userInputedt.setTransformationMethod(PasswordTransformationMethod.getInstance());

                   userInputedt.setVisibility(View.VISIBLE);
                   titleText.setText("Change password");
                   Log.d("liugekuyhg",""+titleText.getText().toString());
                   descriptionText.setText("Are you sure, you want to exit?");
                   descriptionText.setVisibility(View.GONE);



               // userInputedt.setFilters(new InputFilter[]{EMOJI_FILTER, new InputFilter.LengthFilter(12)});


//                userInputedt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                    @Override
//                    public void onFocusChange(View v, boolean hasFocus) {
//                        if(userInputedt.hasFocus()){
//
//                            userInputedt.setFilters(new InputFilter[]{EMOJI_FILTER, new InputFilter.LengthFilter(12)});
//                            //et1.setCursorVisible(true);
////                            userInputedt.setActivated(true);
////                            userInputedt.setPressed(true);
//                        }
//                    }
//                });



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
                                System.out.println("lhhhhhhhhhhhhhhhhhhhhhhhhp"+sessionManager.getRegId("phone").substring(3));
                                //    System.out.println("lhhhhhhhhhhhhhhhhhhhhhhhhp"+profile_phone.getText().toString());
                                // AddData(profile_phone.getText().toString(), profile_passwrd.getText().toString());
                                myDb.updateContact(sessionManager.getRegId("phone").substring(3),userInputedt.getText().toString());


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
                            mBottomSheetDialog.dismiss();
                            selectedFragment = AaAccountFragment.newInstance();
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
                params.put("Password",userInputedt.getText().toString());
                Log.e(TAG,"jdfhjsfhj" + params);

                    return params;
        }
        };

        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }





}



