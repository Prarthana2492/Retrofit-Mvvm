package com.FarmPe.Oxkart.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.Oxkart.Adapter.Inbox_Tab_Adapter;
import com.FarmPe.Oxkart.R;


public class Inbox_Tab_Fragment extends Fragment implements TabLayout.OnTabSelectedListener{


    TabLayout tabFavLayout;
    private ViewPager viewPager1;
    LinearLayout back_feed;
    Inbox_Tab_Adapter adapter;
    Fragment selectedFragment = null;


    public static Inbox_Tab_Fragment newInstance() {
        Inbox_Tab_Fragment fragment = new Inbox_Tab_Fragment();
        return fragment;
    }
    protected void changeTabsFont(TabLayout tabLayout) {

        // Logger.print("In change tab font");

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        //Logger.print("Tab count--->"+tabsCount);
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof AppCompatTextView) {
                    //Logger.print("In font");
                    //  Typeface type = Typeface.createFromAsset(getContext().getAssets(),"fonts/segoeui.ttf");
                    //Typeface type = Typeface.createFromAsset(getContext().getAssets(),"ubuntumedium.ttf");

                    TextView viewChild = (TextView) tabViewChild;
                    // viewChild.setTypeface(type);
                    viewChild.setAllCaps(false);
                    viewChild.setTextSize(100);

                }
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inbox_tab_layout, container, false);
        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        back_feed = view.findViewById(R.id.back_feed);

        // toolbar.setTitle("Favourites");
        //  toolbar.setNavigationIcon(R.drawable.ic_left_arrow);
        tabFavLayout = view.findViewById(R.id.simpleTabLayout_1);


        tabFavLayout.addTab(tabFavLayout.newTab().setText("Accepted"));
        tabFavLayout.addTab(tabFavLayout.newTab().setText("Declined"));
        /*tabFilterLayout1.addTab(tabFilterLayout.newTab().setText(R.string.tutors));
        tabFilterLayout1.addTab(tabFilterLayout.newTab().setText(R.string.hobby));
        //tabFilterLayout.addTab(tabFilterLayout.newTab().setText("activities"));
        tabFilterLayout.addTab(tabFilterLayout.newTab().setText("SpecialNeeds"));*/

        changeTabsFont(tabFavLayout);
        viewPager1 = view.findViewById(R.id.simpleViewPager_1);
        adapter = new Inbox_Tab_Adapter(getActivity().getSupportFragmentManager(), tabFavLayout.getTabCount());


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("farmpe_connectP_tab", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });

        //Adding adapter to pager

        //Adding onTabSelectedListener to swipe views
        viewPager1.setAdapter(adapter);

        viewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabFavLayout));
        tabFavLayout.setOnTabSelectedListener(this);



        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager1.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

