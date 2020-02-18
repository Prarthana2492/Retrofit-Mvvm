package com.FarmPe.Oxkart.Fragment;


import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Oxkart.Activity.Status_bar_change_singleton;
import com.FarmPe.Oxkart.Adapter.BankAccount_Adapter;
import com.FarmPe.Oxkart.Adapter.Bank_District_Adapter;
import com.FarmPe.Oxkart.Adapter.Bank_State_Adapter;
import com.FarmPe.Oxkart.Bean.StateBean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Add_New_Bank_Account_Details_Fragment extends Fragment {


 LinearLayout back_feed,linearLayout,continuebtn;
    Fragment selectedFragment;

    public static EditText do_u_have_ifsc,state,district,confirm_saving_ac;
    static List<StateBean> stateBeanList = new ArrayList<>();
    static List<StateBean> districtBeanList = new ArrayList<>();
    private List<StateBean> searchresultAraaylist = new ArrayList<>();
    StateBean stateBean;
    Bank_State_Adapter stateApdater;
    Bank_District_Adapter districtAdapter;
    JSONArray jsonArray,state_array;
    public static EditText state_txt;
    public static DrawerLayout drawer;
    RecyclerView recyclerView;
    String status,bankdetails_id;
    EditText account_number,bank_name,bank_branch_name;
    SessionManager sessionManager;
    EditText search;
    TextView norecords,toolbar_title;
    public static String search_status="status";




    public static Add_New_Bank_Account_Details_Fragment newInstance() {
        Add_New_Bank_Account_Details_Fragment fragment = new Add_New_Bank_Account_Details_Fragment();
        return fragment;
    }




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bank_navigation_drawer_layout, container, false);


        Status_bar_change_singleton.getInstance().color_change(getActivity());
        getActivity().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


         Window window = getActivity().getWindow();
         window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.dark_green));

          back_feed=view.findViewById(R.id.back_feed);
          do_u_have_ifsc=view.findViewById(R.id.ifsc_code);
          state=view.findViewById(R.id.relationship);
          district=view.findViewById(R.id.relativename);
          drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout_op);
          search = view.findViewById(R.id.search);
          recyclerView = view.findViewById(R.id.recycler_view);
          state_txt = view.findViewById(R.id.state_txt);
          toolbar_title = view.findViewById(R.id.toolbar_title);

          bank_branch_name=view.findViewById(R.id.bank_branch_name);
          bank_name=view.findViewById(R.id.bank_name);
          account_number=view.findViewById(R.id.account_number);
          norecords = view.findViewById(R.id.norecords);

          confirm_saving_ac=view.findViewById(R.id.confirm_acc_no);
          continuebtn=view.findViewById(R.id.continuebtn);
          linearLayout=view.findViewById(R.id.main_layout);
          sessionManager = new SessionManager(getActivity());



         bank_name.setText(getArguments().getString("bank_name"));

         bank_branch_name.setText(getArguments().getString("bank_branch_name"));

         account_number.setText(getArguments().getString("bank_account_number"));

         state.setText(getArguments().getString("bank_state"));

         district.setText(getArguments().getString("bank_district"));

         bankdetails_id = getArguments().getString("bank_details_id");


         if(getArguments().getString("bank_status").equals("edit_address")){

             toolbar_title.setText("Edit Bank Details");
         }




        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
//Find the currently focused view, so we can grab the correct window token from it.
                v = getActivity().getCurrentFocus();
//If no view currently has focus, create a new one, just so we can grab a window token from it
                if (v == null) {
                    v = new View(getActivity());
                }
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


                if(getArguments().getString("bank_status").equals("Profile_Add_Bank_Details")) {


                    selectedFragment = New_Profile_Setting_Fragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_menu, selectedFragment);
                    transaction.commit();



                }  else if(getArguments().getString("bank_status").equals("edit_address")){


                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("bank_list_page", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    }else  if(getArguments().getString("bank_status").equals("Get_Add_Bank_Details")) {

                    selectedFragment = Get_Bank_List_Fragment .newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_menu, selectedFragment);
                    transaction.commit();
                }

            }
        });

           view.setFocusableInTouchMode(true);
           view.requestFocus();
           view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
