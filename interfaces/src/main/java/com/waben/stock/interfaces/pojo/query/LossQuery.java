package com.waben.stock.interfaces.pojo.query;

import java.math.BigDecimal;

public class LossQuery extends PageAndSortQuery {
    private BigDecimal point;

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }
}
