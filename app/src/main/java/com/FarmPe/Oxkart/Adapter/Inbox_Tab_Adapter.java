package com.FarmPe.Oxkart.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.FarmPe.Oxkart.Fragment.Inbox_Pending_Fragment;
import com.FarmPe.Oxkart.Fragment.LookingForFragment;


public class Inbox_Tab_Adapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Inbox_Tab_Adapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {

            case 0:
               Inbox_Pending_Fragment tab1 = new Inbox_Pending_Fragment();
                return tab1;

            case 1:
                Inbox_Pending_Fragment tab2 = new Inbox_Pending_Fragment();
                return tab2;

            case 2:
                Inbox_Pending_Fragment tab3 = new Inbox_Pending_Fragment();
                return tab3;



            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}