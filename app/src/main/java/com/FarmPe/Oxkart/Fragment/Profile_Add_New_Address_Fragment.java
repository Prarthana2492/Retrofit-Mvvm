package com.FarmPe.Oxkart.Fragment;

import android.app.Activity;
import android.content.Context;
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
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.FarmPe.Oxkart.Adapter.Address_Adapter;
import com.FarmPe.Oxkart.Adapter.DistrictAdapter1;
import com.FarmPe.Oxkart.Adapter.Gram_Panchayat_Adapter;
import com.FarmPe.Oxkart.Adapter.Nyay_Panchayat_Adapter;
import com.FarmPe.Oxkart.Adapter.StateApdater1;
import com.FarmPe.Oxkart.Adapter.TalukAdapter1;
import com.FarmPe.Oxkart.Adapter.VillageAdapter1;
import com.FarmPe.Oxkart.Bean.StateBean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;
import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Profile_Add_New_Address_Fragment extends Fragment {

    RecyclerView recycler_brand;
    LinearLayout back_feed,loan_lay,main_layout,continuebtn,linear_layout;
    TextView back_text,next;
    Fragment selectedFragment;
    public static EditText full_name,mobile_no,address,landmark,pincode,state,district,block,nyaypanchayat,grampanchayath,village ;

    Calendar myCalendar;
    public static EditText state_txt;
    public static DrawerLayout drawer;
    RecyclerView recyclerView;

    String account_validate,ifsc_validate,status;
    EditText account_no,ifsc_code;
    SessionManager sessionManager;

    static List<StateBean> stateBeanList = new ArrayList<>();
    static List<StateBean> districtBeanList = new ArrayList<>();
    static List<StateBean> blockBeanList = new ArrayList<>();
    static List<StateBean> nyay_panchyatBeanList = new ArrayList<>();
    static List<StateBean> gram_panchayatBeanList = new ArrayList<>();
    static List<StateBean> villageBeanList = new ArrayList<>();

     private List<StateBean> searchresultAraaylist = new ArrayList<>();

     StateBean stateBean;
     StateApdater1 stateApdater;
     DistrictAdapter1 districtAdapter;
     TalukAdapter1 talukAdapter;
     Nyay_Panchayat_Adapter nyay_panchayat_adapter;
     Gram_Panchayat_Adapter gram_panchayat_adapter;
     VillageAdapter1 villageAdapter1;


    JSONArray state_array,district_array,tal_array;
    EditText search;
    TextView norecords,toolbar_title;

    public static String search_status="status";


    public static Profile_Add_New_Address_Fragment newInstance() {
        Profile_Add_New_Address_Fragment fragment = new Profile_Add_New_Address_Fragment();
        return fragment;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_address_navigation, container, false);


        getActivity().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);



       Window window = getActivity().getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.dark_green));
        back_feed=view.findViewById(R.id.back_feed);
        full_name=view.findViewById(R.id.fulname);
        mobile_no=view.findViewById(R.id.mobno);
        address=view.findViewById(R.id.addressss);
        landmark=view.findViewById(R.id.lnd_mr);
        pincode=view.findViewById(R.id.pincodeee);
        state=view.findViewById(R.id.st);
        district=view.findViewById(R.id.dt);
        block=view.findViewById(R.id.bk);
        village=view.findViewById(R.id.ve);
        recyclerView = view.findViewById(R.id.recycler_view);
        drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout_op);
        linear_layout=view.findViewById(R.id.linear_layout);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        norecords = view.findViewById(R.id.norecords);
        search = view.findViewById(R.id.search);
        sessionManager = new SessionManager(getActivity());

        setupUI(linear_layout);


           full_name.setText(getArguments().getString("addr_name"));
           mobile_no.setText(getArguments().getString("addr_mobile_number"));
           address.setText(getArguments().getString("addr_address"));
           pincode.setText(getArguments().getString("addr_pincode"));
           landmark.setText(getArguments().getString("addr_landmark"));

           state.setText(getArguments().getString("addr_state"));
           district.setText(getArguments().getString("addr_district"));
          block.setText(getArguments().getString("addr_block"));
          village.setText(getArguments().getString("addr_village"));


        if(getArguments().getString("prof_add_status").equals("edit_add_addressss")){

            toolbar_title.setText("Edit Address Details");
        }


        continuebtn=view.findViewById(R.id.continuebtn);

        address.setFilters(new InputFilter[] { EMOJI_FILTER,new InputFilter.LengthFilter(50)});
       // street_name.setFilters(new InputFilter[] {EMOJI_FILTER,new InputFilter.LengthFilter(50)});
        landmark.setFilters(new InputFilter[] {EMOJI_FILTER,new InputFilter.LengthFilter(50)});



        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(getArguments().getString("prof_add_status").equals("profile_add_address")){


                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("settingg", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                }else if(getArguments().getString("prof_add_status").equals("edit_add_addressss")) {



                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("address_list_page", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                }else if(getArguments().getString("prof_add_status").equals("Get_Address")) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("newaddressfragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            }
        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {


                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {


                    if(getArguments().getString("prof_add_status").equals("profile_add_address")){


                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("settingg", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    }else if(getArguments().getString("address_toolbar").equals("edit_add_addressss")) {

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("address_list_page", FragmentManager.POP_BACK_STACK_INCLUSIVE);



                    }else if(getArguments().getString("prof_add_status").equals("Get_Address")) {


                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("newaddressfragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }

                    return true;
                }

                return false;
            }
        });


        full_name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(full_name,mobile_no,address,landmark,pincode,state,district,block,village);
                return false;
            }
        });


        mobile_no.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(mobile_no,full_name,address,landmark,pincode,state,district,block,village);
                return false;
            }
        });


        address.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(address,full_name,mobile_no,landmark,pincode,state,district,block,village);
                return false;
            }
        });


        landmark.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(landmark,full_name,mobile_no,address,pincode,state,district,block,village);
                return false;
            }
        });


        pincode.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(pincode,full_name,mobile_no,address,landmark,state,district,block,village);
                return false;
            }
        });


        state.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(state,full_name,mobile_no,address,landmark,pincode,district,block,village);
                return false;
            }
        });


        district.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(district,full_name,mobile_no,address,landmark,pincode,state,block,village);
                return false;
            }
        });


        block.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(block,full_name,mobile_no,address,landmark,pincode,state,district,village);
                return false;
            }
        });


        village.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(village,full_name,mobile_no,address,landmark,pincode,state,district,block);
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
                stateApdater = new StateApdater1(stateBeanList,getActivity());
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
                    districtAdapter= new DistrictAdapter1( districtBeanList,getActivity());
                    recyclerView.setAdapter(districtAdapter);
                    prepareDistricData();


                }else{

                    Snackbar snackbar = Snackbar
                            .make(linear_layout, "Please Select State", Snackbar.LENGTH_LONG);
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



        block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(!district.getText().toString().equals("")) {

                    drawer.openDrawer(GravityCompat.END);
                    search_status = "taluk";
                    search.setText("");
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                    talukAdapter = new TalukAdapter1(blockBeanList, getActivity());
                    recyclerView.setAdapter(talukAdapter);
                    prepareBlockData();


                }else{

                    Snackbar snackbar = Snackbar
                            .make(linear_layout, "Please Select District", Snackbar.LENGTH_LONG);
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



        village.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(!block.getText().toString().equals("")) {


                    drawer.openDrawer(GravityCompat.END);
                    search_status = "village";
                    search.setText("");
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    villageAdapter1 = new VillageAdapter1(villageBeanList, getActivity());
                    recyclerView.setAdapter(villageAdapter1);
                    prepare_village_data();


                }else{

                    Snackbar snackbar = Snackbar
                            .make(linear_layout, "Please Select Block", Snackbar.LENGTH_LONG);
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
            public void onClick(View view) {

                if(full_name.getText().toString().equals("")) {

                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linear_layout, "Enter Your Name", duration);
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

                } else if(mobile_no.getText().toString().equals("")) {


                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linear_layout, "Enter Your Mobile Number", duration);
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


                } else if(mobile_no.length()<10) {

                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linear_layout, "Invalid Mobile Number", duration);
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



                }else if(address.getText().toString().equals("")){


                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linear_layout, "Enter Your Address", duration);
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


                } else if(pincode.getText().toString().equals("")){


                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linear_layout, "Enter Pincode", duration);
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



                } else if(state.getText().toString().equals("")){


                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linear_layout, "Select State", duration);
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



                } else if(district.getText().toString().equals("")){

                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linear_layout, "Select District", duration);
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


                } else if(block.getText().toString().equals("")){

                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linear_layout, "Select Block", duration);
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






