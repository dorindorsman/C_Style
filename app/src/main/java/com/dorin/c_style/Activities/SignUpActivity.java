package com.dorin.c_style.Activities;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

import com.dorin.c_style.Firebase.FireBaseMyStorage;
import com.dorin.c_style.Firebase.FirebaseDB;
import com.dorin.c_style.Managers.UserDataManager;
import com.dorin.c_style.Objects.User;
import com.dorin.c_style.R;
import com.dorin.c_style.Validator;
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
   private TextInputLayout signUp_TIL_User_First_Name;
   private TextInputEditText signUp_TIET_User_Last_Name;
   private TextInputLayout signUp_TIL_User_Last_Name;
   private MaterialButton signUp_BTN_SignUp;

   public CircleImageView signUp_BTN_User_Image;

   private String urlIMG="https://firebasestorage.googleapis.com/v0/b/c-style-e408e.appspot.com/o/ProfilePictures%2Fprofilepicturedefault.png?alt=media&token=61903f23-556c-4525-a147-564360c0416e";

   Validator validatorFirstName;
   Validator validatorLastName;

   private UserDataManager myUserDataManager;
   private FirebaseAuth myAuth;
   private FireBaseMyStorage fireBaseMyStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        myAuth=FirebaseAuth.getInstance();
        fireBaseMyStorage=FireBaseMyStorage.getInstance();
        fireBaseMyStorage.setCallBack_uploadImg(callBack_uploadImg);
        myUserDataManager= UserDataManager.getInstance();



        findViews();
        initValidator();
        initButtons();
    }



    private void findViews() {
        signUp_BTN_User_Image=findViewById(R.id.signUp_BTN_User_Image);
        signUp_TIET_User_First_Name=findViewById(R.id.signUp_TIET_User_First_Name);
        signUp_TIL_User_First_Name=findViewById(R.id.signUp_TIL_User_First_Name);
        signUp_TIET_User_Last_Name=findViewById(R.id.signUp_TIET_User_Last_Name);
        signUp_TIL_User_Last_Name=findViewById(R.id.signUp_TIL_User_Last_Name);
        signUp_BTN_SignUp=findViewById(R.id.signUp_BTN_SignUp);
    }

    private void initValidator() {
        validatorFirstName=Validator.Builder.make(signUp_TIL_User_First_Name)
                .addWatcher(new Validator.Watcher_StringEmpty("Name Cannot Be Empty"))
                .addWatcher(new Validator.Watcher_String("Name Contains Only Characters"))
                .build();

        validatorLastName=Validator.Builder.make(signUp_TIL_User_Last_Name)
                .addWatcher(new Validator.Watcher_StringEmpty("Name Cannot Be Empty"))
                .addWatcher(new Validator.Watcher_String("Name Contains Only Characters"))
                .build();

    }

    private void initButtons() {

        signUp_BTN_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validatorFirstName.validateIt() && validatorLastName.validateIt()){
                    myUserDataManager.setMyUser(signUp_TIET_User_First_Name.getText().toString(),signUp_TIET_User_Last_Name.getText().toString(),urlIMG);
                    openActivity(MainUserActivity.class);
                }else{
                    Toast.makeText(SignUpActivity.this,"There Are Errors",Toast.LENGTH_LONG).show();
                }

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
        fireBaseMyStorage.uploadImageProfile(resultUri,myAuth.getCurrentUser().getUid(),this);

    }

    FireBaseMyStorage.CallBack_UploadImg callBack_uploadImg=new FireBaseMyStorage.CallBack_UploadImg() {
        @Override
        public void urlReady(String url, Activity activity) {
            urlIMG=url;
        }
    };


}