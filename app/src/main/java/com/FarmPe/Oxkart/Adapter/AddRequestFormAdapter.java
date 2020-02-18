package com.FarmPe.Oxkart.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.FarmPe.Oxkart.Bean.FarmsImageBean;
import com.FarmPe.Oxkart.R;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class AddRequestFormAdapter extends RecyclerView.Adapter<AddRequestFormAdapter.MyViewHolder>  {
    private List<FarmsImageBean> productList;
    Activity activity;
    Fragment selectedFragment;

    public LinearLayout linearLayout;
   public static LinearLayout next_arw;
    public static String first;
    public static CardView cardView;
    public AddRequestFormAdapter(Activity activity, List<FarmsImageBean> moviesList) {
        this.productList = moviesList;


    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public LinearLayout item;
        public TextView prod_price,prod_name,duration,farmer_name,location,connect;




        public MyViewHolder(View view) {
            super(view);

            prod_price=view.findViewById(R.id.prod_price);
            image=view.findViewById(R.id.prod_img);
            item=view.findViewById(R.id.item);


        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_first_layout_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final FarmsImageBean products = productList.get(position);

        holder.prod_price.setText(products.getLooking_fordetails());

        Glide.with(activity).load(products.getModel_image())
                .thumbnail(0.5f)
                // .crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.avatarmale))
                .into(holder.image);


//        Glide.with(activity).load(products.getModel_image())
//
//                .thumbnail(0.5f)
//                //.crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.image);
//
//
//    }
    }

    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh"+productList.size());
        return productList.size();
    }

}