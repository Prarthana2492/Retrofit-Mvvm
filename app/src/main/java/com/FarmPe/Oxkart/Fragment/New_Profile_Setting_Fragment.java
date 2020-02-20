package com.FarmPe.Oxkart.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.FarmPe.Oxkart.Activity.HomePage_With_Bottom_Navigation;
import com.FarmPe.Oxkart.Activity.Status_bar_change_singleton;
import com.FarmPe.Oxkart.Bean.BankBean;
import com.FarmPe.Oxkart.Bean.FarmsImageBean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;
import com.FarmPe.Oxkart.volleypost.VolleyMultipartRequest;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static com.android.volley.VolleyLog.TAG;



public class New_Profile_Setting_Fragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static ArrayList<BankBean> newOrderBeansList1 = new ArrayList<>();


    public static RecyclerView recyclerView;
    LinearLayout back_feed,invite,your_addresss,noti_setting,logout,feedback,change_lang,policy,bank_account,verify_kyc,help,change_password,linearLayout,invite_frnd;
    Fragment selectedFragment;
    TextView notificatn,change_language,acc_info1,lang_setting,feedbk,help_1,abt_frmpe,polic_1,logot,setting_tittle,profname,profile_phone;
    SessionManager sessionManager;
    JSONObject lngObject;
    JSONArray Bank_list_array,new_address_list_array,imagelist_array;
    ImageView b_arrow;
    LinearLayout linear_profile_image;
    CircleImageView profile_image;
    String profnamestr,ProfilePhone,ProfileImage;
    Bitmap bitmap;
    BottomSheetDialog mBottomSheetDialog;
    View sheetView;
    String packageName;
    String selfie_image_id;




    public static New_Profile_Setting_Fragment newInstance() {
        New_Profile_Setting_Fragment fragment = new New_Profile_Setting_Fragment();
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sellersetting_layout, container, false);




        Status_bar_change_singleton.getInstance().color_change(getActivity());

        back_feed=view.findViewById(R.id.back_feed);
        invite_frnd=view.findViewById(R.id.invite);
        noti_setting=view.findViewById(R.id.noti_setting);
       // refer_earn=view.findViewById(R.id.refer_earn);
        feedback=view.findViewById(R.id.feedback);
        change_lang=view.findViewById(R.id.change_lang);
        policy=view.findViewById(R.id.policy);
        logout=view.findViewById(R.id.logout);
        profname=view.findViewById(R.id.profile_name);
        profile_phone=view.findViewById(R.id.profile_number);
        invite=view.findViewById(R.id.invite);
        profile_image=view.findViewById(R.id.profile_image);
        linearLayout=view.findViewById(R.id.linearLayout);
        bank_account=view.findViewById(R.id.acc_info);
        lang_setting=view.findViewById(R.id.lang_setting);
     //   your_address=view.findViewById(R.id.ur_address);
        notificatn=view.findViewById(R.id.notificatn);
        change_language=view.findViewById(R.id.change_language);
        your_addresss=view.findViewById(R.id.address);
        acc_info1=view.findViewById(R.id.acc_info1);
       // refer_ern=view.findViewById(R.id.refer_ern);
        feedbk=view.findViewById(R.id.feedbk);
        polic_1=view.findViewById(R.id.polic_1);
        logot=view.findViewById(R.id.logot);
        verify_kyc=view.findViewById(R.id.kyc);
        help=view.findViewById(R.id.help);
        linear_profile_image = view.findViewById(R.id.linear_profile_image);

        sessionManager = new SessionManager(getActivity());

        lang_setting.setText(sessionManager.getRegId("language_name"));
        profile_phone.setText(sessionManager.getRegId("phone"));




        Resources resources = getResources();
        PackageManager pm = getActivity().getPackageManager();
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        packageName = pm.queryIntentActivities(sendIntent, 0).toString();





            try {

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("UserId", sessionManager.getRegId("userId"));


                Crop_Post.crop_posting(getActivity(), Urls.Get_Image_Details, jsonObject, new VoleyJsonObjectCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject result) {
                        System.out.println("dhfjfjd" + result);


                        try {

                            imagelist_array = result.getJSONArray("captureImagelist");

                            for (int i = 0; i < imagelist_array.length(); i++) {


                                JSONObject jsonObject1 = imagelist_array.getJSONObject(i);
                                selfie_image_id = jsonObject1.getString("CImageId");
                                String image_view = jsonObject1.getString("Image1");



                                Glide.with(getActivity()).load(image_view)
                                        .thumbnail(0.5f)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .error(R.drawable.avatarmale)
                                        .into(profile_image);
                            }




                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }







//                    try{
//
//            JSONObject jsonObject = new JSONObject();
//            JSONObject post_object = new JSONObject();
//            jsonObject.put("Id",sessionManager.getRegId("userId"));
//            post_object.put("objUser",jsonObject);
//
//
//            Crop_Post.crop_posting(getActivity(), Urls.Get_Profile_Details, post_object, new VoleyJsonObjectCallback() {
//                @Override
//                public void onSuccessResponse(JSONObject result) {
//                    System.out.println("ggpgpgpg" + result);
//
//
//
//                    try{
//
//                        JSONObject jsonObject1 = result.getJSONObject("user");
//                      //  profnamestr = jsonObject1.getString("FullName");
//                        System.out.println("ggpgpgpg" + profnamestr);
//                        ProfilePhone = jsonObject1.getString("PhoneNo");
//                        //String ProfileEmail = jsonObject1.getString("EmailId");
//                        ProfileImage = jsonObject1.getString("ProfilePic");
//                       // profile_description = jsonObject1.getString("About");
//
//                     //   profname.setText(profnamestr);
//
//
//
//
//                        //phone_no.setText(ProfilePhone.substring(3));
//
//                        profile_phone.setText(ProfilePhone); // masking + deleting last line
//
//
//                        // profname.setFilters(new InputFilter[]{EMOJI_FILTER});
//                        // profile_phone.setFilters(new InputFilter[]{EMOJI_FILTER});
//                        // aboutText.setFilters(new InputFilter[]{EMOJI_FILTER});
//
//
//                        Glide.with(getActivity()).load(ProfileImage)
//                                .thumbnail(0.5f)
//                                // .crossFade()
//                                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
//                                        .error(R.drawable.avatarmale))
//                                .into(profile_image);
//
////                        Glide.with(getActivity())
////                                .load(ProfileImage)
////                                .centerCrop()
////                                .diskCacheStrategy(DiskCacheStrategy.NONE)
////                                .skipMemoryCache(true)
////                                .dontAnimate()
////                                .into(profile_image);
//
//
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//        }
//




        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("home_setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });




        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("home_setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }
                return false;
            }
        });





        linear_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); // to go to gallery
                startActivityForResult(i, 100); // on activity method will execute*/

            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final TextView yes1,no1,text_desctxt,popup_headingtxt;
                final LinearLayout close_layout;
                System.out.println("aaaaaaaaaaaa");
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.logout_layout);
                text_desctxt =  dialog.findViewById(R.id.text_desc);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);
                yes1 =  dialog.findViewById(R.id.yes_1);
                no1 =  dialog.findViewById(R.id.no_1);
                popup_headingtxt =  dialog.findViewById(R.id.popup_heading);



