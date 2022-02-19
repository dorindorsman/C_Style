package com.dorin.c_style;

import android.app.Application;

import com.dorin.c_style.Firebase.FireBaseMyStorage;
import com.dorin.c_style.Managers.UserDataManager;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FireBaseMyStorage.initHelper(this);
        UserDataManager.initHelper(this);


    }
}