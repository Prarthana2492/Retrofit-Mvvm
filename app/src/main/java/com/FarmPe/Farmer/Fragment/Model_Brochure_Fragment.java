package com.FarmPe.Farmer.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.FarmPe.Farmer.Activity.SignUpActivity;
import com.FarmPe.Farmer.Bean.AgriBean;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;
import org.json.JSONException;
import org.json.JSONObject;




public class Model_Brochure_Fragment extends Fragment {
    Fragment selectedFragment;

    LinearLayout  back_feed;
    public static String status;
    private ArrayAdapter<AgriBean> arrayAdapter;
    SessionManager sessionManager;
    String brochure;
    JSONObject lngObject;
    TextView privacypolicytxt;
    WebView terms;

    public static Model_Brochure_Fragment newInstance() {
        Model_Brochure_Fragment fragment = new Model_Brochure_Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.brochure_webview_layout, container, false);

        back_feed=view.findViewById(R.id.back_feed);
        privacypolicytxt=view.findViewById(R.id.toolbar_title);
        privacypolicytxt.setText("View Brochure");

        brochure = getArguments().getString("brochur_status");
        terms=view.findViewById(R.id.web_terms);
        terms.loadUrl(brochure);

        sessionManager = new SessionManager(getActivity());





        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {


                    selectedFragment = AddModelFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.frame_layout, selectedFragment);
                    transaction.commit();

                    return true;
                }
                return false;
            }
        });



        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedFragment = AddModelFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.frame_layout, selectedFragment);
                transaction.commit();

            }
        });



        return view;
    }
}

