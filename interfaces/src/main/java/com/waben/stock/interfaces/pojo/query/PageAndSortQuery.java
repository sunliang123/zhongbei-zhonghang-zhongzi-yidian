package com.waben.stock.interfaces.pojo.query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author Created by yuyidi on 2017/11/19.
 * @desc
 */
public class  PageAndSortQuery {

    private Integer page=0;
    private Integer size=10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
