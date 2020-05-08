package com.FarmPe.Oxkart.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.FarmPe.Oxkart.Activity.HomePage_With_Bottom_Navigation;
import com.FarmPe.Oxkart.Activity.Status_bar_change_singleton;
import com.FarmPe.Oxkart.Adapter.FarmsImageAdapter;
import com.FarmPe.Oxkart.Adapter.Inbox_Pending_Adapter;
import com.FarmPe.Oxkart.Bean.FarmsImageBean;
import com.FarmPe.Oxkart.Bean.Get_RFQ_List_Bean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class Mail_Box_Fragment extends Fragment {


    public static List<Get_RFQ_List_Bean> newOrderBeansList = new ArrayList<>();
    private List<FarmsImageBean> searchresultAraaylist = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static Inbox_Pending_Adapter farmadapter;
    Fragment selectedFragment = null;
    boolean doubleBackToExitPressedOnce = false;
    String location;
    LinearLayout dropdown_txt;
    TextView filter_text,delete_req;
    SessionManager sessionManager;




    public static JSONArray cropsListArray = null;
    public static JSONArray tractorImplementsModelMasterList = null;
    public static JSONArray tractorAccessoriesModelMasterList = null;
    public static JSONArray harvesterModelMasterList = null;
    public static JSONArray jCBRFQModelList = null;
    public static JSONArray farmMachineryRFQModelList = null;
    public static JSONArray fenceWireRFQModelList = null;
    public static JSONArray tyreRFQModelList = null;
    public static JSONArray miniTruckRFQModelList = null;
    public static JSONArray backhoeAttachmentRFQModelList = null;
    public static JSONArray powerTillerRFQModelList = null;



    public static Mail_Box_Fragment newInstance() {
        Mail_Box_Fragment fragment = new Mail_Box_Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pending_recyc_layout, container, false);
        Status_bar_change_singleton.getInstance().color_change(getActivity());


        recyclerView=view.findViewById(R.id.recyc_mailbox);
        dropdown_txt = view.findViewById(R.id.dropdown_txt);

        newOrderBeansList.clear();


        sessionManager = new SessionManager(getActivity());
        // System.out.println("bbbbbbbbbbbbbbbbb"+ "+1-333-444-5678".replaceAll("[^\\d\\+]", "").replaceAll("\\d(?=\\d{4})", "*"));
        System.out.println("bbbbbbbbbbbbbbbbb"+ "+1-333-444-5678".replaceAll("\\d{4}(?=\\d)", "*"));
        // System.out.println("bbbbbbbbbbbbbbbbbbbb"+ "+1-333-444-5678".replaceAll("\\d{4}(?=\\d)", "*"));






        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {


                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    return true;
                }
                return false;
            }
        });



        dropdown_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(getActivity(),dropdown_txt);
                popupMenu.getMenu().add("INBOX");
                popupMenu.getMenu().add("SENT");
                popupMenu.getMenu().add("FILTERED");



                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem items) {

                        if(items.getTitle().equals("INBOX")){




                            selectedFragment = Inbox_Tab_Fragment.newInstance();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_menu, selectedFragment);
                            transaction.addToBackStack("inbox_fragm");
                            transaction.commit();


                        }else if(items.getTitle().equals("SENT")) {


                            selectedFragment = ComingSoonFragment.newInstance();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_menu, selectedFragment);
                            transaction.commit();


                        }else if(items.getTitle().equals("FILTERED")) {

                            selectedFragment = ComingSoonFragment.newInstance();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_menu, selectedFragment);
                            transaction.commit();


                        }


                        //  select_document_type.setText(items.getTitle());


                        return false;
                    }
                });

                popupMenu.show();

            }
        });

        LookingForList();


        return view;
    }

    private void LookingForList() {

        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId", sessionManager.getRegId("userId"));
            System.out.println("aaaaaaaaaaaaadddd" + sessionManager.getRegId("userId"));


            Crop_Post.crop_posting(getActivity(), Urls.Get_RFQ_Model_Details, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("YourRequestttttttttttttttttt" + result);

                    try {

                        cropsListArray = result.getJSONArray("TractorModelMasterList");
                        tractorImplementsModelMasterList = result.getJSONArray("TractorImplementsModelMasterList");
                        tractorAccessoriesModelMasterList = result.getJSONArray("TractorAccessoriesModelMasterList");
                        harvesterModelMasterList = result.getJSONArray("HarvesterModelMasterList");
                        jCBRFQModelList = result.getJSONArray("JCBModelMasterList");
                        farmMachineryRFQModelList = result.getJSONArray("FarmMachineryModelMasterList");
                        fenceWireRFQModelList = result.getJSONArray("FenceWireModelMasterList");
                        tyreRFQModelList = result.getJSONArray("TyreModelMasterList");
                        miniTruckRFQModelList = result.getJSONArray("MiniTruckModelMasterList");
                        backhoeAttachmentRFQModelList = result.getJSONArray("BackhoeAttachmentModelMasterList");
                        powerTillerRFQModelList = result.getJSONArray("PowerTillerModelMasterList");


                        if(cropsListArray.length()==0 && tractorImplementsModelMasterList.length()==0 && tractorAccessoriesModelMasterList.length()==0 && harvesterModelMasterList.length()==0 && jCBRFQModelList.length()==0
                                && farmMachineryRFQModelList.length()==0 && fenceWireRFQModelList.length()==0 && tyreRFQModelList.length()==0 && miniTruckRFQModelList.length()==0
                                && backhoeAttachmentRFQModelList.length()==0 && powerTillerRFQModelList.length()==0 ){


                            selectedFragment = No_Request_Fragment.newInstance();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_menu, selectedFragment);
                            transaction.commit();


                        }else {

                            addingToarraylist(cropsListArray);
                            addingToarraylist(tractorImplementsModelMasterList);
                            addingToarraylist(tractorAccessoriesModelMasterList);
                            addingToarraylist(harvesterModelMasterList);
                            addingToarraylist(jCBRFQModelList);
                            addingToarraylist(farmMachineryRFQModelList);
                            addingToarraylist(fenceWireRFQModelList);
                            addingToarraylist(tyreRFQModelList);
                            addingToarraylist(miniTruckRFQModelList);
                            addingToarraylist(backhoeAttachmentRFQModelList);
                            addingToarraylist(powerTillerRFQModelList);
                        }

                        // Collections.reverse(newOrderBeansList);
                        farmadapter = new Inbox_Pending_Adapter(getActivity(),  newOrderBeansList);
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


    public void addingToarraylist(JSONArray selectedArray){
        for (int i = 0; i < selectedArray.length(); i++) {
            JSONObject jsonObject1 = null;


            try {
                jsonObject1 = selectedArray.getJSONObject(i);

                String model = jsonObject1.getString("Model");
                String model_image = jsonObject1.getString("ModelImage");
                String id = jsonObject1.getString("Id");
                String lookingfor_details = jsonObject1.getString("LookingForDetails");
                String brand_name = jsonObject1.getString("BrandName");



                String lookingfor_id = jsonObject1.getString("LookingForDetailsId");
                //  String model_id = jsonObject1.getString("ModelId");



// uncomment below later
                Get_RFQ_List_Bean crops = new Get_RFQ_List_Bean(model,lookingfor_details,brand_name,model_image,"Bangalore",id,lookingfor_id);
                newOrderBeansList.add(crops);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }}

}
