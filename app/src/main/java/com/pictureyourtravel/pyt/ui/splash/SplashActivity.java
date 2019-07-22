package com.pictureyourtravel.pyt.ui.splash;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.pictureyourtravel.pyt.ui.signup.TutorialActivity;

import com.pictureyourtravel.pyt.PytApp;
import com.pictureyourtravel.pyt.R;
import com.pictureyourtravel.pyt.data.DataManager;
import com.pictureyourtravel.pyt.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends Activity implements SplashMvpView{

    SplashPresenter mSplashPresenter;
    CommonUtils commonUtils;




    @BindView(R.id.app_name_tv)
    TextView app_name_tv;

    @BindView(R.id.picture_plan_post_tv)
    TextView picture_plan_post_tv;



    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        ButterKnife.bind(this);


        DataManager dataManager = ((PytApp) getApplication()).getDataManager();

        mSplashPresenter = new SplashPresenter(dataManager);

        mSplashPresenter.onAttach(this);
        commonUtils = CommonUtils.getInstance();
        setFonts();

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSplashPresenter.decideNextActivity();
            }
        }, 2000);






    }


    @Override
    public void setFonts() {
        app_name_tv.setTypeface(commonUtils.setTypeFace_semibold(SplashActivity.this));
        picture_plan_post_tv.setTypeface(commonUtils.setTypeFace_regular(SplashActivity.this));

    }

    @Override
    public void openMainActivity() {
//        Intent intent = MainActivity.getStartIntent(this);
//        startActivity(intent);
//        finish();
//        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @Override
    public void openLoginActivity() {
        Intent intent = TutorialActivity.getStartIntent(this);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }



}
