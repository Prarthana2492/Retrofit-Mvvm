package com.FarmPe.Farmer.Fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.FarmPe.Farmer.Activity.LandingPageActivity;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import com.FarmPe.Farmer.Urls;
import com.FarmPe.Farmer.Volly_class.Crop_Post;
import com.FarmPe.Farmer.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONObject;

import static com.FarmPe.Farmer.Activity.LandingPageActivity.mBottomSheetBehavior5;


public class AaHelpFragment extends Fragment {
    BottomSheetDialog mBottomSheetDialog;
    View sheetView;
    Fragment selectedFragment;
    LinearLayout backfeed,feedback_lay,main_layout,privacy_lay,about_lay;
    TextView privacy,farmpe_abt,feedback;
    SessionManager sessionManager;
    EditText feedback_edit;
    JSONObject lngObject;
    String status,message;

    public static AaHelpFragment newInstance() {
        AaHelpFragment fragment = new AaHelpFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_a_help_layout, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        backfeed=view.findViewById(R.id.back_feed);
        feedback_lay=view.findViewById(R.id.feedback_lay);
        main_layout=view.findViewById(R.id.main_layout);
        privacy_lay=view.findViewById(R.id.privacy_lay);
        about_lay=view.findViewById(R.id.abt_farmpe_lay);
        feedback_edit=view.findViewById(R.id.feedback_edit);
        farmpe_abt=view.findViewById(R.id.farmpe_abt);
        privacy=view.findViewById(R.id.privacy);
        feedback=view.findViewById(R.id.feedback);


        sessionManager = new SessionManager(getActivity());
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);



        backfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                //    getFragmentManager().popBackStack("home_menu", android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    return true;

                }
                return false;
            }
        });


        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("status","setting_privacy");
                selectedFragment = PrivacyPolicyFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                selectedFragment.setArguments(bundle);
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("privacy");
                transaction.commit();

            }
        });

        farmpe_abt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                selectedFragment = AboutfarmpeFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, selectedFragment);
                transaction.addToBackStack("abt_farmpe");
                transaction.commit();

            }
        });


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mBottomSheetDialog = new BottomSheetDialog(getActivity());
                sheetView = getActivity().getLayoutInflater().inflate(R.layout.feedback_bottom_sheet, null);


                new KeyboardUtil(getActivity(), sheetView);
                TextView cancel = sheetView.findViewById(R.id.cancel_feedback);
                TextView save = sheetView.findViewById(R.id.save_feedback);
                feedback_edit = sheetView.findViewById(R.id.feedback_edit);
                feedback_edit.setFilters(new InputFilter[]{EMOJI_FILTER,new InputFilter.LengthFilter(100)});



                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(feedback_edit.getText().toString().equals("")){
                           // mBottomSheetDialog.dismiss();

                            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            //Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_LONG).show();
                            int duration = 1000;
                            Snackbar snackbar = Snackbar
                                    .make(mBottomSheetDialog.getWindow().getDecorView(), "Please Enter Description", duration);
                            View snackbarView = snackbar.getView();
                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                            tv.setTextColor(Color.WHITE);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            } else {
                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            snackbar.show();
                            mBottomSheetDialog.show();

                        }else{
                            feedback();
                        }

                    }
                });


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mBottomSheetDialog.dismiss();
                    }
                });
                mBottomSheetDialog.setContentView(sheetView);
                mBottomSheetDialog.show();
            }
        });



        return view;
    }

    public static InputFilter EMOJI_FILTER = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            boolean keepOriginal = true;
            StringBuilder sb = new StringBuilder(end - start);
            for (int index = start; index < end; index++) {
                int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                    return "";
                }
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        if (dstart == 0)
                            return "";
                    }
                }
                return null;
          /*  char c = source.charAt(index);
            if (isCharAllowed(c))
                sb.append(c);
            else
                keepOriginal = false;*/
            }
            if (keepOriginal)
                return null;
            else {
                if (source instanceof Spanned) {
                    SpannableString sp = new SpannableString(sb);
                    TextUtils.copySpansFrom((Spanned) source, start, sb.length(), null, sp, 0);
                    return sp;
                } else {
                    return sb;
                }
            }
        }
    };

    private void feedback() {



        try{

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("FeedbackType","feedback");
            jsonObject.put("FeedbackTitle","feedback");
            jsonObject.put("FeedbackDescription",feedback_edit.getText().toString());
            jsonObject.put("CreatedBy",sessionManager.getRegId("userId"));

            System.out.println("nnnnnnnnnnnnnnnaaaaaaaaa"+jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.AddFeedback, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("AddFeedbackkkkkkkkkkkkkkkkkkkkkkk"+result);

                    try{

                        status= result.getString("Status");
                        message = result.getString("Message");

                        if(!(status.equals("0"))){
                            //Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
                            int duration = 1000;
                            Snackbar snackbar = Snackbar
                                    .make(main_layout, "Your Feedback Submitted Successfully", duration);
                            View snackbarView = snackbar.getView();
                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                            tv.setTextColor(Color.WHITE);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            } else {
                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            snackbar.show();
                            selectedFragment = AaSettingFragment.newInstance();
                            FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_menu, selectedFragment);
                            transaction.commit();

                            mBottomSheetDialog.dismiss();


                        }else{

                            // Toast.makeText(getActivity(),"Your Feedback not Submitted ",Toast.LENGTH_SHORT).show();
                            int duration = 1000;
                            Snackbar snackbar = Snackbar
                                    .make(main_layout,"Your Feedback Not Submitted Successfully", duration);
                            View snackbarView = snackbar.getView();
                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            tv.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.orange));
                            tv.setTextColor(Color.WHITE);

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            } else {
                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            snackbar.show();


                        }


                    }catch (Exception e){
                        e.printStackTrace();

                    }


                }
            });



        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