//                try {
//                    lngObject = new JSONObject(sessionManager.getRegId("language"));
//                    text_desctxt.setText(lngObject.getString("AreyousureyouwanttoLogout"));
//                    yes1.setText(lngObject.getString("Confirm"));
//                    no1.setText(lngObject.getString("Cancel"));
//                    popup_headingtxt.setText(lngObject.getString("Logout"));
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }



                no1.setOnClickListener(new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


               yes1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sessionManager.logoutUser();
                        getActivity().finish();
                        dialog.dismiss();
                    }
                });


                dialog.show();

            }
        });



           bank_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                try{

                    newOrderBeansList1.clear();


                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("UserId",sessionManager.getRegId("userId"));
                    System.out.println("ghdfvghsfh" +sessionManager.getRegId("userId"));

                    Crop_Post.crop_posting(getActivity(), Urls.Get_Bank_Details, jsonObject, new VoleyJsonObjectCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject result) {
                            System.out.println("hjgfhsfjksd" + result);
                            try{

                                Bank_list_array = result.getJSONArray("BankDetails");

                                if(Bank_list_array.length()==0){

                                    Bundle bundle = new Bundle();
                                    bundle.putString("bank_status","Profile_Add_Bank_Details");
                                    selectedFragment = Add_New_Bank_Account_Details_Fragment.newInstance();
                                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_menu, selectedFragment);
                                    selectedFragment.setArguments(bundle);
                                    transaction.addToBackStack("profile_setting");
                                    transaction.commit();



                                }else{

                                    selectedFragment = Get_Bank_List_Fragment.newInstance();
                                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_menu, selectedFragment);
                                    transaction.addToBackStack("profile_setting");
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


        verify_kyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            selectedFragment = Verify_KYC_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("settingg");
                transaction.commit();
            }
        });


        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment = HelpandSupportFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("help_supp");
                transaction.commit();
            }
        });



        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("status","setting_privacy");
                selectedFragment = PrivacyPolicyFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("Policy");
                transaction.commit();
            }
        });



        change_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment = ChangeLanguageFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("lang_setting");
                transaction.commit();
            }
        });




