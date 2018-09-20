package com.waben.stock.interfaces.pojo.stock.stockjy;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/12/1.
 * @desc
 */
public class StockResult<T> {
    private List<T> data;
    private StockMsg msg;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public StockMsg getMsg() {
        return msg;
    }

    public void setMsg(StockMsg msg) {
        this.msg = msg;
    }
}
