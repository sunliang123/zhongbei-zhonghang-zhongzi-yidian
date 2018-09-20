package com.waben.stock.datalayer.buyrecord.controller;

import java.util.List;

import com.waben.stock.datalayer.buyrecord.entity.BuyRecord;
import com.waben.stock.interfaces.dto.investor.InvestorDto;
import com.waben.stock.interfaces.dto.investor.SecurityAccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.buyrecord.entity.Settlement;
import com.waben.stock.datalayer.buyrecord.service.SettlementService;
import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.dto.buyrecord.SettlementDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.SettlementQuery;
import com.waben.stock.interfaces.service.buyrecord.SettlementInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.util.PageToPageInfo;

/**
 * 结算 Controller
 *
 * @author luomengan
 */
// @RestController
// @RequestMapping("/settlement")
@Component
public class SettlementController implements SettlementInterface {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SettlementService settlementService;

	public Response<PageInfo<SettlementDto>> pagesByQuery(@RequestBody SettlementQuery query) {
		Page<Settlement> page = settlementService.pagesByQuery(query);
		PageInfo<SettlementDto> result = PageToPageInfo.pageToPageInfo(page, SettlementDto.class);
		List<Settlement> settlementList = page.getContent();
		List<SettlementDto> settlementDtoList = result.getContent();
		for (int i = 0; i < settlementList.size(); i++) {
			Settlement settlement = settlementList.get(i);
			SettlementDto settlementDto = settlementDtoList.get(i);
			settlementDto.setBuyRecord(
					CopyBeanUtils.copyBeanProperties(BuyRecordDto.class, settlement.getBuyRecord(), false));
		}

		return new Response<>(result);
	}

	@Override
	public Response<SettlementDto> fetchByBuyRecord(@PathVariable Long id) {
		Settlement settlement = settlementService.findByBuyRecord(id);
		SettlementDto settlementDto = CopyBeanUtils.copyBeanProperties(settlement, new SettlementDto(), false);
		BuyRecord buyRecord = settlement.getBuyRecord();
		BuyRecordDto buyRecordDto = CopyBeanUtils.copyBeanProperties(buyRecord, new BuyRecordDto(), false);
		settlementDto.setBuyRecord(buyRecordDto);
		return new Response<>(settlementDto);
	}

}
