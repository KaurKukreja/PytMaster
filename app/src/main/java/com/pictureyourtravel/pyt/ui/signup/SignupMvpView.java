package com.pictureyourtravel.pyt.ui.signup;

import com.pictureyourtravel.pyt.ui.base.MvpView;

public interface SignupMvpView extends MvpView {


    void openSignupScreen();

    void openMainActivity();
    void openLoginActivity();

    void callFbLogin();
    void callGoogleLogin();

    void setFonts();
    void setTexts();





}
