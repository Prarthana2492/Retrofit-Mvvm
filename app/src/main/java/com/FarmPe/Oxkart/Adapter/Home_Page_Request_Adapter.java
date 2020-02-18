package com.FarmPe.Oxkart.Adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Oxkart.Bean.Request_Class_HomePage_Bean;
import com.FarmPe.Oxkart.Fragment.AddBrandFragment;
import com.FarmPe.Oxkart.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


public class Home_Page_Request_Adapter extends RecyclerView.Adapter<Home_Page_Request_Adapter.MyViewHolder>  {
    private List< Request_Class_HomePage_Bean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static String looinkgId;
    String classname;
    public static CardView cardView;
    public Home_Page_Request_Adapter(Activity activity,List< Request_Class_HomePage_Bean> moviesList,String classname) {
        this.productList = moviesList;
        this.activity = activity;
        this.classname =classname;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView pay_img;
        public TextView name;
          LinearLayout item_layout;
        public MyViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.crop_loan);
            pay_img=view.findViewById(R.id.pay_img);
            item_layout=view.findViewById(R.id.item_layout);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.homepage_requestprice_adapter_layout, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final  Request_Class_HomePage_Bean products = productList.get(position);
       holder.name.setText(products.getVeg_name());



        Glide.with(activity).load(products.getImage())
                .thumbnail(0.5f)
                // .crossFade()
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                        .error(R.drawable.avatarmale))
                .into(holder.pay_img);


//        Glide.with(activity).load(products.getImage())
//                .thumbnail(0.5f)
//                //.crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.pay_img);


        holder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
System.out.println("dfsdfsdf" + classname);
                v.clearAnimation();
                Animation mAnimation = new AlphaAnimation(1, 0);
                mAnimation.setInterpolator(new LinearInterpolator());
                mAnimation.setRepeatMode(Animation.REVERSE);
                holder.item_layout.startAnimation(mAnimation);
             //   AddFirstFragment.tracter_title = holder.name.getText().toString().toLowerCase().replace(" price","");
                looinkgId = products.getId();
                System.out.println("asaAAAA" + products.getId());
                Bundle bundle = new Bundle();
                if (classname.equals("home_menu")){
                    bundle.putString("request_status",looinkgId);
                    bundle.putString("status_home","HOME_REQ_PRICE");
                }else {
                    bundle.putString("request_status", looinkgId);
                    bundle.putString("status_home", "REQ_PRICE");
                }
                selectedFragment = AddBrandFragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("req_price");
                selectedFragment.setArguments(bundle);
                transaction.commit();



//                for (int i = 0; i < productList.size(); i++) {
//                    productList.get(i).setSelected(false);
//                }
//                productList.get(position).setSelected(true);
//                notifyDataSetChanged();

            }
        });





//        if (position==0){
//            holder.name.setText("Tractor");
//           // holder.pay_img.setImageResource(R.drawable.tractor);
//            image_loading(R.drawable.tractor, holder.pay_img);
//        }else if (position==1){
//            holder.name.setText("Farm\nTruck");
//            //holder.pay_img.setImageResource(R.drawable.farm_truck);
//            image_loading(R.drawable.farm_truck, holder.pay_img);
//
//        }else if (position==2){
//            holder.name.setText("Backhoe\nLoader");
//            image_loading(R.drawable.backhoe_acessories, holder.pay_img);
//          //  holder.pay_img.setImageResource(R.drawable.backhoe_loader);
//
//        }else if (position==3){
//            holder.name.setText("Harvester");
//            image_loading(R.drawable.harvesting, holder.pay_img);
//           // holder.pay_img.setImageResource(R.drawable.harvesting);
//        }else if (position==4){
//            holder.name.setText("Farm\nMachines");
//            image_loading(R.drawable.machinary, holder.pay_img);
//           // holder.pay_img.setImageResource(R.drawable.farm_machines);
//        }else if (position==5){
//            holder.name.setText("Power\nTillers");
//            image_loading(R.drawable.tiller, holder.pay_img);
//           // holder.pay_img.setImageResource(R.drawable.power_tiller);
//        }else if (position==6){
//            holder.name.setText("Tractor\nImplements");
//            image_loading(R.drawable.tractor_implements, holder.pay_img);
//          //  holder.pay_img.setImageResource(R.drawable.tractor_implements);
//        }else if (position==7){
//            holder.name.setText("Backhoe\nAttachment");
//            image_loading(R.drawable.backhoe, holder.pay_img);
//            //holder.pay_img.setImageResource(R.drawable.bachhoe_attachment);
//        }else if (position==8){
//            holder.name.setText("Irrigation\nSystem");
//            image_loading(R.drawable.sprinkler, holder.pay_img);
//          //  holder.pay_img.setImageResource(R.drawable.sprinkler);
//        }else if (position==9){
//            holder.name.setText("Tractor\n Accessories");
//            image_loading(R.drawable.accessories, holder.pay_img);
//           // holder.pay_img.setImageResource(R.drawable.tractor_accesories);
//        }else if (position==10){
//            holder.name.setText("Tyres");
//            image_loading(R.drawable.tyre, holder.pay_img);
//          //  holder.pay_img.setImageResource(R.drawable.tyres);
//        }else if (position==11){
//            holder.name.setText("Fence\nWires");
//            image_loading(R.drawable.fencing_wire, holder.pay_img);
//           // holder.pay_img.setImageResource(R.drawable.fencing_wire);
//        }



//        holder.name.setText(products.getVeg_name());
      /*  Glide.with(activity).load("http://bookmymoment.azurewebsites.net"+products.getProductImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.prod_image);*/
       /* holder.name.setText(products.getName());
        holder.area.setText(products.getArea());*/

       /* holder.location_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment= TripDetailsFragment.newInstance();
                FragmentTransaction ft = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                //ft.addToBackStack("shoplist");
                ft.replace(R.id.frame_layout, selectedFragment);
                ft.commit();
            }
        });*/


    }

//    private void image_loading(int request_image,ImageView imageView) {
//
//        Glide.with(activity).load(request_image)
//                .thumbnail(0.5f)
//                .crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imageView);
//
//
//    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}
