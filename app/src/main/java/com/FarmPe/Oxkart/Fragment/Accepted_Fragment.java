package com.FarmPe.Oxkart.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.FarmPe.Oxkart.Adapter.Accepted_Adapter;
import com.FarmPe.Oxkart.Bean.Request_Class_Home_Page_Bean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Accepted_Fragment extends Fragment  {


    private List<Request_Class_Home_Page_Bean> newOrderBeansList = new ArrayList<>();



    private RecyclerView recyclerView;

    LinearLayout recently_added_layout,toolbar_layout;
    Accepted_Adapter mAdapter;
    boolean doubleBackToExitPressedOnce = false;
    LinearLayout linearLayout;
    Fragment selectedFragment;

    SessionManager sessionManager;



    JSONArray model_list_array;
    public static JSONArray tractorImplementsModelMasterList = null;
    public static JSONArray tractorAccessoriesModelMasterList = null;
    public static JSONArray harvesterModelMasterList = null;
    public static JSONArray jCBRFQModelList = null;


    public static JSONArray FarmMachineryModelMasterList = null;
    public static JSONArray FenceWireModelMasterList = null;
    public static JSONArray TyreModelMasterList = null;
    public static JSONArray MiniTruckModelMasterList = null;
    public static JSONArray BackhoeAttachmentModelMasterList = null;
    public static JSONArray PowerTillerModelMasterList = null;




    public static Accepted_Fragment newInstance() {
        Accepted_Fragment fragment = new Accepted_Fragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mailbox_layout, container, false);




        //   Status_bar_change_singleton.getInstance().home_change(getActivity());

        recyclerView = view.findViewById(R.id.recycler_what_looking);
        recently_added_layout = view.findViewById(R.id.recently_added_layout);
        toolbar_layout = view.findViewById(R.id.toolbar_header);

        toolbar_layout.setVisibility(View.GONE);
        recently_added_layout.setVisibility(View.GONE);



        sessionManager = new SessionManager(getActivity());


        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        Get_RFQ_deatils();


        System.out.println("newOrderBeansListvsdvdv"+newOrderBeansList.size());
        mAdapter = new Accepted_Adapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(mAdapter);



        return view;
    }



    private void Get_RFQ_deatils() {


        try{


            JSONObject jsonObject = new JSONObject();

            jsonObject.put("CreatedBy",sessionManager.getRegId("userId"));

            Crop_Post.crop_posting(getActivity(), Urls.Get_Accepted_Request, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("fsdfdddsdfs" + result);


                    try{
                        newOrderBeansList.clear();
                        model_list_array = result.getJSONArray("TractorModelMasterList");
                        tractorImplementsModelMasterList = result.getJSONArray("TractorImplementsModelMasterList");
                        tractorAccessoriesModelMasterList = result.getJSONArray("TractorAccessoriesModelMasterList");
                        harvesterModelMasterList = result.getJSONArray("HarvesterModelMasterList");
                        jCBRFQModelList = result.getJSONArray("JCBModelMasterList");
                        FarmMachineryModelMasterList = result.getJSONArray("FarmMachineryModelMasterList");
                        FenceWireModelMasterList = result.getJSONArray("FenceWireModelMasterList");
                        TyreModelMasterList = result.getJSONArray("TyreModelMasterList");
                        MiniTruckModelMasterList = result.getJSONArray("MiniTruckModelMasterList");
                        BackhoeAttachmentModelMasterList = result.getJSONArray("BackhoeAttachmentModelMasterList");
                        PowerTillerModelMasterList = result.getJSONArray("PowerTillerModelMasterList");




                        for (int i = 0; i < model_list_array.length(); i++) {
                            JSONObject jsonObject1 = model_list_array.getJSONObject(i);

                            Request_Class_Home_Page_Bean rfq_bean = new Request_Class_Home_Page_Bean(jsonObject1.getString("LookingForDetails"), jsonObject1.getString("BrandName"), jsonObject1.getString("Model"), jsonObject1.getString("PurchaseTimeline"), jsonObject1.getString("CapturedLocation"), jsonObject1.getString("ModelImage"), jsonObject1.getString("Image1"),jsonObject1.getString("Id"),"",
                                    jsonObject1.getString("LookingForDetailsId"),jsonObject1.getBoolean("IsShortlisted"),jsonObject1.getString("UserName"),"",jsonObject1.getString("MaskedPhoneNo"),jsonObject1.getInt("LookingForInsurance"),jsonObject1.getInt("LookingForFinance"));
                            newOrderBeansList.add(rfq_bean);
                        }


                        for (int i = 0; i < tractorImplementsModelMasterList.length(); i++) {
                            JSONObject jsonObject1 = tractorImplementsModelMasterList.getJSONObject(i);

                            Request_Class_Home_Page_Bean rfq_bean = new Request_Class_Home_Page_Bean(jsonObject1.getString("LookingForDetails"), jsonObject1.getString("BrandName"), jsonObject1.getString("Model"), jsonObject1.getString("PurchaseTimeline"), jsonObject1.getString("CapturedLocation"), jsonObject1.getString("ModelImage"), jsonObject1.getString("Image1"),jsonObject1.getString("Id"),"",
                                    jsonObject1.getString("LookingForDetailsId"),jsonObject1.getBoolean("IsShortlisted"),jsonObject1.getString("UserName"),"",jsonObject1.getString("MaskedPhoneNo"),jsonObject1.getInt("LookingForInsurance"),jsonObject1.getInt("LookingForFinance"));
                            newOrderBeansList.add(rfq_bean);

                        }


                        for (int i = 0; i < tractorAccessoriesModelMasterList.length(); i++) {
                            JSONObject jsonObject1 = tractorAccessoriesModelMasterList.getJSONObject(i);

                            Request_Class_Home_Page_Bean rfq_bean = new Request_Class_Home_Page_Bean(jsonObject1.getString("LookingForDetails"), jsonObject1.getString("BrandName"), jsonObject1.getString("Model"), jsonObject1.getString("PurchaseTimeline"), jsonObject1.getString("CapturedLocation"), jsonObject1.getString("ModelImage"), jsonObject1.getString("Image1"),jsonObject1.getString("Id"),"",
                                    jsonObject1.getString("LookingForDetailsId"),jsonObject1.getBoolean("IsShortlisted"),jsonObject1.getString("UserName"),"",jsonObject1.getString("MaskedPhoneNo"),jsonObject1.getInt("LookingForInsurance"),jsonObject1.getInt("LookingForFinance"));
                            newOrderBeansList.add(rfq_bean);


                        }


                        for (int i = 0; i < harvesterModelMasterList.length(); i++) {
                            JSONObject jsonObject1 = harvesterModelMasterList.getJSONObject(i);

                            Request_Class_Home_Page_Bean rfq_bean = new Request_Class_Home_Page_Bean(jsonObject1.getString("LookingForDetails"), jsonObject1.getString("BrandName"), jsonObject1.getString("Model"), jsonObject1.getString("PurchaseTimeline"), jsonObject1.getString("CapturedLocation"), jsonObject1.getString("ModelImage"), jsonObject1.getString("Image1"),jsonObject1.getString("Id"),"",
                                    jsonObject1.getString("LookingForDetailsId"),jsonObject1.getBoolean("IsShortlisted"),jsonObject1.getString("UserName"),"",jsonObject1.getString("MaskedPhoneNo"),jsonObject1.getInt("LookingForInsurance"),jsonObject1.getInt("LookingForFinance"));
                            newOrderBeansList.add(rfq_bean);
                        }


                        for (int i = 0; i < jCBRFQModelList.length(); i++) {
                            JSONObject jsonObject1 = jCBRFQModelList.getJSONObject(i);
                            Request_Class_Home_Page_Bean rfq_bean = new Request_Class_Home_Page_Bean(jsonObject1.getString("LookingForDetails"), jsonObject1.getString("BrandName"), jsonObject1.getString("Model"), jsonObject1.getString("PurchaseTimeline"), jsonObject1.getString("CapturedLocation"), jsonObject1.getString("ModelImage"), jsonObject1.getString("Image1"),jsonObject1.getString("Id"),"",
                                    jsonObject1.getString("LookingForDetailsId"),jsonObject1.getBoolean("IsShortlisted"),jsonObject1.getString("UserName"),"",jsonObject1.getString("MaskedPhoneNo"),jsonObject1.getInt("LookingForInsurance"),jsonObject1.getInt("LookingForFinance"));
                            newOrderBeansList.add(rfq_bean);
                        }


                        for (int i = 0; i < FarmMachineryModelMasterList.length(); i++) {
                            JSONObject jsonObject1 = FarmMachineryModelMasterList.getJSONObject(i);
                            Request_Class_Home_Page_Bean rfq_bean = new Request_Class_Home_Page_Bean(jsonObject1.getString("LookingForDetails"), jsonObject1.getString("BrandName"), jsonObject1.getString("Model"), jsonObject1.getString("PurchaseTimeline"), jsonObject1.getString("CapturedLocation"), jsonObject1.getString("ModelImage"), jsonObject1.getString("Image1"),jsonObject1.getString("Id"),"",
                                    jsonObject1.getString("LookingForDetailsId"),jsonObject1.getBoolean("IsShortlisted"),jsonObject1.getString("UserName"),"",jsonObject1.getString("MaskedPhoneNo"),jsonObject1.getInt("LookingForInsurance"),jsonObject1.getInt("LookingForFinance"));
                            newOrderBeansList.add(rfq_bean);

                        }


                        for (int i = 0; i < FenceWireModelMasterList.length(); i++) {
                            JSONObject jsonObject1 = FenceWireModelMasterList.getJSONObject(i);
                            Request_Class_Home_Page_Bean rfq_bean = new Request_Class_Home_Page_Bean(jsonObject1.getString("LookingForDetails"), jsonObject1.getString("BrandName"), jsonObject1.getString("Model"), jsonObject1.getString("PurchaseTimeline"), jsonObject1.getString("CapturedLocation"), jsonObject1.getString("ModelImage"), jsonObject1.getString("Image1"),jsonObject1.getString("Id"),"",
                                    jsonObject1.getString("LookingForDetailsId"),jsonObject1.getBoolean("IsShortlisted"),jsonObject1.getString("UserName"),"",jsonObject1.getString("MaskedPhoneNo"),jsonObject1.getInt("LookingForInsurance"),jsonObject1.getInt("LookingForFinance"));
                            newOrderBeansList.add(rfq_bean);

                        }


                        for (int i = 0; i < TyreModelMasterList.length(); i++) {
                            JSONObject jsonObject1 = TyreModelMasterList.getJSONObject(i);
                            Request_Class_Home_Page_Bean rfq_bean = new Request_Class_Home_Page_Bean(jsonObject1.getString("LookingForDetails"), jsonObject1.getString("BrandName"), jsonObject1.getString("Model"), jsonObject1.getString("PurchaseTimeline"), jsonObject1.getString("CapturedLocation"), jsonObject1.getString("ModelImage"), jsonObject1.getString("Image1"),jsonObject1.getString("Id"),"",
                                    jsonObject1.getString("LookingForDetailsId"),jsonObject1.getBoolean("IsShortlisted"),jsonObject1.getString("UserName"),"",jsonObject1.getString("MaskedPhoneNo"),jsonObject1.getInt("LookingForInsurance"),jsonObject1.getInt("LookingForFinance"));
                            newOrderBeansList.add(rfq_bean);
                        }


                        for (int i = 0; i < MiniTruckModelMasterList.length(); i++) {
                            JSONObject jsonObject1 = MiniTruckModelMasterList.getJSONObject(i);
                            Request_Class_Home_Page_Bean rfq_bean = new Request_Class_Home_Page_Bean(jsonObject1.getString("LookingForDetails"), jsonObject1.getString("BrandName"), jsonObject1.getString("Model"), jsonObject1.getString("PurchaseTimeline"), jsonObject1.getString("CapturedLocation"), jsonObject1.getString("ModelImage"), jsonObject1.getString("Image1"),jsonObject1.getString("Id"),"",
                                    jsonObject1.getString("LookingForDetailsId"),jsonObject1.getBoolean("IsShortlisted"),jsonObject1.getString("UserName"),"",jsonObject1.getString("MaskedPhoneNo"),jsonObject1.getInt("LookingForInsurance"),jsonObject1.getInt("LookingForFinance"));
                            newOrderBeansList.add(rfq_bean);

                        }

                        for (int i = 0; i < BackhoeAttachmentModelMasterList.length(); i++) {
                            JSONObject jsonObject1 = BackhoeAttachmentModelMasterList.getJSONObject(i);
                            Request_Class_Home_Page_Bean rfq_bean = new Request_Class_Home_Page_Bean(jsonObject1.getString("LookingForDetails"), jsonObject1.getString("BrandName"), jsonObject1.getString("Model"), jsonObject1.getString("PurchaseTimeline"), jsonObject1.getString("CapturedLocation"), jsonObject1.getString("ModelImage"), jsonObject1.getString("Image1"),jsonObject1.getString("Id"),"",
                                    jsonObject1.getString("LookingForDetailsId"),jsonObject1.getBoolean("IsShortlisted"),jsonObject1.getString("UserName"),"",jsonObject1.getString("MaskedPhoneNo"),jsonObject1.getInt("LookingForInsurance"),jsonObject1.getInt("LookingForFinance"));
                            newOrderBeansList.add(rfq_bean);

                        }


                        for (int i = 0; i < PowerTillerModelMasterList.length(); i++) {
                            JSONObject jsonObject1 = PowerTillerModelMasterList.getJSONObject(i);
                            Request_Class_Home_Page_Bean rfq_bean = new Request_Class_Home_Page_Bean(jsonObject1.getString("LookingForDetails"), jsonObject1.getString("BrandName"), jsonObject1.getString("Model"), jsonObject1.getString("PurchaseTimeline"), jsonObject1.getString("CapturedLocation"), jsonObject1.getString("ModelImage"), jsonObject1.getString("Image1"),jsonObject1.getString("Id"),"",
                                    jsonObject1.getString("LookingForDetailsId"),jsonObject1.getBoolean("IsShortlisted"),jsonObject1.getString("UserName"),"",jsonObject1.getString("MaskedPhoneNo"),jsonObject1.getInt("LookingForInsurance"),jsonObject1.getInt("LookingForFinance"));
                            newOrderBeansList.add(rfq_bean);


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
