package com.FarmPe.Farmer.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Farmer.Adapter.AdapterSelectLanguage;
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

public class ActivitySelectLang extends AppCompatActivity {
    private List<SelectLanguageBean> newOrderBeansList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterSelectLanguage mAdapter;
    boolean doubleBackToExitPressedOnce = false;
    LinearLayout linearLayout;
    TextView continue_lang;

    SessionManager sessionManager;

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("loginonStart");
        sessionManager = new SessionManager(getApplicationContext()); //check
         sessionManager.checkLogin();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_a_selectlang);
        recyclerView =findViewById(R.id.recycler_view_lang);
        continue_lang =findViewById(R.id.continue_lang);



         GridLayoutManager mLayoutManager_farm = new GridLayoutManager(ActivitySelectLang.this, 1, GridLayoutManager.VERTICAL, false);
         recyclerView.setLayoutManager(mLayoutManager_farm);
         recyclerView.setItemAnimator(new DefaultItemAnimator());
         linearLayout= findViewById(R.id.main_layout);
        Langauges();


        continue_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ActivitySelectLang.this, LoginActivity_new.class);
                startActivity(intent);


            }
        });

    }


    private void Langauges() {
        try {
            JSONObject userRequestjsonObject = new JSONObject();
            JSONObject postjsonObject = new JSONObject();
            userRequestjsonObject.put("TalukId",5495);
            postjsonObject.putOpt("Hobliobj", userRequestjsonObject);
            Login_post.login_posting(ActivitySelectLang.this, Urls.Languages,postjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("statussssss"+result);
                    JSONObject jsonObject;
                    try {
                        JSONArray jsonArray=result.getJSONArray("LanguagesList");
                        newOrderBeansList.clear();

                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String language=jsonObject1.getString("Language");
                            int langCode=jsonObject1.getInt("Id");
                            String langimg=jsonObject1.getString("ImageIcon");
                            SelectLanguageBean stateBean=new SelectLanguageBean(language,langCode,langimg);
                            newOrderBeansList.add(stateBean);

                        }

                        mAdapter = new AdapterSelectLanguage(ActivitySelectLang.this, newOrderBeansList);
                        recyclerView.setAdapter(mAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     @Override
    public void onBackPressed() {



        if (doubleBackToExitPressedOnce) {

            Intent intent1 = new Intent(Intent.ACTION_MAIN);
            intent1.addCategory(Intent.CATEGORY_HOME);
            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent1);
            finish();                   }


        doubleBackToExitPressedOnce = true;

        int duration=1000;
        Snackbar snackbar = Snackbar
                .make(linearLayout,"Please click Back again to Exit", duration);
        View snackbarView = snackbar.getView();
        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setBackgroundColor(ContextCompat.getColor(ActivitySelectLang.this,R.color.orange));
        tv.setTextColor(Color.WHITE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        } else {
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
        }

        snackbar.show();


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 3000);


    }

}
