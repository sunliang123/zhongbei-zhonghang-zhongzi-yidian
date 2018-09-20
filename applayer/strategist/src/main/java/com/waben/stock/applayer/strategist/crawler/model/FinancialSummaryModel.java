package com.waben.stock.applayer.strategist.crawler.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 年度报表
 * @author yangdehong
 * @version 2017年4月26日
 */
public class FinancialSummaryModel implements Serializable{

	private static final long serialVersionUID = -5718126385680345843L;
	
	private String id;
    private String stockCode;
    private String stockName;
    private String url;
    private String from;
    private Date createDate;
    
    private String jiezhiriqi; //截止日期
    private String meigujinzichan; //每股净资产
    private String meigushouyi; //每股收益
    private String meiguxianjinhanliang; //每股现金含量
    private String meiguzibengongjijin; //每股资本公积金
    private String gudingzichanheji; //固定资产合计
    private String liudongzichanheji; //流动资产合计
    private String zichanzongji; //资产总计
    private String changqifuzaiheji; //长期负债合计
    private String zhuyingyewushouru; //主营业务收入
    private String caiwufeiyong; //财务费用
    private String jinlirun; //净利润
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getJiezhiriqi() {
		return jiezhiriqi;
	}
	public void setJiezhiriqi(String jiezhiriqi) {
		this.jiezhiriqi = jiezhiriqi;
	}
	public String getMeigujinzichan() {
		return meigujinzichan;
	}
	public void setMeigujinzichan(String meigujinzichan) {
		this.meigujinzichan = meigujinzichan;
	}
	public String getMeigushouyi() {
		return meigushouyi;
	}
	public void setMeigushouyi(String meigushouyi) {
		this.meigushouyi = meigushouyi;
	}
	public String getMeiguxianjinhanliang() {
		return meiguxianjinhanliang;
	}
	public void setMeiguxianjinhanliang(String meiguxianjinhanliang) {
		this.meiguxianjinhanliang = meiguxianjinhanliang;
	}
	public String getMeiguzibengongjijin() {
		return meiguzibengongjijin;
	}
	public void setMeiguzibengongjijin(String meiguzibengongjijin) {
		this.meiguzibengongjijin = meiguzibengongjijin;
	}
	public String getGudingzichanheji() {
		return gudingzichanheji;
	}
	public void setGudingzichanheji(String gudingzichanheji) {
		this.gudingzichanheji = gudingzichanheji;
	}
	public String getLiudongzichanheji() {
		return liudongzichanheji;
	}
	public void setLiudongzichanheji(String liudongzichanheji) {
		this.liudongzichanheji = liudongzichanheji;
	}
	public String getZichanzongji() {
		return zichanzongji;
	}
	public void setZichanzongji(String zichanzongji) {
		this.zichanzongji = zichanzongji;
	}
	public String getChangqifuzaiheji() {
		return changqifuzaiheji;
	}
	public void setChangqifuzaiheji(String changqifuzaiheji) {
		this.changqifuzaiheji = changqifuzaiheji;
	}
	public String getZhuyingyewushouru() {
		return zhuyingyewushouru;
	}
	public void setZhuyingyewushouru(String zhuyingyewushouru) {
		this.zhuyingyewushouru = zhuyingyewushouru;
	}
	public String getCaiwufeiyong() {
		return caiwufeiyong;
	}
	public void setCaiwufeiyong(String caiwufeiyong) {
		this.caiwufeiyong = caiwufeiyong;
	}
	public String getJinlirun() {
		return jinlirun;
	}
	public void setJinlirun(String jinlirun) {
		this.jinlirun = jinlirun;
	}
    
}
