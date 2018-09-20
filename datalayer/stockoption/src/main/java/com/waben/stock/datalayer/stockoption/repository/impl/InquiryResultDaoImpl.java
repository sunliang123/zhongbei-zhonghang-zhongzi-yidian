package com.waben.stock.datalayer.stockoption.repository.impl;

import com.waben.stock.datalayer.stockoption.entity.InquiryResult;
import com.waben.stock.datalayer.stockoption.entity.StockOptionTrade;
import com.waben.stock.datalayer.stockoption.repository.InquiryResultDao;
import com.waben.stock.datalayer.stockoption.repository.impl.jpa.InquiryResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InquiryResultDaoImpl implements InquiryResultDao {
    @Autowired
    private InquiryResultRepository inquiryResultRepository;

    @Override
    public List<InquiryResult> retrieveByTrade(Long trade) {
        StockOptionTrade tradeInfo = new StockOptionTrade();
        tradeInfo.setId(trade);
        return inquiryResultRepository.findAllByTradeOrderByIdDesc(tradeInfo);
    }

    @Override
    public InquiryResult create(InquiryResult inquiryResult) {
        return inquiryResultRepository.save(inquiryResult);
    }

    @Override
    public void delete(Long id) {
        inquiryResultRepository.delete(id);
    }

    @Override
    public InquiryResult update(InquiryResult inquiryResult) {
        return inquiryResultRepository.save(inquiryResult);
    }

    @Override
    public InquiryResult retrieve(Long id) {
        return inquiryResultRepository.findById(id);
    }

    @Override
    public Page<InquiryResult> page(int page, int limit) {
        return null;
    }

    @Override
    public Page<InquiryResult> page(Specification<InquiryResult> specification, Pageable pageable) {
        return inquiryResultRepository.findAll(specification, pageable);
    }

    @Override
    public List<InquiryResult> list() {
        return inquiryResultRepository.findAll();
    }
}
