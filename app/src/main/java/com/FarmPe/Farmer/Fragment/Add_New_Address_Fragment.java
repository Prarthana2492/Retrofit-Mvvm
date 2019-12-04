package com.FarmPe.Farmer.Fragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.Toast;


import com.FarmPe.Farmer.Activity.HomePage_With_Bottom_Navigation;
import com.FarmPe.Farmer.Activity.Status_bar_change_singleton;
import com.FarmPe.Farmer.Adapter.AddFirstAdapter;
import com.FarmPe.Farmer.Adapter.DistrictAdapter;
import com.FarmPe.Farmer.Adapter.DistrictAdapter1;
import com.FarmPe.Farmer.Adapter.HoblisAdapter;
import com.FarmPe.Farmer.Adapter.HoblisAdapter1;
import com.FarmPe.Farmer.Adapter.StateApdater;
import com.FarmPe.Farmer.Adapter.TalukAdapter;
import com.FarmPe.Farmer.Adapter.VillageAdapter;
import com.FarmPe.Farmer.Adapter.You_Address_Adapter;
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

import static com.FarmPe.Farmer.Fragment.Notification_Recyc_Fragment.list;


public class  Add_New_Address_Fragment extends Fragment {

    RecyclerView recyclerView;


    static List<StateBean> stateBeanList = new ArrayList<>();
    static List<StateBean> districtBeanList = new ArrayList<>();
    static List<StateBean> talukBeanList = new ArrayList<>();
    static List<StateBean> hobliBeanList = new ArrayList<>();
    static List<StateBean> villageBeanList = new ArrayList<>();
    private List<StateBean> searchresultAraaylist = new ArrayList<>();
    StateApdater stateApdater;
    DistrictAdapter districtAdapter;
    TalukAdapter talukAdapter;
    HoblisAdapter hoblisAdapter;

    VillageAdapter villageAdapter;

    LinearLayout back_feed,state,district,tehsil,village,adrss_type_linear;
    public static DrawerLayout drawer,main_layout;

      String state_id,district_id,tehsil_id,hobli_id;
      TextView toolbar_titletxt,current_loc,ortext,norecords;
      JSONArray jsonArray,state_array,tal_array,hobli_array;
     StateBean stateBean;
     String add_new_status;
      EditText search;
     public static TextView save_1;
     public static String search_status="status";

    Fragment selectedFragment = null;
    String selected_addresstype;
    JSONObject lngObject;
    LinearLayout linearLayout;

    public static TextView state_txt,district_txt,tehsil_txt,village_txt,block_txt,cancel_add;
    String s_addtype,entername,entermno,inncrtmno,enterstreetad,enterpincode,selectstate,selectdistrict,selecthobli,newaddressadded,addnotadded ;
    public static EditText name,mobile,pincode_no,house_numb,street_name,select_address,address_type,edit_state,edit_districr,pincode_edittxt,edit_village,name_txt,mobile_edit;
    String status,message;
    String Id;
    SessionManager sessionManager;
    int selected_id_time;





    public static Add_New_Address_Fragment newInstance() {
        Add_New_Address_Fragment fragment = new Add_New_Address_Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_address_list, container, false);


        Status_bar_change_singleton.getInstance().color_change(getActivity());


        getActivity().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        HomePage_With_Bottom_Navigation.linear_bottom.setVisibility(View.GONE);

         select_address = view.findViewById(R.id.add_type);
         name = view.findViewById(R.id.full_name);
         mobile = view.findViewById(R.id.mob_no);
         back_feed = view.findViewById(R.id.back_feed);
         pincode_no = view.findViewById(R.id.pincode);
         house_numb = view.findViewById(R.id.house_no);
         street_name = view.findViewById(R.id.colny_street);
         mobile_edit = view.findViewById(R.id.mobile_edit);
         pincode_edittxt = view.findViewById(R.id.pincode_edittxt);

         search = view.findViewById(R.id.search);

         state_txt = view.findViewById(R.id.state_txt);
         district_txt = view.findViewById(R.id.district_txt);
         tehsil_txt = view.findViewById(R.id.tehsil_txt);

