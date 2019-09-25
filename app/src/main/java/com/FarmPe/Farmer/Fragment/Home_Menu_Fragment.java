package com.FarmPe.Farmer.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Farmer.Adapter.Home_Page_Lookinfor_Adapter;

import com.FarmPe.Farmer.Adapter.Home_Page_Request_Adapter;
import com.FarmPe.Farmer.Bean.Request_Class_HomePage_Bean;
import com.FarmPe.Farmer.R;

import java.util.ArrayList;
import java.util.List;

public class Home_Menu_Fragment extends Fragment  {

    private List<Request_Class_HomePage_Bean> newOrderBeansList = new ArrayList<>();
    private List<Request_Class_HomePage_Bean> newOrderBeansList2 = new ArrayList<>();
    private RecyclerView recyclerView,recyclerView2;

    private Home_Page_Request_Adapter mAdapter;
  //  private Home_Page_Lookinfor_Adapter mAdapter2;
    boolean doubleBackToExitPressedOnce = false;
    LinearLayout linearLayout;
    Fragment selectedFragment;

    public static TabLayout tabLayout;
    private ViewPager viewPager;


    public static Home_Menu_Fragment newInstance() {
        Home_Menu_Fragment fragment = new Home_Menu_Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page_layout, container, false);
        recyclerView = view.findViewById(R.id.recycler_view1);
        linearLayout = view.findViewById(R.id.linearLayout);
       // upi_send=view.findViewById(R.id.upiSendMoney);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 4);


        mLayoutManager = new GridLayoutManager(getActivity(),4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };


        // GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        Request_Class_HomePage_Bean item1 = new Request_Class_HomePage_Bean("Lorem");
        newOrderBeansList.add(item1);
        newOrderBeansList.add(item1);
        newOrderBeansList.add(item1);
        newOrderBeansList.add(item1);
        newOrderBeansList.add(item1);
        newOrderBeansList.add(item1);
        newOrderBeansList.add(item1);
        newOrderBeansList.add(item1);
        newOrderBeansList.add(item1);
        newOrderBeansList.add(item1);
        newOrderBeansList.add(item1);
        newOrderBeansList.add(item1);

        System.out.println("newOrderBeansListvsdvdv"+newOrderBeansList.size());
        mAdapter = new Home_Page_Request_Adapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(mAdapter);
//
//        newOrderBeansList2.clear();
//      //  recyclerView2 = view.findViewById(R.id.recycler_view2);
//        // GridLayoutManager mLayoutManager2 = new GridLayoutManager(getActivity() ,4, GridLayoutManager.HORIZONTAL, false);
//        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//        recyclerView2.setItemAnimator(new DefaultItemAnimator());
//        Request_Class_HomePage_Bean item2 = new Request_Class_HomePage_Bean("Ipsum");
//        newOrderBeansList2.add(item2);
//        newOrderBeansList2.add(item2);
//        newOrderBeansList2.add(item2);
//        newOrderBeansList2.add(item2);
//        newOrderBeansList2.add(item2);
//        newOrderBeansList2.add(item2);

//        System.out.println("newOrderBeansListvsdvdv"+newOrderBeansList.size());
//        mAdapter2 = new Home_Page_Lookinfor_Adapter(getActivity(),newOrderBeansList2);
//        recyclerView2.setAdapter(mAdapter2);


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        // this.finishAffinity();

                        if (doubleBackToExitPressedOnce) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                            startActivity(intent);
                            getActivity().finish();
                            System.exit(0);
                        }

                        doubleBackToExitPressedOnce = true;
                        // Toast.makeText(getActivity().getApplicationContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
                        int duration = 1000;
                        Snackbar snackbar = Snackbar
                                .make(linearLayout,"Please Click Back To Exit", duration);
                        View snackbarView = snackbar.getView();
                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                        tv.setTextColor(Color.WHITE);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        } else {
                            tv.setGravity(Gravity.CENTER_HORIZONTAL);
                        }
                        snackbar.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                doubleBackToExitPressedOnce = false;
                            }
                        }, 3000);
                    }

                    return true;
                }
                return false;
            }
        });




        return view;
    }


}
