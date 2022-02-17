package com.dorin.c_style;

import android.app.Application;

import com.dorin.c_style.Firebase.FirebaseDB;
import com.dorin.c_style.Managers.UserDataManager;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        UserDataManager.initHelper(this);


    }
}