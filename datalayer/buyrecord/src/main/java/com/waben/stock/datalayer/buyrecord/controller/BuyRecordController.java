package com.waben.stock.datalayer.buyrecord.controller;

import java.math.BigDecimal;
import java.util.List;

import com.waben.stock.datalayer.buyrecord.entity.Settlement;
import com.waben.stock.interfaces.dto.buyrecord.SettlementDto;
import com.waben.stock.interfaces.dto.stockoption.OfflineStockOptionTradeDto;
import com.waben.stock.interfaces.enums.OfflineStockOptionTradeState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.buyrecord.entity.BuyRecord;
import com.waben.stock.datalayer.buyrecord.service.BuyRecordService;
import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.enums.BuyRecordState;
import com.waben.stock.interfaces.enums.WindControlType;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.BuyRecordQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.StrategyHoldingQuery;
import com.waben.stock.interfaces.pojo.query.StrategyPostedQuery;
import com.waben.stock.interfaces.pojo.query.StrategyUnwindQuery;
import com.waben.stock.interfaces.service.buyrecord.BuyRecordInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.util.PageToPageInfo;

/**
 * 点买记录 Controller
 *
 * @author luomengan
 */
// @RestController
// @RequestMapping("/buyrecord")
@Component
public class BuyRecordController implements BuyRecordInterface {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BuyRecordService buyRecordService;
    
    @Override
    public Response<BuyRecordDto> fetchBuyRecord(@PathVariable Long buyrecord) {
        BuyRecord buyRecord = buyRecordService.findBuyRecord(buyrecord);
        BuyRecordDto buyRecordDto = CopyBeanUtils.copyBeanProperties(BuyRecordDto.class, buyRecord, false);
        return new Response<>(buyRecordDto);
    }

    @Override
    public Response<BuyRecordDto> addBuyRecord(@RequestBody BuyRecordDto buyRecordDto) {
    	logger.info("发布人{}点买股票{}，申请资金{}!", buyRecordDto.getPublisherId(), buyRecordDto.getStockCode(), buyRecordDto.getApplyAmount());
        BuyRecord buyRecord = CopyBeanUtils.copyBeanProperties(BuyRecord.class, buyRecordDto, false);
        return new Response<>(
                CopyBeanUtils.copyBeanProperties(BuyRecordDto.class, buyRecordService.save(buyRecord), false));
    }

    @Override
    public Response<PageInfo<BuyRecordDto>> pagesByQuery(@RequestBody BuyRecordQuery buyRecordQuery) {
        Page<BuyRecord> page = buyRecordService.pagesByQuery(buyRecordQuery);
        PageInfo<BuyRecordDto> result = PageToPageInfo.pageToPageInfo(page, BuyRecordDto.class);
        return new Response<>(result);
    }

    @Override
    public Response<BuyRecordDto> buyLock(@PathVariable Long investorId, @PathVariable Long id, String delegateNumber) {
    	logger.info("投资人{}买入锁定，点买记录{}，委托编号{}!", investorId, id, delegateNumber);
        BuyRecord buyRecord = buyRecordService.buyLock(investorId, id, delegateNumber);
        return new Response<>(CopyBeanUtils.copyBeanProperties(BuyRecordDto.class, buyRecord, false));
    }

    @Override
    public Response<BuyRecordDto> buyInto(@PathVariable Long investorId, @PathVariable Long id,
                                          BigDecimal buyingPrice) {
    	logger.info("投资人{}买入成功，点买记录{}，买入价格{}!", investorId, id, buyingPrice);
        BuyRecord buyRecord = buyRecordService.buyInto(investorId, id, buyingPrice);
        return new Response<>(CopyBeanUtils.copyBeanProperties(BuyRecordDto.class, buyRecord, false));
    }

    @Override
    public Response<BuyRecordDto> sellApply(@PathVariable Long publisherId, @PathVariable Long id) {
    	logger.info("发布人{}申请卖出点买记录{}!", publisherId, id);
        BuyRecord buyRecord = buyRecordService.sellApply(publisherId, id);
        return new Response<>(CopyBeanUtils.copyBeanProperties(BuyRecordDto.class, buyRecord, false));
    }

    @Override
    public Response<BuyRecordDto> sellLock(@PathVariable Long investorId, @PathVariable Long id, String delegateNumber,
                                           String windControlTypeIndex) {
    	logger.info("投资人{}卖出锁定，点买记录{}，委托编号{}，风控类型-{}!", investorId, id, delegateNumber, WindControlType.getByIndex(windControlTypeIndex).getType());
        BuyRecord buyRecord = buyRecordService.sellLock(investorId, id, delegateNumber,
                WindControlType.getByIndex(windControlTypeIndex));
        return new Response<>(CopyBeanUtils.copyBeanProperties(BuyRecordDto.class, buyRecord, false));
    }

    @Override
    public Response<BuyRecordDto> withdrawLock(@PathVariable String entrustNo, @PathVariable Long id) {
        BuyRecord buyRecord = buyRecordService.withdrawLock(entrustNo,id);
        return new Response<>(CopyBeanUtils.copyBeanProperties(BuyRecordDto.class, buyRecord, false));
    }

