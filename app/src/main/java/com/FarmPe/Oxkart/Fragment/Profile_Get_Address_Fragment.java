package com.FarmPe.Oxkart.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.Oxkart.Activity.HomePage_With_Bottom_Navigation;
import com.FarmPe.Oxkart.Activity.Status_bar_change_singleton;
import com.FarmPe.Oxkart.Adapter.Address_Adapter;
import com.FarmPe.Oxkart.Bean.Profile_Address_Bean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;



public class Profile_Get_Address_Fragment extends Fragment {


    public static ArrayList<Profile_Address_Bean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static Address_Adapter address_adapter;
    Fragment selectedFragment = null;
    TextView toolbar_title,Continue_txt;
    public static String livestock_status;
    public static LinearLayout back_feed,linearLayout,Continue;
    JSONArray new_address_list_array;
    SessionManager sessionManager;




    public static Profile_Get_Address_Fragment newInstance() {
        Profile_Get_Address_Fragment fragment = new Profile_Get_Address_Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.soil_details_recy_layout, container, false);
        Status_bar_change_singleton.getInstance().color_change(getActivity());



        recyclerView=view.findViewById(R.id.recycler_what_looking);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        toolbar_title.setText("My Addresses");
        back_feed=view.findViewById(R.id.back_feed);
        linearLayout = view.findViewById(R.id.main_layout);
        Continue = view.findViewById(R.id.continuebtn);
        Continue_txt = view.findViewById(R.id.apply_loan);
        Continue_txt.setText("ADD NEW ADDRESS");
        sessionManager = new SessionManager(getActivity());



        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("prof_add_status","Get_Address");
                selectedFragment = Profile_Add_New_Address_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("newaddressfragment");
                transaction.commit();
            }
        });



      back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("settingg", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {


                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("settingg", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    return true;
                }
                return false;
            }
        });


        newOrderBeansList.clear();
        address_adapter=new Address_Adapter(getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(address_adapter);


//        Add_New_Address_Bean img1=new Add_New_Address_Bean("Jagdish","102","RR Nagar","","Bengaluru","560098","","","Karnataka",
//                "","","","","", true,"","","","");
//        newOrderBeansList.add(img1);
//        newOrderBeansList.add(img1);
//        newOrderBeansList.add(img1);

        get_address_profile_details();

        return view;
    }

    private void get_address_profile_details() {

        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId", sessionManager.getRegId("userId"));


            Crop_Post.crop_posting(getActivity(), Urls.Profile_Get_Adress_Details, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dfsdfssaaa" + result);

                    try {

                        new_address_list_array = result.getJSONArray("AddressDetails");


                        for (int i = 0; i < new_address_list_array.length(); i++) {
                            JSONObject jsonObject1 = new_address_list_array.getJSONObject(i);

                                    Profile_Address_Bean profile_address_bean = new Profile_Address_Bean(jsonObject1.getString("FullName"), jsonObject1.getString("MobileNo"), jsonObject1.getString("Address"), jsonObject1.getString("LandMark"), jsonObject1.getString("State"),
                                    jsonObject1.getString("District"), jsonObject1.getString("BlockName"), jsonObject1.getString("Village"), jsonObject1.getString("Pincode"),
                                    jsonObject1.getString("StateId"), jsonObject1.getString("DistrictId"), jsonObject1.getString("BlockId"), jsonObject1.getString("VillageId"), jsonObject1.getString("AddressDId"));


                            newOrderBeansList.add(profile_address_bean);

                        }
                        address_adapter.notifyDataSetChanged();


                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
