package com.waben.stock.applayer.strategist.crawler.mongo;

import java.util.List;

import com.waben.stock.applayer.strategist.crawler.model.AnnualReportModel;
import com.waben.stock.applayer.strategist.crawler.model.CompanyAnnouncementModel;
import com.waben.stock.applayer.strategist.crawler.model.CompanyProfileModel;
import com.waben.stock.applayer.strategist.crawler.model.StockNewsModel;

/**
 * Created by yhj on 17/5/2.
 */
 public interface CrawlerDao {


     List<?> selectAnnualReport(String code, int page, int pageSize);

     List<?> selectCompanyAnnouncement(String code, Integer page, Integer pageSize);

     CompanyProfileModel selectCompanyProfile(String code);

     List<?> selectFinancialSummary(String code, int page, int pageSize);

     List<?> selectStockNews(String code, int page, int pageSize);

     StockNewsModel selectStockNews(String id) ;

     CompanyAnnouncementModel selectCompanyAnnouncement(String id);

    AnnualReportModel selectAnnualReport(String id);
}
