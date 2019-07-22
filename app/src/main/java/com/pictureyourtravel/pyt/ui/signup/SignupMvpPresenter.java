package com.pictureyourtravel.pyt.ui.signup;

import com.pictureyourtravel.pyt.ui.base.MvpPresenter;

public interface SignupMvpPresenter<V extends SignupMvpView> extends MvpPresenter<V> {

    void startLogin(String emailId);


}