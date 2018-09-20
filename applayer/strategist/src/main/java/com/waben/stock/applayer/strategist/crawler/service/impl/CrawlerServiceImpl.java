package com.waben.stock.applayer.strategist.crawler.service.impl;

import static java.util.concurrent.ForkJoinPool.defaultForkJoinWorkerThreadFactory;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.waben.stock.applayer.strategist.crawler.model.AnnualReportModel;
import com.waben.stock.applayer.strategist.crawler.model.CompanyAnnouncementModel;
import com.waben.stock.applayer.strategist.crawler.model.CompanyProfileModel;
import com.waben.stock.applayer.strategist.crawler.model.StockNewsModel;
import com.waben.stock.applayer.strategist.crawler.model.news.DailyReportModel;
import com.waben.stock.applayer.strategist.crawler.mongo.CrawlerDao;
import com.waben.stock.applayer.strategist.crawler.mongo.DailyReportDao;
import com.waben.stock.applayer.strategist.crawler.service.CrawlerService;
import com.waben.stock.applayer.strategist.crawler.util.StockCrawlerConstants;

/**
 * 自定义 股票 爬虫 获取信息
 *
 * @author yangdehong
 * @version 2017年4月20日
 */
@Service
public class CrawlerServiceImpl implements CrawlerService {

	private static final Logger LOG = LoggerFactory.getLogger(CrawlerServiceImpl.class);

	@Autowired
	private CrawlerDao crawlerDao;
	
	@Autowired
	private DailyReportDao dailyReportDao;

	@Autowired
	MongoTemplate mongoTemplate;

	public void doDelteTask(Class clazz) {

		LOG.info("start doDelteTask {}", clazz);
		ForkJoinPool forkjoinPool = new ForkJoinPool(5, defaultForkJoinWorkerThreadFactory,
				new MyUncaughtExceptionHandler(), true);

		int count = (int) mongoTemplate.count(new Query(), clazz);
		DeleteErrorInfoTask task = new DeleteErrorInfoTask(0, count, clazz, mongoTemplate);

		forkjoinPool.submit(task);

		LOG.info("end doDelteTask {}", clazz);
	}

	@Override
	public List<?> selectData(String type, String code, int page, int pageSize) {

		List<?> list = new ArrayList<>();
		switch (type) {

		case StockCrawlerConstants.STOCK_NEWS_NAME:
			list = crawlerDao.selectStockNews(code, page, pageSize);
			break;
		case StockCrawlerConstants.COMPANY_ANNOUNCEMENT_NAME:
			list = crawlerDao.selectCompanyAnnouncement(code, page, pageSize);
			break;
		case StockCrawlerConstants.ANNUAL_REPORT_NAME:
			list = crawlerDao.selectAnnualReport(code, page, pageSize);
			break;
		case StockCrawlerConstants.FINANCIAL_SUMMARY_NAME:
			list = crawlerDao.selectFinancialSummary(code, page, pageSize);
			break;
		}

		return list;
	}

	@Override
	public CompanyProfileModel selectCompanyProfileData(String code) {

		return crawlerDao.selectCompanyProfile(code);
	}

	@Override
	public StockNewsModel selectStockNews(String id) {

		return crawlerDao.selectStockNews(id);
	}

	@Override
	public CompanyAnnouncementModel selectCompanyAnnouncement(String id) {
		return crawlerDao.selectCompanyAnnouncement(id);
	}

	@Override
	public AnnualReportModel selectAnnualReport(String id) {
		return crawlerDao.selectAnnualReport(id);
	}

	private class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			LOG.error("doDelteTask error {}",e);
		}
	}

	@Override
	public List<?> selectDailyReport(Integer type, int page, int pageSize) {
		return dailyReportDao.selectDailyReport(type, page, pageSize);
	}

	@Override
	public DailyReportModel selectDailyReport(String id) {
		return dailyReportDao.selectDailyReport(id);
	}

}
