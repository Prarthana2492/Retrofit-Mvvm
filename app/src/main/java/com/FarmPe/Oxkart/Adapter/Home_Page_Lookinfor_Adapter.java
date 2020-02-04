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


import com.FarmPe.Oxkart.Bean.Request_Class_HomePage_Bean;
import com.FarmPe.Oxkart.R;

import java.util.Date;
import java.util.List;

public class Home_Page_Lookinfor_Adapter extends RecyclerView.Adapter<Home_Page_Lookinfor_Adapter.MyViewHolder>  {
    private List<Request_Class_HomePage_Bean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static String accept_trip;
    Date o_date;
    //    SessionManager session;
    public static CardView cardView;

    public Home_Page_Lookinfor_Adapter(Activity activity, List<Request_Class_HomePage_Bean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
//        session=new SessionManager(activity);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        // public TextView area;
        public TextView name;
        public ImageView pay_recharge_img;
        LinearLayout recharge_item;

        public MyViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.pay_text);
            pay_recharge_img=view.findViewById(R.id.pay_recharge_img);
            recharge_item=view.findViewById(R.id.recharge_item);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_page_lookinfor_adapter, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Request_Class_HomePage_Bean products = productList.get(position);
        if (position==0){
            holder.name.setText("Agri Consultancy");
           holder.pay_recharge_img.setImageResource(R.drawable.ic_agronomy);
//            holder.recharge_item.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    selectedFragment = MobileRecharge.newInstance();
//                    FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame_layout_home_main, selectedFragment);
//                    transaction.addToBackStack("home");
//                    transaction.commit();
//                }
//            });
        }else if (position==1){
            holder.name.setText("Bussiness Plan");
           holder.pay_recharge_img.setImageResource(R.drawable.ic_analysis);
//            holder.recharge_item.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    selectedFragment = DTHRecharge.newInstance();
//                    FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame_layout_home_main, selectedFragment);
//                    transaction.addToBackStack("home");
//                    transaction.commit();
//                }
//            });
        }else if (position==2){
            holder.name.setText("Legal");
            holder.pay_recharge_img.setImageResource(R.drawable.ic_certificate);
//            holder.recharge_item.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    selectedFragment = ElectricityBillPay.newInstance();
//                    FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame_layout_home_main, selectedFragment);
//                    transaction.addToBackStack("home");
//                    transaction.commit();
//                }
//            });
        }else if (position==3) {
            holder.name.setText("More");
            holder.pay_recharge_img.setImageResource(R.drawable.ic_add_plus);
//            holder.recharge_item.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    selectedFragment = BookCylinder.newInstance();
//                    FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame_layout_home_main, selectedFragment);
//                    transaction.addToBackStack("home");
//                    transaction.commit();
//                }
//            });

        }
//        }else if (position==4){
//            holder.name.setText("Farm Machines");
//            holder.pay_recharge_img.setImageResource(R.drawable.ic_add_plus);
//        }else if (position==5){
//            holder.name.setText("Power Tillers");
//            holder.pay_recharge_img.setImageResource(R.drawable.ic_add_plus);
//        }else if (position==6){
//            holder.name.setText("Tractor Implements");
//            holder.pay_recharge_img.setImageResource(R.drawable.ic_add_plus);
//        }else if (position==7){
//            holder.name.setText("Backhoe Attachment");
//            holder.pay_recharge_img.setImageResource(R.drawable.ic_add_plus);
//        }else if (position==8){
//            holder.name.setText("Irrigation System");
//            holder.pay_recharge_img.setImageResource(R.drawable.ic_add_plus);
//        }else if (position==9){
//            holder.name.setText("Tractor Accessories");
//            holder.pay_recharge_img.setImageResource(R.drawable.ic_add_plus);
//        }else if (position==10){
//            holder.name.setText("Tyres");
//            holder.pay_recharge_img.setImageResource(R.drawable.ic_add_plus);
//        }else if (position==11){
//            holder.name.setText("Fence wires");
//            holder.pay_recharge_img.setImageResource(R.drawable.ic_add_plus);
//        }






    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}