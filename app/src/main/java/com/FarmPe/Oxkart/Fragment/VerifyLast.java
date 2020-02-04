//package com.FarmPe.Oxkart.Fragment;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.FarmPe.Oxkart.R;
//import com.FarmPe.Oxkart.SessionManager;
//
//
//public class VerifyLast extends AppCompatActivity{
//
//    LinearLayout back_feed,selfie_img;
//    TextView phone_num;
//    TextView submit;
//    SessionManager sessionManager;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.verification_last);
//        submit=findViewById(R.id.submit);
//        back_feed=findViewById(R.id.back_feed);
//        phone_num=findViewById(R.id.phone_num);
//
//        sessionManager=new SessionManager(this);
//        back_feed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(VerifyLast.this, VerificationMain.class);
//                startActivity(intent);
//            }
//        });
//
//        phone_num.setText(sessionManager.getRegId("phone"));
//       // VerifyAadhar.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//
//        /*submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(VerifyLast.this,LandingPageActivity.class);
//                startActivity(intent);
//            }
//        });*/
//
//    }
//    @Override
//    public void onBackPressed() {
//        Intent intent=new Intent(VerifyLast.this,VerificationMain.class);
//        startActivity(intent);
//    }
//
//}
