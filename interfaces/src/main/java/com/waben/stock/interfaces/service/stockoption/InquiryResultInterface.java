package com.waben.stock.interfaces.service.stockoption;

import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.dto.stockoption.InquiryResultDto;
import com.waben.stock.interfaces.pojo.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

public interface InquiryResultInterface {
    @RequestMapping(value = "/add", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<InquiryResultDto> add(@RequestBody InquiryResultDto inquiryResultDto);

    @RequestMapping(value = "/findByTrade/{trade}", method = RequestMethod.GET)
    Response<InquiryResultDto> findByTrade(@PathVariable("trade") Long trade);
}
