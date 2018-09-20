package com.waben.stock.applayer.strategist.crawler.mongo;

import java.util.Date;

/**
 * Created by yhj on 17/5/2.
 */
public interface CrawlerBaseDao {
    void updateCrawlerBase(String code, String infoType, Date endTime);

    Date getLastRequestDate(String code, String infoType);
}
