package com.FarmPe.Oxkart.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.FarmPe.Oxkart.Bean.AddTractorBean2;
import com.FarmPe.Oxkart.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;



public class HomePage_Adapter extends RecyclerView.Adapter<HomePage_Adapter.MyViewHolder> {

    private List<AddTractorBean2> productList;
    Activity activity;


    public HomePage_Adapter(Activity activity, List<AddTractorBean2> moviesList) {
        this.productList = moviesList;
        this.activity=activity;


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView model_name, brand_name;


        public MyViewHolder(View view) {
            super(view);

            image = view.findViewById(R.id.image);
            model_name = view.findViewById(R.id.model_name);
            brand_name = view.findViewById(R.id.brand_name);

        }

    }

     @Override
     public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())

                .inflate(R.layout.homepage_adapter_layout, parent, false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final AddTractorBean2 products = productList.get(position);
        holder.model_name.setText(products.getProd_name());
        holder.brand_name.setText(products.getProd_category());

        System.out.println("dddiiiijgh" + products.getImage());

        Glide.with(activity).load(products.getImage())

                .thumbnail(0.5f)
                //  .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
