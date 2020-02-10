package com.FarmPe.Oxkart.Fragment;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.FarmPe.Oxkart.R;
import com.FarmPe.Oxkart.SessionManager;
import com.FarmPe.Oxkart.Urls;
import com.FarmPe.Oxkart.Volly_class.Crop_Post;
import com.FarmPe.Oxkart.Volly_class.VoleyJsonObjectCallback;
import org.json.JSONArray;
import org.json.JSONObject;




public class Edit_Verification_Fragment extends Fragment {

    Fragment selectedFragment;
    LinearLayout continue_btn, back_feed, back_voter_edit, linearLayout, edit_lay_selfie, select_loc_edit, front_voter_edit;
    ImageView capture_photo;
    TextView edit_location, click_selfie, upload_front, upload_voter_back, select_loc, upload_frnt_image, upload_voter_id, pending_selfie, select, sel_loc_text;
    SessionManager sessionManager;
    JSONArray get_location_array, imagelist_array, vote_list_array, vote_bk_list_array;
    String location_id;


    public static Edit_Verification_Fragment newInstance() {
        Edit_Verification_Fragment fragment = new Edit_Verification_Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.verify_edit_layout, container, false);

        //  Status_bar_change_singleton.getInstance().color_change(getActivity());


        back_feed = view.findViewById(R.id.back_feed);

        continue_btn = view.findViewById(R.id.continu_btn);


        click_selfie = view.findViewById(R.id.click_selfie);
        edit_lay_selfie = view.findViewById(R.id.edit_lay_selfie);


        select_loc_edit = view.findViewById(R.id.select_loc_edit);
        sel_loc_text = view.findViewById(R.id.sel_loc_text);
        select = view.findViewById(R.id.select);

        upload_front = view.findViewById(R.id.upload_front);
        upload_voter_back = view.findViewById(R.id.upload_voter_back);
        front_voter_edit = view.findViewById(R.id.front_voter_edit);
        back_voter_edit = view.findViewById(R.id.back_voter_edit);

        // select_location = view.findViewById(R.id.select_location);
        //        capture_photo = view.findViewById(R.id.capture_photo);
        //        edit_capture_photo = view.findViewById(R.id.edit_capture_photo);
        //        edit_loc = view.findViewById(R.id.edit_loc);


        linearLayout = view.findViewById(R.id.main_layout);
        sessionManager = new SessionManager(getActivity());


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("verify", FragmentManager.POP_BACK_STACK_INCLUSIVE);


//                selectedFragment = Verification_Aadhar_Fragment.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout1, selectedFragment);
//                transaction.commit();


            }

        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
