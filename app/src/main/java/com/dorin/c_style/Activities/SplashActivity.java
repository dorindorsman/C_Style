package com.dorin.c_style.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.dorin.c_style.R;

public class SplashActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT=5000;

    ImageView panel_IMG_Icon;
    TextView panel_LBL_Icon;
    TextView panel_LBL_Android;


    //Animations
    Animation topAnimation, bottomAnimation, middleAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        topAnimation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        middleAnimation= AnimationUtils.loadAnimation(this,R.anim.middle_animation);

        findViews();
        initAnimation();

        //Splash Screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainUserActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();

            }
        },SPLASH_TIME_OUT);

    }

    private void initAnimation() {
        panel_IMG_Icon.setAnimation(topAnimation);
        panel_LBL_Icon.setAnimation(middleAnimation);
        panel_LBL_Android.setAnimation(bottomAnimation);
    }

    private void findViews() {
        panel_IMG_Icon=findViewById(R.id.panel_IMG_Icon);
        panel_LBL_Icon=findViewById(R.id.panel_LBL_Icon);
        panel_LBL_Android=findViewById(R.id.panel_LBL_Android);
    }
}