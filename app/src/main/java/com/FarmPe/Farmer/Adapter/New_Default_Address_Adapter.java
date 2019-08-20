package com.FarmPe.Farmer.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Farmer.Bean.Add_New_Address_Bean;
import com.FarmPe.Farmer.Bean.StateBean;
import com.FarmPe.Farmer.R;

import java.util.List;

public class New_Default_Address_Adapter extends RecyclerView.Adapter<New_Default_Address_Adapter.HoblisMyViewHolder> {

    List<Add_New_Address_Bean>stateBeans;
    public static String villageid;
    Activity activity;



    public New_Default_Address_Adapter(List<Add_New_Address_Bean> stateBeans, Activity activity) {
        this.stateBeans = stateBeans;
        this.activity = activity;
    }



    @NonNull
    @Override
    public HoblisMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View stateview=LayoutInflater.from(parent.getContext()).inflate(R.layout.state_name,parent,false);


        return new HoblisMyViewHolder(stateview);
    }

    @Override
    public void onBindViewHolder(@NonNull final HoblisMyViewHolder holder, int position) {
        final Add_New_Address_Bean stateBean=stateBeans.get(position);
       //holder.statename.setText(stateBean.getName());


    }

    @Override
    public int getItemCount() {
        return stateBeans.size();
    }

    public class HoblisMyViewHolder extends RecyclerView.ViewHolder{
        TextView statename;
        LinearLayout state_name_layout;
        public HoblisMyViewHolder(View itemView) {
            super(itemView);
            statename=itemView.findViewById(R.id.state_item);
            state_name_layout=itemView.findViewById(R.id.state_name_layout);

        }
    }
}
