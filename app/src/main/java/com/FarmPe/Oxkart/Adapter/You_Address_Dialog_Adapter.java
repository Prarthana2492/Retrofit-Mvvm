//package com.FarmPe.Farmer.Adapter;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//
//
//import com.FarmPe.Farmer.Bean.Add_New_Address_Bean;
//import com.FarmPe.Farmer.Bean.StateBean;
//import com.FarmPe.Farmer.Fragment.Add_New_Address_Fragment;
//import com.FarmPe.Farmer.Fragment.Model_Brochure_Fragment;
//
//import com.FarmPe.Farmer.R;
//import com.FarmPe.Farmer.SessionManager;
//
//import java.util.List;
//
//public class You_Address_Dialog_Adapter extends RecyclerView.Adapter<You_Address_Dialog_Adapter.HoblisMyViewHolder> {
//
//    List<Add_New_Address_Bean>productList;
//    public static String add_id,district_addrs,tehsil_addrs;
//    Activity activity;
//    public static int selected_position = 0;
//    Fragment selectedFragment = null;
//
//
//
//    public You_Address_Dialog_Adapter(List<Add_New_Address_Bean> moviesList, Activity activity) {
//        this.productList = moviesList;
//        this.activity = activity;
//    }
//
//    @NonNull
//    @Override
//    public HoblisMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View stateview=LayoutInflater.from(parent.getContext()).inflate(R.layout.default_address_adapter,parent,false);
//
//        return new HoblisMyViewHolder(stateview);
//    }
//
//
//    @Override
//    public void onBindViewHolder(@NonNull final HoblisMyViewHolder holder, final int position) {
//        final Add_New_Address_Bean products = productList.get(position);
//
//
//        if(selected_position == position){
//
//            holder.adrr_txt.setChecked(true);
//
//
//        } else{
//
//            holder.adrr_txt.setChecked(false);
//        }
//
//
//        add_id =products.getAdd_id();
//        district_addrs =products.getAdd_district();
//        tehsil_addrs =products.getAdd_taluk();
//
//        Request_Details_New.addId = add_id;
//
//        System.out.println("1123213213" + products.getAdd_id() );
//        System.out.println("1123213213name" + products.getAdd_name() );
//
//        holder.adrr_txt.setText(products.getAdd_name());
//        holder.street_addrss.setText(products.getAdd_door_no());
//        holder.colony.setText(products.getAdd_street());
//        holder.area_district.setText(products.getAdd_district()+","+products.getAdd_taluk()+","+products.getAdd_hobli());
//        holder.city_1.setText(products.getAdd_state()+" - " + products.getAdd_pincode());
//        holder.mobile_no.setText("Phone No - " + products.getAdd_mobile());
//
//
//        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
//        {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//              //  Request_Details_New.addId = products.getAdd_id();
//                // checkedId is the RadioButton selected
//                if (holder.adrr_txt.isChecked()) {
//                    selected_position = position;
//                    Request_Details_New.dialog.dismiss();
//                    Request_Details_New.address_text.setText(products.getAdd_district() + "," + products.getAdd_taluk());
//                }
//            }
//        });
//
//
//        holder.state_name_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               // Request_Details_New.addId = products.getAdd_id();
//
//                selected_position = position;
//
//               // Request_Details_New.dialog.dismiss();
//
//           // Request_Details_New.address_text.setText(products.getAdd_district() + "," + products.getAdd_taluk());
//            }
//        });
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return productList.size();
//    }
//
//    public class HoblisMyViewHolder extends RecyclerView.ViewHolder{
//        TextView name,mobile_no,street_addrss,landmrk,city_1,edit_1,delete_1,default_1,default_add,area_district,colony;
//        LinearLayout add_new_adress,state_name_layout;
//        RadioButton adrr_txt;
//        RadioGroup radioGroup;
//
//
//        public HoblisMyViewHolder(View itemView) {
//            super(itemView);
//
//
//
//            mobile_no= itemView.findViewById(R.id.mobile_no1);
//            street_addrss= itemView.findViewById(R.id.street_address1);
//            city_1= itemView.findViewById(R.id.city_1);
//            edit_1= itemView.findViewById(R.id.edit_1);
//            delete_1 = itemView.findViewById(R.id.delete_1);
//            default_1 = itemView.findViewById(R.id.default_1);
//            default_add = itemView.findViewById(R.id.default_add);
//            area_district = itemView.findViewById(R.id.districttt);
//            colony = itemView.findViewById(R.id.colony);
//            adrr_txt = itemView.findViewById(R.id.adrr_txt);
//            radioGroup= (RadioGroup) itemView.findViewById(R.id.radio_group);
//            state_name_layout = itemView.findViewById(R.id.state_name_layout);
//            add_new_adress = itemView.findViewById(R.id.linear_frame);
//
//            // sessionManager = new SessionManager(activity);
//
//        }
//    }
//}
