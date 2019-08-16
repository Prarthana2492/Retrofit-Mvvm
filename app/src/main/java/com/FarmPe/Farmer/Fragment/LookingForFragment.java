package com.FarmPe.Farmer.Fragment;

import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.FarmPe.Farmer.Adapter.FarmsImageAdapter;
import com.FarmPe.Farmer.Bean.FarmsImageBean;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Volly_class.Crop_Post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class LookingForFragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    private List<FarmsImageBean> searchresultAraaylist = new ArrayList<>();

    public static RecyclerView recyclerView;
    public static FarmsImageAdapter farmadapter;
    Fragment selectedFragment = null;
    boolean doubleBackToExitPressedOnce = false;
    String location;
    TextView filter_text,delete_req;
    SessionManager sessionManager;

   public static JSONArray cropsListArray = null;

    public static LookingForFragment newInstance() {
        LookingForFragment fragment = new LookingForFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.looking_for_recy, container, false);
        recyclerView=view.findViewById(R.id.recycler_looking);
        filter_text=view.findViewById(R.id.filter_text);
        delete_req =view.findViewById(R.id.delete_req);
        newOrderBeansList.clear();


        sessionManager = new SessionManager(getActivity());
      // System.out.println("bbbbbbbbbbbbbbbbb"+ "+1-333-444-5678".replaceAll("[^\\d\\+]", "").replaceAll("\\d(?=\\d{4})", "*"));
       System.out.println("bbbbbbbbbbbbbbbbb"+ "+1-333-444-5678".replaceAll("\\d{4}(?=\\d)", "*"));
      // System.out.println("bbbbbbbbbbbbbbbbbbbb"+ "+1-333-444-5678".replaceAll("\\d{4}(?=\\d)", "*"));



        LookingForList();



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    HomeMenuFragment.onBack_status="no_request";



                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });



        filter_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedFragment = Comming_soon_looking.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.first_full_frame, selectedFragment);
                transaction.addToBackStack("looking_edit");
                transaction.commit();

            }
        });


        return view;
    }
    private void LookingForList() {

        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId", sessionManager.getRegId("userId"));
            System.out.println("aaaaaaaaaaaaadddd" + sessionManager.getRegId("userId"));

            Crop_Post.crop_posting(getActivity(), Urls.YourRequest, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("YourRequestttttttttttttttttt" + result);


                    try {
                        cropsListArray = result.getJSONArray("LookingForList");
                        System.out.println("eeeddd" + cropsListArray.length());

                        if (cropsListArray.length() == 0) {

                            selectedFragment = No_Request_Fragment.newInstance();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout, selectedFragment);
                            transaction.commit();

                        } else {


                            for (int i = 0; i < cropsListArray.length(); i++) {
                                JSONObject jsonObject1 = cropsListArray.getJSONObject(i);
                                JSONObject jsonObject2 = jsonObject1.getJSONObject("Address");
                                String model = jsonObject1.getString("Model");
                                String purchaseTimeline = jsonObject1.getString("PurchaseTimeline");
                                String image = jsonObject1.getString("ModelImage");
                                String id = jsonObject1.getString("Id");
                                String name = jsonObject2.getString("Name");
                                String city = jsonObject2.getString("City");
                                String state = jsonObject2.getString("State");
                                String hp_range = jsonObject1.getString("HorsePowerRange");
                                location = city + ", " + state;


                                System.out.println("madelslistt" + newOrderBeansList.size());

                                FarmsImageBean crops = new FarmsImageBean(image, "Tractor Price", model, hp_range, purchaseTimeline, name, location, id);
                                newOrderBeansList.add(crops);


                            }
                        }

                        farmadapter = new FarmsImageAdapter(getActivity(), newOrderBeansList);
                        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(mLayoutManager_farm);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(farmadapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