         village_txt = view.findViewById(R.id.village_txt);
         address_type = view.findViewById(R.id.address_type);
         adrss_type_linear = view.findViewById(R.id.adrss_type_linear);
         edit_state = view.findViewById(R.id.ed_state);
         edit_districr = view.findViewById(R.id.ed_dstrt);
         edit_village = view.findViewById(R.id.ed_vill);

         ortext = view.findViewById(R.id.ortext);
         save_1 = view.findViewById(R.id.save_1);
         recyclerView = view.findViewById(R.id.recycler_view);
         drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout_op);
         state = view.findViewById(R.id.state_1);
         district = view.findViewById(R.id.district_1);
         tehsil = view.findViewById(R.id.tehsil_1);
         village = view.findViewById(R.id.village_1);
         name_txt = view.findViewById(R.id.name_txt);
         cancel_add = view.findViewById(R.id.cancel_add);
         linearLayout = view.findViewById(R.id.linear_layout);
         norecords = view.findViewById(R.id.norecords);
         toolbar_titletxt=view.findViewById(R.id.setting_tittle);


         System.out.println("selecteddddd_iddd"+selected_id_time);
         name.setText(getArguments().getString("Addr_name"));
         System.out.println("selecteddddd_idddnz"+getArguments().getString("Addr_name"));
         mobile.setText(getArguments().getString("Addr_mobile"));
         pincode_no.setText(getArguments().getString("Addr_pincode"));
          house_numb.setText(getArguments().getString("Addr_Houseno"));
         street_name.setText(getArguments().getString("Addr_Street"));

         //landmrk.setText(getArguments().getString("Addr_landmark"));
        //city.setText(getArguments().getString("Addr_city"));

           state_txt.setText(getArguments().getString("Addr_state"));
           district_txt.setText(getArguments().getString("Addr_district"));
           tehsil_txt.setText(getArguments().getString("Addr_taluk"));
        // block_txt.setText(getArguments().getString("Addr_hobli"));
           village_txt.setText(getArguments().getString("Addr_hobli"));
           address_type.setText(getArguments().getString("Addr_pickup_from"));
           selected_addresstype = getArguments().getString("Addr_pickup_from");


          name.setFilters(new InputFilter[] { EMOJI_FILTER,new InputFilter.LengthFilter(30)});
          street_name.setFilters(new InputFilter[] {EMOJI_FILTER,new InputFilter.LengthFilter(50)});


          add_new_status = getArguments().getString("status_add_map");


          setupUI(drawer);

                 state_id = StateApdater.stateid;
                 district_id = DistrictAdapter.districtid;
                 tehsil_id = TalukAdapter.talukid;
                 hobli_id = HoblisAdapter.hobliid;

        sessionManager = new SessionManager(getActivity());

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        current_loc = view.findViewById(R.id.current_loc);

        if (getArguments().getString("navigation_from").equals("REQ_NEW")){
            current_loc.setVisibility(View.VISIBLE);
            ortext.setVisibility(View.VISIBLE);

       }else {
            current_loc.setVisibility(View.GONE);
            ortext.setVisibility(View.GONE);
        }

        if(getArguments().getString("navigation_from").equals("your_add")){
            toolbar_titletxt.setText("Edit Your Address");

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
                System.out.println("lllllllllllllllllllllllll"+getArguments().getString("navigation_from"));

                if (getArguments().getString("navigation_from").equals("yu_ads_frg")) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("yu_ads_frg", FragmentManager.POP_BACK_STACK_INCLUSIVE);



                }else if(getArguments().getString("navigation_from").equals("MAP_FRAGMENT")){

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("map_location", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                }

                else if(getArguments().getString("navigation_from").equals("your_add")){

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("your_add", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                }else if(getArguments().getString("navigation_from").equals("SETTING_FRAG")){

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                }else if(getArguments().getString("navigation_from").equals("Address_book")){

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("request_adress_book", FragmentManager.POP_BACK_STACK_INCLUSIVE);



                }else if(getArguments().getString("navigation_from").equals("HOME_FRAGMENT")){

                  //  HomeMenuFragment.onBack_status = "no_request";
                    selectedFragment = AaAccountFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_menu, selectedFragment);
                    transaction.commit();


                } else if(getArguments().getString("navigation_from").equals("edit_lokng_frg")) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("edit", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                }

//                }else{
//                    FragmentManager fm = getActivity().getSupportFragmentManager();
//                    fm.popBackStack("request", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                }
            }
        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    System.out.println("lllllllllllllllllllllllll"+getArguments().getString("navigation_from"));

                    if (getArguments().getString("navigation_from").equals("yu_ads_frg")) {

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("yu_ads_frg", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    }else if(getArguments().getString("navigation_from").equals("your_add")){
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("your_add", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    }else   if (getArguments().getString("navigation_from").equals("MAP_FRAGMENT")){

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("map_location", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    }else if(getArguments().getString("navigation_from").equals("SETTING_FRAG")){

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);



                    }else if(getArguments().getString("navigation_from").equals("Address_book")){

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("request_adress_book", FragmentManager.POP_BACK_STACK_INCLUSIVE);



                    }else if(getArguments().getString("navigation_from").equals("HOME_FRAGMENT")){

                        HomeMenuFragment.onBack_status = "no_request";
                        selectedFragment = AaAccountFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_menu, selectedFragment);
                        transaction.commit();


                    }else if(getArguments().getString("navigation_from").equals("edit_lokng_frg")) {

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("edit", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }

//                    } else{
//
//                        selectedFragment = Home_Menu_Fragment.newInstance();
//                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.frame_menu, selectedFragment);
//                        transaction.commit();
//                    }

                    return true;
                }
                return false;
            }
        });


        cancel_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (getArguments().getString("navigation_from").equals("yu_ads_frg")) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("yu_ads_frg", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                }else if(getArguments().getString("navigation_from").equals("your_add")){

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("your_add", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                }else if(getArguments().getString("navigation_from").equals("SETTING_FRAG")){

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);



                }else if(getArguments().getString("navigation_from").equals("HOME_FRAGMENT")){


                    HomeMenuFragment.onBack_status = "no_request";
                    selectedFragment = AaAccountFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_menu, selectedFragment);
                    transaction.commit();


                }else if(getArguments().getString("navigation_from").equals("Address_book")){

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("request_adress_book", FragmentManager.POP_BACK_STACK_INCLUSIVE);



                }else if(getArguments().getString("navigation_from").equals("edit_lokng_frg")) {


                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("edit", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                } else{

                    selectedFragment = Home_Menu_Fragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_menu, selectedFragment);
                    transaction.commit();
                }

            }
        });


        current_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = MapFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("currentlocation");
                transaction.commit();
            }
        });

        address_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.select_address_popup);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                LinearLayout image = (LinearLayout) dialog.findViewById(R.id.close_popup);
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
                        address_type.setText(home.getText().toString());

                        selected_addresstype = "Home";

                        dialog.dismiss();
                    }
                });


                ware_house.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        address_type.setText(ware_house.getText().toString());
                        selected_addresstype = "Warehouse";
                        dialog.dismiss();

                    }
                });

                farm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        address_type.setText(farm.getText().toString());
                        selected_addresstype = "Farm";
                        dialog.dismiss();

                    }
                });

                others.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        address_type.setText(others.getText().toString());
                        selected_addresstype = "Others";
                        dialog.dismiss();

                    }
                });


                dialog.show();

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

                stateApdater = new StateApdater(stateBeanList,getActivity());

                recyclerView.setAdapter(stateApdater);

                prepareStateData();


            }
        });


        district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!state_txt.getText().toString().equals("")) {


                    drawer.openDrawer(GravityCompat.END);

                    search_status="district";
                    search.setText("");


                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    districtAdapter= new DistrictAdapter( districtBeanList,getActivity());
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


        tehsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!district_txt.getText().toString().equals("")) {


                    drawer.openDrawer(GravityCompat.END);
                    search_status = "taluk";
                    search.setText("");
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                    talukAdapter = new TalukAdapter(talukBeanList, getActivity());
                    recyclerView.setAdapter(talukAdapter);
                    prepareTalukData();


                }else{

                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Please Select District", Snackbar.LENGTH_LONG);
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

//
//        hobli.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//
//                drawer.openDrawer(GravityCompat.END);
//
//                search_status = "hobli";
//                search.setText("");
//
//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//                recyclerView.setLayoutManager(mLayoutManager);
//
//                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                recyclerView.setLayoutManager(layoutManager);
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//                hoblisAdapter = new HoblisAdapter(hobliBeanList, getActivity());
//                recyclerView.setAdapter(hoblisAdapter);
//
//                prepareHobliData();
//
//            }
//        });


        village.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!district_txt.getText().toString().equals("")) {

                    // submit.setVisibility(View.VISIBLE);

                    drawer.openDrawer(GravityCompat.END);
                    search_status = "village";
                    search.setText("");
                    //   search.setQueryHint("");
                    // stateBeanList.clear();

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    villageAdapter = new VillageAdapter(villageBeanList, getActivity());
                    recyclerView.setAdapter(villageAdapter);

                    prepareHobliData();


                }else {


                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Please Select Tehsil", Snackbar.LENGTH_LONG);
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



        save_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if(address_type.getText().toString().equals("")){

                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, s_addtype,duration);
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



                }else if(name.getText().toString().equals("")) {
                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, entername,duration);
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





                }else if(mobile.getText().toString().equals("")){

                    int duration = 1000;

                    Snackbar snackbar = Snackbar
                            .make(linearLayout, entermno,duration);
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


                }else if(mobile.length()<10){
                    int duration=1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, inncrtmno,duration);
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



                }else if(state_txt.getText().toString().equals("")) {
                    int duration=1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, selectstate,duration);
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


                }else if(district_txt.getText().toString().equals("")) {
                    int duration=1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, selectdistrict,duration);
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


                }else if(tehsil_txt.getText().toString().equals("")) {
                    int duration=1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout,"Select Tehsil",duration);
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



                }else if(!(pincode_no.getText().toString().equals("")) && pincode_no.length()<6){
                    int duration=1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter a valid pincode",duration);
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

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
//Find the currently focused view, so we can grab the correct window token from it.
                    view = getActivity().getCurrentFocus();
//If no view currently has focus, create a new one, just so we can grab a window token from it
                    if (view == null) {
                        view = new View(getActivity());
                    }
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    ComposeCategory();

                }

            }
        });


        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));
           // toolbar_titletxt.setText(lngObject.getString("AddNewAddress"));
           // select_address.setHint(lngObject.getString("SelectanAddressType"));

            name_txt.setHint(lngObject.getString("FullName")+ " :");
            mobile_edit.setHint(lngObject.getString("PhoneNo") + " :");
            street_name.setHint(lngObject.getString("Colony_Street_Locality"));

            pincode_edittxt.setHint(lngObject.getString("Pincode")+" :");
            edit_state.setHint(lngObject.getString("State") + " :");

            edit_districr.setHint(lngObject.getString("District") + " :");
           // edit_village.setHint(lngObject.getString("Village")+" :");

            //  add_new_address.setText(lngObject.getString("AddAddress"));

            s_addtype = lngObject.getString("SelectanAddressType");
            entername = lngObject.getString("Enteryourname");
            entermno = lngObject.getString("EnterPhoneNo");
            inncrtmno = lngObject.getString("Entervalidmobilenumber");
            enterstreetad = lngObject.getString("EnterStreetaddress");
            enterpincode = lngObject.getString("Enterpincode");
            selectstate = lngObject.getString("Selectstate");
            selectdistrict = lngObject.getString("SelectDistrict");
            //selecttaluk = lngObject.getString("SelectTaluk");
            selecthobli = lngObject.getString("Selecthobli");
            newaddressadded = lngObject.getString("NewAddressaddedsuccessfully");
            addnotadded = lngObject.getString("YourAddressnotAdded");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;

    }

    private void prepareTalukData() {

        recyclerView.setVisibility(View.VISIBLE);

        try{

            JSONObject jsonObject = new JSONObject();
            JSONObject jsonpost = new JSONObject();
            jsonObject.put("DistrictId",DistrictAdapter.districtid);
            jsonpost.put("Talukobj",jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.Taluks, jsonpost, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("aaaaaaaaaaaaafffffffffffff"+result);
                    try{
                        talukBeanList.clear();
                        tal_array = result.getJSONArray("TalukList") ;



                         if (tal_array != null &&tal_array.length() > 0) {


                                for (int i = 0; i < tal_array.length(); i++) {
                                JSONObject jsonObject1 = tal_array.getJSONObject(i);
                                stateBean = new StateBean(jsonObject1.getString("Taluk"), jsonObject1.getString("TalukId"));
                                talukBeanList.add(stateBean);

                            }

                            sorting(talukBeanList);

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

    private void prepareHobliData() {
        recyclerView.setVisibility(View.VISIBLE);

        try{

            final JSONObject jsonObject = new JSONObject();

            JSONObject json_post = new JSONObject();
            jsonObject.put("TalukId",TalukAdapter.talukid);
            json_post.put("Hobliobj",jsonObject);

            System.out.println("hhhhhhhssssskljhgfmmmmmmmmmmmmmmmmmmmmmmmmmmmm" + json_post);


            Crop_Post.crop_posting(getActivity(), Urls.Hoblis, json_post, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("hhhhhhhssssskljhgfmmmmmmmmmmmmmmmmmmmmmmmmmmmm" + result);

                    try{
                        villageBeanList.clear();
                        hobli_array = result.getJSONArray("HobliList");

                        if (hobli_array != null &&hobli_array.length() > 0) {
                            for (int i = 0; i < hobli_array.length(); i++) {

                                JSONObject jsonObject3 = hobli_array.getJSONObject(i);
                                stateBean = new StateBean(jsonObject3.getString("Hobli"), jsonObject3.getString("HobliId"));
                                villageBeanList.add(stateBean);

                            }

                            sorting(villageBeanList);
                            villageAdapter.notifyDataSetChanged();
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

//    private void prepareVillageData() {
//
//
//        try{
//            JSONObject jsonObject = new JSONObject();
//            JSONObject post_Object = new JSONObject();
//            jsonObject.put("HobliId",hoblisAdapter.hobliid);
//            post_Object.put("Villageobj",jsonObject);
//
//            Crop_Post.crop_posting(getActivity(), Urls.Villages, post_Object, new VoleyJsonObjectCallback() {
//                @Override
//                public void onSuccessResponse(JSONObject result) {
//                    System.out.println("111vvv" + result);
//
//                    try{
//                        villageBeanList.clear();
//                        village_array = result.getJSONArray("VillageList");
//                        for(int i = 0;i<village_array.length();i++) {
//                            JSONObject jsonObject1 = village_array.getJSONObject(i);
//                            stateBean = new StateBean(jsonObject1.getString("Village"), jsonObject1.getString("VillagId"));
//                            villageBeanList.add(stateBean);
//                        }
//
//                        sorting(villageBeanList);
//
//                        villageAdapter.notifyDataSetChanged();
//                        //   grade_dialog.show();
//
//
//
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }

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

        try{

            JSONObject jsonObject = new JSONObject();
            JSONObject post_jsonobject = new JSONObject();

            jsonObject.put("StateId",stateApdater.stateid);
            post_jsonobject.put("Districtobj",jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.Districts, post_jsonobject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dddddddddddd11111" + result);
                    try {
                        districtBeanList.clear();
                        jsonArray = result.getJSONArray("DistrictList");
                        if (jsonArray != null &&jsonArray.length() > 0) {
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

                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void ComposeCategory() {
        try{

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("DistrictId",DistrictAdapter.districtid);
            jsonObject.put("HobliId", VillageAdapter.villageid);
            jsonObject.put("MobileNo",mobile.getText().toString());
            jsonObject.put("Name",name.getText().toString());
            jsonObject.put("PickUpFrom",selected_addresstype);
            jsonObject.put("Pincode",pincode_no.getText().toString());
            jsonObject.put("StateId",StateApdater.stateid);
            jsonObject.put("TalukId",TalukAdapter.talukid);
            // jsonObject.put("VillageId", VillageAdapter.villageid);
            jsonObject.put("StreeAddress",house_numb.getText().toString());
            jsonObject.put("StreeAddress1",street_name.getText().toString());
            jsonObject.put("UserId",sessionManager.getRegId("userId"));
          ///  jsonObject.put("Id", You_Address_Adapter.add_id);


            System.out.println("Add_New_AddresssssssssssssssssjsonObject"+jsonObject);



            if(getArguments().getString("navigation_from").equals("your_add")){
                System.out.println("Add_New_AddresssssssssssssssssjsonObjectiffff"+jsonObject);

                jsonObject.put("Id", You_Address_Adapter.add_id);

                jsonObject.put("StateId",getArguments().getString("Addr_state_id"));
                jsonObject.put("DistrictId",getArguments().getString("Addr_district_id"));
                jsonObject.put("TalukId",getArguments().getString("Addr_tehsil_id"));
                jsonObject.put("HobliId",getArguments().getString("Addr_hobli_id"));



            }

            System.out.println("Add_New_AddresssssssssssssssssjsonObjectiffffekkkkk"+jsonObject);

                Crop_Post.crop_posting(getActivity(), Urls.Add_New_Address, jsonObject, new VoleyJsonObjectCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject result) {
                        Bundle bundle = new Bundle();

                        System.out.println("Add_New_Addressssssssssssssssslllllllllllllllllllllll" + result);
                        try {

                            status = result.getString("Status");
                            message = result.getString("Message");

                            bundle.putString("add_id", status);

                            bundle.putString("streetname", DistrictAdapter.district_name);


                            if (!(status.equals("0"))) {


                                if (getArguments().getString("navigation_from").equals("yu_ads_frg")) {
                                    int duration = 1000;
                                    Snackbar snackbar = Snackbar
                                            .make(linearLayout, newaddressadded, duration);
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


                                    FragmentManager fm = getActivity().getSupportFragmentManager();
                                    fm.popBackStack("yu_ads_frg", FragmentManager.POP_BACK_STACK_INCLUSIVE);




                                } else if(getArguments().getString("navigation_from").equals("HOME_FRAGMENT")){

                                    int duration = 1000;
                                    Snackbar snackbar = Snackbar
                                            .make(linearLayout, newaddressadded, duration);
                                    View snackbarView = snackbar.getView();
                                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                                    tv.setTextColor(Color.WHITE);

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    } else {
                                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                                    }


                                //    HomeMenuFragment.onBack_status="no_request";
                                    selectedFragment = AaAccountFragment.newInstance();
                                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_menu, selectedFragment);
                                    transaction.addToBackStack("home");
                                    transaction.commit();
                                    snackbar.show();


                                } else if(getArguments().getString("navigation_from").equals("MAP_FRAGMENT")) {

                                    int duration = 1000;
                                    Snackbar snackbar = Snackbar
                                            .make(linearLayout, newaddressadded, duration);
                                    View snackbarView = snackbar.getView();
                                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                                    tv.setTextColor(Color.WHITE);

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    } else {
                                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                                    }


                                    if(add_new_status.equals("REQ_PRICE")){



                                        Bundle bundle1 = new Bundle();
                                        bundle1.putString("dealer_status", "Add_Add_Dealer_Book");
                                        bundle.putString("add_id", status);
                                        selectedFragment = DealerProfile.newInstance();
                                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                        transaction.replace(R.id.frame_menu, selectedFragment);
                                        transaction.addToBackStack("home");
                                        selectedFragment.setArguments(bundle1);
                                        transaction.commit();
                                        snackbar.show();


                                    }else{

                                        Bundle bundle1 = new Bundle();
                                        bundle1.putString("request_navigation", "ADD_FRAGMENT");
                                        bundle.putString("add_id", status);
                                        selectedFragment = Request_Details_New_Fragment.newInstance();
                                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                        transaction.replace(R.id.frame_menu, selectedFragment);
                                        transaction.addToBackStack("home");
                                        selectedFragment.setArguments(bundle1);
                                        transaction.commit();
                                        snackbar.show();

                                    }

                                } else if (getArguments().getString("navigation_from").equals("SETTING_FRAG")) {
                                    int duration = 1000;
                                    Snackbar snackbar = Snackbar
                                            .make(linearLayout, newaddressadded, duration);
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
                                    selectedFragment = You_Address_Fragment.newInstance();
                                    FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_menu, selectedFragment);
                                    transaction.commit();





                                } else if (getArguments().getString("navigation_from").equals("Address_book")) {
                                    int duration = 1000;
                                    Snackbar snackbar = Snackbar
                                            .make(linearLayout, newaddressadded, duration);
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
                                    selectedFragment = Request_Details_New_Fragment.newInstance();
                                    FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_menu, selectedFragment);
                                    transaction.commit();


                                } else if (getArguments().getString("navigation_from").equals("your_add")) {
                                    int duration = 1000;
                                    Snackbar snackbar1 = Snackbar
                                            .make(linearLayout, "Address updated Successfully", duration);
                                    View snackbarView1 = snackbar1.getView();
                                    TextView tv1 = (TextView) snackbarView1.findViewById(android.support.design.R.id.snackbar_text);
                                    tv1.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                                    tv1.setTextColor(Color.WHITE);

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        tv1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    } else {
                                        tv1.setGravity(Gravity.CENTER_HORIZONTAL);
                                    }
                                    snackbar1.show();

                                    FragmentManager fm = getActivity().getSupportFragmentManager();
                                    fm.popBackStack("your_add", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                                } else {
                                    int duration = 1000;
                                    Snackbar snackbar = Snackbar
                                            .make(linearLayout, newaddressadded, duration);
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

                            } else {

                                int duration = 1000;
                                Snackbar snackbar = Snackbar
                                        .make(linearLayout, addnotadded, duration);
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


                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    }

                });


        }catch (Exception e){
            e.printStackTrace();

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
    public static InputFilter EMOJI_FILTER1 = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            boolean keepOriginal = true;
            StringBuilder sb = new StringBuilder(end - start);
            for (int index = start; index < end; index++) {
                int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                    return "";
                }
                String filtered = "";
                for (int i = start; i < end; i++) {
                    char character = source.charAt(i);
                    if (!Character.isWhitespace(character)) {
                        filtered += character;
                    }
                }
                return filtered;
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



    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        /*InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);*/

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
                stateApdater = new StateApdater(searchresultAraaylist, getActivity());
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


                districtAdapter = new DistrictAdapter(searchresultAraaylist, getActivity());
                recyclerView.setAdapter(districtAdapter);

            }

//            districtAdapter = new DistrictAdapter(searchresultAraaylist, getActivity());
//            recyclerView.setAdapter(districtAdapter);


        }

        else if (search_status.equals("taluk")) {
            searchresultAraaylist.clear();
            for (int i = 0; i < talukBeanList.size(); i++) {

                if (talukBeanList.get(i).getName().toLowerCase().contains(text)) {
                    searchresultAraaylist.add(talukBeanList.get(i));

                }
            }

            if (searchresultAraaylist.size() == 0) {
                recyclerView.setVisibility(View.GONE);
                norecords.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                norecords.setVisibility(View.GONE);

                talukAdapter = new TalukAdapter(searchresultAraaylist, getActivity());
                recyclerView.setAdapter(talukAdapter);
            }
        }

        else if (search_status.equals("village")){
            searchresultAraaylist.clear();
            for (int i = 0; i < hobliBeanList.size(); i++) {

                if (hobliBeanList.get(i).getName().toLowerCase().contains(text)) {
                    searchresultAraaylist.add(hobliBeanList.get(i));

                }
            }

            if (searchresultAraaylist.size()==0){
                recyclerView.setVisibility(View.GONE);
                norecords.setVisibility(View.VISIBLE);
            }else {
                recyclerView.setVisibility(View.VISIBLE);
                norecords.setVisibility(View.GONE);

                hoblisAdapter = new HoblisAdapter(searchresultAraaylist, getActivity());
                recyclerView.setAdapter(hoblisAdapter);
            }
        }

//        else if (search_status.equals("village")){
//            searchresultAraaylist.clear();
//            for (int i = 0; i < villageBeanList.size(); i++) {
//
//                if (villageBeanList.get(i).getName().toLowerCase().contains(text)) {
//                    searchresultAraaylist.add(villageBeanList.get(i));
//
//                }
//            }
////                    villageAdapter = new VillageAdapter(sdearcstateBeanList);
//            recyclerView.setAdapter(villageAdapter);
//        }

    }
}



