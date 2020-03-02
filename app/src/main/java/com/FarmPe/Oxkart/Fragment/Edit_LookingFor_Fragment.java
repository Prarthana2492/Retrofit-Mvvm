package com.FarmPe.Oxkart.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.FarmPe.Oxkart.Activity.HomePage_With_Bottom_Navigation;
import com.FarmPe.Oxkart.Activity.Status_bar_change_singleton;
import com.FarmPe.Oxkart.Bean.FarmsImageBean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Edit_LookingFor_Fragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    LinearLayout back_feed;
     TextView brand_name,toolbar_title,preview,purchase_plan,finance_req,insurance,textView,profile_name,profile_no,user_address;
     Fragment selectedFragment = null;
      SessionManager sessionManager;
      JSONArray rfq_list_array;
      ImageView image_lookingd,image3;
     String model_name,model_looking_fordetails,model_horse_power_range,model_brand_name;


    public static Edit_LookingFor_Fragment newInstance() {

       Edit_LookingFor_Fragment fragment = new Edit_LookingFor_Fragment();
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.edit_request_layout, container, false);
        // getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Status_bar_change_singleton.getInstance().color_change(getActivity());

              back_feed = view.findViewById(R.id.back_feed);
              textView = view.findViewById(R.id.preview);
              image_lookingd = view.findViewById(R.id.image_lookingd);
              image3 = view.findViewById(R.id.image3);
              profile_name = view.findViewById(R.id.profile_name);
              profile_no = view.findViewById(R.id.profile_no);
              toolbar_title = view.findViewById(R.id.toolbar_title);
              user_address = view.findViewById(R.id.user_address);


        brand_name = view.findViewById(R.id.brand_name);
        purchase_plan = view.findViewById(R.id.purchase_plan);
        finance_req = view.findViewById(R.id.finance_req);
        insurance = view.findViewById(R.id.insurance);


        sessionManager = new SessionManager(getActivity());

            back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("edit_looking", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("status","edit_for");
                selectedFragment = Preview_Edit_Looking_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("editpage");
                selectedFragment.setArguments(bundle);
                transaction.commit();
            }
        });


        try{

            JSONObject jsonObject = new JSONObject();
            JSONObject post_json  = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));
            post_json.put("RFQDetailsObj",jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.Get_RFQ_Details, post_json, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("sdfhgdsfsdf" + result);

                    try{

                        rfq_list_array = result.getJSONArray("RFQDetailsList");

                           for(int i=0;i<rfq_list_array.length();i++) {

                             JSONObject jsonObject1 = rfq_list_array.getJSONObject(i);

                             model_name = jsonObject1.getString("Model");
                             model_looking_fordetails = jsonObject1.getString("LookingForDetails");
                             model_horse_power_range = jsonObject1.getString("HorsePowerRange");
                             model_brand_name = jsonObject1.getString("BrandName");

                             String model_features_specification = jsonObject1.getString("FeaturesAndSpecs");
                             String model_drive_type= jsonObject1.getString("DriveType");
                             String model_purchase_timeline = jsonObject1.getString("PurchaseTimeline");
                             String model_looking_fr_finance = jsonObject1.getString("LookingForFinance");
                             String model_looing_fr_insurance = jsonObject1.getString("LookingForInsurance");
                             String model_fullname = jsonObject1.getString("FullName");
                             String model_phone_no = jsonObject1.getString("MobileNo");
                             String model_image = jsonObject1.getString("ModelImage");
                             String model_address= jsonObject1.getString("StreeAddress");
                             String model_address1 = jsonObject1.getString("StreeAddress1");
                             String model_state = jsonObject1.getString("State");
                            String model_district = jsonObject1.getString("District");
                            String model_taluk = jsonObject1.getString("Taluk");
                            String model_hobli = jsonObject1.getString("Hoblie");
                            String model_pincode = jsonObject1.getString("Pincode");
                            String model_id= jsonObject1.getString("Id");
                            System.out.println("dsfsdfsdfhhhhhh" + jsonObject1.getString("Id"));



                            brand_name.setText(model_brand_name + ":" + model_name + " " + model_drive_type + " " +model_horse_power_range);
                            finance_req.setText(model_looking_fr_finance);
                            purchase_plan.setText(model_purchase_timeline);
                            insurance.setText(model_looing_fr_insurance);
                            profile_name.setText(model_fullname);
                            profile_no.setText(model_phone_no);
                            toolbar_title.setText(model_looking_fordetails);
                            user_address.setText(model_fullname + "\n" + model_address + "\n" + model_address1 + "\n" + model_district + "," + model_state + " - " + model_pincode);


                               Glide.with(getActivity()).load(model_image)
                                       .thumbnail(0.5f)
                                       // .crossFade()
                                       .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                                               .error(R.drawable.avatarmale))
                                       .into(image_lookingd);


                               Glide.with(getActivity()).load(model_image)
                                       .thumbnail(0.5f)
                                       // .crossFade()
                                       .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                                               .error(R.drawable.avatarmale))
                                       .into(image3);

//
//                            Glide.with(getActivity()).load(model_image)
//                                    //  Glide.with(activity).load(R.drawable.tractor_sonalika)
//
//                                    .thumbnail(0.5f)
//                                   // .crossFade()
//                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                    .centerCrop()
//                                    .into(image_lookingd);


//                            Glide.with(getActivity()).load(model_image)
//                                    //  Glide.with(activity).load(R.drawable.tractor_sonalika)
//
//                                    .thumbnail(0.5f)
//                                   // .crossFade()
//                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                    .centerCrop()
//                                    .into(image3);
                        }


                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });

        }catch(Exception e){
            e.printStackTrace();
        }


        return view;

    }

    }









