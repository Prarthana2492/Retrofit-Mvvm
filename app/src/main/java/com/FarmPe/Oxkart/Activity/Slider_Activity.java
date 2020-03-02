package com.FarmPe.Oxkart.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.FarmPe.Oxkart.Adapter.SliderPagerAdapter;
import com.FarmPe.Oxkart.Bean.ListBean2;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;




public class Slider_Activity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{

    int page_position = 0;
    ArrayList<ListBean2>  apply_loan;
    LinearLayout ll_dots, cate1, cate2, cate3;
    public static JSONObject lngObject;
    TextView proceed;
    public static boolean connectivity_check;
    ConnectivityReceiver connectivityReceiver;
    SessionManager sessionManager;
    public static  String toast_internet,toast_nointernet;



    @Override
    protected void onStop()

    {
        unregisterReceiver(connectivityReceiver);
        super.onStop();
    }



    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }



    private void showSnack(boolean isConnected) {
        String message = null;
        int color=0;
        if (isConnected) {
            if(connectivity_check) {
                message = "Good! Connected to Internet";
                color = Color.WHITE;

                Toast toast = Toast.makeText(Slider_Activity.this,toast_internet, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                toast.show();

//                int duration=1000;
//                Snackbar snackbar = Snackbar.make(linear_layout,toast_internet, duration);
//                View sbView = snackbar.getView();
//                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
//                textView.setBackgroundColor(ContextCompat.getColor(New_Login_Activity2.this,R.color.orange));
//                textView.setTextColor(Color.WHITE);
//                snackbar.show();


                connectivity_check=false;
            }


        } else {
            message = "No Internet Connection";
            color = Color.RED;

            int duration=1000;
            connectivity_check=true;

            Toast toast = Toast.makeText(Slider_Activity.this,toast_nointernet, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
            toast.show();


//            Snackbar.make(findViewById(android.R.id.content),toast_nointernet, duration).show();


        }
    }

    @Override
    public void onResume() {
        super.onResume();

        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        connectivityReceiver = new ConnectivityReceiver();
        registerReceiver(connectivityReceiver, intentFilter);
        MyApplication.getInstance().setConnectivityListener(this);


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.sliderlayoutviewpager);
        super.onCreate(savedInstanceState);


        checkConnection();

        proceed = findViewById(R.id.proceed);

        sessionManager = new SessionManager(this);

        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };


        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        if (!hasPermissions(this, PERMISSIONS)) {
        }


        try {

            lngObject = new JSONObject(sessionManager.getRegId("language"));

            System.out.println("llllllllllllkkkkkkkkkkkkkkk" + lngObject.getString("EnterPhoneNo"));

            proceed.setText(lngObject.getString("PROCEED").replace("\n",""));
            toast_internet = lngObject.getString("GoodConnectedtoInternet");
            toast_nointernet = lngObject.getString("NoInternetConnection");





            //  pass.setHint(lngObject.getString("Password"));
            //  remember_me.setText(lngObject.getString("RememberMe"));




        } catch (JSONException e) {
            e.printStackTrace();
        }



        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Slider_Activity.this,New_Login_Activity2.class);
                startActivity(intent);
            }
        });



        apply_loan = new ArrayList<>();
        ListBean2 bean=new ListBean2("Tractor",2,R.drawable.tractor_red,1);
        apply_loan.add(bean);
        ListBean2 bean3=new ListBean2("Farm\nTruck",3,R.drawable.farm_truck,1);
        apply_loan.add(bean3);
        ListBean2 bean4=new ListBean2("Backhoe\nLoader",4,R.drawable.backhoe_acessories,1);
        apply_loan.add(bean4);
        ListBean2 bean5=new ListBean2("Harvester",5,R.drawable.harvesting,1);
        apply_loan.add(bean5);
        ListBean2 bean11=new ListBean2("Farm\nMachines",1,R.drawable.machinary,1);
        apply_loan.add(bean11);
        ListBean2 bean8=new ListBean2("Power\nTillers",11,R.drawable.tiller,1);
        apply_loan.add(bean8);
        ListBean2 bean6=new ListBean2("Tractor\nImplements",7,R.drawable.tractor_implements,1);
        apply_loan.add(bean6);
        ListBean2 bean7=new ListBean2("Backhoe\nAttachment",8,R.drawable.backhoe,1);
        apply_loan.add(bean7);
        ListBean2 bean9=new ListBean2("Irrigation\nSystem",9,R.drawable.sprinkler,1);
        apply_loan.add(bean9);
        ListBean2 bean14=new ListBean2("Tractor\n Accessories",10,R.drawable.accessories,1);
        apply_loan.add(bean14);


        final ViewPager vp_slider = (ViewPager) findViewById(R.id.vp_slider);
        ll_dots = (LinearLayout) findViewById(R.id.ll_dots);


        SliderPagerAdapter sliderPagerAdapter = new SliderPagerAdapter(Slider_Activity.this, apply_loan);



        vp_slider.setAdapter(sliderPagerAdapter);



        vp_slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        addBottomDots(0);
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == 3) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                vp_slider.setCurrentItem(page_position, true);
            }
        };
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    private void addBottomDots(int currentPage) {
        TextView[] dots = new TextView[3];
        ll_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(Slider_Activity.this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(40);
            dots[i].setPadding(10, 0, 10, 0);
            dots[i].setTextColor(Color.parseColor("#DFDDDD"));
            ll_dots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.parseColor("#E50914"));
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);

    }
}
