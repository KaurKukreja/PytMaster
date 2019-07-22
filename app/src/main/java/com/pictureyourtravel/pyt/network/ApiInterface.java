package com.pictureyourtravel.pyt.network;

import com.pictureyourtravel.pyt.PytApp;
import com.pictureyourtravel.pyt.data.DataManager;
import com.pictureyourtravel.pyt.data.SharedPrefsHelper;
import com.pictureyourtravel.pyt.ui.forgot_password.ForgotPasswordModel;
import com.pictureyourtravel.pyt.ui.login.LoginModel;
import com.pictureyourtravel.pyt.ui.signup.FbLoginModel;
import com.pictureyourtravel.pyt.ui.standard_signup_fourth_screen.CategoriesModel;
import com.pictureyourtravel.pyt.ui.standard_signup_fourth_screen.EditProfileModel;
import com.pictureyourtravel.pyt.ui.standard_signup_fourth_screen.SignupWithEmailModel;
import com.pictureyourtravel.pyt.ui.standard_signup_third_screen.CoverPicturesModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.facebook.FacebookSdk.getApplicationContext;

public interface ApiInterface {
//
//    SharedPrefsHelper dataManager = new SharedPrefsHelper(getApplicationContext());
//    d
//
//



    @POST("signupWithEmail")
    Call<SignupWithEmailModel> SignupWithEmail (@Body RequestBody task);



    @POST("LoginWithEmail")
    Call<LoginModel> LoginWithEmail(@Body RequestBody body);


    @POST("forgotPassword")
    Call<ForgotPasswordModel> forgotPassword(@Header("x-access-token") String accesstoken, @Body RequestBody task);


    @GET("coverImages")
    Call<CoverPicturesModel> getCoverPictures();


    @POST("loginWithFacebook")
    Call<FbLoginModel> fbloginApi(@Body RequestBody task);



    @POST("loginWithGoogle")
    Call<FbLoginModel> googleLoginApi(@Body RequestBody task);



    @POST("edituserDetails")
    Call<EditProfileModel> edituserpicApi(@Body RequestBody body);




    @POST("get_categories_V102")
    Call<CategoriesModel> get_categories(@Body RequestBody nody);


}
