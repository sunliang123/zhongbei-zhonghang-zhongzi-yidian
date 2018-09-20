package com.waben.stock.datalayer.stockcontent;

import com.waben.stock.datalayer.stockcontent.entity.*;
import com.waben.stock.datalayer.stockcontent.pojo.Resonse;
import com.waben.stock.datalayer.stockcontent.pojo.StockVariety;
import com.waben.stock.datalayer.stockcontent.service.*;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.interfaces.web.HttpRest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockContentApplicationTests {

    @Autowired
    private StockService stockService;
    @Autowired
    private StockExponentService stockExponentService;
    @Autowired
    private AmountValueService amountValueService;
    @Autowired
    private LossService lossService;
    @Autowired
    private StrategyTypeService straegyTypeService;

    @Test
    public void initAmountValue() {
        AmountValue amountValue = new AmountValue();
        amountValue.setValue(20000L);
        amountValueService.save(amountValue);
    }

    @Test
    public void initLoss() {
        Loss loss = new Loss();
        loss.setPoint(new BigDecimal(17.5));
        lossService.save(loss);
    }

    @Test
    public void initStraegyType() {
        StrategyType straegyType = new StrategyType();
        straegyType.setName("T+5");
        straegyType.setProfit(new BigDecimal(0.5));
        straegyType.setState(true);
        straegyType.setCycle(5);
        straegyType.setDeferred(18);
        List<AmountValue> amountValues = amountValueService.fetchAmountValues();
        List<Loss> losses = lossService.fetchLosses();
        StrategyType result = straegyTypeService.save(straegyType, amountValues, losses);
        System.out.println(JacksonUtil.encode(result));
    }


    public void testGetStockList() {
        String url = "http://lemi.esongbai.com/order/order/getStockVariety.do?page={page}&pageSize={pageSize}";
        int page = 0, pageSize = 500;
        int index = 0;
        request(url, page, pageSize, index);
    }

    private Resonse request(String url, Integer page, Integer pageSize, int index) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("pageSize", String.valueOf(pageSize));
        Resonse result = HttpRest.get(url, Resonse.class, params);
        if (result.getCode().equals("200") && result.getData().length != 0) {
            ++page;
            for (StockVariety stockVariety : result.getData()) {
                Stock stock = new Stock();
                stock.setName(stockVariety.getVarietyName());
                stock.setCode(stockVariety.getVarietyType());
                StockExponent stockExponent = stockExponentService.findStockExponent(stockVariety.getExchangeCode());
                stock.setExponent(stockExponent);
                stock.setStatus(true);
                stockService.saveStock(stock);
            }
            request(url, page, result.getPageSize(), index);
        }
        return result;
    }

}
