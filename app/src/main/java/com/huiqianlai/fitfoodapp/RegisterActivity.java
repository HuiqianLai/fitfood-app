package com.huiqianlai.fitfoodapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText mNameEditText;
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
    }
}