package com.huiqianlai.fitfoodapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.huiqianlai.fitfoodapp.utils.async.GetDrawableCallback;

import java.util.concurrent.ExecutionException;

public class GlideUtils {
    /**
     * @param url 通过URL得到 Drawable
     * @return
     */
    public static void getDrawableGlide(String url, CustomTarget<Drawable> customTarget, Context context) {
        Glide.with(context).load(url).into(customTarget);
    }

    /**
     * @param url 通过URL得到 Bitmap
     * @return
     */
    public static void getBitmapGlide(String url, CustomTarget<Bitmap> customTarget, Context context) {
        Glide.with(context).asBitmap().load(url).into(customTarget);
    }

    /**
     * 这是一个耗时的操作需要异步处理
     *
     * @param url 通过URL得到 Drawable
     * @return
     */
    public static void getDrawableGlide(String url, Context context, GetDrawableCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Drawable drawable = Glide.with(context)
                            .load(url)
                            .submit()
                            .get();
                    callback.onSuccess(drawable);
                } catch (ExecutionException e) {
                    callback.onFail();
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    callback.onFail();
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 这是一个耗时的操作需要异步处理
     *
     * @param url 通过URL得到 Bitmap
     * @return
     */
    public static Bitmap getBitmapGlide(String url, Context context) {
        try {
            return Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .submit()
                    .get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
