//package com.FarmPe.Farmer.Fragment;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import com.FarmPe.Farmer.R;
//
//
//public class AA  extends Fragment {
//
//    private onGreenclickListener mcallback;
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_green, container, false);
//
//        Button button = v.findViewById(R.id.button);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String message = "Hello green, im blue";
//                mcallback.messfromgreenfragent(message);
//
//            }
//        });
//
//        return v;
//
//    }
//
//    public interface onGreenclickListener{
//        void messfromgreenfragent(String text);
//
//
//    }
//}
