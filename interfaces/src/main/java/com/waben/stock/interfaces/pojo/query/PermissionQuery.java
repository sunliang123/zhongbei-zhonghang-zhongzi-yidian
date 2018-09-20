package com.waben.stock.interfaces.pojo.query;

/**
 * @author Created by yuyidi on 2017/12/11.
 * @desc
 */
public class PermissionQuery extends PageAndSortQuery {

    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
