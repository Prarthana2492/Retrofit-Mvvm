package com.FarmPe.Farmer.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;



import com.FarmPe.Farmer.Adapter.HomePage_Adapter;
import com.FarmPe.Farmer.Adapter.Home_Slider_Adapter;
import com.FarmPe.Farmer.Adapter.Noimg_Recylr_Adapter;
import com.FarmPe.Farmer.Bean.AddTractorBean1;
import com.FarmPe.Farmer.Bean.AddTractorBean2;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Volly_class.Crop_Post;
import com.FarmPe.Farmer.Volly_class.HomePage_Post;
import com.FarmPe.Farmer.Volly_class.HomepageLogin_post;
import com.FarmPe.Farmer.Volly_class.Login_post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FarmPe_Logo_Fragment extends Fragment {
    Fragment selectedFragment;
    public static LinearLayout backfeed;

    LinearLayout linearLayout, no_request, no_farms, requests_made;
    JSONObject lngObject;
    public static TextView reqst_count, nameee;
    SessionManager sessionManager;
    public static String toast_click_back;
    boolean doubleBackToExitPressedOnce = false;
    JSONArray count_images_array, rfq_images_array;
    List<Integer> image_arraylist = new ArrayList<Integer>();
    private int[] myImageList = new int[]{R.drawable.banner1, R.drawable.banner2,
            R.drawable.banner3};


    HomePage_Adapter homePage_adapter;
    TextView Add_make_request, no_make_request, no_list_farm, seeall_request,slide_text;
    ViewPager slider;
    LinearLayout ll_dots;
    Home_Slider_Adapter home_slider_adapter;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 200;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 1000;
    private static int NUM_PAGES = 0;
    private ShimmerFrameLayout mShimmerViewContainer;

    RecyclerView recyclerView;
    RecyclerView noimg_recyclerView;

    public static List<AddTractorBean1> newOrderBeansList = new ArrayList<>();
    public static List<AddTractorBean2> newOrderBeansList2 = new ArrayList<>();
    public static List<AddTractorBean2> newOrderBeansList3 = new ArrayList<>();
    public static JSONArray cropsListArray = null;
    public static JSONArray tractorImplementsModelMasterList = null;
    public static JSONArray tractorAccessoriesModelMasterList = null;
    public static JSONArray harvesterModelMasterList = null;
    public static JSONArray jCBRFQModelList = null;
    public static JSONArray farmMachineryRFQModelList = null;
    public static JSONArray fenceWireRFQModelList = null;
    public static JSONArray tyreRFQModelList = null;
    public static JSONArray miniTruckRFQModelList = null;
    public static JSONArray backhoeAttachmentRFQModelList = null;
    public static JSONArray powerTillerRFQModelList = null;


    Noimg_Recylr_Adapter noimg_recylr_adapter;
    public static FarmPe_Logo_Fragment newInstance() {
        FarmPe_Logo_Fragment fragment = new FarmPe_Logo_Fragment();
        return fragment;
    }


    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }



    @Override

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_main, container, false);
        //   backfeed= view.findViewById(R.id.back_feed1);

        linearLayout = view.findViewById(R.id.layout);
        // no_farms= view.findViewById(R.id.no_farms);
        no_request = view.findViewById(R.id.no_requests);

        requests_made = view.findViewById(R.id.request_made);
     //   slide_text = view.findViewById(R.id.slide_text);
       // nameee = view.findViewById(R.id.nameee);
        sessionManager = new SessionManager(getActivity());
        recyclerView = view.findViewById(R.id.recylr_2);
       // noimg_recyclerView = view.findViewById(R.id.recylr_5);
        slider = view.findViewById(R.id.vp_slider);
        ll_dots = view.findViewById(R.id.ll_dots);
        reqst_count = view.findViewById(R.id.request_count);
        Add_make_request = view.findViewById(R.id.add_request);
        // no_list_farm= view.findViewById(R.id.list_farmmmmm);
        no_make_request = view.findViewById(R.id.make_requesttttt);
        seeall_request = view.findViewById(R.id.request_sell_all);

        image_arraylist.clear();
        image_arraylist.add(R.drawable.banner1);
        image_arraylist.add(R.drawable.banner2);
        image_arraylist.add(R.drawable.banner3);
        image_arraylist.add(R.drawable.banner1);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmerAnimation();

        home_slider_adapter = new Home_Slider_Adapter(getActivity(), myImageList);
        slider.setAdapter(home_slider_adapter);

        addBottomDots(0, ll_dots);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                slider.post(new Runnable(){
                    @Override
                    public void run() {

                        slider.setCurrentItem((slider.getCurrentItem()+1)%myImageList.length);
                    }
                });
            }
        };

        timer = new Timer();
        timer.schedule(timerTask, 7000, 7000);




