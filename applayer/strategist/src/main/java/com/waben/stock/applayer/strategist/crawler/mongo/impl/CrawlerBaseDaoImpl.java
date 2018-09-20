package com.waben.stock.applayer.strategist.crawler.mongo.impl;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.waben.stock.applayer.strategist.crawler.model.CrawlerModel;
import com.waben.stock.applayer.strategist.crawler.mongo.CrawlerBaseDao;
import com.waben.stock.applayer.strategist.crawler.util.StockCrawlerConstants;

/**
 * Created by yhj on 17/5/2.
 */
@Repository
public class CrawlerBaseDaoImpl implements CrawlerBaseDao {


    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void init() {

        // 添加索引
        mongoTemplate.indexOps(StockCrawlerConstants.CRAWLER_MONGO_BASE_COLLECTIONS).ensureIndex(new Index("stockCode", Sort.Direction.ASC));
        mongoTemplate.indexOps(StockCrawlerConstants.CRAWLER_MONGO_BASE_COLLECTIONS).ensureIndex(new Index("from", Sort.Direction.ASC));
        mongoTemplate.indexOps(StockCrawlerConstants.CRAWLER_MONGO_BASE_COLLECTIONS).ensureIndex(new Index("infoType", Sort.Direction.ASC));
    }


    @Override
    public void updateCrawlerBase(String code, String infoType, Date endTime) {
        Query query = new Query();
        query.addCriteria(Criteria.where("stockCode").is(code));
        query.addCriteria(Criteria.where("from").is(StockCrawlerConstants.STOCK_INFO_FROM));
        query.addCriteria(Criteria.where("infoType").is(infoType));
        mongoTemplate.remove(query, StockCrawlerConstants.CRAWLER_MONGO_BASE_COLLECTIONS);
        CrawlerModel crawlerModel = new CrawlerModel();
        crawlerModel.setStockCode(code);
        crawlerModel.setInfoType(infoType);
        crawlerModel.setFrom(StockCrawlerConstants.STOCK_INFO_FROM);
        crawlerModel.setEndtime(endTime);
        mongoTemplate.insert(crawlerModel, StockCrawlerConstants.CRAWLER_MONGO_BASE_COLLECTIONS);
    }


    @Override
    public Date getLastRequestDate(String code, String infoType) {
        // 获取上次的时间
        Date time = null;

        Query query = new Query();
        query.addCriteria(Criteria.where("stockCode").is(code));
        query.addCriteria(Criteria.where("from").is(StockCrawlerConstants.STOCK_INFO_FROM));
        query.addCriteria(Criteria.where("infoType").is(infoType));
        CrawlerModel crawlerModel = mongoTemplate.findOne(query, CrawlerModel.class, StockCrawlerConstants.CRAWLER_MONGO_BASE_COLLECTIONS);
        // 存在获取 最后时间
        if (crawlerModel != null) {
            time = crawlerModel.getEndtime();
        }

        return time;
    }
}
