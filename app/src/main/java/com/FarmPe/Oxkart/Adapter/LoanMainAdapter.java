//package com.FarmPe.Farmer.Adapter;
//
//import android.app.Activity;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import com.FarmPe.Farmer.Bean.ListBean;
//
//import com.FarmPe.Farmer.R;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//
//import java.util.List;
//
//public class LoanMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
//    private List<ListBean> productList;
//    Activity activity;
//    Fragment selectedFragment;
//
//
//
//    public static CardView cardView;
//    public LoanMainAdapter(Activity activity, List<ListBean> moviesList) {
//        this.productList = moviesList;
//        this.activity=activity;
//    }
//
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        private TextView loan_text;
//        private LinearLayout loan_lay;
//        private ImageView loan_img;
//
//
//        public MyViewHolder(View view) {
//            super(view);
//            loan_text=view.findViewById(R.id.loan_text);
//            loan_img=view.findViewById(R.id.loan_img);
//            loan_lay=view.findViewById(R.id.loan_lay);
//            // maore_lay=view.findViewById(R.id.maore_lay);
//
//        }
//
//    }
//
//
//
//    public class MyViewHolderForMore extends RecyclerView.ViewHolder {
//
//        private TextView morecount;
//
//        public MyViewHolderForMore(View itemView) {
//            super(itemView);
//            morecount= itemView.findViewById(R.id.morecount);
//        }
//
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//
//        if(viewType==0) {
//            View itemView = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.loan_item, parent, false);
//
//            //  int width =  parent.getMeasuredWidth();
//            float height = (float) parent.getMeasuredHeight() /3;//(Width/Height)
//            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
//            params.height = Math.round(height);
//            itemView.setLayoutParams(params);
//
//            return new MyViewHolder(itemView);
//        }
//        else{
//            View itemView = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.loan_item_more1, parent, false);
//           /* int height = parent.getMeasuredHeight() / 3;
//            itemView.setMinimumHeight(height);*/
//            float height = (float) parent.getMeasuredHeight() /3;//(Width/Height)
//            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
//            params.height = Math.round(height);
//            return new MyViewHolderForMore(itemView);
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
//
//        if (holder.getItemViewType() == 0) {
//            MyViewHolder viewHolder0 = (MyViewHolder) holder;
//            final ListBean products1 = productList.get(position);
//
//           /* DisplayMetrics displayMetrics = new DisplayMetrics();
//            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//            int width_px = Resources.getSystem().getDisplayMetrics().widthPixels;
//            int height_px =Resources.getSystem().getDisplayMetrics().heightPixels;
//            int height_set=(int)(height_px*0.1);
//            System.out.println("height&Width"+width_px+","+height_px);
//            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width_px,height_set);
//             viewHolder0.loan_lay.setLayoutParams(parms);*/
//            viewHolder0.loan_text.setText(products1.getName());
//
//            if (products1.getName().equals("Insurance")) {
//
//                viewHolder0.loan_lay.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        LoanAdapter.insurance = null;
//                        selectedFragment = FarmerInsuranceFragment.newInstance();
//                        FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.frame_layout, selectedFragment);
//                        transaction.addToBackStack("home");
//                        transaction.commit();
//                    }
//                });
//
//               /* if (products1.getName().equals("Crop Loan")) {
//                    viewHolder0.loan_lay.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            LoanAdapter.loan = null;
//                            selectedFragment = CropLoanDetails.newInstance();
//                            FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
//                            transaction.replace(R.id.frame_layout, selectedFragment);
//                            transaction.addToBackStack("home");
//                            transaction.commit();
//                        }
//                    });*/
//                } else if (products1.getName().equals("Kisan Credit Card")) {
//
//                viewHolder0.loan_lay.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            LoanAdapter.kisan = null;
//                            selectedFragment = KisanCreditCard.newInstance();
//                            FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
//                            transaction.replace(R.id.frame_layout, selectedFragment);
//                            transaction.addToBackStack("home");
//                            transaction.commit();
//                        }
//                    });
//                }
//
//           /* viewHolder0.loan_lay.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    selectedFragment = LoansListFragment.newInstance();
//                    FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame_layout, selectedFragment);
//                    transaction.addToBackStack("home");
//                    transaction.commit();
//                }
//            });*/
//
//                Glide.with(activity).load(products1.getImage())
//
//                        .thumbnail(0.5f)
//                        .crossFade()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(viewHolder0.loan_img);
//            } else {
//
//                MyViewHolderForMore viewHolder2 = (MyViewHolderForMore) holder;
//                viewHolder2.morecount.setText("+" + HomeFragment.morecount);
//
//                viewHolder2.morecount.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        selectedFragment = LoansListFragment.newInstance();
//                        FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.frame_layout, selectedFragment);
//                        transaction.addToBackStack("home");
//                        transaction.commit();
//                    }
//                });
//
//            }
//
//        }
//
//    @Override
//    public int getItemViewType(int position) {
//
//        // Just as an example, return 0 or 2 depending on position
//        // Note that unlike in ListView adapters, types don't have to be contiguous
//        if (position==11 & HomeFragment.morecount!=0) {
//            System.out.println("my pos "+HomeFragment.morecount);
//            return 1;
//        }
//        else return 0;
//        //  return position % 2 * 2;
//    }
//
//
//
//
//
//
//    @Override
//    public int getItemCount() {
//        if(HomeFragment.morecount!=0)
//            return productList.size()+1;
//        else
//            return productList.size();
//    }
//}