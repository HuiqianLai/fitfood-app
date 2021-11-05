package com.huiqianlai.fitfoodapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.huiqianlai.fitfoodapp.okhttp.OkHttpUtils;
import com.huiqianlai.fitfoodapp.okhttp.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mConfirmPasswordEditText;
    private EditText mHeight;
    private EditText mWeight;
    private Button mRegister;
    private CheckBox mMaleCheckBox;
    private CheckBox mFemaleCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUI();
    }

    private void setUI() {
        mNameEditText = findViewById(R.id.set_your_user_name);
        mEmailEditText = findViewById(R.id.set_your_email);
        mPasswordEditText = findViewById(R.id.set_your_password);
        mConfirmPasswordEditText = findViewById(R.id.comfirm_your_password);
        mHeight = findViewById(R.id.edit_body_height);
        mWeight = findViewById(R.id.edit_body_weight);
        mMaleCheckBox = findViewById(R.id.checkbox_male);
        mFemaleCheckBox = findViewById(R.id.checkbox_female);

        mRegister = findViewById(R.id.register);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.equals(mPasswordEditText.getText(), mConfirmPasswordEditText.getText())) {
                    Toast.makeText(view.getContext(), "The two passwords entered are inconsistent!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(mNameEditText.getText())) {
                    Toast.makeText(view.getContext(), "Please fill in your name!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(mEmailEditText.getText())) {
                    Toast.makeText(view.getContext(), "Please fill in your email!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(mPasswordEditText.getText())) {
                    Toast.makeText(view.getContext(), "Please fill in your password!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(mConfirmPasswordEditText.getText())) {
                    Toast.makeText(view.getContext(), "Please comfirm your password!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(mHeight.getText())) {
                    Toast.makeText(view.getContext(), "Please fill in your height!", Toast.LENGTH_LONG).show();
                    return;

                }
                if (TextUtils.isEmpty(mWeight.getText())) {
                    Toast.makeText(view.getContext(), "Please fill in your weight!", Toast.LENGTH_LONG).show();
                    return;
                }

                doRegister();
            }
        });

    }

    private void doRegister() {
        //request http
        postRegister();
    }

    private void postRegister() {
        String url = Consts.BASE_URL + "register";

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("name", mNameEditText.getText());
        params.put("email", mEmailEditText.getText());
        if (mMaleCheckBox.isChecked()) {
            params.put("gender", "MALE");
        } else {
            params.put("gender", "FEMALE");
        }
        params.put("weight", Integer.parseInt(mWeight.getText().toString()));
        params.put("height", Integer.parseInt(mHeight.getText().toString()));
        params.put("password", mPasswordEditText.getText());
        params.put("password_confirmation", mConfirmPasswordEditText.getText());

        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(params.toString())
                .build()
                .execute(new MyStringCallback());
    }


    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            setTitle("loading...");
        }

        @Override
        public void onAfter(int id) {
            setTitle("Fitfood-okHttp");
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            Log.e("laihuiqian", "onError:" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "onResponse：complete");
            Log.d("laihuiqian", "onResponse:" + response);

            switch (id) {
                case 100:
                    Toast.makeText(RegisterActivity.this, "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(RegisterActivity.this, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
            Log.d("laihuiqian", "inProgress:" + progress);
//            mProgressBar.setProgress((int) (100 * progress));
        }
    }
}