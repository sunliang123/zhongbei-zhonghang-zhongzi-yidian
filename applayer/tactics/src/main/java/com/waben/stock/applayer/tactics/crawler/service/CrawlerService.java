package com.waben.stock.applayer.tactics.crawler.service;

import java.util.List;

import com.waben.stock.applayer.tactics.crawler.model.AnnualReportModel;
import com.waben.stock.applayer.tactics.crawler.model.CompanyAnnouncementModel;
import com.waben.stock.applayer.tactics.crawler.model.CompanyProfileModel;
import com.waben.stock.applayer.tactics.crawler.model.StockNewsModel;
import com.waben.stock.applayer.tactics.crawler.model.news.DailyReportModel;

public interface CrawlerService {
    List<?> selectData(String type, String code, int page, int pageSize);

    CompanyProfileModel selectCompanyProfileData(String code);

    StockNewsModel selectStockNews(String id);

    CompanyAnnouncementModel selectCompanyAnnouncement(String id);

    AnnualReportModel selectAnnualReport(String id);

    void doDelteTask(Class clazz);
    
    List<?> selectDailyReport(Integer type, int page, int pageSize);

    DailyReportModel selectDailyReport(String id);

}
