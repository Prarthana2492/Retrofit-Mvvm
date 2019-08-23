package com.FarmPe.Farmer.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Farmer.Adapter.DistrictAdapter;
import com.FarmPe.Farmer.Adapter.DistrictAdapter1;
import com.FarmPe.Farmer.Adapter.HoblisAdapter;
import com.FarmPe.Farmer.Adapter.HoblisAdapter1;
import com.FarmPe.Farmer.Adapter.New_Default_Address_Adapter;
import com.FarmPe.Farmer.Adapter.StateApdater;
import com.FarmPe.Farmer.Adapter.StateApdater1;
import com.FarmPe.Farmer.Adapter.TalukAdapter;
import com.FarmPe.Farmer.Adapter.TalukAdapter1;
import com.FarmPe.Farmer.Adapter.VillageAdapter;
import com.FarmPe.Farmer.Adapter.VillageAdapter1;
import com.FarmPe.Farmer.Adapter.You_Address_Adapter;
import com.FarmPe.Farmer.Bean.AddTractorBean;
import com.FarmPe.Farmer.Bean.Add_New_Address_Bean;
import com.FarmPe.Farmer.Bean.StateBean;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Volly_class.Crop_Post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class New_Address_Fragment extends Fragment {

    public static List<AddTractorBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    RecyclerView dialog_recyclerView;
    ImageView dialog_image;
    LinearLayout back_feed, main_layout, search_bar;
    Fragment selectedFragment;
    String address_map;
    public static  EditText search;
    public static DrawerLayout drawer;
    TextView toolbar_title, continue_3;
    public static TextView address_list;
    public static String item_list,address;
    public static TextView state, district, taluk, block, village, skip, current_loc,or;
    public static EditText street_add, pincode;
    public static Dialog grade_dialog;
    public static String search_status = "status";
    private List<StateBean> searchresultAraaylist = new ArrayList<>();
    String selected_addresstype;
    JSONObject lngObject;
    static List<StateBean> stateBeanList = new ArrayList<>();
    static List<StateBean> districtBeanList = new ArrayList<>();
    static List<StateBean> talukBeanList = new ArrayList<>();
    static List<StateBean> hobliBeanList = new ArrayList<>();
    static List<StateBean> villageBeanList = new ArrayList<>();
    ArrayList<Add_New_Address_Bean> new_address_beanArrayList = new ArrayList<>();
    ImageView left_arrw;

    SessionManager sessionManager;
    StateApdater1 stateApdater;
    DistrictAdapter1 districtAdapter;
    TalukAdapter1 talukAdapter;
    HoblisAdapter1 hoblisAdapter;
    New_Default_Address_Adapter new_default_address_adapter;

   VillageAdapter1 villageAdapter;

    Add_New_Address_Bean add_new_address_bean;
    JSONArray get_address_array;
    JSONArray jsonArray, state_array, tal_array, hobli_array, village_array;
    StateBean stateBean;
    EditText current_adds,default_address,mob_no,name;
    Dialog dialog;
    public static String district_string, state_string,street_string, pincode_string;


    public static New_Address_Fragment newInstance() {
        New_Address_Fragment fragment = new New_Address_Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.request_address_layout, container, false);

        back_feed = view.findViewById(R.id.back_feed);
        or = view.findViewById(R.id.or);
        name = view.findViewById(R.id.name);
        mob_no = view.findViewById(R.id.mob_no);
        continue_3 = view.findViewById(R.id.continue_3);
        state = view.findViewById(R.id.state);
        district = view.findViewById(R.id.district);
        recyclerView = view.findViewById(R.id.recycler_view);
        taluk = view.findViewById(R.id.taluk);
        block = view.findViewById(R.id.block);
        search = view.findViewById(R.id.search);

         village=view.findViewById(R.id.village);

        search_bar = view.findViewById(R.id.search_bar);
        default_address = view.findViewById(R.id.default_address);
        street_add = view.findViewById(R.id.street_add);
        drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout_op);
        pincode = view.findViewById(R.id.pincode);
        main_layout = view.findViewById(R.id.main_layout);
        current_loc = view.findViewById(R.id.current_loc);

        sessionManager = new SessionManager(getActivity());

        street_add.setFilters(new InputFilter[]{EMOJI_FILTER, new InputFilter.LengthFilter(30)});

        address_map = getArguments().getString("status");

        if (address_map.equals("default")) {

            current_loc.setVisibility(View.VISIBLE);


        } else {
//            current_adds.setText(address_map);
//            current_adds.setVisibility(View.VISIBLE);

        }

       // setupUI(main_layout);
     //   current_adds.setText(address_map);

