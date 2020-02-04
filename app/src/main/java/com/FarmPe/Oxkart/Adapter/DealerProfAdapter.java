package com.FarmPe.Oxkart.Adapter;

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
import android.widget.TextView;

import com.FarmPe.Oxkart.Bean.DealerProfBean;
import com.FarmPe.Oxkart.Fragment.Book_Nw_Requirement_Details;
import com.FarmPe.Oxkart.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;



public class DealerProfAdapter extends RecyclerView.Adapter<DealerProfAdapter.MyViewHolder>  {
    private List<DealerProfBean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static String preview_stat;




    public static CardView cardView;
    public DealerProfAdapter(Activity activity, List<DealerProfBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView dealername,product,location,edit_prof,preview_prof;



        public MyViewHolder(View view) {
            super(view);
            dealername=view.findViewById(R.id.dealer_name);
            product=view.findViewById(R.id.product);
            location=view.findViewById(R.id.location);
            image=view.findViewById(R.id.prod_img);
//            edit_prof=view.findViewById(R.id.edit_prof);
//            preview_prof=view.findViewById(R.id.preview_prof);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dealer_prof_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final DealerProfBean products = productList.get(position);
        holder.dealername.setText(products.getDealer_name());
        holder.product.setText(products.getProduct()+", "+products.getDealer_type());
        holder.location.setText(products.getLocation());


        try {
            Glide.with(activity).load(products.getImage())
                    //  Glide.with(activity).load(R.drawable.tractor_sonalika)

                    .thumbnail(0.5f)
                    //.crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image);
        } catch (
                Exception e) {
            e.printStackTrace();
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 selectedFragment = Book_Nw_Requirement_Details.newInstance();
                 FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                 transaction.replace(R.id.frame_menu, selectedFragment);
                 transaction.addToBackStack("dealer_page");
                 transaction.commit();


            }
        });

//        holder.edit_prof.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedFragment = EditProfileFragment.newInstance();
//                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout, selectedFragment);
//                transaction.addToBackStack("prof");
//                transaction.commit();
//            }
//        });
//        holder.preview_prof.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                preview_stat="profile";
//                selectedFragment = PreviewProfileFragment.newInstance();
//                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout, selectedFragment);
//                transaction.addToBackStack("prof");
//                transaction.commit();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh"+productList.size());
        return productList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }





}