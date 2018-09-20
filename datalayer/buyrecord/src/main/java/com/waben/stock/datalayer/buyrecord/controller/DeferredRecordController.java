package com.waben.stock.datalayer.buyrecord.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.buyrecord.entity.DeferredRecord;
import com.waben.stock.datalayer.buyrecord.service.DeferredRecordService;
import com.waben.stock.interfaces.dto.buyrecord.DeferredRecordDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.buyrecord.DeferredRecordInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

/**
 * 递延记录 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/deferredrecord")
@Component
public class DeferredRecordController implements DeferredRecordInterface {
	
	@Autowired
	private DeferredRecordService service;
	
	@Override
	public Response<List<DeferredRecordDto>> fetchByPublisherIdAndBuyRecordId(@PathVariable Long publisherId, @PathVariable Long buyRecordId) {
		List<DeferredRecord> recordList = service.findByPublisherIdAndBuyRecordId(publisherId, buyRecordId);
        return new Response<>(CopyBeanUtils.copyListBeanPropertiesToList(recordList, DeferredRecordDto.class));
	}
	
}
