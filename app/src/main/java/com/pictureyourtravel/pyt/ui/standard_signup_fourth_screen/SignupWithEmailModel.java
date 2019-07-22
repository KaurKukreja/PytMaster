package com.pictureyourtravel.pyt.ui.standard_signup_fourth_screen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignupWithEmailModel {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data {

        @SerializedName("_id")
        @Expose
        private String id;


        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("lastName")
        @Expose
        private String lastName;






        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("email")
        @Expose
        private String email;

        @SerializedName("picture")
        @Expose
        private String picture;
        @SerializedName("connection")
        @Expose
        private List<String> connection = null;

        @SerializedName("runTimeLocation")
        @Expose
        private List<RuntimeLocation> runTimeLocation = null;
        @SerializedName("deviceToken")
        @Expose
        private List<DeviceToken> deviceToken = null;
        @SerializedName("uType")
        @Expose
        private String uType="";




        public String getuType() {
            return uType;
        }

        public void setuType(String uType) {
            this.uType = uType;
        }




        public Data(String id, String firstName, String lastName, List<RuntimeLocation> runTimeLocation, String email, String password, List<DeviceToken> deviceToken) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.runTimeLocation = runTimeLocation;
            this.email = email;
            this.password = password;
            this.deviceToken = deviceToken;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }


        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }











        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List<RuntimeLocation> getRunTimeLocation() {
            return runTimeLocation;
        }

        public void setRunTimeLocation(List<RuntimeLocation> runTimeLocation) {
            this.runTimeLocation = runTimeLocation;
        }

        public List<DeviceToken> getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(List<DeviceToken> deviceToken) {
            this.deviceToken = deviceToken;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public List<String> getConnection() {
            return connection;
        }

        public void setConnection(List<String> connection) {
            this.connection = connection;
        }

    }
    public class DeviceToken {

        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("device")
        @Expose
        private String device;
        @SerializedName("_id")
        @Expose
        private String id;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }
    public class RuntimeLocation {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("fullName")
        @Expose

        private String fullName;
        @SerializedName("placeId")
        @Expose
        private String placeId;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;



        public RuntimeLocation(String id, String type, String fullName, String placeId,String imageUrl) {
            this.id = placeId;
            this.type = type;
            this.fullName = fullName;
            this.placeId = placeId;
            this.imageUrl = imageUrl;
        }

        public String getId() {
            return placeId;
        }

        public void setId(String id) {
            this.id = placeId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getPlaceId() {
            return placeId;
        }

        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }
        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

}
