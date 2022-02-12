package com.dorin.c_style.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dorin.c_style.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainUserActivity extends AppCompatActivity {

        BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        findViews();







    }

    private void findViews() {
        bottomNavigationView=findViewById(R.id.panel_BottomNavigationView);
        bottomNavigationView.setBackground(null);
    }
}