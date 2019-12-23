package com.FarmPe.Farmer.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.FarmPe.Farmer.Activity.LoginActivity;
import com.FarmPe.Farmer.Fragment.Home_Menu_Fragment;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Volly_class.Crop_Post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;
import com.FarmPe.Farmer.volleypost.VolleyMultipartRequest;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static com.android.volley.VolleyLog.TAG;

public class AddMoneyFragment extends Fragment {
    TextView save,pay_profile_name,pay_profile_no;
    LinearLayout back,linearLayout;
    Fragment selectedFragment;
    EditText amountEt,noteEt,nameEt,addbhimEt;
    final int UPI_PAYMENT = 0;
    String strtext;
    public static CircleImageView profile_image_payment;
    Bitmap bitmap;
    SessionManager sessionManager;
    String Profile_Name,ProfilePhone,ProfileImage;



    public static AddMoneyFragment newInstance() {
        AddMoneyFragment fragment = new AddMoneyFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dealer_pay, container, false);
        save = view.findViewById(R.id.requst_price);
        noteEt = view.findViewById(R.id.des);
        amountEt = view.findViewById(R.id.amount);
        nameEt = view.findViewById(R.id.bn);
        addbhimEt = view.findViewById(R.id.addbhim);
        linearLayout = view.findViewById(R.id.linearLayout);
        profile_image_payment = view.findViewById(R.id.profile_image);
        pay_profile_name = view.findViewById(R.id.pay_profile_name);
        pay_profile_no = view.findViewById(R.id.pay_profile_no);
        sessionManager = new SessionManager(getActivity());




        addbhimEt.setFilters(new InputFilter[] {EMOJI_FILTER,new InputFilter.LengthFilter(50)});


        setupUI(linearLayout);




        profile_image_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); // to go to gallery
                startActivityForResult(i, 100); // on activity method will execute*/

            }
        });



        try{

            JSONObject jsonObject = new JSONObject();
            JSONObject post_object = new JSONObject();
            jsonObject.put("Id",sessionManager.getRegId("userId"));
            post_object.put("objUser",jsonObject);


            Crop_Post.crop_posting(getActivity(), Urls.Get_Profile_Details, post_object, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("ggpgpgpg" + result);

                    try{

                        JSONObject jsonObject1 = result.getJSONObject("user");
                        Profile_Name = jsonObject1.getString("FullName");
                        ProfilePhone = jsonObject1.getString("PhoneNo");
                        //String ProfileEmail = jsonObject1.getString("EmailId");
                        ProfileImage = jsonObject1.getString("ProfilePic");
                        // profile_description = jsonObject1.getString("About");
                        pay_profile_name.setText(Profile_Name);




                        //phone_no.setText(ProfilePhone.substring(3));

                        pay_profile_no.setText(ProfilePhone); // masking + deleting last line

                        // profname.setFilters(new InputFilter[]{EMOJI_FILTER});
                        // profile_phone.setFilters(new InputFilter[]{EMOJI_FILTER});
                        // aboutText.setFilters(new InputFilter[]{EMOJI_FILTER});


                        Glide.with(getActivity())
                                .load(ProfileImage)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(true)
                                .dontAnimate()
                                .into(profile_image_payment);


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }






        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(addbhimEt.getText().toString().equals("")){

                    Snackbar snackbar = Snackbar
                            .make(linearLayout,"Add Your BHIM UPI ID", Snackbar.LENGTH_LONG);
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


                    //  Toast.makeText(getActivity(), "Add Your BHIM UPI ID", Toast.LENGTH_SHORT).show();

                }else if(nameEt.getText().toString().equals("")){

                    Snackbar snackbar = Snackbar.make(linearLayout,"Enter the Name", Snackbar.LENGTH_LONG);
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


                    //  Toast.makeText(getActivity(), "Add Your BHIM UPI ID", Toast.LENGTH_SHORT).show();


                }else if(amountEt.getText().toString().equals("")) {

                   // Toast.makeText(getActivity(), "Enter the Amount", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(linearLayout,"Enter the Amount", Snackbar.LENGTH_LONG);
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


                }else {

                    String amount = amountEt.getText().toString();
                    String note = noteEt.getText().toString();
                    String name = nameEt.getText().toString();
                    String addbhim = addbhimEt.getText().toString();
                    //    strtext = getArguments().getString("edttext");

                    System.out.println("fghfhfgfhfhf" + amount);
                    System.out.println("fghfhfgfhfhf" + name);
                    System.out.println("fghfhfgfhfhf" + note);
                    payUsingUpi(amount, addbhim, name, note);


               /* //Register Listener for Event
                selectedFragment = Addbhimfragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("add");
                transaction.commit();*/

                }
            }
        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                    fm.popBackStack ("book_payment", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }
                return false;
            }
        });


        back = view.findViewById(R.id.back_feed);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
