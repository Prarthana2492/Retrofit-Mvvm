package com.FarmPe.Farmer.Adapter;

import android.app.Activity;
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

import com.FarmPe.Farmer.Fragment.Request_Details_New;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.FarmPe.Farmer.Bean.AddTractorBean;
import com.FarmPe.Farmer.Fragment.RequestFormFragment;
import com.FarmPe.Farmer.R;

import java.util.List;

public class AddModelAdapter extends RecyclerView.Adapter<AddModelAdapter.MyViewHolder>  {
    private List<AddTractorBean> productList;
    Activity activity;
    Fragment selectedFragment;

    public LinearLayout linearLayout;
   public static LinearLayout next_arw;
    public static String first,tractor_id;
    public static CardView cardView;
    public AddModelAdapter(Activity activity, List<AddTractorBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public LinearLayout item;
        public TextView prod_price,prod_name,duration,farmer_name,location,connect,select;




        public MyViewHolder(View view) {
            super(view);

            prod_price=view.findViewById(R.id.prod_price);
            item=view.findViewById(R.id.itemmm);
            image=view.findViewById(R.id.imageff);
            select=view.findViewById(R.id.selectt);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.select_model_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final AddTractorBean products = productList.get(position);

        holder.prod_price.setText(products.getProd_name());
        System.out.println("nammmmm"+products.getProd_name());

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tractor_id=products.getId();

                for (int i = 0; i < productList.size(); i++) {
                    productList.get(i).setSelected(false);
                }
                productList.get(position).setSelected(true);
                notifyDataSetChanged();

            }
        });


        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tractor_id=products.getId();

                selectedFragment = Request_Details_New.newInstance();
                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("fourth");
                transaction.commit();
            }
        });


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


    }

    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh"+productList.size());
        return productList.size();
    }

}