package com.waben.stock.applayer.strategist.crawler.model;

import java.io.Serializable;
import java.util.Date;

/**
 * mysql 缓存对象
 * @author yangdehong
 * @version 2017年4月25日
 */
public class CrawlerModel implements Serializable{
	
	private static final long serialVersionUID = -2028990596655163559L;

	private String stockCode;

    private String infoType;
    
    private String stockName;
    
    private String from;

    private Date endtime;

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

}