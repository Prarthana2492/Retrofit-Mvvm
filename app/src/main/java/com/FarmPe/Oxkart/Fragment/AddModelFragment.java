package com.FarmPe.Oxkart.Fragment;



import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.Oxkart.Activity.Status_bar_change_singleton;
import com.FarmPe.Oxkart.Adapter.AddBrandAdapter;

import com.FarmPe.Oxkart.Adapter.AddModelAdapter;
import com.FarmPe.Oxkart.Bean.ModelBean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class AddModelFragment extends Fragment {


    public static List<ModelBean> modelBeanArrayList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static AddModelAdapter farmadapter;
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



     ModelBean modelBean;
     Fragment selectedFragment = null;
     TextView toolbar_title,continue_button,sub_label,filter_text,toolbar_title1;
     LinearLayout back_feed;
     SessionManager sessionManager;
     public static LinearLayout linearLayout;
     ImageView b_arrow;
     public static String text_box;



    public static AddModelFragment newInstance() {
        AddModelFragment fragment = new AddModelFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_model_recy, container, false);



        Status_bar_change_singleton.getInstance().color_change(getActivity());
        recyclerView=view.findViewById(R.id.recycler_what_looking);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);
        continue_button=view.findViewById(R.id.continue_button);
        filter_text=view.findViewById(R.id.filter_text);
        linearLayout=view.findViewById(R.id.linearLayout);
        sub_label=view.findViewById(R.id.sub_label);
        sessionManager = new SessionManager(getActivity());

        text_box = getArguments().getString("status_home");


       /* toolbar_title.setText("Select Model");

        sub_label.setText("Do you know the model ? then select model of your choice else select \"Suggest Model\"");
*/

        b_arrow=view.findViewById(R.id.b_arrow);


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("third", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });






        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("third", FragmentManager.POP_BACK_STACK_INCLUSIVE);

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
                LinearLayout image = (LinearLayout) dialog.findViewById(R.id.close_popup);


                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });




        ModelList();
       // newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        modelBeanArrayList.clear();

        farmadapter = new AddModelAdapter(getActivity(),modelBeanArrayList);
                    recyclerView.setAdapter(farmadapter);


        return view;
    }


    private void ModelList() {

        try{

          JSONObject jsonObject = new JSONObject();

            jsonObject.put("CreatedBy",sessionManager.getRegId("userId"));
            jsonObject.put("LookingForDetailsId",AddBrandFragment.request_looking_id);
            jsonObject.put("BrandId",AddBrandAdapter.brandId);
            System.out.println("fgfggdfcxxg" + jsonObject);


            Crop_Post.crop_posting(getActivity(), Urls.Model_List, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("fgfggdfcxxg" + result);

                    try{

                        modelBeanArrayList.clear();
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



                        for(int i=0;i<model_list_array.length();i++){


                            JSONObject jsonObject1 = model_list_array.getJSONObject(i);
                            modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                            modelBeanArrayList.add(modelBean);

                        }

                        System.out.println("fgfggdfcxxggggggggggggggggggggggggggg" + tractorAccessoriesModelMasterList);


                        for(int i=0;i<tractorImplementsModelMasterList.length();i++){
                            JSONObject jsonObject1 = tractorImplementsModelMasterList.getJSONObject(i);
                            modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                            modelBeanArrayList.add(modelBean);


                        }   for(int i=0;i<tractorAccessoriesModelMasterList.length();i++){
                            JSONObject jsonObject1 = tractorAccessoriesModelMasterList.getJSONObject(i);
                            modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                            modelBeanArrayList.add(modelBean);



                        }    for(int i=0;i<harvesterModelMasterList.length();i++){
                             JSONObject jsonObject1 = harvesterModelMasterList.getJSONObject(i);
                             modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                             modelBeanArrayList.add(modelBean);



                        }       for(int i=0;i<jCBRFQModelList.length();i++){
                                JSONObject jsonObject1 = jCBRFQModelList.getJSONObject(i);
                                modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                                modelBeanArrayList.add(modelBean);




                        }   for(int i=0;i<FarmMachineryModelMasterList.length();i++){
                            JSONObject jsonObject1 = FarmMachineryModelMasterList.getJSONObject(i);
                            modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                            modelBeanArrayList.add(modelBean);



                       }
                                   for(int i=0;i<FenceWireModelMasterList.length();i++){
                                   JSONObject jsonObject1 = FenceWireModelMasterList.getJSONObject(i);
                                   modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                                   modelBeanArrayList.add(modelBean);

                        }
                                  for(int i=0;i<TyreModelMasterList.length();i++){
                                  JSONObject jsonObject1 = TyreModelMasterList.getJSONObject(i);
                                  modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                                  modelBeanArrayList.add(modelBean);

                        }

                                for(int i=0;i<MiniTruckModelMasterList.length();i++){
                                JSONObject jsonObject1 = MiniTruckModelMasterList.getJSONObject(i);
                                modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                                modelBeanArrayList.add(modelBean);

                        }

                                for(int i=0;i<BackhoeAttachmentModelMasterList.length();i++){
                                JSONObject jsonObject1 = BackhoeAttachmentModelMasterList.getJSONObject(i);
                                modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                                modelBeanArrayList.add(modelBean);

                        }

                                for(int i=0;i<PowerTillerModelMasterList.length();i++){
                                JSONObject jsonObject1 = PowerTillerModelMasterList.getJSONObject(i);
                                modelBean = new ModelBean(jsonObject1.getString("BrandName"),jsonObject1.getString("Model"),"","","","","","",jsonObject1.getString("ModelImage"),jsonObject1.getString("Brochure"),jsonObject1.getString("Id"),"",jsonObject1.getBoolean("IsShortlisted"));
                                modelBeanArrayList.add(modelBean);

                        }


                                farmadapter.notifyDataSetChanged();

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
