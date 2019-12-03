package com.FarmPe.Farmer.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


import com.FarmPe.Farmer.Bean.ContactVO;
import com.FarmPe.Farmer.R;

import java.util.ArrayList;

public class FragmentB extends Fragment{

    private Button payButton;
    RecyclerView recyclerView;
    Fragment selectedFragment;
   public static LinearLayout linearlayouttest;
    public  static final int RequestPermissionCode  = 1 ;
    ArrayList<ContactVO> newmember_List = new ArrayList<>();
    ContactVO contactVO;
  //  public static List<ContactVO> newmember_List = new ArrayList<>();

     public static FragmentB newInstance() {
      FragmentB fragment = new FragmentB();
      return fragment;
  }

    @Nullable
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        payButton = view.findViewById(R.id.button_pay);
        linearlayouttest = view.findViewById(R.id.test);
       // String[] items = getResources().getStringArray(R.array.PhonePe);
        String stramount= System.currentTimeMillis()+"abcdefgh";
        //Create instance of EasyUpiPayment
        //Proceed for Payment on click


       payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment = Addbhimfragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.commit();
               // easyUpiPayment.startPayment();
            }
        });
        return view;

    }

}