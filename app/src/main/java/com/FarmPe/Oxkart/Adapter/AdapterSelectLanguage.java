package com.FarmPe.Oxkart.Adapter;



import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.FarmPe.Oxkart.Activity.ActivitySelectLang;
import com.FarmPe.Oxkart.Bean.First_Language_Bean;
import com.FarmPe.Oxkart.Bean.SelectLanguageBean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import org.json.JSONObject;

import java.util.List;



public class AdapterSelectLanguage extends RecyclerView.Adapter<AdapterSelectLanguage.MyViewHolder>  {

    private List<First_Language_Bean> productList;
    Activity activity;
    Fragment selectedFragment;
    SessionManager sessionManager;
    String lng_list;

    public static int selected_position=0;


    public static CardView cardView;

    public AdapterSelectLanguage(Activity activity, List<First_Language_Bean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
        sessionManager = new SessionManager(activity);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView lang_text;
        public ImageView tick_image,lang_icon;
        public LinearLayout language;
//        public RadioGroup lang_icon;
//        public RadioButton lang_txt;




        public MyViewHolder(View view) {
            super(view);
            lang_text=view.findViewById(R.id.lang_text);
            language=view.findViewById(R.id.main_layout);
//            lang_img=view.findViewById(R.id.lang_icon);
//            right_img = view.findViewById(R.id.right_img);

            tick_image=view.findViewById(R.id.tick_image);
            lang_icon=view.findViewById(R.id.lang_icon);

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
        final First_Language_Bean products1 = productList.get(position);



        holder.lang_text.setText(products1.getVendor());
        lng_list = products1.getVendor();

        Glide.with(activity).load(products1.getImageicon())
                .thumbnail(0.5f)
                //   .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.lang_icon);


//
//        if(selected_position == position){
//
//            holder.tick_image.setImageResource(R.drawable.ic_verified_green);
//            holder.lang_text.setTypeface(null, Typeface.BOLD);
//
//        }else{
//
//            holder.tick_image.setImageResource(R.drawable.ic_verified_grey);
//            holder.lang_text.setTypeface(null, Typeface.NORMAL);
//        }




        if (lng_list.equals(sessionManager.getRegId("language_name"))) {

            holder.tick_image.setImageResource(R.drawable.ic_verified_green);
            holder.lang_text.setTypeface(null, Typeface.BOLD);


        } else {

            System.out.println("sfdsdfsdxvvvv" + sessionManager.getRegId("language_name"));


            if((sessionManager.getRegId("language_name").equals(""))){

                if(position == 0){

                    holder.tick_image.setImageResource(R.drawable.ic_verified_green);
                    holder.lang_text.setTypeface(null, Typeface.BOLD);

                }



            }else{

                holder.tick_image.setImageResource(R.drawable.ic_verified_grey);
                holder.lang_text.setTypeface(null, Typeface.NORMAL);
            }

        }






//        if(selected_position == position){
//
//            sessionManager.saveLanguage_name(products1.getVendor());
//
//            holder.lang_txt.setChecked(true);
//            holder.lang_txt.setTypeface(null, Typeface.BOLD);
//
//
//        }else{
//
//
//            holder.lang_txt.setChecked(false);
//            holder.lang_txt.setTypeface(null, Typeface.NORMAL);
//        }



        System.out.println("iiiddddddmmmmmmmmmmmmmmmmm" + products1.getLanguageid());


//        if (sessionManager.getRegId("language_name").equals(products1.getVendor())){
//            // holder.right_img.setImageResource(R.drawable.ic_verified_filled_grey_white);
//            //  holder.lng_rad_but.setBackgroundColor(Color.GREEN);
//            holder.right_img.setVisibility(View.VISIBLE);
//
//        }else {
//
//            holder.right_img.setVisibility(View.GONE);
//
//            //// holder.right_img.setImageResource(R.drawable.filled_grey_circle);
//
////            holder.right_img.setImageResource(R.drawable.v);
//
//            //  holder.lng_rad_but.setBackgroundColor(Color.WHITE);
//
//
//        }



        holder.language.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("iiiddddddkkkkkkkkkkkkkkkkkkkkkkkkkkk" + products1.getLanguageid());

            sessionManager.saveLanguage_name(products1.getVendor());
            getLang(Integer.parseInt(products1.getLanguageid()));
            lng_list = products1.getVendor();
          //  sessionManager.saveLanguage(lng_list);
            selected_position = position;
            notifyDataSetChanged();


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
                             String select_title = result.getString("SelectYourLanguage");
                             String continue_btnn = result.getString("PROCEED").replace("\n","");

                             ActivitySelectLang.select_your_lang_text.setText(select_title);
                             ActivitySelectLang.continue_lang.setText(continue_btnn);


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