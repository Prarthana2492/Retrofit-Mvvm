package com.FarmPe.Farmer.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.Farmer.Fragment.AddFirstFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.FarmPe.Farmer.Bean.AddTractorBean;
import com.FarmPe.Farmer.Fragment.AddBrandFragment;
import com.FarmPe.Farmer.R;

import java.util.List;

public class AddFirstAdapter extends RecyclerView.Adapter<AddFirstAdapter.MyViewHolder>  {
    private List<AddTractorBean> productList;
    Activity activity;
    Fragment selectedFragment;

    public LinearLayout linearLayout;
    public static LinearLayout next_arw;
    public static String first,looinkgId,purchase_tractor;
    public static CardView cardView;
    public AddFirstAdapter(Activity activity, List<AddTractorBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;


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
                .inflate(R.layout.request_price_adapter, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final AddTractorBean products = productList.get(position);
       // looinkgId=products.getId();

        holder.prod_price.setText(products.getProd_name());

        purchase_tractor=products.getProd_name();

        Glide.with(activity).load(products.getImage())

                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);

//        if (productList.get(position).isSelected()){
//            holder.item.setBackgroundResource(R.drawable.grey_background_drawable);
//
//        }else {
//            holder.item.setBackgroundResource(R.drawable.border_transperent);
//        }


        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddFirstFragment.tracter_title = holder.prod_price.getText().toString().toLowerCase().replace(" price","");
                looinkgId=products.getId();

              //  AddBrandAdapter.brandId = null;
                selectedFragment = AddBrandFragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("req_price");
                transaction.commit();

//                for (int i = 0; i < productList.size(); i++) {
//                    productList.get(i).setSelected(false);
//                }
//                productList.get(position).setSelected(true);
//                notifyDataSetChanged();

            }
        });




    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}