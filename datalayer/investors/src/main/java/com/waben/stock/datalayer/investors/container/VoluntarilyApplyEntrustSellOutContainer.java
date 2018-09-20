package com.waben.stock.datalayer.investors.container;

import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.interfaces.util.JacksonUtil;
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
public class VoluntarilyApplyEntrustSellOutContainer {

    Logger logger = LoggerFactory.getLogger(getClass());
    Map<String, SecuritiesStockEntrust> sellOutContainer = new ConcurrentHashMap<>();

    public void add(SecuritiesStockEntrust stock) {
        sellOutContainer.put(stock.getTradeNo(), stock);
    }

    public void remove(String tradeNo) {
        sellOutContainer.remove(tradeNo);
    }

    public Map<String, SecuritiesStockEntrust> getSellOutContainer() {
        return sellOutContainer;
    }

}
