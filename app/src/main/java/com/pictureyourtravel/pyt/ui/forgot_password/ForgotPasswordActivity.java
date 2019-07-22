package com.pictureyourtravel.pyt.ui.forgot_password;

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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pictureyourtravel.pyt.PytApp;
import com.pictureyourtravel.pyt.R;
import com.pictureyourtravel.pyt.data.DataManager;
import com.pictureyourtravel.pyt.network.ApiClient;
import com.pictureyourtravel.pyt.network.ApiInterface;
import com.pictureyourtravel.pyt.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPasswordActivity extends Activity implements ForgotPasswordMvpView {
    CommonUtils commonUtils;
    DataManager dataManager;
    @BindView(R.id.forgot_back_rl)
    RelativeLayout forgot_back_rl;

    @BindView(R.id.verify_email_tv)
    TextView verify_email_tv;

@BindView(R.id.forgot_password_tv)
    TextView forgot_password_tv;

@BindView(R.id.forgot_emial_tv)
    TextView forgot_emial_tv;

@BindView(R.id.email_second_tv)
    TextView email_second_tv;


    @BindView(R.id.email_et)
    EditText email_et;


    @BindView(R.id.email_view)
    View email_view;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ForgotPasswordActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        commonUtils = CommonUtils.getInstance();
        dataManager = ((PytApp) getApplication()).getDataManager();
        setFonts();
    }


    @OnClick(R.id.forgot_back_rl)
    public void goBack() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @OnClick(R.id.verify_email_tv)
    public void verifyEmail() {
        String email = email_et.getText().toString();


        if (email.equals("")) {
            email_view.setBackgroundColor(0xFFFF4f51);

        } else {
            if(!commonUtils.isValidEmail(email))

            {
                email_view.setBackgroundColor(0xFFFF4f51);
                email_et.setTextColor(0xFFFF4f51);
            } else {
                callForgotPasswordAPI(email);
            }
        }

    }




    private void callForgotPasswordAPI(final String email) {


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        JSONObject my = new JSONObject();
        try {
            my.put("email",email);

            Log.e("sending forgot pswd",".........."+ String.valueOf(my));

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
        Log.e("database..token.",dataManager.getSecurityToken().toString());
        Call<ForgotPasswordModel> call = apiService.forgotPassword(dataManager.getSecurityToken(),body);
        commonUtils.printLog("forgot password url",call.request().toString());
//        commonUtils.printLog("forgot password header",call.request().header("x-access-token").toString());
        call.enqueue(new Callback<ForgotPasswordModel>() {
            @Override
            public void onResponse(Call<ForgotPasswordModel> call, Response<ForgotPasswordModel> response) {
                if(response.isSuccessful())
                {
//                    commonProgressBar.hide();

                    if(response.body().getStatus().toString().equals("1"))
                    {
                 Toast.makeText(ForgotPasswordActivity.this,"Email Sent Successfully",Toast.LENGTH_SHORT).show();
//                        utils.FullScreenDialogLayout(ForgotPasswordActivity.this,true,"Email Sent Successfully",response.body().getMsg().toString());
                    }
                    if(response.body().getStatus().toString().equals("0"))
                    {
                        Toast.makeText(ForgotPasswordActivity.this,"Email Not Registered",Toast.LENGTH_SHORT).show();
//                        utils.FullScreenDialogLayout(ForgotPasswordActivity.this,false,"Email Not Registered",response.body().getMsg().toString());
                    }

                }else {
                    commonUtils.printLog("forgot password failure","else.........");
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordModel> call, Throwable t) {


                commonUtils.printLog("forgot password failure",t.getMessage().toString());

            }
        });
    }


    @Override
    public void setFonts() {


        forgot_password_tv.setTypeface(commonUtils.setTypeFace_bold(ForgotPasswordActivity.this));

        forgot_emial_tv.setTypeface(commonUtils.setTypeFace_regular(ForgotPasswordActivity.this));
        email_second_tv.setTypeface(commonUtils.setTypeFace_light(ForgotPasswordActivity.this));

        email_et.setTypeface(commonUtils.setTypeFace_semibold(ForgotPasswordActivity.this));

    }
}