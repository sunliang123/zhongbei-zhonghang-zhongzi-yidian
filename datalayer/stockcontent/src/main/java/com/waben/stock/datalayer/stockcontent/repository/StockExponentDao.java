package com.waben.stock.datalayer.stockcontent.repository;

import com.waben.stock.datalayer.stockcontent.entity.StockExponent;

/**
 * @author Created by yuyidi on 2017/11/21.
 * @desc
 */
public interface StockExponentDao extends BaseDao<StockExponent, Long> {

    StockExponent retrieveWithExponeneCode(String exponentCode);
}
