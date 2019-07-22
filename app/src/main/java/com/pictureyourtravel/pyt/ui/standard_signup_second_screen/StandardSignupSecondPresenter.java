package com.pictureyourtravel.pyt.ui.standard_signup_second_screen;

import com.pictureyourtravel.pyt.data.DataManager;
import com.pictureyourtravel.pyt.ui.base.BasePresenter;

public class StandardSignupSecondPresenter <V extends StandardSignupSecondMvpView> extends BasePresenter<V> implements StandardSignupSecondMvpPresenter<V> {

    public StandardSignupSecondPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void startLogin(String userID) {
        getDataManager().saveUserId(userID);
        getDataManager().setLoggedIn();
//        getMvpView().openMainActivity();
    }

}