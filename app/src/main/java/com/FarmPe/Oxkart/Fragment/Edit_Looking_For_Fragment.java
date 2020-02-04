package com.FarmPe.Oxkart.Fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.FarmPe.Oxkart.Activity.HomePage_With_Bottom_Navigation;
import com.FarmPe.Oxkart.Activity.Status_bar_change_singleton;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
//import com.FarmPe.Farmer.Adapter.AddHpAdapter;
import com.FarmPe.Oxkart.Adapter.FarmsImageAdapter;
import com.FarmPe.Oxkart.Bean.FarmsImageBean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;
import com.FarmPe.Oxkart.volleypost.VolleyMultipartRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static com.android.volley.VolleyLog.TAG;


public class Edit_Looking_For_Fragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
   // public static AddHpAdapter farmadapter;
    SessionManager sessionManager;
    public static String back;
    ImageView tractor_img;
    TextView toolbar_title,update_btn_txt,brand;
    JSONObject lngObject;
    JSONArray edit_req_array;
    String toast_name,toast_mobile,toast_passwrd,toast_new_mobile,toast_minimum_toast,  toast_update,toast_image;
    String addressID=null;
    LinearLayout update_btn,linearLayout,address;
    private static int RESULT_LOAD_IMG = 1;
    Bitmap selectedImage,imageB;
    EditText profile_name,profile_phone,profile_mail,profile_passwrd;
    RadioButton month_1,month_2,month_3,month_4,finance_yes,finance_no;
    CircleImageView prod_img;

    TextView farmer_name,farmer_phone,farmer_email,farmer_loc,delete_req,hp_power,address_text,request,model;
    LinearLayout back_feed;
    Fragment selectedFragment;
    RadioGroup radio_group_time,radioGroup_finance;
    String time_period,lookingfordetails_id,modelid;
    String finance,timeline;
    public static int selectedId_time_recent;
    BottomSheetDialog mBottomSheetDialog;
    View sheetView;

    public static Edit_Looking_For_Fragment newInstance() {
        Edit_Looking_For_Fragment fragment = new Edit_Looking_For_Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.farmers_detail_page, container, false);
       // getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Status_bar_change_singleton.getInstance().color_change(getActivity());
        HomePage_With_Bottom_Navigation.linear_bottom.setVisibility(View.GONE);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);
        farmer_phone=view.findViewById(R.id.phone_no);
        brand=view.findViewById(R.id.brand);
        prod_img=view.findViewById(R.id.prod_img);
      //  hp_power=view.findViewById(R.id.hp_power);
        model=view.findViewById(R.id.model);
        delete_req=view.findViewById(R.id.delete_req);
        month_1=view.findViewById(R.id.month_1);
        month_2=view.findViewById(R.id.month_2);
        month_3=view.findViewById(R.id.month_3);
        month_4=view.findViewById(R.id.month_4);
        finance_yes=view.findViewById(R.id.finance_yes);
        finance_no=view.findViewById(R.id.finance_no);
        linearLayout=view.findViewById(R.id.linearLayout);
        address_text=view.findViewById(R.id.address_text);
        sessionManager = new SessionManager(getActivity());
        request=view.findViewById(R.id.update_rqt);
        radio_group_time=view.findViewById(R.id.radio_group_time);
        radioGroup_finance=view.findViewById(R.id.radioGroup_finance);
        address=view.findViewById(R.id.address_layout);

        try {

            addressID=getArguments().getString("add_id");

           }catch (Exception e){

           }


        if (addressID==null){
            getting_edit();
        }else{

            String stret_name = getArguments().getString("streetname");
            addressID=getArguments().getString("add_id");
            lookingfordetails_id=getArguments().getString("looking_forId");
            modelid=getArguments().getString("modelId");

            address_text.setText(stret_name);

        }


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeMenuFragment.onBack_status = "looking_frg";

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("looking1", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            /*    selectedFragment = HomeMenuFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                // transaction.addToBackStack("looking");
                transaction.commit();*/
            }
        });



        radioGroup_finance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedId_time_recent = radioGroup_finance.getCheckedRadioButtonId();
                RadioButton  radioButton = (RadioButton)view.findViewById(selectedId_time_recent);

                if (String.valueOf(radioButton.getText()).equals("Yes")){
                    finance="True";
                }else {
                    finance="False";
                }
            }
        });


        radio_group_time.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedId_time_recent = radio_group_time.getCheckedRadioButtonId();
                RadioButton radioButton1 = (RadioButton)view.findViewById(selectedId_time_recent);


                //time_period=String.valueOf(radioButton.getText());
            }
        });



        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_profile_details();
            }
        });



        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("navigation_from", "edit_lokng_frg");
                bundle.putString("looking_forId",lookingfordetails_id);
                bundle.putString("modelId", modelid);
                selectedFragment = Add_New_Address_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("edit");
                transaction.commit();
            }
        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    HomeMenuFragment.onBack_status = "looking_frg";

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("looking1", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }
                return false;
            }
        });



        delete_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              /*  final TextView yes1,no1,text_desctxt,popup_headingtxt;
                final LinearLayout close_layout;
                System.out.println("aaaaaaaaaaaa");
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.delete_request_layout);
                text_desctxt =  dialog.findViewById(R.id.text_desc);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);
                yes1 =  dialog.findViewById(R.id.yes_1);
                no1 =  dialog.findViewById(R.id.no_1);
                popup_headingtxt =  dialog.findViewById(R.id.popup_heading);



                close_layout =  dialog.findViewById(R.id.close_layout);
                no1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                close_layout.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                yes1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        delete_request();

                        dialog.dismiss();
                    }
                });


                dialog.show();*/

                mBottomSheetDialog = new BottomSheetDialog(getActivity());
                sheetView = getActivity().getLayoutInflater().inflate(R.layout.general_layout, null);
                TextView confirm = sheetView.findViewById(R.id.positive_text);
                TextView titleText = sheetView.findViewById(R.id.bottom_sheet_title);
                TextView descriptionText = sheetView.findViewById(R.id.bottom_sheet_description);
                EditText userInput = sheetView.findViewById(R.id.user_text);
                userInput.setVisibility(View.GONE);
                titleText.setText("Delete The Request");
                descriptionText.setText("Are you sure you want to delete the Request?");
                confirm.setText("Confirm");




                TextView cancel = sheetView.findViewById(R.id.negetive_text);
                cancel.setText("Cancel");
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        delete_request();
                        mBottomSheetDialog.dismiss();
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



