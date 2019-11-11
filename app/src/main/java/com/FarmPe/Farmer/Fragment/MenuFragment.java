package com.FarmPe.Farmer.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.FarmPe.Farmer.R;


public class MenuFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    Fragment selectedFragment;
    public LinearLayout back;
    public static TabLayout tabLayout;
    ViewPager viewPager;
    ProgressDialog progressdialog;
    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comming_layout2, container, false);
        tabLayout =view.findViewById(R.id.tablayout);
        back = view.findViewById(R.id.back_feed);
        tabLayout.addTab(tabLayout.newTab().setText("PhonePe"));
        tabLayout.addTab(tabLayout.newTab().setText("Google Pay"));
        tabLayout.addTab(tabLayout.newTab().setText("BHIM UPI ID"));
        tabLayout.setOnTabSelectedListener(this);
        HistoryPager adapter = new HistoryPager(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        int limit = (adapter.getCount() > 1 ? adapter.getCount() - 1 : 1);
        viewPager =view.findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(limit);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fm.popBackStack ("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                    fm.popBackStack ("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });
        view.setFocusableInTouchMode(true);
        view.requestFocus();
     /*   Bundle bundle=null;
        if(bundle!=null) {
            if (getArguments().getString("status").equals("ORDER_LIST")) {
                viewPager.setCurrentItem(1);
            } else {
                viewPager.setCurrentItem(0);
            }
        }*/
        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

}

