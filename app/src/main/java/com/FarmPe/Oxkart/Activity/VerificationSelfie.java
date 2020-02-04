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
//public class VerificationSelfie extends AppCompatActivity{
//    static final int REQUEST_IMAGE_CAPTURE = 1;
//    private Camera mCamera;
//    ImageView button_capture,take_photo;
//    private CameraPreview mPreview;
//    final static private int NEW_PICTURE = 1;
//    FrameLayout preview;
//    ImageView imageView;
//    BottomSheetDialog mBottomSheetDialog;
//    View sheetView;
//    public static String selfie;
//    LinearLayout sele_loc,selfie_img;
//    public static byte[] imagedata;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.camera);
//        button_capture=findViewById(R.id.button_capture);
//        imageView = findViewById(R.id.image);
//        preview = (FrameLayout)findViewById(R.id.camera_preview);
//        button_capture.setVisibility(View.INVISIBLE);
//        int cameraid = findFrontFacingCamera();
//
//
//
//        selfie="selfie_st";
//        if(cameraid>=0)
//            mCamera=Camera.open(cameraid);
//      else  mCamera = getCameraInstance();
//
//        mCamera.setDisplayOrientation(90);
//        mPreview = new CameraPreview(VerificationSelfie.this, mCamera);
//        preview.addView(mPreview);
//      //  FaceDetector face_detector = new FaceDetector(background_image.getWidth(), background_image.getHeight(), 2);
//        mCamera.setFaceDetectionListener(new Camera.FaceDetectionListener() {
//            @Override
//            public void onFaceDetection(Camera.Face[] faces, Camera camera) {
//for(int i=0;i<faces.length;i++){
//    System.out.println("id "+faces[i].id+" score "+faces[i].score+" rect ");
//
//    if(faces[i].score>70)
//        button_capture.setVisibility(View.VISIBLE);
//    else
//        button_capture.setVisibility(View.INVISIBLE);
//}
//
//            }
//        });
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
//                Intent intent=new Intent(VerificationSelfie.this, ReviewPhoto.class);
//                intent.putExtra("EXTRA_SELFIE", "SELFIE");
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
//            Intent intent=new Intent(VerificationSelfie.this,ReviewPhoto.class);
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
//    private int findFrontFacingCamera() {
//
//        int cameraId = -1;
//        // Search for the front facing camera
//        int numberOfCameras = Camera.getNumberOfCameras();
//        for (int i = 0; i < numberOfCameras; i++) {
//            Camera.CameraInfo info = new Camera.CameraInfo();
//            Camera.getCameraInfo(i, info);
//            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
//                cameraId = i;
//                break;
//            }
//        }
//        return cameraId;
//
//    }
//
//}
