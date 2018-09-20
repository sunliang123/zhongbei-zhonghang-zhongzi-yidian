package com.waben.stock.interfaces.pojo.stock.stockjy;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/30.
 * @desc
 */
public class StockResponse<T> {
    private List<StockResult<T>> result;

    public List<StockResult<T>> getResult() {
        return result;
    }

    public void setResult(List<StockResult<T>> result) {
        this.result = result;
    }
}
