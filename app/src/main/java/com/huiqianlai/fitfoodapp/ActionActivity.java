package com.huiqianlai.fitfoodapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.huiqianlai.fitfoodapp.bean.UserBean;
import com.huiqianlai.fitfoodapp.okhttp.OkHttpUtils;
import com.huiqianlai.fitfoodapp.okhttp.callback.StringCallback;
import com.huiqianlai.fitfoodapp.utils.data.SPUtils;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import devlight.io.library.ntb.NavigationTabBar;
import okhttp3.Call;

public class ActionActivity extends FragmentActivity {
    private String TAG = "ActionActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {

                if (position == 2) {
//                    final UserView view = new UserView(ActionActivity.this);
                    final View view = LayoutInflater.from(
                            getBaseContext()).inflate(R.layout.fragment_user, null, false);
                    final QMUIGroupListView mGroupListView = view.findViewById(R.id.groupListView);
                    initListView(mGroupListView, new Callback() {
                        @Override
                        public void onSuccess() {
                            container.addView(view);

                        }
                    });

                    return view;

                } else {
                    final View view = LayoutInflater.from(
                            getBaseContext()).inflate(R.layout.item_vp, null, false);

                    final TextView txtPage = (TextView) view.findViewById(R.id.txt_vp_item_page);
                    txtPage.setText(String.format("Page #%d", position));

                    container.addView(view);
                    return view;
                }
            }
        });


        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first),
                        Color.parseColor(colors[0]))
                        .title("Heart")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_second),
                        Color.parseColor(colors[1]))
                        .title("Cup")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_third),
                        Color.parseColor(colors[2]))
                        .title("Diploma")
                        .build());

        navigationTabBar.setViewPager(viewPager, 2);
        navigationTabBar.setModels(models);

        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }


    private void initListView(QMUIGroupListView mGroupListView, Callback callback) {
        loadData(this, mGroupListView, callback);
    }

    private void loadData(Context context, QMUIGroupListView mGroupListView, Callback callback) {
        UserBean bean = (UserBean) SPUtils.get(context, "userBean", null);
        if (bean == null) {
            // request token
            requestUserData((String) SPUtils.get(context, "token", ""), mGroupListView, callback);
        } else {
            initGroupListView(bean, mGroupListView, callback);
        }
    }

    private void requestUserData(String token, QMUIGroupListView mGroupListView, Callback callback) {
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

                            SPUtils.put(ActionActivity.this, "registerBean", bean);

                            initGroupListView(bean, mGroupListView, callback);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    private void initGroupListView(UserBean userBean, QMUIGroupListView mGroupListView, Callback callback) {

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
                    Toast.makeText(ActionActivity.this, text + " is Clicked", Toast.LENGTH_SHORT).show();
                    if (((QMUICommonListItemView) v).getAccessoryType() == QMUICommonListItemView.ACCESSORY_TYPE_SWITCH) {
                        ((QMUICommonListItemView) v).getSwitch().toggle();
                    }
                }
            }
        };

        int size = QMUIDisplayHelper.dp2px(this, 20);
        QMUIGroupListView.newSection(this)
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

                .setMiddleSeparatorInset(QMUIDisplayHelper.dp2px(this, 16), 0)
                .addTo(mGroupListView);

        callback.onSuccess();


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