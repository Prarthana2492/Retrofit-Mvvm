package com.FarmPe.Farmer.Adapter;

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

import com.FarmPe.Farmer.Bean.Profile_Address_Bean;
import com.FarmPe.Farmer.Fragment.Profile_Add_New_Address_Fragment;
import com.FarmPe.Farmer.Fragment.Profile_Get_Address_Fragment;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Volly_class.Crop_Post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONObject;

import java.util.List;


public class Address_Adapter extends RecyclerView.Adapter<Address_Adapter.MyViewHolder> {

    private List<Profile_Address_Bean> productList;
    Activity activity;
    Fragment selectedFragment;
  public static String address_id;
    LinearLayout linear_layout;
    SessionManager sessionManager;



    public Address_Adapter(Activity activity, List<Profile_Address_Bean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout item;
        public TextView bankname,name,area,delete_addrss,edit_address,mobile_no,block;




        public MyViewHolder(View view) {
            super(view);

            bankname=view.findViewById(R.id.bankname);
            name=view.findViewById(R.id.name);
            area=view.findViewById(R.id.area);
            delete_addrss=view.findViewById(R.id.delete_addrss);
            edit_address=view.findViewById(R.id.edit_address);
            linear_layout=view.findViewById(R.id.linear_layout);
            mobile_no=view.findViewById(R.id.mobile_no);
            block=view.findViewById(R.id.block);
            sessionManager = new SessionManager(activity);


        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())


                .inflate(R.layout.address_adapter, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Profile_Address_Bean products = productList.get(position);

        address_id = products.getProf_addrss_id();



       // holder.bankname.setText(products.getBankname());
        holder.name.setText(products.getProf_add_name()+ " , " + products.getProf_address());
      //  holder.phone_no.setText(products.getPhonenumber()+" | "+products.getIfsccode());
        holder.area.setText(products.getProf_landmark()+" , "+ products.getProf_block_name());
        holder.block.setText(products.getProf_state_name() + " - " +products.getProf_pincode());
        holder.mobile_no.setText("Phone No" + " - " + products.getProf_add_mobile());



        holder.edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                address_id = products.getProf_addrss_id();


                Bundle bundle = new Bundle();
                bundle.putString("prof_add_status","edit_add_addressss");
                bundle.putString("addr_name",products.getProf_add_name());
                bundle.putString("addr_mobile_number",products.getProf_add_mobile());
                bundle.putString("addr_address",products.getProf_address());
                bundle.putString("addr_pincode",products.getProf_pincode());
                bundle.putString("addr_landmark",products.getProf_landmark());
                bundle.putString("addr_state",products.getProf_state_name());
                bundle.putString("addr_district",products.getProf_district_name());
                bundle.putString("addr_block",products.getProf_block_name());
                bundle.putString("addr_village",products.getProf_village());

                selectedFragment = Profile_Add_New_Address_Fragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("address_list_page");
                transaction.commit();


            }
        });


      holder.delete_addrss.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {


              address_id = products.getProf_addrss_id();

              try{

                  JSONObject jsonObject = new JSONObject();
                  jsonObject.put("UserId",sessionManager.getRegId("userId"));
                  jsonObject.put("AddressDId",address_id);

                  Crop_Post.crop_posting(activity, Urls.Profile_Delete_Adress_Details, jsonObject, new VoleyJsonObjectCallback() {
                      @Override
                      public void onSuccessResponse(JSONObject result) {
                          System.out.println("ttdfhhdfc" + result);

                          try{

                              String status = result.getString("Status");
                              String message = result.getString("Message");


                              if(status.equals("1")){


                                  int duration = 1000;
                                  Snackbar snackbar = Snackbar
                                          .make(Profile_Get_Address_Fragment.linearLayout, message, duration);
                                  View snackbarView = snackbar.getView();
                                  TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                  tv.setBackgroundColor(ContextCompat.getColor(activity, R.color.orange));
                                  tv.setTextColor(Color.WHITE);


                                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                      tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


                                  } else {

                                      tv.setGravity(Gravity.CENTER_HORIZONTAL);

                                  }

                                  snackbar.show();


                                //  Toast.makeText(activity, "address deleted successfully", Toast.LENGTH_SHORT).show();

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

          }
      });


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

}
