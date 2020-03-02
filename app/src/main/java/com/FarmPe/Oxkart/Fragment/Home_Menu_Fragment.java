package com.FarmPe.Oxkart.Fragment;

import android.app.Dialog;
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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;




import com.FarmPe.Oxkart.Activity.Status_bar_change_singleton;

import com.FarmPe.Oxkart.Adapter.Home_Page_Request_Adapter;
import com.FarmPe.Oxkart.Bean.Request_Class_HomePage_Bean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;



import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;



public class Home_Menu_Fragment extends Fragment  {

    private List<Request_Class_HomePage_Bean> newOrderBeansList = new ArrayList<>();
    private List<Request_Class_HomePage_Bean> newOrderBeansList2 = new ArrayList<>();
    private RecyclerView recyclerView;
    LinearLayout notificatn_img,your_locatn;
    private Home_Page_Request_Adapter mAdapter;
    //  private Home_Page_Lookinfor_Adapter mAdapter2;
    boolean doubleBackToExitPressedOnce = false;
    LinearLayout linearLayout;
    Fragment selectedFragment;
    CircleImageView prod_imgg;
    SessionManager sessionManager;
    String ProfileImage;
    TextView locationtxt;

    public static TabLayout tabLayout;
    private ViewPager viewPager;



    public static Home_Menu_Fragment newInstance() {
        Home_Menu_Fragment fragment = new Home_Menu_Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page_layout, container, false);

        Status_bar_change_singleton.getInstance().home_change(getActivity());

        recyclerView = view.findViewById(R.id.recycler_view);
        linearLayout = view.findViewById(R.id.linearLayout);
        prod_imgg = view.findViewById(R.id.prod_imgg);
        notificatn_img = view.findViewById(R.id.notificatn_img);
        your_locatn = view.findViewById(R.id.your_locatn);
        locationtxt = view.findViewById(R.id.locationtxt);
        sessionManager = new SessionManager(getActivity());



    Bundle bundle=getArguments();

        if (bundle!=null){
            locationtxt.setText(getArguments().getString("homelocation"));

        }else{
            locationtxt.setText("Bangalore");
        }

