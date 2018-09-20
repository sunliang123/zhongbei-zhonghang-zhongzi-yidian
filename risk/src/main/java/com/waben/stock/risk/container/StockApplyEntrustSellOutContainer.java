package com.waben.stock.risk.container;

import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Created by yuyidi on 2017/12/3.
 * @desc 券商股票申请委托卖出容器
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class StockApplyEntrustSellOutContainer {

    Logger logger = LoggerFactory.getLogger(getClass());
    Map<String, SecuritiesStockEntrust> sellOutContainer = new ConcurrentHashMap<>();

    public void add(SecuritiesStockEntrust stock) {
        sellOutContainer.put(stock.getTradeNo(), stock);
    }

    public void remove(String code) {
        sellOutContainer.remove(code);
    }

    public Map<String, SecuritiesStockEntrust> getSellOutContainer() {
        return sellOutContainer;
    }
}
