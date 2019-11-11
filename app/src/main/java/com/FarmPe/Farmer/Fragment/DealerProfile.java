package com.FarmPe.Farmer.Fragment;

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
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Farmer.Activity.HomePage_With_Bottom_Navigation;
import com.FarmPe.Farmer.Adapter.DealerProfAdapter;
import com.FarmPe.Farmer.Bean.DealerProfBean;
import com.FarmPe.Farmer.R;

import java.util.ArrayList;
import java.util.List;


public class DealerProfile extends Fragment {
    RecyclerView recycler_sales;
    public static List<DealerProfBean> newOrderBeansList = new ArrayList<>();
    DealerProfAdapter madapter;
    LinearLayout back_feed;
    public static TextView status_text;
    RecyclerView recyclerView;

    public static DealerProfile newInstance() {
        DealerProfile fragment = new DealerProfile();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dealer_prof_recy, container, false);
        recycler_sales=view.findViewById(R.id.recycler_dealer_prof);
        back_feed=view.findViewById(R.id.back_feed);
     //   status_text=view.findViewById(R.id.status_text);
        HomePage_With_Bottom_Navigation.linear_bottom.setVisibility(View.GONE);

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("home_page", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });




        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("home_page", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }
                return false;
            }
        });




        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager3 = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recycler_sales.setLayoutManager(mLayoutManager3);
        recycler_sales.setItemAnimator(new DefaultItemAnimator());

        DealerProfBean bean=new DealerProfBean(R.drawable.dealer_prof,"Vinayaka Motors","Tractor ","Farm Trucks Dealer","Rajarajeshwari Nagar, Bangalore");
        DealerProfBean bean1=new DealerProfBean(R.drawable.tractor_red,"Mahindra Motors","Tractor ","Farm Trucks Dealer","Jayanagar, Bangalore");
        newOrderBeansList.add(bean);
        newOrderBeansList.add(bean1);
        newOrderBeansList.add(bean);
        newOrderBeansList.add(bean1);
        newOrderBeansList.add(bean);
        newOrderBeansList.add(bean1);
        newOrderBeansList.add(bean);
        newOrderBeansList.add(bean1);

        newOrderBeansList.add(bean);
        madapter=new DealerProfAdapter(getActivity(),newOrderBeansList);
        recycler_sales.setAdapter(madapter);



        return view;
    }



}
