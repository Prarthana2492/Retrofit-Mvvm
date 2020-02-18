package com.FarmPe.Oxkart.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.FarmPe.Oxkart.Adapter.NotificationAdapter;
import com.FarmPe.Oxkart.Bean.Notification_Home_Bean;
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



public class NotificationFragment extends Fragment {

    public static List<Notification_Home_Bean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static NotificationAdapter farmadapter;
    TextView toolbar_title;
    ImageView back_arrw;
    LinearLayout back_feed,no_notifitn_added;
    Notification_Home_Bean notification_home_bean;
    Fragment selectedFragment;
    SessionManager sessionManager;
    JSONObject lngObject;
    JSONArray notifn_array;
    String location;


    public static NotificationFragment newInstance() {
        NotificationFragment fragment = new NotificationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_recy, container, false);
        Status_bar_change_singleton.getInstance().color_change(getActivity());

        recyclerView=view.findViewById(R.id.recycler_noti);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);
        back_arrw=view.findViewById(R.id.back_arrw);
        no_notifitn_added=view.findViewById(R.id.no_notifitn_added);

        sessionManager = new SessionManager(getActivity());


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {



                    selectedFragment = Home_Menu_Fragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_menu, selectedFragment);
                    transaction.commit();

                    return true;
                }
                return false;
            }
        });



        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



              //  back_arrw.setImageDrawable(getResources().getDrawable(R.drawable.ic_whitecancel));
                selectedFragment = Home_Menu_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.commit();

            }
        });


        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        farmadapter=new NotificationAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(farmadapter);

        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        try{

            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("ToUserId",sessionManager.getRegId("userId"));


            Crop_Post.crop_posting(getActivity(), Urls.Notification_HomePage, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("sdffdffds" + result);

                    try{
                        newOrderBeansList.clear();
                        notifn_array = result.getJSONArray("NotificationList");

                        for(int i=0;i<notifn_array.length();i++){
                            JSONObject jsonObject1 = notifn_array.getJSONObject(i);
                            notification_home_bean = new Notification_Home_Bean(jsonObject1.getString("NotificationText"),jsonObject1.getString("Id"));
                            newOrderBeansList.add(notification_home_bean);

                        }

                        if(notifn_array.length()==0){

                            no_notifitn_added.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);

                        }else{

                            no_notifitn_added.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
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








        return view;
    }



}
