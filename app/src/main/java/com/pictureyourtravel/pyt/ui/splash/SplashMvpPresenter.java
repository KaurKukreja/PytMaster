package com.pictureyourtravel.pyt.ui.splash;

import com.pictureyourtravel.pyt.ui.base.MvpPresenter;





public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {

    void decideNextActivity();

}