package com.FarmPe.Oxkart.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.Oxkart.Bean.Notification_recy_bean;

import com.FarmPe.Oxkart.Fragment.Notification_Recyc_Fragment;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.util.List;


public class Notification_Adapter1 extends RecyclerView.Adapter<Notification_Adapter1.MyViewHolder>  {
    private List<Notification_recy_bean> productList;
    Activity activity;
    Fragment selectedFragment;
    public LinearLayout linearLayout;
    public static String first;
    public static CardView cardView;
    Boolean isTouched = false;
    SessionManager sessionManager;
    public Notification_Adapter1(Activity activity, List<Notification_recy_bean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
       sessionManager=new SessionManager(activity);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView actninfo;
        public SwitchCompat switch1;



        public MyViewHolder(View view) {
            super(view);
            actninfo=view.findViewById(R.id.actninfo);

            image=view.findViewById(R.id.image);
            switch1=view.findViewById(R.id.switch1);


        }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notificatn_btn_layout, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Notification_recy_bean products = productList.get(position);




        holder.actninfo.setText(products.getNoti_txt());

        if (Notification_Recyc_Fragment.list.contains(products.getNoti_id())){

            holder.switch1.setChecked(true);
        }else {


            holder.switch1.setChecked(false);

        }

        holder.actninfo.setText(products.getNoti_txt());


        holder.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    enable_switch(products.getNoti_id());
                    FirebaseMessaging.getInstance().subscribeToTopic(products.getNoti_code());// to register in topic(subcribe)
                } else {
                    enable_switch("");

                    FirebaseMessaging.getInstance().unsubscribeFromTopic(products.getNoti_code());// to register in topic(unsubscribe)
                }

            }
        });






    }

    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh"+productList.size());
        return productList.size();
    }



    private void enable_switch(String not_typeId) {

        try{

            JSONObject jsonObject1 = new JSONObject();
            JSONObject post_object1 = new JSONObject();

            jsonObject1.put("NotificationTypeId",not_typeId);
            jsonObject1.put("Id",sessionManager.getRegId("userId"));
            post_object1.put("objUser",jsonObject1);


            Crop_Post.crop_posting(activity, Urls.UPDATEUSERNOTIFICATIONSETTING, post_object1, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("notification_status" + result);

                    try{

 JSONObject jsonObject1 = result.getJSONObject("user");
                        String ProfileName1 = jsonObject1.getString("NotificationTypeId");
                        System.out.println("notification_status" + ProfileName1);





                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }

    }


}