package com.pictureyourtravel.pyt.ui.standard_signup_fourth_screen;

import com.pictureyourtravel.pyt.ui.base.MvpPresenter;

public interface StandardSignupFourthMvpPresenter <V extends StandardSignupFourthMvpView> extends MvpPresenter<V> {
    void startLogin(String emailId);
}
