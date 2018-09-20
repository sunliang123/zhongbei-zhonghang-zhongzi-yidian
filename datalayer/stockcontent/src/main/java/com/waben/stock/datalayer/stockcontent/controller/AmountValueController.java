package com.waben.stock.datalayer.stockcontent.controller;

import com.waben.stock.datalayer.stockcontent.entity.AmountValue;
import com.waben.stock.datalayer.stockcontent.entity.Loss;
import com.waben.stock.datalayer.stockcontent.entity.StrategyType;
import com.waben.stock.datalayer.stockcontent.service.AmountValueService;
import com.waben.stock.interfaces.dto.investor.InvestorDto;
import com.waben.stock.interfaces.dto.investor.SecurityAccountDto;
import com.waben.stock.interfaces.dto.stockcontent.AmountValueDto;
import com.waben.stock.interfaces.dto.stockcontent.LossDto;
import com.waben.stock.interfaces.dto.stockcontent.StrategyTypeDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.AmountValueQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.service.stockcontent.AmountValueInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// @RestController
// @RequestMapping("/amountvalue")
@Component
public class AmountValueController implements AmountValueInterface{

    @Autowired
    private AmountValueService amountValueService;

    @Override
    public Response<PageInfo<AmountValueDto>> pagesByQuery(@RequestBody AmountValueQuery amountValueQuery) {
        Page<AmountValue> amountValues = amountValueService.pages(amountValueQuery);
        PageInfo<AmountValueDto> result = new PageInfo<>(amountValues, AmountValueDto.class);
        return new Response<>(result);
    }

    @Override
    public Response<AmountValueDto> fetchById(@PathVariable Long id) {
        AmountValue amountValue = amountValueService.fetchById(id);
        AmountValueDto amountValueDto = CopyBeanUtils.copyBeanProperties(amountValue, new AmountValueDto(), false);
        return new Response<>(amountValueDto);
    }

    @Override
    public Response<AmountValueDto> modify(@RequestBody AmountValueDto amountValueDto) {
        AmountValue amountValue = CopyBeanUtils.copyBeanProperties(AmountValue.class, amountValueDto, false);
        AmountValueDto result = CopyBeanUtils.copyBeanProperties(AmountValueDto.class,amountValueService.revision(amountValue),false);
        return new Response<>(result);
    }
}
