package com.dorin.c_style.Activities;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dorin.c_style.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity {

   private ImageView signUp_IMG_icon;
   private TextView signUp_LBL_SignUp;
   private TextView signUp_LBL_Txt;
   private TextInputLayout signUp_LBL_User_First_Name;
   private TextInputLayout signUp_LBL_User_Last_Name;
   private MaterialButton signUp_BTN_SignUp;

   public CircleImageView signUp_BTN_User_Image;


 //  private CameraGalleryManager cameraGalleryManager;
   private FirebaseDatabase myDB;
   private DatabaseReference myRef;
   private FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

  //      cameraGalleryManager=CameraGalleryManager.getInstance();
        myDB=FirebaseDatabase.getInstance();
        myRef=myDB.getReference("users");
        myAuth=FirebaseAuth.getInstance();
        myAuth.getCurrentUser().getPhoneNumber();




        findViews();
        initButtons();
    }

    private void findViews() {
        signUp_IMG_icon=findViewById(R.id.signUp_IMG_icon);
        signUp_LBL_SignUp=findViewById(R.id.signUp_LBL_SignUp);
        signUp_LBL_Txt=findViewById(R.id.signUp_LBL_Txt);
        signUp_BTN_User_Image=findViewById(R.id.signUp_BTN_User_Image);
        signUp_LBL_User_First_Name=findViewById(R.id.signUp_LBL_User_First_Name);
        signUp_LBL_User_Last_Name=findViewById(R.id.signUp_LBL_User_Last_Name);
        signUp_BTN_SignUp=findViewById(R.id.signUp_BTN_SignUp);
    }

    private void initButtons() {

        signUp_BTN_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri resultUri = data.getData();
        signUp_BTN_User_Image.setImageURI(resultUri);

    }


}