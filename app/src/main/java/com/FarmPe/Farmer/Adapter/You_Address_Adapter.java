package com.FarmPe.Farmer.Adapter;


import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.FarmPe.Farmer.Bean.Add_New_Address_Bean;
import com.FarmPe.Farmer.Fragment.Add_New_Address_Fragment;
import com.FarmPe.Farmer.Fragment.You_Address_Fragment;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Utils;
import com.FarmPe.Farmer.Volly_class.Crop_Post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public class You_Address_Adapter extends RecyclerView.Adapter<You_Address_Adapter.MyViewHolder>{


    private List<Add_New_Address_Bean> productList;
    Activity activity;
    Fragment selectedFragment = null;
    String status,message,status1,message1;

    SessionManager sessionManager;
    public static TextView name,variety,loc,grade,quantity,uom,price,edit;
    public static String add_id;
    public static CardView cardView;
    JSONObject lngObject;
    LinearLayout linearLayout;
    String deleted, default_addrs_updtd;
    BottomSheetDialog mBottomSheetDialog;
    View sheetView;

    public You_Address_Adapter(List<Add_New_Address_Bean> moviesList, Activity activity) {

        this.productList = moviesList;
        this.activity=activity;


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,mobile_no,street_addrss,landmrk,city_1,edit_1,delete_1,default_1,default_add,area_district,colony;
        LinearLayout add_new_adress;


        public MyViewHolder(View view) {
            super(view);


            name = view.findViewById(R.id.name1);
            mobile_no= view.findViewById(R.id.mobile_no1);
            street_addrss= view.findViewById(R.id.street_address1);
            city_1= view.findViewById(R.id.city_1);
            edit_1= view.findViewById(R.id.edit_1);
            delete_1 = view.findViewById(R.id.delete_1);
            default_1 = view.findViewById(R.id.default_1);
            default_add = view.findViewById(R.id.default_add);
            linearLayout = view.findViewById(R.id.bottom_sheet1);
            area_district = view.findViewById(R.id.districttt);
            colony = view.findViewById(R.id.colony);
            add_new_adress = view.findViewById(R.id.linear_frame);
            sessionManager = new SessionManager(activity);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.address_layout, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Add_New_Address_Bean products = productList.get(position);


        if(products.getDefault_addr()){
            System.out.println("11111ddd" + products.getDefault_addr());

            holder.default_add.setVisibility(View.VISIBLE);
            holder.default_1.setVisibility(View.GONE);


        }else{

            holder.default_add.setVisibility(View.GONE);
            holder.default_1.setVisibility(View.VISIBLE);

        }


        add_id =products.getAdd_id();

        System.out.println("1123213213" + products.getAdd_id() );
        System.out.println("1123213213name" + products.getAdd_name() );


        holder.name.setText(products.getAdd_name());
        holder.street_addrss.setText(products.getAdd_door_no());
        holder.colony.setText(products.getAdd_street());
        holder.area_district.setText(products.getAdd_district()+","+products.getAdd_taluk()+","+products.getAdd_hobli());
        holder.city_1.setText(products.getAdd_state()+" - " + products.getAdd_pincode());
        holder.mobile_no.setText("Phone No - " + products.getAdd_mobile());
        // holder.landmrk.setText(products.getAdd_landmark());



        holder.edit_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                add_id =products.getAdd_id();
                Bundle bundle = new Bundle();
                bundle.putString("Addr_name",products.getAdd_name());
                bundle.putString("Addr_mobile",products.getAdd_mobile());
                bundle.putString("Addr_pincode",products.getAdd_pincode());
                bundle.putString("Addr_Street",products.getAdd_street());
                bundle.putString("Addr_Houseno",products.getAdd_door_no());
                // bundle.putString("Addr_landmark",products.getAdd_landmark());
                //bundle.putString("Addr_city",products.getAdd_city());
                bundle.putString("Addr_state",products.getAdd_state());
                bundle.putString("Addr_district",products.getAdd_district());
                bundle.putString("Addr_taluk",products.getAdd_taluk());
                bundle.putString("Addr_hobli",products.getAdd_hobli());
                bundle.putString("Addr_village",products.getAdd_village());
                bundle.putString("Addr_pickup_from",products.getAdd_pickup_frm());
                bundle.putString("Addr_state_id",products.getAdd_state_id());
                bundle.putString("Addr_district_id",products.getAdd_district_id());
                bundle.putString("Addr_tehsil_id",products.getAdd_tehsil_id());
                bundle.putString("Addr_hobli_id",products.getAdd_hobli_id());
                System.out.println("edittttttttttttttttttttttttttttttttttttttttttt"+bundle);
                bundle.putString("navigation_from","your_add");


                selectedFragment = Add_New_Address_Fragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("your_add");
                transaction.commit();

            }
        });


        holder.delete_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mBottomSheetDialog = new BottomSheetDialog(activity);
                sheetView = activity.getLayoutInflater().inflate(R.layout.general_layout, null);
                TextView confirm = sheetView.findViewById(R.id.positive_text);
                TextView titleText = sheetView.findViewById(R.id.bottom_sheet_title);
                TextView descriptionText = sheetView.findViewById(R.id.bottom_sheet_description);
                EditText userInput = sheetView.findViewById(R.id.user_text);
                userInput.setVisibility(View.GONE);
                titleText.setText("Remove Address");
                descriptionText.setText("Are you sure you want to remove the Address?");
                confirm.setText("Confirm");
                TextView cancel = sheetView.findViewById(R.id.negetive_text);
                cancel.setText("Cancel");


                try {

                    lngObject = new JSONObject(sessionManager.getRegId("language"));
                    // popuptxt.setText(lngObject.getString("SelectanAddressType"));
                    confirm.setText(lngObject.getString("Confirm"));
                    cancel.setText(lngObject.getString("Cancel"));
                    deleted=lngObject.getString("Addressdeletedsuccessfully");
                    descriptionText.setText(lngObject.getString("Areyousureyouwanttoremovetheaddress"));
                    titleText.setText(lngObject.getString("Removeaddress"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        add_id =products.getAdd_id();

                        try{
                            JSONObject jsonObject  = new JSONObject();
                            jsonObject.put("Id",add_id);
                            jsonObject.put("UserId",sessionManager.getRegId("userId"));

                            Crop_Post.crop_posting(activity, Urls.Delete_Address_Details, jsonObject, new VoleyJsonObjectCallback() {
                                @Override
                                public void onSuccessResponse(JSONObject result) {
                                    System.out.println("111111dddd" + result);

                                    try{

                                        status = result.getString("Status");
                                        message = result.getString("Message");

                                        if(status.equals("1")){


                                            int duration = 1000;
                                            Snackbar snackbar = Snackbar
                                                    .make(You_Address_Fragment.linearLayout, deleted, duration);
                                            View snackbarView2 = snackbar.getView();
                                            TextView tv = (TextView) snackbarView2.findViewById(android.support.design.R.id.snackbar_text);
                                            tv.setBackgroundColor(ContextCompat.getColor(activity,R.color.orange));
                                            tv.setTextColor(Color.WHITE);


                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                                            } else {
                                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                                            }


                                            snackbar.show();

                                        }

                                        productList.remove(position);
                                        notifyDataSetChanged();

                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });


                        }catch (Exception e){
                            e.printStackTrace();
                        }


                        mBottomSheetDialog.dismiss();
                    }
                });




                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mBottomSheetDialog.dismiss();
                    }
                });
                mBottomSheetDialog.setContentView(sheetView);
                mBottomSheetDialog.show();


                /*final TextView yes1,no1,delete_text,popupheading;
                System.out.println("aaaaaaaaaaaa");
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.address_delete_popup);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(Utils.setCancleable);


                ImageView image = dialog.findViewById(R.id.close_popup);
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                no1 =  dialog.findViewById(R.id.no_1);
                no1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                yes1 =  dialog.findViewById(R.id.yes_1);
                delete_text = dialog.findViewById(R.id.text_desc);
                popupheading = dialog.findViewById(R.id.popup_heading);


                try {


                    lngObject = new JSONObject(sessionManager.getRegId("language"));
                    // popuptxt.setText(lngObject.getString("SelectanAddressType"));
                    yes1.setText(lngObject.getString("Confirm"));
                    no1.setText(lngObject.getString("Cancel"));
                    deleted=lngObject.getString("Addressdeletedsuccessfully");
                    delete_text.setText(lngObject.getString("Areyousureyouwanttoremovetheaddress"));
                    popupheading.setText(lngObject.getString("Removeaddress"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                yes1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        add_id =products.getAdd_id();

                        try{
                            JSONObject jsonObject  = new JSONObject();
                            jsonObject.put("Id",add_id);
                            jsonObject.put("UserId",sessionManager.getRegId("userId"));

                            Crop_Post.crop_posting(activity, Urls.Delete_Address_Details, jsonObject, new VoleyJsonObjectCallback() {
                                @Override
                                public void onSuccessResponse(JSONObject result) {
                                    System.out.println("111111dddd" + result);

                                    try{

                                        status = result.getString("Status");
                                        message = result.getString("Message");

                                        if(status.equals("1")){

                                            int duration = 1000;
                                            Snackbar snackbar = Snackbar
                                                      .make(linearLayout, deleted, duration);
                                            View snackbarView2 = snackbar.getView();
                                            TextView tv = (TextView) snackbarView2.findViewById(android.support.design.R.id.snackbar_text);
                                            tv.setBackgroundColor(ContextCompat.getColor(activity,R.color.orange));
                                            tv.setTextColor(Color.WHITE);

                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                            } else {
                                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                                            }

                                            snackbar.show();

                                        }


                                        productList.remove(position);
                                        notifyDataSetChanged();


                                        if(productList.size()<=1){
                                            You_Address_Fragment.address_list.setText(productList.size() + " Address is added");



                                        }else{

                                            You_Address_Fragment.address_list.setText(productList.size() + " Addresses are added");


                                        }



                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });


                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        dialog.dismiss();
                    }
                });


                dialog.show();
*/
            }
        });



        holder.default_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                add_id =products.getAdd_id();

                try{
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("Id",add_id);
                    jsonObject.put("UserId",sessionManager.getRegId("userId"));

                    Crop_Post.crop_posting(activity, Urls.Default_Address, jsonObject, new VoleyJsonObjectCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject result) {
                            System.out.println("uuuuuaaaaaa" + result);

                            try{

                                status1 = result.getString("Status");
                                message1 = result.getString("Message");

                                if(status1.equals("1")){
                                    int duration = 1000;
                                    Snackbar snackbar = Snackbar
                                            .make(You_Address_Fragment.linearLayout, default_addrs_updtd, duration);
                                    View snackbarView1 = snackbar.getView();
                                    TextView tv = (TextView) snackbarView1.findViewById(android.support.design.R.id.snackbar_text);
                                    tv.setBackgroundColor(ContextCompat.getColor(activity,R.color.orange));
                                    tv.setTextColor(Color.WHITE);


                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    } else {
                                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                                    }


                                    snackbar.show();

                                    selectedFragment = You_Address_Fragment.newInstance();
                                    FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_layout, selectedFragment);
                                    transaction.commit();
                                }

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });


                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });


        try {

            lngObject = new JSONObject(sessionManager.getRegId("language"));
            holder.edit_1.setText(lngObject.getString("Edit"));
            holder.delete_1.setText(lngObject.getString("Delete"));
          //  holder.default_1.setText(lngObject.getString("setasdefault"));
          //  holder.default_add .setText(lngObject.getString("defaultaddressfordelivery"));
            default_addrs_updtd =lngObject.getString("DefaultaddressupdatedSuccessfully");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}

