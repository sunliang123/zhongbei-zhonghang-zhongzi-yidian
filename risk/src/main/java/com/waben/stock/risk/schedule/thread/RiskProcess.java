package com.waben.stock.risk.schedule.thread;

import com.waben.stock.interfaces.enums.WindControlType;
import com.waben.stock.interfaces.pojo.stock.quotation.PositionStock;
import com.waben.stock.interfaces.pojo.stock.quotation.StockMarket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class RiskProcess implements Callable<List<PositionStock>> {
    Logger logger = LoggerFactory.getLogger(getClass());
    String expriessTime = "14:40:00";
    long millisOfDay = 24 * 60 * 60 * 1000;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private StockMarket stockMarket;
    private List<PositionStock> positionStock;
    private Map<String, PositionStock> entrustSellOutContainer;

    public RiskProcess(StockMarket stockMarket, List<PositionStock> positionStocks, Map<String, PositionStock>
            entrustSellOutContainer) {
        this.stockMarket = stockMarket;
        this.positionStock = positionStocks;
        this.entrustSellOutContainer = entrustSellOutContainer;
    }

    @Override
    public List<PositionStock> call() {
        List<PositionStock> counts = new ArrayList<>();
        logger.info("股票:{},已持仓中订单数量:{}", stockMarket.getName(), positionStock.size());
        long start = System.currentTimeMillis();
        String tradeSession = "880003450508";
        // 遍历容器内持仓中的点买交易订单
        for (PositionStock riskBuyInStock : positionStock) {
            //如果申请卖出容器里没有该订单，说明该订单已经被申请卖出，删除持仓容器里的订单
            if (entrustSellOutContainer.get(riskBuyInStock.getTradeNo()) == null) {
                logger.info("删除持仓容器的数据:{}", riskBuyInStock.toString());
                counts.add(riskBuyInStock);
                continue;
            }
            String currTradeSession = riskBuyInStock.getTradeSession();
            if (currTradeSession == null) {
                if (tradeSession == null) {
                    continue;
                }
                riskBuyInStock.setTradeSession(tradeSession);
            } else {
                tradeSession = currTradeSession;
            }
            BigDecimal lastPrice = stockMarket.getLastPrice();
            BigDecimal downLimitPrice = stockMarket.getDownLimitPrice();//跌停价格
            BigDecimal upLimitPrice = stockMarket.getUpLimitPrice();//涨停价格
            BigDecimal lossPosition = riskBuyInStock.getLossPosition();
            BigDecimal profitPosition = riskBuyInStock.getProfitPosition();
            logger.info("最新行情价格:{},止损价格：{},止盈价格:{}", lastPrice, lossPosition, profitPosition);
            Date currentDay = new Date();
            Date expriessDay = riskBuyInStock.getExpireTime();
            String currentTime = sdf.format(currentDay);
            logger.info("过期时间:{},当前时间:{}", time.format(expriessDay), time.format(currentDay));
            logger.info("过期时间:{},当前时间:{}", expriessTime, currentTime);
            logger.info("结果：{}", currentTime.compareTo(expriessTime));
            long expriessDayL = Long.parseLong(expriessDay.getTime() + "");
            long currentDayL = Long.parseLong(currentDay.getTime() + "");
//            //判断是否跌停或涨停
//            if(lastPrice.compareTo(upLimitPrice)>=0||lastPrice.compareTo(downLimitPrice)<=0) {
//                if(lastPrice.compareTo(upLimitPrice)>=0) {
//                    //涨停
//                    riskBuyInStock.setWindControlType(WindControlType.LIMITUP.getIndex());
//                }else {
//                    //跌停
//                    riskBuyInStock.setWindControlType(WindControlType.LIMITDOWN.getIndex());
//                }
//            }else

            if(stockMarket.getStatus()==0||lastPrice.compareTo(downLimitPrice)==0||lastPrice.compareTo(upLimitPrice)==0) {
                if(lastPrice.compareTo(upLimitPrice)==0) {
                    logger.info("股票已涨停：{}",riskBuyInStock.getStockName());
                }
                if(lastPrice.compareTo(downLimitPrice)==0) {
                    logger.info("股票已跌停：{}",riskBuyInStock.getStockName());
                }
                if (stockMarket.getStatus()==0) {
                    logger.info("股票已停牌：{}",riskBuyInStock.getStockName());
                }
                continue;
            }
            if (currentDayL - expriessDayL >= 0 && currentTime.compareTo(expriessTime) >= 0) {
                //判断持仓到期时间是否已经达到且是否达到14:40:00
                logger.info("交易期满:{}", riskBuyInStock.getTradeNo());
                riskBuyInStock.setWindControlType(WindControlType.TRADINGEND.getIndex());
            } else {
                // 判断  最新行情价格与 当前持仓订单买入价格   是否达到止盈或止损点位  若 达到则 执行强制卖出  卖出跌停价
                if (profitPosition.compareTo(lastPrice) == -1 || profitPosition.compareTo(lastPrice) == 0 ||
                        lossPosition.compareTo(lastPrice) == 1 || lossPosition.compareTo(lastPrice) == 0) {
                    long buying = riskBuyInStock.getBuyingTime().getTime()/millisOfDay;//买入时间
                    long current = currentDay.getTime()/millisOfDay;
                    if(current-buying>=1) {
                        if (lastPrice.compareTo(profitPosition) >= 0) {
                            //达到止盈点位
                            riskBuyInStock.setWindControlType(WindControlType.REACHPROFITPOINT.getIndex());
                        } else {
                            //达到止损点位
                            riskBuyInStock.setWindControlType(WindControlType.REACHLOSSPOINT.getIndex());
                        }
                        logger.info("满足止损止盈条件:{}", riskBuyInStock.getTradeNo());
                    }
                } else {
                    logger.info("当前持仓订单未满足止损或止盈条件,不执行风控强制平仓：{}", riskBuyInStock.getTradeNo());
                    continue;
                }
            }
            //若当前时间点是在14:40之前 则不进入风控队列
            riskBuyInStock.setEntrustPrice(stockMarket.getDownLimitPrice());
            counts.add(riskBuyInStock);
        }
        long end = System.currentTimeMillis();
        logger.info("Call执行时间：{}", (end - start));
        return counts;
    }
}