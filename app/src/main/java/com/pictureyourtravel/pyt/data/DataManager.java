package com.pictureyourtravel.pyt.data;

public class DataManager {

    SharedPrefsHelper mSharedPrefsHelper;

    public DataManager(SharedPrefsHelper sharedPrefsHelper) {
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void clear() {
        mSharedPrefsHelper.clear();
    }



    public void saveUserId(String userId) {
        mSharedPrefsHelper.putUserId(userId);
    }

    public String getUserId() {
        return mSharedPrefsHelper.getUserId();
    }



     public void saveSecurityToken(String securityToken) {
        mSharedPrefsHelper.putSecurityToken(securityToken);
    }

    public String getSecurityToken() {
        return mSharedPrefsHelper.getSecurityToken();
    }






   public void saveUserName(String UserName) {
        mSharedPrefsHelper.putUserName(UserName);
    }

    public String getUserName() {
        return mSharedPrefsHelper.getUserName();
    }

    public void setLoggedIn() {
        mSharedPrefsHelper.setLoggedInMode(true);
    }

    public Boolean getLoggedInMode() {
        return mSharedPrefsHelper.getLoggedInMode();
    }



    public void saveUserpic(String profilePic) {
        mSharedPrefsHelper.putProfilePicture(profilePic);
    }


    public String getUserpic() {
        return mSharedPrefsHelper.getProfilePicture();
    }
}
