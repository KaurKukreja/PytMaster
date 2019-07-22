package com.pictureyourtravel.pyt.ui.standard_signup_first_screen;

import com.pictureyourtravel.pyt.data.DataManager;
import com.pictureyourtravel.pyt.ui.base.BasePresenter;

public class StandardSignupFirstPresenter <V extends StandardSignupFirstMvpView> extends BasePresenter<V> implements StandardSignupFirstMvpPresenter<V> {

    public StandardSignupFirstPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void startLogin(String userID) {
        getDataManager().saveUserId(userID);
        getDataManager().setLoggedIn();
//        getMvpView().openMainActivity();
    }

}