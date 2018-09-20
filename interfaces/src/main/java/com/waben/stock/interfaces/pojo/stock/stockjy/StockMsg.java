package com.waben.stock.interfaces.pojo.stock.stockjy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Created by yuyidi on 2017/11/30.
 * @desc
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockMsg  {

    @JsonProperty("error_info")
    private String errorInfo;

    @JsonProperty("error_no")
    private String errorNo;

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
}