//
//        change_password.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//                selectedFragment = ChangePassword_Fragment.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_menu, selectedFragment);
//                transaction.addToBackStack("settingg");
//                transaction.commit();
//            }
//        });
//
//

        your_addresss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("UserId",sessionManager.getRegId("userId"));


                    Crop_Post.crop_posting(getActivity(), Urls.Profile_Get_Adress_Details, jsonObject, new VoleyJsonObjectCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject result) {
                            System.out.println("dfsdfssaaa" + result);

                            try{

                                new_address_list_array = result.getJSONArray("AddressDetails");

                                  if(new_address_list_array.length()==0){

                                    Bundle bundle = new Bundle();
                                    bundle.putString("prof_add_status","profile_add_address");
                                    selectedFragment = Profile_Add_New_Address_Fragment.newInstance();
                                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_menu, selectedFragment);
                                    selectedFragment.setArguments(bundle);
                                    transaction.addToBackStack("settingg");
                                    transaction.commit();


                                  }else{

                                    selectedFragment = Profile_Get_Address_Fragment.newInstance();
                                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_menu, selectedFragment);
                                    transaction.addToBackStack("settingg");
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

        invite.setOnClickListener(new View.OnClickListener() {
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
                            whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Hey , you found one app \"FarmPeSellerHub\" Tap https://play.google.com/store/apps/details?id=com.FarmPe.Farms to download the app!");

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
                            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey , you found one app \"FarmPeSellerHub\" Tap https://play.google.com/store/apps/details?id=com.FarmPe.Farms to download the app");
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
                            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey , you found one app \"FarmPeSellerHub\" Tap https://play.google.com/store/apps/details?id=com.FarmPe.Farms to download the app");

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
                            intent.putExtra(Intent.EXTRA_TEXT, "Hey , you found one app \"FarmPeSellerHub\" Tap https://play.google.com/store/apps/details?id=com.FarmPe.Farms to download the app!");

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
                            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey , you found one app \"FarmPeSellerHub\" Tap https://play.google.com/store/apps/details?id=com.FarmPe.Farms to download the app");
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
                            messageIntent.putExtra(Intent.EXTRA_TEXT, "Hey , you found one app \"FarmPeSellerHub\" Tap https://play.google.com/store/apps/details?id=com.FarmPe.Farms to download the app");
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


        noti_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment = AaNotificationSetting.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("noti_setting");
                transaction.commit();
            }
        });



      /* logout_layout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final TextView yes1,no1,text_desctxt,popup_headingtxt;
               final LinearLayout close_layout;
               System.out.println("aaaaaaaaaaaa");
               final Dialog dialog = new Dialog(getContext());
               dialog.setContentView(R.layout.logout_layout);
               text_desctxt =  dialog.findViewById(R.id.text_desc);
               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
               dialog.setCancelable(false);
               yes1 =  dialog.findViewById(R.id.yes_1);
               no1 =  dialog.findViewById(R.id.no_1);
               popup_headingtxt =  dialog.findViewById(R.id.popup_heading);


               try {
                   lngObject = new JSONObject(sessionManager.getRegId("language"));
                   text_desctxt.setText(lngObject.getString("AreyousureyouwanttoLogout"));
                   yes1.setText(lngObject.getString("Confirm"));
                   no1.setText(lngObject.getString("Cancel"));
                   popup_headingtxt.setText(lngObject.getString("Logout"));

               } catch (JSONException e) {
                   e.printStackTrace();
               }


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
                       sessionManager.logoutUser();
                       getActivity().finish();
                       dialog.dismiss();
                   }
               });


               dialog.show();

           }
       });*/
        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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


                   profile_image.setImageBitmap(bitmap);
               //    AddMoneyFragment.profile_image_payment.setImageBitmap(bitmap);

              //  if(getArguments().getString("HOME_IMAGE").equals("Selfie_image")){

                    uploadSelfieImage(getResizedBitmap(bitmap,100,100));

//                }else {

                 //   uploadImage(getResizedBitmap(bitmap,100,100));

             //   }

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


