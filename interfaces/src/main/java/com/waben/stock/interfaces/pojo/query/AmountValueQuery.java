package com.waben.stock.interfaces.pojo.query;

import java.math.BigDecimal;

public class AmountValueQuery extends PageAndSortQuery{
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
