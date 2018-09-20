package com.waben.stock.applayer.strategist.crawler.model;

/**
 * Created by qmw on 2017/10/25.
 */
public class ReportModel {
    private String author; // 作者
    private String brief;// 摘要
    private String content; // 内容
    private Long ctime; // 创建时间
    private String title;//标题
    private String img;

    private String time_show;
    private String text;
    private String thumb;
    private Integer id;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime_show() {
        return time_show;
    }

    public void setTime_show(String time_show) {
        this.time_show = time_show;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
