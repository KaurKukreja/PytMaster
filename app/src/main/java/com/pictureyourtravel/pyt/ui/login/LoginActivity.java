package com.pictureyourtravel.pyt.ui.login;

import androidx.appcompat.app.AppCompatActivity;
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
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.pictureyourtravel.pyt.PytApp;
import com.pictureyourtravel.pyt.R;
import com.pictureyourtravel.pyt.data.DataManager;
import com.pictureyourtravel.pyt.network.ApiClient;
import com.pictureyourtravel.pyt.network.ApiInterface;
import com.pictureyourtravel.pyt.ui.forgot_password.ForgotPasswordActivity;
import com.pictureyourtravel.pyt.ui.home.MainActivity;
import com.pictureyourtravel.pyt.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity implements LoginMvpView {


    LoginPresenter     loginPresenter;
    CommonUtils        commonUtils;


    String             refreshedToken = "",emailAddress = "", password="";





    @BindView(R.id.back_rl)
    RelativeLayout back_rl;

    @BindView(R.id.login_bottom)
    TextView login_bottom;

    @BindView(R.id.login_tv)
    TextView login_tv;

    @BindView(R.id.email_login_tv)
    TextView email_login_tv;

    @BindView(R.id.pswd_tv)
    TextView pswd_tv;

    @BindView(R.id.email_login_et)
    TextView email_login_et;

    @BindView(R.id.pswd_et)
    TextView pswd_et;


  @BindView(R.id.forget_pswd_tv)
    TextView forget_pswd_tv;



   @BindView(R.id.show_tv)
    TextView show_tv;





   @BindView(R.id.email_view)
   View email_view;


   @BindView(R.id.pswd_view)
   View pswd_view;









    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);
        ButterKnife.bind(this);
        DataManager dataManager = ((PytApp) getApplication()).getDataManager();
        loginPresenter = new LoginPresenter(dataManager);
        loginPresenter.onAttach(this);
        commonUtils = CommonUtils.getInstance();
        setFonts();


        email_login_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                }else {
                    email_view.setBackgroundColor(0xFF1c4261);
                    email_login_et.setTextColor(0xFF000000);


                    if (pswd_et.getText().toString().equals("")) {
                        pswd_view.setBackgroundColor(0xFFbbbbbb);
                    } else {
                        pswd_view.setBackgroundColor(0xFF1c4261);
                    }



                }
            }
        });




     pswd_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                }else {
                    pswd_view.setBackgroundColor(0xFF1c4261);
                    pswd_et.setTextColor(0xFF000000);


                    if (email_login_et.getText().toString().equals("")) {
                        email_view.setBackgroundColor(0xFFbbbbbb);
                    } else {
                        email_view.setBackgroundColor(0xFF1c4261);
                    }



                }
            }
        });











    }


    @OnClick(R.id.login_bottom)
    public void openMain() {

        emailAddress=email_login_et.getText().toString().trim();
        password=pswd_et.getText().toString();
        if(emailAddress.isEmpty())
        {
            email_view.setBackgroundColor(0xFFff5050);

        }else {
            if(!commonUtils.isValidEmail(emailAddress))
            {
                email_view.setBackgroundColor(0xFFff5050);
                email_login_et.setTextColor(0xFFff5050);

//                commonUtils.defaultAlert(LoginActivity.this,"Please Enter a Valid Email Address");
            }else {
                if(password.isEmpty())
                {
                    pswd_view.setBackgroundColor(0xFFff5050);
//                    commonUtils.defaultAlert(LoginActivity.this,"Please Enter Password");
                }else {
                    loginAPI();
                }
            }

    }}


    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(this);
        startActivity(intent);
        finish();

    }

    @Override
    public void openForgotPasswordActivity() {
        Intent intent = ForgotPasswordActivity.getStartIntent(this);
        startActivity(intent);
//        finish();
    }


    @OnClick(R.id.forget_pswd_tv)
    public void ForgotPassword() {
        openForgotPasswordActivity();
    }



 @OnClick(R.id.back_rl)
    public void goBack() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }





    public void setFonts() {

        login_tv.setTypeface(commonUtils.setTypeFace_bold(LoginActivity.this));
        email_login_et.setTypeface(commonUtils.setTypeFace_bold(LoginActivity.this));
        email_login_tv.setTypeface(commonUtils.setTypeFace_semibold(LoginActivity.this));
        pswd_tv.setTypeface(commonUtils.setTypeFace_semibold(LoginActivity.this));
        pswd_et.setTypeface(commonUtils.setTypeFace_bold(LoginActivity.this));
        forget_pswd_tv.setTypeface(commonUtils.setTypeFace_regular(LoginActivity.this));
        show_tv.setTypeface(commonUtils.setTypeFace_regular(LoginActivity.this));
        pswd_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    @Override
    public void setTexts() {

    }




    @OnClick(R.id.email_login_et)
    public void setEmailViewColor() {


        email_login_et.setTextColor(0xFF000000);
        email_view.setBackgroundColor(0xFF1c4261);
    }

    @OnClick(R.id.pswd_et)
    public void setFirstNameViewColor() {


        pswd_view.setBackgroundColor(0xFF1c4261);
    }





















    @OnClick(R.id.show_tv)
public void showHideAction()
{
  if(!pswd_et.getText().toString().equals("")) {
      if (show_tv.getText().equals("Show")) {
          pswd_et.setInputType(InputType.TYPE_CLASS_TEXT);
          show_tv.setText("Hide");
      } else {
          pswd_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
          show_tv.setText("Show");
      }
  }
}



    @Override
    public void loginAPI() {

            refreshedToken = FirebaseInstanceId.getInstance().getToken();
//            commonProgressBar.show();

        commonUtils.printLog("Login API   refreshedToken",String.valueOf(refreshedToken));

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            JSONObject my = new JSONObject();
            try {

                my.put("email", emailAddress);
                my.put("password",password);
                my.put("appVersion",commonUtils.getAppVersion(LoginActivity.this));
                JSONObject token = new JSONObject();
                token.put("token", refreshedToken);
                token.put("device", "android");
                my.put("deviceToken", token);


                commonUtils.printLog("Login API Json data",String.valueOf(my));


            } catch (JSONException e) {
                e.printStackTrace();
            }


            RequestBody body = null;

            try {
                body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                        (new JSONObject(String.valueOf(my))).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Call<LoginModel> call = apiService.LoginWithEmail(body);
            Log.e("sign in ......", call.request().toString());
            call.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    commonUtils.printLog("Login API response",response.body().toString());
//                    commonProgressBar.hide();

                    if (response.isSuccessful()) {

                        if (response.body() != null) {

                            commonUtils.printLog("Login API Status",response.body().getStatus().toString());
                            commonUtils.printLog("Login API utype",response.body().getuType().toString());


                            if (response.body().getStatus().toString().equalsIgnoreCase("1")) {
//                                utils.show_analytics(LoginActivity.this, sharedPreferencePYT.getUserID(), sharedPreferencePYT.getUserName(), "", "Process_SignUp_Completed");
                                try {
//                                    Log.e("login email.........utype",response.body().getData().getuType()+"utype...");
//                                    GlobalConstant.privacy = response.body().getData().getuType();
//                                    sharedPreferencePYT.setUserID(response.body().getData().getId());
//                                    sharedPreferencePYT.setUserName(response.body().getData().getName());
//                                    if (response.body().getData().getPicture() != null) {
//                                        sharedPreferencePYT.setUserpic(response.body().getData().getPicture());
//                                    }
//                                    Log.e("after........utype",response.body().getuType()+"utype...");

                                     openMainActivity();

//                                                                       Intent i = new Intent(LoginActivity.this, SearchScreen.class);
//                                    i.putExtra("Text", "");
//                                    i.putExtra("mystring", "ddd");
//                                    startActivity(i);
//                                    finish();


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            if (response.body().getStatus().toString().equalsIgnoreCase("2")) {
                                commonUtils.defaultAlert(LoginActivity.this,"This email is not registered, Please enter a valid email.");

//                                   Toast.makeText(LoginActivity.this, response.body().getMsg().toString(), Toast.LENGTH_SHORT).show();
                            }
                            if (response.body().getStatus().toString().equalsIgnoreCase("3")) {

//                                commonUtils.defaultAlert(LoginActivity.this,"Your password is incorrect, Please try again.");
//                            Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(LoginActivity.this, response.body().getMsg().toString(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    }else {
//                        commonProgressBar.hide();
//                        listener3=new OnDialogButtonClickListener() {
//                            @Override
//                            public void onOkButtonClicked() {
//                                signInAPI();
//                            }
//
//                            @Override
//                            public void onNegativeButtonClicked() {
//
//                            }
//                        };
//
//                        utils.FullScreenServerAlert(LoginActivity.this,listener3);
                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
//                    commonProgressBar.hide();
//
//                listener3=new OnDialogButtonClickListener() {
//                    @Override
//                    public void onOkButtonClicked() {
//                        signInAPI();
//                    }
//
//                    @Override
//                    public void onNegativeButtonClicked() {
//
//                    }
//                };
//
//                utils.FullScreenServerAlert(LoginActivity.this,listener3);

                    commonUtils.printLog("login API failure",t.getMessage().toString());

                }
            });

    }





}
