package com.waben.stock.risk.container;

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
public class StockApplyEntrustWithdrawContainer {

    Logger logger = LoggerFactory.getLogger(getClass());
    Map<String, SecuritiesStockEntrust> withdrawContainer = new ConcurrentHashMap<>();

    public void add(SecuritiesStockEntrust stock) {
        withdrawContainer.put(stock.getTradeNo(), stock);
    }

    public void remove(String code) {
        withdrawContainer.remove(code);
    }

    public Map<String, SecuritiesStockEntrust> getWithdrawContainer() {
        return withdrawContainer;
    }

}
