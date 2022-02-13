package com.dorin.c_style.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.dorin.c_style.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainUserActivity extends AppCompatActivity {

        BottomNavigationView bottomNavigationView;
        DrawerLayout drawerLayout;
        NavigationView panel_TopNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        findViews();







    }

    private void findViews() {
        bottomNavigationView=findViewById(R.id.panel_BottomNavigationView);
        bottomNavigationView.setBackground(null);

        drawerLayout=findViewById(R.id.panel_DrawerLayout);
        findViewById(R.id.panel_IMG_Menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        panel_TopNavigationView= findViewById(R.id.panel_TopNavigationView);
        panel_TopNavigationView.setItemIconTintList(null);
    }


}