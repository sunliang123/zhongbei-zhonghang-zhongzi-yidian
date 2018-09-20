package com.waben.stock.datalayer.stockcontent.controller;

import com.waben.stock.datalayer.stockcontent.entity.AmountValue;
import com.waben.stock.datalayer.stockcontent.entity.Loss;
import com.waben.stock.datalayer.stockcontent.entity.Stock;
import com.waben.stock.datalayer.stockcontent.service.LossService;
import com.waben.stock.interfaces.dto.stockcontent.AmountValueDto;
import com.waben.stock.interfaces.dto.stockcontent.LossDto;
import com.waben.stock.interfaces.dto.stockcontent.StockDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.LossQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.service.stockcontent.LossInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @RestController
// @RequestMapping("/loss")
@Component
public class LossController implements LossInterface{

    @Autowired
    private LossService lossService;

    @Override
    public Response<PageInfo<LossDto>> pagesByQuery(@RequestBody LossQuery lossQuery) {
        Page<Loss> losses = lossService.pages(lossQuery);
        PageInfo<LossDto> result = new PageInfo<>(losses, LossDto.class);
        return new Response<>(result);
    }

    @Override
    public Response<LossDto> fetchById(@PathVariable Long id) {
        Loss loss = lossService.fetchById(id);
        LossDto lossDto = CopyBeanUtils.copyBeanProperties(loss, new LossDto(), false);
        return new Response<>(lossDto);
    }

    @Override
    public Response<LossDto> modify(@RequestBody LossDto lossDto) {
        Loss loss = CopyBeanUtils.copyBeanProperties(Loss.class, lossDto, false);
        LossDto result = CopyBeanUtils.copyBeanProperties(LossDto.class,lossService.revision(loss),false);
        return new Response<>(result);
    }

    @Override
    public Response<LossDto> add(@RequestBody LossDto lossDto) {
        Loss loss = CopyBeanUtils.copyBeanProperties(Loss.class, lossDto, false);
        LossDto result = CopyBeanUtils.copyBeanProperties(LossDto.class,lossService.save(loss),false);
        return new Response<>(result);
    }

    @Override
    public Response<List<LossDto>> fetchAllLoss() {
        List<Loss> losses = lossService.findAllLoss();
        List<LossDto> lossDtos = CopyBeanUtils.copyListBeanPropertiesToList(losses, LossDto.class);
        return new Response<>(lossDtos);
    }
}
