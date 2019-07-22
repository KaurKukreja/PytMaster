package com.pictureyourtravel.pyt.ui.standard_signup_fourth_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
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
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.pictureyourtravel.pyt.PytApp;
import com.pictureyourtravel.pyt.R;
import com.pictureyourtravel.pyt.data.DataManager;
import com.pictureyourtravel.pyt.network.ApiClient;
import com.pictureyourtravel.pyt.network.ApiInterface;
import com.pictureyourtravel.pyt.ui.home.MainActivity;
import com.pictureyourtravel.pyt.ui.standard_signup_third_screen.StandardSignupThirdActivity;
import com.pictureyourtravel.pyt.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StandardSignupFourthActivity extends Activity implements StandardSignupFourthMvpView {
    StandardSignupFourthPresenter standardSignupFourthPresenter;
    CommonUtils commonUtils;
    DataManager dataManager;
//    InterestAdapter interestAdapter;

    Intent intent;
    String                                refreshedToken = "",  apiStatus="",firstName = "", lastName = "", emailAddress = "", password="",coverImage="",profilePicture="",bioDesp="",bioLink="";

    @BindView(R.id.back_rl)
    RelativeLayout back_rl;

    @BindView(R.id.heading_fourth_tv)
    TextView heading_fourth_tv;

    @BindView(R.id.first_name_fourth_tv)
    TextView first_name_fourth_tv;

    @BindView(R.id.bio_tv)
    TextView bio_tv;

    @BindView(R.id.get_started_tv)
    TextView get_started_tv;
    @BindView(R.id.choose_interest_tv)
    TextView choose_interest_tv;
    @BindView(R.id.choose_interest_desp_tv)
    TextView choose_interest_desp_tv;

    @BindView(R.id.bio_desp_fourth_et)
    EditText bio_desp_fourth_et;

    @BindView(R.id.bio_link_fourth_et)
    EditText bio_link_fourth_et;


    @BindView(R.id.bio_link_view)
    View bio_link_view;


    @BindView(R.id.bio_desp_view)
    View bio_desp_view;



//    @BindView(R.id.interest_group)
//    ChipGroup interest_group;

//   @BindView(R.id.interest_chips_rc_view)
//   RecyclerView interest_chips_rc_view;




    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, StandardSignupFourthActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_signup_fourth);
        FirebaseApp.initializeApp(this);
        ButterKnife.bind(this);
         dataManager = ((PytApp) getApplication()).getDataManager();
        standardSignupFourthPresenter = new StandardSignupFourthPresenter(dataManager);
        standardSignupFourthPresenter.onAttach(this);
        commonUtils=CommonUtils.getInstance();
        setFonts();
        setTexts();
        getIntentValues();
        callCategories();



        bio_link_fourth_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    if(bio_link_fourth_et.getText().toString().equals(""))
                    {
                        bio_link_view.setBackgroundColor(0xFFbbbbbb);
                    }else {
                        bio_link_view.setBackgroundColor(0xFF1c4261);
                    }


                    // code to execute when EditText loses focus
                }else {
                    bio_link_view.setBackgroundColor(0xFF1c4261);



                }
            }
        });



         bio_desp_fourth_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    if(bio_desp_fourth_et.getText().toString().equals(""))
                    {
                        bio_desp_view.setBackgroundColor(0xFFbbbbbb);
                    }else {
                        bio_desp_view.setBackgroundColor(0xFF1c4261);
                    }


                    // code to execute when EditText loses focus
                }else {
                    bio_desp_view.setBackgroundColor(0xFF1c4261);

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



     @OnClick(R.id.get_started_tv)
     public  void GetStarted()
     {

         bioLink=bio_link_fourth_et.getText().toString();
         bioDesp=bio_desp_fourth_et.getText().toString();
//         if (bioLink.isEmpty()) {
//             commonUtils.defaultAlert(StandardSignupFourthActivity.this, "Please Enter bio link");
//         }else {
//             if (bioDesp.isEmpty()) {
//                 commonUtils.defaultAlert(StandardSignupFourthActivity.this, "Please Enter bio desp");
//             }else {


                 if(apiStatus.equals("signUP"))
                 {
                     editProfileAPI();
                 }else {
                     SignUpAPI();
                 }
//             }
//         }


     }






    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }



    @Override
    public void SignUpAPI() {
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//        String uiD = sharedPreferencePYT.getUserID();
        JSONObject my = new JSONObject();
        try {
            my.put("firstName", firstName);
            my.put("lastName", lastName);
            my.put("email", emailAddress);
            my.put("password", password);
            my.put("appVersion", commonUtils.getAppVersion(StandardSignupFourthActivity.this));
            my.put("picture", profilePicture);
            my.put("cover", coverImage);

            JSONObject token = new JSONObject();
            token.put("token", refreshedToken);
            token.put("device", "android");
            my.put("deviceToken", token);

            my.put("description", bioDesp);
            my.put("website", bioLink);
            commonUtils.printLog("Signup with Email json",String.valueOf(my));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = null;
//        try {
        try {
            body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                    (new JSONObject(String.valueOf(my))).toString());
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        Call<SignupWithEmailModel> call = apiService.SignupWithEmail(body);

        commonUtils.printLog("Signup request url",call.request().toString());


        call.enqueue(new Callback<SignupWithEmailModel>() {
            @Override
            public void onResponse(Call<SignupWithEmailModel> call, Response<SignupWithEmailModel> response) {
                if(response.isSuccessful())
                {
                    commonUtils.printLog("Signup with Email status",response.body().getStatus().toString());
                    if (response.body().getStatus().toString().equalsIgnoreCase("1"))
                    {


//                        dataManager.saveSecurityToken(response.body().getToken());
                        openMainActivity();
                    }
                }

            }

            @Override
            public void onFailure(Call<SignupWithEmailModel> call, Throwable t) {
                commonUtils.printLog("Signup with Email failure",t.getMessage().toString());
            }
        });
    }


















    @Override
    public void getIntentValues() {
        intent=getIntent();
        apiStatus=intent.getStringExtra("apiStatus");
        firstName=intent.getStringExtra("firstName");
        lastName=intent.getStringExtra("lastName");
        emailAddress=intent.getStringExtra("emailAddress");
        password=intent.getStringExtra("password");
        coverImage=intent.getStringExtra("coverImage");
        profilePicture=intent.getStringExtra("profilePicture");
    }

    @Override
    public void editProfileAPI() {






//            commonProgressBar.show();






            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            String uid = dataManager.getUserId();
            JSONObject my = new JSONObject();
            try {
                my.put("picture", profilePicture);
                my.put("userId", uid);
                my.put("firstName", firstName);
                my.put("lastName", lastName);
                my.put("cover", coverImage);
                my.put("description", bioDesp);
                my.put("website", bioLink);
               commonUtils.printLog(" edit profile data", String.valueOf(my));


            } catch (JSONException e) {
                e.printStackTrace();
            }


            RequestBody body = null;
            try {
                body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                        (new JSONObject(String.valueOf(my))).toString());

                Call<EditProfileModel> call = apiService.edituserpicApi(body);
                call.enqueue(new Callback<EditProfileModel>() {
                    @Override
                    public void onResponse(Call<EditProfileModel> call, Response<EditProfileModel> response) {
                        commonUtils.printLog(" edit profile Url",call.request().toString());

//                        commonProgressBar.hide();



                        if (response.isSuccessful()) {

                            if (response.body().getStatus().toString().equalsIgnoreCase("1")) {


                                commonUtils.printLog("Status......................", response.body().getStatus().toString());



                                openMainActivity();





                            } else {
//                                utils.dialog_msg_show(SignUpFinalStepActivity.this, response.body().getMsg());
                            }


                        } else {
//                            listener=new OnDialogButtonClickListener() {
//                                @Override
//                                public void onOkButtonClicked() {
//                                    EditProfileAPI();
//                                }
//
//                                @Override
//                                public void onNegativeButtonClicked() {
//
//                                }
//                            };
//
//                            utils.FullScreenServerAlert(SignUpFinalStepActivity.this,listener);
                        }
                        //  Log.e("inside Error: ",response.body().getErr().getErrors().getEmail().getName()+"");

                    }

                    @Override
                    public void onFailure(Call<EditProfileModel> call, Throwable t) {
//                        commonProgressBar.hide();
//                        Log.e("inside failure: ", t + "");
//                        listener=new OnDialogButtonClickListener() {
//                            @Override
//                            public void onOkButtonClicked() {
//                                EditProfileAPI();
//                            }
//
//                            @Override
//                            public void onNegativeButtonClicked() {
//
//                            }
//                        };
//
//                        utils.FullScreenServerAlert(SignUpFinalStepActivity.this,listener);


                    }

                });
            } catch (JSONException e) {
//                commonProgressBar.hide();
                e.printStackTrace();
            }
        }






    @Override
    public void setFonts() {
        heading_fourth_tv.setTypeface(commonUtils.setTypeFace_bold(StandardSignupFourthActivity.this));
        first_name_fourth_tv.setTypeface(commonUtils.setTypeFace_regular(StandardSignupFourthActivity.this));
        bio_tv.setTypeface(commonUtils.setTypeFace_regular(StandardSignupFourthActivity.this));
        bio_desp_fourth_et.setTypeface(commonUtils.setTypeFace_semibold(StandardSignupFourthActivity.this));
        bio_link_fourth_et.setTypeface(commonUtils.setTypeFace_semibold(StandardSignupFourthActivity.this));
        get_started_tv.setTypeface(commonUtils.setTypeFace_regular(StandardSignupFourthActivity.this));
        choose_interest_tv.setTypeface(commonUtils.setTypeFace_semibold(StandardSignupFourthActivity.this));
        choose_interest_desp_tv.setTypeface(commonUtils.setTypeFace_regular(StandardSignupFourthActivity.this));
    }

    @Override
    public void setTexts() {

    }





    private void callCategories() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        JSONObject my = new JSONObject();
        try {
            my.put("userId", dataManager.getUserId());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = null;
        try {
            body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                    (new JSONObject(String.valueOf(my))).toString());
            Call<CategoriesModel> call = apiService.get_categories(body);

            call.enqueue(new Callback<CategoriesModel>() {
                @Override
                public void onResponse(Call<CategoriesModel> call, Response<CategoriesModel> response) {


                    try {
                        if (response.isSuccessful()) {

                            if (response.body() != null) {

                                if (response.body().getStatus().toString().equals("5")) {
//                                    calllogoutApi();
                                } else {
                                    if (response.body().getStatus().toString().equalsIgnoreCase("1")) {


                                        for (int i = 0; i < response.body().getData().size(); i++) {
                                            List<CategoriesModel.Category_Data> categories = new ArrayList<CategoriesModel.Category_Data>();
                                            categories = response.body().getData();
//                                            setTag(categories);
//                                            interestAdapter=new InterestAdapter(StandardSignupThirdActivity.this,categories);
//                                            interest_chips_rc_view.setAdapter(interestAdapter);
//                                            GlobalConstant.Id = categoriesId;
                                        }

//                                        List<Category_Data> categories = new ArrayList<Category_Data>();
//                                        categories = response.body().getData();
//                                        Gson gson = new Gson();
//                                        MyWrapper wrapper = new MyWrapper();
//                                        wrapper.setCategories(categories);
//                                        String serializedMap = gson.toJson(wrapper);
//                                        sharedPreferencePYT.setsave_category(serializedMap);
//                                        SharedPreferences.Editor edito = preferences.edit();
//                                        edito.putString("saved_categories", serializedMap);
//                                        edito.apply();
                                    } else {
//                                        utils.dialog_msg_show(SearchScreen.this, response.message());
                                    }
                                }

                            }
                        }else {
//                            listener3=new OnDialogButtonClickListener() {
//                                @Override
//                                public void onOkButtonClicked() {
//                                    callCategories();
//                                }
//
//                                @Override
//                                public void onNegativeButtonClicked() {
//
//                                }
//                            };
//
//                            utils.FullScreenServerAlert(SearchScreen.this,listener3);
                        }
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onFailure(Call<CategoriesModel> call, Throwable t) {

//                    Log.e("inside failure: ", t + "");

//                    listener3=new OnDialogButtonClickListener() {
//                        @Override
//                        public void onOkButtonClicked() {
//                            callCategories();
//                        }
//
//                        @Override
//                        public void onNegativeButtonClicked() {
//
//                        }
//                    };
//
//                    utils.FullScreenServerAlert(SearchScreen.this,listener3);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }


//    private void setTag(final List<CategoriesModel.Category_Data> categories) {
////        final ChipGroup chipGroup = findViewById(R.id.tag_group);
//        for (int index = 0; index < categories.size(); index++) {
//            final String tagName = categories.get(index).getDisplayName();
//            final Chip chip = new Chip(this);
//            int paddingDp = (int) TypedValue.applyDimension(
//                    TypedValue.COMPLEX_UNIT_DIP, 10,
//                    getResources().getDisplayMetrics()
//            );
//            chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
//            chip.setText(tagName);
//
//            chip.setCloseIconEnabled(true);
//            //Added click listener on close icon to remove tag from ChipGroup
//            chip.setOnCloseIconClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    categories.remove(tagName);
//                    interest_group.removeView(chip);
//                }
//            });
//
//            interest_group.addView(chip);
//        }
//    }
}
