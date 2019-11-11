package com.FarmPe.Farmer.Adapter;

import android.app.Activity;

import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.FarmPe.Farmer.Bean.ContactVO;
import com.FarmPe.Farmer.Fragment.Addbhimfragment;
import com.FarmPe.Farmer.R;


import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<ContactVO> contactVOList;
    private Context mContext;
    Activity activity;
Fragment selectedFragment;
    public static String stateid;
    public static int selected_position=0;
    public RecyclerViewAdapter(Activity activity, List<ContactVO> contactVOList) {
        this.contactVOList = contactVOList;
        this.activity = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView prod_price, prod_name, duration, location;

        public MyViewHolder(View view) {
            super(view);
            prod_price = view.findViewById(R.id.text1);
            prod_name = view.findViewById(R.id.text2);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ContactVO contactVO = contactVOList.get(position);
        System.out.println("lengthhhhhhh" + contactVOList.size());
        holder.prod_price.setText(contactVO.getContactNumber());
        holder.prod_name.setText(contactVO.getContactName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_position = position;
                stateid=contactVO.getContactNumber();
                System.out.println("lengthhhhhhh" + stateid);
                Bundle bundle = new Bundle();
                bundle.putString("numberid", stateid);
                selectedFragment = Addbhimfragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("addcontacts");
                transaction.commit();
            }
        });
    }
    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh" + contactVOList.size());
        return contactVOList.size();
    }
}