package com.waben.stock.datalayer.stockcontent.pojo;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/21.
 * @desc
 */
public class Resonse {
    String code;
    StockVariety[] data;
    String msg;
    Integer page;
    Integer pageSize;
    Integer resultCount;
    Integer total;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public StockVariety[] getData() {
        return data;
    }

    public void setData(StockVariety[] data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
