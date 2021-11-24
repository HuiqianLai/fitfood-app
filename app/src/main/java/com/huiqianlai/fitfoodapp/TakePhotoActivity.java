package com.huiqianlai.fitfoodapp;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.dd.processbutton.iml.ActionProcessButton;
import com.huiqianlai.fitfoodapp.utils.PictureSelectUtil;

public class TakePhotoActivity extends AppCompatActivity {

    private int REQUEST_CODE_PERMISSIONS = 10; //arbitrary number, can be changed accordingly
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private ImageView iv_img;
    private ActionProcessButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        iv_img = findViewById(R.id.iv_img);
        button = findViewById(R.id.upload);

        startCamera();

//        if (allPermissionsGranted()) {
//            startCamera(); //start camera if permission has been granted by user
//        } else {
//            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
//        }
    }

    private void startCamera() {
        PictureSelectUtil.with(this)
                .camera()
                .crop()
                .setCallback(new PictureSelectUtil.OnCallback() {
                    @Override
                    public void onCallback(Uri uri) {
                        button.setVisibility(View.VISIBLE);
                        Glide.with(TakePhotoActivity.this).load(uri).into(iv_img);
                    }
                }).select();
    }


    private boolean allPermissionsGranted() {
        //check if req permissions have been granted
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}