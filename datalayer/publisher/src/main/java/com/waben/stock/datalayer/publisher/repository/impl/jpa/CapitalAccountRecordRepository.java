package com.waben.stock.datalayer.publisher.repository.impl.jpa;

import com.waben.stock.datalayer.publisher.entity.CapitalAccount;
import com.waben.stock.datalayer.publisher.entity.CapitalAccountRecord;

import java.util.List;

/**
 * @author Created by yuyidi on 2018/5/4.
 * @desc
 */
public interface CapitalAccountRecordRepository extends CustomJpaRepository<CapitalAccountRecord,Long> {

    List<CapitalAccountRecord> findByCapitalAccount(CapitalAccount capitalAccount);

}
