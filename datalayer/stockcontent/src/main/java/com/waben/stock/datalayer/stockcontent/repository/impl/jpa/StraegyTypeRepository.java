package com.waben.stock.datalayer.stockcontent.repository.impl.jpa;

import com.waben.stock.datalayer.stockcontent.entity.StrategyType;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/23.
 * @desc
 */
public interface StraegyTypeRepository extends CustomJpaRepository<StrategyType, Long> {

    List<StrategyType> findAllByState(Boolean state);

}