//        String slide_txt = "<font color=#000000> When a Farmer Cultivates his land,</font> <font color=#EC4848> Hes Cultivating a Dream Alongside.</font>";
//
//        slide_text.setText(Html.fromHtml(slide_txt));

        slider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                System.out.println("cddsd = "+position);;
                addBottomDots(position,  ll_dots);
                // page = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        no_request.setVisibility(View.GONE);


      //  nameee.setText(sessionManager.getRegId("name"));

        try {

            lngObject = new JSONObject(sessionManager.getRegId("language"));
            toast_click_back = lngObject.getString("PleaseclickBACKagaintoexit");


        } catch (JSONException e) {
            e.printStackTrace();
        }



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
                                .make(linearLayout, toast_click_back, duration);
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



        newOrderBeansList2.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        // recyclerView.addItemDecoration(new ItemDecorator( -80));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
       // noimg_recyclerView.setItemAnimator(new DefaultItemAnimator());


        homePage_adapter = new HomePage_Adapter(getActivity(), newOrderBeansList2);
        recyclerView.setAdapter(homePage_adapter);

      /*  noimg_recylr_adapter=new Noimg_Recylr_Adapter(getActivity(),newOrderBeansList3);
        noimg_recyclerView.setAdapter(noimg_recylr_adapter);
*/

        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm1 = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        noimg_recyclerView.setLayoutManager(mLayoutManager_farm1);


        noimg_recylr_adapter = new Noimg_Recylr_Adapter(getActivity(), newOrderBeansList3);
        noimg_recyclerView.setAdapter(noimg_recylr_adapter);



        Add_make_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Bundle bundle = new Bundle();
                bundle.putString("status","home_request");
                selectedFragment = AddFirstFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("farmpe_logo");
                transaction.commit();

            }
        });

        no_make_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("status","home_request");
                selectedFragment = AddFirstFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("farmpe_logo");
                transaction.commit();
            }
        });


        seeall_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("status","hme_request");
                selectedFragment = LookingForFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("home");
                transaction.commit();

            }
        });


        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("CreatedBy", sessionManager.getRegId("userId"));

            HomePage_Post.crop_posting(getActivity(), Urls.Home_Page_Count, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("fjfhffjcount" + result);

                    try {

                        newOrderBeansList.clear();
                        newOrderBeansList2.clear();

                     //   String farm_count = String.valueOf(result.getInt("FarmsCount"));
                         String request_count = String.valueOf(result.getInt("RFQCount"));
                         String notificatn_count = String.valueOf(result.getInt("NotificationCount"));

                         JSONObject rfqListObject=result.getJSONObject("RFQList");
                         cropsListArray = rfqListObject.getJSONArray("TractorRFQModelList");
                         tractorImplementsModelMasterList = rfqListObject.getJSONArray("TractorImplementsRFQModelList");
                        tractorAccessoriesModelMasterList = rfqListObject.getJSONArray("TractorAccesoriesRFQModelList");
                        harvesterModelMasterList = rfqListObject.getJSONArray("HarvesterRFQModelList");
                        jCBRFQModelList = rfqListObject.getJSONArray("JCBRFQModelList");
                        farmMachineryRFQModelList = rfqListObject.getJSONArray("FarmMachineryRFQModelList");
                        fenceWireRFQModelList = rfqListObject.getJSONArray("FenceWireRFQModelList");
                        tyreRFQModelList = rfqListObject.getJSONArray("TyreRFQModelList");
                        miniTruckRFQModelList = rfqListObject.getJSONArray("MiniTruckRFQModelList");
                        backhoeAttachmentRFQModelList = rfqListObject.getJSONArray("BackhoeAttachmentRFQModelList");
                        powerTillerRFQModelList = rfqListObject.getJSONArray("PowerTillerRFQModelList");


                        if (request_count.equalsIgnoreCase("0")) {
                            no_request.setVisibility(View.VISIBLE);
                            requests_made.setVisibility(View.GONE);
                            Noimg_recylr();
                           // request_made_lyt.setVisibility(View.VISIBLE);
                           /* no_farms.setVisibility(View.VISIBLE);
                            farms_lists.setVisibility(View.GONE);*/

                        } else {

                            no_request.setVisibility(View.GONE);
                            requests_made.setVisibility(View.VISIBLE);
                          //  request_made_lyt.setVisibility(View.GONE);

                        }

                            //   if (i <= 3) {


                            //   }

                        for (int i = 0; i < cropsListArray.length(); i++) {
                            JSONObject jsonObject1 = cropsListArray.getJSONObject(i);
                            AddTractorBean2 img2 = new AddTractorBean2(jsonObject1.getString("ModelImage"),jsonObject1.getString("Model"),jsonObject1.getString("LookingForDetails"),jsonObject1.getString("Id"));
                            newOrderBeansList2.add(img2);

                        }


                        for (int i = 0; i < tractorImplementsModelMasterList.length(); i++) {
                            JSONObject jsonObject1 = tractorImplementsModelMasterList.getJSONObject(i);
                            AddTractorBean2 img2 = new AddTractorBean2(jsonObject1.getString("ModelImage"),jsonObject1.getString("Model"),jsonObject1.getString("LookingForDetails"),jsonObject1.getString("Id"));
                            newOrderBeansList2.add(img2);

                        }


                        for (int i = 0; i < tractorAccessoriesModelMasterList.length(); i++) {
                            JSONObject jsonObject1 = tractorAccessoriesModelMasterList.getJSONObject(i);
                            AddTractorBean2 img2 = new AddTractorBean2(jsonObject1.getString("ModelImage"),jsonObject1.getString("Model"),jsonObject1.getString("LookingForDetails"),jsonObject1.getString("Id"));
                            newOrderBeansList2.add(img2);

                        }


                        for (int i = 0; i < harvesterModelMasterList.length(); i++) {
                            JSONObject jsonObject1 = harvesterModelMasterList.getJSONObject(i);
                            AddTractorBean2 img2 = new AddTractorBean2(jsonObject1.getString("ModelImage"),jsonObject1.getString("Model"),jsonObject1.getString("LookingForDetails"),jsonObject1.getString("Id"));
                            newOrderBeansList2.add(img2);

                        }

                        for (int i = 0; i < jCBRFQModelList.length(); i++) {
                            JSONObject jsonObject1 = jCBRFQModelList.getJSONObject(i);
                            AddTractorBean2 img2 = new AddTractorBean2(jsonObject1.getString("ModelImage"),jsonObject1.getString("Model"),jsonObject1.getString("LookingForDetails"),jsonObject1.getString("Id"));
                            newOrderBeansList2.add(img2);

                        }

                        for (int i = 0; i < farmMachineryRFQModelList.length(); i++) {
                            JSONObject jsonObject1 = farmMachineryRFQModelList.getJSONObject(i);
                            AddTractorBean2 img2 = new AddTractorBean2(jsonObject1.getString("ModelImage"),jsonObject1.getString("Model"),jsonObject1.getString("LookingForDetails"),jsonObject1.getString("Id"));
                            newOrderBeansList2.add(img2);

                        }
                        for (int i = 0; i < fenceWireRFQModelList.length(); i++) {
                            JSONObject jsonObject1 = fenceWireRFQModelList.getJSONObject(i);
                            AddTractorBean2 img2 = new AddTractorBean2(jsonObject1.getString("ModelImage"),jsonObject1.getString("Model"),jsonObject1.getString("LookingForDetails"),jsonObject1.getString("Id"));
                            newOrderBeansList2.add(img2);

                        }

                        for (int i = 0; i < tyreRFQModelList.length(); i++) {
                            JSONObject jsonObject1 = tyreRFQModelList.getJSONObject(i);
                            AddTractorBean2 img2 = new AddTractorBean2(jsonObject1.getString("ModelImage"),jsonObject1.getString("Model"),jsonObject1.getString("LookingForDetails"),jsonObject1.getString("Id"));
                            newOrderBeansList2.add(img2);

                        }  for (int i = 0; i < miniTruckRFQModelList.length(); i++) {
                            JSONObject jsonObject1 = miniTruckRFQModelList.getJSONObject(i);
                            AddTractorBean2 img2 = new AddTractorBean2(jsonObject1.getString("ModelImage"),jsonObject1.getString("Model"),jsonObject1.getString("LookingForDetails"),jsonObject1.getString("Id"));
                            newOrderBeansList2.add(img2);

                        }for (int i = 0; i < backhoeAttachmentRFQModelList.length(); i++) {
                            JSONObject jsonObject1 = backhoeAttachmentRFQModelList.getJSONObject(i);
                            AddTractorBean2 img2 = new AddTractorBean2(jsonObject1.getString("ModelImage"),jsonObject1.getString("Model"),jsonObject1.getString("LookingForDetails"),jsonObject1.getString("Id"));
                            newOrderBeansList2.add(img2);

                        }for (int i = 0; i < powerTillerRFQModelList.length(); i++) {
                            JSONObject jsonObject1 = powerTillerRFQModelList.getJSONObject(i);
                            AddTractorBean2 img2 = new AddTractorBean2(jsonObject1.getString("ModelImage"),jsonObject1.getString("Model"),jsonObject1.getString("LookingForDetails"),jsonObject1.getString("Id"));
                            newOrderBeansList2.add(img2);

                        }

                        reqst_count.setText("Requests "+ "(" + request_count + ")");
                       // HomeMenuFragment.request_count.setText(request_count);
                        HomeMenuFragment.notifictn_count.setText(notificatn_count);


                        homePage_adapter.notifyDataSetChanged();



                         new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mShimmerViewContainer.stopShimmerAnimation();
                                mShimmerViewContainer.setVisibility(View.GONE);
                            }
                        }, 2000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    public class ItemDecorator extends RecyclerView.ItemDecoration {
        private final int mSpace;

        public ItemDecorator(int space) {
            this.mSpace = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            if (position != 0)
                // outRect.top = mSpace;
                outRect.left = mSpace;
        }

    }

    //showing dots on screen
    private void addBottomDots(int currentPage, LinearLayout ll_dots) {
        TextView[] dots = new TextView[myImageList.length];
        ll_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            // System.out.println("dots_lengthhh"+dots.length);
            dots[i] = new TextView(getActivity());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.parseColor("#FFFFFF")); //bg color
            ll_dots.addView(dots[i]);
        }
        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.parseColor("#EC4848")); // flip color
    }


    private void Noimg_recylr() {
        try {
            newOrderBeansList3.clear();
            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("Id", 3);

            JSONObject postjsonObject = new JSONObject();
            postjsonObject.put("LookingForObj", userRequestjsonObject);
            System.out.println("postObj"+postjsonObject.toString());


            HomepageLogin_post.login_posting(getActivity(), Urls.GetLookingForItems,postjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);
                    JSONArray cropsListArray=null;

                    try {
                        cropsListArray=result.getJSONArray("LookingForDetailsList");
                        System.out.println("eeeddd"+cropsListArray.length());

                        for (int i=0;i<cropsListArray.length();i++){
                            JSONObject jsonObject1=cropsListArray.getJSONObject(i);
                            String getPrice=jsonObject1.getString("LookingForDetails");
                            String LookingForDetailsIcon=jsonObject1.getString("LookingForDetailsIcon");
                            // String lookingForId=jsonObject1.getString("LookingForId");
                            String lookingForId=jsonObject1.getString("Id");

                            AddTractorBean2 crops = new AddTractorBean2(LookingForDetailsIcon, getPrice,"","");
                            newOrderBeansList3.add(crops);

                            noimg_recylr_adapter.notifyDataSetChanged();
                            mShimmerViewContainer.stopShimmerAnimation();
                            mShimmerViewContainer.setVisibility(View.GONE);

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}



