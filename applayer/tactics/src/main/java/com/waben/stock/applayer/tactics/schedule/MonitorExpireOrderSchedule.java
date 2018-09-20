package com.waben.stock.applayer.tactics.schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.impl.calendar.WeeklyCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.waben.stock.applayer.tactics.business.HolidayBusiness;
import com.waben.stock.interfaces.constants.HolidayConstant;
import com.waben.stock.interfaces.dto.stockoption.StockOptionTradeDto;
import com.waben.stock.interfaces.enums.StockOptionTradeState;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.stockoption.StockOptionTradeInterface;
import com.waben.stock.interfaces.util.JacksonUtil;

/**
 * 监控到期订单
 * 
 * <p>
 * 如果订单到期，自动修改为已到期
 * </p>
 * 
 */
@Component
@EnableScheduling
public class MonitorExpireOrderSchedule {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private StockOptionTradeInterface tradeController;
	
	@Autowired
	private HolidayBusiness holidayBusiness;
	
	/**
	 * 14:59:00的时候检测自动到期的订单
	 */
	@Scheduled(cron = "0 59 14 * * ?")
	public void monitor() {
		try {
			boolean isTradeDay = holidayBusiness.isTradeDay(new Date());
			logger.info("进入到期订单判断，当前是否交易日{}", isTradeDay);
			if(!isTradeDay) {
				return;
			}
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
	        SimpleDateFormat hd = new SimpleDateFormat("yyyy-MM-dd");
	        List<StockOptionTradeDto> stockOptionTradeDtos = null;
	        boolean flag = false;
	        while(!flag) {
	            try {
	            	stockOptionTradeDtos = tradeController.stockOptionsWithState(3).getResult();
	                flag = true;
	            }catch (Exception e) {
	                logger.info("错误信息：{}",e.getMessage());
	                try {
						Thread.sleep(10000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
	            }
	        }
	        WeeklyCalendar workDay = new WeeklyCalendar();
	        Calendar calendar = Calendar.getInstance();

	        for (StockOptionTradeDto stockOptionTradeDto : stockOptionTradeDtos) {
	            Date expireTime = stockOptionTradeDto.getExpireTime();
	            Date currentTime = new Date();
	            boolean isWork = workDay.isTimeIncluded(expireTime.getTime());//是否是工作日
	            int isHoliyday = HolidayConstant.holiyday_2018.indexOf(hd.format(expireTime));//是否是节假日
	            while (isHoliyday>0||!isWork) {
	                calendar.setTime(expireTime);
	                calendar.add(Calendar.DATE, -1);
	                expireTime = calendar.getTime();
	                isHoliyday = HolidayConstant.holiyday_2018.indexOf(hd.format(expireTime));
	                isWork = workDay.isTimeIncluded(expireTime.getTime());
	            }
	            flag = true;
	            // if(fmt.format(expireTime).equals(fmt.format(currentTime))) {
	            if(fmt.format(currentTime).compareTo(fmt.format(expireTime)) >= 0) {
	                while (flag) {
	                    try {
	                    	Response<StockOptionTradeDto> result = tradeController.exercise(stockOptionTradeDto.getId());
	                        logger.info("结果：{}", JacksonUtil.encode(result));
	                        flag = false;
	                    }catch (Exception e) {
	                    	e.printStackTrace();
	                        Response<StockOptionTradeDto> result = tradeController.fetchById(stockOptionTradeDto.getId());
	                        if(result.getResult().getState() == StockOptionTradeState.APPLYRIGHT) {
	                            flag = false;
	                        }
	                    }
	                }
	            }
	        }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
