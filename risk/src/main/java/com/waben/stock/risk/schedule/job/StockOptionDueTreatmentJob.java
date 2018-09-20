package com.waben.stock.risk.schedule.job;

import com.waben.stock.interfaces.constants.HolidayConstant;
import com.waben.stock.interfaces.dto.stockoption.StockOptionTradeDto;
import com.waben.stock.interfaces.enums.StockOptionTradeState;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.risk.business.StockOptionTradeBusiness;
import com.waben.stock.risk.warpper.ApplicationContextBeanFactory;
import org.quartz.*;
import org.quartz.impl.calendar.WeeklyCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class StockOptionDueTreatmentJob implements InterruptableJob {
    Logger logger = LoggerFactory.getLogger(getClass());
    private Boolean interrupted = false;

    private StockOptionTradeBusiness stockOptionTradeBusiness = ApplicationContextBeanFactory.getBean
            (StockOptionTradeBusiness.class);;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hd = new SimpleDateFormat("yyyy-MM-dd");
        List<StockOptionTradeDto> stockOptionTradeDtos = null;
        boolean flag = false;
        while(!flag) {
            try {
                stockOptionTradeDtos = stockOptionTradeBusiness.stockOptionsWithTurnover();
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
                        StockOptionTradeDto result = stockOptionTradeBusiness.stockOptionDueTreatment(stockOptionTradeDto.getId());
                        logger.info("结果：{}", JacksonUtil.encode(result));
                        flag = false;
                    }catch (Exception e) {
                        logger.info("错误信息：{}",e.getMessage());
                        StockOptionTradeDto result = stockOptionTradeBusiness.findById(stockOptionTradeDto.getId());
                        if(result.getState() == StockOptionTradeState.APPLYRIGHT) {
                            flag = false;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        logger.info("期权到期处理任务被中断");
        interrupted = true;
    }
}
