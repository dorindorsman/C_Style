//package com.dorin.c_style.Managers;
//
//
//import com.dorin.c_style.Activities.SignUpActivity;
//import android.Manifest;
//import android.app.Activity;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.os.Build;
//
//import androidx.annotation.RequiresApi;
//import androidx.core.content.ContextCompat;
//
//import com.github.dhaval2404.imagepicker.ImagePicker;
//import com.theartofdev.edmodo.cropper.CropImage;
//
//public class CameraGalleryManager {
//
//    public static final int CAMERA_REQUEST=100;
//    public static final int STORAGE_REQUEST=101;
//    String cameraPermission[]=new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};;
//    String storagePermission[]=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
//
//    private static CameraGalleryManager single_instance = null;
//    private Context context;
//
//    private CameraGalleryManager(Context context){ this.context=context;}
//
//    public static CameraGalleryManager initHelper(Context context) {
//        if (single_instance == null) {
//            single_instance = new CameraGalleryManager(context);
//        }
//        return single_instance;
//    }
//
//    public static CameraGalleryManager getInstance() {
//        return single_instance;
//    }
//
//
//
//
//
//    public void pickFromGallery(Activity activity) {
//
//        CropImage.activity().start(activity);
//
////        ImagePicker.Companion.with(activity)
////                .crop()	    			//Crop image(Optional), Check Customization for more option
////                .compress(1024)			//Final image size will be less than 1 MB(Optional)
////                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
////                .start();
//
//    }
//
//    public boolean checkStoragePermission() {
//        boolean result= ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
//        return result;
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    public void requestStoragePermission(Activity activity) {
//        activity.requestPermissions(storagePermission,STORAGE_REQUEST);
//    }
//
//
//    public boolean checkCameraPermission() {
//        boolean result1= ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
//        boolean result2= ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
//        return result1 && result2;
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    public void requestCameraPermission(Activity activity) {
//        activity.requestPermissions(cameraPermission,CAMERA_REQUEST);
//    }
//
//}
