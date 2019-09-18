package com.FarmPe.Farmer.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
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
import com.FarmPe.Farmer.Adapter.AddBrandAdapter;
import com.FarmPe.Farmer.Adapter.AddFirstAdapter;

import com.FarmPe.Farmer.Adapter.AddModelAdapter;
import com.FarmPe.Farmer.Adapter.Request_Favorite_Adapter;
import com.FarmPe.Farmer.Bean.AddTractorBean;
import com.FarmPe.Farmer.Bean.ModelBean;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Volly_class.Crop_Post;
import com.FarmPe.Farmer.Volly_class.Login_post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;



public class Request_Favorite_Fragment extends Fragment {

    public static List<ModelBean> modelBeanArrayList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static Request_Favorite_Adapter farmadapter;
    JSONArray model_list_array;
    public static LinearLayout linearLayout;

    public static JSONArray tractorImplementsModelMasterList = null;
    public static JSONArray tractorAccessoriesModelMasterList = null;
    public static JSONArray harvesterModelMasterList = null;
    public static JSONArray jCBRFQModelList = null;
    ModelBean modelBean;
    Fragment selectedFragment = null;
    TextView toolbar_title,continue_button,sub_label,filter_text;
    LinearLayout back_feed,recently_added_layout,no_favo_added;
    ImageView b_arrow;
    SessionManager sessionManager;


    public static Request_Favorite_Fragment newInstance() {
        Request_Favorite_Fragment fragment = new Request_Favorite_Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_model_recy, container, false);
        recyclerView=view.findViewById(R.id.recycler_what_looking);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);
        linearLayout=view.findViewById(R.id.linearLayout);
        continue_button=view.findViewById(R.id.continue_button);
        filter_text=view.findViewById(R.id.filter_text);
        sub_label=view.findViewById(R.id.sub_label);
        no_favo_added=view.findViewById(R.id.no_favo_added);
        recently_added_layout=view.findViewById(R.id.recently_added_layout);

        sessionManager = new SessionManager(getActivity());

        toolbar_title.setText("Favorites");
        recently_added_layout.setVisibility(View.GONE);


        b_arrow=view.findViewById(R.id.b_arrow);
        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("req_fav", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("req_fav", FragmentManager.POP_BACK_STACK_INCLUSIVE);
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


        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        farmadapter=new Request_Favorite_Adapter(getActivity(),modelBeanArrayList);
        recyclerView.setAdapter(farmadapter);


        try{


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("CreatedBy",sessionManager.getRegId("userId"));
            System.out.println("fdsfsdf" + sessionManager.getRegId("userId"));

            Crop_Post.crop_posting(getActivity(), Urls.Get_Favorites, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("fsdfdddsdfs" + result);


                    try{
                        modelBeanArrayList.clear();


                        model_list_array = result.getJSONArray("TractorModelMasterList");

                        tractorImplementsModelMasterList = result.getJSONArray("TractorImplementsModelMasterList");
                        tractorAccessoriesModelMasterList = result.getJSONArray("TractorAccessoriesModelMasterList");
                        harvesterModelMasterList = result.getJSONArray("HarvesterModelMasterList");
                        jCBRFQModelList = result.getJSONArray("JCBModelMasterList");


                        if(model_list_array.length()==0 && tractorImplementsModelMasterList.length()==0 && tractorAccessoriesModelMasterList.length()==0 && harvesterModelMasterList.length()==0 && jCBRFQModelList.length()==0){

                            no_favo_added.setVisibility(View.VISIBLE);


                        }else {


                            for (int i = 0; i < model_list_array.length(); i++) {
                                JSONObject jsonObject1 = model_list_array.getJSONObject(i);
                                modelBean = new ModelBean(jsonObject1.getString("BrandName"), jsonObject1.getString("Model"), "", "","", "", "", "", jsonObject1.getString("ModelImage"), jsonObject1.getString("Brochure"), jsonObject1.getString("Id"),jsonObject1.getString("LookingForDetailsId"),jsonObject1.getBoolean("IsShortlisted"));
                                modelBeanArrayList.add(modelBean);

                            }

                            for (int i = 0; i < tractorImplementsModelMasterList.length(); i++) {
                                JSONObject jsonObject1 = tractorImplementsModelMasterList.getJSONObject(i);
                                modelBean = new ModelBean(jsonObject1.getString("BrandName"), jsonObject1.getString("Model"),"","", "","", "","" , jsonObject1.getString("ModelImage"), jsonObject1.getString("Brochure"), jsonObject1.getString("Id"),jsonObject1.getString("LookingForDetailsId"),jsonObject1.getBoolean("IsShortlisted"));
                                modelBeanArrayList.add(modelBean);

                            }
                            for (int i = 0; i < tractorAccessoriesModelMasterList.length(); i++) {
                                JSONObject jsonObject1 = tractorAccessoriesModelMasterList.getJSONObject(i);
                                modelBean = new ModelBean(jsonObject1.getString("BrandName"), jsonObject1.getString("Model"),"","","","","","" ,jsonObject1.getString("ModelImage"), jsonObject1.getString("Brochure"), jsonObject1.getString("Id"),jsonObject1.getString("LookingForDetailsId"),jsonObject1.getBoolean("IsShortlisted"));
                                modelBeanArrayList.add(modelBean);


                            }
                            for (int i = 0; i < harvesterModelMasterList.length(); i++) {
                                JSONObject jsonObject1 = harvesterModelMasterList.getJSONObject(i);
                                modelBean = new ModelBean(jsonObject1.getString("BrandName"), jsonObject1.getString("Model"),"", "", "", "", "", "", jsonObject1.getString("ModelImage"), jsonObject1.getString("Brochure"), jsonObject1.getString("Id"),jsonObject1.getString("LookingForDetailsId"),jsonObject1.getBoolean("IsShortlisted"));
                                modelBeanArrayList.add(modelBean);

                            }
                            for (int i = 0; i < jCBRFQModelList.length(); i++) {
                                JSONObject jsonObject1 = jCBRFQModelList.getJSONObject(i);
                                modelBean = new ModelBean(jsonObject1.getString("BrandName"), jsonObject1.getString("Model"), "", "", "", "", "", "", jsonObject1.getString("ModelImage"), jsonObject1.getString("Brochure"), jsonObject1.getString("Id"),jsonObject1.getString("LookingForDetailsId"),jsonObject1.getBoolean("IsShortlisted"));
                                modelBeanArrayList.add(modelBean);

                            }

                            farmadapter.notifyDataSetChanged();

                        }


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }


        return view;
    }




}
