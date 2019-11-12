package com.FarmPe.Farmer.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.FarmPe.Farmer.R;



public class Addbhimfragment extends Fragment{
    String status, message;
    TextView confirm;
    EditText upiid;
    LinearLayout back;
    Fragment selectedFragment;
    Activity activity;
    String numberstr;

    public static Addbhimfragment newInstance() {
        Addbhimfragment fragment = new Addbhimfragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_layout, container, false);
        back = view.findViewById(R.id.back_feed);
        confirm = view.findViewById(R.id.continue_4);
       /* MainActivity.linearlayout.setVisibility(View.GONE);
        FragmentB.linearlayouttest.setVisibility(View.GONE);*/
        upiid = view.findViewById(R.id.mobile_no);
        final String stramount= System.currentTimeMillis()+"abcdefgh";
        numberstr = getArguments().getString("numberid");
        upiid.setText(numberstr);
        //Create instance of EasyUpiPayment
        //"8904845510@upi"
        System.out.println("fasfasfa"+upiid.getText().toString().trim());
       /* easyUpiPayment= new EasyUpiPayment.Builder()
                .with(getActivity())
                .setPayeeVpa("9492737031@upi")
                .setPayeeName("himabindhu")
                .setTransactionId(stramount)
                .setTransactionRefId("123456abcd")
                .setDescription("For Test")
                .setAmount("1.00")
                .build();*/
       // easyUpiPayment.setPaymentStatusListener(this);
        System.out.println("fasfasfa"+upiid.getText().toString().trim());
        //Proceed for Payment on click

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String upiId = upiid.getText().toString();
              if(upiId.contains("@")){

                  Bundle bundle = new Bundle();
                    bundle.putString("edttext", upiid.getText().toString());
                    System.out.println("hjhkh" + bundle);
                    selectedFragment = AddMoneyFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_menu, selectedFragment);
                    selectedFragment.setArguments(bundle);
                    transaction.addToBackStack("yu_ads_frg");
                    transaction.commit();

               }
              else

                  {

                    Toast.makeText(getActivity(),"Payee VPA address should be valid (For e.g. example@upi and example@ybl)", Toast.LENGTH_LONG).show();
                  }
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                   /* Bundle bundle = new Bundle();
                    bundle.putString("status", "ORDER_LIST");
                    System.out.println("hjhkh" + bundle);*/
                    selectedFragment = MenuFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                  //  selectedFragment.setArguments(bundle);
                    transaction.addToBackStack("addcontacts");
                    transaction.commit();
               /*   FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("addcontacts", FragmentManager.POP_BACK_STACK_INCLUSIVE);
             */       return true;
                }
                return false;
            }
        });

//   java.lang.ClassCastException: com.example.easyupipayment.MainActivity cannot be cast to com.shreyaspatil.EasyUpiPayment.listener.PaymentStatusListener
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("status", "ORDER_LIST");
                System.out.println("hjhkh" + bundle);
                selectedFragment = MenuFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("addcontacts");
                transaction.commit();
               /* FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("addcontacts", FragmentManager.POP_BACK_STACK_INCLUSIVE);*/
            }
        });
        return view;
    }

}