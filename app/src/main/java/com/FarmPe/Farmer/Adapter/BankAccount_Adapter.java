package com.FarmPe.Farmer.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Farmer.Bean.BankBean;
import com.FarmPe.Farmer.R;

import java.util.List;

public class BankAccount_Adapter extends RecyclerView.Adapter<BankAccount_Adapter.MyViewHolder> {

    private List<BankBean> productList;
    Activity activity;
    Fragment selectedFragment;



    public BankAccount_Adapter(Activity activity, List<BankBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout item;
        public TextView bankname,name,phone_no,ifsc,area,city;


        public MyViewHolder(View view) {
            super(view);

           // item=view.findViewById(R.id.item);
            bankname=view.findViewById(R.id.bankname);
            name=view.findViewById(R.id.name);
            phone_no=view.findViewById(R.id.ph_no);
            area=view.findViewById(R.id.area);


        }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())


                .inflate(R.layout.bankaccount_adapter, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final BankBean products = productList.get(position);



        holder.bankname.setText(products.getBankname());
        holder.name.setText(products.getName());
        holder.phone_no.setText(products.getPhonenumber()+" | "+products.getIfsccode());
        holder.area.setText(products.getArea()+", "+products.getCity());


       /* holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedFragment = Spices_Category_Fragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("spicescateory");
                transaction.commit();
            }
        });
*/


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }










}
