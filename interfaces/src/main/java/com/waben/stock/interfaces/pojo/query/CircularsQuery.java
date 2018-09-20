package com.waben.stock.interfaces.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Created by yuyidi on 2017/12/11.
 * @desc
 */
@ApiModel(value="CircularsQuery",description="公告查询对象")

public class CircularsQuery extends PageAndSortQuery{
    @ApiModelProperty(value = "公告标题")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
