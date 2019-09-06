package com.FarmPe.Farmer.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.FarmPe.Farmer.Bean.ModelBean;
import com.FarmPe.Farmer.Fragment.Model_Brochure_Fragment;
import com.FarmPe.Farmer.Fragment.Request_Details_New;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Volly_class.Crop_Post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.FarmPe.Farmer.Bean.AddTractorBean;
import com.FarmPe.Farmer.Fragment.RequestFormFragment;
import com.FarmPe.Farmer.R;
import com.google.firebase.database.core.Constants;

import org.json.JSONObject;

import java.net.URI;
import java.util.List;
import java.util.concurrent.Callable;

public class AddModelAdapter extends RecyclerView.Adapter<AddModelAdapter.MyViewHolder>  {
    private List<ModelBean> productList;
    Activity activity;
    Fragment selectedFragment;
    SessionManager sessionManager;

    LinearLayout linearLayout;
   public static LinearLayout next_arw;
    public static String first,tractor_id,model_id;
    public static CardView cardView;
    ImageView fav_request;
    String brochure_pdf;
    public AddModelAdapter(Activity activity, List<ModelBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
            sessionManager = new SessionManager(activity);
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image,fav_request;
        public LinearLayout item;
        public TextView brand_name,select,model,hp_power,brochure;




        public MyViewHolder(View view) {
            super(view);

            brand_name=view.findViewById(R.id.brand_name);
            model=view.findViewById(R.id.model);
            hp_power=view.findViewById(R.id.hp_power);
            image=view.findViewById(R.id.imageff);
            select=view.findViewById(R.id.selectt);
            brochure=view.findViewById(R.id.brochure);
            fav_request=view.findViewById(R.id.fav_request);

            linearLayout=view.findViewById(R.id.layout);



        }

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.select_model_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ModelBean products = productList.get(position);


        model_id = products.getId();
        brochure_pdf = products.getPdf_brochure();

        holder.brand_name.setText(products.getBrand_name());
        holder.model.setText(products.getModel_name());
        holder.hp_power.setText(products.getDrive_type()+ " , " + products.getSteering()+ " , " + products.getHorse_power() + " , " + products.getClutch_type()+ " , " + products.getTransmission_type());

  System.out.println("fhjhgf" + products.getPdf_brochure());

        Glide.with(activity).load(products.getImage())

                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);


        holder.brochure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(products.getPdf_brochure().equalsIgnoreCase("")){
                    //  Toast.makeText(activity, "No Brochure ", Toast.LENGTH_SHORT).show();
                    int duration = 1000;
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "No Brochure", duration);
                    View snackbarView2 = snackbar.getView();
                    TextView tv = (TextView) snackbarView2.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(activity,R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snackbar.show();
                }else {
                    Bundle bundle = new Bundle();
                    bundle.putString("brochur_status", brochure_pdf);
                    selectedFragment = Model_Brochure_Fragment.newInstance();
                    FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.frame_layout, selectedFragment);
                    selectedFragment.setArguments(bundle);
                    transaction.addToBackStack("pdf");
                    transaction.commit();
                }
//                Bundle bundle = new Bundle();
//                bundle.putString("brochur_status",brochure_pdf);
//                selectedFragment = Model_Brochure_Fragment.newInstance();
//                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
//                transaction.add(R.id.frame_layout, selectedFragment);
//                selectedFragment.setArguments(bundle);
//                transaction.addToBackStack("pdf");
//                transaction.commit();

//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(products.getPdf_brochure()));
//                activity.startActivity(browserIntent);
            }
        });


        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tractor_id=products.getId();
                selectedFragment = Request_Details_New.newInstance();
                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("fourth");
                transaction.commit();
            }
        });


      holder.fav_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               holder.fav_request.setImageResource(R.drawable.ic_star_filled);

                request_fav();

            }
        });



//        if (productList.get(position).isSelected()){
//            holder.item.setBackgroundResource(R.drawable.grey_background_drawable);
//
//        }else {
//            holder.item.setBackgroundResource(R.drawable.border_transperent);
//        }


    }

    private void request_fav() {
        try{

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ModelId",model_id);
            jsonObject.put("LookingForDetailsId",AddFirstAdapter.looinkgId);
            jsonObject.put("IsShortlisted",true);
            jsonObject.put("CreatedBy",sessionManager.getRegId("userId"));
            System.out.println("gfjgfgjdfmmmmmmmmmmm" + jsonObject);

             Crop_Post.crop_posting(activity, Urls.Add_Favorites, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("gfjgfgjdf" + result);
                    try{

                        String status = result.getString("Status");

                            if(status.equals("1")){


                                int duration = 1000;
                                Snackbar snackbar = Snackbar
                                        .make(linearLayout, "Your Request is Favorited", duration);
                                View snackbarView2 = snackbar.getView();
                                TextView tv = (TextView) snackbarView2.findViewById(android.support.design.R.id.snackbar_text);
                                tv.setBackgroundColor(ContextCompat.getColor(activity,R.color.orange));
                                tv.setTextColor(Color.WHITE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                                    tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                else {
                                    tv.setGravity(Gravity.CENTER_HORIZONTAL);
                                }
                                snackbar.show();

                          //  Toast.makeText(activity, "Your request is favorited", Toast.LENGTH_SHORT).show();


                        }


                    }catch (Exception e){
                        e.printStackTrace();

                    }
                }
            });


        }catch(Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh"+productList.size());
        return productList.size();
    }

}