package com.FarmPe.Oxkart.Adapter;

import android.app.Activity;
import android.graphics.Color;
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

import com.FarmPe.Oxkart.Bean.ModelBean;
import com.FarmPe.Oxkart.Fragment.MapFragment;
import com.FarmPe.Oxkart.Fragment.Model_Brochure_Fragment;

import com.FarmPe.Oxkart.Fragment.Request_Favorite_Fragment;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.FarmPe.Oxkart.R;

import org.json.JSONObject;

import java.util.List;

public class Request_Favorite_Adapter extends RecyclerView.Adapter<Request_Favorite_Adapter.MyViewHolder>  {
    private List<ModelBean> productList;
    Activity activity;
    Fragment selectedFragment;
    SessionManager sessionManager;

    public LinearLayout linearLayout;
    public static LinearLayout next_arw;
    public static String first,tractor_id,model_id;
    public static CardView cardView;
    String brochure_pdf,looking_for_id;



    public Request_Favorite_Adapter(Activity activity, List<ModelBean> moviesList) {
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
           // hp_power=view.findViewById(R.id.hp_power);
            image=view.findViewById(R.id.imageff);
            select=view.findViewById(R.id.selectt);
            brochure=view.findViewById(R.id.brochure);
           fav_request=view.findViewById(R.id.fav_request);

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

        holder.fav_request.setImageResource(R.drawable.ic_star_filled);


        looking_for_id =products.getLookingForDetailsId();
        brochure_pdf = products.getPdf_brochure();

        holder.brand_name.setText(products.getBrand_name());
        holder.model.setText(products.getModel_name());


        String drive_type = products.getDrive_type()+",";
        String steering = products.getSteering()+",";
        String clutch_type=products.getClutch_type()+",";
        String transmission_type= products.getTransmission_type()+",";
       // String horse_power= products.getHorse_power();


        if (products.getSteering().equals("")){
            steering=" ";
        } if (products.getClutch_type().equals("")){
            clutch_type=" ";
        } if (products.getTransmission_type().equals("")) {
            transmission_type = " ";
//        }if (products.getHorse_power().equals("")){
//            horse_power=" ";
//        }
        } if (products.getDrive_type().equals("")){
            drive_type=" ";
        }

       // holder.hp_power.setText(drive_type+steering+clutch_type+transmission_type+horse_power)   ;
       // holder.hp_power.setText(products.getDrive_type()+ " , " + products.getSteering()+ " , " + products.getHorse_power() + " , " + products.getClutch_type()+ " , " + products.getTransmission_type());

        System.out.println("fhjhgf" + products.getPdf_brochure());
        System.out.println("fhjhgfdfsdfsdf" + products.getImage());

        Glide.with(activity).load(products.getImage())

                .thumbnail(0.5f)
                //.crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.image);


        holder.fav_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model_id = products.getId();

                holder.fav_request.setImageResource(R.drawable.ic_star);

                try{

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("ModelId",model_id);
                    jsonObject.put("LookingForDetailsId",looking_for_id);
                    jsonObject.put("IsShortlisted",false);
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
                                            .make(Request_Favorite_Fragment.linearLayout,"Your Request is Unfavorited", duration);
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


                                productList.remove(position);
                                notifyDataSetChanged();


                            }catch (Exception e){
                                e.printStackTrace();

                            }
                        }
                    });


                }catch(Exception e){
                    e.printStackTrace();
                }


            }
        });


        holder.brochure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("brochur_status",brochure_pdf);
                selectedFragment = Model_Brochure_Fragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("pdf");
                transaction.commit();

//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(products.getPdf_brochure()));
//                activity.startActivity(browserIntent);
            }
        });


        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model_id = products.getId();
                Bundle bundle=new Bundle();
                bundle.putString("navigation_from","fav_fragment");
                bundle.putString("MOD_ID",model_id);
                bundle.putString("LOOKING_ID",products.getLookingForDetailsId());

                selectedFragment = MapFragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("favo_req");
                transaction.commit();
            }
        });





//        if (productList.get(position).isSelected()){
//            holder.item.setBackgroundResource(R.drawable.grey_background_drawable);
//
//        }else {
//            holder.item.setBackgroundResource(R.drawable.border_transperent);
//        }


    }


    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh"+productList.size());
        return productList.size();
    }

}