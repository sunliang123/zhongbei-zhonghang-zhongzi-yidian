package com.waben.stock.applayer.strategist.crawler.mongo;

import java.util.List;

import com.waben.stock.applayer.strategist.crawler.model.news.DailyReportModel;

/**
 * Created by qmw on 2017/10/26.
 */
public interface DailyReportDao {
    String saveReport(DailyReportModel dailyReport);
    
    List<?> selectDailyReport(Integer type, int page, int pageSize);

	DailyReportModel selectDailyReport(String id);
}
