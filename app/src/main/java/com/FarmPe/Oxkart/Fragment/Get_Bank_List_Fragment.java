package com.FarmPe.Oxkart.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.FarmPe.Oxkart.Activity.Status_bar_change_singleton;
import com.FarmPe.Oxkart.Adapter.BankAccount_Adapter;
import com.FarmPe.Oxkart.Bean.BankBean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Get_Bank_List_Fragment extends Fragment {

    public static ArrayList<BankBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static BankAccount_Adapter bankAccount_adapter;
    Fragment selectedFragment = null;
    TextView toolbar_title;
    public static String livestock_status;
    public static   LinearLayout back_feed,linearLayout,Continue;
    JSONArray Bank_list_array;
    SessionManager sessionManager;



    public static Get_Bank_List_Fragment newInstance() {
        Get_Bank_List_Fragment fragment = new Get_Bank_List_Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.soil_details_recy_layout, container, false);
        Status_bar_change_singleton.getInstance().color_change(getActivity());




        recyclerView=view.findViewById(R.id.recycler_what_looking);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        toolbar_title.setText("Bank Accounts");
        back_feed=view.findViewById(R.id.back_feed);
        linearLayout = view.findViewById(R.id.main_layout);
        Continue = view.findViewById(R.id.continuebtn);
        sessionManager = new SessionManager(getActivity());



        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("bank_status","Get_Add_Bank_Details");
                selectedFragment = Add_New_Bank_Account_Details_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("bankaccount");
                selectedFragment.setArguments(bundle);
                transaction.commit();

            }
        });


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("profile_setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("profile_setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }

                return false;
            }
        });


        newOrderBeansList.clear();
        bankAccount_adapter = new BankAccount_Adapter( getActivity(),newOrderBeansList);
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(bankAccount_adapter);


        //

        //volley
        //No automatic parsing
        //Caching Mechanism
        //Retry policy
        //inbuilt image loading support


        //Retrofit-
        //
        //Automatic JSON parsing
        //No Caching mechanism
        //no retry policy
        //no Image loading


//        BankBean img1=new BankBean("State Bank of India","Jagdish Kumar","30253918938","SBI0009876","Raja Rajeshwari Nagar","Bangaluru","");
//        newOrderBeansList.add(img1);
//
//        BankBean img2=new BankBean("Bank of Baroda","Jagdish Kumar","5325391836","BOB0000987","Raja Rajeshwari Nagar","Bangaluru","");
//        newOrderBeansList.add(img2);

        //string bulider ia a mutable where values can be changed ,it provides some methods like append insert delete reverse etc to modify string n its non synchronized,its not thread safe,faster
        //string buffer is  sync , thread safe,slower

        //string is immutable

        //string requets - is http request where the response parses string.


        //The Set interface provides an unordered collection of unique objects, i.e. Set doesn't allow duplicates,

        // while Map provides a data structure based on key-value pair and

        //its an architecture to manipulate and store group of objects


        Get_Bank_List();


        return view;
    }



    private void Get_Bank_List() {

        try{
            newOrderBeansList.clear();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));
            System.out.println("ghdfvghsfh" +sessionManager.getRegId("userId"));


            Crop_Post.crop_posting(getActivity(), Urls.Get_Bank_Details, jsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("hjgfhsfjksd" + result);
                    try{

                           Bank_list_array = result.getJSONArray("BankDetails");

                            for(int i=0;i<Bank_list_array.length();i++){
                            JSONObject jsonObject1 = Bank_list_array.getJSONObject(i);

                            BankBean bankBean= new BankBean(jsonObject1.getString("BankName"),jsonObject1.getString("BankBranchName"),jsonObject1.getString("SavingsBankAccountNumber"),jsonObject1.getString("IsIFSCCodeExist"),jsonObject1.getString("State"),jsonObject1.getString("District"),jsonObject1.getString("StateId"), jsonObject1.getString("DistrictId"),jsonObject1.getString("BankDetailsId"));
                            newOrderBeansList.add(bankBean);

                        }


                        bankAccount_adapter.notifyDataSetChanged();


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
