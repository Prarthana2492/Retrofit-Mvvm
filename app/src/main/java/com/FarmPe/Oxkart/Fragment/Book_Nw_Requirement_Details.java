package com.FarmPe.Oxkart.Fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Oxkart.Activity.Status_bar_change_singleton;
import com.FarmPe.Oxkart.Bean.Add_New_Address_Bean;
import com.FarmPe.Oxkart.Bean.FarmsImageBean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;


import java.util.ArrayList;
import java.util.List;

public class Book_Nw_Requirement_Details extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    ArrayList<Add_New_Address_Bean> new_address_beanArrayList = new ArrayList<>();
    public static RecyclerView recyclerView;

    TextView immediate_btn,one_month_btn,two_month_btn,three_month_btn,aftr_three_month_btn,demo_yes,demo_no,insuranc_yes,insuranc_no,fin_yes,fin_no;
    LinearLayout request_price_layout,linearLayout,back_feed,purchase_linear;
    Fragment selectedFragment = null;
    SessionManager sessionManager;
    String purchase_plan,looking_finance,looking_demo,looking_insurance;



    public static Book_Nw_Requirement_Details newInstance() {
        Book_Nw_Requirement_Details fragment = new Book_Nw_Requirement_Details();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_now_details_layout, container, false);
        Status_bar_change_singleton.getInstance().color_change(getActivity());

        immediate_btn=view.findViewById(R.id.immediate_btn);
        one_month_btn=view.findViewById(R.id.one_month_btn);
        two_month_btn=view.findViewById(R.id.two_month_btn);
        three_month_btn=view.findViewById(R.id.three_month_btn);
        aftr_three_month_btn=view.findViewById(R.id.aftr_three_month_btn);
        request_price_layout=view.findViewById(R.id.request_price_layout);
        back_feed=view.findViewById(R.id.back_feed);


        demo_yes=view.findViewById(R.id.demo_yes);
        demo_no=view.findViewById(R.id.demo_no);
        insuranc_yes=view.findViewById(R.id.insuranc_yes);
        insuranc_no=view.findViewById(R.id.insuranc_no);
        fin_yes=view.findViewById(R.id.fin_yes);
        fin_no=view.findViewById(R.id.fin_no);
        linearLayout=view.findViewById(R.id.linearLayout);
        purchase_linear=view.findViewById(R.id.purchase_linear);
        sessionManager = new SessionManager(getActivity());




        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("dealer_page", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    return true;
                }
                return false;
            }
        });


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("dealer_page", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });


        immediate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                purchase_plan = immediate_btn.getText().toString();
                immediate_btn.setTextColor(Color.parseColor("#FFFFFF"));
                one_month_btn.setTextColor(Color.parseColor("#000000"));
                two_month_btn.setTextColor(Color.parseColor("#000000"));
                three_month_btn.setTextColor(Color.parseColor("#000000"));
                aftr_three_month_btn.setTextColor(Color.parseColor("#000000"));
                immediate_btn.setBackgroundResource(R.drawable.black_border_blue_filled);
                one_month_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);
                two_month_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);
                three_month_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);
                aftr_three_month_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);

            }
        });


        one_month_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                purchase_plan = one_month_btn.getText().toString();
                one_month_btn.setTextColor(Color.parseColor("#FFFFFF"));
                immediate_btn.setTextColor(Color.parseColor("#000000"));
                two_month_btn.setTextColor(Color.parseColor("#000000"));
                three_month_btn.setTextColor(Color.parseColor("#000000"));
                aftr_three_month_btn.setTextColor(Color.parseColor("#000000"));
                one_month_btn.setBackgroundResource(R.drawable.black_border_blue_filled);
                immediate_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);
                two_month_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);
                three_month_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);
                aftr_three_month_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);
            }

        });


        two_month_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                purchase_plan = two_month_btn.getText().toString();
                two_month_btn.setTextColor(Color.parseColor("#FFFFFF"));
                one_month_btn.setTextColor(Color.parseColor("#000000"));
                immediate_btn.setTextColor(Color.parseColor("#000000"));
                three_month_btn.setTextColor(Color.parseColor("#000000"));
                aftr_three_month_btn.setTextColor(Color.parseColor("#000000"));
                two_month_btn.setBackgroundResource(R.drawable.black_border_blue_filled);
                immediate_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);
                one_month_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);
                three_month_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);
                aftr_three_month_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);

            }
        });

        three_month_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                purchase_plan = three_month_btn.getText().toString();
                three_month_btn.setTextColor(Color.parseColor("#FFFFFF"));
                two_month_btn.setTextColor(Color.parseColor("#000000"));
                one_month_btn.setTextColor(Color.parseColor("#000000"));
                immediate_btn.setTextColor(Color.parseColor("#000000"));
                aftr_three_month_btn.setTextColor(Color.parseColor("#000000"));
                three_month_btn.setBackgroundResource(R.drawable.black_border_blue_filled);
                immediate_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);
                one_month_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);
                two_month_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);
                aftr_three_month_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);

            }
        });



        aftr_three_month_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                purchase_plan = aftr_three_month_btn.getText().toString();
                aftr_three_month_btn.setTextColor(Color.parseColor("#FFFFFF"));
                three_month_btn.setTextColor(Color.parseColor("#000000"));
                two_month_btn.setTextColor(Color.parseColor("#000000"));
                one_month_btn.setTextColor(Color.parseColor("#000000"));
                immediate_btn.setTextColor(Color.parseColor("#000000"));

                aftr_three_month_btn.setBackgroundResource(R.drawable.black_border_blue_filled);
                three_month_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);
                immediate_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);
                one_month_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);
                two_month_btn.setBackgroundResource(R.drawable.black_bordr_white_filled);

            }
        });


        fin_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                looking_finance = fin_yes.getText().toString();
                fin_yes.setTextColor(Color.parseColor("#FFFFFF"));
                fin_no.setTextColor(Color.parseColor("#000000"));
                fin_yes.setBackgroundResource(R.drawable.black_border_blue_filled);
                fin_no.setBackgroundResource(R.drawable.black_bordr_white_filled);



            }
        });

        fin_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                looking_finance = fin_no.getText().toString();
                fin_no.setTextColor(Color.parseColor("#FFFFFF"));
                fin_yes.setTextColor(Color.parseColor("#000000"));
                fin_no.setBackgroundResource(R.drawable.black_border_blue_filled);
                fin_yes.setBackgroundResource(R.drawable.black_bordr_white_filled);


            }
        });


        insuranc_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                looking_insurance = insuranc_yes.getText().toString();
                insuranc_yes.setTextColor(Color.parseColor("#FFFFFF"));
                insuranc_no.setTextColor(Color.parseColor("#000000"));
                insuranc_yes.setBackgroundResource(R.drawable.black_border_blue_filled);
                insuranc_no.setBackgroundResource(R.drawable.black_bordr_white_filled);

            }
        });



        insuranc_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                looking_insurance = insuranc_no.getText().toString();
                insuranc_no.setTextColor(Color.parseColor("#FFFFFF"));
                insuranc_yes.setTextColor(Color.parseColor("#000000"));
                insuranc_no.setBackgroundResource(R.drawable.black_border_blue_filled);
                insuranc_yes.setBackgroundResource(R.drawable.black_bordr_white_filled);

            }
        });


        demo_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                looking_demo = demo_yes.getText().toString();
                demo_yes.setTextColor(Color.parseColor("#FFFFFF"));
                demo_no.setTextColor(Color.parseColor("#000000"));
                demo_yes.setBackgroundResource(R.drawable.black_border_blue_filled);
                demo_no.setBackgroundResource(R.drawable.black_bordr_white_filled);


            }
        });



        demo_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                looking_demo = demo_no.getText().toString();
                demo_no.setTextColor(Color.parseColor("#FFFFFF"));
                demo_yes.setTextColor(Color.parseColor("#000000"));
                demo_no.setBackgroundResource(R.drawable.black_border_blue_filled);
                demo_yes.setBackgroundResource(R.drawable.black_bordr_white_filled);

            }
        });

        request_price_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(purchase_plan==null){

                  //  Toast.makeText(getActivity(), "Please Select purchase plan", Toast.LENGTH_SHORT).show();


                    Snackbar snackbar = Snackbar
                            .make(linearLayout,"Please Select purchase plan", Snackbar.LENGTH_LONG);
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

                  //  Toast.makeText(getActivity(), "fgdgdgdgfdg", Toast.LENGTH_SHORT).show();


                }else if(looking_finance == null){


                    Snackbar snackbar = Snackbar
                            .make(linearLayout,"Please Select finance", Snackbar.LENGTH_LONG);
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


                   // Toast.makeText(getActivity(), "Please Select finance", Toast.LENGTH_SHORT).show();


                }else if(looking_insurance == null){


                    Snackbar snackbar = Snackbar
                            .make(linearLayout,"Please select insurance", Snackbar.LENGTH_LONG);
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



                 //   Toast.makeText(getActivity(), "Please select insurance", Toast.LENGTH_SHORT).show();


                }else if(looking_demo == null){


                    Snackbar snackbar = Snackbar
                            .make(linearLayout,"Please select demo/test drive", Snackbar.LENGTH_LONG);
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




                  //  Toast.makeText(getActivity(), "Please select demo/test drive", Toast.LENGTH_SHORT).show();


                }else{

                        selectedFragment = AddMoneyFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_menu, selectedFragment);
                        transaction.addToBackStack("book_payment");
                        transaction.commit();
                }

            }
        });


        return view;
    }


