package com.pictureyourtravel.pyt.ui.signup;


import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.pictureyourtravel.pyt.PytApp;
import com.pictureyourtravel.pyt.R;
import com.pictureyourtravel.pyt.data.DataManager;

import com.pictureyourtravel.pyt.network.ApiClient;
import com.pictureyourtravel.pyt.network.ApiInterface;
import com.pictureyourtravel.pyt.ui.home.MainActivity;
import com.pictureyourtravel.pyt.ui.login.LoginActivity;
import com.pictureyourtravel.pyt.ui.standard_signup_first_screen.StandardSignupFirstActivity;
import com.pictureyourtravel.pyt.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static com.pictureyourtravel.pyt.utils.CommonUtils.serverClientId;

public class TutorialActivity extends Activity implements SignupMvpView {

    SignUpPresenter        signUpPresenter;
    CommonUtils            commonUtils;
    CallbackManager        callbackManager;
    GraphRequest           request;
    DataManager            dataManager;
    Boolean                fbClciked = false;
    String                 FACEBOOK_PERMISSION[] = {"email", "user_photos", "public_profile", "user_location", "user_tagged_places", "user_friends", "user_hometown"};
    String                 apiStatus="login",firstName = "", emailAddress="";

    private static final int      RC_SIGN_IN = 007;
    private GoogleSignInClient googleSignInClient ;


    @BindView(R.id.fb_tv)
    TextView fb_tv;


    @BindView(R.id.google_tv)
    TextView google_tv;


    @BindView(R.id.policy_info_tv_line_1)
    TextView policy_info_tv_line_1;


    @BindView(R.id.policy_info_tv_line_2)
    TextView policy_info_tv_line_2;


    @BindView(R.id.already_have_account_tv)
    TextView already_have_account_tv;


    @BindView(R.id.create_account_tv)
    TextView create_account_tv;

    @BindView(R.id.app_name_signup)
    TextView app_name_signup;

    @BindView(R.id.picture_plan_post_signup_tv)
    TextView picture_plan_post_signup_tv;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, TutorialActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        ButterKnife.bind(this);



        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(TutorialActivity.this.getResources().getString(R.string.serverClientId))
                .requestEmail()
                .build();


        googleSignInClient = GoogleSignIn.getClient(this, gso);

         dataManager = ((PytApp) getApplication()).getDataManager();
        signUpPresenter = new SignUpPresenter(dataManager);

        signUpPresenter.onAttach(this);


        commonUtils = CommonUtils.getInstance();

