package com.huiqianlai.fitfoodapp;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.gson.Gson;
import com.huiqianlai.fitfoodapp.bean.LoginBean;
import com.huiqianlai.fitfoodapp.common.io.FileUtils;
import com.huiqianlai.fitfoodapp.okhttp.OkHttpUtils;
import com.huiqianlai.fitfoodapp.okhttp.callback.StringCallback;
import com.huiqianlai.fitfoodapp.utils.PictureSelectUtil;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.MediaType;

public class SelectPhotoActivity extends AppCompatActivity {

    private ImageView iv_img;
    private ActionProcessButton button;
    private String TAG = "SelectPhotoActivity";
    Timer timer = new Timer();
    private Uri finalUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_photo);
        iv_img = findViewById(R.id.iv_img);
        button = findViewById(R.id.upload);

        PictureSelectUtil.with(this)
                .gallery()
                .crop()
                .setCallback(new PictureSelectUtil.OnCallback() {
                    @Override
                    public void onCallback(Uri uri) {
                        finalUri = uri;
                        button.setVisibility(View.VISIBLE);
                        Glide.with(SelectPhotoActivity.this).load(uri).into(iv_img);
                    }
                }).select();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doUploadPhoto();
            }
        });
    }

    private void startLoading() {
        button.setMode(ActionProcessButton.Mode.ENDLESS);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                button.setProgress(50);
            }
        }, 1);
    }

    private void endLoading() {
        timer.cancel();
        button.setProgress(0);
    }

    private static String encodeFileToBase64Binary(File file) throws Exception {
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStreamReader.read(bytes);
        Base64.Encoder encoder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encoder = Base64.getEncoder();
            return new String(encoder.encode(bytes), "UTF-8");
        } else {
            return android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT);
        }
    }

    private void doUploadPhoto() {
        startLoading();

        String url = Consts.BASE_URL + "meal_image";

        String filePath = FileUtils.getFilePathByUri(SelectPhotoActivity.this, finalUri);
        File file = new File(filePath);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("base64", true);
            jsonObject.put("meal_image", encodeFileToBase64Binary(file));
        } catch (Exception e) {
            e.printStackTrace();
        }


        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                        Log.e("laihuiqian", "onError:" + e.getMessage());

                        endLoading();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onResponse：complete");
                        Log.d("laihuiqian", "onResponse:" + response);

                        endLoading();

                        try {
                            LoginBean loginbean = new Gson().fromJson(response, LoginBean.class);

//                            SPUtils.put(LoginActivity.this, "token", loginbean.getData().getAccessToken());
//
//
//                            if (TextUtils.equals(loginbean.getMessage(), "fail")) {
//                                // login fail
//                                Toast.makeText(LoginActivity.this, "Login failed!!", Toast.LENGTH_SHORT).show();
//                            } else if (TextUtils.equals(loginbean.getMessage(), "success")) {
//                                Toast.makeText(LoginActivity.this, "Login success!!", Toast.LENGTH_SHORT).show();
//                                saveToken(loginbean.getData().getAccessToken());
//
//                                Intent intent = new Intent(LoginActivity.this, ActionActivity.class);
//                                startActivity(intent);
//                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
    }
}