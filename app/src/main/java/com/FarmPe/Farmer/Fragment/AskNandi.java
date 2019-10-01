package com.FarmPe.Farmer.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.Farmer.Activity.HomePage_With_Bottom_Navigation;
import com.FarmPe.Farmer.Bean.AgriBean;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;

import org.json.JSONObject;

public class AskNandi extends Fragment {
    Fragment selectedFragment;

    LinearLayout back_feed;
    public static String status;
    private ArrayAdapter<AgriBean> arrayAdapter;
    SessionManager sessionManager;
    String brochure;
    JSONObject lngObject;
    TextView privacypolicytxt;
    WebView terms;

    public static AskNandi newInstance() {
        AskNandi fragment = new AskNandi();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.brochure_webview_layout, container, false);
        HomePage_With_Bottom_Navigation.linear_bottom.setVisibility(View.GONE);

        back_feed=view.findViewById(R.id.back_feed);
        privacypolicytxt=view.findViewById(R.id.toolbar_title);
        privacypolicytxt.setText("View Brochure");

        brochure = getArguments().getString("brochur_status");

        terms=view.findViewById(R.id.web_terms);

        terms.getSettings().setJavaScriptEnabled(true);
        terms.getSettings().setPluginState(WebSettings.PluginState.ON);
        terms.setWebViewClient(new Callback());
        // terms.loadUrl("http://docs.google.com/gview?embedded=true&url=" + brochure);
        terms.loadUrl("https://webchat.botframework.com/embed/OxkartEn?s=VQ69g0k8DXo.AKCjyyk5ccMtFADrld2_Y6lA2vkFV7WIBhKzC492zN4");

        sessionManager = new SessionManager(getActivity());





        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {


                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("pdf", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });



        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("pdf", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });



        return view;
    }


    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, String url) {
            return (false);
        }
    }
}

