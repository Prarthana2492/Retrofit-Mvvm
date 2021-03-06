package com.FarmPe.Oxkart.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


import com.FarmPe.Oxkart.Adapter.SelectLanguageAdapter;
import com.FarmPe.Oxkart.Bean.SelectLanguageBean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Login_post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectLanguageActivity extends AppCompatActivity {
    private List<SelectLanguageBean> newOrderBeansList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SelectLanguageAdapter mAdapter;
    SessionManager sessionManager;
    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("loginonStart");
        sessionManager = new SessionManager(getApplicationContext()); //check
       // sessionManager.checkLogin();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_language_layout);
        //select_lang=findViewById(R.id.select_lang);

        recyclerView =findViewById(R.id.recycler_view1);

        /*select_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SelectLanguageActivity.this,WelcomeActivity.class);
                startActivity(intent);
            }
        });*/


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new SelectLanguageAdapter(SelectLanguageActivity.this, newOrderBeansList);
        recyclerView.setAdapter(mAdapter);
        Langauges();
    }

    private void Langauges() {

        try {

            newOrderBeansList.clear();
            JSONObject userRequestjsonObject = new JSONObject();
            JSONObject postjsonObject = new JSONObject();
            userRequestjsonObject.put("TalukId",5495);
            postjsonObject.putOpt("Hobliobj", userRequestjsonObject);

            Login_post.login_posting(SelectLanguageActivity.this, Urls.Languages,postjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("statussssss"+result);
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();

            if (doubleBackToExitPressedOnce) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                startActivity(intent);
                finish();
                System.exit(0);
            }

            doubleBackToExitPressedOnce = true;
            Toast.makeText(getApplicationContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 3000);

        }
    }
}
