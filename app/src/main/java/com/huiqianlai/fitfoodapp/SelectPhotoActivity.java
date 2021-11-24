package com.huiqianlai.fitfoodapp;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.huiqianlai.fitfoodapp.utils.PictureSelectUtil;

public class SelectPhotoActivity extends AppCompatActivity {

    private ImageView iv_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_photo);
        iv_img = findViewById(R.id.iv_img);

        PictureSelectUtil.with(this)
                .gallery()
                .crop()
                .setCallback(new PictureSelectUtil.OnCallback() {
                    @Override
                    public void onCallback(Uri uri) {
                        Glide.with(SelectPhotoActivity.this).load(uri).into(iv_img);
                    }
                }).select();
    }
}