package com.waben.stock.datalayer.investors.repository.impl.jpa;

import com.waben.stock.datalayer.investors.entity.Investor;
import com.waben.stock.interfaces.enums.CapitalFlowType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author Created by yuyidi on 2017/11/30.
 * @desc
 */
public interface InvestorRepository extends CustomJpaRepository<Investor, Long> {
    Investor findByUserName(String userName);
    @Transactional
    @Modifying
    @Query(value="update investor set state=?2, user_name=?3 where id=?1",nativeQuery = true)
    Integer revisionById(Long investorId, Boolean state,String userName);
}
