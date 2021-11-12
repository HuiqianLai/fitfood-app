package com.huiqianlai.fitfoodapp;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.huiqianlai.fitfoodapp.bean.UserBean;
import com.huiqianlai.fitfoodapp.okhttp.OkHttpUtils;
import com.huiqianlai.fitfoodapp.okhttp.callback.StringCallback;
import com.huiqianlai.fitfoodapp.utils.data.SPUtils;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;

public class UserView extends View {
    private String TAG = "UserFragment";

    private QMUIGroupListView mGroupListView;

    public UserView(Context context) {
        super(context);

    }

    public UserView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    public UserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context);
    }

    public UserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initUI(context);
    }


    private void initUI(Context context) {
        LayoutInflater.from(context).inflate(R.layout.fragment_user, null, false);
        mGroupListView = findViewById(R.id.groupListView);

        loadData(context);
    }


    private void loadData(Context context) {
        UserBean bean = (UserBean) SPUtils.get(context, "userBean", null);
        if (bean == null) {
            // request token
            requestUserData((String) SPUtils.get(context, "token", ""));
        } else {
            initGroupListView(bean);
        }
    }

    private void initGroupListView(UserBean userBean) {

        QMUICommonListItemView idView = mGroupListView.createItemView(
                null,
                "Id",
                userBean.getId().toString(),
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);


        QMUICommonListItemView nameView = mGroupListView.createItemView(
                null,
                "Name",
                userBean.getName(),
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);

        QMUICommonListItemView emailView = mGroupListView.createItemView(
                null,
                "Email",
                userBean.getEmail(),
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);

        QMUICommonListItemView emailVerifiedTime = mGroupListView.createItemView(
                null,
                "Email Verified Time",
                userBean.getEmail_verified_at() == null ? "" : userBean.getEmail_verified_at(),
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);

        QMUICommonListItemView userCreateTime = mGroupListView.createItemView(
                null,
                "User Create Time",
                formatDate(userBean.getCreated_at()).toString(),
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);

        QMUICommonListItemView userUpdateTime = mGroupListView.createItemView(
                null,
                "User Update Time",
                formatDate(userBean.getUpdated_at()).toString(),
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);

        QMUICommonListItemView genderView = mGroupListView.createItemView(
                null,
                "Gender",
                userBean.getGender(),
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);

        QMUICommonListItemView weightView = mGroupListView.createItemView(
                null,
                "Weight",
                String.valueOf(userBean.getWeight()),
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);

        QMUICommonListItemView heightView = mGroupListView.createItemView(
                null,
                "Height",
                String.valueOf(userBean.getHeight()),
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView) {
                    CharSequence text = ((QMUICommonListItemView) v).getText();
                    Toast.makeText(getContext(), text + " is Clicked", Toast.LENGTH_SHORT).show();
                    if (((QMUICommonListItemView) v).getAccessoryType() == QMUICommonListItemView.ACCESSORY_TYPE_SWITCH) {
                        ((QMUICommonListItemView) v).getSwitch().toggle();
                    }
                }
            }
        };

        int size = QMUIDisplayHelper.dp2px(getContext(), 20);
        QMUIGroupListView.newSection(getContext())
                .setTitle("Section 1: User Data")
                //    .setDescription("Section 1 的描述")
                .setLeftIconSize(size, ViewGroup.LayoutParams.WRAP_CONTENT)
                .addItemView(idView, onClickListener)
                .addItemView(nameView, onClickListener)
                .addItemView(emailView, onClickListener)
                .addItemView(emailVerifiedTime, onClickListener)
                .addItemView(userCreateTime, onClickListener)
                .addItemView(userUpdateTime, onClickListener)
                .addItemView(genderView, onClickListener)
                .addItemView(weightView, onClickListener)
                .addItemView(heightView, onClickListener)

                .setMiddleSeparatorInset(QMUIDisplayHelper.dp2px(getContext(), 16), 0)
                .addTo(mGroupListView);


    }

    private void requestUserData(String token) {
        String url = Consts.BASE_URL + "user?";

        OkHttpUtils
                .get()
                .url(url)
                .addHeader("Authorization", "Bearer " + token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                        Log.e("laihuiqian", "onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onResponse：complete");
                        Log.d("laihuiqian", "onResponse:" + response);


                        try {
                            UserBean bean = new Gson().fromJson(response, UserBean.class);

                            SPUtils.put(getContext(), "registerBean", bean);

                            initGroupListView(bean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    private Date formatDate(String date) {
        date = date.replace("Z", " UTC");
        System.out.println(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date d = new Date();
        try {
            d = format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return d;


    }


}