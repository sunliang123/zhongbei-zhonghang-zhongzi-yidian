package com.waben.stock.applayer.strategist.crawler.model;

/**
 * Created by qmw on 2017/10/24.
 */

public class BulletinModel {

    private String content; //内容
    private String title; //标题
    private String brief; //短内容
    private Long ctime; // 创建时间
    private Integer jpush; // 是否推送
    private Integer recommend; //"0:普通，1:重要标红(int)",

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public Integer getJpush() {
        return jpush;
    }
    public void setJpush(Integer jpush) {
        this.jpush = jpush;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }
}
