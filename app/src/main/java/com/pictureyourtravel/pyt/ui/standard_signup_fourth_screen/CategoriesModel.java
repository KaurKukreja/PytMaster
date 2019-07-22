package com.pictureyourtravel.pyt.ui.standard_signup_fourth_screen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesModel {
    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<Category_Data> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Category_Data> getData() {
        return data;
    }

    public void setData(List<Category_Data> data) {
        this.data = data;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public class Category_Data {
        public boolean isChecked()
        {
            return isChecked;
        }

        public void setChecked(boolean checked)
        {
            isChecked = checked;
        }

        private  boolean isChecked;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("displayName")
        @Expose
        private String displayName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

    }

}
