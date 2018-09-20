/**
 * @author huanglei
 * @date 2017年4月18日
 */

package com.waben.stock.applayer.tactics.crawler.model.news;

import java.util.Date;

/**
 * 日报
 */
public class DailyReportModel {
    public static final Integer STATUS_SHOW = 1;
    public static final String DAILY_REPORT_CLICKS_REDISKEY = "user:report:click:count";

    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 显示状态 0:不显示,1 显示
     */
    private Integer status;

    private Integer type; // 0,乐米日报 1,7*24资讯, 2 要闻

    private Integer newType;// 要闻的类型 1热点板块 2环球市场 3题材
    /**
     * 文章格式 {@link ArticleEnum}
     */
    private Integer format;

    /**
     * 内容
     */
    private String content;

    /**
     * 地址
     */
    private String url;
    /**
     * 封面图
     */
    private String coverUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 修改人
     */
    private String operator;
    /**
     * 阅读量
     */
    private Integer clicks;
    /**
     * 来源
     */
    private String source;

    private Integer collect;
    private String brief; //短内容 摘要

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Integer getNewType() {
        return newType;
    }

    public void setNewType(Integer newType) {
        this.newType = newType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFormat() {
        return format;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }
}
