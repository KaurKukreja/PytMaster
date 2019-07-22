package com.pictureyourtravel.pyt.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.pictureyourtravel.pyt.R;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CommonUtils {

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    public static String serverClientId="630913578233-4vsohbgunor07mu6d9clisuc8d2u67r6.apps.googleusercontent.com";
    private static CommonUtils INSTANCE = null;

    // other instance variables can be here

    public static CommonUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CommonUtils();
        }
        return(INSTANCE);
    }









    /***************** TypeFaces **************/

    public Typeface setTypeFace_light(Activity activity) {
        Typeface custom_font = Typeface.createFromAsset(activity.getAssets(), "montserrat_light.ttf");
        return custom_font;
    }
    public Typeface setTypeFace_bold(Activity activity) {
        Typeface custom_font = Typeface.createFromAsset(activity.getAssets(), "montserrat_bold.ttf");
        return custom_font;
    }
    public Typeface setTypeFace_regular(Activity activity) {
        Typeface custom_font = Typeface.createFromAsset(activity.getAssets(), "montserrat_regular.ttf");
        return custom_font;
    }
    public Typeface setTypeFace_semibold(Activity activity) {
        Typeface custom_font = Typeface.createFromAsset(activity.getAssets(), "montserrat_semiBold.ttf");
        return custom_font;
    }





/***************GET APP VERSION******************/

public  String getAppVersion(Activity activity) {
    String version = "";
    try {
        // PackageInfo pInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
        PackageInfo pInfo = activity.getPackageManager().getPackageInfo("com.pictureyourtravel.pyt", 0);
        version = pInfo.versionName;
    } catch (PackageManager.NameNotFoundException e) {
        e.printStackTrace();
    }
    return version;
}


public void printLog(String title, String value)
{
   Log.e(title,".................."+value);
}


    public final boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    public void defaultAlert(Activity activity, String msg) {
        final Dialog dialog = new Dialog(activity,R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.default_alert_layout);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView txt_msg = (TextView) dialog.findViewById(R.id.txt_msg);
        txt_msg.setText(msg);

        TextView rel_vw_save_details = (TextView) dialog.findViewById(R.id.rel_vw_save_details);
        rel_vw_save_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        try {
            dialog.show();
        } catch (Exception e) {

        }

    }









    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public   boolean checkPermission(final Context context)
    {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }












}
