package com.dorin.c_style.Activities;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dorin.c_style.Firebase.FirebaseDB;
import com.dorin.c_style.Managers.UserDataManager;
import com.dorin.c_style.Objects.User;
import com.dorin.c_style.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity {

   private TextInputEditText signUp_TIET_User_First_Name;
   private TextInputEditText signUp_TIET_User_Last_Name;
   private MaterialButton signUp_BTN_SignUp;

   public CircleImageView signUp_BTN_User_Image;

   private String urlIMG="https://firebasestorage.googleapis.com/v0/b/c-style-e408e.appspot.com/o/ProfilePictures%2Fprofilepicturedefault.png?alt=media&token=61903f23-556c-4525-a147-564360c0416e";

   private FirebaseStorage myStorage;
   private StorageReference myStorageReference;
   private UserDataManager myUserDataManager;
   private FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        myAuth=FirebaseAuth.getInstance();
        myStorage=FirebaseStorage.getInstance();
        myStorageReference=myStorage.getReference().child("ProfilePictures").child(myAuth.getCurrentUser().getUid());
        myUserDataManager= UserDataManager.getInstance();



        findViews();
        initButtons();
    }

    private void findViews() {
        signUp_BTN_User_Image=findViewById(R.id.signUp_BTN_User_Image);
        signUp_TIET_User_First_Name=findViewById(R.id.signUp_TIET_User_First_Name);
        signUp_TIET_User_Last_Name=findViewById(R.id.signUp_TIET_User_Last_Name);
        signUp_BTN_SignUp=findViewById(R.id.signUp_BTN_SignUp);
    }

    private void initButtons() {

        signUp_BTN_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myUserDataManager.setMyUser(signUp_TIET_User_First_Name.getText().toString(),signUp_TIET_User_Last_Name.getText().toString(),urlIMG);
                openActivity(MainUserActivity.class);
            }
        });


        signUp_BTN_User_Image.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(SignUpActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

    }

    private void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri resultUri = data.getData();
        signUp_BTN_User_Image.setImageURI(resultUri);
        uploadImage(resultUri);
    }


    private void uploadImage(Uri resultUri)
    {
        if (resultUri != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // adding listeners on upload
            // or failure of image
            myStorageReference.putFile(resultUri)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful()){
                                // Image uploaded successfully
                                // Dismiss dialog
                                progressDialog.dismiss();
                                Toast
                                        .makeText(SignUpActivity.this,
                                                "Image Uploaded!",
                                                Toast.LENGTH_SHORT)
                                        .show();
                                myStorageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                            urlIMG=uri.toString();
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
                                    .makeText(SignUpActivity.this,
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