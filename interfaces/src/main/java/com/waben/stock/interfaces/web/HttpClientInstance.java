package com.waben.stock.interfaces.web;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by yuyidi on 2017/8/28.
 * @desc
 */
public class HttpClientInstance {

    private HttpClientInstance() {
    }

    public static CloseableHttpClient getInstance() {
        return HttpClientHandler.nestedInstance;
    }

    private static CloseableHttpClient clientInstance() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new
                PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(1000);//最大连接数
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(100); //并发连接数
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
        HttpRequestRetryHandler requestRetryHandler = new DefaultHttpRequestRetryHandler(5, true);
        httpClientBuilder.setRetryHandler(requestRetryHandler);
//        httpClientBuilder.setRedirectStrategy(new LaxRedirectStrategy());
        List<BasicHeader> headers = new ArrayList<>();
        BasicHeader userAgent = new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36");
        BasicHeader acceptEncoding = new BasicHeader("Accept-Encoding", "gzip,deflate");
        BasicHeader acceptLanguage = new BasicHeader("Accept-Language", "zh-CN");
        headers.add(userAgent);
        headers.add(acceptEncoding);
        headers.add(acceptLanguage);
        httpClientBuilder.setDefaultHeaders(headers);
        return httpClientBuilder.build();
    }

    private static class HttpClientHandler {
        private static final CloseableHttpClient nestedInstance = clientInstance();
    }
}
