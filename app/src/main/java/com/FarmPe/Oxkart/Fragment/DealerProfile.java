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
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Oxkart.Activity.HomePage_With_Bottom_Navigation;
import com.FarmPe.Oxkart.Adapter.DealerProfAdapter;
import com.FarmPe.Oxkart.Bean.DealerProfBean;
import com.FarmPe.Oxkart.R;

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




        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(getArguments().getString("dealer_status").equals("Map_Dealer")){

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("map_fragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                }else if(getArguments().getString("dealer_status").equals("Home_Dealer")) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("home_page", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                  }else if(getArguments().getString("dealer_status").equals("Req_Book")) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("address_book", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                  }else if(getArguments().getString("dealer_status").equals("Add_Add_Dealer_Book")) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                }
        }
        });



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {



                    if(getArguments().getString("dealer_status").equals("Map_Dealer")){

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack ("map_fragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    }else if(getArguments().getString("dealer_status").equals("Home_Dealer")) {


                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack ("home_page", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    }else if(getArguments().getString("dealer_status").equals("Req_Book")) {

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("address_book", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    }else if(getArguments().getString("dealer_status").equals("Add_Add_Dealer_Book")) {

                     FragmentManager fm = getActivity().getSupportFragmentManager();
                     fm.popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                }

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
