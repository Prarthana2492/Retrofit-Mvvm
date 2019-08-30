package com.FarmPe.Farmer.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.Farmer.Activity.LoginActivity_new;
import com.FarmPe.Farmer.Bean.SelectLanguageBean;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Volly_class.Crop_Post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONObject;

import java.util.List;


public class AdapterSelectLanguage extends RecyclerView.Adapter<AdapterSelectLanguage.MyViewHolder>  {
    private List<SelectLanguageBean> productList;
    Activity activity;
    Fragment selectedFragment;
    SessionManager sessionManager;



    public static CardView cardView;
    public AdapterSelectLanguage(Activity activity, List<SelectLanguageBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
        sessionManager = new SessionManager(activity);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lang_text;
        public LinearLayout language;
        public ImageView lang_img,right_img;




        public MyViewHolder(View view) {
            super(view);
            lang_text=view.findViewById(R.id.lang_text);
            language=view.findViewById(R.id.main_layout);
            lang_img=view.findViewById(R.id.lang_icon);
            right_img = view.findViewById(R.id.right_img);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.a_a_selectlang_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final SelectLanguageBean products1 = productList.get(position);


        holder.lang_text.setText(products1.getVendor());
//        Glide.with(activity).load(R.drawable.success)
//
//                .thumbnail(0.5f)
//                .crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.lang_img);

        System.out.println("iiiddddddmmmmmmmmmmmmmmmmm" + products1.getLanguageid());


        if (sessionManager.getRegId("language_name").equals(products1.getVendor())){
            // holder.right_img.setImageResource(R.drawable.ic_verified_filled_grey_white);
            //  holder.lng_rad_but.setBackgroundColor(Color.GREEN);
            holder.right_img.setVisibility(View.VISIBLE);

        }else {

            holder.right_img.setVisibility(View.GONE);

            //// holder.right_img.setImageResource(R.drawable.filled_grey_circle);

//            holder.right_img.setImageResource(R.drawable.v);

            //  holder.lng_rad_but.setBackgroundColor(Color.WHITE);


        }


        holder.language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("iiiddddddkkkkkkkkkkkkkkkkkkkkkkkkkkk" + products1.getLanguageid());

                sessionManager.saveLanguage_name(products1.getVendor());
                getLang(products1.getLanguageid());

                /*selectedFragment = AaSettingFragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();*/

               /* sessionManager.saveLanguage_name(products1.getVendor());
                getLang(products1.getLanguageid());
                LoginActivity_new.change_lang.setText(products1.getVendor());
                LoginActivity_new.dialog.dismiss();*/

            }
        });
    }


    private void getLang(int id) {

        try{


              JSONObject jsonObject = new JSONObject();
              jsonObject.put("Id",id);


               System.out.print("iiidddddd"+ id);

               Crop_Post.crop_posting(activity, Urls.CHANGE_LANGUAGE, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("qqqqqqvvhhhhhhhhhhhh" + result);

                    try{



                         sessionManager.saveLanguage(result.toString());



                        Intent intent = new Intent(activity, LoginActivity_new.class);

                        activity.startActivity(intent);

/*

                        String lang_title1 = result.getString("ChangeLanguage");
                        System.out.println("ckkkkkkk"+result.getString("ChangeLanguage"));
                        LoginActivity_new.popup_heading.setText(result.getString("ChangeLanguage"));
                        System.out.println("ppppoooo"+LoginActivity_new.popup_heading.getText().toString());

                          String log_login = result.getString("Login");
                          String log_mobile = result.getString("DigitMobileNumber");
                          String log_password = result.getString("Password");
                          String log_remember_me = result.getString("RememberMe");
                          String log_forgot_passwrd = result.getString("ForgotPassword");
                          String log_register = result.getString("Register");

                          
                      //  LoginActivity_new_new.popup_heading.setText(lang_title1);

                           LoginActivity_new.remember_me.setText(log_remember_me);
                           LoginActivity_new.log_in.setText(log_login);
                           LoginActivity_new.forgot_pass.setText(log_forgot_passwrd+ "?");
                           LoginActivity_new.text_pass.setHint(log_password);
                           LoginActivity_new.welcome_back.setText(log_login);
                           LoginActivity_new.createaccount.setText(log_register);
                           LoginActivity_new.text_mobile.setHint(log_mobile);
                           LoginActivity_new.mob_toast = result.getString("EnterPhoneNo");
                           LoginActivity_new.pass_toast = result.getString("EnterPassword");
                           LoginActivity_new.toast_invalid = result.getString("InvalidCredentials");
                           LoginActivity_new.toast_click_back = result.getString("PleaseclickBACKagaintoexit");
*/



                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }
}