//Find the currently focused view, so we can grab the correct window token from it.
                    v = getActivity().getCurrentFocus();
//If no view currently has focus, create a new one, just so we can grab a window token from it
                    if (v == null) {
                        v = new View(getActivity());
                    }
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


                    if(getArguments().getString("bank_status").equals("Profile_Add_Bank_Details")){


                        selectedFragment = New_Profile_Setting_Fragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_menu, selectedFragment);
                        transaction.commit();


                    }  else if(getArguments().getString("bank_status").equals("edit_address")){


                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("bank_list_page", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    } else  if(getArguments().getString("bank_status").equals("Get_Add_Bank_Details")) {
//                     System.out.println("asdghsafjbs" + getArguments().getString("bank_status").equals("Get_Add_Bank_Details") );
//
                        selectedFragment = Get_Bank_List_Fragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_menu, selectedFragment);
                        transaction.commit();

                    }

                    return true;
                }

                return false;
            }
        });




        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sorting(s.toString());
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });



        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

                drawer.openDrawer(GravityCompat.END);
                search_status="state";
                search.setText("");

                stateBeanList.clear();
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                stateApdater = new Bank_State_Adapter(stateBeanList,getActivity());

                recyclerView.setAdapter(stateApdater);

                prepareStateData();



            }
        });


        district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!state.getText().toString().equals("")) {


                    drawer.openDrawer(GravityCompat.END);

                    search_status="district";
                    search.setText("");

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    districtAdapter= new Bank_District_Adapter( districtBeanList,getActivity());
                    recyclerView.setAdapter(districtAdapter);

                    prepareDistricData();


                }else{

                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Please Select State", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snackbar.show();

                }

            }
        });



        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String acc_no = account_number.getText().toString();
                String confirm_acc_no = confirm_saving_ac.getText().toString();

                if(bank_name.getText().toString().equals("")){

                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter Bank Name", duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                    tv.setTextColor(Color.WHITE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);


                    }
                    snackbar.show();


                } else if(bank_branch_name.getText().toString().equals("")){

                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter Bank Branch Name", duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                    tv.setTextColor(Color.WHITE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);


                    }
                    snackbar.show();



                } else if(acc_no.equals("")){

                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter Account Number", duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                    tv.setTextColor(Color.WHITE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }

                    snackbar.show();



                } else if(confirm_acc_no.equals("")) {


                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Confirm Account Number", duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                    tv.setTextColor(Color.WHITE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);


                    }
                    snackbar.show();



                } else if(!(acc_no.equals(confirm_acc_no))) {

                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Account number is not matching", duration);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                    tv.setTextColor(Color.WHITE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);


                    }
                    snackbar.show();


                }else{

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
//Find the currently focused view, so we can grab the correct window token from it.
                    v = getActivity().getCurrentFocus();
//If no view currently has focus, create a new one, just so we can grab a window token from it
                    if (v == null) {
                        v = new View(getActivity());
                    }
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


                    Add_Bank_Account();

                }

