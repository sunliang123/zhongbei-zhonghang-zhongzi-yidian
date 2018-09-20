package com.waben.stock.datalayer.manage.repository;

import java.util.List;

import com.waben.stock.datalayer.manage.entity.BankInfo;

/**
 * 银行信息 Dao
 * 
 * @author luomengan
 *
 */
public interface BankInfoDao extends BaseDao<BankInfo, Long> {

	BankInfo retrieveByBankNameLike(String bankName);

	List<BankInfo> retrieveByAppSupport(boolean appSupport);

	List<BankInfo> retrieveByPcSupport(boolean pcSupport);

}
