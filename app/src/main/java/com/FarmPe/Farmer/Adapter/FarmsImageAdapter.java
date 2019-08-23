package com.FarmPe.Farmer.Adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.Farmer.Fragment.Edit_Looking_For_Fragment;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Volly_class.Crop_Post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.FarmPe.Farmer.Bean.FarmsImageBean;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FarmsImageAdapter extends RecyclerView.Adapter<FarmsImageAdapter.MyViewHolder>  {
    private List<FarmsImageBean> productList;
    Activity activity;
    Fragment selectedFragment;
    JSONObject lngObject;
    public LinearLayout linearLayout;
    public static String first,looking_forId,model_id,timeline,looking_for,address,location_det;
    SessionManager session;;
    public static CardView cardView;

    public FarmsImageAdapter(Activity activity, List<FarmsImageBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
    session=new SessionManager(activity);

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image,image_looking,edit;
        public TextView prod_price,prod_name,duration,farmer_name,location,connect;


        public  LinearLayout linear_looking_main;


        public MyViewHolder(View view) {
            super(view);

            prod_price=view.findViewById(R.id.prod_price);
            prod_name=view.findViewById(R.id.prod_name);
            linear_looking_main=view.findViewById(R.id.linear_looking_main);
            image_looking=view.findViewById(R.id.image_looking);
            edit=view.findViewById(R.id.edit);


        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.farm_img_item, parent, false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final FarmsImageBean products = productList.get(position);

        try {

            holder.prod_price.setText(products.getProd_price());
            holder.prod_name.setText(products.getModelname() + " " + products.getHp());


            looking_forId = products.getId();
            model_id = products.getModelname();
            timeline = products.getDuration();
            address = products.getLocation();
        }catch (Exception e){

        }

System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"+products.getId());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location_det= products.getLocation_details();
                looking_forId=products.getId();


                selectedFragment = Edit_Looking_For_Fragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("looking1");
                transaction.commit();
            }
        });


        try {
            Glide.with(activity).load(products.getImage())
                    //  Glide.with(activity).load(R.drawable.tractor_sonalika)

                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image_looking);
        } catch (
                Exception e) {
            e.printStackTrace();
        }

        try {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width_px = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height_px =Resources.getSystem().getDisplayMetrics().heightPixels;
        int height_set=(int)(height_px*0.6);
        System.out.println("height&Width"+width_px+","+height_px);


        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width_px,height_set);
            holder.linear_looking_main.setLayoutParams(parms);

        } catch (
                Exception e) {
            e.printStackTrace();
        }




        try {
            lngObject = new JSONObject(session.getRegId("language"));
           // holder.connect.setText(lngObject.getString("connect"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh"+productList.size());
        return productList.size();
    }

}