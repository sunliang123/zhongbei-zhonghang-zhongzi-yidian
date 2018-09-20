package com.waben.stock.applayer.strategist.crawler.mongo.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.waben.stock.applayer.strategist.crawler.model.news.DailyReportModel;
import com.waben.stock.applayer.strategist.crawler.mongo.DailyReportDao;

/**
 * Created by qmw on 2017/10/26.
 */
@Repository
public class DailyReportDaoImpl implements DailyReportDao {
	public static final String DAILY_REPORT = "lm_news_dailyReport";
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public String saveReport(DailyReportModel dailyReport) {
		dailyReport.setId(new ObjectId().toString());
		mongoTemplate.save(dailyReport, DAILY_REPORT);
		redisTemplate.opsForHash().put(DailyReportModel.DAILY_REPORT_CLICKS_REDISKEY, dailyReport.getId(), 0);
		return dailyReport.getId();
	}

	@PostConstruct
	public void init() {

		// 添加索引
		mongoTemplate.indexOps(DAILY_REPORT).ensureIndex(new Index("type", Sort.Direction.ASC));
		mongoTemplate.indexOps(DAILY_REPORT).ensureIndex(new Index("createTime", Sort.Direction.ASC));

	}

	@Override
	public List<?> selectDailyReport(Integer type, int page, int pageSize) {
		Query query = createQuery(type, page, pageSize);
		List<?> list = mongoTemplate.find(query, DailyReportModel.class, DAILY_REPORT);
		return list;
	}

	private Query createQuery(Integer type, int page, int pageSize) {
		Query query = Query.query(Criteria.where("type").is(type));
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("id", 1);
        fieldsObject.put("title", 1);
        fieldsObject.put("status", 1);
        fieldsObject.put("type", 1);
        fieldsObject.put("newType", 1);
        fieldsObject.put("format", 1);
        fieldsObject.put("content", 1);
        fieldsObject.put("url", 1);
        fieldsObject.put("coverUrl", 1);
        fieldsObject.put("createTime", 1);
        fieldsObject.put("modifyDate", 1);
        fieldsObject.put("operator", 1);
        fieldsObject.put("source", 1);
        fieldsObject.put("brief", 1);
        query = new BasicQuery(query.getQueryObject(), fieldsObject);
        int start = (page < 1 ? 0 : page) * pageSize;
        query.skip(start);
        query.limit(pageSize);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "createTime")));
		return query;
	}

	@Override
	public DailyReportModel selectDailyReport(String id) {
		Query query = Query.query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, DailyReportModel.class, DAILY_REPORT);
	}

}
