package com.huiqianlai.fitfoodapp.utils.async;

import android.graphics.drawable.Drawable;

public interface GetDrawableCallback {
    void onSuccess(Drawable result);

    void onFail();
}
