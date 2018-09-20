package com.waben.stock.datalayer.stockoption.repository.impl.jpa;

import com.waben.stock.datalayer.stockoption.entity.InquiryResult;
import com.waben.stock.datalayer.stockoption.entity.StockOptionTrade;

import java.util.List;

public interface InquiryResultRepository extends CustomJpaRepository<InquiryResult,Long>{
    List<InquiryResult> findAllByTradeOrderByIdDesc(StockOptionTrade trade);
}
