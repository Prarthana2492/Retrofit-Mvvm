package com.FarmPe.Farmer.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.Farmer.Fragment.AddModelFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.FarmPe.Farmer.Bean.AddTractorBean;
import com.FarmPe.Farmer.Fragment.AddHpFragment;
import com.FarmPe.Farmer.R;

import java.util.List;

public class AddBrandAdapter extends RecyclerView.Adapter<AddBrandAdapter.MyViewHolder>  {
    private List<AddTractorBean> productList;
    Activity activity;
    Fragment selectedFragment;

    public LinearLayout linearLayout;
    public static String first;
    public static CardView cardView;
    public static String brandId;
    public AddBrandAdapter(Activity activity, List<AddTractorBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;


    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public LinearLayout item;
        public TextView prod_price,prod_name,duration,location;





        public MyViewHolder(View view) {
            super(view);
            prod_price=view.findViewById(R.id.prod_price);
            image=view.findViewById(R.id.prod_img);
            item=view.findViewById(R.id.item);
            linearLayout=view.findViewById(R.id.linear_layout);


        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.brand_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final AddTractorBean products = productList.get(position);
        brandId=products.getId();
        holder.prod_price.setText(products.getProd_name());
        System.out.println("croptttt"+ brandId);


        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(products.getImage().equalsIgnoreCase("")){
                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "No Brands", duration);
                    View snackbarView2 = snackbar.getView();
                    TextView tv = (TextView) snackbarView2.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(activity,R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snackbar.show();

                }else {


                    brandId = products.getId();
                    selectedFragment = AddModelFragment.newInstance();
                    FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.addToBackStack("third");
                    transaction.commit();

                }
//                for (int i = 0; i < productList.size(); i++) {
//                    productList.get(i).setSelected(false);
//                }
//                productList.get(position).setSelected(true);
//                notifyDataSetChanged();

            }
        });


       /* holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddHpAdapter.hp_model = null;
                selectedFragment = AddHpFragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("second");
                transaction.commit();
            }
        });*/

        Glide.with(activity).load(products.getImage())

                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);


//        if (productList.get(position).isSelected()){
//                      holder.item.setBackgroundResource(R.drawable.grey_background_drawable);


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