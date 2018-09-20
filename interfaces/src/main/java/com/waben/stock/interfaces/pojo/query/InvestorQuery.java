package com.waben.stock.interfaces.pojo.query;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @author Created by yuyidi on 2017/11/30.
 * @desc
 */
public class InvestorQuery extends PageAndSortQuery {
    private String userName;

    private Date beginTime;
    private Date endTime;
    private Integer state;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
