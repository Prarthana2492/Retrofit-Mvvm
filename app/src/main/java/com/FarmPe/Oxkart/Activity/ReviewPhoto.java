//package com.FarmPe.Oxkart.Activity;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Color;
//import android.graphics.Matrix;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.design.widget.Snackbar;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.FarmPe.Oxkart.R;
//import com.FarmPe.Oxkart.SessionManager;
//import com.FarmPe.Oxkart.Urls;
//import com.FarmPe.Oxkart.volleypost.VolleyMultipartRequest;
//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.NetworkResponse;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.Volley;
//
//import java.io.ByteArrayOutputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.android.volley.VolleyLog.TAG;
//
//
//
//
//
//public class ReviewPhoto extends AppCompatActivity{
//
//    LinearLayout main_layout,selfie_img,back_feed;
//    TextView upload_img,take_photo_again;
//    Bitmap bitmap;
//    ImageView review_img;
//    SessionManager sessionManager;
//    public static String selfie_st,front_voter_st,back_voter_st,selfie_review;
//    Bitmap btmp;
//
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.review_photo);
//        main_layout=findViewById(R.id.main_layout);
//        upload_img=findViewById(R.id.upload_img);
//        review_img=findViewById(R.id.review_img);
//        back_feed=findViewById(R.id.back_feed);
//        take_photo_again=findViewById(R.id.take_photo_again);
//        sessionManager=new SessionManager(this);
//
//        back_feed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (VerificationSelfie.selfie!=null){
//                    Intent intent=new Intent(ReviewPhoto.this,VerificationSelfie.class);
//                    startActivity(intent);
//                }
//                else if (VerificationVoterIdFront.voterFrontid!=null){
//                    Intent intent=new Intent(ReviewPhoto.this,VerificationVoterIdFront.class);
//                    startActivity(intent);
//                }else{
//                    Intent intent=new Intent(ReviewPhoto.this, VerificationVoterIdback.class);
//                    startActivity(intent);
//                }
//
//            }
//        });
//        take_photo_again.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (VerificationSelfie.selfie!=null){
//                    Intent intent=new Intent(ReviewPhoto.this,VerificationSelfie.class);
//                    startActivity(intent);
//                }
//                else if (VerificationVoterIdFront.voterFrontid!=null){
//                    Intent intent=new Intent(ReviewPhoto.this,VerificationVoterIdFront.class);
//                    startActivity(intent);
//                }else{
//                    Intent intent=new Intent(ReviewPhoto.this,VerificationVoterIdback.class);
//                    startActivity(intent);
//                }
//
//            }
//        });
//
//
//        if (VerificationVoterIdFront.voterFrontid!=null){
//            final Bitmap btmp1 = BitmapFactory.decodeByteArray(VerificationVoterIdFront.imagedata,0,VerificationVoterIdFront.imagedata.length);
//            review_img.setImageBitmap(btmp1);
//
//
//        }else if (VerificationVoterIdback.voterBackid!=null){
//            final Bitmap btmp2 = BitmapFactory.decodeByteArray(VerificationVoterIdback.imagedata,0,VerificationVoterIdback.imagedata.length);
//            review_img.setImageBitmap(btmp2);
//
//
//        }else{
//             btmp = BitmapFactory.decodeByteArray(VerificationSelfie.imagedata,0,VerificationSelfie.imagedata.length);
//            review_img.setImageBitmap(RotateBitmap(btmp,-90));
//        }
//
//
//        final Bundle extras = getIntent().getExtras();
//
//        upload_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (VerificationSelfie.selfie!=null){
//                   uploadImage(btmp);
//                }
//                else if (VerificationVoterIdFront.voterFrontid!=null){
//                    //uploadImage();
//                    front_voter_st="frontvoter_pending";
//                    Intent intent=new Intent(ReviewPhoto.this,VerificationMain.class);
//                    startActivity(intent);
//                }else {
//                    System.out.println("back_voterrrrr");
//                    back_voter_st = "backvoter_pending";
//                    Intent intent = new Intent(ReviewPhoto.this, VerificationMain.class);
//                    startActivity(intent);
//                }
//                /*else{
//                    uploadImage(btmp);
//
//                }*/
//            }
//        });
//
//
//    }
//    private void uploadImage(Bitmap bitmap){
//        final ProgressDialog progressDialog = ProgressDialog.show(this, "",
//                "Loading....Please wait.");
//
//         progressDialog.show();
//
//        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Urls.Add_Update_Image_Details,
//                new Response.Listener<NetworkResponse>(){
//                    @Override
//                    public void onResponse(NetworkResponse response) {
//                        progressDialog.dismiss();
//
//
//                        Snackbar snackbar = Snackbar
//                                .make(main_layout, "Your photo has uploaded for verification", Snackbar.LENGTH_LONG);
//
//                        View snackbarView = snackbar.getView();
//                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                        tv.setBackgroundColor(ContextCompat.getColor(ReviewPhoto.this,R.color.orange));
//                        tv.setTextColor(Color.WHITE);
//
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                        } else {
//                            tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                        }
//                        snackbar.show();
//
//                        selfie_st="success";
//                        Intent intent=new Intent(ReviewPhoto.this,VerificationMain.class);
//                        startActivity(intent);
//                    }
//                },
//
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(ReviewPhoto.this,error.getMessage(), Toast.LENGTH_SHORT).show();
//                         progressDialog.dismiss();
//                    }
//                }) {
//
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("CImageId","0");
//                params.put("UserId",sessionManager.getRegId("userId"));
//                params.put("CreatedBy",sessionManager.getRegId("userId"));
//
//            System.out.println("params string "+params);
//
//                return params;
//            }
//            @Override
//            protected Map<String, DataPart> getByteData() {
//                Map<String, DataPart> params = new HashMap<>();
//                long imagename = System.currentTimeMillis();
//
//                Log.e(TAG,"Im here " + params);
//                if (VerificationSelfie.imagedata!=null) {
//                    params.put("Image1", new DataPart(imagename + ".png", VerificationSelfie.imagedata));
//
//                }
//                System.out.println("params file "+params);
//                return params;
//            }
//        };
//        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(1000 * 60, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        //adding the request to volley
//        Volley.newRequestQueue(this).add(volleyMultipartRequest);
//    }
//
//
//    private void uploadImage(){
//        final ProgressDialog progressDialog = ProgressDialog.show(this, "",
//                "Loading....Please wait.");
//
//        progressDialog.show();
//
//        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Urls.Add_Update_Image_Details,
//                new Response.Listener<NetworkResponse>(){
//                    @Override
//                    public void onResponse(NetworkResponse response) {
//                        progressDialog.dismiss();
//
//
//                        Snackbar snackbar = Snackbar
//                                .make(main_layout, "Your photo has uploaded for verification", Snackbar.LENGTH_LONG);
//                        View snackbarView = snackbar.getView();
//                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                        tv.setBackgroundColor(ContextCompat.getColor(ReviewPhoto.this,R.color.orange));
//                        tv.setTextColor(Color.WHITE);
//
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                        } else {
//                            tv.setGravity(Gravity.CENTER_HORIZONTAL);
//                        }
//                        snackbar.show();
//
//                        selfie_st="success";
//                        Intent intent=new Intent(ReviewPhoto.this,VerificationMain.class);
//                        startActivity(intent);
//                    }
//                },
//
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(ReviewPhoto.this,error.getMessage(), Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                    }
//                }) {
//
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("CImageId","0");
//                params.put("UserId","13");
//                params.put("CreatedBy","13");
//
//                System.out.println("params string "+params);
//
//                return params;
//            }
//            @Override
//            protected Map<String, DataPart> getByteData() {
//                Map<String, DataPart> params = new HashMap<>();
//                long imagename = System.currentTimeMillis();
//
//                Log.e(TAG,"Im here " + params);
//                if (VerificationSelfie.imagedata!=null) {
//                    params.put("Image1", new DataPart(imagename + ".png", VerificationSelfie.imagedata));
//
//                }
//                System.out.println("params file "+params);
//                return params;
//            }
//        };
//        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(1000 * 60, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        //adding the request to volley
//        Volley.newRequestQueue(this).add(volleyMultipartRequest);
//    }
//
//
//    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
//        return byteArrayOutputStream.toByteArray();
//    }
//    public Bitmap RotateBitmap(Bitmap source, float angle)
//    {
//        Matrix matrix = new Matrix();
//        matrix.postRotate(angle);
//        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
//    }
//
//
//}