        setFonts();
        setTexts();
        LoginWithFacebook();
    }






    void LoginWithFacebook() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        final AccessToken accessToken = loginResult.getAccessToken();
                        request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(final JSONObject user, GraphResponse graphResponse) {

                                if (loginResult != null) {
                                    final String myToken = loginResult.getAccessToken().getToken();
                                    final String fb_Id = loginResult.getAccessToken().getUserId();
                                    String refreshedToken = "";
                                    try {
                                        refreshedToken = FirebaseInstanceId.getInstance().getToken();
                                    } catch (Exception e) {

                                    }

                                    String permi = String.valueOf(loginResult.getAccessToken().getPermissions());
                                    String dec = String.valueOf(loginResult.getAccessToken().getDeclinedPermissions());
                                      if (fbClciked == false) {


                                        fbClciked=true;
//                                        if (utils.isNetworkAvailable(TutorialActivity.this))

                                            callfblogin(fb_Id, myToken, refreshedToken);

//                                        else
//                                            utils.dialog_alert_Internet(TutorialActivity.this, 0);


                                    }

                                }
                            }
                        });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,first_name,last_name,name,email,gender,picture");
                        parameters.putString("locale", "en_US");
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        commonUtils.printLog("facebook error ",exception.getMessage());
                        commonUtils.defaultAlert(TutorialActivity.this, "Facebook Error!!");
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);




        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RC_SIGN_IN) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);
            }
        }
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount acct = completedTask.getResult(ApiException.class);
            String id = acct.getId();
            String idToken = acct.getIdToken();
            Log.e("display name:......... ", "display name: " + acct.getDisplayName());
            Log.e("id:......... ", "id " + id);
            Log.e("idToken:......... ", "idToken: " + idToken);
            String personName = acct.getDisplayName();
            String email = acct.getEmail();;

            LoginWithGoogle(id, email, personName, idToken);

            // Signed in successfully, show authenticated UI.

        } catch (Exception e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w("error.........", "signInResult:failed code=" + e.getStatusCode());
            Log.w("error.........", "signInResult:failed code=" + e.getMessage());
//            updateUI(null);
        }
    }






    private void LoginWithGoogle(final String id, final String email, final String personName, final String idToken) {

//        commonProgressBar.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        JSONObject my = new JSONObject();
        try {
            my.put("gmailId", id);
            my.put("email", email);
            my.put("name", personName);
//            my.put("picture", personPhotoUrl);
//            my.put("gToken", idToken);
            my.put("appVersion", commonUtils.getAppVersion(TutorialActivity.this));
            JSONObject token = new JSONObject();
            token.put("token", FirebaseInstanceId.getInstance().getToken());
            token.put("device", "android");
            my.put("deviceToken", token);
            Log.e("sending google data", String.valueOf(my));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = null;
        try {
            body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                    (new JSONObject(String.valueOf(my))).toString());
        } catch (JSONException e) {
            e.printStackTrace();
//            commonProgressBar.hide();
        }
        Call<FbLoginModel> call = apiService.googleLoginApi(body);
        Log.e("url google before res..", call.request().toString());
        call.enqueue(new Callback<FbLoginModel>() {
            @Override
            public void onResponse(Call<FbLoginModel> call, Response<FbLoginModel> response) {
//                commonProgressBar.hide();
//                try {
                if (response.isSuccessful()) {
                    Log.e("url google..after.", call.request().toString());
                    Log.e("url google..status.", response.body().getStatus().toString());


                    if (response.body() != null) {
                        if (response.body().getStatus().toString().equalsIgnoreCase("1")) {

//


//                            utils.show_analytics(TutorialActivity.this, sharedPreferencePYT.getUserID(), sharedPreferencePYT.getUserName(), "", "Process_GoogleLogin_Completed");



// try {
//                            GlobalConstant.Search_invite_Tooltipcount++;





                            dataManager.saveUserId(response.body().getUserId());
                           dataManager.saveUserName(response.body().getFirstName());
                           dataManager.saveSecurityToken(response.body().getToken());

                            Log.e("google..token.", response.body().getToken().toString());

                            if (response.body().getProfilePic() != null) {
                                dataManager.saveUserpic(response.body().getProfilePic());
                            }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }







//                            if (response.body().getRuntimeLocation() != null) {
//                                myList = response.body().getRuntimeLocation();
//                            }
//                            try {
//                                for (int i = 0; i < myList.size(); i++) {
//                                    //String id, String type, String fullName, String placeId
//                                    String loc = myList.get(i).getFullName();
//                                    if (loc.contains(",")) {
//                                        arr = loc.split(",");
//                                        fullname = arr[0];
//                                    } else {
//                                        fullname = loc;
//                                    }
//
//                                    RuntimeLocation runtimeLocation = new RuntimeLocation(
//                                            myList.get(i).getPlaceId(), myList.get(i).getType(), fullname, myList.get(i).getPlaceId(), myList.get(i).getImageUrl()
//                                    );
//                                    myList2.add(i, runtimeLocation);
//                                }
//
//                                String userType = response.body().getuType();
//                                GlobalConstant.privacy = userType;
//
////                                Log.e("privcay_tutoral.....google..", GlobalConstant.privacy);
//
//                            } catch (Exception e) {
//                            }
//
//                            method_getAddedLocations();
//
//                            commonProgressBar.hide();







                            if (response.body().getApiStatus().equals("signUP")) {

                                apiStatus="signUP";
                                firstName=response.body().getFirstName();
                                emailAddress=response.body().getEmail();
                                openSignupScreen();


//                                Intent i = new Intent(TutorialActivity.this, SignUpFinalStepActivity.class);
//                                i.putExtra("tag", "0");
//                                i.putExtra("fullName", response.body().getName());
//                                i.putExtra("picture", response.body().getProfilePic());
//                                i.putExtra("uId", response.body().getUserId());
//                                startActivity(i);
//                                overridePendingTransition(R.anim.enter_in, R.anim.enter_out);
//                                finish();





                            } else {


                                openMainActivity();



//                                Intent i = new Intent(TutorialActivity.this, SearchScreen.class);
//                                i.putExtra("Text", "another_text");
//                                i.putExtra("mystring", "vfxggh");
//                                startActivity(i);
//                                overridePendingTransition(R.anim.enter_in, R.anim.enter_out);
//                                finish();





                            }
                        }














                        else if (response.body().getStatus().toString().equalsIgnoreCase("0")) {
                            Toast.makeText(TutorialActivity.this,  response.body().getMsg().toString(), Toast.LENGTH_SHORT).show();
//                            utils.show_analytics(TutorialActivity.this, sharedPreferencePYT.getUserID(), sharedPreferencePYT.getUserName(), "", "Process_FacebookLogin_Failed");
//                            commonProgressBar.hide();
//                            callGoogleLoginErrorApi(id, idToken, response.body().getMsg(), response);  //callloginerrorapi();
                        } else {
                            Toast.makeText(TutorialActivity.this, response.body().getMsg().toString(), Toast.LENGTH_SHORT).show();
//                            commonProgressBar.hide();
//                            Log.e("status..else.............", response.body().getStatus().toString());
//                            utils.dialog_msg_show(TutorialActivity.this, response.body().getMsg());

                        }





                    } else {
//                        commonProgressBar.hide();
////                        Log.e("status", response.body().getStatus().toString());
//                        utils.dialog_msg_show(TutorialActivity.this, response.body().getMsg());
                        //null body

                    }
                } else {


                    Log.e("login error", "........."    +   String.valueOf(response.code()));
//                    commonProgressBar.hide();                 commonProgressBar.hide();
//                    listener2=new OnDialogButtonClickListener() {
//                        @Override
//                        public void onOkButtonClicked() {
//                            LoginWithGoogle(  id,  email,  personName,   idToken);
//                        }
//
//                        @Override
//                        public void onNegativeButtonClicked() {
//
//                        }
//                    };
//
//                    utils.FullScreenServerAlert(TutorialActivity.this,listener2);

                }
            }




            @Override
            public void onFailure(Call<FbLoginModel> call, Throwable t) {
//                commonProgressBar.hide();
//                listener2=new OnDialogButtonClickListener() {
//                    @Override
//                    public void onOkButtonClicked() {
//                        LoginWithGoogle(  id,  email,  personName,   idToken);
//                    }
//
//                    @Override
//                    public void onNegativeButtonClicked() {
//
//                    }
//                };
//
//                utils.FullScreenServerAlert(TutorialActivity.this,listener2);
//
            }

        });

    }




    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(this);
        startActivity(intent);
        finish();

    }
    private void callfblogin(final String fbId, final String accessToken, final String dev_token) {
//        commonProgressBar.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        JSONObject my = new JSONObject();
        try {
            my.put("fbId", fbId);
            my.put("accessToken", accessToken);
            my.put("appVersion", commonUtils.getAppVersion(TutorialActivity.this));
            JSONObject token = new JSONObject();
            token.put("token", dev_token);
            token.put("device", "android");
            my.put("deviceToken", token);
            Log.e("sending fb data/", String.valueOf(my));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = null;
//        try {
        try {
            body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                    (new JSONObject(String.valueOf(my))).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<FbLoginModel> call = apiService.fbloginApi(body);
        Log.e("fb login url......",call.request().toString());
        call.enqueue(new Callback<FbLoginModel>() {

            @Override
            public void onResponse(Call<FbLoginModel> call, Response<FbLoginModel> response) {






//                try {
                    if (response.isSuccessful()) {
//                        Log.e("url", call.request().toString());
                        fbClciked = false;
                        if (response.body() != null) {
                            Log.e("fb login status.","............"+ response.body().getStatus().toString());
                            Log.e("fb getApiStatus..","..............."+ response.body().getApiStatus().toString());
                            if (response.body().getStatus().toString().equals("1")) {






//                            utils.show_analytics(TutorialActivity.this, sharedPreferencePYT.getUserID(), sharedPreferencePYT.getUserName(), "", "Process_GoogleLogin_Completed");



// try {
//                            GlobalConstant.Search_invite_Tooltipcount++;



                                dataManager.saveUserId(response.body().getUserId());
                                dataManager.saveUserName(response.body().getFirstName());
                                if (response.body().getProfilePic() != null) {
                                    dataManager.saveUserpic(response.body().getProfilePic());
                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }







//                            if (response.body().getRuntimeLocation() != null) {
//                                myList = response.body().getRuntimeLocation();
//                            }
//                            try {
//                                for (int i = 0; i < myList.size(); i++) {
//                                    //String id, String type, String fullName, String placeId
//                                    String loc = myList.get(i).getFullName();
//                                    if (loc.contains(",")) {
//                                        arr = loc.split(",");
//                                        fullname = arr[0];
//                                    } else {
//                                        fullname = loc;
//                                    }
//
//                                    RuntimeLocation runtimeLocation = new RuntimeLocation(
//                                            myList.get(i).getPlaceId(), myList.get(i).getType(), fullname, myList.get(i).getPlaceId(), myList.get(i).getImageUrl()
//                                    );
//                                    myList2.add(i, runtimeLocation);
//                                }
//
//                                String userType = response.body().getuType();
//                                GlobalConstant.privacy = userType;
//
////                                Log.e("privcay_tutoral.....google..", GlobalConstant.privacy);
//
//                            } catch (Exception e) {
//                            }
//
//                            method_getAddedLocations();
//
//                            commonProgressBar.hide();







                                if (response.body().getApiStatus().equals("signUP")) {

                                    apiStatus="signUP";
                                    firstName=response.body().getFirstName();

                                    emailAddress=response.body().getEmail();
                                    openSignupScreen();


//                                Intent i = new Intent(TutorialActivity.this, SignUpFinalStepActivity.class);
//                                i.putExtra("tag", "0");
//                                i.putExtra("fullName", response.body().getName());
//                                i.putExtra("picture", response.body().getProfilePic());
//                                i.putExtra("uId", response.body().getUserId());
//                                startActivity(i);
//                                overridePendingTransition(R.anim.enter_in, R.anim.enter_out);
//                                finish();





                                } else {


                                    openMainActivity();



//                                Intent i = new Intent(TutorialActivity.this, SearchScreen.class);
//                                i.putExtra("Text", "another_text");
//                                i.putExtra("mystring", "vfxggh");
//                                startActivity(i);
//                                overridePendingTransition(R.anim.enter_in, R.anim.enter_out);
//                                finish();





                                }
                            }














                            else if (response.body().getStatus().toString().equalsIgnoreCase("0")) {
//                            utils.show_analytics(TutorialActivity.this, sharedPreferencePYT.getUserID(), sharedPreferencePYT.getUserName(), "", "Process_FacebookLogin_Failed");
//                            commonProgressBar.hide();
//                            callGoogleLoginErrorApi(id, idToken, response.body().getMsg(), response);  //callloginerrorapi();
                            } else {
//                            commonProgressBar.hide();
//                            Log.e("status..else.............", response.body().getStatus().toString());
//                            utils.dialog_msg_show(TutorialActivity.this, response.body().getMsg());

                            }





                        } else {
//                        commonProgressBar.hide();
////                        Log.e("status", response.body().getStatus().toString());
//                        utils.dialog_msg_show(TutorialActivity.this, response.body().getMsg());
                            //null body

                        }
                    } else {

                        fbClciked = false;
                        Log.e("login error", "........."+String.valueOf(response.code()));
//                    commonProgressBar.hide();                 commonProgressBar.hide();
//                    listener2=new OnDialogButtonClickListener() {
//                        @Override
//                        public void onOkButtonClicked() {
//                            LoginWithGoogle(  id,  email,  personName,   idToken);
//                        }
//
//                        @Override
//                        public void onNegativeButtonClicked() {
//
//                        }
//                    };
//
//                    utils.FullScreenServerAlert(TutorialActivity.this,listener2);

                    }
            }




            @Override
            public void onFailure(Call<FbLoginModel> call, Throwable t) {
//                commonProgressBar.hide();
//                listener2=new OnDialogButtonClickListener() {
//                    @Override
//                    public void onOkButtonClicked() {
//                        LoginWithGoogle(  id,  email,  personName,   idToken);
//                    }
//
//                    @Override
//                    public void onNegativeButtonClicked() {
//
//                    }
//                };
//
//                utils.FullScreenServerAlert(TutorialActivity.this,listener2);
//
            }

        });

    }



















    @OnClick(R.id.fb_tv)
    public void fbLogin() {
        callFbLogin();
    }



    @OnClick(R.id.google_tv)
    public void googleLogin() {
        callGoogleLogin();
    }










    @OnClick(R.id.create_account_tv)
    public void openSignup() {
        openSignupScreen();
    }


    @OnClick(R.id.already_have_account_tv)
    public void loginHere() {
        openLoginActivity();
    }





    @Override
    public void openSignupScreen() {
        Intent intent = StandardSignupFirstActivity.getStartIntent(this);
        intent.putExtra("apiStatus",apiStatus);
        intent.putExtra("firstName",firstName);
        intent.putExtra("emailAddress",emailAddress);

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }













    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(this);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @Override
    public void callFbLogin() {
        LoginManager.getInstance().logOut();
        LoginManager.getInstance().logInWithReadPermissions(TutorialActivity.this, Arrays.asList(FACEBOOK_PERMISSION));


    }

    @Override
    public void callGoogleLogin() {
        signOut();
        signIn();


    }

    private void signIn() {
//        showProgressDialog();
//        if (utils.isNetworkAvailable(TutorialActivity.this)) {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
//        } else {
//            utils.dialog_alert_Internet(TutorialActivity.this, 0);
//        }

    }

    private void signOut() {
        googleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
    @Override
    public void setFonts() {


        fb_tv.setTypeface(commonUtils.setTypeFace_regular(TutorialActivity.this));
        google_tv.setTypeface(commonUtils.setTypeFace_regular(TutorialActivity.this));
        create_account_tv.setTypeface(commonUtils.setTypeFace_regular(TutorialActivity.this));
        policy_info_tv_line_1.setTypeface(commonUtils.setTypeFace_light(TutorialActivity.this));
        policy_info_tv_line_2.setTypeface(commonUtils.setTypeFace_light(TutorialActivity.this));
        already_have_account_tv.setTypeface(commonUtils.setTypeFace_regular(TutorialActivity.this));


    }

    @Override
    public void setTexts() {

        String first = "By tapping on Facebook, Google & Create Account buttons, I agree to Picture Your Travel ";
        String second = "<b><font color='#EE0000'>privacy policy</font></b>";
        String third = " and ";
        String fourth = "<b><font color='#EE0000'>terms of use.</font></b>";


        policy_info_tv_line_1.setText(Html.fromHtml(first + second + third + fourth));
//        policy_info_tv_line_1.setText("By tapping on Facebook, Google & Create Account buttons, I agree to Picture Your Travel privacy policy and terms of use.");
        policy_info_tv_line_2.setText("We don't post anything to Facebook.");


        String have_account = "Already have an account? ";
        String login = "<b><font color='#EE0000'>Log In here.</font></b>";
        already_have_account_tv.setText(Html.fromHtml(have_account + login));


    }
}