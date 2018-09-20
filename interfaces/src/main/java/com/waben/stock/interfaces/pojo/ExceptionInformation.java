package com.waben.stock.interfaces.pojo;

/**
 * @author Created by yuyidi on 2017/11/17.
 * @desc
 */
public class ExceptionInformation {
    private Class exception;
    private Integer httpStatus;
    private String error;

    public ExceptionInformation(Class exception, Integer httpStatus,  String error) {
        this.exception = exception;
        this.httpStatus = httpStatus;
        this.error = error;
    }

    public Class getException() {
        return exception;
    }

    public void setException(Class exception) {
        this.exception = exception;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
