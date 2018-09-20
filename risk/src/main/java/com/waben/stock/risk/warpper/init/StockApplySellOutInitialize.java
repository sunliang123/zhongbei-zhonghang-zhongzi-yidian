package com.waben.stock.risk.warpper.init;

import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.interfaces.pojo.stock.quotation.StockMarket;
import com.waben.stock.risk.business.BuyRecordBusiness;
import com.waben.stock.risk.warpper.messagequeue.rabbitmq.VoluntarilyApplyEntrustProducer;
import com.waben.stock.risk.web.StockQuotationHttp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Created by yuyidi on 2017/12/14.
 * @desc
 */
//@Component
public class StockApplySellOutInitialize implements CommandLineRunner {

    @Autowired
    private BuyRecordBusiness buyRecordBusiness;

    @Autowired
    private StockQuotationHttp stockQuotationHttp;
    @Autowired
    private VoluntarilyApplyEntrustProducer producer;

    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void run(String... args) throws Exception {
        //拉取最新股票详情
        List<BuyRecordDto> buyRecords = buyRecordBusiness.buyRecordsWithSellOut();
        logger.info("获取卖出中的点买交易记录个数：{}", buyRecords.size());
        if(buyRecords.size()==0){
            return;
        }
        Set<String> codes = new HashSet();
        for (BuyRecordDto buyRecord : buyRecords) {
            codes.add(buyRecord.getStockCode());
        }
        List<String> codePrams = new ArrayList();
        codePrams.addAll(codes);
        List<StockMarket> quotations = stockQuotationHttp.fetQuotationByCode(codePrams);
        for (BuyRecordDto buyRecord : buyRecords) {
            SecuritiesStockEntrust securitiesStockEntrust = new SecuritiesStockEntrust();
            securitiesStockEntrust.setBuyRecordId(buyRecord.getId());
            securitiesStockEntrust.setSerialCode(buyRecord.getSerialCode());
            securitiesStockEntrust.setEntrustNumber(buyRecord.getNumberOfStrand());
            securitiesStockEntrust.setBuyRecordState(buyRecord.getState());
            securitiesStockEntrust.setStockCode(buyRecord.getStockCode());
            securitiesStockEntrust.setEntrustTime(buyRecord.getUpdateTime());
            securitiesStockEntrust.setWindControlType(buyRecord.getWindControlType().getIndex());
            securitiesStockEntrust.setTradeNo(buyRecord.getTradeNo());
            for(StockMarket stockMarket: quotations) {
                if(stockMarket.getInstrumentId().equals(buyRecord.getStockCode())) {
                    securitiesStockEntrust.setEntrustPrice(stockMarket.getDownLimitPrice());
                    break;
                }
            }
            producer.voluntarilyEntrustApplySellOut(securitiesStockEntrust);
        }
    }
}
