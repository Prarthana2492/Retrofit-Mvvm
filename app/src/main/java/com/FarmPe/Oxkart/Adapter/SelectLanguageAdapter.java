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
import android.widget.TextView;

import com.FarmPe.Oxkart.Bean.SelectLanguageBean;
import com.FarmPe.Oxkart.Fragment.ChangeLanguageFragment;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONObject;

import java.util.List;



public class SelectLanguageAdapter extends RecyclerView.Adapter<SelectLanguageAdapter.MyViewHolder>  {


    private List<SelectLanguageBean> productList;
    SelectLanguageBean selectLanguageBean;
    Activity activity;
    Fragment selectedFragment;
    SessionManager sessionManager;
    public static int selected_position=0;
    String lng_list;



    public static CardView cardView;
    public SelectLanguageAdapter(Activity activity, List<SelectLanguageBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
        sessionManager=new SessionManager(activity);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView language_name,lang_text;
        public LinearLayout submit_langu,lang_onclick;
        public RadioButton lang_txt;
        public ImageView tick_image, lang_icon;



        public MyViewHolder(View view) {
            super(view);

            language_name = view.findViewById(R.id.lang_text);
            lang_onclick = view.findViewById(R.id.main_layout);
            tick_image = view.findViewById(R.id.tick_image);
            lang_text = view.findViewById(R.id.lang_text);
            lang_icon = view.findViewById(R.id.lang_icon);
//            right_img = view.findViewById(R.id.right_img);
//            lang_image = view.findViewById(R.id.lang_icon);


        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.language_item, parent, false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final SelectLanguageBean products = productList.get(position);


        holder.lang_text.setText(products.getVendor());

        lng_list = products.getVendor();


        Glide.with(activity).load(products.getImageicon())
                .thumbnail(0.5f)
                //   .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.lang_icon);


//        if (lng_list.equals(sessionManager.getRegId("language_name"))) {
//
////            holder.lang_text.setChecked(true);
////            holder.lang_text.setTypeface(null, Typeface.BOLD);
//
//
//       } else {
//
//            System.out.println("sfdsdfsdxvvvv" + sessionManager.getRegId("language_name"));
//
//
//            if((sessionManager.getRegId("language_name").equals(""))){
//
//                if(position == 0){
//
////                    holder.lang_txt.setChecked(true);
////                    holder.lang_txt.setTypeface(null, Typeface.NORMAL);
//
//                }
//
//
//
//           }else{
////                holder.lang_txt.setChecked(false);
////                holder.lang_txt.setTypeface(null, Typeface.NORMAL);
//            }
//
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




        holder.lang_onclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  holder.lang_txt.setTypeface(null, Typeface.BOLD);
                System.out.println("iiiddddddkkkkkkkkkkkkkkkkkkkkkkkkkkk" + products.getLanguageid());
                sessionManager.saveLanguage_name(products.getVendor());
                getLang(Integer.parseInt(products.getLanguageid()));
                lng_list = products.getVendor();
                selected_position = position;
                notifyDataSetChanged();

            }
        });


//
//        if (sessionManager.getRegId("language_name").equals(products.getVendor())){
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


//        if (LoginActivity.isEng && position == 0){
//            holder.right_img.setImageResource(R.drawable.ic_verified_filled_grey_white);
//            LoginActivity.isEng = false;
//
//        }else if(HomeMenuFragment.isEng && position==0){
//
//            holder.right_img.setImageResource(R.drawable.ic_verified_filled_grey_white);
//            HomeMenuFragment.isEng = false;
//
//        }
//        else {
//            if (sessionManager.getRegId("language_name").equals(products.getVendor())) {
//                holder.right_img.setImageResource(R.drawable.ic_verified_filled_grey_white);
//
//
//            } else {
//
//                holder.right_img.setImageResource(R.drawable.filled_grey_circle);
//
//
//            }
//        }

    }

    private void getLang(int id) {

        try{


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Id",id);


            System.out.print("iiidddddd"+ id);

            Crop_Post.crop_posting(activity, Urls.CHANGE_LANGUAGE, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("qqqqqqvv" + result);

                    try{

                        sessionManager.saveLanguage(result.toString());

                        String lang_title1 = result.getString("ChangeLanguage");

                        ChangeLanguageFragment.lang_title.setText(lang_title1);


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