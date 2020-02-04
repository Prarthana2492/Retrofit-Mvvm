//package com.FarmPe.Oxkart.Activity;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.graphics.Color;
//import android.graphics.Rect;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.Settings;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.RecyclerView;
//import android.text.Html;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.FarmPe.Oxkart.Adapter.Home_Slider_Adapter;
//import com.FarmPe.Oxkart.R;
//import com.FarmPe.Oxkart.SessionManager;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Timer;
//
//import static android.Manifest.permission.ACCESS_FINE_LOCATION;
//import static android.Manifest.permission.CAMERA;
//
//
//
//
//public class Slider_Activity extends AppCompatActivity {
//
//
//    LinearLayout proceed_btn;
//    JSONObject lngObject;
//    public static TextView reqst_count, nameee;
//    SessionManager sessionManager;
//    public static String toast_click_back;
//    boolean doubleBackToExitPressedOnce = false;
//    JSONArray count_images_array, rfq_images_array;
//    List<Integer> image_arraylist = new ArrayList<Integer>();
//
//    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
//    public static final String ALLOW_KEY = "ALLOWED";
//    public static final String CAMERA_PREF = "camera_pref";
//
//
//
//    private static final int PERMISSION_REQUEST_CODE = 200;
//
//    private int[] myImageList = new int[]{R.drawable.splash_image, R.drawable.splash_image,
//            R.drawable.splash_image};
//
//    ViewPager slider;
//    LinearLayout ll_dots;
//    Home_Slider_Adapter home_slider_adapter;
//    int currentPage = 0;
//    Timer timer;
//    final long DELAY_MS = 200;//delay in milliseconds before task is to be executed
//    final long PERIOD_MS = 1000;
//    private static int NUM_PAGES = 0;
//
//
//    RecyclerView recyclerView;
//    RecyclerView noimg_recyclerView;
//
//
//    @Override
//    public void onDestroy() {
//        timer.cancel();
//        super.onDestroy();
//    }
//
//
//    @Override
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.slider_layout);
//        //   backfeed= view.findViewById(R.id.back_feed1);
//
//        //   slide_text = view.findViewById(R.id.slide_text);
//        // nameee = view.findViewById(R.id.nameee);
//        sessionManager = new SessionManager(this);
//
//        slider = findViewById(R.id.vp_slider);
//        ll_dots = findViewById(R.id.ll_dots);
//        proceed_btn = findViewById(R.id.proceed_btn);
//
//
//        image_arraylist.clear();
//        image_arraylist.add(R.drawable.splash_image);
//        image_arraylist.add(R.drawable.splash_image);
//        image_arraylist.add(R.drawable.splash_image);
//        // image_arraylist.add(R.drawable.banner1);
//
//        home_slider_adapter = new Home_Slider_Adapter(this, myImageList);
//        slider.setAdapter(home_slider_adapter);
//
//
//        addBottomDots(0, ll_dots);
//
////
////        if (ContextCompat.checkSelfPermission(Slider_Activity.this,
////                Manifest.permission.CAMERA)
////                != PackageManager.PERMISSION_GRANTED) {
////
////            if (getFromPref(Slider_Activity.this, ALLOW_KEY)) {
////                showSettingsAlert();
////
////
////            } else if (ContextCompat.checkSelfPermission(Slider_Activity.this,
////                    Manifest.permission.CAMERA)
////                    != PackageManager.PERMISSION_GRANTED) {
////                // Should we show an explanation?
////
////                if (ActivityCompat.shouldShowRequestPermissionRationale(Slider_Activity.this,
////                        Manifest.permission.CAMERA)) {
////                    showAlert();
////
////                } else {
////                    // No explanation needed, we can request the permission.
////                    ActivityCompat.requestPermissions(Slider_Activity.this,
////                            new String[]{Manifest.permission.CAMERA},
////                            MY_PERMISSIONS_REQUEST_CAMERA);
////
////                }
////            }
////        }
//
//        checkPermission();
//        requestPermission();
//
//        proceed_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(Slider_Activity.this, New_Login_Activity2.class);
//                startActivity(intent);
//
//            }//
//
//
////        TimerTask timerTask = new TimerTask() {
////            @Override
////            public void run() {
////                slider.post(new Runnable(){
////                    @Override
////                    public void run() {
////
////                        slider.setCurrentItem((slider.getCurrentItem()+1)%myImageList.length);
////                    }
////                });
////            }
////        };
////
////        timer = new Timer();
////        timer.schedule(timerTask, 7000, 7000);
////
////
//
//        });
//
//
////        String slide_txt = "<font color=#000000> When a Farmer Cultivates his land,</font> <font color=#EC4848> Hes Cultivating a Dream Alongside.</font>";
////
////        slide_text.setText(Html.fromHtml(slide_txt));
//
//        slider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                System.out.println("cddsd = " + position);
//                ;
//                addBottomDots(position, ll_dots);
//                // page = position;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//
//    }
//
//    private boolean checkPermission() {
//        int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
//        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
//
//        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
//
//    }
//
//    private void requestPermission() {
//        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, CAMERA}, PERMISSION_REQUEST_CODE);
//
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0) {
//                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//
//
//                    if (locationAccepted && cameraAccepted) {
//                        //Snackbar.make(v, "Permission Granted, Now you can access location data and camera.", Snackbar.LENGTH_LONG).show();
//                        //   Toast.makeText(this, "Permission Granted, Now you can access location data and camera.", Toast.LENGTH_SHORT).show();
//
//                    }  else{
//                        //  Snackbar.make(view, "Permission Denied, You cannot access location data and camera.", Snackbar.LENGTH_LONG).show();
//                        Toast.makeText(this, "Permission Denied, You cannot access location data and camera.", Toast.LENGTH_SHORT).show();
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
//                                showMessageOKCancel("You need to allow access to both the permissions",
//                                        new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                                    requestPermissions(new String[]{ACCESS_FINE_LOCATION, CAMERA},
//                                                            PERMISSION_REQUEST_CODE);
//                                                }
//                                            }
//                                        });
//                                return;
//                            }
//                        }
//                    }
//                }
//
//                break;
//        }
//    }
//
//    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
//        new AlertDialog.Builder(Slider_Activity.this).
//                setMessage(message).setPositiveButton("OK", okListener).setNegativeButton("Cancel", null).create().show();
//    }
//
//
//
//
//
//
////    public static void saveToPreferences(Context context, String key,
////                                         Boolean allowed) {
////        SharedPreferences myPrefs = context.getSharedPreferences
////                (CAMERA_PREF, Context.MODE_PRIVATE);
////        SharedPreferences.Editor prefsEditor = myPrefs.edit();
////        prefsEditor.putBoolean(key, allowed);
////        prefsEditor.commit();
////    }
////
//
//
////    public static Boolean getFromPref(Context context, String key) {
////        SharedPreferences myPrefs = context.getSharedPreferences
////                (CAMERA_PREF, Context.MODE_PRIVATE);
////        return (myPrefs.getBoolean(key, false));
////    }
//
//
//
////
////    private void showAlert() {
////        AlertDialog alertDialog = new AlertDialog.Builder(Slider_Activity.this).create();
////        alertDialog.setTitle("Alert");
////        alertDialog.setMessage("App needs to access the Camera.");
////
////        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
////                new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int which) {
////                        dialog.dismiss();
////                        Slider_Activity.this.finish();
////                    }
////                });
////
////
////        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ALLOW",
////                new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int which) {
////                        dialog.dismiss();
////
////                       Intent intent = new Intent(Slider_Activity.this,LoginActivity_new.class);
////                        startActivity(intent);
////
////
////                        ActivityCompat.requestPermissions(Slider_Activity.this,
////
////                                new String[]{CAMERA},
////                                MY_PERMISSIONS_REQUEST_CAMERA);
////
////                    }
////                });
////
////        alertDialog.show();
////    }
//
//
//
////    private void showSettingsAlert() {
////        AlertDialog alertDialog = new AlertDialog.Builder(Slider_Activity.this).create();
////        alertDialog.setTitle("Alert");
////        alertDialog.setMessage("App needs to access the Camera.");
////
////
////        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
////                new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int which) {
////                        dialog.dismiss();
////                        //finish();
////                    }
////                });
////
////
////        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SETTINGS",
////                new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int which) {
////                        dialog.dismiss();
////
////                        Intent intent = new Intent(Slider_Activity.this,LoginActivity_new.class);
////                        startActivity(intent);
////                        startInstalledAppDetailsActivity(Slider_Activity.this);
////                    }
////                });
////        alertDialog.show();
////    }
////
//
//
////    @Override
////    public void onRequestPermissionsResult
////            (int requestCode, String permissions[], int[] grantResults) {
////        switch (requestCode) {
////            case MY_PERMISSIONS_REQUEST_CAMERA: {
////                for (int i = 0, len = permissions.length; i < len; i++) {
////                    String permission = permissions[i];
////                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
////                        boolean showRationale =
////                                ActivityCompat.shouldShowRequestPermissionRationale
////                                        (Slider_Activity.this, permission);
////
////                        if (showRationale) {
////                            showAlert();
////
////
////                        } else if (!showRationale) {
////                            // user denied flagging NEVER ASK AGAIN
////                            // you can either enable some fall back,
////                            // disable features of your app
////                            // or open another dialog explaining
////                            // again the permission and directing to
////                            // the app setting
////                            saveToPreferences(Slider_Activity.this, ALLOW_KEY, true);
////
////                        }
////                    }
////                }
////            }
////        }
////        // other 'case' lines to check for other
////        // permissions this app might request
//    //}
//
//
//    public static void startInstalledAppDetailsActivity(final Activity context) {
//        if (context == null) {
//            return;
//        }
//        final Intent i = new Intent();
//        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//        i.addCategory(Intent.CATEGORY_DEFAULT);
//        i.setData(Uri.parse("package:" + context.getPackageName()));
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//        context.startActivity(i);
//    }
//
//
//
//    public class ItemDecorator extends RecyclerView.ItemDecoration {
//        private final int mSpace;
//
//        public ItemDecorator(int space) {
//            this.mSpace = space;
//        }
//
//        @Override
//        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//            int position = parent.getChildAdapterPosition(view);
//            if (position != 0)
//                // outRect.top = mSpace;
//                outRect.left = mSpace;
//        }
//
//    }
//
//    //showing dots on screen
//    private void addBottomDots(int currentPage, LinearLayout ll_dots) {
//        TextView[] dots = new TextView[myImageList.length];
//        ll_dots.removeAllViews();
//        for (int i = 0; i < dots.length; i++) {
//            // System.out.println("dots_lengthhh"+dots.length);
//            dots[i] = new TextView(this);
//            dots[i].setText(Html.fromHtml("&#8226;"));
//            dots[i].setTextSize(35);
//            dots[i].setTextColor(Color.parseColor("#999999")); //bg color
//            ll_dots.addView(dots[i]);
//        }
//        if (dots.length > 0)
//            dots[currentPage].setTextColor(Color.parseColor("#EC4848")); // flip color
//    }
//
//}
