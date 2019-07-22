package com.pictureyourtravel.pyt;

import android.app.Application;

import com.pictureyourtravel.pyt.data.DataManager;
import com.pictureyourtravel.pyt.data.SharedPrefsHelper;

public class PytApp extends Application {

    DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(getApplicationContext());
        dataManager = new DataManager(sharedPrefsHelper);

    }

    public DataManager getDataManager() {
        return dataManager;
    }

}