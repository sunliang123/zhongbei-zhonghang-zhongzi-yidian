package com.waben.stock.risk.warpper.init;

import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.risk.business.BuyRecordBusiness;
import com.waben.stock.risk.warpper.messagequeue.rabbitmq.VoluntarilyApplyEntrustProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/12/14.
 * @desc 初始化申请买入的点买记录
 */
//@Component
public class StockApplyBuyInInitialize implements CommandLineRunner {

    @Autowired
    private BuyRecordBusiness buyRecordBusiness;

    @Autowired
    private VoluntarilyApplyEntrustProducer producer;

    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void run(String... args) throws Exception {
        List<BuyRecordDto> buyRecords = buyRecordBusiness.buyRecordsWithBuyIn();
        logger.info("获取买入中的点买交易记录个数：{}", buyRecords.size());
        for (BuyRecordDto buyRecord : buyRecords) {
            SecuritiesStockEntrust securitiesStockEntrust = new SecuritiesStockEntrust();
            securitiesStockEntrust.setBuyRecordId(buyRecord.getId());
            securitiesStockEntrust.setSerialCode(buyRecord.getSerialCode());
            securitiesStockEntrust.setEntrustNumber(buyRecord.getNumberOfStrand());
            securitiesStockEntrust.setEntrustPrice(buyRecord.getDelegatePrice());
            securitiesStockEntrust.setBuyRecordState(buyRecord.getState());
            securitiesStockEntrust.setStockCode(buyRecord.getStockCode());
            securitiesStockEntrust.setEntrustTime(buyRecord.getCreateTime());
            securitiesStockEntrust.setTradeNo(buyRecord.getTradeNo());
            producer.voluntarilyEntrustApplyBuyIn(securitiesStockEntrust);
        }
    }
}