//
//        current_adds.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                selectedFragment = MapFragment.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout, selectedFragment);
//                transaction.addToBackStack("farm_third");
//                transaction.commit();
//            }
//        });


       gettingAddress(" ");


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("list_farm2", FragmentManager.POP_BACK_STACK_INCLUSIVE);

//                    Bundle bundle = new Bundle();
//                    bundle.putInt("RB_S", List_Farm_Adapter2.position_1);
//                    selectedFragment = ListYourFarmsSecond.newInstance();
//                    selectedFragment.setArguments(bundle);
//                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame_layout, selectedFragment);
//                    transaction.addToBackStack("list_farm1");
//                    transaction.commit();

                    return true;
                }
                return false;
            }
        });


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("list_farm2", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });


        current_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment = MapFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("farm_third");
                transaction.commit();

            }
        });




        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sorting1(s.toString());
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


        default_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.default_address_pop_up_layout);

                dialog_image = (ImageView) dialog.findViewById(R.id.close_popup);
                dialog_recyclerView =  (RecyclerView) dialog.findViewById(R.id.address_recyc);
               address_list =(TextView)dialog.findViewById(R.id.ware_hus);


                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                dialog_recyclerView.setLayoutManager(layoutManager);
                dialog_recyclerView.setItemAnimator(new DefaultItemAnimator());

                new_default_address_adapter = new New_Default_Address_Adapter(new_address_beanArrayList, getActivity());

                dialog_recyclerView.setAdapter(new_default_address_adapter);

                gettingAddress(" ");

                dialog_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

             //   dialog.show();

            }
        });



        street_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.select_address_popup);

                ImageView image = (ImageView) dialog.findViewById(R.id.close_popup);
                final TextView home =(TextView)dialog.findViewById(R.id.home_1);
                final TextView ware_house = (TextView)dialog.findViewById(R.id.ware_hus) ;
                final TextView farm = (TextView)dialog.findViewById(R.id.farm) ;
                final TextView others = (TextView)dialog.findViewById(R.id.othrs) ;
                final TextView popuptxt = (TextView)dialog.findViewById(R.id.popup_heading) ;

                try {
                    lngObject = new JSONObject(sessionManager.getRegId("language"));
                    popuptxt.setText(lngObject.getString("SelectanAddressType"));
                    home.setText(lngObject.getString("Home"));
                    ware_house.setText(lngObject.getString("Warehouse"));
                    farm.setText(lngObject.getString("Farm"));
                    others.setText(lngObject.getString("Others"));
                  //  new_add_toast = (lngObject.getString("NewAddressaddedsuccessfully"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });



                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        street_add.setText(home.getText().toString());

                        selected_addresstype = "Home";

                        dialog.dismiss();
                    }
                });



                ware_house.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        street_add.setText(ware_house.getText().toString());
                        selected_addresstype = "Warehouse";
                        dialog.dismiss();

                    }
                });

                farm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        street_add.setText(farm.getText().toString());
                        selected_addresstype = "Farm";
                        dialog.dismiss();

                    }
                });

                others.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        street_add.setText(others.getText().toString());
                        selected_addresstype = "Others";
                        dialog.dismiss();

                    }
                });


                dialog.show();

            }
        });




        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                // submit.setVisibility(View.GONE);
                drawer.openDrawer(GravityCompat.END);
                search_status = "state";
                search.setText("");
                //  search.setQueryHint("");
                //  search.setQuery("",false);
                stateBeanList.clear();
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                stateApdater = new StateApdater1(stateBeanList, getActivity());

                recyclerView.setAdapter(stateApdater);

                prepareStateData();


            }
        });

        district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!state.getText().toString().equals("")) {
                    getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                    // submit.setVisibility(View.INVISIBLE);
                    drawer.openDrawer(GravityCompat.END);
                    // stateBeanList.clear();
                    search_status = "district";
                    search.setText("");
                    //  search.setQuery("",false);
                    // search.setQueryHint("");
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    districtAdapter = new DistrictAdapter1(districtBeanList, getActivity());
                    recyclerView.setAdapter(districtAdapter);

                    prepareDistricData();
                }
                else {
                    Snackbar snackbar = Snackbar
                            .make(main_layout, "Please select a state", Snackbar.LENGTH_LONG);
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


        taluk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!district.getText().toString().equals("")) {
                    getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                    //submit.setVisibility(View.INVISIBLE);
                    drawer.openDrawer(GravityCompat.END);
                    // stateBeanList.clear();
                    search_status = "taluk";
                    search.setText("");
                    //  search.setQueryHint("");
                    //  search.setQuery("",false);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                    talukAdapter = new TalukAdapter1(talukBeanList, getActivity());
                    recyclerView.setAdapter(talukAdapter);
                    prepareTalukData();
                }

                else{
                    Snackbar snackbar = Snackbar
                            .make(main_layout, "Please select a district", Snackbar.LENGTH_LONG);
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
                if(!taluk.getText().toString().equals("")) {
                    getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                    //  submit.setVisibility(View.INVISIBLE);
                    drawer.openDrawer(GravityCompat.END);
                    // stateBeanList.clear();
                    search_status = "block";
                    search.setText("");
                    //search.setQuery("",false);
                    //  search.setQueryHint("");
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);

                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                    hoblisAdapter = new HoblisAdapter1(hobliBeanList, getActivity());
                    recyclerView.setAdapter(hoblisAdapter);

                    prepareHobliData();


                }else{
                    Snackbar snackbar = Snackbar
                            .make(main_layout, "Please select a taluk", Snackbar.LENGTH_LONG);
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
                getActivity().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                // submit.setVisibility(View.VISIBLE);

                drawer.openDrawer(GravityCompat.END);
                search_status="village";
                search.setText("");
                //   search.setQueryHint("");
                // stateBeanList.clear();

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                villageAdapter = new VillageAdapter1(villageBeanList,getActivity());
                recyclerView.setAdapter(villageAdapter);

                prepareVillageData();



            }
        });


        continue_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    //System.out.println("kjyhgvkhygf" + "onClick: nbvMHfgdfmgluhg");
                    if (state.getText().toString().equals("") && district.getText().toString().equals("") && taluk.getText().toString().equals("") && block.getText().toString().equals("")) {
                        int duration = 1000;
                        Snackbar snackbar = Snackbar
                                .make(main_layout, "Please enter all the details",duration);
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



                    } else if (state.getText().toString().equals("")) {
                        int duration = 1000;
                        Snackbar snackbar = Snackbar
                                .make(main_layout, "Select State",duration);
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


                    } else if (district.getText().toString().equals("")) {
                        int duration = 1000;
                        Snackbar snackbar = Snackbar
                                .make(main_layout, "Select District",duration);
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


                    } else if (taluk.getText().toString().equals("")) {
                        int duration = 1000;
                        Snackbar snackbar = Snackbar
                                .make(main_layout, "Select Taluk", duration);
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



                    } else if (block.getText().toString().equals("")) {
                        int duration = 1000;
                        Snackbar snackbar = Snackbar
                                .make(main_layout, "Select Block",duration);
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


                    } else if (pincode.getText().toString().length() < 6) {
                        int duration = 1000;
                        // Toast.makeText(getActivity(), "Enter a valid Pincode", Toast.LENGTH_SHORT).show();
                        Snackbar snackbar = Snackbar
                                .make(main_layout, "Enter 6 Digit Pincode",duration);
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


                    } else {
                        street_string = street_add.getText().toString();
                        pincode_string = pincode.getText().toString();
                        state_string = state.getText().toString();
                        district_string = district.getText().toString();
//                        selectedFragment = ListYourFarmsFive.newInstance();
//                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.frame_layout, selectedFragment);
//                        transaction.addToBackStack("farm_third");
//
//                        transaction.commit();

                        ComposeCategory();
                    }



                }

        });

        return view;
    }




    public void gettingAddress(final String pickUPFrom){

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



                        if(new_address_beanArrayList.size()==1){

                            item_list = String.valueOf(new_address_beanArrayList.size());
                            address_list.setText(item_list+" " + " Address is added" );
                            new_default_address_adapter.notifyDataSetChanged();
                            dialog.show();


                        }else if (new_address_beanArrayList.size()==0) {
                            default_address.setVisibility(View.GONE);
                            or.setVisibility(View.GONE);


                        }
                        else{
                            dialog.show();

                            item_list = String.valueOf(new_address_beanArrayList.size());
                            address_list.setText(item_list+" " + " Addresses are added" );
                            new_default_address_adapter.notifyDataSetChanged();

                        }


                       //dialog.show();




                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });


        }catch (Exception e){
            e.printStackTrace();
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



    private void prepareStateData() {

        try

        {

            JSONObject jsonObject = new JSONObject();

            Crop_Post.crop_posting(getActivity(), Urls.State, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    try {
                        stateBeanList.clear();
                        state_array = result.getJSONArray("StateList");
                        for (int i = 0; i < state_array.length(); i++) {
                            JSONObject jsonObject1 = state_array.getJSONObject(i);

                            stateBean = new StateBean(jsonObject1.getString("State").trim(), jsonObject1.getString("StateId"));
                            stateBeanList.add(stateBean);
                        }
                        sorting(stateBeanList);

                        stateApdater.notifyDataSetChanged();
                        grade_dialog.show();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

        }catch(
                Exception e)

        {
            e.printStackTrace();
        }

    }


    private void prepareDistricData() {


        try{

            JSONObject jsonObject = new JSONObject();
            JSONObject post_jsonobject = new JSONObject();
            jsonObject.put("StateId",StateApdater1.stateid);
            post_jsonobject.put("Districtobj",jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.Districts, post_jsonobject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dddddddddddd11111" + result);
                    try{
                        districtBeanList.clear();
                        jsonArray = result.getJSONArray("DistrictList");
                        for(int i =0;i<jsonArray.length();i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            stateBean = new StateBean(jsonObject1.getString("District"),jsonObject1.getString("DistrictId"));
                            districtBeanList.add(stateBean);
                        }

                        sorting(districtBeanList);


                        districtAdapter.notifyDataSetChanged();
                        grade_dialog.show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }



    private void prepareTalukData() {

        try{

            JSONObject jsonObject = new JSONObject();
            JSONObject jsonpost = new JSONObject();
            jsonObject.put("DistrictId",DistrictAdapter1.districtid);
            jsonpost.put("Talukobj",jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.Taluks, jsonpost, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("aaaaaaaaaaaaafffffffffffff"+result);
                    try{
                        talukBeanList.clear();
                        tal_array = result.getJSONArray("TalukList") ;
                        for(int i=0;i<tal_array.length();i++){
                            JSONObject jsonObject1 = tal_array.getJSONObject(i);
                            stateBean = new StateBean(jsonObject1.getString("Taluk"),jsonObject1.getString("TalukId"));
                            talukBeanList.add(stateBean);

                        }
                        sorting(talukBeanList);

                        talukAdapter.notifyDataSetChanged();
                        // grade_dialog.show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void prepareHobliData() {

        try{

            final JSONObject jsonObject = new JSONObject();

            JSONObject json_post = new JSONObject();
            jsonObject.put("TalukId",TalukAdapter1.talukid);
            json_post.put("Hobliobj",jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.Hoblis, json_post, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("hhhhhhhssssskljhgf" + result);

                    try{
                        hobliBeanList.clear();
                        hobli_array = result.getJSONArray("HobliList");
                        for(int i = 0;i<hobli_array.length();i++){
                            JSONObject jsonObject3 = hobli_array.getJSONObject(i);
                            stateBean = new StateBean(jsonObject3.getString("Hobli"),jsonObject3.getString("HobliId"));
                            hobliBeanList.add(stateBean);

                        }
                        sorting(hobliBeanList);

                        hoblisAdapter.notifyDataSetChanged();
                        //  grade_dialog.show();



                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void prepareVillageData() {


        try{
            JSONObject jsonObject = new JSONObject();
            JSONObject post_Object = new JSONObject();
            jsonObject.put("HobliId",HoblisAdapter1.hobliid);
            post_Object.put("Villageobj",jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.Villages, post_Object, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("111vvv" + result);

                    try{
                        villageBeanList.clear();
                        village_array = result.getJSONArray("VillageList");
                        for(int i = 0;i<village_array.length();i++) {
                            JSONObject jsonObject1 = village_array.getJSONObject(i);
                            stateBean = new StateBean(jsonObject1.getString("Village"), jsonObject1.getString("VillagId"));
                            villageBeanList.add(stateBean);
                        }

                        sorting(villageBeanList);

                        villageAdapter.notifyDataSetChanged();
                        //   grade_dialog.show();



                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static InputFilter EMOJI_FILTER = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            boolean keepOriginal = true;
            StringBuilder sb = new StringBuilder(end - start);
            for (int index = start; index < end; index++) {
                int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                    return "";
                }
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        if (dstart == 0)
                            return "";
                    }
                }
                return null;
      /*  char c = source.charAt(index);
        if (isCharAllowed(c))
            sb.append(c);
        else
            keepOriginal = false;*/
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


    public  void sorting1(String filter_text){
        System.out.println("lllllllllllllllljjjjjjj"+stateBeanList.size());

        final String text = filter_text.toLowerCase();



        if (search_status.equals("state")){
            searchresultAraaylist.clear();
            for (int i = 0; i < stateBeanList.size(); i++) {

                if (stateBeanList.get(i).getName().toLowerCase().contains(text)) {
                    searchresultAraaylist.add(stateBeanList.get(i));

                }
            }
            stateApdater = new StateApdater1(searchresultAraaylist,getActivity());
            recyclerView.setAdapter(stateApdater);
        }
        else if (search_status.equals("district")) {
            searchresultAraaylist.clear();
            for (int i = 0; i < districtBeanList.size(); i++) {

                if (districtBeanList.get(i).getName().toLowerCase().contains(text)) {
                    searchresultAraaylist.add(districtBeanList.get(i));

                }
            }
            districtAdapter = new DistrictAdapter1(searchresultAraaylist, getActivity());
            recyclerView.setAdapter(districtAdapter);


        }

        else if (search_status.equals("taluk")){
            searchresultAraaylist.clear();
            for (int i = 0; i < talukBeanList.size(); i++) {

                if (talukBeanList.get(i).getName().toLowerCase().contains(text)) {
                    searchresultAraaylist.add(talukBeanList.get(i));

                }
            }
            talukAdapter = new TalukAdapter1(searchresultAraaylist, getActivity());
            recyclerView.setAdapter(talukAdapter);
        }


        else if (search_status.equals("block")){
            searchresultAraaylist.clear();
            for (int i = 0; i < hobliBeanList.size(); i++) {

                if (hobliBeanList.get(i).getName().toLowerCase().contains(text)) {
                    searchresultAraaylist.add(hobliBeanList.get(i));

                }
            }
            hoblisAdapter = new HoblisAdapter1(searchresultAraaylist, getActivity());
            recyclerView.setAdapter(hoblisAdapter);


        }

    }



    private void ComposeCategory() {
        try{

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("DistrictId", DistrictAdapter1.districtid);
            jsonObject.put("HobliId", HoblisAdapter1.hobliid);
            //jsonObject.put("LandMark",landmrk.getText().toString());
            // jsonObject.put("City",city.getText().toString());
            jsonObject.put("MobileNo",mob_no.getText().toString());
            jsonObject.put("Name",name.getText().toString());
            jsonObject.put("PickUpFrom",selected_addresstype);
            jsonObject.put("Pincode",(pincode.getText().toString()));
            jsonObject.put("StateId", StateApdater1.stateid);
            jsonObject.put("TalukId", TalukAdapter1.talukid);
            jsonObject.put("VillageId", VillageAdapter1.villageid);
            // jsonObject.put("StreeAddress",house_numb.getText().toString());
            jsonObject.put("StreeAddress1","hhhhhhhhhhhhhhhh");
            jsonObject.put("UserId",sessionManager.getRegId("userId"));
            System.out.println("Add_New_AddresssssssssssssssssjsonObject"+jsonObject);





            Crop_Post.crop_posting(getActivity(), Urls.Add_New_Address, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("Add_New_Addresssssssssssssssss"+result);
                    try{

                    String    status= result.getString("Status");





                        Bundle bundle = new Bundle();
                        bundle.putString("status","default");
                        bundle.putInt("selected_id2",getArguments().getInt("selected_id2"));
                        bundle.putInt("selected_id_time1",getArguments().getInt("selected_id_time1"));
                        bundle.putString("add_id",status);
                        bundle.putString("streetname",DistrictAdapter1.distric_name+HoblisAdapter1.hobli_name);








                        selectedFragment = RequestFormFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.addToBackStack("list_farm2");
                        selectedFragment.setArguments(bundle);
                        transaction.commit();







                    }catch (Exception e){
                        e.printStackTrace();

                    }
                }
            });


        }catch (Exception e){
            System.out.println("Add_New_Addresssssssssssssssss"+e.toString());

            e.printStackTrace();

        }
    }




}

