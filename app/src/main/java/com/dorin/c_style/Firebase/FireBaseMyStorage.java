package com.dorin.c_style.Firebase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FireBaseMyStorage {

    public interface CallBack_UploadImg{
        void urlReady(String url,Activity activity);
    }


    private FirebaseStorage myStorage;
    public StorageReference storageReferenceProfile;
    public StorageReference storageReferenceItems;
    private static FireBaseMyStorage single_instance;
    private CallBack_UploadImg callBack_uploadImg;
    private Context context;

    private FireBaseMyStorage(Context context) {
        myStorage=FirebaseStorage.getInstance();
        storageReferenceProfile=myStorage.getReference().child("ProfilePictures");
        storageReferenceItems=myStorage.getReference().child("ItemsPictures");
        this.context=context;
    }

    public static FireBaseMyStorage getInstance() {
        return single_instance;
    }

    public static FireBaseMyStorage initHelper(Context context){
        if (single_instance == null) {
            single_instance = new FireBaseMyStorage(context);
        }
        return single_instance;
    }

    public void setCallBack_uploadImg(CallBack_UploadImg callBack_uploadImg){
        this.callBack_uploadImg=callBack_uploadImg;
    }


    public void uploadImageProfile(Uri resultUri , String id,Activity activity)
    {
        if (resultUri != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(activity);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // adding listeners on upload
            // or failure of image
            storageReferenceProfile.child(id).putFile(resultUri)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful()){
                                // Image uploaded successfully
                                // Dismiss dialog

                                progressDialog.dismiss();
                                Toast
                                        .makeText(activity,
                                                "Image Uploaded!",
                                                Toast.LENGTH_SHORT)
                                        .show();
                                storageReferenceProfile.child(id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        callBack_uploadImg.urlReady(uri.toString(),activity);
                                    }
                                });
                            }
                        }
                    })


                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(activity,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }


    public void uploadImageItem(Uri resultUri, String uid, String itemId, Activity activity)
    {
        if (resultUri != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(activity);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // adding listeners on upload
            // or failure of image
            storageReferenceItems.child(itemId).putFile(resultUri)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful()){
                                // Image uploaded successfully
                                // Dismiss dialog
                                progressDialog.dismiss();
                                Toast
                                        .makeText(activity,
                                                "Image Uploaded!",
                                                Toast.LENGTH_SHORT)
                                        .show();
                                storageReferenceItems.child(itemId).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        callBack_uploadImg.urlReady(uri.toString(),activity);
                                    }
                                });
                            }
                        }
                    })


                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                          //  progressDialog.dismiss();
                            Toast
                                    .makeText(activity,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }




}
