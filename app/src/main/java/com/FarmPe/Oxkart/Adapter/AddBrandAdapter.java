package com.FarmPe.Oxkart.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.FarmPe.Oxkart.Fragment.AddBrandFragment;
import com.FarmPe.Oxkart.Fragment.AddModelFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.FarmPe.Oxkart.Bean.AddTractorBean;
import com.FarmPe.Oxkart.R;
import com.bumptech.glide.request.RequestOptions;

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
        public ImageView image,default_image;
        public LinearLayout item;
        public TextView prod_price,prod_name,duration,location;



        public MyViewHolder(View view) {
            super(view);
            prod_price=view.findViewById(R.id.prod_price);
            image=view.findViewById(R.id.prod_img);
            item=view.findViewById(R.id.item);
            linearLayout=view.findViewById(R.id.linear_layout);
            default_image=view.findViewById(R.id.default_img);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.brand_item, parent, false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final AddTractorBean products = productList.get(position);



        brandId=products.getId();
        holder.prod_price.setText(products.getProd_name());
        System.out.println("croptttt"+ brandId);


        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                v.clearAnimation();
                Animation mAnimation = new AlphaAnimation(1, 0);
                mAnimation.setInterpolator(new LinearInterpolator());
                mAnimation.setRepeatMode(Animation.REVERSE);
                holder.item.startAnimation(mAnimation);



                if(products.getImage().equalsIgnoreCase("")){

                    Toast toast = Toast.makeText(activity,"No Brands", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                    toast.show();



               }else {

                     brandId = products.getId();
                     Bundle bundle = new Bundle();
                     bundle.putString("status_home",AddBrandFragment.price);
                     selectedFragment = AddModelFragment.newInstance();
                     FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                     transaction.replace(R.id.frame_menu, selectedFragment);
                     selectedFragment.setArguments(bundle);
                     transaction.addToBackStack("third");
                     transaction.commit();

                }



//           for (int i = 0; i < productList.size(); i++) {
//                    productList.get(i).setSelected(false);
//                }
//                productList.get(position).setSelected(true);
//                notifyDataSetChanged();

            }
        });


//        holder.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AddHpAdapter.hp_model = null;
//                selectedFragment = AddHpFragment.newInstance();
//                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
//                transaction.add(R.id.frame_layout, selectedFragment);
//                transaction.addToBackStack("second");
//                transaction.commit();
//            }
//        });


//


//        Glide.with(activity).load(products.getImage())
//
//                .thumbnail(0.5f)
//                .centerCrop()
//              //  .crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.image);

System.out.println("dsfdsfewr" + products.getImage());


   if(products.getImage().equalsIgnoreCase("")){

       holder.default_image.setVisibility(View.VISIBLE);


     }else{


       holder.image.setVisibility(View.VISIBLE);



       Glide.with(activity).load(products.getImage())
            .thumbnail(0.5f)
            //   .crossFade()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.image);


    }

//        Glide.with(activity).load(products.getImage())
//                .thumbnail(1.0f)
//                // .crossFade()
//                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
//                        .error(R.drawable.ic_photo))
//                .into(holder.image);




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