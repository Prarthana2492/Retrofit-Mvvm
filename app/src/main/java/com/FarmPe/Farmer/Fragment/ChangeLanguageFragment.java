package com.FarmPe.Farmer.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.FarmPe.Farmer.Activity.HomePage_With_Bottom_Navigation;
import com.FarmPe.Farmer.Activity.Status_bar_change_singleton;
import com.FarmPe.Farmer.Adapter.SelectLanguageAdapter;
import com.FarmPe.Farmer.Bean.SelectLanguageBean;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Volly_class.Login_post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;




public class ChangeLanguageFragment extends Fragment {
    private List<SelectLanguageBean> newOrderBeansList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SelectLanguageAdapter mAdapter;
    TextView continue_lang;
    SessionManager sessionManager;
    LinearLayout back_feed;
    public static   JSONObject lngObject;
    public static TextView lang_title;
    Fragment selectedFragment = null;



    public static ChangeLanguageFragment newInstance() {
        ChangeLanguageFragment fragment = new ChangeLanguageFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_language_layout, container, false);

        Status_bar_change_singleton.getInstance().color_change(getActivity());


        HomePage_With_Bottom_Navigation.linear_bottom.setVisibility(View.GONE);

        back_feed=view.findViewById(R.id.back_feed);
        lang_title=view.findViewById(R.id.lang_title);
        continue_lang=view.findViewById(R.id.continue_lang);
        recyclerView =view.findViewById(R.id.recycler_view1);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new SelectLanguageAdapter(getActivity(), newOrderBeansList);
        sessionManager = new SessionManager(getActivity());
        recyclerView.setAdapter(mAdapter);
        Langauges();



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("lang_setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }
                return false;
            }
        });



        continue_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedFragment = New_Profile_Setting_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("setting");
                transaction.commit();
            }
        });


//        try {
//            lngObject = new JSONObject(sessionManager.getRegId("language"));
//
//            lang_title.setText(lngObject.getString("ChangeLanguage"));
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("lang_setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);


            }
        });


        return view;
    }

    private void Langauges() {
        try {
            newOrderBeansList.clear();
            JSONObject userRequestjsonObject = new JSONObject();
            JSONObject postjsonObject = new JSONObject();
            userRequestjsonObject.put("TalukId", 5495);
            postjsonObject.putOpt("Hobliobj", userRequestjsonObject);

            Login_post.login_posting(getActivity(), Urls.Languages,postjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("statussssscccs"+result);
                    JSONObject jsonObject;

                    try {

                        JSONArray jsonArray=result.getJSONArray("LanguagesList");
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String language=jsonObject1.getString("Language");
                             String langCode=jsonObject1.getString("Id");
                            String langimg=jsonObject1.getString("ImageIcon");
                            SelectLanguageBean stateBean=new SelectLanguageBean(language,langCode,langimg);
                            newOrderBeansList.add(stateBean);
                            recyclerView.setAdapter(mAdapter);
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

