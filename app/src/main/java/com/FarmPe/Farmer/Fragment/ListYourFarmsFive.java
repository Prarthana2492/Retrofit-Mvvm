package com.FarmPe.Farmer.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.Farmer.Adapter.AddPhotoAdapter;
import com.FarmPe.Farmer.Bean.AddPhotoBean;
import com.FarmPe.Farmer.R;
import java.util.ArrayList;
import java.util.List;



public class ListYourFarmsFive extends Fragment {

    public static List<AddPhotoBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static AddPhotoAdapter farmadapter;
    LinearLayout back_feed,add_photo_layout2,add_photo_layout1;
    Fragment selectedFragment;
    TextView toolbar_title,upload_1;
    Bitmap bitmap = null;
    public static final int GET_FROM_GALLERY = 3;
    ImageView cover_image;


    public static ListYourFarmsFive newInstance() {
        ListYourFarmsFive fragment = new ListYourFarmsFive();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_farm_fifth_layout_item, container, false);
        recyclerView=view.findViewById(R.id.recycler_photo);
        back_feed=view.findViewById(R.id.back_feed);
        upload_1=view.findViewById(R.id.upload_1);

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("lookingFourth", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("lookingFourth", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });


        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
       /* if (AddPhotoFragmentSub.bitmap==null){
            AddPhotoBean img1=new AddPhotoBean(bitmap);
            newOrderBeansList.add(img1);
        }*/
            System.out.println("bittttttttttttt"+AddPhotoFragmentSub.bitmap);
            AddPhotoBean img1=new AddPhotoBean(AddPhotoFragmentSub.bitmap);
            newOrderBeansList.add(img1);
            farmadapter=new AddPhotoAdapter(getActivity(),newOrderBeansList);
            recyclerView.setAdapter(farmadapter);


            upload_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });



        return view;
    }


}
