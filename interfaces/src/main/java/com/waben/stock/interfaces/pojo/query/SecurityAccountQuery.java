package com.waben.stock.interfaces.pojo.query;

import java.util.Date;

/**
 * @author Created by yuyidi on 2017/11/30.
 * @desc
 */
public class SecurityAccountQuery extends PageAndSortQuery {
    private String account;
//    private Date beginTime;
//    private Date endTime;
//    public Date getBeginTime() {
//        return beginTime;
//    }
//
//    public void setBeginTime(Date beginTime) {
//        this.beginTime = beginTime;
//    }
//
//    public Date getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(Date endTime) {
//        this.endTime = endTime;
//    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
