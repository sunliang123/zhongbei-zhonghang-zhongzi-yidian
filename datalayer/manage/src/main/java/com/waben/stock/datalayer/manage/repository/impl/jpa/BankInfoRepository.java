package com.waben.stock.datalayer.manage.repository.impl.jpa;

import java.util.List;

import com.waben.stock.datalayer.manage.entity.BankInfo;

/**
 * 银行信息 Jpa
 * 
 * @author luomengan
 *
 */
public interface BankInfoRepository extends CustomJpaRepository<BankInfo, Long> {

	BankInfo findByBankNameLike(String string);

	List<BankInfo> findByAppSupportAndEnable(boolean appSupport, boolean enable);

	List<BankInfo> findByPcSupportAndEnable(boolean pcSupport, boolean enable);

}
