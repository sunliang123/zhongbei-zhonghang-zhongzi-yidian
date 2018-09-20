package com.waben.stock.interfaces.exception;

/**
 * @author Created by yuyidi on 2017/12/6.
 * @desc 熔断器容灾异常
 */
public class NetflixCircuitException extends RuntimeException  {

    private String code;

    public NetflixCircuitException(String code) {
        super(code);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