//Find the currently focused view, so we can grab the correct window token from it.
                v = getActivity().getCurrentFocus();
//If no view currently has focus, create a new one, just so we can grab a window token from it
                if (v == null) {
                    v = new View(getActivity());
                }
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

               /* FragmentManager fm = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fm.popBackStack ("yu_ads_frg", FragmentManager.POP_BACK_STACK_INCLUSIVE);
*/
                FragmentManager fm = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fm.popBackStack ("book_payment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        return view;
    }


    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {


            //getting the image Uri
            Uri imageUri = data.getData();

            try {

                //   g_vision_controller = G_Vision_Controller.getInstance( );
                //getting the image Uri

                final InputStream imageStream;

                imageStream = getActivity().getContentResolver().openInputStream(imageUri);

                bitmap = BitmapFactory.decodeStream(imageStream);

                //   g_vision_controller.callCloudVision(bitmap,getActivity(),"profile");

                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
//
                profile_image_payment.setImageBitmap(bitmap);
                AddMoneyFragment.profile_image_payment.setImageBitmap(bitmap);

                uploadImage(getResizedBitmap(bitmap,100,100));



                int duration = 1000;
                Snackbar snackbar = Snackbar
                        .make(linearLayout, "You Changed Your Profile Photo", duration);
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
                //  Toast.makeText(getActivity(),"Your Changed Your Profile Photo", Toast.LENGTH_SHORT).show();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();

    }

    private void uploadImage(final Bitmap bitmap){
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "",
                "Loading....Please wait.");
        progressDialog.show();

        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Urls.Update_Profile_Details,
                new Response.Listener<NetworkResponse>(){
                    @Override
                    public void onResponse(NetworkResponse response) {

                        Log.e(TAG,"afaeftagsbillvalue"+response);
                        Log.e(TAG,"afaeftagsbillvalue"+response);

                        progressDialog.dismiss();

/*
                        if(profile_passwrd.getText().toString().length()<=12 && profile_passwrd.getText().toString().length()>=6){
                            if(myDb.isEmailExists(profile_phone.getText().toString().substring(3))) {
                                System.out.println("lhhhhhhhhhhhhhhhhhhhhhhhhp"+profile_passwrd.getText().toString());
                                System.out.println("lhhhhhhhhhhhhhhhhhhhhhhhhp"+profile_phone.getText().toString());
                                // AddData(profile_phone.getText().toString(), profile_passwrd.getText().toString());
                                myDb.updateContact(profile_phone.getText().toString().substring(3),profile_passwrd.getText().toString());
                            }
                        }
                        else{
                        }
*/
//                        HomeMenuFragment.prod_img.setImageBitmap(bitmap);
//                        HomeMenuFragment.prod_img1.setImageBitmap(bitmap);
                        // sessionManager.save_name(userObject.getString("FullName"),userObject.getString("PhoneNo"),userObject.getString("ProfilePic"));

                        profile_image_payment.setImageBitmap(bitmap);

                        //  uploadImage(getResizedBitmap(bitmap,100,100));

//
//                        int duration = 1000;
//                        Snackbar snackbar = Snackbar
//                                .make(linearLayout, "Profile Details Updated Successfully", duration);
//                        View snackbarView = snackbar.getView();
//                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                        tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
//                        tv.setTextColor(Color.WHITE);
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                        } else {
//                            tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                        }
//
//                        snackbar.show();


                        // Toast.makeText(getActivity(),"Profile Details Updated Successfully", Toast.LENGTH_SHORT).show();
                    /*    selectedFragment = SettingFragment.newInstance();
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frame_layout,selectedFragment);
                        ft.commit();*/

                    }
                },


                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("UserId",sessionManager.getRegId("userId"));
                params.put("FullName",sessionManager.getRegId("name"));
                params.put("PhoneNo",sessionManager.getRegId("phone"));
                //  params.put("EmailId","abcd@gmail.com");
                //    params.put("Password",profile_passwrd.getText().toString());
                Log.e(TAG,"afaeftagsparams"+params);
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                Log.e(TAG,"Im here " + params);

                if (bitmap!=null) {
                    params.put("File", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                }

                return params;
            }
        };


        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(1000 * 60, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //adding the request to volley
        Volley.newRequestQueue(getActivity()).add(volleyMultipartRequest);

    }


    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        if (bm == null) {

            return null;

        } else {
            int width = bm.getWidth();
            int height = bm.getHeight();
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            // CREATE A MATRIX FOR THE MANIPULATION
            Matrix matrix = new Matrix();
            // RESIZE THE BIT MAP
            matrix.postScale(scaleWidth, scaleHeight);

            // "RECREATE" THE NEW BITMAP
            Bitmap resizedBitmap = Bitmap.createBitmap(
                    bm, 0, 0, width, height, matrix, false);
            //bm.recycle();


            return resizedBitmap;
        }
    }


    public static InputFilter  EMOJI_FILTER = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            boolean keepOriginal = true;

            String specialChars = ".1/*!@#$%^&*()\"{}_[]|\\?/<>,.:-'';§£¥₹...%&+=€π|";

            StringBuilder sb = new StringBuilder(end - start);

            for (int index = start; index < end; index++) {
                int type = Character.getType(source.charAt(index));

                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL||type==Character.MATH_SYMBOL||specialChars.contains("" + source)) {

                    return "";
                }

                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {

                        if (dstart == 0)

                            return "";

                    }else if(Character.isDigit(source.charAt(i))) {

                        return "";
                    }
                }
                return null;

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


    void payUsingUpi(String amount, String addbhim, String name, String note) {

        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", addbhim)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();


        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);
        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");
        // check if intent resolves
        if(null != chooser.resolveActivity(getActivity().getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(getActivity(),"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
        }
    }


    // for Paymen
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode) {
//            case UPI_PAYMENT:
//                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
//                    if (data != null) {
//                        String trxt = data.getStringExtra("response");
//                        Log.d("UPI", "onActivityResult: " + trxt);
//                        ArrayList<String> dataList = new ArrayList<>();
//                        dataList.add(trxt);
//                        upiPaymentDataOperation(dataList);
//                    } else {
//                        Log.d("UPI", "onActivityResult: " + "Return data is null");
//                        ArrayList<String> dataList = new ArrayList<>();
//                        dataList.add("nothing");
//                        upiPaymentDataOperation(dataList);
//                    }
//                } else {
//                    Log.d("UPI", "onActivityResult: " + "Return data is null"); //when user simply back without payment
//                    ArrayList<String> dataList = new ArrayList<>();
//                    dataList.add("nothing");
//                    upiPaymentDataOperation(dataList);
//                }
//                break;
//        }
//    }


    private void upiPaymentDataOperation(ArrayList<String> data) {

        if (isConnectionAvailable(getActivity())) {
            String str = data.get(0);
            Log.d("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");


            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }

                else {

                    paymentCancel = "Payment cancelled by user.";
                }
            }

            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(getActivity(), "Transaction successful.", Toast.LENGTH_SHORT).show();
                selectedFragment = Home_Menu_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                Log.d("UPI", "responseStr: "+approvalRefNo);
            }

            else if("Payment cancelled by user.".equals(paymentCancel)) {

                Toast.makeText(getActivity(), "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
            }

            else {

                Toast.makeText(getActivity(), "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
            }

        } else {

            Toast.makeText(getActivity(), "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }





    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {

            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();

            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {

                return true;
            }

        }


        return false;
    }
}
