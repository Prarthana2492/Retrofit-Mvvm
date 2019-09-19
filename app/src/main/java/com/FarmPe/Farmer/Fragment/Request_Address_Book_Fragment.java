package com.FarmPe.Farmer.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.Farmer.Adapter.Request_Address_Book_Adapter;
import com.FarmPe.Farmer.Adapter.You_Address_Adapter;
import com.FarmPe.Farmer.Bean.Add_New_Address_Bean;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Volly_class.Crop_Post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class Request_Address_Book_Fragment extends Fragment {

    Fragment selectedFragment;
    TextView name,mobile_no,street_addrss,landmrk,pincode,add_new_address,select_address_type,filter;
    EditText doc_number,doc_name;
    private RecyclerView recyclerView;
    public static TextView address_list;
    LinearLayout back_feed;
    Request_Address_Book_Adapter mAdapter;
    String pickUPFrom;
    SessionManager sessionManager;
    public static String navigation_all;
    public static String item_list,address;
    public static LinearLayout linearLayout;

    JSONObject lngObject;

    ArrayList<Add_New_Address_Bean> new_address_beanArrayList = new ArrayList<>();
    Add_New_Address_Bean add_new_address_bean;
    JSONArray get_address_array;
    LinearLayout back,select_add_address,addnew_linear,add_visible;
    String Id,ad_list,adrs_are_added;
    ImageView b_arrow;


    public static Request_Address_Book_Fragment newInstance() {
        Request_Address_Book_Fragment fragment = new Request_Address_Book_Fragment();
        return fragment;

    }


    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adress_book_recy_layout, container, false);
        //  getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        recyclerView = view.findViewById(R.id.recycler_2);

        sessionManager =new SessionManager(getActivity());

        mAdapter = new Request_Address_Book_Adapter(new_address_beanArrayList,getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        addressbook_list();


        return view;
    }

    private void addressbook_list() {


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


                            add_new_address_bean = new Add_New_Address_Bean(jsonObject1.getString("Name"),jsonObject1.getString("StreeAddress"),jsonObject1.getString("StreeAddress1"),jsonObject1.getString("LandMark"),jsonObject1.getString("City"),jsonObject1.getString("Pincode"),jsonObject1.getString("MobileNo"), jsonObject1.getString("PickUpFrom"),jsonObject1.getString("State"),jsonObject1.getString("District"),jsonObject1.getString("Taluk"),jsonObject1.getString("Hoblie"),jsonObject1.getString("Village"),jsonObject1.getString("Id"),
                                    jsonObject1.getBoolean("IsDefaultAddress"),jsonObject1.getString("StateId"),jsonObject1.getString("DistrictId"),jsonObject1.getString("TalukId"),jsonObject1.getString("VillageId"));
                            new_address_beanArrayList.add(add_new_address_bean);

                        }



                        mAdapter.notifyDataSetChanged();




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
