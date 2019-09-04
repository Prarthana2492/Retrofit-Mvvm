package com.FarmPe.Farmer.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    LinearLayout back_feed;
    TextView filter_text,delete_req;
    SessionManager sessionManager;

   public static JSONArray cropsListArray = null;
   public static JSONArray tractorImplementsModelMasterList = null;
   public static JSONArray tractorAccessoriesModelMasterList = null;
   public static JSONArray harvesterModelMasterList = null;
   public static JSONArray jCBRFQModelList = null;

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
        back_feed =view.findViewById(R.id.back_feed);
        newOrderBeansList.clear();


        sessionManager = new SessionManager(getActivity());
      // System.out.println("bbbbbbbbbbbbbbbbb"+ "+1-333-444-5678".replaceAll("[^\\d\\+]", "").replaceAll("\\d(?=\\d{4})", "*"));
       System.out.println("bbbbbbbbbbbbbbbbb"+ "+1-333-444-5678".replaceAll("\\d{4}(?=\\d)", "*"));
      // System.out.println("bbbbbbbbbbbbbbbbbbbb"+ "+1-333-444-5678".replaceAll("\\d{4}(?=\\d)", "*"));



        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getArguments().getString("status").equals("setting_your_request")) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("setting_req", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                } else if(getArguments().getString("status").equals("hme_request")){

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                }


            }
        });


        LookingForList();



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {



                    if(getArguments().getString("status").equals("setting_your_request")){

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("setting_req", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    } else if(getArguments().getString("status").equals("hme_request")){

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    }


//                    }else{
//
//                        HomeMenuFragment.onBack_status="no_request";
//                        FragmentManager fm = getActivity().getSupportFragmentManager();
//                        fm.popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                    }

//                    HomeMenuFragment.onBack_status="no_request";
//
//
//
//                    FragmentManager fm = getActivity().getSupportFragmentManager();
//                    fm.popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });

        filter_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.layout_filterpopup);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                final TextView rec = (TextView) dialog.findViewById(R.id.recen_added);
                final TextView asce = (TextView)dialog.findViewById(R.id.sort_ascendi) ;
                final TextView desc = (TextView)dialog.findViewById(R.id.sort_desendi) ;
                //   final TextView popuptxt = (TextView)dialog.findViewById(R.id.popup_heading) ;
                ImageView image = (ImageView) dialog.findViewById(R.id.close_popup);
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();


            }
        });

