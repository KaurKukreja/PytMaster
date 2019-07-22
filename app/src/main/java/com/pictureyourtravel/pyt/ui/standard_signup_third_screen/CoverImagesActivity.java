package com.pictureyourtravel.pyt.ui.standard_signup_third_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pictureyourtravel.pyt.R;
import com.pictureyourtravel.pyt.network.ApiClient;
import com.pictureyourtravel.pyt.network.ApiInterface;
import com.pictureyourtravel.pyt.utils.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


import static com.pictureyourtravel.pyt.ui.standard_signup_third_screen.StandardSignupThirdActivity.coverImageBitmap;
import static com.pictureyourtravel.pyt.ui.standard_signup_third_screen.StandardSignupThirdActivity.coverImageLarge;
import static com.pictureyourtravel.pyt.ui.standard_signup_third_screen.StandardSignupThirdActivity.coverImageThumb;

public class CoverImagesActivity extends AppCompatActivity {

    List<CoverPicturesData> coverPicturesData;
    CoverPicturesAdapter coverPicturesAdapter;

    final int cameraPermission = 2401;
       CoverPicturesData  coverPicturesDataObj  =   new CoverPicturesData();

    @BindView(R.id.cover_pic_rv)
    RecyclerView cover_pic_rv;

    @BindView(R.id.close_rl)
    RelativeLayout close_rl;




    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, CoverImagesActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_images);
        ButterKnife.bind(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder(); StrictMode.setVmPolicy(builder.build());
        getCoverPicturesAPI();

    }



    private void getCoverPicturesAPI() {


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CoverPicturesModel> call = apiService.getCoverPictures();
        call.enqueue(new Callback<CoverPicturesModel>() {
            @Override
            public void onResponse(Call<CoverPicturesModel> call, Response<CoverPicturesModel> response) {


                if(response.isSuccessful())
                {
                    coverPicturesData=new ArrayList<>();
                    coverPicturesDataObj.setImageLarge("");
                    coverPicturesDataObj.setImageThumb("");
                    coverPicturesData.add(coverPicturesDataObj);
                    coverPicturesData=response.body().getData();
                    setImagesInRecyclerview(coverPicturesData);
                }else {

                }
            }

            @Override
            public void onFailure(Call<CoverPicturesModel> call, Throwable t) {

                Log.e("onFailure cover_pic...",t.getMessage());


            }
        });

    }



         private void setImagesInRecyclerview(final List<CoverPicturesData> coverPicturesData) {
        cover_pic_rv.setLayoutManager(new GridLayoutManager(CoverImagesActivity.this, 3));
        cover_pic_rv.setNestedScrollingEnabled(false);




        coverPicturesAdapter = new CoverPicturesAdapter(CoverImagesActivity.this, coverPicturesData,coverImageThumb, coverImageBitmap, new coverPictureClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                try {
                    if(position==0)
                    {
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


                        }
                    }else {
                        coverImageThumb = coverPicturesData.get(position).getImageThumb();
                        coverImageLarge = coverPicturesData.get(position).getImageLarge();
                        finish();
                    }


                }catch (NullPointerException e)
                {

                }


            }
        });

        cover_pic_rv.setAdapter(coverPicturesAdapter);
    }










    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(CoverImagesActivity.this);

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


                    coverImageThumb="";
                    coverImageLarge="";
                    coverImageBitmap=bitmap;
                    finish();




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

                coverImageThumb="";
                coverImageLarge="";
                coverImageBitmap=bitmap;
                finish();



            }
        }
    }




















  @OnClick(R.id.close_rl)
    public void goBack()
  {
      finish();
  }
}
