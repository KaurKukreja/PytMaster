package com.pictureyourtravel.pyt.ui.standard_signup_fourth_screen;

import com.pictureyourtravel.pyt.data.DataManager;
import com.pictureyourtravel.pyt.ui.base.BasePresenter;

public class StandardSignupFourthPresenter <V extends StandardSignupFourthMvpView> extends BasePresenter<V> implements StandardSignupFourthMvpPresenter<V> {

    public StandardSignupFourthPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void startLogin(String userID) {
        getDataManager().saveUserId(userID);
        getDataManager().setLoggedIn();
//        getMvpView().openMainActivity();
    }

}