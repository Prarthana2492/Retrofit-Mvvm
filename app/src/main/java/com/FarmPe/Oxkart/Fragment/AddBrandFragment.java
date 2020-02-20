package com.FarmPe.Oxkart.Fragment;


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

import com.FarmPe.Oxkart.Activity.HomePage_With_Bottom_Navigation;
import com.FarmPe.Oxkart.Activity.Status_bar_change_singleton;
import com.FarmPe.Oxkart.Adapter.AddBrandAdapter;
import com.FarmPe.Oxkart.Adapter.AddFirstAdapter;
import com.FarmPe.Oxkart.Bean.AddTractorBean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Login_post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;




public class AddBrandFragment extends Fragment {


    public static List<AddTractorBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static AddBrandAdapter farmadapter;
    Fragment selectedFragment = null;
    TextView toolbar_title,sub_label;
    LinearLayout back_feed,linearLayout;
    TextView continue_button;
    ImageView b_arrow;
    public static String price;
    public static String request_looking_id;


    public static AddBrandFragment newInstance() {
        AddBrandFragment fragment = new AddBrandFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.brand_recy_layout, container, false);

        Status_bar_change_singleton.getInstance().color_change(getActivity());


        recyclerView=view.findViewById(R.id.recycler_what_looking);
        toolbar_title=view.findViewById(R.id.toolbar_title);

        sub_label = view.findViewById(R.id.sub_label);
        continue_button = view.findViewById(R.id.continue_button);
        linearLayout = view.findViewById(R.id.linearLayout);
        b_arrow = view.findViewById(R.id.b_arrow);
        back_feed=view.findViewById(R.id.back_feed);


        price = getArguments().getString("status_home");

       // toolbar_title.setText("Select Brand");

     //   sub_label.setText("let us know the manufacturer/brand name you are interested to buy");

        request_looking_id = getArguments().getString("request_status");

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // b_arrow.setImageDrawable(getResources().getDrawable(R.drawable.ic_whitecancel));

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("req_price", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("req_price", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });



//        continue_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(AddBrandAdapter.brandId == null){
//
//                    Snackbar snackbar = Snackbar
//                            .make(linearLayout, "Please choose any option", Snackbar.LENGTH_LONG);
//                    View snackbarView = snackbar.getView();
//                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
//                    tv.setTextColor(Color.WHITE);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                    } else {
//                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                    }
//
//                    snackbar.show();
//                  /*  int duration = 1000;
//                    Snackbar snackbar = Snackbar
//                            .make(linearLayout, "Please choose any option", duration);
//                    View snackbarView = snackbar.getView();
//                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
//                    tv.setTextColor(Color.WHITE);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                    } else {
//                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                    }
//
//                    snackbar.show();*/
//
//                }else{
//                    AddHpAdapter.hp_model = null;
//                    selectedFragment = AddHpFragment.newInstance();
//                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    transaction.add(R.id.frame_layout, selectedFragment);
//                    transaction.addToBackStack("second");
//                    transaction.commit();
//
//                }
//
//            }
//        });
//





        BrandList();
        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }


    private void BrandList() {
        try {
            newOrderBeansList.clear();
              JSONObject userRequestjsonObject = new JSONObject();
              userRequestjsonObject.put("LookingForDetailsId", request_looking_id);


            System.out.println("sdfsdfsdf" + AddFirstAdapter.looinkgId );
            JSONObject postjsonObject = new JSONObject();
            System.out.println("postObj"+userRequestjsonObject.toString());

            Login_post.login_posting(getActivity(), Urls.GetBrandList,userRequestjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);
                    JSONArray cropsListArray=null;


                    try {
                        cropsListArray=result.getJSONArray("BrandList");
                        System.out.println("ee e ddd"+cropsListArray.length());

                        for (int i=0;i<cropsListArray.length();i++){

                            JSONObject jsonObject1=cropsListArray.getJSONObject(i);
                            String brand_name=jsonObject1.getString("BrandName");
                            String id=jsonObject1.getString("Id");
                            String BrandIcon=jsonObject1.getString("BrandIcon");

                            AddTractorBean crops = new AddTractorBean(BrandIcon, brand_name,id,false);
                            newOrderBeansList.add(crops);

                        }

                         farmadapter=new AddBrandAdapter(getActivity(),newOrderBeansList);
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
