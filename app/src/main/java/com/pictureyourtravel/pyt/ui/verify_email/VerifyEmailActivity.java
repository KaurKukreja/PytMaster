package com.pictureyourtravel.pyt.ui.verify_email;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pictureyourtravel.pyt.R;
import com.pictureyourtravel.pyt.utils.CommonUtils;

public class VerifyEmailActivity extends Activity implements VerifyEmailMvpView {

    CommonUtils commonUtils;

    @BindView(R.id.not_received_email_tv)
    TextView not_received_email_tv;


    @BindView(R.id.verify_header_tv)
    TextView verify_header_tv;


    @BindView(R.id.verify_desp_tv)
    TextView verify_desp_tv;


    @BindView(R.id.verify_btn)
    TextView verify_btn;

    @BindView(R.id.back_rl)
    RelativeLayout back_rl;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);
        ButterKnife.bind(this);
        commonUtils = CommonUtils.getInstance();
        setFonts();
    }




    @Override
    public void setFonts() {

        verify_header_tv.setTypeface(commonUtils.setTypeFace_bold(VerifyEmailActivity.this));
        verify_btn.setTypeface(commonUtils.setTypeFace_regular(VerifyEmailActivity.this));
        verify_desp_tv.setTypeface(commonUtils.setTypeFace_regular(VerifyEmailActivity.this));
        not_received_email_tv.setTypeface(commonUtils.setTypeFace_regular(VerifyEmailActivity.this));



        String have_account = "Didn't receive the email? ";
        String login = "<b><font color='#EE0000'>Resend Email</font></b>";
        not_received_email_tv.setText(Html.fromHtml(have_account + login));

    }



    @OnClick(R.id.back_rl)
    public void goBack()
    {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
