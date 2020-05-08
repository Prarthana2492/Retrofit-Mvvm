package com.FarmPe.Oxkart.Adapter;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.FarmPe.Oxkart.Bean.Request_Class_Home_Page_Bean;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class Accepted_Adapter extends RecyclerView.Adapter<Accepted_Adapter.MyViewHolder>  {


    private List<Request_Class_Home_Page_Bean> productList;
    Activity activity;
    Fragment selectedFragment;
    public static String looinkgId;
    String classname;
    public static CardView cardView;
    Boolean shortlisted  = false;
    String toast_message;
    SessionManager sessionManager;



    public Accepted_Adapter(Activity activity, List< Request_Class_Home_Page_Bean> moviesList) {

        this.productList = moviesList;
        this.activity = activity;
        sessionManager = new SessionManager(activity);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView fav_request,model_image;
        public TextView brand_txt,location_txt;
        public CircleImageView profile_image;
        LinearLayout contact_details;




        public MyViewHolder(View view) {
            super(view);
            brand_txt=view.findViewById(R.id.brand_txt);
            location_txt=view.findViewById(R.id.location_txt);
            profile_image=view.findViewById(R.id.profile_image);
            contact_details=view.findViewById(R.id.contact_details);
            fav_request=view.findViewById(R.id.fav_request);
            model_image=view.findViewById(R.id.model_image);
//            item_layout=view.findViewById(R.id.item_layout);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mailbox_adapter_layout, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Request_Class_Home_Page_Bean products = productList.get(position);

        holder.contact_details.setVisibility(View.GONE);

        holder.brand_txt.setText(products.getLooking_fr_price() + ", " + products.getBrand() + ", " + products.getModel() + ", " + products.getPurchase_timline());
        holder.location_txt.setText(products.getLocation());


        Glide.with(activity).load(products.getProfile_image())
                .thumbnail(0.5f)
                //.crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.profile_image);


        Glide.with(activity).load(products.getModel_image())
                .thumbnail(0.5f)
                //.crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.model_image);


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

}
