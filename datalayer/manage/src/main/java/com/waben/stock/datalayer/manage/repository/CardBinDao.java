package com.waben.stock.datalayer.manage.repository;

import com.waben.stock.datalayer.manage.entity.CardBin;

/**
 * 银行卡片 Dao
 * 
 * @author luomengan
 *
 */
public interface CardBinDao extends BaseDao<CardBin, Long> {

	CardBin retrieveByBankCard(String bankCard);

}
