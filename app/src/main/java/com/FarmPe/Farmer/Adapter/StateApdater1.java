package com.FarmPe.Farmer.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Farmer.Bean.StateBean;

import com.FarmPe.Farmer.Fragment.Add_New_Address_Fragment;
import com.FarmPe.Farmer.Fragment.Profile_Add_New_Address_Fragment;
import com.FarmPe.Farmer.R;

import java.util.List;

public class StateApdater1 extends RecyclerView.Adapter<StateApdater1.MyStateHolder> {

    List<StateBean>stateBeans;
    Activity activity;
   public static String stateid;


    public StateApdater1(List<StateBean> stateBeans, Activity activity) {
        this.stateBeans = stateBeans;
        this.activity=activity;
    }


    @NonNull
    @Override
    public MyStateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View stateview=LayoutInflater.from(parent.getContext()).inflate(R.layout.state_name,parent,false);
        return new MyStateHolder(stateview);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyStateHolder holder, int position) {
    final StateBean stateBean=stateBeans.get(position);

         holder.statename.setText(stateBean.getName());


        holder.state_name_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
//Find the currently focused view, so we can grab the correct window token from it.
                view = activity.getCurrentFocus();
//If no view currently has focus, create a new one, just so we can grab a window token from it
                if (view  == null) {
                    view = new View(activity);
                }
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


                stateid=stateBean.getId();


                Profile_Add_New_Address_Fragment.state.setText(holder.statename.getText().toString());
                Profile_Add_New_Address_Fragment.drawer.closeDrawers();
               // New_Address_Fragment .search.setText("");

            }
        });

    }


     @Override
     public int getItemCount() {
        return stateBeans.size();
    }

     public class MyStateHolder extends RecyclerView.ViewHolder{

         TextView statename;
         LinearLayout state_name_layout;


        public MyStateHolder(View itemView) {

            super(itemView);
            statename=itemView.findViewById(R.id.state_item);
            state_name_layout=itemView.findViewById(R.id.state_name_layout);

        }
    }
}
