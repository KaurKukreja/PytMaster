package com.pictureyourtravel.pyt.ui.login;

import com.pictureyourtravel.pyt.ui.base.MvpPresenter;






public interface LoginMvpPresenter <V extends LoginMvpView> extends MvpPresenter<V> {

    void startLogin(String emailId);



}