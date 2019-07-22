package com.pictureyourtravel.pyt.ui.standard_signup_third_screen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoverPicturesData {
    @SerializedName("imageThumb")
    @Expose
    private String imageThumb;
    @SerializedName("imageLarge")
    @Expose
    private String imageLarge;

    public String getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
    }

    public String getImageLarge() {
        return imageLarge;
    }

    public void setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
    }
}