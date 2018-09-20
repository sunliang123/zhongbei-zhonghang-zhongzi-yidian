package com.waben.stock.interfaces.pojo;

import java.io.Serializable;

/**
 * @author yuyidi 2017-07-05 17:23:44
 * @class com.wangbei.pojo.Response
 * @description http接口响应实体
 */
public class   Response<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /*状态码*/
    private String code = "200";
    /*响应实体对象*/
    private T result;
    /*响应结果内容*/
    private String message = "响应成功";


    public Response() {
    }

    public Response(String code) {
        this.code = code;
    }

    public Response(T result) {
        this.result = result;
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(String code, T result, String message) {
        this.code = code;
        this.result = result;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
