package com.pictureyourtravel.pyt.ui.standard_signup_second_screen;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pictureyourtravel.pyt.PytApp;
import com.pictureyourtravel.pyt.R;
import com.pictureyourtravel.pyt.data.DataManager;
import com.pictureyourtravel.pyt.ui.standard_signup_third_screen.StandardSignupThirdActivity;
import com.pictureyourtravel.pyt.utils.CommonUtils;

import java.util.regex.Pattern;

public class StandardSignupSecondActivity extends Activity implements StandardSignupSecondMvpView {


    StandardSignupSecondPresenter standardSignupSecondPresenter;
    CommonUtils commonUtils;
    Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");

    Intent intent;
    String apiStatus = "", firstName = "", lastName = "", emailAddress = "", password = "";

    @BindView(R.id.next_tv)
    TextView next_tv;
    @BindView(R.id.create_password_tv)
    TextView create_password_tv;
    @BindView(R.id.email_second_tv)
    TextView email_second_tv;


    @BindView(R.id.pswd_tv)
    TextView pswd_tv;
    @BindView(R.id.show_tv)
    TextView show_tv;


    @BindView(R.id.pswd_et)
    TextView pswd_et;

    @BindView(R.id.back_rl)
    RelativeLayout back_rl;

    @BindView(R.id.second_pswd_view)
    View second_pswd_view;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, StandardSignupSecondActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_signup_second);


        ButterKnife.bind(this);
        DataManager dataManager = ((PytApp) getApplication()).getDataManager();
        standardSignupSecondPresenter = new StandardSignupSecondPresenter(dataManager);

        standardSignupSecondPresenter.onAttach(this);


        commonUtils = CommonUtils.getInstance();

        setFonts();
        setTexts();
        getIntentValues();



        pswd_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                }else {
                    second_pswd_view.setBackgroundColor(0xFF1c4261);



                }
            }
        });

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





    @OnClick(R.id.next_tv)
    public void openSignupThird() {
        password = pswd_et.getText().toString();

        if (password.isEmpty()) {
            second_pswd_view.setBackgroundColor(0xFFff5050);
        }

        else if(regex.matcher(password).find()&&password.length()>8)
        {
            openSignupThirdScreen();
        }
        else {
            commonUtils.defaultAlert(StandardSignupSecondActivity.this, "Password should have at least 1 character.");
        }
    }





    @OnClick(R.id.pswd_et)
    public void setFirstNameViewColor() {


        second_pswd_view.setBackgroundColor(0xFF1c4261);
    }


    @Override
    public void getIntentValues() {
        intent = getIntent();
        firstName = intent.getStringExtra("firstName");
        lastName = intent.getStringExtra("lastName");
        emailAddress = intent.getStringExtra("emailAddress");
        apiStatus = intent.getStringExtra("apiStatus");


        commonUtils.printLog("firstName", firstName);
        commonUtils.printLog("lastName", lastName);
        commonUtils.printLog("emailAddress", emailAddress);
        commonUtils.printLog("apiStatus", apiStatus);


    }

    @Override
    public void openSignupThirdScreen() {
        Intent intent = StandardSignupThirdActivity.getStartIntent(this);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("emailAddress", emailAddress);
        intent.putExtra("password", password);
        intent.putExtra("apiStatus", apiStatus);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
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


    @Override
    public void setFonts() {

        create_password_tv.setTypeface(commonUtils.setTypeFace_bold(StandardSignupSecondActivity.this));
        pswd_tv.setTypeface(commonUtils.setTypeFace_regular(StandardSignupSecondActivity.this));
        show_tv.setTypeface(commonUtils.setTypeFace_regular(StandardSignupSecondActivity.this));
        email_second_tv.setTypeface(commonUtils.setTypeFace_light(StandardSignupSecondActivity.this));
        pswd_et.setTypeface(commonUtils.setTypeFace_semibold(StandardSignupSecondActivity.this));
        next_tv.setTypeface(commonUtils.setTypeFace_regular(StandardSignupSecondActivity.this));

        pswd_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    @Override
    public void setTexts() {

    }
}
