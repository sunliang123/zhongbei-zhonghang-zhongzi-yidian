package com.waben.stock.interfaces.pojo.query;

/**
 * @author Created by yuyidi on 2017/12/6.
 * @desc
 */
public class StrategyTypeQuery extends PageAndSortQuery {
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
