package com.waben.stock.datalayer.stockoption.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/3/16.
 */

/**
 * 邮件本地备份信息
 */
@Entity
@Table(name = "mail_url_info")
public class MailUrlInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标的名称
     */
    @Column(length = 20, nullable = false)
    private String underlying;

    /**
     * 标的代码
     */
    @Column(length = 30)
    private String code;

    /**
     * 本地路径
     */
    @Column(length = 300, nullable = false)
    private String localUrl;

    /**
     * 模板名称
     */
    @Column(length = 30, nullable = false)
    private String templateName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnderlying() {
        return underlying;
    }

    public void setUnderlying(String underlying) {
        this.underlying = underlying;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
