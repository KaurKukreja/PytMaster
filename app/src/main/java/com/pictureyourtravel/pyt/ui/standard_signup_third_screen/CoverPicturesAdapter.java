package com.pictureyourtravel.pyt.ui.standard_signup_third_screen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pictureyourtravel.pyt.R;
import com.pictureyourtravel.pyt.utils.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class CoverPicturesAdapter extends RecyclerView.Adapter   <CoverPicturesAdapter.MyViewHolder> {

    Context context;
    List<CoverPicturesData> coverPicturesData;
    coverPictureClickListener itemClickListener;
    String coverPicture;
    Bitmap coverImageBitmap=null;


    public CoverPicturesAdapter(CoverImagesActivity context, List<CoverPicturesData> coverPicturesData, String coverPicture, Bitmap coverImageBitmap, coverPictureClickListener itemClickListener) {
        this.context=context;
        this.coverPicturesData=coverPicturesData;
        this.itemClickListener=itemClickListener;
        this.coverPicture=coverPicture;
        this.coverImageBitmap=coverImageBitmap;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cover_pictures_grid_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {



        if(position==0)
        {


            holder.internal_img.setVisibility(View.VISIBLE);

            if(!coverPicture.equals(""))
            {
                holder.cover_image_in_adapter.setAlpha((float) 0.5);
                Picasso.with(context)
                        .load(coverPicture).fit().transform(new RoundedTransformation(20, 3)) // thumbnail url goes here
                        .into(holder.cover_image_in_adapter);

            }


            else if(coverImageBitmap!=null)
            {
                holder.cover_image_in_adapter.setImageBitmap(getRoundedCornerBitmap(coverImageBitmap, 200));


                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.cover_image_in_adapter.getLayoutParams();
                params.setMargins(2, 2, 2, 2);
                holder.cover_image_in_adapter.setLayoutParams(params);
            }


            else {


                final int sdk = android.os.Build.VERSION.SDK_INT;
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    holder.cover_image_in_adapter.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.rounded_image_view));
                } else {
                    holder.cover_image_in_adapter.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded_image_view));
                }


                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.cover_image_in_adapter.getLayoutParams();
                params.setMargins(2, 2, 2, 2);
                holder.cover_image_in_adapter.setLayoutParams(params);
            }
        }









        else {
            holder.internal_img.setVisibility(View.GONE);
            Picasso.with(context)
                    .load(coverPicturesData.get(position).getImageThumb()).fit().transform(new RoundedTransformation(20, 3)) // thumbnail url goes here
                    .into(holder.cover_image_in_adapter);

        }






        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v,position);

            }
        });



    }














    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
    @Override
    public int getItemCount() {
        return coverPicturesData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView cover_image_in_adapter,internal_img;

        public MyViewHolder(View itemView) {
            super(itemView);
            cover_image_in_adapter=(ImageView)itemView.findViewById(R.id.cover_image_in_adapter);
            internal_img=(ImageView)itemView.findViewById(R.id.internal_img);
//            img_around_tick=(ImageView)itemView.findViewById(R.id.img_around_tick);
        }
    }
}


