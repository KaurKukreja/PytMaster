package com.pictureyourtravel.pyt.ui.login;

import com.pictureyourtravel.pyt.data.DataManager;
import com.pictureyourtravel.pyt.ui.base.BasePresenter;

public class LoginPresenter <V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {


    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }




    @Override
    public void startLogin(String emailId) {

    }




}
