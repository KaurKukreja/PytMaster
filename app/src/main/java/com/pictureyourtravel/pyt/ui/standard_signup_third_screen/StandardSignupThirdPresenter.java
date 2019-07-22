package com.pictureyourtravel.pyt.ui.standard_signup_third_screen;

import com.pictureyourtravel.pyt.data.DataManager;
import com.pictureyourtravel.pyt.ui.base.BasePresenter;

public class StandardSignupThirdPresenter <V extends StandardSignupThirdMvpView> extends BasePresenter<V> implements StandardSignupThirdMvpPresenter<V> {

    public StandardSignupThirdPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void startLogin(String userID) {
        getDataManager().saveUserId(userID);
        getDataManager().setLoggedIn();
//        getMvpView().openMainActivity();
    }

}