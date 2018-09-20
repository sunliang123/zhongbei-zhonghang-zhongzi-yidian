package com.waben.stock.risk.warpper.init;

import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.dto.stockcontent.StockDto;
import com.waben.stock.interfaces.enums.EntrustState;
import com.waben.stock.interfaces.enums.EntrustType;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.risk.business.BuyRecordBusiness;
import com.waben.stock.risk.business.StockBusiness;
import com.waben.stock.risk.container.StockApplyEntrustSellOutContainer;
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
public class StockSellOutLockInitialize implements CommandLineRunner {

    @Autowired
    private BuyRecordBusiness buyRecordBusiness;
    @Autowired
    private StockBusiness stockBusiness;
    @Autowired
    private StockApplyEntrustSellOutContainer stockApplyEntrustSellOutContainer;
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void run(String... args) throws Exception {
        List<BuyRecordDto> buyRecords = buyRecordBusiness.buyRecordsWithSellOutLock();
        logger.info("获取卖出锁定的点买交易记录个数：{}", buyRecords.size());
        for (BuyRecordDto buyRecord : buyRecords) {
            SecuritiesStockEntrust securitiesStockEntrust = new SecuritiesStockEntrust();
            securitiesStockEntrust.setBuyRecordId(buyRecord.getId());
            securitiesStockEntrust.setSerialCode(buyRecord.getSerialCode());
            securitiesStockEntrust.setInvestor(buyRecord.getInvestorId());
            StockDto stockDto = stockBusiness.fetchByCode(buyRecord.getStockCode());
            securitiesStockEntrust.setStockName(stockDto.getName());
            securitiesStockEntrust.setStockCode(stockDto.getCode());
            securitiesStockEntrust.setExponent(stockDto.getExponent().getExponentCode());
            securitiesStockEntrust.setEntrustNumber(buyRecord.getNumberOfStrand());
            securitiesStockEntrust.setEntrustPrice(buyRecord.getDelegatePrice());
            securitiesStockEntrust.setBuyRecordState(buyRecord.getState());
            securitiesStockEntrust.setEntrustType(EntrustType.SELL);
//            securitiesStockEntrust.setTradeSession(investorDto.getSecuritiesSession());
            securitiesStockEntrust.setTradeNo(buyRecord.getTradeNo());
            securitiesStockEntrust.setEntrustNo(buyRecord.getDelegateNumber());
            securitiesStockEntrust.setEntrustState(EntrustState.HASBEENREPORTED);
            securitiesStockEntrust.setLossPosition(buyRecord.getLossPosition());
            securitiesStockEntrust.setProfitPosition(buyRecord.getProfitPosition());
            securitiesStockEntrust.setWindControlType(buyRecord.getWindControlType().getIndex());
            securitiesStockEntrust.setEntrustTime(buyRecord.getUpdateTime());
            stockApplyEntrustSellOutContainer.add(securitiesStockEntrust);
        }
    }
}
