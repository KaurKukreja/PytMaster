package com.pictureyourtravel.pyt.ui.login;

import com.pictureyourtravel.pyt.ui.base.MvpView;

public interface LoginMvpView extends MvpView {


    void openMainActivity();
    void openForgotPasswordActivity();

    void setFonts();
    void setTexts();

    void loginAPI();
}
