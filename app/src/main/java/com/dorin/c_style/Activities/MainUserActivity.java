package com.dorin.c_style.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dorin.c_style.Fregments.CalenderFragment;
import com.dorin.c_style.Fregments.ClosetFragment;
import com.dorin.c_style.Fregments.FavoritesFragment;
import com.dorin.c_style.Fregments.NotificationsFragment;
import com.dorin.c_style.Fregments.OutfitsFragment;
import com.dorin.c_style.Fregments.ProfileFragment;
import com.dorin.c_style.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainUserActivity extends AppCompatActivity {

      public static final int PROFILE = 0, CLOSET = 1, NEWITEM = 2, OUTFITS=3, FAVORITES=4,CALENDER=5,NOTIFICATIONS=6,SETTINGS=7,LOGOUT=8;

      private DrawerLayout panel_DrawerLayout;
      private CoordinatorLayout panel_Menu;
      private ConstraintLayout panel_Top_Menu;
      private LinearLayout panel_TopAppBar;
      private ImageView panel_IMG_Menu;
      private TextView panel_TextTitle;
      private FragmentContainerView panel_Fragment;
      private BottomAppBar panel_BottomAppBar;


      private NavigationView panel_TopNavigationView;
      private BottomNavigationView bottomNavigationView;
      private FloatingActionButton panel_BTN_AddItem;
      private MenuItem panel_BTN_menuProfile;


      private FragmentManager fragmentManager;
      private FragmentTransaction fragmentTransaction;

      private Fragment[] panel_fragments;

      private final int SIZE=9;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        findViews();
        setFragments();
        initColorMenu();
        initBtn();




    }


    private void findViews() {
        panel_DrawerLayout=findViewById(R.id.panel_DrawerLayout);
        findViewById(R.id.panel_IMG_Menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panel_DrawerLayout.openDrawer(GravityCompat.START);
            }
        });



        panel_Menu=findViewById(R.id.panel_Menu);
        panel_TopAppBar=findViewById(R.id.panel_TopAppBar);
        panel_IMG_Menu=findViewById(R.id.panel_IMG_Menu);
        panel_TextTitle=findViewById(R.id.panel_TextTitle);
        panel_Fragment=findViewById(R.id.panel_Fragment);
        panel_BottomAppBar=findViewById(R.id.panel_BottomAppBar);

        bottomNavigationView=findViewById(R.id.panel_BottomNavigationView);
        bottomNavigationView.setBackground(null);

        panel_BTN_AddItem=findViewById(R.id.panel_BTN_AddItem);

        panel_Top_Menu=findViewById(R.id.panel_Top_Menu);
        panel_TopNavigationView= findViewById(R.id.panel_TopNavigationView);
        panel_TopNavigationView.setItemIconTintList(null);
        panel_BTN_menuProfile=findViewById(R.id.menuProfile);
    }


    private void setFragments() {
        panel_fragments = new Fragment[SIZE];
        panel_fragments[PROFILE] = new ProfileFragment().setActivity(this);
        panel_fragments[CLOSET] = new ClosetFragment().setActivity(this);
     //   panel_fragments[NEWITEM] = new NewItemFragment().setActivity(this);
        panel_fragments[OUTFITS] = new OutfitsFragment().setActivity(this);
        panel_fragments[FAVORITES] = new FavoritesFragment().setActivity(this);
        panel_fragments[CALENDER] = new CalenderFragment().setActivity(this);
        panel_fragments[NOTIFICATIONS] = new NotificationsFragment().setActivity(this);

    }


    private void initColorMenu() {


        int navDefaultTextColor = Color.parseColor("#8A7156");
        int navDefaultIconColor = Color.parseColor("#8A7156");

        //Defining ColorStateList for menu item Text
        ColorStateList navMenuTextList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_focused},
                        new int[]{android.R.attr.state_pressed}
                },
                new int []{
                        Color.parseColor("#d8a673"),
                        navDefaultTextColor,
                        navDefaultTextColor,
                        navDefaultTextColor,
                        navDefaultTextColor
                }
        );

        //Defining ColorStateList for menu item Icon
        ColorStateList navMenuIconList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_focused},
                        new int[]{android.R.attr.state_pressed}
                },
                new int[] {
                        Color.parseColor("#d8a673"),
                        navDefaultIconColor,
                        navDefaultIconColor,
                        navDefaultIconColor,
                        navDefaultIconColor
                }
        );

        panel_TopNavigationView.setItemTextColor(navMenuTextList);
        panel_TopNavigationView.setItemIconTintList(navMenuIconList);


    }



    private void initBtn() {

        panel_BTN_AddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panel_TextTitle.setText(getString(R.string.additem));
                startActivity(new Intent(MainUserActivity.this,NewItemActivity.class));
                //new item
            }
        });


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.closest:
                        panel_TextTitle.setText(getString(R.string.closet));
                        replaceFragments(panel_fragments[CLOSET]);
                        break;
                    case R.id.favorites:
                        panel_TextTitle.setText(getString(R.string.favorites));
                        replaceFragments(panel_fragments[FAVORITES]);
                        break;
                    case R.id.calender:
                        panel_TextTitle.setText(getString(R.string.Calender));
                        replaceFragments(panel_fragments[CALENDER]);
                        break;
                    case R.id.outfits:
                        panel_TextTitle.setText(getString(R.string.Outfits));
                        replaceFragments(panel_fragments[OUTFITS]);
                        break;


                }

                return true;
            }
        });




        panel_TopNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.menuProfile:
                        panel_TextTitle.setText(getString(R.string.profile));
                        replaceFragments(panel_fragments[PROFILE]);
                        break;
                    case R.id.menuNotifications:
                        panel_TextTitle.setText(getString(R.string.notifications));
                        replaceFragments(panel_fragments[NOTIFICATIONS]);
                        break;
                    case R.id.Settings:
                        panel_TextTitle.setText(getString(R.string.setting));
                        replaceFragments(panel_fragments[SETTINGS]);
                        break;
                    case R.id.Logout:
                        AuthUI.getInstance()
                                .signOut(MainUserActivity.this)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @SuppressLint("RestrictedApi")
                                    public void onComplete(@NonNull Task<Void> task) {
                                        // user is now signed out
                                        startActivity(new Intent(MainUserActivity.this, LoginActivity.class));
                                        Toast.makeText(MainUserActivity.this, "Signed Out", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                        break;
                }
                return true;
            }
        });



    }


    private void replaceFragments(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.panel_Fragment, fragment, null);
        fragmentTransaction.commit();
    }

}