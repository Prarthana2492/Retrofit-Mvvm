package com.FarmPe.Farmer.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.FarmPe.Farmer.Activity.SignUpActivity;
import com.FarmPe.Farmer.Activity.Status_bar_change_singleton;
import com.FarmPe.Farmer.Bean.AgriBean;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import org.json.JSONException;
import org.json.JSONObject;




public class PrivacyPolicyFragment extends Fragment {
    Fragment selectedFragment;

    LinearLayout  back_feed;
    public static String status;
    private ArrayAdapter<AgriBean> arrayAdapter;
    SessionManager sessionManager;

    JSONObject lngObject;
    TextView privacypolicytxt;
    WebView terms;

    public static PrivacyPolicyFragment newInstance() {
        PrivacyPolicyFragment fragment = new PrivacyPolicyFragment();
        return fragment;
    }

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.privacy_policy, container, false);

      //   HomePage_With_Bottom_Navigation.linear_bottom.setVisibility(View.GONE);
         Status_bar_change_singleton.getInstance().color_change(getActivity());
        back_feed=view.findViewById(R.id.back_feed);
        privacypolicytxt=view.findViewById(R.id.setting_tittle);
        privacypolicytxt.setText("Privacy Policy");
        terms=view.findViewById(R.id.web_terms);
        terms.loadUrl("http://farmpe.in/privacy.html");
        sessionManager = new SessionManager(getActivity());


    System.out.println("eewqewqe" + getArguments().getString("status"));

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    String status;
                    status=getArguments().getString("status");

                    if(status.equals("sign_Privacy")) {

                        System.out.println("jfnjgfjdsf");

//                        Intent intent=new Intent(getActivity(), SignUpActivity.class);
//                        startActivity(intent);
                        Intent myIntent = new Intent(getActivity(), SignUpActivity.class);
                        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(myIntent);


                    }else if(status.equals("setting_privacy")){

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("Policy", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }
                    return true;
                }
                return false;
            }
        });


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String status;
                status=getArguments().getString("status");

                if(status.equals("sign_Privacy")) {

                    Intent myIntent = new Intent(getActivity(), SignUpActivity.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(myIntent);


                }else if(status.equals("setting_privacy")){

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("Policy", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                }

            }
        });


        try {

            lngObject = new JSONObject(sessionManager.getRegId("language"));
            privacypolicytxt.setText(lngObject.getString("PrivacyPolicy"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}