        your_locatn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = GetLocationFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("home");
                transaction.commit();
            }
        });

        /*your_locatn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent intent = new Intent(getActivity(), GetLocationActivity.class);
             startActivity(intent);


            }
        });
*/



        prod_imgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("HOME_IMAGE","Selfie_image");
                selectedFragment = New_Profile_Setting_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("home_setting");
                transaction.commit();

            }
        });





        notificatn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                selectedFragment = NotificationFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.commit();

            }
        });


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 4);


        mLayoutManager = new GridLayoutManager(getActivity(),3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };




        // GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);
        Request_Class_HomePage_Bean item1    =  new Request_Class_HomePage_Bean("Tractor","1",R.drawable.tractor);
        Request_Class_HomePage_Bean item2    =  new Request_Class_HomePage_Bean("Farm\nTruck","9",R.drawable.farm_truck);
        Request_Class_HomePage_Bean item3    =  new Request_Class_HomePage_Bean("Backhoe\nLoader","4",R.drawable.backhoe_acessories);
        Request_Class_HomePage_Bean item4    =  new Request_Class_HomePage_Bean("Harvester","5",R.drawable.harvesting);
        Request_Class_HomePage_Bean item5    =  new Request_Class_HomePage_Bean("Farm\nMachines","6",R.drawable.machinary);
        Request_Class_HomePage_Bean item6    =  new Request_Class_HomePage_Bean("Power\nTillers","12",R.drawable.tiller);
        Request_Class_HomePage_Bean item7    =  new Request_Class_HomePage_Bean("Tractor\nImplements","2",R.drawable.tractor_implements);
        Request_Class_HomePage_Bean item8    =  new Request_Class_HomePage_Bean("Backhoe\nAttachment","10",R.drawable.backhoe);
        Request_Class_HomePage_Bean item9    =  new Request_Class_HomePage_Bean("Irrigation\nSystem","",R.drawable.sprinkler);
        Request_Class_HomePage_Bean item10   =  new Request_Class_HomePage_Bean("Tractor\n Accessories","3",R.drawable.accessories);
        Request_Class_HomePage_Bean item11   =  new Request_Class_HomePage_Bean("Tyres","8",R.drawable.tyre);
        Request_Class_HomePage_Bean item12   =  new Request_Class_HomePage_Bean("Fence\nWires","7",R.drawable.fencing_wire);



        newOrderBeansList.clear();
        newOrderBeansList.add(item1);
        newOrderBeansList.add(item2);
        newOrderBeansList.add(item3);
        newOrderBeansList.add(item4);
        newOrderBeansList.add(item5);
        newOrderBeansList.add(item6);
        newOrderBeansList.add(item7);
        newOrderBeansList.add(item8);
        newOrderBeansList.add(item9);
        newOrderBeansList.add(item10);
        newOrderBeansList.add(item11);
        newOrderBeansList.add(item12);



        System.out.println("newOrderBeansListvsdvdv"+newOrderBeansList.size());
        mAdapter = new Home_Page_Request_Adapter(getActivity(),newOrderBeansList,"home_menu");
        recyclerView.setAdapter(mAdapter);


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
//                        int duration = 1000;
//                        Snackbar snackbar = Snackbar
//                                .make(linearLayout,"Please Click Back To Exit", duration);
//                        View snackbarView = snackbar.getView();
//                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                        tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
//                        tv.setTextColor(Color.WHITE);
//
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                        } else {
//                            tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                        }
//                        snackbar.show();

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



      /*  try{

            JSONObject jsonObject = new JSONObject();
            JSONObject post_object = new JSONObject();
            jsonObject.put("Id",sessionManager.getRegId("userId"));
            post_object.put("objUser",jsonObject);


            Crop_Post.crop_posting(getActivity(), Urls.Get_Profile_Details, post_object, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("ggpgpgpg" + result);



                    try{

                        JSONObject jsonObject1 = result.getJSONObject("user");
                      //  profnamestr = jsonObject1.getString("FullName");
                       // System.out.println("ggpgpgpg" + profnamestr);
                      //  ProfilePhone = jsonObject1.getString("PhoneNo");
                        //String ProfileEmail = jsonObject1.getString("EmailId");
                        ProfileImage = jsonObject1.getString("ProfilePic");
                        // profile_description = jsonObject1.getString("About");

                        //   profname.setText(profnamestr);




                        //phone_no.setText(ProfilePhone.substring(3));

                      //  profile_phone.setText(ProfilePhone); // masking + deleting last line


                        // profname.setFilters(new InputFilter[]{EMOJI_FILTER});
                        // profile_phone.setFilters(new InputFilter[]{EMOJI_FILTER});
                        // aboutText.setFilters(new InputFilter[]{EMOJI_FILTER});


                        Glide.with(getActivity()).load(ProfileImage)
                                .thumbnail(0.5f)
                                // .crossFade()
                                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                                        .error(R.drawable.avatarmale))
                                .into(prod_imgg);

//                        Glide.with(getActivity())
//                                .load(ProfileImage)
//                                .centerCrop()
//                                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                                .skipMemoryCache(true)
//                                .dontAnimate()
//                                .into(profile_image);


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }*/

        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId", sessionManager.getRegId("userId"));


            Crop_Post.crop_posting(getActivity(), Urls.Get_Image_Details, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dhfjfjd" + result);


                    try {

                      JSONArray imagelist_array = result.getJSONArray("captureImagelist");

                        for (int i = 0; i < imagelist_array.length(); i++) {


                            JSONObject jsonObject1 = imagelist_array.getJSONObject(i);
                           String image_view = jsonObject1.getString("Image1");



                            Glide.with(getActivity()).load(image_view)
                                    .thumbnail(0.5f)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .error(R.drawable.avatarmale)
                                    .into(prod_imgg);
                        }




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


}
