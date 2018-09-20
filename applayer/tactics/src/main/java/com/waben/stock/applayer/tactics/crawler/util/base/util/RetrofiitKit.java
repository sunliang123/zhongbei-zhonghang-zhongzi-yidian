package com.waben.stock.applayer.tactics.crawler.util.base.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 12 on 2017/5/8.
 */
public class RetrofiitKit {

    private static OkHttpClient client = new OkHttpClient.Builder()
            // 向Request Header添加一些业务相关数据，如APP版本，token
//                .addInterceptor()
            // 连接超时时间设置
            .connectTimeout(5, TimeUnit.SECONDS)
            // 读取超时时间设置
            .readTimeout(5, TimeUnit.SECONDS)
            // 失败重试
            .retryOnConnectionFailure(true)
            // 支持Https需要加入SSLSocketFactory
            .build();


    public static Retrofit getRetrofit(String url) {

        return new Retrofit.Builder()
            .client(client)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
            .build();
}

}
