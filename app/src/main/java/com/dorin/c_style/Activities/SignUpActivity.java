package com.dorin.c_style.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.dorin.c_style.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity {

    ImageView panel_IMG_Signup;
    TextView panel_LBL_SignUp;
    TextView panel_LBL_txt;
    CircleImageView panel_BTN_User_Image;
    TextInputLayout panel_LBL_User_First_Name;
    TextInputLayout panel_LBL_User_Last_Name;
    MaterialButton panel_BTN_SignUp;


    FirebaseDatabase myDB;
    DatabaseReference myRef;
    FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        myDB=FirebaseDatabase.getInstance();
        myRef=myDB.getReference("users");

        myAuth=FirebaseAuth.getInstance();
        myAuth.getCurrentUser().getPhoneNumber();


        findViews();
        initButtons();
    }

    private void initButtons() {

        panel_BTN_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });
    }

    private void findViews() {
        panel_IMG_Signup=findViewById(R.id.panel_IMG_Signup);
        panel_LBL_SignUp=findViewById(R.id.panel_LBL_SignUp);
        panel_LBL_txt=findViewById(R.id.panel_LBL_txt);
        panel_BTN_User_Image=findViewById(R.id.panel_BTN_User_Image);
        panel_LBL_User_First_Name=findViewById(R.id.panel_LBL_User_First_Name);
        panel_LBL_User_Last_Name=findViewById(R.id.panel_LBL_User_Last_Name);
        panel_BTN_SignUp=findViewById(R.id.panel_BTN_SignUp);
    }
}