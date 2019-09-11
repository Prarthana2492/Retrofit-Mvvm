package com.FarmPe.Farmer.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.FarmPe.Farmer.Adapter.AddFirstAdapter;

import com.FarmPe.Farmer.Adapter.AddModelAdapter;
import com.FarmPe.Farmer.Adapter.TalukAdapter;
import com.FarmPe.Farmer.Adapter.You_Address_Dialog_Adapter;
import com.FarmPe.Farmer.Bean.Add_New_Address_Bean;
import com.FarmPe.Farmer.Bean.FarmsImageBean;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Volly_class.Crop_Post;
import com.FarmPe.Farmer.Volly_class.Login_post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Request_Details_New extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    ArrayList<Add_New_Address_Bean> new_address_beanArrayList = new ArrayList<>();

    public static RecyclerView recyclerView;

    TextView toolbar_title,request;
    public static   EditText address_text;
    Fragment selectedFragment;
    RadioGroup radioGroup,radioGroup_finance;
    RadioButton radioButton,finance_yes,finance_no,radioButton1;
    LinearLayout back_feed,address_layout,no_address_text;
    SessionManager sessionManager;
    You_Address_Dialog_Adapter you_address_dialog_adapter;
    View view;
   public static String addId;
    String time_period;
    public static Dialog dialog;
    boolean finance;
    EditText purchase_edit;
    String finance_status;
    public static String back;
    LinearLayout linearLayout;
    public static int selectedId,selectedId_time_recent;
    int finance_selected,time_selected;
    Add_New_Address_Bean add_new_address_bean;
    TextView whenPurchase, lookingForFinance,add_addrss;
    JSONArray get_address_array;
    String pickUPFrom;
    ImageView b_arrow;

    String purchasetime="";
    String lookingforfinance="no";
    SwitchCompat lookingForFinance_switch;




   public static Request_Details_New newInstance() {
       Request_Details_New fragment = new Request_Details_New();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.request_form_layout, container, false);
