package com.FarmPe.Farmer.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.FarmPe.Farmer.Bean.Request_Class_HomePage_Bean;
import com.FarmPe.Farmer.R;

import java.util.Date;
import java.util.List;


public class Home_Page_Request_Adapter extends RecyclerView.Adapter<Home_Page_Request_Adapter.MyViewHolder>  {
    private List<Request_Class_HomePage_Bean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static String accept_trip;
    Date o_date;
    //    SessionManager session;
    public static CardView cardView;
    public Home_Page_Request_Adapter(Activity activity,List<Request_Class_HomePage_Bean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
//        session=new SessionManager(activity);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView pay_img;
        public TextView name;
        // public TextView confirmbutton;
        //public TextView accept;
        //  LinearLayout location_layout;

        public MyViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.crop_loan);
            pay_img=view.findViewById(R.id.pay_img);


        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.homepage_requestprice_adapter_layout, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Request_Class_HomePage_Bean products = productList.get(position);

        if (position==0){
            holder.name.setText("Tractor");
            holder.pay_img.setImageResource(R.drawable.tractor);
        }else if (position==1){
            holder.name.setText("Farm Truck");
            holder.pay_img.setImageResource(R.drawable.farm_truck);
        }else if (position==2){
            holder.name.setText("Backhoe Loader");
            holder.pay_img.setImageResource(R.drawable.backhoe_loader);
        }else if (position==3){
            holder.name.setText("Harvester");
            holder.pay_img.setImageResource(R.drawable.harvesting);
        }else if (position==4){
            holder.name.setText("Farm Machines");
            holder.pay_img.setImageResource(R.drawable.farm_machines);
        }else if (position==5){
            holder.name.setText("Power Tillers");
            holder.pay_img.setImageResource(R.drawable.power_tiller);
        }else if (position==6){
            holder.name.setText("Tractor Implements");
            holder.pay_img.setImageResource(R.drawable.tractor_implements);
        }else if (position==7){
            holder.name.setText("Backhoe Attachment");
            holder.pay_img.setImageResource(R.drawable.bachhoe_attachment);
        }else if (position==8){
            holder.name.setText("Irrigation System");
            holder.pay_img.setImageResource(R.drawable.sprinkler);
        }else if (position==9){
            holder.name.setText("Tractor Accessories");
            holder.pay_img.setImageResource(R.drawable.tractor_accesories);
        }else if (position==10){
            holder.name.setText("Tyres");
            holder.pay_img.setImageResource(R.drawable.tyres);
        }else if (position==11){
            holder.name.setText("Fence Wires");
            holder.pay_img.setImageResource(R.drawable.fencing_wire);
        }



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

    @Override
    public int getItemCount() {
        return 12;
    }

}