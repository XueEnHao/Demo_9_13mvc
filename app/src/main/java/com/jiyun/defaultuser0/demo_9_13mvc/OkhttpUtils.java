package com.jiyun.defaultuser0.demo_9_13mvc;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by defaultuser0 on 2017/9/13.
 */

public class OkhttpUtils {
    private static OkhttpUtils okhttpUtils;
    private static OkHttpClient okHttpClient;
    private OkhttpUtils(){
        okHttpClient =new OkHttpClient();
    }
    public static synchronized OkhttpUtils getInstance(){
        if (okhttpUtils ==null) {
            okhttpUtils = new OkhttpUtils();
        }
        return okhttpUtils;
    }
    public void sendGet(String url, Callback callback){
        Request build = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(callback);
    }
}
