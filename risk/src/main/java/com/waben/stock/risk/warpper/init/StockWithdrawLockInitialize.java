package com.waben.stock.risk.warpper.init;

import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.enums.EntrustState;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.risk.business.BuyRecordBusiness;
import com.waben.stock.risk.container.StockApplyEntrustWithdrawContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/12/14.
 * @desc
 */
//@Component
public class StockWithdrawLockInitialize implements CommandLineRunner {

    @Autowired
    private BuyRecordBusiness buyRecordBusiness;

    @Autowired
    private StockApplyEntrustWithdrawContainer stockApplyEntrustWithdrawContainer;
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void run(String... args) throws Exception {
        List<BuyRecordDto> buyRecords = buyRecordBusiness.buyRecordsWithWithdrawStock();
        logger.info("获取撤单锁定的点买交易记录个数：{}", buyRecords.size());
        for (BuyRecordDto buyRecord : buyRecords) {
            SecuritiesStockEntrust securitiesStockEntrust = new SecuritiesStockEntrust();
            securitiesStockEntrust.setBuyRecordId(buyRecord.getId());
            securitiesStockEntrust.setSerialCode(buyRecord.getSerialCode());
            securitiesStockEntrust.setInvestor(buyRecord.getInvestorId());
            securitiesStockEntrust.setEntrustNumber(buyRecord.getNumberOfStrand());
            securitiesStockEntrust.setEntrustPrice(buyRecord.getDelegatePrice());
            securitiesStockEntrust.setBuyRecordState(buyRecord.getState());
            securitiesStockEntrust.setTradeNo(buyRecord.getTradeNo());
            securitiesStockEntrust.setEntrustNo(buyRecord.getDelegateNumber());
            securitiesStockEntrust.setEntrustState(EntrustState.REPORTEDTOWITHDRAW);
            securitiesStockEntrust.setLossPosition(buyRecord.getLossPosition());
            securitiesStockEntrust.setProfitPosition(buyRecord.getProfitPosition());
            securitiesStockEntrust.setEntrustTime(buyRecord.getUpdateTime());
            stockApplyEntrustWithdrawContainer.add(securitiesStockEntrust);
        }
    }
}
