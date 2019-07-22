package com.pictureyourtravel.pyt.ui.standard_signup_second_screen;

import com.pictureyourtravel.pyt.ui.base.MvpPresenter;

public interface StandardSignupSecondMvpPresenter <V extends StandardSignupSecondMvpView> extends MvpPresenter<V> {
    void startLogin(String emailId);
}
