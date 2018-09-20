package com.waben.stock.risk;

import com.fasterxml.jackson.core.type.TypeReference;
import com.waben.stock.interfaces.constants.HolidayConstant;
import com.waben.stock.interfaces.enums.EntrustState;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.interfaces.pojo.stock.quotation.StockMarket;
import com.waben.stock.interfaces.pojo.stock.stockjy.StockResponse;
import com.waben.stock.interfaces.pojo.stock.stockjy.data.StockEntrustQueryResult;
import com.waben.stock.interfaces.pojo.stock.stockjy.data.StockLoginInfo;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.risk.schedule.WorkCalendar;
import org.junit.Test;
import org.quartz.impl.calendar.WeeklyCalendar;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Created by yuyidi on 2017/12/8.
 * @desc
 */
public class SimpleTest {

    @Test
    public void testEntrustResult() {
        String json = "{\"result\":[{\"data\":[{\"business_amount\":\"100.00\",\"business_price\":\"13.050\"," +
                "\"entrust_amount\":\"100.00\",\"entrust_bs\":\"1\",\"entrust_date\":\"20171208\"," +
                "\"entrust_no\":\"144\",\"entrust_price\":\"13.500\",\"entrust_status\":\"8\"," +
                "\"entrust_time\":\"145150\",\"exchange_type\":\"2\"," +
                "\"position_str\":\"20171208021451508400002200000144\",\"stock_account\":\"0070001553\"," +
                "\"stock_code\":\"000001\",\"stock_name\":\"平安银行\",\"withdraw_flag\":\"0\"}]}," +
                "{\"msg\":{\"error_info\":\"OK\",\"error_no\":\"3000\"}}]}";
        String json1 = "{\"result\":[{\"data\":[{\"business_amount\":\"200.00\",\"business_price\":\"6.450\"," +
                "\"entrust_amount\":\"200.00\",\"entrust_bs\":\"1\",\"entrust_no\":\"835\"," +
                "\"entrust_price\":\"7.190\",\"entrust_status\":\"8\",\"entrust_time\":\"111117\"," +
                "\"exchange_type\":\"1\",\"stock_account\":\"A204750122\",\"stock_code\":\"600248\"," +
                "\"stock_name\":\"延长化建\"}]},{\"msg\":{\"error_info\":\"OK\",\"error_no\":\"3000\"}}]}";
        StockResponse<StockEntrustQueryResult> stockResponse = JacksonUtil.decode(json, new
                TypeReference<StockResponse<StockEntrustQueryResult>>() {
                });
        StockEntrustQueryResult stockEntrustQueryResult = stockResponse.getResult().get(0).getData().get(0);
        Float amount = Float.valueOf(stockResponse.getResult().get(0).getData().get(0).getEntrustPrice());
        System.out.println(amount.intValue());
        System.out.println(stockEntrustQueryResult.getBusinessPrice());
        System.out.println(new BigDecimal(stockEntrustQueryResult.getBusinessPrice()));
        System.out.println(JacksonUtil.encode(stockResponse));
    }

    @Test
    public void testEntrustSession() {
        String json = "{\"result\":[{\"data\":[{\"client_id\":\"70001553\",\"client_name\":\"日终测试1\"," +
                "\"fund_account\":\"70001553\",\"trade_session\":\"0722f5795befa1477a9f1ca096e8ac67011512715010\"}]}," +
                "{\"msg\":{\"error_info\":\"OK\",\"error_no\":\"3000\"}}]}";
        StockResponse<StockLoginInfo> stockResponse = JacksonUtil.decode(json, new
                TypeReference<StockResponse<StockLoginInfo>>() {
                });
        System.out.println(JacksonUtil.encode(stockResponse));
    }

    @Test
    public void testBigDecimal() {
        BigDecimal amountValue = new BigDecimal(10000);
        BigDecimal price = new BigDecimal(13.45);
        System.out.println(amountValue.divide(price, 2, RoundingMode.HALF_EVEN).intValue());
    }

    @Test
    public void testCard() {
        System.out.println(ChickID("421126199206302514"));
    }

    public boolean ChickID(String text) {
        if (text != null) {
            int correct = new IdCardUtil(text).isCorrect();
            if (0 == correct) {// 符合规范
                return true;
            }
        }
        return false;
    }

    @Test
    public void testBack() {
        Map<String, SecuritiesStockEntrust> init = new ConcurrentHashMap<>();
//        init.put("12345678", new SecuritiesStockEntrust("12345678", "abcdef1"));
//        init.put("12345681", new SecuritiesStockEntrust("12345681", "abcdef1"));
//        init.put("12345680", new SecuritiesStockEntrust("12345680", "abcdef1"));
//        init.put("12345689", new SecuritiesStockEntrust("12345689", null));
//        init.put("12345608", new SecuritiesStockEntrust("12345608", null));
        String tradeSession = null;
        while (true) {
            //容器中委托数据可能包含来自数据库或者消息队列
            Map<String, SecuritiesStockEntrust> stockEntrusts =init;
            System.out.println("券商委托股票容器内剩余:"+ stockEntrusts.size()+"个委托订单");
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Map.Entry<String, SecuritiesStockEntrust> entry : stockEntrusts.entrySet()) {
                try {
                    SecuritiesStockEntrust securitiesStockEntrust = entry.getValue();
                    String currTradeSession = securitiesStockEntrust.getTradeSession();
                    if (currTradeSession == null) {
                        System.out.println("交易号："+securitiesStockEntrust.getTradeNo()+"交易回话为空");
                        securitiesStockEntrust.setTradeSession(tradeSession);
                        continue;
                    } else {
                        tradeSession = currTradeSession;
                    }
                    System.out.println("开始执行委托查询,委托编号"+securitiesStockEntrust.getTradeNo()+"---"+securitiesStockEntrust.getTradeSession());
                } catch (ServiceException ex) {
                    System.out.println("券商委托单查询异常"+ ex.getMessage());
                }

            }
        }
    }

    @Test
    public void test() {
        String amount = "21.030";
        System.out.println(Float.valueOf(amount).intValue());
    }

    @Test
    public void testWorkTime() {
        WeeklyCalendar workDay = new WeeklyCalendar();
        //排除特定的日期
        WorkCalendar workCalendar = new WorkCalendar(workDay, HolidayConstant.holiyday_2018);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(new Date(workCalendar.getNextIncludedTime(new Date().getTime())));

    }
}
