package com.huiqianlai.fitfoodapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {
    private String TAG = "UserFragment";

    private QMUIGroupListView mGroupListView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGroupListView = view.findViewById(R.id.groupListView);

        loadData();
    }

    private void loadData() {
        UserBean bean = (UserBean) SPUtils.get(getActivity(), "userBean", null);
        if (bean == null) {
            // request token
            requestUserData((String) SPUtils.get(getActivity(), "token", ""));
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
                    Toast.makeText(getActivity(), text + " is Clicked", Toast.LENGTH_SHORT).show();
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

                            SPUtils.put(getActivity(), "registerBean", bean);

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