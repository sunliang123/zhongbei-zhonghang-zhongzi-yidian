package com.waben.stock.applayer.tactics.crawler.mongo.impl;

import static com.waben.stock.applayer.tactics.crawler.util.StockCrawlerConstants.ANNUAL_REPORT_NAME;
import static com.waben.stock.applayer.tactics.crawler.util.StockCrawlerConstants.COMPANY_ANNOUNCEMENT_NAME;
import static com.waben.stock.applayer.tactics.crawler.util.StockCrawlerConstants.COMPANY_PROFILE_NAME;
import static com.waben.stock.applayer.tactics.crawler.util.StockCrawlerConstants.FINANCIAL_SUMMARY_NAME;
import static com.waben.stock.applayer.tactics.crawler.util.StockCrawlerConstants.STOCK_NEWS_NAME;
import static com.waben.stock.applayer.tactics.crawler.util.StockCrawlerConstants.getCrawlerCollections;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.waben.stock.applayer.tactics.crawler.model.AnnualReportModel;
import com.waben.stock.applayer.tactics.crawler.model.CompanyAnnouncementModel;
import com.waben.stock.applayer.tactics.crawler.model.CompanyProfileModel;
import com.waben.stock.applayer.tactics.crawler.model.FinancialSummaryModel;
import com.waben.stock.applayer.tactics.crawler.model.StockNewsModel;
import com.waben.stock.applayer.tactics.crawler.mongo.CrawlerDao;

/**
 * Created by yhj on 17/5/2.
 */
@Repository
public class CrawlerDaoImpl implements CrawlerDao {


    @Autowired
    private MongoTemplate mongoTemplate;


    @PostConstruct
    public void init() {

        // 添加索引
        mongoTemplate.indexOps(getCrawlerCollections(ANNUAL_REPORT_NAME)).ensureIndex(new Index("stockCode", Sort.Direction.ASC));
        mongoTemplate.indexOps(getCrawlerCollections(ANNUAL_REPORT_NAME)).ensureIndex(new Index("time", Sort.Direction.ASC));


        mongoTemplate.indexOps(getCrawlerCollections(COMPANY_ANNOUNCEMENT_NAME)).ensureIndex(new Index("stockCode", Sort.Direction.ASC));
        mongoTemplate.indexOps(getCrawlerCollections(COMPANY_ANNOUNCEMENT_NAME)).ensureIndex(new Index("time", Sort.Direction.ASC));


        mongoTemplate.indexOps(getCrawlerCollections(COMPANY_PROFILE_NAME)).ensureIndex(new Index("stockCode", Sort.Direction.ASC));


        mongoTemplate.indexOps(getCrawlerCollections(FINANCIAL_SUMMARY_NAME)).ensureIndex(new Index("stockCode", Sort.Direction.ASC));
        mongoTemplate.indexOps(getCrawlerCollections(FINANCIAL_SUMMARY_NAME)).ensureIndex(new Index("jiezhiriqi", Sort.Direction.ASC));


        mongoTemplate.indexOps(getCrawlerCollections(STOCK_NEWS_NAME)).ensureIndex(new Index("stockCode", Sort.Direction.ASC));
        mongoTemplate.indexOps(getCrawlerCollections(STOCK_NEWS_NAME)).ensureIndex(new Index("time", Sort.Direction.ASC));

    }

    public List<?> selectAnnualReport(String code, int page, int pageSize) {

        Query query = createQuery(code, page, pageSize);

        List<?> list =
                mongoTemplate.find(query, AnnualReportModel.class,
                        getCrawlerCollections(ANNUAL_REPORT_NAME));


        return list;
    }

    public List<?> selectCompanyAnnouncement(String code, Integer page, Integer pageSize) {
        Query query = createQuery(code, page, pageSize);

        List<?> list =
                mongoTemplate.find(query, CompanyAnnouncementModel.class,
                        getCrawlerCollections(COMPANY_ANNOUNCEMENT_NAME));


        return list;
    }


    public CompanyProfileModel selectCompanyProfile(String code) {

        Query query = Query.query(Criteria.where("stockCode").is(code));

        CompanyProfileModel list =
                mongoTemplate.findOne(query, CompanyProfileModel.class,
                        getCrawlerCollections(COMPANY_PROFILE_NAME));


        return list;
    }

    public List<?> selectFinancialSummary(String code, int page, int pageSize) {

        Query query = createQuery(code, page, pageSize);
        query.with(new Sort(Sort.Direction.DESC, "createDate"));
        List<?> list =
                mongoTemplate.find(query, FinancialSummaryModel.class,
                        getCrawlerCollections(FINANCIAL_SUMMARY_NAME));


        return list;
    }

    public List<?> selectStockNews(String code, int page, int pageSize) {

        Query query = Query.query(Criteria.where("stockCode").is(code));
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("createDate", 1);
        fieldsObject.put("from", 1);
        fieldsObject.put("id", 1);
        fieldsObject.put("stockCode", 1);
        fieldsObject.put("time", 1);
        fieldsObject.put("title", 1);
        fieldsObject.put("url", 1);

        query = new BasicQuery(query.getQueryObject(), fieldsObject);
        int start = (page < 1 ? 0 : page) * pageSize;
        query.skip(start);
        query.limit(pageSize);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "time")));

        List<?> list =
                mongoTemplate.find(query, StockNewsModel.class,
                        getCrawlerCollections(STOCK_NEWS_NAME));

        return list;
    }


    public StockNewsModel selectStockNews(String id) {
        Query query = Query.query(Criteria.where("id").is(id));

        return mongoTemplate.findOne(query, StockNewsModel.class,
                getCrawlerCollections(STOCK_NEWS_NAME));
    }

    public CompanyAnnouncementModel selectCompanyAnnouncement(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, CompanyAnnouncementModel.class,
                getCrawlerCollections(COMPANY_ANNOUNCEMENT_NAME));
    }

    public AnnualReportModel selectAnnualReport(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, AnnualReportModel.class,
                getCrawlerCollections(ANNUAL_REPORT_NAME));
    }

    private Query createQuery(String code, int page, int pageSize) {
        int start = (page < 1 ? 0 : page) * pageSize;

        Query query = Query.query(Criteria.where("stockCode").is(code));
        query.skip(start);
        query.limit(pageSize);

        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "time")));

        return query;
    }


}
