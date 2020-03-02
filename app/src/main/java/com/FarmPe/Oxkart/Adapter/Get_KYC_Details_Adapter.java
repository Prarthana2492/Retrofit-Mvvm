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
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Oxkart.Bean.BankBean;
import com.FarmPe.Oxkart.Bean.KYC_Bean;
import com.FarmPe.Oxkart.Fragment.Add_New_Bank_Account_Details_Fragment;
import com.FarmPe.Oxkart.Fragment.Get_Bank_List_Fragment;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONObject;

import java.util.List;


public class Get_KYC_Details_Adapter extends RecyclerView.Adapter<Get_KYC_Details_Adapter.MyViewHolder> {

    private List<KYC_Bean> productList;
    Activity activity;
    Fragment selectedFragment;
    SessionManager sessionManager;
    public static String bank_id;
    LinearLayout linear_layout;



    public Get_KYC_Details_Adapter(Activity activity, List<KYC_Bean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout item;
        public TextView bankname,name,acc_no,area,delete_bank_details,edit_bank_details;


        public MyViewHolder(View view) {
            super(view);

            // item=view.findViewById(R.id.item);
            bankname=view.findViewById(R.id.bankname);
            name=view.findViewById(R.id.name);
            acc_no=view.findViewById(R.id.acc_no);
            area=view.findViewById(R.id.area);
            delete_bank_details = view.findViewById(R.id.delete_bank_details);
            edit_bank_details = view.findViewById(R.id.edit_bank_details);
            linear_layout = view.findViewById(R.id.linear_layout);
            sessionManager = new SessionManager(activity);


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
        final KYC_Bean products = productList.get(position);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }










}
