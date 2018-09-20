package com.waben.stock.datalayer.investors.repository;

import com.waben.stock.datalayer.investors.entity.Investor;

/**
 * @author Created by yuyidi on 2017/11/30.
 * @desc
 */
public interface InvestorDao extends BaseDao<Investor,Long> {

    Investor retieveWithUserName(String userName);

    Integer updateById(String userName,Boolean state,Long investorId);
}
