package com.waben.stock.datalayer.stockoption.controller;

import com.waben.stock.datalayer.stockoption.entity.InquiryResult;
import com.waben.stock.datalayer.stockoption.entity.StockOptionOrg;
import com.waben.stock.datalayer.stockoption.entity.StockOptionTrade;
import com.waben.stock.datalayer.stockoption.service.InquiryResultService;
import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.dto.stockoption.InquiryResultDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.stockoption.InquiryResultInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

// @RestController
// @RequestMapping("/inquiryresult")
@Component
public class InquiryResultController implements InquiryResultInterface {
    @Autowired
    private InquiryResultService inquiryResultService;

    @Override
    public Response<InquiryResultDto> add(@RequestBody InquiryResultDto inquiryResultDto) {
        InquiryResult inquiryResult = CopyBeanUtils.copyBeanProperties(InquiryResult.class, inquiryResultDto, false);
        inquiryResult.setOrg(CopyBeanUtils.copyBeanProperties(StockOptionOrg.class, inquiryResultDto.getOrg(), false));
        inquiryResult.setTrade(CopyBeanUtils.copyBeanProperties(StockOptionTrade.class, inquiryResultDto.getTrade(),
                false));
        InquiryResult inquiry = inquiryResultService.save(inquiryResult);
        InquiryResultDto result = CopyBeanUtils.copyBeanProperties(InquiryResultDto.class, inquiry, false);
        return new Response<>(result);
    }

    @Override
    public Response<InquiryResultDto> findByTrade(@PathVariable Long trade) {
        InquiryResult inquiry = inquiryResultService.fetchByTrade(trade);
        InquiryResultDto result = CopyBeanUtils.copyBeanProperties(InquiryResultDto.class, inquiry, false);
        return new Response<>(result);
    }
}
