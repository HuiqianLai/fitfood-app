package com.huiqianlai.fitfoodapp;

import android.app.Application;

import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

public class MainApplication extends Application {
    public MainApplication() {
        super();
        QMUISwipeBackActivityManager.init(this);
    }
}
