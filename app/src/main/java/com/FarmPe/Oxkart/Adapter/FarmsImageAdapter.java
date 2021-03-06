package com.FarmPe.Oxkart.Adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
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

import com.FarmPe.Oxkart.Fragment.Edit_LookingFor_Fragment;
import com.FarmPe.Oxkart.Fragment.Preview_Edit_Looking_Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.FarmPe.Oxkart.Bean.FarmsImageBean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class  FarmsImageAdapter extends RecyclerView.Adapter<FarmsImageAdapter.MyViewHolder>  {
    private List<FarmsImageBean> productList;
    Activity activity;
    Fragment selectedFragment;
    JSONObject lngObject;
    public LinearLayout linearLayout;
    public static String first,looking_forId,model_id,timeline,looking_for,address,id;
    SessionManager session;;
    public static CardView cardView;



    public FarmsImageAdapter(Activity activity, List<FarmsImageBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
    session=new SessionManager(activity);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView image,image_looking,edit;
        public TextView looking_fr_details,item_model_name,duration,farmer_name,location,edit_looking,selectt,preview_model;
        public  LinearLayout linear_looking_main;



        public MyViewHolder(View view) {
            super(view);

            looking_fr_details=view.findViewById(R.id.looking_fr_details);
            item_model_name=view.findViewById(R.id.item_model_name);
            linear_looking_main=view.findViewById(R.id.linear_looking_main);
            image_looking=view.findViewById(R.id.image_looking);
            edit=view.findViewById(R.id.edit);
            selectt=view.findViewById(R.id.selectt);
//            edit_looking=view.findViewById(R.id.edit_looking);
//            preview_model=view.findViewById(R.id.preview_model);

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


        System.out.println("dhfuifuisah" + products.getId());
        holder.selectt.setVisibility(View.GONE);


        try {

            holder.looking_fr_details.setText(products.getLooking_fordetails());
            holder.item_model_name.setText(products.getModelname() + " " + products.getHorse_power());


        }catch (Exception e){
            e.printStackTrace();

        }

//        holder.edit_looking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                model_id = products.getModelid();
//                id = products.getId();
//                address = products.getLocation();
//                looking_forId = products.getLookingfordetails_id();
//                System.out.println("dhfuifuisah" + products.getId());
//                selectedFragment = Edit_LookingFor_Fragment.newInstance();
//                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_menu, selectedFragment);
//                transaction.addToBackStack("edit_looking");
//                transaction.commit();
//            }
//        });
//

//
//        holder.preview_model.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                Bundle bundle = new Bundle();
//                bundle.putString("status","lookng_for");
//                selectedFragment = Preview_Edit_Looking_Fragment.newInstance();
//                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_menu, selectedFragment);
//                transaction.addToBackStack("looking_for_edit");
//                selectedFragment.setArguments(bundle);
//                transaction.commit();
//
//            }
//        });
//

        try {

            Glide.with(activity).load(products.getModel_image())
                    .thumbnail(0.5f)
                    // .crossFade()
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                            .error(R.drawable.avatarmale))
                    .into(holder.image);

//            Glide.with(activity).load(products.getModel_image())
//                    //  Glide.with(activity).load(R.drawable.tractor_sonalika)
//
//                    .thumbnail(0.5f)
//                    //.crossFade()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .centerCrop()
//                    .into(holder.image_looking);

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



    }


    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh"+productList.size());
        return productList.size();
    }

}