//                selectedFragment = Addnewbankdetailswithifsc.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_menu, selectedFragment);
//                transaction.addToBackStack("add_new");
//                transaction.commit();
            }
        });



        do_u_have_ifsc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(do_u_have_ifsc,state,district,bank_name,bank_branch_name,account_number,confirm_saving_ac);
                return false;

            }
        });


        state.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(state,do_u_have_ifsc,district,bank_name,bank_branch_name,account_number,confirm_saving_ac);
                return false;
            }
        });



        district.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(district,state,do_u_have_ifsc,bank_name,bank_branch_name,account_number,confirm_saving_ac);
                return false;
            }
        });



        bank_name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(bank_name,do_u_have_ifsc,state,district,bank_branch_name,account_number,confirm_saving_ac);
                return false;
            }
        });


        bank_branch_name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(bank_branch_name,state,district,bank_name,account_number,confirm_saving_ac,do_u_have_ifsc);
                return false;
            }
        });


        account_number.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(account_number,state,district,bank_name,bank_branch_name,confirm_saving_ac,do_u_have_ifsc);
                return false;
            }
        });


        confirm_saving_ac.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(confirm_saving_ac,state,district,bank_name,bank_branch_name,account_number,do_u_have_ifsc);
                return false;
            }
        });




        do_u_have_ifsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                PopupMenu popup = new PopupMenu(getActivity(), do_u_have_ifsc);
                popup.getMenu().add("Yes");
                popup.getMenu().add("No");



                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        System.out.println("teeexxtt"+item.getTitle());


                        do_u_have_ifsc.setText(item.getTitle());


                        if (item.getTitle().equals("Yes")) {

                             Bundle bundle = new Bundle();
                             bundle.putString("status_ifsc",do_u_have_ifsc.getText().toString());
                             selectedFragment = Add_Bank_Details_IFSC_Fragment.newInstance();
                             FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                             transaction.replace(R.id.frame_menu, selectedFragment);
                             transaction.addToBackStack("add_bank");
                             selectedFragment.setArguments(bundle);
                             transaction.commit();


                        }else if(item.getTitle().equals("No")){

                            do_u_have_ifsc.setText(item.getTitle());

                        }

                     //    do_u_have_ifsc.setText(item.getTitle());

                        return true;
                    }
                });


                popup.show(); //showing

            }
        });



        return view;
    }




    private void prepareStateData() {

        recyclerView.setVisibility(View.VISIBLE);

        try{

            JSONObject jsonObject = new JSONObject();

            Crop_Post.crop_posting(getActivity(), Urls.State, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("11111ssss" + result);


                    try{
                        stateBeanList.clear();
                        state_array = result.getJSONArray("StateList");

                        if (state_array != null &&state_array.length() > 0) {
                            for (int i = 0; i < state_array.length(); i++) {
                                JSONObject jsonObject1 = state_array.getJSONObject(i);

                                stateBean = new StateBean(jsonObject1.getString("State").trim().replace("&amp;", "&"), jsonObject1.getString("StateId"));
                                stateBeanList.add(stateBean);
                            }

                            sorting(stateBeanList);

                            stateApdater.notifyDataSetChanged();
                            //  grade_dialog.show();

                        }else {
                            recyclerView.setVisibility(View.GONE);
                            norecords.setVisibility(View.VISIBLE);
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


    private void prepareDistricData() {


        recyclerView.setVisibility(View.VISIBLE);


        try {

            JSONObject jsonObject = new JSONObject();
            JSONObject post_jsonobject = new JSONObject();

            jsonObject.put("StateId", Bank_State_Adapter.stateid);
            post_jsonobject.put("Districtobj", jsonObject);


            Crop_Post.crop_posting(getActivity(), Urls.Districts, post_jsonobject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dddddddddddd11111" + result);

                    try {

                        districtBeanList.clear();
                        jsonArray = result.getJSONArray("DistrictList");

                        if (jsonArray != null && jsonArray.length() > 0) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                stateBean = new StateBean(jsonObject1.getString("District"), jsonObject1.getString("DistrictId"));
                                districtBeanList.add(stateBean);
                            }

                            sorting(districtBeanList);


                            districtAdapter.notifyDataSetChanged();
                            // grade_dialog.show();

                        } else {
                            recyclerView.setVisibility(View.GONE);
                            norecords.setVisibility(View.VISIBLE);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public  void sorting(String filter_text){

        final String text = filter_text.toLowerCase();



        if (search_status.equals("state")){
            searchresultAraaylist.clear();
            for (int i = 0; i < stateBeanList.size(); i++) {

                if (stateBeanList.get(i).getName().toLowerCase().contains(text)) {
                    searchresultAraaylist.add(stateBeanList.get(i));

                }
            }

            if (searchresultAraaylist.size()==0){
                recyclerView.setVisibility(View.GONE);
                norecords.setVisibility(View.VISIBLE);
            }


            else {
                recyclerView.setVisibility(View.VISIBLE);
                norecords.setVisibility(View.GONE);
                stateApdater = new Bank_State_Adapter(searchresultAraaylist, getActivity());
                recyclerView.setAdapter(stateApdater);
            }

            //            stateApdater = new StateApdater(searchresultAraaylist,getActivity());
//            recyclerView.setAdapter(stateApdater);
        }


        else if (search_status.equals("district")) {
            searchresultAraaylist.clear();
            for (int i = 0; i < districtBeanList.size(); i++) {

                if (districtBeanList.get(i).getName().toLowerCase().contains(text)) {
                    searchresultAraaylist.add(districtBeanList.get(i));

                }
            }


            if (searchresultAraaylist.size()==0){
                recyclerView.setVisibility(View.GONE);
                norecords.setVisibility(View.VISIBLE);

            }else {

                recyclerView.setVisibility(View.VISIBLE);
                norecords.setVisibility(View.GONE);


                districtAdapter = new Bank_District_Adapter(searchresultAraaylist, getActivity());
                recyclerView.setAdapter(districtAdapter);

            }

//            districtAdapter = new DistrictAdapter(searchresultAraaylist, getActivity());
//            recyclerView.setAdapter(districtAdapter);

        }

    }

    public void sorting(List<StateBean> arrayList){

        Collections.sort(arrayList, new Comparator() {
            @Override
            public int compare(Object state_name1, Object state_name2) {
                //use instanceof to verify the references are indeed of the type in question

                return ((StateBean)state_name1).getName()
                        .compareTo(((StateBean)state_name2).getName());
            }
        });
    }



    private void Add_Bank_Account() {

        try{

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));
            jsonObject.put("IsIFSCCodeExist","1");
            jsonObject.put("SavingsBankAccountNumber",account_number.getText().toString());
            jsonObject.put("StateId",Bank_State_Adapter.stateid);
            jsonObject.put("DistrictId", Bank_District_Adapter.districtid);
            jsonObject.put("BankName",bank_name.getText().toString());
            jsonObject.put("BankBranchName",bank_branch_name.getText().toString());
            System.out.println("sdfsdfsdf" + bankdetails_id);
            System.out.println("lgldfdffdsf"+jsonObject);



            if(getArguments().getString("bank_status").equals("edit_address")){

                jsonObject.put("UserId",sessionManager.getRegId("userId"));
                jsonObject.put("IsIFSCCodeExist","1");
                jsonObject.put("SavingsBankAccountNumber",account_number.getText().toString());
                jsonObject.put("StateId",Bank_State_Adapter.stateid);
                jsonObject.put("DistrictId",Bank_District_Adapter.districtid);
                jsonObject.put("BankName",bank_name.getText().toString());
                jsonObject.put("BankBranchName",bank_branch_name.getText().toString());
                jsonObject.put("BankDetailsId", BankAccount_Adapter.bank_id);

                System.out.println("bbbaddadadadad"+jsonObject);


//                getArguments().getString("bank_name"));
//
//
//


//                jsonObject.put("StateId",getArguments().getString("bank_stateId"));
//                jsonObject.put("DistrictId",getArguments().getString("Addr_district_id"));
//                jsonObject.put("TalukId",getArguments().getString("Addr_tehsil_id"));
//                jsonObject.put("HobliId",getArguments().getString("Addr_hobli_id"));



            }


            Crop_Post.crop_posting(getActivity(), Urls.Add_Bank_Details, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("sdbfhjbvbvbv" + result);

                    try{


                        String bank_status = result.getString("Status");

                        if(bank_status.equals("1")){

                            int duration = 1000;
                            Snackbar snackbar = Snackbar
                                    .make(linearLayout, "Bank Account Added Successfully", duration);
                            View snackbarView = snackbar.getView();
                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                            tv.setTextColor(Color.WHITE);


                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


                            } else {


                                tv.setGravity(Gravity.CENTER_HORIZONTAL);

                            }

                              selectedFragment = Get_Bank_List_Fragment.newInstance();
                              FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                              transaction.replace(R.id.frame_menu, selectedFragment);
                              transaction.commit();
                              snackbar.show();

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


    public void linear_layout_selection(EditText selectdl1,EditText l2,EditText l3,EditText l4, EditText l5 ,EditText l6 , EditText l7){

             selectdl1.setBackgroundResource(R.drawable.border_1_layout);
             l2.setBackgroundResource(R.drawable.request_price_white_border2);
             l3.setBackgroundResource(R.drawable.request_price_white_border2);
             l4.setBackgroundResource(R.drawable.request_price_white_border2);
             l5.setBackgroundResource(R.drawable.request_price_white_border2);
             l6.setBackgroundResource(R.drawable.request_price_white_border2);
             l7.setBackgroundResource(R.drawable.request_price_white_border2);


  }
}