    @Override
    public Response<BuyRecordDto> sellOut(@PathVariable Long investorId, @PathVariable Long id,
                                          BigDecimal sellingPrice) {
    	logger.info("投资人{}卖出成功，点买记录{}，卖出价格{}!", investorId, id, sellingPrice);
        BuyRecord buyRecord = buyRecordService.sellOut(investorId, id, sellingPrice);
        return new Response<>(CopyBeanUtils.copyBeanProperties(BuyRecordDto.class, buyRecord, false));
    }
    
    @Override
	public Response<BuyRecordDto> deferred(@PathVariable Long id) {
    	BuyRecord buyRecord = buyRecordService.deferred(id);
        return new Response<>(CopyBeanUtils.copyBeanProperties(BuyRecordDto.class, buyRecord, false));
	}
    
    @Override
    public Response<Integer> strategyJoinCount(@PathVariable Long publisherId, @PathVariable Long strategyTypeId) {
    	return new Response<>(buyRecordService.strategyJoinCount(publisherId, strategyTypeId));
    }

    @Override
    public Response<Void> dropBuyRecord(@PathVariable Long id) {
        buyRecordService.remove(id);
        return new Response<>();
    }
    
    @Override
    public Response<List<BuyRecordDto>> buyRecordsWithStatus(@PathVariable("state") Integer state) {
        BuyRecordState buyRecordState = BuyRecordState.getByIndex(String.valueOf(state));
        List<BuyRecord> buyRecords = buyRecordService.fetchByStateAndOrderByCreateTime(buyRecordState);
        List<BuyRecordDto> result = CopyBeanUtils.copyListBeanPropertiesToList(buyRecords, BuyRecordDto.class);
        for (int i=0; i<buyRecords.size(); i++) {
            SettlementDto settlementDto = CopyBeanUtils.copyBeanProperties(SettlementDto.class, buyRecords.get(i).getSettlement(), false);
            result.get(i).setSettlement(settlementDto);
        }
        return new Response<>(result);
    }

    @Override
    public Response<PageInfo<BuyRecordDto>> pagesByPostedQuery(@RequestBody StrategyPostedQuery strategyPostedQuery) {
        Page<BuyRecord> page = buyRecordService.pagesByPostedQuery(strategyPostedQuery);
        PageInfo<BuyRecordDto> result = PageToPageInfo.pageToPageInfo(page, BuyRecordDto.class);
        return new Response<>(result);
    }

    @Override
    public Response<PageInfo<BuyRecordDto>> pagesByHoldingQuery(@RequestBody StrategyHoldingQuery strategyHoldingQuery) {
        Page<BuyRecord> page = buyRecordService.pagesByHoldingQuery(strategyHoldingQuery);
        PageInfo<BuyRecordDto> result = PageToPageInfo.pageToPageInfo(page, BuyRecordDto.class);
        return new Response<>(result);
    }

    @Override
    public Response<PageInfo<BuyRecordDto>> pagesByUnwindQuery(@RequestBody StrategyUnwindQuery strategyUnwindQuery) {
        Page<BuyRecord> page = buyRecordService.pagesByUnwindQuery(strategyUnwindQuery);
        PageInfo<BuyRecordDto> result = PageToPageInfo.pageToPageInfo(page, BuyRecordDto.class);
        return new Response<>(result);
    }

    @Override
    public Response<PageInfo<BuyRecordDto>> pagesByWithdrawQuery(@RequestBody StrategyUnwindQuery trategyUnwindQuery) {
        Page<BuyRecord> page = buyRecordService.pagesByWithdrawQuery(trategyUnwindQuery);
        PageInfo<BuyRecordDto> result = PageToPageInfo.pageToPageInfo(page, BuyRecordDto.class);
        return new Response<>(result);
    }

    @Override
    public void delete(@PathVariable Long id) {
        buyRecordService.delete(id);
    }

    @Override
    public Response<BuyRecordDto> updateState(@RequestBody BuyRecordDto buyRecordDto) {
        BuyRecord buyRecord = CopyBeanUtils.copyBeanProperties(BuyRecord.class, buyRecordDto, false);
        BuyRecordDto result = CopyBeanUtils.copyBeanProperties(BuyRecordDto.class, buyRecordService.revisionState(buyRecord), false);
        return new Response<>(result);
    }

    @Override
    public Response<List<BuyRecordDto>> fetchMonthsProfit(@PathVariable String year) {
        List<BuyRecord> buyRecords = buyRecordService.findByStateAndUpdateTimeBetween(BuyRecordState.UNWIND,year);
        List<BuyRecordDto> result = CopyBeanUtils.copyListBeanPropertiesToList(buyRecords, BuyRecordDto.class);
        for (int i=0; i<buyRecords.size(); i++) {
            SettlementDto settlementDto = CopyBeanUtils.copyBeanProperties(SettlementDto.class, buyRecords.get(i).getSettlement(), false);
            result.get(i).setSettlement(settlementDto);
        }
        return new Response<>(result);
    }


    @Override
	public Response<BuyRecordDto> revoke(@PathVariable Long id) {
		BuyRecord buyRecord = buyRecordService.revoke(id);
        return new Response<>(CopyBeanUtils.copyBeanProperties(BuyRecordDto.class, buyRecord, false));
	}


	@Override
    public Response<Boolean> echo() {
        return new Response<>(true);
    }

}
