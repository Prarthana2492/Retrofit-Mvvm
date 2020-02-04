//package com.FarmPe.Oxkart.Activity;
//
//import android.content.Intent;
//import android.graphics.Color;
//import android.hardware.Camera;
//import android.os.Bundle;
//import android.support.design.widget.BottomSheetDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.FarmPe.Oxkart.R;
//
//
//public class VerificationVoterIdFront extends AppCompatActivity{
//    static final int REQUEST_IMAGE_CAPTURE = 1;
//    private Camera mCamera;
//    ImageView button_capture,take_photo;
//    private CameraPreview mPreview;
//    final static private int NEW_PICTURE = 1;
//    FrameLayout preview;
//    ImageView imageView;
//    BottomSheetDialog mBottomSheetDialog;
//    View sheetView;
//    public static String voterFrontid;
//    LinearLayout sele_loc,selfie_img;
//    public static byte[] imagedata;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.camera);
//        button_capture=findViewById(R.id.button_capture);
//        imageView = findViewById(R.id.image);
//        preview = (FrameLayout)findViewById(R.id.camera_preview);
//
//        mCamera = getCameraInstance();
//        mCamera.setDisplayOrientation(90);
//
//        mPreview = new CameraPreview(VerificationVoterIdFront.this, mCamera);
//        preview.addView(mPreview);
//        voterFrontid="front";
//
//
////        Button cancel2 = findViewById(R.id.cancel);
////        cancel2.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                preview.removeAllViews();
////                mCamera = getCameraInstance();
////                mCamera.setDisplayOrientation(90);
////                mPreview = new CameraPreview(VerificationSelfie.this, mCamera);
////                preview.addView(mPreview);
////            }
////        });
////
////        Button ok = findViewById(R.id.ok);
////        ok.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent=new Intent(VerificationSelfie.this,ReviewPhoto.class);
////                startActivity(intent);
////            }
////        });
//
//
//        button_capture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                mCamera.takePicture(null,null,mPicture);
//            }
//        });
//       // mCamera.open();
//
//        mBottomSheetDialog = new BottomSheetDialog(this);
//
//        sheetView = this.getLayoutInflater().inflate(R.layout.click_a_selfie, null);
//        ImageView cancel = sheetView.findViewById(R.id.cancel);
//        final TextView tips = sheetView.findViewById(R.id.tips);
//         final LinearLayout tips_layout = sheetView.findViewById(R.id.tips_layout);
//         final TextView title = sheetView.findViewById(R.id.title);
//
//
//        tips.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tips_layout.setVisibility(View.VISIBLE);
//                tips.setTextColor(Color.parseColor("#000000"));
//            }
//        });
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//               tips_layout.setVisibility(View.GONE);
//                tips.setTextColor(Color.parseColor("#090BCD"));
//
//                // mBottomSheetDialog.dismiss();
//            }
//        });
//
//        title.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(VerificationVoterIdFront.this, ReviewPhoto.class);
//                startActivity(intent);
//            }
//        });
//
//        mBottomSheetDialog.setContentView(sheetView);
//        mBottomSheetDialog.show();
//
//    }
//    public static Camera getCameraInstance(){
//        Camera c = null;
//        try {
//            c = Camera.open(); // attempt to get a Camera instance
//        }
//        catch (Exception e){
//            System.out.println("camera not avilable");
//            // Camera is not available (in use or does not exist)
//        }
//        return c; // returns null if camera is unavailable
//    }
//
//    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
//
//        @Override
//        public void onPictureTaken(byte[] data, Camera camera) {
//
////            mPreview = new CameraPreview(VerificationSelfie.this, mCamera);
////            preview.addView(mPreview);
//            imagedata=data;
//            Intent intent=new Intent(VerificationVoterIdFront.this,ReviewPhoto.class);
//            startActivity(intent);
//          //  imageView.setImageBitmap(BitmapFactory.decodeByteArray(data,0,data.length));
//
////            File rootPath = new File(Environment.getExternalStorageDirectory(), "XohriApp");
////            if(!rootPath.exists()) {
////                rootPath.mkdirs();
////            }
////
////            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
////
////            String fileName = timeStamp+".jpg";
////
////            File f = new File(rootPath +"/"+ fileName);
////
////            try {
////                FileOutputStream fos = new FileOutputStream(f);
////                fos.write(data);
////                fos.close();
////                filepath=rootPath +"/"+ fileName;
////            } catch (FileNotFoundException e) {
////                Log.d("TAG", "File not found: " + e.getMessage());
////            } catch (IOException e) {
////                Log.d("TAG", "Error accessing file: " + e.getMessage());
////            }
////
//        }
//    };
//
//    @Override
//    public void onBackPressed() {
//        Intent intent=new Intent(VerificationVoterIdFront.this, VerificationMain.class);
//        startActivity(intent);
//    }
//
//
//}
