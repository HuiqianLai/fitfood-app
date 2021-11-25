package com.huiqianlai.fitfoodapp;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.gson.Gson;
import com.huiqianlai.fitfoodapp.bean.ImageResultBean;
import com.huiqianlai.fitfoodapp.bean.UploadImageBean;
import com.huiqianlai.fitfoodapp.common.io.FileUtils;
import com.huiqianlai.fitfoodapp.okhttp.OkHttpUtils;
import com.huiqianlai.fitfoodapp.okhttp.callback.StringCallback;
import com.huiqianlai.fitfoodapp.utils.PictureSelectUtil;
import com.huiqianlai.fitfoodapp.utils.data.SPUtils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.MediaType;

public class TakePhotoActivity extends AppCompatActivity {

    private int REQUEST_CODE_PERMISSIONS = 10; //arbitrary number, can be changed accordingly
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private ImageView iv_img;
    private ActionProcessButton button;
    private String TAG = "TakePhotoActivity";
    Timer timer = new Timer();
    private Uri finalUri;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        iv_img = findViewById(R.id.iv_img);
        button = findViewById(R.id.upload);
        textView = findViewById(R.id.total_calories);

        startCamera();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doUploadPhoto();
            }
        });
    }

    private void startCamera() {
        PictureSelectUtil.with(this)
                .camera()
                .crop()
                .setCallback(new PictureSelectUtil.OnCallback() {
                    @Override
                    public void onCallback(Uri uri) {
                        finalUri = uri;
                        button.setVisibility(View.VISIBLE);
                        Glide.with(TakePhotoActivity.this).load(uri).into(iv_img);
                    }
                }).select();
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

        String filePath = FileUtils.getFilePathByUri(TakePhotoActivity.this, finalUri);
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
                .addHeader("Authorization", "Bearer " + (String) SPUtils.get(TakePhotoActivity.this, "token", ""))
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
                            UploadImageBean uploadImageBean = new Gson().fromJson(response, UploadImageBean.class);

                            if (TextUtils.equals(uploadImageBean.getMessage(), "fail")) {
                                // login fail
                                Toast.makeText(TakePhotoActivity.this, "Upload image failed!!", Toast.LENGTH_SHORT).show();
                            } else if (TextUtils.equals(uploadImageBean.getMessage(), "success")) {
                                Toast.makeText(TakePhotoActivity.this, "Upload image success!!", Toast.LENGTH_SHORT).show();

                                SPUtils.put(TakePhotoActivity.this, "user_id", uploadImageBean.getData().getUserId());

                                getCaloriesResult(uploadImageBean.getData().getId());
                                // use image id to request the total_calories
//                                Intent intent = new Intent(LoginActivity.this, ActionActivity.class);
//                                startActivity(intent);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    private void getCaloriesResult(int id) {
        String url = Consts.BASE_URL + "image_result/" + id;

//        try {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("image_id", id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        OkHttpUtils
                .get()
                .url(url)
                .addHeader("Authorization", "Bearer " + (String) SPUtils.get(TakePhotoActivity.this, "token", ""))
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
                            ImageResultBean imageResultBean = new Gson().fromJson(response, ImageResultBean.class);

                            if (TextUtils.equals(imageResultBean.getMessage(), "fail")) {
                                // login fail
                                Toast.makeText(TakePhotoActivity.this, "Get calories failed!!", Toast.LENGTH_SHORT).show();
                            } else if (TextUtils.equals(imageResultBean.getMessage(), "success")) {
                                Toast.makeText(TakePhotoActivity.this, "Get calories success!!", Toast.LENGTH_SHORT).show();

                                textView.setVisibility(View.VISIBLE);
                                textView.setText("Total calories is :" + String.valueOf(imageResultBean.getData().get(0).getTotalCalories()));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
    }


}