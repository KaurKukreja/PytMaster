package com.pictureyourtravel.pyt.ui.signup;

import com.pictureyourtravel.pyt.data.DataManager;
import com.pictureyourtravel.pyt.ui.base.BasePresenter;

public class SignUpPresenter<V extends SignupMvpView> extends BasePresenter<V> implements SignupMvpPresenter<V> {

    public SignUpPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void startLogin(String userID) {
        getDataManager().saveUserId(userID);
        getDataManager().setLoggedIn();
//        getMvpView().openMainActivity();
    }

}