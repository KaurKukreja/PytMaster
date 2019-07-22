package com.pictureyourtravel.pyt.ui.standard_signup_fourth_screen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pictureyourtravel.pyt.ui.signup.FbLoginModel;

import java.util.List;

public class EditProfileModel {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public EditProfileModel(String msg,  Integer status,  List<FbLoginModel.Datum> data) {
        this.msg=msg;
        this.status = status;

    }
}
