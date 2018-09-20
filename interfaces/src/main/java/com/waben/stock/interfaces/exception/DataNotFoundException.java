package com.waben.stock.interfaces.exception;

/**
 * @author Created by yuyidi on 2017/7/18.
 * @desc
 */
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException() {
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