//                } else if(village.getText().toString().equals("")){
//
//
//                    int duration = 1000;
//                    Snackbar snackbar = Snackbar
//                            .make(linear_layout, "Select Village", duration);
//                    View snackbarView = snackbar.getView();
//                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
//                    tv.setTextColor(Color.WHITE);
//
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                    } else {
//                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
//
//                    }
//
//                    snackbar.show();


                }else {

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
//Find the currently focused view, so we can grab the correct window token from it.
                    view = getActivity().getCurrentFocus();
//If no view currently has focus, create a new one, just so we can grab a window token from it
                    if (view  == null) {
                        view = new View(getActivity());
                    }
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


                    add_profile_address();

                }
            }
        });


        return view;
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

            jsonObject.put("StateId",StateApdater1.stateid);
            post_jsonobject.put("Districtobj", jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.Districts, post_jsonobject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dddddddddddd11111" + result);
                    try {
                        districtBeanList.clear();
                        district_array = result.getJSONArray("DistrictList");
                        if (district_array != null && district_array.length() > 0) {
                            for (int i = 0; i < district_array.length(); i++) {
                                JSONObject jsonObject1 = district_array.getJSONObject(i);
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



    private void prepareBlockData() {


        recyclerView.setVisibility(View.VISIBLE);

        try{

            JSONObject jsonObject = new JSONObject();
            JSONObject jsonpost = new JSONObject();
            jsonObject.put("DistrictId", DistrictAdapter1.districtid);
            jsonpost.put("Blockobj",jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.Block_List, jsonpost, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("aaaaaaaaaaaaafffffffffffff"+result);
                    try{
                        blockBeanList.clear();
                        tal_array = result.getJSONArray("BlockList") ;



                        if (tal_array != null &&tal_array.length() > 0) {


                            for (int i = 0; i < tal_array.length(); i++) {
                                JSONObject jsonObject1 = tal_array.getJSONObject(i);
                                stateBean = new StateBean(jsonObject1.getString("BlockName"), jsonObject1.getString("BlockId"));
                                blockBeanList.add(stateBean);

                            }

                            sorting(blockBeanList);

                            talukAdapter.notifyDataSetChanged();
                            // grade_dialog.show();
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


    private void prepare_village_data() {


        recyclerView.setVisibility(View.VISIBLE);

        try {

            JSONObject jsonObject = new JSONObject();
            JSONObject post_jsonobject = new JSONObject();

            jsonObject.put("BlockId",TalukAdapter1.talukid);
            post_jsonobject.put("Villageobj", jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.Village_list, post_jsonobject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dddddddddddd11111" + result);
                    try {
                        villageBeanList.clear();
                        district_array = result.getJSONArray("VillageListByBlock");
                        if (district_array != null && district_array.length() > 0) {
                            for (int i = 0; i < district_array.length(); i++) {
                                JSONObject jsonObject1 = district_array.getJSONObject(i);
                                stateBean = new StateBean(jsonObject1.getString("Village"), jsonObject1.getString("VillagId"));
                                villageBeanList.add(stateBean);
                            }

                            sorting(villageBeanList);

                            villageAdapter1.notifyDataSetChanged();
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





    public static InputFilter  EMOJI_FILTER = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            boolean keepOriginal = true;
            String specialChars = ".1/*!@#$%^&*()\"{}_[]|\\?/<>,.:-'';§£¥₹...%&+=€π|";
            StringBuilder sb = new StringBuilder(end - start);
            for (int index = start; index < end; index++) {
                int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL||type==Character.MATH_SYMBOL||specialChars.contains("" + source)) {
                    return "";
                }
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        if (dstart == 0)
                            return "";
                    }else if(Character.isDigit(source.charAt(i))) {
                        return "";
                    }
                }
                return null;

            }
            if (keepOriginal)
                return null;
            else {
                if (source instanceof Spanned) {
                    SpannableString sp = new SpannableString(sb);
                    TextUtils.copySpansFrom((Spanned) source, start, sb.length(), null, sp, 0);
                    return sp;
                } else {
                    return sb;
                }
            }
        }
    };




    private void add_profile_address() {

        try{

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));
            jsonObject.put("FullName",full_name.getText().toString());
            jsonObject.put("MobileNo",mobile_no.getText().toString());
            jsonObject.put("Address",address.getText().toString());
            jsonObject.put("LandMark",landmark.getText().toString());
            jsonObject.put("StateId",StateApdater1.stateid);
            jsonObject.put("DistrictId",DistrictAdapter1.districtid);
            jsonObject.put("BlockId",TalukAdapter1.talukid);
            jsonObject.put("VillageId",VillageAdapter1.villageid);
            jsonObject.put("PinCode",pincode.getText().toString());


            if(getArguments().getString("prof_add_status").equals("edit_add_addressss")){

                jsonObject.put("UserId",sessionManager.getRegId("userId"));
                jsonObject.put("FullName",full_name.getText().toString());
                jsonObject.put("MobileNo",mobile_no.getText().toString());
                jsonObject.put("Address",address.getText().toString());
                jsonObject.put("LandMark",landmark.getText().toString());
                jsonObject.put("StateId",StateApdater1.stateid);
                jsonObject.put("DistrictId",DistrictAdapter1.districtid);
                jsonObject.put("BlockId",TalukAdapter1.talukid);
                jsonObject.put("VillageId",VillageAdapter1.villageid);
                jsonObject.put("PinCode",pincode.getText().toString());
                jsonObject.put("AddressDId", Address_Adapter.address_id);

            }


            System.out.println("trtrtrtreterte" + jsonObject);



            Crop_Post.crop_posting(getActivity(), Urls.Profile_Add_Adress_Details, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dfdfsdgggh" + result);


                    try{

                        String status = result.getString("Status");
                        String message = result.getString("Message");


                        if(status.equals("1")){

                          //  Toast.makeText(getActivity(), "added successfully", Toast.LENGTH_SHORT).show();

                            int duration = 1000;
                            Snackbar snackbar = Snackbar
                                    .make(linear_layout, message, duration);
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


                            selectedFragment = Profile_Get_Address_Fragment.newInstance();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_menu, selectedFragment);
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
            }else {
                recyclerView.setVisibility(View.VISIBLE);
                norecords.setVisibility(View.GONE);
                stateApdater = new StateApdater1(searchresultAraaylist, getActivity());
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


                districtAdapter = new DistrictAdapter1(searchresultAraaylist, getActivity());
                recyclerView.setAdapter(districtAdapter);

            }

//            districtAdapter = new DistrictAdapter(searchresultAraaylist, getActivity());
//            recyclerView.setAdapter(districtAdapter);


        }


        else if (search_status.equals("taluk")) {
            searchresultAraaylist.clear();
            for (int i = 0; i < blockBeanList.size(); i++) {

                if (blockBeanList.get(i).getName().toLowerCase().contains(text)) {
                    searchresultAraaylist.add(blockBeanList.get(i));

                }
            }

            if (searchresultAraaylist.size() == 0) {
                recyclerView.setVisibility(View.GONE);
                norecords.setVisibility(View.VISIBLE);
            } else {

                recyclerView.setVisibility(View.VISIBLE);
                norecords.setVisibility(View.GONE);

                talukAdapter = new TalukAdapter1(searchresultAraaylist, getActivity());
                recyclerView.setAdapter(talukAdapter);
            }
        }


        else if (search_status.equals("village")) {
            searchresultAraaylist.clear();
            for (int i = 0; i < villageBeanList.size(); i++) {

                if (villageBeanList.get(i).getName().toLowerCase().contains(text)) {
                    searchresultAraaylist.add(villageBeanList.get(i));

                }
            }

            if (searchresultAraaylist.size() == 0) {
                recyclerView.setVisibility(View.GONE);
                norecords.setVisibility(View.VISIBLE);


            } else {

                recyclerView.setVisibility(View.VISIBLE);
                norecords.setVisibility(View.GONE);

                villageAdapter1 = new VillageAdapter1(searchresultAraaylist, getActivity());
                recyclerView.setAdapter(villageAdapter1);
            }
        }

    }


    public void setupUI(View view) {


        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }

            });
        }

        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {


        InputMethodManager inputManager = (InputMethodManager)
                activity.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();

        if (focusedView != null) {

            try{
                assert inputManager != null;
                inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }catch(AssertionError e){
                e.printStackTrace();
            }
        }
    }

    public void linear_layout_selection(EditText selectdl1,EditText l2,EditText l3,EditText l4, EditText l5 ,EditText l6 , EditText l7,EditText l8,EditText l9){
      selectdl1.setBackgroundResource(R.drawable.border_1_layout);


      l2.setBackgroundResource(R.drawable.request_price_white_border2);
      l3.setBackgroundResource(R.drawable.request_price_white_border2);
      l4.setBackgroundResource(R.drawable.request_price_white_border2);
      l5.setBackgroundResource(R.drawable.request_price_white_border2);
      l6.setBackgroundResource(R.drawable.request_price_white_border2);
      l7.setBackgroundResource(R.drawable.request_price_white_border2);
      l8.setBackgroundResource(R.drawable.request_price_white_border2);
      l9.setBackgroundResource(R.drawable.request_price_white_border2);

  }
}
