package com.pictureyourtravel.pyt.ui.base;

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

}