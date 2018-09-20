package com.waben.stock.datalayer.investors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.waben.stock.interfaces.pojo.stock.stockjy.StockMsg;
import com.waben.stock.interfaces.pojo.stock.stockjy.StockMsgResult;
import com.waben.stock.interfaces.pojo.stock.stockjy.StockResponse;
import com.waben.stock.interfaces.pojo.stock.stockjy.StockResult;
import com.waben.stock.interfaces.pojo.stock.stockjy.data.StockLoginInfo;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.interfaces.web.HttpRest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Created by yuyidi on 2017/11/30.
 * @desc
 */
public class SimpleTest {

    @Test
    public void stockLogin() {
        String url = "http://106.15.37.226:8445/stockjy/login?account_content={accountContent}&password={password}";
        Map<String, String> params = new HashMap<>();
        params.put("accountContent", "70001553");
        params.put("password", "111111");
//        StockResponse<StockLoginInfo> json = HttpRest.get(url, StockResponse.class, params);
//        System.out.println(JacksonUtil.encode(json));
//        System.out.println(json.getResult().get(0).getData().get(0).getTradeSession());
//        System.out.println(json.getResult().get(1).getMsg().getErrorNo());
        String json = HttpRest.get(url, String.class, params);
        StockResponse<StockLoginInfo> stockResponse = JacksonUtil.decode(json, new TypeReference<StockResponse<StockLoginInfo>>() {});
        System.out.println(stockResponse.getResult().get(0).getData().get(0).getTradeSession());
        System.out.println(stockResponse.getResult().get(1).getMsg().getErrorNo());
    }

    @Test
    public void simpleJson() {
//        StockResponse stockResponse = new StockResponse();
//        StockResult stockResult = new StockResult();
//        StockLoginInfo stockLoginInfo = new StockLoginInfo();
//        stockLoginInfo.setClientId("11111");
//        List<StockLoginInfo> data = new ArrayList<>();
//        data.add(stockLoginInfo);
//        StockMsg stockMsg = new StockMsg();
//        stockMsg.setErrorNo("3000");
//        stockResult.setData(data);
//        stockResult.setMsg(stockMsg);
//        stockResponse.setResult(new StockResult[]{stockResult});
//

        StockResponse stockResponse = new StockResponse();
        StockLoginInfo stockLoginInfo = new StockLoginInfo();
        stockLoginInfo.setClientId("11111");
        List<StockLoginInfo> data = new ArrayList<>();
        data.add(stockLoginInfo);
        StockMsgResult stockMsgResult = new StockMsgResult();
        StockMsg stockMsg = new StockMsg();
        stockMsg.setErrorNo("3000");
        stockMsgResult.setMsg(stockMsg);
//        Map<String,LinkedHashMap> obj = ( Map<String,LinkedHashMap>) JacksonUtil.decode(JacksonUtil.encode
// (stockResponse),StockResponse.class).getResult().get(1);
//        System.out.println(""+obj.get("msg").get("error_no"));

    }
}
