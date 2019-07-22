package com.pictureyourtravel.pyt.ui.standard_signup_third_screen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoverPicturesModel {
    @SerializedName("data")
    @Expose
    private List<CoverPicturesData> data = null;

    public List<CoverPicturesData> getData() {
        return data;
    }

    public void setData(List<CoverPicturesData> data) {
        this.data = data;
    }


}

