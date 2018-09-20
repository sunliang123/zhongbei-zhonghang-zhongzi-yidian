package com.waben.stock.datalayer.stockoption.repository;

import com.waben.stock.datalayer.stockoption.entity.InquiryResult;

import java.util.List;

public interface InquiryResultDao extends BaseDao<InquiryResult, Long>  {
    List<InquiryResult> retrieveByTrade(Long trade);
}