//        final View view = getView() != null ? getView() :
//                inflater.inflate(R.layout.request_form_layout, container, false);

        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);
        b_arrow=view.findViewById(R.id.b_arrow);
        whenPurchase = view.findViewById(R.id.whenPurchase);
        lookingForFinance_switch = view.findViewById(R.id.lookingForFinance_switch);
        //whenPurchase.setText("When are you planning to purchase "+ AddFirstFragment.tracter_title+"?");
        lookingForFinance = view.findViewById(R.id.lookingForFinance);
       // lookingForFinance.setText("Are you looking for finance / loan for "+AddFirstFragment.tracter_title+" purchase?");
        address_layout=view.findViewById(R.id.address_layout);
        radioGroup=view.findViewById(R.id.radio_group_time);
        radioGroup_finance=view.findViewById(R.id.radioGroup_finance);
        request=view.findViewById(R.id.add_address);
        address_text=view.findViewById(R.id.address_text);
        recyclerView = view.findViewById(R.id.recycler_view);
        purchase_edit=view.findViewById(R.id.purchase_edit);
        add_addrss=view.findViewById(R.id.add_addrss);
        //purchase_edit=view.findViewById(R.id.add_addrss);
        linearLayout=view.findViewById(R.id.linear_layout);
        no_address_text=view.findViewById(R.id.no_address_text);
       // toolbar_title.setText("Request for Quotation");
        sessionManager=new SessionManager(getActivity());
        Bundle bundle=getArguments();


        if (bundle==null){
           gettingAddress();


        }else{
           // finance_selected=bundle.getInt("selected_id2");
           // time_selected=bundle.getInt("selected_id_time1");
            addId=bundle.getString("add_id");
            String stret_name=bundle.getString("streetname");
            address_text.setText(stret_name);
            String currentaddress_str=bundle.getString("currentaddress_id");
            address_text.setText(currentaddress_str);


            }



        lookingForFinance_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    lookingforfinance="yes";
                }else {
                    lookingforfinance="no";

                }


            }
        });


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   b_arrow.setImageDrawable(getResources().getDrawable(R.drawable.ic_whitecancel));

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("fourth", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {


                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("fourth", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }
                return false;
            }
        });



        add_addrss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("navigation_from","REQ_NEW");

                selectedFragment = Add_New_Address_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("req_newdetail");
                transaction.commit();

            }
        });


        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (addId==null){


                    Snackbar snackbar = Snackbar
                            .make(linearLayout,"Enter Address", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                    tv.setTextColor(Color.WHITE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }

                    snackbar.show();

                }else if (purchasetime.equals("")){
                    Snackbar snackbar = Snackbar
                            .make(linearLayout,"Select Purchase Planning", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                    tv.setTextColor(Color.WHITE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }

                    snackbar.show();
                }

                else {
                    RequestForm();

                }

            }
        });


        purchase_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.request_form_purchase_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                LinearLayout image = (LinearLayout) dialog.findViewById(R.id.close_popup);
                final TextView immediate =(TextView)dialog.findViewById(R.id.immediate);
                final TextView one_month = (TextView)dialog.findViewById(R.id.one_month) ;
                final TextView two_months = (TextView)dialog.findViewById(R.id.two_months) ;
                final TextView after_3months = (TextView)dialog.findViewById(R.id.after_3months) ;
              //  final TextView popuptxt = (TextView)dialog.findViewById(R.id.popup_heading) ;



                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                immediate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        purchasetime=immediate.getText().toString();
                        purchase_edit.setText(immediate.getText().toString());
                        dialog.dismiss();
                    }
                });


                one_month.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        purchase_edit.setText(one_month.getText().toString());
                        purchasetime=one_month.getText().toString();

                        dialog.dismiss();

                    }
                });

                two_months.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        purchase_edit.setText(two_months.getText().toString());
                        purchasetime=two_months.getText().toString();

                        dialog.dismiss();

                    }
                });



                after_3months.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        purchase_edit.setText(after_3months.getText().toString());
                        purchasetime=after_3months.getText().toString();

                        dialog.dismiss();

                    }
                });


                dialog.show();

            }
        });


        address_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                     dialog = new Dialog(getActivity());
                     dialog.setContentView(R.layout.default_address_pop_up_layout);
                     dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                     LinearLayout image = (LinearLayout) dialog.findViewById(R.id.close_popup);
                     recyclerView = dialog.findViewById(R.id.recycler_view);
                     no_address_text=dialog.findViewById(R.id.no_address_text);
                     final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                     layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                     recyclerView.setLayoutManager(layoutManager);
                     recyclerView.setItemAnimator(new DefaultItemAnimator());
                     you_address_dialog_adapter = new You_Address_Dialog_Adapter(new_address_beanArrayList, getActivity());
                     recyclerView.setAdapter(you_address_dialog_adapter);
                     address_list();

                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });


        return view;
    }


    private void address_list() {

        try{
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));
            jsonObject.put("PickUpFrom",pickUPFrom);
            System.out.println("aaaaaaaaaaaaadddd" + sessionManager.getRegId("userId"));

            Crop_Post.crop_posting(getActivity(), Urls.Get_New_Address, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("ggggggggggaaaaaaa"+result);
                    try{
                        new_address_beanArrayList.clear();

                        get_address_array = result.getJSONArray("UserAddressDetails");
                        for(int i=0;i<get_address_array.length();i++){
                            JSONObject jsonObject1 = get_address_array.getJSONObject(i);


                            add_new_address_bean = new Add_New_Address_Bean(jsonObject1.getString("Name"),jsonObject1.getString("StreeAddress"),jsonObject1.getString("StreeAddress1"),jsonObject1.getString("LandMark"),jsonObject1.getString("City"),jsonObject1.getString("Pincode"),jsonObject1.getString("MobileNo"),
                                    jsonObject1.getString("PickUpFrom"),jsonObject1.getString("State"),jsonObject1.getString("District"),jsonObject1.getString("Taluk"),jsonObject1.getString("Hoblie"),jsonObject1.getString("Village"),jsonObject1.getString("Id"),jsonObject1.getBoolean("IsDefaultAddress"));
                            new_address_beanArrayList.add(add_new_address_bean);

                        }


                         if(new_address_beanArrayList.size()== 0){
                             no_address_text.setVisibility(View.VISIBLE);
                             recyclerView.setVisibility(View.GONE);
                         }else{

                             no_address_text.setVisibility(View.GONE);
                             recyclerView.setVisibility(View.VISIBLE);
                         }


                        you_address_dialog_adapter.notifyDataSetChanged();



                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }



    }

    private void RequestForm() {




        System.out.println("purchase"+time_period);
        System.out.println("finance"+time_period);

        try {

            JSONObject userRequestjsonObject = new JSONObject();

            userRequestjsonObject.put("UserId",sessionManager.getRegId("userId"));
            userRequestjsonObject.put("PurchaseTimeline", purchase_edit);
            userRequestjsonObject.put("LookingForFinance", "yes");
            userRequestjsonObject.put("AddressId", addId);
           // userRequestjsonObject.put("AddressId", addId);
            userRequestjsonObject.put("ModelId", AddModelAdapter.tractor_id);
            userRequestjsonObject.put("IsAgreed", "True");
            userRequestjsonObject.put("LookingForDetailsId", AddFirstAdapter.looinkgId);


            System.out.println("postObjmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"+userRequestjsonObject.toString());


            Login_post.login_posting(getActivity(), Urls.AddRequestForQuotation,userRequestjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);

                    newOrderBeansList.clear();

                    try {
                        String status=result.getString("Status");
                        String message=result.getString("Message");


                        Snackbar snackbar = Snackbar
                                .make(linearLayout,"Your Request Added Successfully", Snackbar.LENGTH_LONG);
                        View snackbarView = snackbar.getView();
                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                        tv.setTextColor(Color.WHITE);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        } else {
                            tv.setGravity(Gravity.CENTER_HORIZONTAL);
                        }

                        snackbar.show();

                        selectedFragment = HomeMenuFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void gettingAddress(){

        try{
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));
            jsonObject.put("PickUpFrom",pickUPFrom);
            System.out.println("aaaaaaaaaaaaadddd" + sessionManager.getRegId("userId"));

            Crop_Post.crop_posting(getActivity(), Urls.Get_New_Address, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("ggggggggggaaaaaaa"+result);
                    try{
                        new_address_beanArrayList.clear();


                        get_address_array = result.getJSONArray("UserAddressDetails");
                        for(int i=0;i<get_address_array.length();i++){
                            JSONObject jsonObject1 = get_address_array.getJSONObject(i);


                            // add_new_address_bean = new Add_New_Address_Bean(jsonObject1.getString("Name"),jsonObject1.getString("StreeAddress"),jsonObject1.getString("StreeAddress1"),jsonObject1.getString("LandMark"),jsonObject1.getString("City"),jsonObject1.getString("Pincode"),jsonObject1.getString("MobileNo"),
                            //jsonObject1.getString("PickUpFrom"),jsonObject1.getString("State"),jsonObject1.getString("District"),jsonObject1.getString("Taluk"),jsonObject1.getString("Hoblie"),jsonObject1.getString("Village"),jsonObject1.getString("Id"),jsonObject1.getBoolean("IsDefaultAddress"));
                            // new_address_beanArrayList.add(add_new_address_bean);

                            if (jsonObject1.getBoolean("IsDefaultAddress")){
                                addId=jsonObject1.getString("Id");
                                address_text.setText(jsonObject1.getString("Hoblie")+"  "+jsonObject1.getString("District"));

                            }

                        }

                        // item_list = String.valueOf(new_address_beanArrayList.size());
                        //  address_list.setText(item_list+" " + ad_list );



                        // mAdapter.notifyDataSetChanged();




                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });


        }catch (Exception e){
            e.printStackTrace();
    }



}


}
