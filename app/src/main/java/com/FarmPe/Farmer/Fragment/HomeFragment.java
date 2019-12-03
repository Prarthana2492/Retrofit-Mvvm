//package com.FarmPe.Farmer.Fragment;
//
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.annotation.RequiresApi;
//import android.support.design.widget.Snackbar;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.Gravity;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//
//import com.FarmPe.Farmer.Adapter.LoanMainAdapter;
//import com.FarmPe.Farmer.Bean.ListBean;
//import com.FarmPe.Farmer.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class HomeFragment extends Fragment {
//    RecyclerView recyclerView,recyclerView_looking;
//    public static List<ListBean> newOrderBeansList = new ArrayList<>();
//    public static List<ListBean> newOrderBeansList1 = new ArrayList<>();
//    LoanMainAdapter madapter;
//    LookingAdapter madapter1;
//    Fragment selectedFragment;
//    ImageView home_img,time_img,noti_img,user_img;
//    LinearLayout coordinate_layout,track_app,ask_nandi,notification,my_search,find_bank_off,loan_repay,add_loan_acc,profile;
//    boolean doubleBackToExitPressedOnce = false;
//
//    public static int morecount,morecount_looking=0;
//
//    public static HomeFragment newInstance() {
//        HomeFragment fragment = new HomeFragment();
//        return fragment;
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.home_page1, container, false);
//        recyclerView=view.findViewById(R.id.recycler_view_loan);
//        recyclerView_looking=view.findViewById(R.id.recycler_view_looking);
//        coordinate_layout=view.findViewById(R.id.coordinate_layout);
//        ask_nandi=view.findViewById(R.id.ask_nandi);
//        track_app=view.findViewById(R.id.track_app);
//        notification=view.findViewById(R.id.notification);
//        my_search=view.findViewById(R.id.my_search);
//        find_bank_off=view.findViewById(R.id.find_bank_off);
//        loan_repay=view.findViewById(R.id.loan_repay);
//        add_loan_acc=view.findViewById(R.id.add_loan_acc);
//        profile=view.findViewById(R.id.profile);
//        home_img=view.findViewById(R.id.home_img);
//        time_img=view.findViewById(R.id.track_img);
//        noti_img=view.findViewById(R.id.noti_img);
//        user_img=view.findViewById(R.id.prof_img);
//        Window window = getActivity().getWindow();
//
//
//        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.colorPrimaryDark));
//
//
//
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//
//
//        view.setFocusableInTouchMode(true);
//        view.requestFocus();
//        view.setOnKeyListener(new View.OnKeyListener() {
//
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
//                    if (doubleBackToExitPressedOnce) {
//
//                        Intent intent1 = new Intent(Intent.ACTION_MAIN);
//                        intent1.addCategory(Intent.CATEGORY_HOME);
//                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
//                        startActivity(intent1);
//                        getActivity().finish();                   }
//                   // System.exit(0);
//
//                   // home_img.setImageResource(R.drawable.ic_home_green);
//
//                    doubleBackToExitPressedOnce = true;
//
//                    Snackbar snackbar = Snackbar
//                            .make(coordinate_layout,"Please click back again to exit", Snackbar.LENGTH_LONG);
//                    View snackbarView = snackbar.getView();
//                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
//                    tv.setTextColor(Color.WHITE);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                    } else {
//                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                    }
//                    snackbar.show();
//                    //   Toast.makeText(getApplicationContext(), toast_click_back, Toast.LENGTH_SHORT).show();
//                    new Handler().postDelayed(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            doubleBackToExitPressedOnce=false;
//                        }
//                    }, 3000);
//                }
//                return true;
//            }
//        });
//
//        newOrderBeansList.clear();
//        GridLayoutManager mLayoutManager3 = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(mLayoutManager3);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        ListBean bean=new ListBean("Insurance",1,R.drawable.insurance,1,1);
//        newOrderBeansList.add(bean);
//        ListBean bean1=new ListBean("Tractor Loan",1,R.drawable.tractor_green,1,1);
//        newOrderBeansList.add(bean1);
//        ListBean bean2=new ListBean("Crop Loan",1,R.drawable.croploan,1,1);
//        newOrderBeansList.add(bean2);
//        ListBean bean3=new ListBean("Kisan Credit Card",1,R.drawable.kisancreditcard,1,1);
//        newOrderBeansList.add(bean3);
//        ListBean bean4=new ListBean("Produce Marketing",1,R.drawable.producemarketing,1,1);
//        newOrderBeansList.add(bean4);
//        ListBean bean5=new ListBean("Assets Backed Loan",1,R.drawable.homeloan,1,1);
//        newOrderBeansList.add(bean5);
//        ListBean bean6=new ListBean("Dairy Loan",1,R.drawable.dairyfarm,1,1);
//        newOrderBeansList.add(bean6);
//        ListBean bean7=new ListBean("Poultry Loan",1,R.drawable.polutry,1,1);
//        newOrderBeansList.add(bean7);
//        ListBean bean8=new ListBean("Combine Harvester",1,R.drawable.power_tiller,1,1);
//        newOrderBeansList.add(bean8);
//        ListBean bean9=new ListBean("Power Tiller",1,R.drawable.power_tiller,1,1);
//        newOrderBeansList.add(bean9);
//        ListBean bean10=new ListBean("Pump Set",1,R.drawable.pumpset,1,1);
//        newOrderBeansList.add(bean10);
//          ListBean bean11=new ListBean("Poultry Loan",1,R.drawable.polutry,1,1);
//        newOrderBeansList.add(bean11);
//         ListBean bean12=new ListBean("Combine Harvester",1,R.drawable.power_tiller,1,1);
//        newOrderBeansList.add(bean12);
//        ListBean bean13=new ListBean("Power Tiller",1,R.drawable.power_tiller,1,1);
//        newOrderBeansList.add(bean13);
//
//   /*      ListBean bean14=new ListBean("Pump Set",1,R.drawable.pumpset);
//        newOrderBeansList.add(bean14);*/
//
//        if(newOrderBeansList.size()>12)
//        {
//            madapter = new LoanMainAdapter(getActivity(), newOrderBeansList.subList(0,11));
//            recyclerView.setAdapter(madapter);
//            morecount = newOrderBeansList.size()-11;
//            System.out.println("morecount "+morecount);
//        }
//
//        else {
//            madapter = new LoanMainAdapter(getActivity(), newOrderBeansList);
//            recyclerView.setAdapter(madapter);
//        }
//
//        newOrderBeansList1.clear();
//
//        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
//        recyclerView_looking.setLayoutManager(mLayoutManager);
//        recyclerView_looking.setItemAnimator(new DefaultItemAnimator());
//
//
//        ListBean looking=new ListBean("Bank Account",1,R.drawable.agronomy,1,1);
//        newOrderBeansList1.add(looking);
//
//        ListBean looking1=new ListBean("Update KYC",1,R.drawable.analysis,1,1);
//
//        newOrderBeansList1.add(looking1);
//        ListBean looking2=new ListBean("History",1,R.drawable.certificate,1,1);
//
//
//        newOrderBeansList1.add(looking2);
//        newOrderBeansList1.add(looking2);
//        newOrderBeansList1.add(looking2);
//
//
//        //harman
//
//        if(newOrderBeansList1.size()>4)
//        {
//
//            madapter1 = new LookingAdapter(getActivity(), newOrderBeansList1.subList(0,3));
//            recyclerView_looking.setAdapter(madapter1);
//            morecount_looking = newOrderBeansList1.size()-3;
//            System.out.println("morecount "+morecount);
//
//
//
//
//
//
//
//
//
//        }
//
//        else {
//
//            madapter1 = new LookingAdapter(getActivity(), newOrderBeansList1);
//            recyclerView_looking.setAdapter(madapter1);
//        }
//
////        madapter1=new LookingAdapter(getActivity(),newOrderBeansList1);
////        recyclerView_looking.setAdapter(madapter1);
//
//
//
//
//
//
//
//        return view;
//    }
//
//
//
//}