//    private void request_price() {
//
//
//        System.out.println("postObjmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmccfc"+purchase_plan);
//
//
//        try {
//
//            JSONObject userRequestjsonObject = new JSONObject();
//            userRequestjsonObject.put("UserId",sessionManager.getRegId("userId"));
//            userRequestjsonObject.put("PurchaseTimeline",purchase_plan);
//            userRequestjsonObject.put("LookingForFinance",looking_finance);
//            userRequestjsonObject.put("AddressId",getArguments().getString("add_id"));
//            userRequestjsonObject.put("IsAgreed", "True");
//            userRequestjsonObject.put("ModelId", AddModelAdapter.model_id);
//            userRequestjsonObject.put("LookingForDetailsId", AddBrandFragment.request_looking_id);
//
//
//
//            System.out.println("postObjmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"+userRequestjsonObject.toString());
//
//
//
//            Login_post.login_posting(getActivity(), Urls.AddRequestForQuotation,userRequestjsonObject,new VoleyJsonObjectCallback() {
//                @Override
//                public void onSuccessResponse(JSONObject result) {
//                    System.out.println("cropsresult"+result);
//
//
//                    newOrderBeansList.clear();
//
//
//                    try {
//                        String status=result.getString("Status");
//                        String message=result.getString("Message");
//
//
//                        Snackbar snackbar = Snackbar
//                                .make(linearLayout,"Your Request Added Successfully", Snackbar.LENGTH_LONG);
//                        View snackbarView = snackbar.getView();
//                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                        tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
//                        tv.setTextColor(Color.WHITE);
//
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//
//                            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//
//                        } else {
//                            tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                        }
//
//                        snackbar.show();
//
//                        HomePage_With_Bottom_Navigation.home_icon.setImageResource(R.drawable.ic_home_green);
//                        HomePage_With_Bottom_Navigation.profile_icon.setImageResource(R.drawable.ic_user_home);
//                        HomePage_With_Bottom_Navigation.text_home.setTextColor(Color.parseColor("#18a360"));
//                        HomePage_With_Bottom_Navigation.profile_text.setTextColor(Color.parseColor("#595959"));
//
//
//                        selectedFragment = MenuFragment.newInstance();
//                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.frame_menu, selectedFragment);
//                        transaction.commit();
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//
//    }

}
