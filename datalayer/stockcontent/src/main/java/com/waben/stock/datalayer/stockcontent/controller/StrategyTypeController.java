package com.waben.stock.datalayer.stockcontent.controller;

import java.util.List;

import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.waben.stock.datalayer.stockcontent.entity.StrategyType;
import com.waben.stock.datalayer.stockcontent.service.StrategyTypeService;
import com.waben.stock.interfaces.dto.stockcontent.StrategyTypeDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.StrategyTypeQuery;
import com.waben.stock.interfaces.service.stockcontent.StrategyTypeInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

/**
 * @author Created by yuyidi on 2017/11/23.
 * @desc
 */
// @RestController
// @RequestMapping("/strategytype")
@Component
public class StrategyTypeController implements StrategyTypeInterface {

    @Autowired
    private StrategyTypeService straegyTypeService;

    @Override
    public Response<List<StrategyTypeDto>> lists(@RequestParam(value = "enable",defaultValue = "false") Boolean enable) {
        List<StrategyType> straegyTypes = straegyTypeService.lists(enable);
        List<StrategyTypeDto> result = CopyBeanUtils.copyListBeanPropertiesToList(straegyTypes, StrategyTypeDto.class);
        return new Response<>(result);
    }

    @Override
    public Response<PageInfo<StrategyTypeDto>> pages(@RequestBody StrategyTypeQuery query) {
        Page<StrategyType> pages = straegyTypeService.pages(query);
        PageInfo<StrategyTypeDto> result = new PageInfo<>(pages, StrategyTypeDto.class);
        return new Response<>(result);
    }

    @Override
    public Response<StrategyTypeDto> modify(@RequestBody StrategyTypeDto strategyTypeDto,@RequestParam("loss") List<Long> loss) {
        StrategyType strategyType = CopyBeanUtils.copyBeanProperties(StrategyType.class, strategyTypeDto, false);
        StrategyTypeDto result = CopyBeanUtils.copyBeanProperties(StrategyTypeDto.class,straegyTypeService.revision(strategyType,loss),false);
        return new Response<>(result);
    }

    @Override
    public void delete(@PathVariable Long id) {
        straegyTypeService.delete(id);
    }

    @Override
    public Response<StrategyTypeDto> add(@RequestBody StrategyTypeDto strategyTypeDto) {
        StrategyType strategyType = CopyBeanUtils.copyBeanProperties(StrategyType.class, strategyTypeDto, false);
        StrategyTypeDto result = CopyBeanUtils.copyBeanProperties(StrategyTypeDto.class,straegyTypeService.save(strategyType),false);
        return new Response<>(result);
    }

    @Override
	public Response<StrategyTypeDto> fetchById(@PathVariable Long id) {
		StrategyType strategyType = straegyTypeService.findById(id);
		return new Response<>(CopyBeanUtils.copyBeanProperties(StrategyTypeDto.class, strategyType, false));
	}
}
