package com.waben.stock.applayer.tactics.crawler.mongo;

import java.util.List;

import com.waben.stock.applayer.tactics.crawler.model.news.DailyReportModel;

/**
 * Created by qmw on 2017/10/26.
 */
public interface DailyReportDao {
    String saveReport(DailyReportModel dailyReport);
    
    List<?> selectDailyReport(Integer type, int page, int pageSize);

	DailyReportModel selectDailyReport(String id);
}