//    private void uploadImage(final Bitmap bitmap){
//
//        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "",
//                "Loading....Please wait.");
//        progressDialog.show();
//
//
//        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Urls.Update_Profile_Details,
//                new Response.Listener<NetworkResponse>(){
//                    @Override
//                    public void onResponse(NetworkResponse response) {
//
//                        Log.e(TAG,"afaeftagsbillvalue"+response);
//                        Log.e(TAG,"afaeftagsbillvalue"+response);
//
//                        progressDialog.dismiss();
//
///*
//                        if(profile_passwrd.getText().toString().length()<=12 && profile_passwrd.getText().toString().length()>=6){
//                            if(myDb.isEmailExists(profile_phone.getText().toString().substring(3))) {
//                                System.out.println("lhhhhhhhhhhhhhhhhhhhhhhhhp"+profile_passwrd.getText().toString());
//                                System.out.println("lhhhhhhhhhhhhhhhhhhhhhhhhp"+profile_phone.getText().toString());
//                                // AddData(profile_phone.getText().toString(), profile_passwrd.getText().toString());
//                                myDb.updateContact(profile_phone.getText().toString().substring(3),profile_passwrd.getText().toString());
//                            }
//                        }
//                        else{
//                        }
//*/
////                        HomeMenuFragment.prod_img.setImageBitmap(bitmap);
////                        HomeMenuFragment.prod_img1.setImageBitmap(bitmap);
//                        // sessionManager.save_name(userObject.getString("FullName"),userObject.getString("PhoneNo"),userObject.getString("ProfilePic"));
//
//                        profile_image.setImageBitmap(bitmap);
//
//                      //  uploadImage(getResizedBitmap(bitmap,100,100));
//
////
////                        int duration = 1000;
////                        Snackbar snackbar = Snackbar
////                                .make(linearLayout, "Profile Details Updated Successfully", duration);
////                        View snackbarView = snackbar.getView();
////                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
////                        tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
////                        tv.setTextColor(Color.WHITE);
////                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
////                            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
////                        } else {
////                            tv.setGravity(Gravity.CENTER_HORIZONTAL);
////                        }
////
////                        snackbar.show();
//
//
//                       // Toast.makeText(getActivity(),"Profile Details Updated Successfully", Toast.LENGTH_SHORT).show();
//                    /*    selectedFragment = SettingFragment.newInstance();
//                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                        ft.replace(R.id.frame_layout,selectedFragment);
//                        ft.commit();*/
//
//                    }
//                },
//
//
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();
//
//                        progressDialog.dismiss();
//
//                    }
//
//                }) {
//
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//
//                params.put("UserId",sessionManager.getRegId("userId"));
//              //  params.put("FullName",sessionManager.getRegId("name"));
//                params.put("PhoneNo",sessionManager.getRegId("phone"));
//                //  params.put("EmailId","abcd@gmail.com");
//                //    params.put("Password",profile_passwrd.getText().toString());
//                Log.e(TAG,"afaeftagsparams"+params);
//
//                return params;
//
//            }
//
//
//            @Override
//            protected Map<String, DataPart> getByteData() {
//                Map<String, DataPart> params = new HashMap<>();
//                long imagename = System.currentTimeMillis();
//
//                Log.e(TAG,"Im here " + params);
//
//                if (bitmap!=null) {
//
//                    params.put("ProfilePic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
//
//                }
//
//                return params;
//            }
//        };
//
//        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(1000 * 60, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        //adding the request to volley
//
//        Volley.newRequestQueue(getActivity()).add(volleyMultipartRequest);
//
//    }


    //upload selfie image
    private void uploadSelfieImage(final Bitmap bitmap){
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "",
                "Loading....Please wait.");
        progressDialog.show();


        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Urls.Add_Update_Image_Details,
                new Response.Listener<NetworkResponse>(){

                    @Override
                    public void onResponse(NetworkResponse response) {
                        Log.e(TAG,"afaeftagsbillvalue"+response.data);
                        Log.e(TAG,"afaeftagsbillvalue"+response);
                        progressDialog.dismiss();

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
            protected VolleyError parseNetworkError(VolleyError volleyError){
                if(volleyError.networkResponse != null && volleyError.networkResponse.data != null){
                    VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
                    progressDialog.dismiss();

                    //Toast.makeText(getActivity(),volleyError.getMessage(), Toast.LENGTH_SHORT).show();

                }

                return volleyError;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                Log.e(TAG,"Im here " + params);

                if (bitmap!=null) {

                    params.put("Image1", new DataPart(imagename + ".png",getFileDataFromDrawable(bitmap)));

                }

                Log.e(TAG,"Im here " + params);
                return params;

            }


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("UserId", sessionManager.getRegId("userId"));

                params.put("CImageId",selfie_image_id);

                //  System.out.println("Latitude11111111"+String.valueOf(Farms_MapView_Fragment.a));
                //  params.put("FarmDescription", description.getText().toString());
                Log.e(TAG,"afaeftagsparams"+params);
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

}
