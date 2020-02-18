package com.FarmPe.Oxkart.Volly_class;

import android.app.Activity;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;


public class HomepageLogin_post {

    public static StringRequest stringRequest;
    public static ProgressBar progressBar;


    public static void login_posting(final Activity activity, String url, final JSONObject jsonObject, final VoleyJsonObjectCallback callback){


     /*   final ProgressDialog progressDialog = ProgressDialog.show(activity, "",
                "Loading....Please wait.");*/
        //   progressDialog.setContentView(R.layout.small_progress_bar);

        //     progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        JsonObjectRequest jobReq = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                      //  progressDialog.cancel();

                        try {
                            callback.onSuccessResponse(jsonObject);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },


                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                       // progressDialog.cancel();

                    }
                });
       /* jobReq.setRetryPolicy(new DefaultRetryPolicy(
                (int) TimeUnit.SECONDS.toMillis(2000),
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/

        VolleySingletonQuee.getInstance(activity).addToRequestQueue(jobReq);

    }

  }