package com.waben.stock.datalayer.stockoption.service;

import com.waben.stock.datalayer.stockoption.entity.InquiryResult;
import com.waben.stock.datalayer.stockoption.repository.InquiryResultDao;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InquiryResultService {

    @Autowired
    private InquiryResultDao inquiryResultDao;

    public InquiryResult save(InquiryResult inquiryResult) {
        return inquiryResultDao.create(inquiryResult);
    }

    public InquiryResult fetchByTrade(Long trade) {
        List<InquiryResult> result = inquiryResultDao.retrieveByTrade(trade);
        if (result == null) {
            throw new DataNotFoundException(ExceptionConstant.INQUIRY_RESULT_NOT_FOUND);
        }
        return result.get(0);
    }
}
