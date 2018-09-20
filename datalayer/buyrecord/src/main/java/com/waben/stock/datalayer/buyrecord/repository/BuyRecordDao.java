package com.waben.stock.datalayer.buyrecord.repository;

import com.waben.stock.datalayer.buyrecord.entity.BuyRecord;
import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.enums.BuyRecordState;

import java.util.Date;
import java.util.List;

/**
 * 点买记录 Dao
 * @author luomengan
 *
 */
public interface BuyRecordDao extends BaseDao<BuyRecord, Long> {

    List<BuyRecord> retieveByStateAndOrderByCreateTime(BuyRecordState state);
    
    Integer strategyJoinCount(Long publisherId, Long strategyTypeId);

    List<BuyRecord> retrieveByStateAndUpdateTimeBetween(BuyRecordState state, Date start, Date end);
}