//                    HomeMenuFragment.onBack_status = "farms";
//
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("verify", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;

                }

                return false;
            }
        });

        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId", sessionManager.getRegId("userId"));


            Crop_Post.crop_posting(getActivity(), Urls.Get_Shop_Location, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("dhfjfjd" + result);


                    try {
                        get_location_array = result.getJSONArray("clocationList");

                        for (int i = 0; i < get_location_array.length(); i++) {

                            JSONObject jsonObject1 = get_location_array.getJSONObject(i);


                            location_id = jsonObject1.getString("CLocationId");
                            String location_lat = jsonObject1.getString("Latitude");
                            String location_long = jsonObject1.getString("Longitude");
                            String location_captured_image = jsonObject1.getString("CapturedLocation");

                        }
                        if (get_location_array.length() == 0) {

                            select_loc_edit.setVisibility(View.GONE);
                            select.setVisibility(View.VISIBLE);
                            sel_loc_text.setText("Select Loction");

                        } else {

                            sel_loc_text.setText("Location Captured");
                            select.setVisibility(View.GONE);
                            select_loc_edit.setVisibility(View.VISIBLE);

                        }


                          continue_bkground();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
//
        } catch (Exception e) {
            e.printStackTrace();
        }


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("Edit_Fragment", "curr_loc_edit");
                bundle.putString("Edit_Location_Id", location_id);
                selectedFragment = Shop_Current_Location_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("map_locatn");;
                selectedFragment.setArguments(bundle);
                transaction.commit();

            }
        });


        select_loc_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("Edit_Fragment", "curr_loc_edit");
                bundle.putString("Edit_Location_Id", location_id);
                selectedFragment = Shop_Current_Location_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("map_locatn");
                selectedFragment.setArguments(bundle);
                transaction.commit();


            }
        });

        click_selfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("Selfie_Edit","edit_selfie");
                selectedFragment = Selfie_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("edit_selfie");
                selectedFragment.setArguments(bundle);
                transaction.commit();
            }
        });


        edit_lay_selfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("Selfie_Edit","edit_selfie");
                selectedFragment = Selfie_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("edit_selfie");
                selectedFragment.setArguments(bundle);
                transaction.commit();

            }
        });


        upload_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("VoterFront_Fragment","upload_front");
                selectedFragment = Voter_Id_Front_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("edit_voter");
                selectedFragment.setArguments(bundle);
                transaction.commit();


            }
        });


        front_voter_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("VoterFront_Fragment","edit_front_voter");
                selectedFragment = Voter_Id_Front_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("edit_voter");
                selectedFragment.setArguments(bundle);
                transaction.commit();


            }
        });

        upload_voter_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("VoterBack_Fragment","edit_back_voter");
                selectedFragment = Voter_Id_Back_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("edit_voter");
                selectedFragment.setArguments(bundle);
                transaction.commit();

            }
        });

        back_voter_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("VoterBack_Fragment","edit_back_voter");
                selectedFragment = Voter_Id_Back_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.addToBackStack("edit_voter");
                selectedFragment.setArguments(bundle);
                transaction.commit();

            }
        });


        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId", sessionManager.getRegId("userId"));


            Crop_Post.crop_posting(getActivity(), Urls.Get_Front_Voter_ID_Details, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dhfjfjd" + result);


                    try {

                        vote_list_array = result.getJSONArray("voterIdfrontLists");

                        for (int i = 0; i < vote_list_array.length(); i++) {

                            JSONObject jsonObject1 = vote_list_array.getJSONObject(i);
                            String image_id = jsonObject1.getString("CVoterId");
                            String image_view = jsonObject1.getString("VoterIdFront");


                        }


                        if (vote_list_array.length() == 0) {

                            front_voter_edit.setVisibility(View.GONE);
                            upload_front.setVisibility(View.VISIBLE);


                        } else {

                            front_voter_edit.setVisibility(View.VISIBLE);
                            upload_front.setVisibility(View.GONE);

                        }


                         continue_bkground();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId", sessionManager.getRegId("userId"));


            Crop_Post.crop_posting(getActivity(), Urls.Get_Back_Voter_ID_Details, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dhfjfjd" + result);


                    try {

                        vote_bk_list_array = result.getJSONArray("voterIdbackLists");

                        for (int i = 0; i < vote_bk_list_array.length(); i++) {


                            JSONObject jsonObject1 = vote_bk_list_array.getJSONObject(i);
                            String image_id = jsonObject1.getString("CVoterId");
                            String image_view = jsonObject1.getString("VoterIdBack");


//
//                            Glide.with(getActivity()).load(image_view)
//                                    .thumbnail(0.5f)
//                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                    .error(R.drawable.avatarmale)
//                                    .into(capture_photo);

                        }


                        if (vote_bk_list_array.length() == 0) {

                            back_voter_edit.setVisibility(View.GONE);
                            upload_voter_back.setVisibility(View.VISIBLE);


                        } else {


                            back_voter_edit.setVisibility(View.VISIBLE);
                            upload_voter_back.setVisibility(View.GONE);


                        }

                         continue_bkground();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId", sessionManager.getRegId("userId"));


            Crop_Post.crop_posting(getActivity(), Urls.Get_Image_Details, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dhfjfjd" + result);


                    try {

                        imagelist_array = result.getJSONArray("captureImagelist");

                        for (int i = 0; i < imagelist_array.length(); i++) {


                            JSONObject jsonObject1 = imagelist_array.getJSONObject(i);
                            String image_id = jsonObject1.getString("CImageId");
                            String image_view = jsonObject1.getString("Image1");


//
//                            Glide.with(getActivity()).load(image_view)
//                                    .thumbnail(0.5f)
//                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                    .error(R.drawable.avatarmale)
//                                    .into(capture_photo);
                        }


                        if (imagelist_array.length() == 0) {

                            click_selfie.setVisibility(View.VISIBLE);
                            edit_lay_selfie.setVisibility(View.GONE);

                        } else {

                            click_selfie.setVisibility(View.GONE);
                            edit_lay_selfie.setVisibility(View.VISIBLE);

                        }

                          continue_bkground();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }





        return view;

    }


    private void continue_bkground() {

        if (get_location_array.length() == 0 || imagelist_array.length() == 0 || vote_bk_list_array.length() == 0 || vote_list_array.length() == 0) {


            continue_btn.setBackgroundResource(R.drawable.grey_curved_border);


        } else {

            continue_btn.setBackgroundResource(R.drawable.border_filled_red_not_curved);


            continue_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertMessage();

                }
            });


        }

    }

    private void AlertMessage() { // alert dialog box

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity(),R.style.AppCompatAlertDialogStyle);
        alertDialogBuilder.setMessage("Do you want to submit the details for verification?");
        //alertDialogBuilder.setMessage(Html.fromHtml("<font size = '18dp'>Do You want to submit the details for verification?</font>"));
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                selectedFragment = Verification_Last_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout1, selectedFragment);
                transaction.commit();

            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.show();
    }
}


