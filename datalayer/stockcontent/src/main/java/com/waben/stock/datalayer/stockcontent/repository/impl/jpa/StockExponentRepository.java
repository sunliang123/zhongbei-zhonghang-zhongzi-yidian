package com.waben.stock.datalayer.stockcontent.repository.impl.jpa;

import com.waben.stock.datalayer.stockcontent.entity.StockExponent;

/**
 * @author Created by yuyidi on 2017/11/21.
 * @desc
 */
public interface StockExponentRepository extends CustomJpaRepository<StockExponent, Long> {

    StockExponent findByExponentCode(String exponentCode);
}
