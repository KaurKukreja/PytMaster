package com.pictureyourtravel.pyt.data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefsHelper {
    public static final String MY_PREFS = "MY_PREFS";

    public static final String USER_ID = "USER_ID";
    public static final String USER_NAME = "USER_NAME";
    public static final String FB_ID = "FB_ID";
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String PROFILE_PICTURE = "PROFILE_PICTURE";
    public static final String SECURITY_TOKEN = "SECURITY_TOKEN";

    SharedPreferences mSharedPreferences;

    public SharedPrefsHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
    }

    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }




    public boolean getLoggedInMode() {
        return mSharedPreferences.getBoolean("IS_LOGGED_IN", false);
    }

    public void setLoggedInMode(boolean loggedIn) {
        mSharedPreferences.edit().putBoolean("IS_LOGGED_IN", loggedIn).apply();
    }















    public void putSecurityToken(String SecurityToken) {
        mSharedPreferences.edit().putString(SECURITY_TOKEN, SecurityToken).apply(); }

    public String getSecurityToken() {
        return mSharedPreferences.getString(SECURITY_TOKEN, null);
    }





     public void putUserId(String userId) {
        mSharedPreferences.edit().putString(USER_ID, userId).apply();
    }
    public String getUserId() {
        return mSharedPreferences.getString(USER_ID, null);
    }




    public void putUserName(String userName) {
        mSharedPreferences.edit().putString(USER_NAME, userName).apply();
    }
    public String getUserName() {
        return mSharedPreferences.getString(USER_NAME, null);
    }




    public void putProfilePicture(String profilePic) {
        mSharedPreferences.edit().putString(PROFILE_PICTURE, profilePic).apply();
    }
     public String getProfilePicture() {
         return mSharedPreferences.getString(PROFILE_PICTURE, null);
    }



}
