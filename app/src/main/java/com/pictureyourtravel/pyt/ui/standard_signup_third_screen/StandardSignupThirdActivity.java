package com.pictureyourtravel.pyt.ui.standard_signup_third_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pictureyourtravel.pyt.PytApp;
import com.pictureyourtravel.pyt.R;
import com.pictureyourtravel.pyt.data.DataManager;
import com.pictureyourtravel.pyt.ui.standard_signup_fourth_screen.StandardSignupFourthActivity;
import com.pictureyourtravel.pyt.utils.CircleTransform;
import com.pictureyourtravel.pyt.utils.CommonUtils;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StandardSignupThirdActivity extends Activity implements StandardSignupThirdMvpView {
    StandardSignupThirdPresenter standardSignupThirdPresenter ;
    CommonUtils commonUtils;
    DataManager dataManager;
    final int cameraPermission = 2401;
    Intent intent;
    public static String  coverImageThumb ="",coverImageLarge="";
    public static Bitmap  coverImageBitmap =null;


    String                             imageview="",   apiStatus="", firstName = "", lastName = "", emailAddress = "", password="",profilePicture="";

    @BindView(R.id.next_tv_third)
    TextView next_tv_third;


    @BindView(R.id.heading_third_tv)
    TextView heading_third_tv;



    @BindView(R.id.back_rl)
    RelativeLayout back_rl;

    @BindView(R.id.profile_pic_rl)
    RelativeLayout profile_pic_rl;

    @BindView(R.id.profile_image_view)
    ImageView profile_image_view;

    @BindView(R.id.cover_pic_iv)
    ImageView cover_pic_iv;

    @BindView(R.id.cover_pic_rl)
    RelativeLayout cover_pic_rl;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, StandardSignupThirdActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_signup_third);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder(); StrictMode.setVmPolicy(builder.build());
        ButterKnife.bind(this);
        dataManager = ((PytApp) getApplication()).getDataManager();
        standardSignupThirdPresenter = new StandardSignupThirdPresenter(dataManager);

        standardSignupThirdPresenter.onAttach(this);


        commonUtils=CommonUtils.getInstance();

        setFonts();
        setTexts();
        getIntentValues();
    }


    @OnClick(R.id.profile_pic_rl)
    public void selectProfileImage()
    {
        imageview = "profile";
        String messagesPermission1 = android.Manifest.permission.CAMERA;
        String messagesPermission2 = android.Manifest.permission.READ_EXTERNAL_STORAGE;
        String messagesPermission3 = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {




            int hasaccesslocation4 = checkSelfPermission(messagesPermission1);
            int hasaccesslocation3 = checkSelfPermission(messagesPermission2);
            int hasaccesslocation5 = checkSelfPermission(messagesPermission3);

            if (hasaccesslocation4 != PackageManager.PERMISSION_GRANTED || hasaccesslocation5 != PackageManager.PERMISSION_GRANTED || hasaccesslocation3 != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, cameraPermission);

                return;
            } else {

                selectImage();

            }


        }else {

            String Permission1 = android.Manifest.permission.CAMERA;
            String Permission2 = android.Manifest.permission.READ_EXTERNAL_STORAGE;
            String Permission3 = Manifest.permission.WRITE_EXTERNAL_STORAGE;
            int permissionn = PermissionChecker.checkSelfPermission(StandardSignupThirdActivity.this, Permission1);
            int permissionnn = PermissionChecker.checkSelfPermission(StandardSignupThirdActivity.this, Permission2);
            int permissionnnnn = PermissionChecker.checkSelfPermission(StandardSignupThirdActivity.this, Permission3);

            if (permissionn == PermissionChecker.PERMISSION_GRANTED &&permissionnn == PermissionChecker.PERMISSION_GRANTED &&permissionnnnn == PermissionChecker.PERMISSION_GRANTED ) {
                // good to go
            } else {
                // permission not granted, you decide what to do
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(!coverImageThumb.equals(""))
        {
            Picasso.with(StandardSignupThirdActivity.this)
                    .load(coverImageThumb)//.fit()//.transform(new RoundedTransformation(20, 3)) // thumbnail url goes here
                    .into(cover_pic_iv);

        }else {
            cover_pic_iv.setImageBitmap(coverImageBitmap);
        }
    }

    @OnClick(R.id.cover_pic_rl)
    public void selectCoverImage()
    {

        openCoverImagesActivity();

    }




    @OnClick(R.id.next_tv_third)
    public void openSignupSecond()
    {
        openSignupFourthScreen();
    }





    @Override
    public void openCoverImagesActivity() {
        Intent intent = CoverImagesActivity.getStartIntent(this);
        startActivity(intent);
    }







    @Override
    public void getIntentValues() {
        intent=getIntent();
        firstName=intent.getStringExtra("firstName");
        lastName=intent.getStringExtra("lastName");
        emailAddress=intent.getStringExtra("emailAddress");
        apiStatus=intent.getStringExtra("apiStatus");
        password=intent.getStringExtra("password");

//        try {
            if (dataManager.getUserpic() != null && !dataManager.getUserpic().equals("")) {
                profilePicture = dataManager.getUserpic();
                Picasso.with(StandardSignupThirdActivity.this).load(profilePicture).transform(new CircleTransform()).into(profile_image_view);
            }
//        }catch (NullPointerException e)
//        {
//
//        }

    }







    @Override
    public void openSignupFourthScreen() {
        Intent intent = StandardSignupFourthActivity.getStartIntent(this);
        intent.putExtra("firstName",firstName);
        intent.putExtra("lastName",lastName);
        intent.putExtra("emailAddress",emailAddress);
        intent.putExtra("password",password);
        intent.putExtra("coverImage",coverImageLarge);
        intent.putExtra("profilePicture",profilePicture);
        intent.putExtra("apiStatus",apiStatus);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
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
        heading_third_tv.setTypeface(commonUtils.setTypeFace_bold(StandardSignupThirdActivity.this));
        next_tv_third.setTypeface(commonUtils.setTypeFace_regular(StandardSignupThirdActivity.this));


    }

    @Override
    public void setTexts() {







    }




    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(StandardSignupThirdActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    if(imageview.equals("profile")) {
                        bitmap=getRoundedShape(bitmap);
                        profile_image_view.setImageBitmap(bitmap);

                    }else {
                        cover_pic_iv.setImageBitmap(bitmap);
                    }

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }




            else if (requestCode == 2) {

                Bitmap bitmap=null;
                try {
                     bitmap=BitmapFactory.decodeStream(getContentResolver().openInputStream(data.getData()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                if(imageview.equals("profile")) {

                    bitmap=getRoundedShape(bitmap);
                    profile_image_view.setImageBitmap(bitmap);

                }else {
                    cover_pic_iv.setImageBitmap(bitmap);
                }

                }
        }
    }







    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        // TODO Auto-generated method stub
        int targetWidth = 200;
        int targetHeight = 200;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth,
                        targetHeight), null);
        return targetBitmap;
    }


    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case cameraPermission:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    selectImage();
                }

                break;
        }
    }
}















