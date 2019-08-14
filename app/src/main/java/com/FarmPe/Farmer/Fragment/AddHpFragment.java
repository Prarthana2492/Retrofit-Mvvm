package com.FarmPe.Farmer.Fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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

import com.FarmPe.Farmer.Adapter.AddFirstAdapter;
import com.FarmPe.Farmer.Adapter.AddHpAdapter;
import com.FarmPe.Farmer.Adapter.AddModelAdapter;
import com.FarmPe.Farmer.Bean.AddTractorBean;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Volly_class.Login_post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddHpFragment extends Fragment {

    public static List<AddTractorBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static AddHpAdapter farmadapter;
    TextView toolbar_title,continue_button;
    LinearLayout back_feed,linearLayout;
    Fragment selectedFragment;

ImageView b_arrow;
    public static AddHpFragment newInstance() {
        AddHpFragment fragment = new AddHpFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_first_recy, container, false);
        recyclerView=view.findViewById(R.id.recycler_what_looking);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);
        continue_button=view.findViewById(R.id.continue_button);
        linearLayout=view.findViewById(R.id.linearLayout);
        toolbar_title.setText("Select HP");
        b_arrow=view.findViewById(R.id.b_arrow);
        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b_arrow.setImageDrawable(getResources().getDrawable(R.drawable.ic_whitecancel));
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("second", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("second", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });


        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(AddHpAdapter.hp_model == null){
                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Please choose any option", duration);
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

                }else{
                 AddModelAdapter.tractor_id =null;
                    selectedFragment = AddModelFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.frame_layout, selectedFragment);
                    transaction.addToBackStack("third");
                    transaction.commit();
                }


            }
        });


        HpList();

        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        return view;
    }

    private void HpList() {

        try {
            newOrderBeansList.clear();

            JSONObject userRequestjsonObject = new JSONObject();
             userRequestjsonObject.put("LookingForDetailsId", AddFirstAdapter.looinkgId);



            System.out.println("postObj"+userRequestjsonObject.toString());

            Login_post.login_posting(getActivity(), Urls.GetHPList,userRequestjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);
                    JSONArray cropsListArray=null;
                    try {
                        cropsListArray=result.getJSONArray("HPList");
                        System.out.println("e     e e ddd"+cropsListArray.length());
                        for (int i=0;i<cropsListArray.length();i++){
                            JSONObject jsonObject1=cropsListArray.getJSONObject(i);

                            String horsepower=jsonObject1.getString("HorsePowerRange");
                            String image=jsonObject1.getString("HorsePowerIcon");

                            String id=jsonObject1.getString("Id");


                            AddTractorBean hp = new AddTractorBean(image, horsepower,id,false);
                            newOrderBeansList.add(hp);


                        }
                        farmadapter=new AddHpAdapter(getActivity(),newOrderBeansList);
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
