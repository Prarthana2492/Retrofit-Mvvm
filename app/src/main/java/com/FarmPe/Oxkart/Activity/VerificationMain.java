//package com.FarmPe.Oxkart.Activity;
//
//import android.app.Dialog;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//
//import android.view.View;
//
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.FarmPe.Oxkart.Fragment.VerifyLast;
//import com.FarmPe.Oxkart.R;
//import com.FarmPe.Oxkart.SessionManager;
//import com.FarmPe.Oxkart.Urls;
//import com.FarmPe.Oxkart.Volly_class.Login_post;
//import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;
//import com.FarmPe.Oxkart.volleypost.VolleyMultipartRequest;
//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.NetworkResponse;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.android.volley.VolleyLog.TAG;
//
//
//public class VerificationMain extends AppCompatActivity{
//
//    LinearLayout sele_loc,selfie_img,back_feed;
//    TextView continue_btn,sel_loc_text,select,pending_selfie,click,pending_frnt,pending_back,pending_text_f,pending_text_b;
//    SessionManager sessionManager;
//    String status;
//    double edit_loc_st;
//    String CImageId,cont_st1,cont_st2,cont_st3,cont_st4;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.verification_layout);
//        sele_loc=findViewById(R.id.sele_loc);
//        selfie_img=findViewById(R.id.selfie_img);
//        back_feed=findViewById(R.id.back_feed);
//        continue_btn=findViewById(R.id.continue_btn);
//        sel_loc_text=findViewById(R.id.sel_loc_text);
//        select=findViewById(R.id.select);
//        pending_selfie=findViewById(R.id.pending_selfie);
//        click=findViewById(R.id.click);
//        pending_frnt=findViewById(R.id.pending_frnt);
//        pending_back=findViewById(R.id.pending_back);
//        pending_text_f=findViewById(R.id.pending_text_f);
//        pending_text_b=findViewById(R.id.pending_text_b);
//
//
//        sessionManager=new SessionManager(this);
//        System.out.println("sssseeess"+sessionManager.getRegId("userId"));
//        VerificationVoterIdFront.voterFrontid=null;
//        VerificationSelfie.selfie=null;
//        VerificationVoterIdback.voterBackid=null;
//
//
//        System.out.println("latttttttt"+edit_loc_st);
//
//        if (SelectLocation.lat_st!=0.0){
//            sel_loc_text.setText("Location Captured");
//            select.setText("Edit");
//            cont_st1="1";
//        }
//        if (ReviewPhoto.front_voter_st!=null){
//            pending_text_f.setVisibility(View.VISIBLE);
//            pending_text_f.setText("Pending");
//            pending_frnt.setVisibility(View.GONE);
//            cont_st2="2";
//
//        }
//        if (ReviewPhoto.back_voter_st!=null){
//            System.out.println("back_voterrrrr2222");
//
//            pending_text_b.setText("Pending");
//            pending_back.setVisibility(View.GONE);
//            pending_text_b.setVisibility(View.VISIBLE);
//            //  pending_back.setTextColor(Color.parseColor("#000"));
//            cont_st3="3";
//
//        }
//
//        if (ReviewPhoto.selfie_st!=null){
//            System.out.println("back_voterrrrr2222");
//
//            pending_selfie.setText("Pending");
//            click.setVisibility(View.INVISIBLE);
//            //  pending_back.setTextColor(Color.parseColor("#000"));
//            cont_st4="4";
//
//        }
//
//
//        System.out.println("statusssssscont"+cont_st1);
//
//        if (cont_st1==null||cont_st2==null||cont_st3==null){
//            continue_btn.setVisibility(View.GONE);
//
//
//        } else if (cont_st1.equals("1")&& cont_st2.equals("2")&&cont_st3.equals("3")&&cont_st4.equals("4")){
//            System.out.println("const_sttttt");
//            continue_btn.setVisibility(View.VISIBLE);
//
//        }else {
//            continue_btn.setVisibility(View.GONE);
//        }
//
//        if (VerificationVoterIdFront.voterFrontid!=null && VerificationVoterIdback.voterBackid!=null){
//
//            uploadImage();
//        }
//
//
//        sele_loc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent=new Intent(VerificationMain.this, Shop_Current_Location_Fragment.class);
//                startActivity(intent);
//            }
//        });
//
//
//
//        selfie_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent=new Intent(VerificationMain.this,VerificationSelfie.class);
//                startActivity(intent);
//                // Toast.makeText(VerificationMain.this,"In Progress", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        pending_frnt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (pending_frnt.getText().toString().equals("Upload")) {
//
//                    Intent intent = new Intent(VerificationMain.this, VerificationVoterIdFront.class);
//                    startActivity(intent);
//                }
//            }
//        });
//
//        pending_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (pending_back.getText().toString().equals("Upload")) {
//
//                    Intent intent = new Intent(VerificationMain.this, VerificationVoterIdback.class);
//                    startActivity(intent);
//                }
//            }
//        });
//
//
//        back_feed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(VerificationMain.this,VerifyAadhar.class);
//                startActivity(intent);
//            }
//        });
//
//
//        continue_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getImageDetails();
//                /*Intent intent=new Intent(VerificationMain.this,VerifyLast.class);
//                startActivity(intent);*/
//                final Dialog dialog = new Dialog(VerificationMain.this);
//                dialog.setContentView(R.layout.submit_details_popup);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
//                TextView ok=dialog.findViewById(R.id.ok);
//                TextView cancel=dialog.findViewById(R.id.cancel);
//
//             //   System.out.println("lattttlong"+SelectLocation.latitude+" "+SelectLocation.longitude);
//                ok.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        try {
//
//                            JSONObject jsonObject = new JSONObject();
//
//                            jsonObject.put("Latitude", "");
//                            jsonObject.put("Longitude", "");
//                            jsonObject.put("CVoterId", 1);
//                            jsonObject.put("CImageId", CImageId);
//                            jsonObject.put("CreatedBy", sessionManager.getRegId("userId"));
//                            jsonObject.put("UserId", sessionManager.getRegId("userId"));
//
//
//
//                            Login_post.login_posting(VerificationMain.this, Urls.AddUpdateLocationDetails, jsonObject, new VoleyJsonObjectCallback() {
//                                @Override
//                                public void onSuccessResponse(JSONObject result) {
//                                    System.out.println("111111user" + result);
//                                    try {
//                                        status = result.getString("Status");
//                                        LandingPageActivity.applicationId=status;
//
//                                        if ((status.equals("0"))){
//                                            Intent intent=new Intent(VerificationMain.this,VerifyLast.class);
//                                            startActivity(intent);
//                                        }
//
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//
//                                }
//                            });
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                        Intent intent=new Intent(VerificationMain.this, VerifyLast.class);
//                        startActivity(intent);
//                    }
//                });
//
//                cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.cancel();
//                    }
//                });
//
//                dialog.show();
//
//            }
//        });
//    }
//    private void getImageDetails() {
//
//        try {
//            JSONObject userRequestjsonObject = new JSONObject();
//
//            Login_post.login_posting(this, Urls.GetCImagelist, userRequestjsonObject, new VoleyJsonObjectCallback() {
//                @Override
//                public void onSuccessResponse(JSONObject result) {
//                    System.out.println("statussssss" + result);
//                    JSONArray jsonArray = new JSONArray();
//
//                    try {
//
//                        jsonArray = result.getJSONArray("captureImagelist");
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                            CImageId = jsonObject1.getString("CImageId");
//                            String Image1 = jsonObject1.getString("Image1");
//
//
//                        }
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//
//        private void uploadImage () {
//            final ProgressDialog progressDialog = ProgressDialog.show(this, "",
//                    "Loading....Please wait.");
//
//            progressDialog.show();
//
//
//            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Urls.AddUpdateVoterIdDetails,
//                    new Response.Listener<NetworkResponse>() {
//                        @Override
//                        public void onResponse(NetworkResponse response) {
//                            progressDialog.dismiss();
//                        /*Intent intent=new Intent(VerificationMain.this,VerificationMain.class);
//                        startActivity(intent);*/
//                        }
//                    },
//
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(VerificationMain.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                            progressDialog.dismiss();
//                        }
//                    }) {
//
//
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<>();
//                    params.put("CVoterId", "0");
//                    params.put("UserId", sessionManager.getRegId("userId"));
//                    params.put("CreatedBy", sessionManager.getRegId("userId"));
//                    System.out.println("params string " + params);
//
//                    return params;
//                }
//
//                @Override
//                protected Map<String, DataPart> getByteData() {
//                    Map<String, DataPart> params = new HashMap<>();
//                    long imagename = System.currentTimeMillis();
//
//                    Log.e(TAG, "Im here " + params);
//                    if (VerificationSelfie.imagedata != null) {
//                        params.put("VoterIdBack", new DataPart(imagename + ".png", VerificationVoterIdback.imagedata));
//                        params.put("VoterIdFront", new DataPart(imagename + ".png", VerificationVoterIdFront.imagedata));
//
//                    }
//                    System.out.println("params file " + params);
//                    return params;
//                }
//            };
//            volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(1000 * 60, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//            //adding the request to volley
//            Volley.newRequestQueue(this).add(volleyMultipartRequest);
//        }
//
//        @Override
//        public void onBackPressed () {
//            Intent intent = new Intent(VerificationMain.this, VerifyAadhar.class);
//            startActivity(intent);
//        }
//
//
//}
