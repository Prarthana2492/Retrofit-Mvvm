package com.FarmPe.Farmer.Fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.FarmPe.Farmer.Activity.HomePage_With_Bottom_Navigation;
import com.FarmPe.Farmer.Activity.Status_bar_change_singleton;
import com.FarmPe.Farmer.Bean.FarmsImageBean;
import com.FarmPe.Farmer.R;
import java.util.ArrayList;
import java.util.List;



public class Edit_LookingFor_Fragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    LinearLayout back_feed;
    TextView textView;
    Fragment selectedFragment = null;



    public static Edit_LookingFor_Fragment newInstance() {
        Edit_LookingFor_Fragment fragment = new Edit_LookingFor_Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.edit_request_layout, container, false);
        // getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Status_bar_change_singleton.getInstance().color_change(getActivity());
        HomePage_With_Bottom_Navigation.linear_bottom.setVisibility(View.GONE);

           back_feed = view.findViewById(R.id.back_feed);
           textView = view.findViewById(R.id.preview);

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


        return view;

    }

    }









