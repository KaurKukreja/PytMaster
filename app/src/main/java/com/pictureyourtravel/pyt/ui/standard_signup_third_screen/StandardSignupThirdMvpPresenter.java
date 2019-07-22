package com.pictureyourtravel.pyt.ui.standard_signup_third_screen;

import com.pictureyourtravel.pyt.ui.base.MvpPresenter;

public interface StandardSignupThirdMvpPresenter <V extends StandardSignupThirdMvpView> extends MvpPresenter<V> {
    void startLogin(String emailId);
}
