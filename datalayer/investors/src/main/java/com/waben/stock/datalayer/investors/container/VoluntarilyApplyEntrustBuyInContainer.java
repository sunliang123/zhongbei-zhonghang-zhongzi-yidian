package com.waben.stock.datalayer.investors.container;

import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Created by yuyidi on 2017/12/3.
 * @desc 券商股票申请委托买入容器
 */
@Component
public class VoluntarilyApplyEntrustBuyInContainer {

    Logger logger = LoggerFactory.getLogger(getClass());
    Map<String, SecuritiesStockEntrust> buyInContainer = new ConcurrentHashMap<>();

    public void add(SecuritiesStockEntrust stock) {
        buyInContainer.put(stock.getTradeNo(), stock);
    }

    public void remove(String tradeNo) {
        buyInContainer.remove(tradeNo);
    }

    public Map<String, SecuritiesStockEntrust> getBuyInContainer() {
        return buyInContainer;
    }

}