//        try{
//
//            JSONObject jsonObject = new JSONObject();
//            JSONObject post_object = new JSONObject();
//
//            jsonObject.put("Id", FarmsImageAdapter.looking_forId);
//            post_object.put("objUser",jsonObject);
//            System.out.println("ggpgpgpg" + post_object);
//
//
//            Crop_Post.crop_posting(getActivity(), Urls.Get_Profile_Details, post_object, new VoleyJsonObjectCallback() {
//                @Override
//                public void onSuccessResponse(JSONObject result) {
//                    System.out.println("ggpgpgpg" + result);
//
//                    try{
//
//                        JSONObject jsonObject1 = result.getJSONObject("user");
//                        String ProfileName1 = jsonObject1.getString("FullName");
//                        System.out.println("11111" + jsonObject1.getString("FullName"));
//                        String ProfilePhone = jsonObject1.getString("MaskedPhoneNo");
//                        String ProfileEmail = jsonObject1.getString("EmailId");
//                        String ProfileImage = jsonObject1.getString("ProfilePic");
//                        System.out.println("11111" + ProfileName1);
//
//
//
//                        farmer_name.setText(ProfileName1);
//                        farmer_phone.setText(ProfilePhone);
//                        farmer_email.setText(ProfileEmail);
//
//
//                        Glide.with(getActivity()).load(ProfileImage)
//
//                                .thumbnail(0.5f)
//                                //  .crossFade()
//                                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                .error(R.drawable.avatarmale)
//                                .into(prod_img);
//
//
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//
//                }
//            });






      /*  SharedPreferences myPrefrence = getActivity().getPreferences(MODE_PRIVATE);
        String imageS = myPrefrence.getString("imagePreferance", "");
        if(!imageS.equals("")) imageB = decodeToBase64(imageS);
        prod_img.setImageBitmap(imageB);
*/
        return view;
    }


    private void getting_edit() {


        for(int i=0;i<LookingForFragment.cropsListArray.length();i++){

            JSONObject jsonObject1 = null;
            try {
                jsonObject1 = LookingForFragment.cropsListArray.getJSONObject(i);

                JSONObject jsonObject2 = jsonObject1.getJSONObject("Address");

                String id = jsonObject1.getString("Id");

                if (id.equals(FarmsImageAdapter.looking_forId)) {
                    System.out.println("gddhghgh" + jsonObject1);
                    String purchasetimeline = jsonObject1.getString("PurchaseTimeline");
                    String lookin_true = jsonObject1.getString("LookingForFinance");
                    String brand_name = jsonObject1.getString("BrandName");
                    String model_name = jsonObject1.getString("Model");
                    String model_image = jsonObject1.getString("ModelImage");
                    String horse_range = "";
                    String addrss_id = jsonObject2.getString("Id");
                    String addrss_name = jsonObject2.getString("Name");
                    String mobile_no = jsonObject2.getString("MobileNo");
                    String street_address = jsonObject2.getString("StreeAddress1");
                    String pincode = jsonObject2.getString("Pincode");
                    String state = jsonObject2.getString("State");
                    String district = jsonObject2.getString("District");
                    String taluk = jsonObject2.getString("Taluk");
                    String area = jsonObject2.getString("Hoblie");
                    lookingfordetails_id = jsonObject1.getString("LookingForDetailsId");
                    addressID = jsonObject1.getString("AddressId");
                    modelid = jsonObject1.getString("ModelId");


                    brand.setText("Brand - " + brand_name);
                    //hp_power.setText("HP - " + horse_range);
                    model.setText("Model - " + model_name);
                    address_text.setText(taluk + " , " + district );


                    if (lookin_true.equals("true")) {
                        finance_yes.setChecked(true);

                        finance = "True";


                    } else {

                        finance_no.setChecked(true);
                        finance = "False";

                    }

                    if (purchasetimeline.equals("Immediately")) {

                        month_1.setChecked(true);


                    } else if (purchasetimeline.equals("1 Month")) {

                        month_2.setChecked(true);


                    } else if (purchasetimeline.equals("3 Months")) {

                        month_3.setChecked(true);


                    } else if (purchasetimeline.equals("After 3 Months")) {
                        month_4.setChecked(true);

                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

//
//                            Glide.with(getActivity()).load(model_image)
//
//                                    .thumbnail(0.5f)
//                                    .crossFade()
//                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                    .into(tractor_img);
//


        }






     /*   try{




            Crop_Post.crop_posting(getActivity(), Urls.Get_Edit_Request, post_jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dfdsfsf" + result);
                    try{

                        edit_req_array = result.getJSONArray("LookingForList");
                        for(int i=0;i<edit_req_array.length();i++){

                            JSONObject jsonObject1 = edit_req_array.getJSONObject(i);
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("Address");

                            String id = jsonObject1.getString("Id");
                            String purchasetimeline = jsonObject1.getString("PurchaseTimeline");
                            String lookin_true = jsonObject1.getString("LookingForFinance");
                            String brand_name = jsonObject1.getString("BrandName");
                            String model_name = jsonObject1.getString("Model");
                            String model_image = jsonObject1.getString("ModelImage");
                            String horse_range = jsonObject1.getString("HorsePowerRange");
                            String addrss_id = jsonObject2.getString("Id");
                            String addrss_name = jsonObject2.getString("Name");
                            String mobile_no = jsonObject2.getString("MobileNo");
                            String street_address = jsonObject2.getString("StreeAddress1");
                            String pincode = jsonObject2.getString("Pincode");
                            String state = jsonObject2.getString("State");
                            String district = jsonObject2.getString("District");
                            String taluk = jsonObject2.getString("Taluk");
                            lookingfordetails_id = jsonObject1.getString("LookingForDetailsId");
                            addrss_id = jsonObject1.getString("AddressId");
                            modelid = jsonObject1.getString("ModelId");

                            System.out.println("dfdsfsfkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" + lookin_true);

                            brand.setText("Brand - " + brand_name);
                            hp_power.setText("HP - " + horse_range);
                            hp_power.setText("Model - " + model_name);
                            address_text.setText(street_address + " , " + state + " , " + pincode);



                            if(lookin_true.equals("true")){
                                finance_yes.setChecked(true);

                                finance="True";


                            }else{

                                finance_no.setChecked(true);
                                finance="False";

                            }


                            if(purchasetimeline.equals("Immediately")){

                                month_1.setChecked(true);

                            }else if(purchasetimeline.equals("1 Month")){

                                month_2.setChecked(true);


                            }else if(purchasetimeline.equals("3 Months")){

                                month_3.setChecked(true);


                            }else if(purchasetimeline.equals("After 3 Months")){
                                month_4.setChecked(true);


                            }

//
//                            Glide.with(getActivity()).load(model_image)
//
//                                    .thumbnail(0.5f)
//                                    .crossFade()
//                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                    .into(tractor_img);
//


                        }


                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
*/





    }

    private void delete_request() {

        try{


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));
            jsonObject.put("Id",FarmsImageAdapter.looking_forId);

            System.out.print("wwwwwefsdwwwwwefsdddddddwwwwwefsdwwwwwefsddddddd" + jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.Delete_Request, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.print("wwwwwefsdddd" + result);

                    try{

                        String status = result.getString("Status");
                        String message = result.getString("Message");


                        if(status.equals("1")){

                            Snackbar snackbar = Snackbar
                                    .make(linearLayout,message, Snackbar.LENGTH_LONG);
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


                            selectedFragment = HomeMenuFragment.newInstance();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout, selectedFragment);
                            transaction.commit();


                        }else{

                            Toast.makeText(getActivity(), "Request Quotation not deleted", Toast.LENGTH_SHORT).show();
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
                return null;
          /*  char c = source.charAt(index);
            if (isCharAllowed(c))
                sb.append(c);
            else
                keepOriginal = false;*/
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
    private Bitmap decodeToBase64(String input) {

        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }


    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            final InputStream imageStream;
            try {
                imageStream = getActivity().getContentResolver().openInputStream(imageUri);

                selectedImage = BitmapFactory.decodeStream(imageStream);
                prod_img.setImageBitmap(selectedImage);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }else {
            Toast.makeText(getActivity(),toast_image,Toast.LENGTH_LONG).show();
        }
    }


    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();



        //System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj"+byteArrayOutputStream.toByteArray());

    }

    private void uploadImage(final Bitmap bitmap){
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST,Urls.Update_Profile_Details,
                new Response.Listener<NetworkResponse>(){
                    @Override
                    public void onResponse(NetworkResponse response) {
                        //Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        //  String resultResponse = new String(response.data);
                        selectedImage=null;

                        Toast.makeText(getActivity(),toast_update, Toast.LENGTH_SHORT).show();
                        selectedFragment = SettingFragment.newInstance();
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frame_layout,selectedFragment);
                        ft.commit();

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            /*
             *pass files using below method
             * */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //  params.put("UserId",sessionManager.getRegId("userId") );

                params.put("UserId",sessionManager.getRegId("userId"));
                params.put("FullName",profile_name.getText().toString());
                params.put("PhoneNo",profile_phone.getText().toString());
                params.put("EmailId",profile_mail.getText().toString());
                params.put("Password",profile_passwrd.getText().toString());
                Log.e(TAG,"afaeftagsparams"+params);
                return params;
            }


            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                // params.put("File", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                Log.e(TAG,"Imhereafaeftagsparams Imhereafaeftagsparams "+bitmap);

                if (bitmap==null){

                }else {
                    params.put("File", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));

                }
                Log.e(TAG,"Imhereafaeftagsparams "+params);
                return params;
            }
        };
        //adding the request to volley
        Volley.newRequestQueue(getActivity()).add(volleyMultipartRequest);
    }


    private String encodeToBase64(Bitmap image) {

        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    private void update_profile_details() {


        /*{
            "Id":2,
                "UserId":1,
                "ModelId":2,
                "PurchaseTimeline":"Immediately",
                "LookingForFinance":"True",
                "AddressId":1,
                "IsAgreed":"True",
                "LookingForDetailsId":1
        }*/


        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));
            jsonObject.put("Id",FarmsImageAdapter.looking_forId);
            jsonObject.put("PurchaseTimeline",time_period);
            jsonObject.put("ModelId",modelid);

            jsonObject.put("LookingForFinance",finance);

            jsonObject.put("AddressId", addressID);
            jsonObject.put("IsAgreed","True");
            jsonObject.put("LookingForDetailsId",lookingfordetails_id);

            System.out.print("wwwwwefsdwwwwwefsdddddddwwwwwefsdwwwwwefsdddddddjjjjjjjjjjjjjjjjjjjjjjjjjjjj" + jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.AddRequestForQuotation, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.print("wwwwwefsdddd" + result);

                    try{

                        String status = result.getString("Status");
                        String message = result.getString("Message");


                        if(!(status.equals("0"))){

                            Snackbar snackbar = Snackbar
                                    .make(linearLayout,message, Snackbar.LENGTH_LONG);
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


                            selectedFragment = LookingForFragment.newInstance();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout, selectedFragment);
                            transaction.commit();


                        }else{

                            Toast.makeText(getActivity(), "Request Quotation not updated", Toast.LENGTH_SHORT).show();
                        }


                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });




        }catch(Exception e){
            e.printStackTrace();
        }








    }




    public static void hideSoftKeyboard(Activity activity) {
        /*InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);*/

        InputMethodManager inputManager = (InputMethodManager)
                activity.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();

        if (focusedView != null) {

            try {
                assert inputManager != null;
                inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (AssertionError e) {
                e.printStackTrace();
            }
        }
    }



}

