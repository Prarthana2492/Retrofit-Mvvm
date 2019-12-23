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
import android.widget.Toast;


import com.FarmPe.Farmer.Bean.BankBean;
import com.FarmPe.Farmer.Fragment.AddBrandFragment;
import com.FarmPe.Farmer.Fragment.Add_New_Bank_Account_Details_Fragment;
import com.FarmPe.Farmer.Fragment.Get_Bank_List_Fragment;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Volly_class.Crop_Post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONObject;

import java.util.List;

public class BankAccount_Adapter extends RecyclerView.Adapter<BankAccount_Adapter.MyViewHolder> {

    private List<BankBean> productList;
    Activity activity;
    Fragment selectedFragment;
    SessionManager sessionManager;
    public static String bank_id;
    LinearLayout linear_layout;



    public BankAccount_Adapter(Activity activity, List<BankBean> moviesList) {
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
        final BankBean products = productList.get(position);

        bank_id = products.getBank_details_id();

        holder.bankname.setText(products.getBank_name());
        holder.name.setText("abc");
        holder.acc_no.setText(products.getAcc_number());
        holder.area.setText(products.getState_name()+ ", "+products.getDistrict_name());


        holder.delete_bank_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bank_id = products.getBank_details_id();


                try{

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("UserId",sessionManager.getRegId("userId"));
                    jsonObject.put("BankDetailsId",bank_id);


                    Crop_Post.crop_posting(activity, Urls.Delete_Bank_Details, jsonObject, new VoleyJsonObjectCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject result) {
                            System.out.println("sdjkfjf" + result);
                            try{

                                String status = result.getString("Status");
                                String message = result.getString("Message");

                                if(status.equals("1")){

                                   // Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();

                                    int duration = 1000;
                                    Snackbar snackbar = Snackbar
                                            .make(Get_Bank_List_Fragment.linearLayout, message, duration);
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

        holder.edit_bank_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bank_id = products.getBank_details_id();

                Bundle bundle = new Bundle();
                bundle.putString("bank_status","edit_address");
                bundle.putString("bank_full_name","abc");
                bundle.putString("bank_name",products.getBank_name());
                bundle.putString("bank_branch_name",products.getBranch_name());
                bundle.putString("bank_account_number",products.getAcc_number());
                bundle.putString("bank_state",products.getState_name());
                bundle.putString("bank_stateId",products.getState_id());
                bundle.putString("bank_district",products.getDistrict_name());
                bundle.putString("bank_districtId",products.getDistrict_id());

                selectedFragment = Add_New_Bank_Account_Details_Fragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("bank_list_page");
                transaction.commit();
            }
        });

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }










}
