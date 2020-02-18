package com.FarmPe.Oxkart.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.FarmPe.Oxkart.Adapter.AdapterSelectLanguage;
import com.FarmPe.Oxkart.Bean.SelectLanguageBean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.Login_post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;

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
    public static TextView continue_lang, select_your_lang_text;
    public static JSONObject lngObject;

    SessionManager sessionManager;


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("loginonStart");
        //check
        sessionManager.checkLogin();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_a_selectlang);
        recyclerView = findViewById(R.id.recycler_view_lang);
        continue_lang = findViewById(R.id.continue_lang);
        select_your_lang_text = findViewById(R.id.select_your_lang_text);


        sessionManager = new SessionManager(this);


        SelectLanguageBean item1 = new SelectLanguageBean("English", "1", "");
        SelectLanguageBean item2 = new SelectLanguageBean("हिंदी", "2", "");
        SelectLanguageBean item3 = new SelectLanguageBean("ಕನ್ನಡ", "3", "");
        SelectLanguageBean item4 = new SelectLanguageBean("తెలుగు", "4", "");
        SelectLanguageBean item5 = new SelectLanguageBean("தமிழ்", "5", "");
        SelectLanguageBean item6 = new SelectLanguageBean("മലയാളം", "6", "");
        SelectLanguageBean item7 = new SelectLanguageBean("मराठी", "7", "");
        SelectLanguageBean item8 = new SelectLanguageBean("বাংলা", "8", "");
        SelectLanguageBean item9 = new SelectLanguageBean("ગુજરાતી", "9", "");
        SelectLanguageBean item10 = new SelectLanguageBean("বাংলা", "10", "");



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


        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(ActivitySelectLang.this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        linearLayout = findViewById(R.id.main_layout);
        mAdapter = new AdapterSelectLanguage(ActivitySelectLang.this, newOrderBeansList);
        recyclerView.setAdapter(mAdapter);
        //Langauges();


        try {

            if ((sessionManager.getRegId("language")).equals("")) {

                getLang(1);


            } else {

                lngObject = new JSONObject(sessionManager.getRegId("language"));

                System.out.println("llllllllllllkkkkkkkkkkkkkkk" + lngObject.getString("EnterPhoneNo"));

                select_your_lang_text.setText(lngObject.getString("SelectYourLanguage"));
                continue_lang.setText(lngObject.getString("PROCEED").replace("\n",""));



                //  pass.setHint(lngObject.getString("Password"));
                //  remember_me.setText(lngObject.getString("RememberMe"));


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        continue_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ActivitySelectLang.this, Slider_Activity.class);
                startActivity(intent);

            }
        });

    }

      private void getLang(int id) {


        try {


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Id", id);

            System.out.print("iiidddddd" + id);

            Crop_Post.crop_posting(ActivitySelectLang.this, Urls.CHANGE_LANGUAGE, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {


                    System.out.println("qqqqqqvv" + result);

                    try {
                        sessionManager.saveLanguage(result.toString());

                        String lang_title1 = result.getString("SelectYourLanguage");
                        String proceed_btn = result.getString("PROCEED").replace("\n","");

                        select_your_lang_text.setText(lang_title1);
                        continue_lang.setText(proceed_btn);


                        // String log_login = result.getString("Login");


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

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
                           // SelectLanguageBean stateBean=new SelectLanguageBean(language,langCode,langimg);
                            //newOrderBeansList.add(stateBean);

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

//         Toast toast = Toast.makeText(ActivitySelectLang.this,"Please Click Back Again To Exit", Toast.LENGTH_SHORT);
//         toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
//         toast.show();

//        int duration=1000;
//        Snackbar snackbar = Snackbar
//                .make(linearLayout,"Please click Back again to Exit", duration);
//        View snackbarView = snackbar.getView();
//        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//        tv.setBackgroundColor(ContextCompat.getColor(ActivitySelectLang.this,R.color.orange));
//        tv.setTextColor(Color.WHITE);
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//        } else {
//            tv.setGravity(Gravity.CENTER_HORIZONTAL);
//        }
//
//        snackbar.show();


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 3000);


    }

}