//        filter_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                selectedFragment = Comming_soon_looking.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.first_full_frame, selectedFragment);
//                transaction.addToBackStack("looking_edit");
//                transaction.commit();
//
//            }
//        });

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
                        cropsListArray = result.getJSONArray("TractorRFQModelList");

                    tractorImplementsModelMasterList = result.getJSONArray("TractorImplementsRFQModelList");
                    tractorAccessoriesModelMasterList = result.getJSONArray("TractorAccesoriesRFQModelList");
                    harvesterModelMasterList = result.getJSONArray("HarvesterRFQModelList");
                        jCBRFQModelList = result.getJSONArray("JCBRFQModelList");

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
                            String area = jsonObject2.getString("Hoblie");
                            String hp_range = jsonObject1.getString("HorsePower");
                            location = city + ", " + state+","+area;


                            String location_det = jsonObject2.getString("Name")+","+jsonObject2.getString("MobileNo")
                                    +","+jsonObject2.getString("StreeAddress1")+","+jsonObject2.getString("PickUpFrom")
                                    +","+jsonObject2.getString("State")+","+jsonObject2.getString("District")
                                    +","+jsonObject2.getString("Taluk")+","+jsonObject2.getString("Hoblie")
                                    +","+jsonObject2.getString("Pincode");



                            System.out.println("madelslistt" + newOrderBeansList.size());

                            FarmsImageBean crops = new FarmsImageBean(image, "Tractor Price", model, hp_range, purchaseTimeline, name, location, id,location_det);
                            newOrderBeansList.add(crops);

                        }


                        for (int i = 0; i < tractorImplementsModelMasterList.length(); i++) {
                            JSONObject jsonObject1 = tractorImplementsModelMasterList.getJSONObject(i);
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("Address");
                            String model = jsonObject1.getString("Model");
                            String purchaseTimeline = jsonObject1.getString("PurchaseTimeline");
                            String image = jsonObject1.getString("ModelImage");
                            String id = jsonObject1.getString("Id");
                            String name = jsonObject2.getString("Name");
                            String city = jsonObject2.getString("City");
                            String state = jsonObject2.getString("State");
                            String area = jsonObject2.getString("Hoblie");
                          //  String hp_range = jsonObject1.getString("HorsePower");
                            String hp_range = "";
                            location = city + ", " + state+","+area;


                            String location_det = jsonObject2.getString("Name")+","+jsonObject2.getString("MobileNo")
                                    +","+jsonObject2.getString("StreeAddress1")+","+jsonObject2.getString("PickUpFrom")
                                    +","+jsonObject2.getString("State")+","+jsonObject2.getString("District")
                                    +","+jsonObject2.getString("Taluk")+","+jsonObject2.getString("Hoblie")
                                    +","+jsonObject2.getString("Pincode");



                            System.out.println("madelslistt" + newOrderBeansList.size());

                            FarmsImageBean crops = new FarmsImageBean(image, "Tractor Price", model, hp_range, purchaseTimeline, name, location, id,location_det);
                            newOrderBeansList.add(crops);

                        }





                        for (int i = 0; i < tractorAccessoriesModelMasterList.length(); i++) {
                            JSONObject jsonObject1 = tractorAccessoriesModelMasterList.getJSONObject(i);
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("Address");
                            String model = jsonObject1.getString("Model");
                            String purchaseTimeline = jsonObject1.getString("PurchaseTimeline");
                            String image = jsonObject1.getString("ModelImage");
                            String id = jsonObject1.getString("Id");
                            String name = jsonObject2.getString("Name");
                            String city = jsonObject2.getString("City");
                            String state = jsonObject2.getString("State");
                            String area = jsonObject2.getString("Hoblie");
                          //  String hp_range = jsonObject1.getString("HorsePower");
                            String hp_range = "";
                            location = city + ", " + state+","+area;


                            String location_det = jsonObject2.getString("Name")+","+jsonObject2.getString("MobileNo")
                                    +","+jsonObject2.getString("StreeAddress1")+","+jsonObject2.getString("PickUpFrom")
                                    +","+jsonObject2.getString("State")+","+jsonObject2.getString("District")
                                    +","+jsonObject2.getString("Taluk")+","+jsonObject2.getString("Hoblie")
                                    +","+jsonObject2.getString("Pincode");



                            System.out.println("madelslistt" + newOrderBeansList.size());

                            FarmsImageBean crops = new FarmsImageBean(image, "Tractor Price", model, hp_range, purchaseTimeline, name, location, id,location_det);
                            newOrderBeansList.add(crops);

                        }


                        for (int i = 0; i < harvesterModelMasterList.length(); i++) {
                            JSONObject jsonObject1 = harvesterModelMasterList.getJSONObject(i);
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("Address");
                            String model = jsonObject1.getString("Model");
                            String purchaseTimeline = jsonObject1.getString("PurchaseTimeline");
                            String image = jsonObject1.getString("ModelImage");
                            String id = jsonObject1.getString("Id");
                            String name = jsonObject2.getString("Name");
                            String city = jsonObject2.getString("City");
                            String state = jsonObject2.getString("State");
                            String area = jsonObject2.getString("Hoblie");
                          //  String hp_range = jsonObject1.getString("HorsePower");
                            String hp_range = "";
                            location = city + ", " + state+","+area;


                            String location_det = jsonObject2.getString("Name")+","+jsonObject2.getString("MobileNo")
                                    +","+jsonObject2.getString("StreeAddress1")+","+jsonObject2.getString("PickUpFrom")
                                    +","+jsonObject2.getString("State")+","+jsonObject2.getString("District")
                                    +","+jsonObject2.getString("Taluk")+","+jsonObject2.getString("Hoblie")
                                    +","+jsonObject2.getString("Pincode");



                            System.out.println("madelslistt" + newOrderBeansList.size());

                            FarmsImageBean crops = new FarmsImageBean(image, "Tractor Price", model, hp_range, purchaseTimeline, name, location, id,location_det);
                            newOrderBeansList.add(crops);

                        }

                        for (int i = 0; i < jCBRFQModelList.length(); i++) {
                            JSONObject jsonObject1 = jCBRFQModelList.getJSONObject(i);
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("Address");
                            String model = jsonObject1.getString("Model");
                            String purchaseTimeline = jsonObject1.getString("PurchaseTimeline");
                            String image = jsonObject1.getString("ModelImage");
                            String id = jsonObject1.getString("Id");
                            String name = jsonObject2.getString("Name");
                            String city = jsonObject2.getString("City");
                            String state = jsonObject2.getString("State");
                            String area = jsonObject2.getString("Hoblie");
                            String hp_range = jsonObject1.getString("HorsePower");
                            location = city + ", " + state+","+area;


                            String location_det = jsonObject2.getString("Name")+","+jsonObject2.getString("MobileNo")
                                    +","+jsonObject2.getString("StreeAddress1")+","+jsonObject2.getString("PickUpFrom")
                                    +","+jsonObject2.getString("State")+","+jsonObject2.getString("District")
                                    +","+jsonObject2.getString("Taluk")+","+jsonObject2.getString("Hoblie")
                                    +","+jsonObject2.getString("Pincode");



                            System.out.println("madelslistt" + newOrderBeansList.size());

                            FarmsImageBean crops = new FarmsImageBean(image, "Tractor Price", model, hp_range, purchaseTimeline, name, location, id,location_det);
                            newOrderBeansList.add(crops);

                        }



                        farmadapter = new FarmsImageAdapter(getActivity(), newOrderBeansList);
                        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(mLayoutManager_farm);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(farmadapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                /*    try {
                        cropsListArray = result.getJSONArray("TractorRFQModelList");
                        tractorImplementsModelMasterList = result.getJSONArray("TractorImplementsRFQModelList");
                        tractorAccessoriesModelMasterList = result.getJSONArray("TractorAccesoriesRFQModelList");
                        harvesterModelMasterList = result.getJSONArray("HarvesterRFQModelList");
                       // jCBModelMasterList = result.getJSONArray("JCBModelMasterList");
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
                                String area = jsonObject2.getString("Hoblie");
                                String hp_range = jsonObject1.getString("HorsePower");
                                location = city + ", " + state+","+area;


                                String location_det = jsonObject2.getString("Name")+","+jsonObject2.getString("MobileNo")
                                        +","+jsonObject2.getString("StreeAddress1")+","+jsonObject2.getString("PickUpFrom")
                                        +","+jsonObject2.getString("State")+","+jsonObject2.getString("District")
                                        +","+jsonObject2.getString("Taluk")+","+jsonObject2.getString("Hoblie")
                                        +","+jsonObject2.getString("Pincode");



                                System.out.println("madelslistt" + newOrderBeansList.size());

                                FarmsImageBean crops = new FarmsImageBean(image, "Tractor Price", model, hp_range, purchaseTimeline, name, location, id,location_det);
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
                    }*/


                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
