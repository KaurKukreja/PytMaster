package com.pictureyourtravel.pyt.ui.standard_signup_first_screen;

import com.pictureyourtravel.pyt.ui.base.MvpPresenter;

public interface StandardSignupFirstMvpPresenter <V extends StandardSignupFirstMvpView> extends MvpPresenter<V> {
    void startLogin(String emailId);
}
