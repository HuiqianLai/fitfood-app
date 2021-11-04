package com.huiqianlai.fitfoodapp.okhttp.builder;


import com.huiqianlai.fitfoodapp.okhttp.OkHttpUtils;
import com.huiqianlai.fitfoodapp.okhttp.request.OtherRequest;
import com.huiqianlai.fitfoodapp.okhttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder {
    @Override
    public RequestCall build() {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers, id).build();
    }
}
