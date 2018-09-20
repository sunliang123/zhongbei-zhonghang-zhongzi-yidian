package com.waben.stock.applayer.tactics.payapi.paypal.utils;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.Map;

public class FormRequest {

    public static String doPost(Map<String, String> params, String url) {

        HttpClient httpClient = new HttpClient();

        PostMethod postMethod = new PostMethod(url);

        postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");//

        //   填入各个表单域的值
        NameValuePair[] data = new NameValuePair[params.size()];
        int i = 0;
        for (String key : params.keySet()) {
            data[i] = new NameValuePair(key, params.get(key));
            i++;
        }
        //   将表单的值放入postMethod中
        postMethod.setRequestBody(data);
        //   执行postMethod
        int statusCode = 0;
        try {
            statusCode = httpClient.executeMethod(postMethod);
        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//   HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
//   301或者302
        String str = "";
        if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
            //   从头中取出转向的地址
            Header locationHeader = postMethod.getResponseHeader("location");
            String location = null;
            if (locationHeader != null) {
                location = locationHeader.getValue();
                System.out.println("The page was redirected to:" + location);
            } else {
                System.err.println("当前文件值为空");
            }
            return "";
        } else {
            try {
                str = postMethod.getResponseBodyAsString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        postMethod.releaseConnection();
        return str;
    }
}