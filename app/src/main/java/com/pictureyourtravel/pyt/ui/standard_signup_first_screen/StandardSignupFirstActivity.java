package com.pictureyourtravel.pyt.ui.standard_signup_first_screen;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pictureyourtravel.pyt.PytApp;
import com.pictureyourtravel.pyt.R;
import com.pictureyourtravel.pyt.data.DataManager;
import com.pictureyourtravel.pyt.ui.standard_signup_second_screen.StandardSignupSecondActivity;
import com.pictureyourtravel.pyt.ui.standard_signup_third_screen.StandardSignupThirdActivity;
import com.pictureyourtravel.pyt.utils.CommonUtils;

public class StandardSignupFirstActivity extends Activity implements StandardSignupFirstMvpView {




    StandardSignupFirstPresenter         standardSignupFirstPresenter;
    CommonUtils                          commonUtils;
    Intent                               intent;

    String                                apiStatus="",firstName = "", lastName = "", emailAddress = "";

    @BindView(R.id.back_rl)
    RelativeLayout back_rl;

    @BindView(R.id.next_tv)
    TextView next_tv;

    @BindView(R.id.heading_tv)
    TextView heading_tv;

    @BindView(R.id.first_name_tv)
    TextView first_name_tv;

    @BindView(R.id.last_name_tv)
    TextView last_name_tv;

    @BindView(R.id.email_tv)
    TextView email_tv;

    @BindView(R.id.first_name_et)
    EditText first_name_et;

    @BindView(R.id.last_name_et)
    EditText last_name_et;

    @BindView(R.id.email_et)
    EditText email_et;


    @BindView(R.id.first_name_view)
    View first_name_view;


    @BindView(R.id.last_name_view)
    View last_name_view;


    @BindView(R.id.email_view)
    View email_view;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, StandardSignupFirstActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_signup_first);


        ButterKnife.bind(this);
        DataManager dataManager = ((PytApp) getApplication()).getDataManager();
        standardSignupFirstPresenter = new StandardSignupFirstPresenter(dataManager);
        standardSignupFirstPresenter.onAttach(this);
        commonUtils=CommonUtils.getInstance();

        setFonts();

        getIntentValues();




        first_name_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                }else {
                    first_name_view.setBackgroundColor(0xFF1c4261);


                    if (email_et.getText().toString().equals("")) {
                        email_view.setBackgroundColor(0xFFbbbbbb);
                    } else {
                        email_view.setBackgroundColor(0xFF1c4261);
                    }

                    if (last_name_et.getText().toString().equals("")) {
                        last_name_view.setBackgroundColor(0xFFbbbbbb);
                    } else {
                        last_name_view.setBackgroundColor(0xFF1c4261);
                    }
                }
            }
        });







        last_name_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                }else {
                    last_name_view.setBackgroundColor(0xFF1c4261);


                    if (first_name_et.getText().toString().equals("")) {
                        first_name_view.setBackgroundColor(0xFFbbbbbb);
                    } else {
                        first_name_view.setBackgroundColor(0xFF1c4261);
                    }

                    if (email_et.getText().toString().equals("")) {
                        email_view.setBackgroundColor(0xFFbbbbbb);
                    } else {
                        email_view.setBackgroundColor(0xFF1c4261);
                    }
                }
            }
        });







        email_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                }else {
                    email_view.setBackgroundColor(0xFF1c4261);


                    email_et.setTextColor(0xFF000000);




                    if (first_name_et.getText().toString().equals("")) {
                        first_name_view.setBackgroundColor(0xFFbbbbbb);
                    } else {
                        first_name_view.setBackgroundColor(0xFF1c4261);
                    }

                    if (last_name_et.getText().toString().equals("")) {
                        last_name_view.setBackgroundColor(0xFFbbbbbb);
                    } else {
                        last_name_view.setBackgroundColor(0xFF1c4261);
                    }







                }
            }
        });












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









    @Override
    public void setFonts() {


        heading_tv.setTypeface(commonUtils.setTypeFace_bold(StandardSignupFirstActivity.this));

        first_name_tv.setTypeface(commonUtils.setTypeFace_regular(StandardSignupFirstActivity.this));
        last_name_tv.setTypeface(commonUtils.setTypeFace_regular(StandardSignupFirstActivity.this));
        email_tv.setTypeface(commonUtils.setTypeFace_regular(StandardSignupFirstActivity.this));

        next_tv.setTypeface(commonUtils.setTypeFace_regular(StandardSignupFirstActivity.this));
        first_name_et.setTypeface(commonUtils.setTypeFace_semibold(StandardSignupFirstActivity.this));
        last_name_et.setTypeface(commonUtils.setTypeFace_semibold(StandardSignupFirstActivity.this));
        email_et.setTypeface(commonUtils.setTypeFace_semibold(StandardSignupFirstActivity.this));

    }






    @Override
    public void setTexts() {

    }










     @OnClick(R.id.email_et)
    public void setEmailViewColor() {


         email_et.setTextColor(0xFF000000);
         email_view.setBackgroundColor(0xFF1c4261);
     }

   @OnClick(R.id.first_name_et)
    public void setFirstNameViewColor() {


    first_name_view.setBackgroundColor(0xFF1c4261);
     }





    @OnClick(R.id.next_tv)
    public void openSignupSecond()
    {
        checkValidations();
    }


    @Override
    public void checkValidations() {

        firstName        = first_name_et.getText().toString();
        lastName          = last_name_et.getText().toString();
        emailAddress      =  email_et.getText().toString().trim();



        if(firstName.equals(""))
        {
            first_name_view.setBackgroundColor(0xFFff5050);


        }else {
//            if(lastName.equals(""))
//            {
//                last_name_view.setBackgroundColor(0xFFff5050);
//            }
//
//
//
//
//            else {
                if(emailAddress.isEmpty())
                {
                    email_view.setBackgroundColor(0xFFff5050);
                }else {


                    if(!commonUtils.isValidEmail(emailAddress))
                    {

                        email_view.setBackgroundColor(0xFFff5050);
                        email_et.setTextColor(0xFFff5050);
//                    commonUtils.defaultAlert(StandardSignupFirstActivity.this,"Please Enter a Valid Email Address");

                    }




                    else {
                        if(apiStatus.equals("signUP")) {
                            openSignupThirdScreen();
                        }else {
                            openSignupSecondScreen();
                        }
                    }




//                }

            }
        }



    }

    @Override
    public void openSignupSecondScreen() {
        Intent intent = StandardSignupSecondActivity.getStartIntent(this);
        intent.putExtra("firstName",firstName);
        intent.putExtra("lastName",lastName);
        intent.putExtra("emailAddress",emailAddress);
        intent.putExtra("apiStatus",apiStatus);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @Override
    public void openSignupThirdScreen() {
        Intent intent = StandardSignupThirdActivity.getStartIntent(this);
        intent.putExtra("firstName",firstName);
        intent.putExtra("lastName",lastName);
        intent.putExtra("emailAddress",emailAddress);
        intent.putExtra("password","");
        intent.putExtra("apiStatus",apiStatus);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

    }

    @Override
    public void getIntentValues() {
        intent=getIntent();
        apiStatus=intent.getStringExtra("apiStatus");
        firstName=intent.getStringExtra("firstName");
        emailAddress=intent.getStringExtra("emailAddress");

        first_name_et.setText(firstName);
        email_et.setText(emailAddress);



//        lastName=intent.getStringExtra("lastName");
//        emailAddress=intent.getStringExtra("emailAddress");

    }


}
