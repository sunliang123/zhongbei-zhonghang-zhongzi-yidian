package com.waben.stock.interfaces.pojo.stock.stockjy.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Created by yuyidi on 2017/12/5.
 * @desc
 */
public class CommonErrorInfo {

    @JsonProperty("error_info")
    private String errorInfo;
    @JsonProperty("error_no")
    private String errorNo;
    @JsonProperty("error_no_src")
    private String errorNoSrc;
    @JsonProperty("error_pathinfo")
    private String errorPathInfo;
    @JsonProperty("position_str")
    private String positionStr;

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
    }

    public String getErrorNoSrc() {
        return errorNoSrc;
    }

    public void setErrorNoSrc(String errorNoSrc) {
        this.errorNoSrc = errorNoSrc;
    }

    public String getErrorPathInfo() {
        return errorPathInfo;
    }

    public void setErrorPathInfo(String errorPathInfo) {
        this.errorPathInfo = errorPathInfo;
    }

    public String getPositionStr() {
        return positionStr;
    }

    public void setPositionStr(String positionStr) {
        this.positionStr = positionStr;
    }
}
