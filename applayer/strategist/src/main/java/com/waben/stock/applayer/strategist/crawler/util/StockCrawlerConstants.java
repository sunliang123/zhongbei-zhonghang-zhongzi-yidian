package com.waben.stock.applayer.strategist.crawler.util;

import java.util.HashMap;
import java.util.Map;

public class StockCrawlerConstants {

    public static final int CONN_MAX_TIMES = 3;

    /**
     * mongo 等信息 抬头
     */
    public static final String CRAWLER_MONGO_COLLECTIONS_PREFIX = "crawler_";
    /**
     * 爬虫 捕获 信息来源
     */
    public static final String STOCK_INFO_FROM = "sina";
    /**
     * 基础信息，存过记录
     */
    public static final String CRAWLER_MONGO_BASE_COLLECTIONS = "crawler_mongo_base_infos";
    /**
     * 主要捕获信息
     */
    public static final String STOCK_NEWS_NAME = "stock_news";// 个股资讯
    public static final String COMPANY_PROFILE_NAME = "company_profile";// 公司简介
    public static final String COMPANY_ANNOUNCEMENT_NAME = "company_announcement";// 公司公告
    public static final String ANNUAL_REPORT_NAME = "annual_report";// 年度报告
    public static final String FINANCIAL_SUMMARY_NAME = "financial_summary";//财务摘要

    /**
     * 所有连接
     */
    public static final String STOCK_INFO_BASE_URL = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCB_AllNewsStock/symbol/{0}{1}.phtml";
    public static final String COMPANY_PROFILE_BASE_URL = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpInfo/stockid/{0}.phtml";
    public static final String COMPANY_ANNOUNCEMENT_BASE_URL = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCB_AllBulletin/stockid/{0}.phtml";
    public static final String ANNUAL_REPORT_BASE_URL = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCB_Bulletin/stockid/{0}/page_type/yjdbg.phtml";
    public static final String FINANCIAL_SUMMARY_BASE_URL = "http://vip.stock.finance.sina.com.cn/corp/go.php/vFD_FinanceSummary/stockid/{0}/displaytype/4.phtml";
    public static final String ANNUAL_REPORT_DETAIL_URL = "http://vip.stock.finance.sina.com.cn{0}";

    /**
     * 股票 所属 交易所
     */
    public static final Map<String, String> STOCK_BASE_URL_TYPE_MAP = new HashMap<String, String>();

    static {
        STOCK_BASE_URL_TYPE_MAP.put("60", "sh");
        STOCK_BASE_URL_TYPE_MAP.put("30", "sz");
        STOCK_BASE_URL_TYPE_MAP.put("00", "sz");
    }

    public static String getCrawlerCollections(String name) {
        return StockCrawlerConstants.CRAWLER_MONGO_COLLECTIONS_PREFIX + name;
    }

}
