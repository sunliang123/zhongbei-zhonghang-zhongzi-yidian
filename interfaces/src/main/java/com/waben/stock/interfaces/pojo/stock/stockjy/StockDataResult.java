package com.waben.stock.interfaces.pojo.stock.stockjy;

import com.waben.stock.interfaces.pojo.stock.stockjy.data.StockLoginInfo;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/30.
 * @desc
 */
public class StockDataResult<T>   {

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
